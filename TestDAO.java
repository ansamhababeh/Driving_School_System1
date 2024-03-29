import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TestDAO {
    private Connection connection;

    public TestDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a test record
    public void addTest(Test test) throws SQLException {
        String sql = "INSERT INTO Test (Tid, Tresult, Pdate, StudentId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, test.getTid());
            pstmt.setString(2, test.getTresult());
            pstmt.setDate(3, new Date(test.getPdate().getTime()));
            pstmt.setInt(4, test.getStudentId());
            pstmt.executeUpdate();
        }
    }

    // Get a test record by ID
    public Test getTest(int tid) throws SQLException {
        String sql = "SELECT * FROM Test WHERE Tid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Test(
                    rs.getInt("Tid"),
                    rs.getString("Tresult"),
                    rs.getDate("Pdate"),
                    rs.getInt("StudentId")
                );
            }
        }
        return null;
    }
    
    public boolean studentExists(int studentId) throws SQLException {
        System.out.println("Checking existence for student ID: " + studentId); // Debugging line
        String sql = "SELECT studentId FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            boolean exists = rs.next();
            System.out.println("Student ID found: " + (exists ? rs.getInt("studentId") : "None")); // More debugging
            return exists;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()); // Log SQL exception if any
            throw e;
        }
    }





    // Additional CRUD methods (update, delete) can be added similarly
}
