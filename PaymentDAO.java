import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class PaymentDAO {
    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a payment
    public void addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (Pid, StudentId, amount, Pdate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getPid());
            pstmt.setInt(2, payment.getStudentId());
            pstmt.setInt(3, payment.getAmount());
            pstmt.setDate(4, new Date(payment.getPdate().getTime()));
            pstmt.executeUpdate();
        }
    }

    // Get a payment by ID
    public Payment getPayment(int pid) throws SQLException {
        String sql = "SELECT * FROM payment WHERE Pid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                    rs.getInt("Pid"),
                    rs.getInt("StudentId"),
                    rs.getInt("amount"),
                    rs.getDate("Pdate")
                );
            }
        }
        return null;
    }
    public boolean studentExists(int studentId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count is greater than 0
            }
        }
        return false;
    }


}
