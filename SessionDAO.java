import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO {
    private Connection connection;

    public SessionDAO(Connection connection) {
        this.connection = connection;
    }
    public void addStudentSession(int studentId, int trainerId) throws SQLException {
        // Check if the student already exists
        String checkSql = "SELECT COUNT(*) FROM studentsession WHERE studentID = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, studentId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Student exists, delete existing record
                String deleteSql = "DELETE FROM studentsession WHERE studentID = ?";
                try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                    deleteStmt.setInt(1, studentId);
                    deleteStmt.executeUpdate();
                }
            }
        }

        // Insert new record
        String insertSql = "INSERT INTO studentsession (studentID, trainerID) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setInt(1, studentId);
            insertStmt.setInt(2, trainerId);
            insertStmt.executeUpdate();
        }
    }


    // Add a session
    public void addSession(Session session) throws SQLException {
        String sql = "INSERT INTO session (sessionId, studentID, plateNumber, trainerID, sessionCost, sessionTime, sessionDate, sessionStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, session.getSessionId());
            pstmt.setInt(2, session.getStudentID());
            pstmt.setInt(3, session.getPlateNumber());
            pstmt.setInt(4, session.getTrainerID());
            pstmt.setInt(5, session.getSessionCost());
            pstmt.setString(6, session.getSessionTime());
            pstmt.setString(7, session.getSessionDate());
            pstmt.setString(8, session.getSessionStatus());
            pstmt.executeUpdate();
        }
    }

    // Retrieve a session by ID
    public Session getSession(int sessionId) throws SQLException {
        String sql = "SELECT * FROM session WHERE sessionId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Session(
                    rs.getInt("sessionId"),
                    rs.getInt("studentID"),
                    rs.getInt("plateNumber"),
                    rs.getInt("trainerID"),
                    rs.getInt("sessionCost"),
                    rs.getString("sessionTime"),
                    rs.getString("sessionDate"),
                    rs.getString("sessionStatus")
                );
            }
            return null;
        }
    }

    // Update a session
    public void updateSession(Session session) throws SQLException {
        String sql = "UPDATE session SET studentID = ?, plateNumber = ?, trainerID = ?, sessionCost = ?, sessionTime = ?, sessionDate = ?, sessionStatus = ? WHERE sessionId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, session.getStudentID());
            pstmt.setInt(2, session.getPlateNumber());
            pstmt.setInt(3, session.getTrainerID());
            pstmt.setInt(4, session.getSessionCost());
            pstmt.setString(5, session.getSessionTime());
            pstmt.setString(6, session.getSessionDate());
            pstmt.setString(7, session.getSessionStatus());
            pstmt.setInt(8, session.getSessionId());
            pstmt.executeUpdate();
        }
    }

    // Delete a session
    public void deleteSession(int sessionId) throws SQLException {
        String sql = "DELETE FROM session WHERE sessionId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            pstmt.executeUpdate();
        }
    }
}
