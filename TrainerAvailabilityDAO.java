import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerAvailabilityDAO {
    private Connection connection;

    public TrainerAvailabilityDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTrainerAvailability(TrainerAvailability trainerAvailability) throws SQLException {
        String sql = "INSERT INTO trainerAvalibility (trainerID, avalableDay, avalableTime) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trainerAvailability.getTrainerID());
            pstmt.setString(2, trainerAvailability.getAvailableDay());
            pstmt.setString(3, trainerAvailability.getAvailableTime());
            pstmt.executeUpdate();
        }
    }

    public void updateTrainerAvailability(TrainerAvailability trainerAvailability) throws SQLException {
        String sql = "UPDATE trainerAvalibility SET avalableDay = ?, avalableTime = ? WHERE trainerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, trainerAvailability.getAvailableDay());
            pstmt.setString(2, trainerAvailability.getAvailableTime());
            pstmt.setInt(3, trainerAvailability.getTrainerID());
            pstmt.executeUpdate();
        }
    }

    public void deleteTrainerAvailability(int trainerID, String avalableDay, String avalableTime) throws SQLException {
        String sql = "DELETE FROM trainerAvalibility WHERE trainerID = ? AND avalableDay = ? AND avalableTime = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trainerID);
            pstmt.setString(2, avalableDay);
            pstmt.setString(3, avalableTime);
            pstmt.executeUpdate();
        }
    }

    public List<TrainerAvailability> getTrainerAvailabilities() throws SQLException {
        List<TrainerAvailability> availabilities = new ArrayList<>();
        String sql = "SELECT * FROM trainerAvalibility";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int trainerID = rs.getInt("trainerID");
                String avalableDay = rs.getString("avalableDay");
                String avalableTime = rs.getString("avalableTime");
                availabilities.add(new TrainerAvailability(trainerID, avalableDay, avalableTime));
            }
        }
        return availabilities;
    }
    public boolean doesTrainerExist(int trainerID, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM trainer WHERE trainerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trainerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
