import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTrainerDAO {
    private Connection connection;

    public StudentTrainerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudentTrainer(StudentTrainer studentTrainer) throws SQLException {
        String sql = "INSERT INTO student_trainer (studentId, trainerId) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentTrainer.getStudentId());
            pstmt.setInt(2, studentTrainer.getTrainerId());
            pstmt.executeUpdate();
        }
    }

    public void deleteStudentTrainer(int studentId, int trainerId) throws SQLException {
        String sql = "DELETE FROM student_trainer WHERE studentId = ? AND trainerId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, trainerId);
            pstmt.executeUpdate();
        }
    }

    public List<Integer> getTrainersForStudent(int studentId) throws SQLException {
        List<Integer> trainerIds = new ArrayList<>();
        String sql = "SELECT trainerId FROM student_trainer WHERE studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    trainerIds.add(rs.getInt("trainerId"));
                }
            }
        }
        return trainerIds;
    }

    public List<Integer> getStudentsForTrainer(int trainerId) throws SQLException {
        List<Integer> studentIds = new ArrayList<>();
        String sql = "SELECT studentId FROM student_trainer WHERE trainerId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    studentIds.add(rs.getInt("studentId"));
                }
            }
        }
        return studentIds;
    }
}
