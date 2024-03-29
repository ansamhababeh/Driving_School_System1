import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (studentId, studentFirstName, studentMiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setStudentPreparedStatement(pstmt, student);
            pstmt.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET studentFirstName = ?, studentMiddleName = ?, studentLastName = ?, cityAddress = ?, streetAddress = ?, phone1 = ?, phone2 = ?, wallet = ?, gender = ?, birthdate = ? WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setStudentPreparedStatement(pstmt, student);
            pstmt.setInt(11, student.getStudentId());
            pstmt.executeUpdate();
        }
    }

    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        }
    }

    public Student getStudent(int studentId) throws SQLException {
        String sql = "SELECT * FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractStudentFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    // Helper method to extract data from ResultSet and create a Student object
    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        int studentId = rs.getInt("studentId");
        String firstName = rs.getString("studentFirstName");
        String middleName = rs.getString("studentMiddleName");
        String lastName = rs.getString("studentLastName");
        String cityAddress = rs.getString("cityAddress");
        String streetAddress = rs.getString("streetAddress");
        int phone1 = rs.getInt("phone1");
        Integer phone2 = (Integer) rs.getObject("phone2");
        int wallet = rs.getInt("wallet");
        String gender = rs.getString("gender");
        Date birthdate = rs.getDate("birthdate");

        return new Student(studentId, firstName, middleName, lastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate);
    }

    // Helper method to set parameters of PreparedStatement from Student object
    private void setStudentPreparedStatement(PreparedStatement pstmt, Student student) throws SQLException {
        pstmt.setInt(1, student.getStudentId());
        pstmt.setString(2, student.getStudentFirstName());
        pstmt.setString(3, student.getStudentMiddleName());
        pstmt.setString(4, student.getStudentLastName());
        pstmt.setString(5, student.getCityAddress());
        pstmt.setString(6, student.getStreetAddress());
        pstmt.setInt(7, student.getPhone1());
        pstmt.setObject(8, student.getPhone2());
        pstmt.setInt(9, student.getWallet());
        pstmt.setString(10, student.getGender());
        pstmt.setDate(11, student.getBirthdate());
    }
    public boolean studentExists(int studentId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
