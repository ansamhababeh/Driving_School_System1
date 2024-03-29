import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public void addTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainer.getTrainerID());
            pstmt.setString(2, trainer.getTrainerFirstName());
            pstmt.setString(3, trainer.getTrainerLastName());
            pstmt.setInt(4, trainer.getNumberOfVehiclesOwns());
            pstmt.setString(5, trainer.getCityAddress());
            pstmt.setString(6, trainer.getStreetAddress());
            pstmt.setInt(7, trainer.getPhone1());
            if (trainer.getPhone2() != null) {
                pstmt.setInt(8, trainer.getPhone2());
            } else {
                pstmt.setNull(8, java.sql.Types.INTEGER);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Trainer getTrainer(int trainerID) {
        String sql = "SELECT * FROM trainer WHERE trainerID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerID);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Trainer trainer = new Trainer(
                    rs.getInt("trainerID"),
                    rs.getString("trainerFirstName"),
                    rs.getString("trainerLastName"),
                    rs.getInt("numberOfVehiclesOwns"),
                    rs.getString("cityAddress"),
                    rs.getString("streetAddress"),
                    rs.getInt("phone1"),
                    rs.getInt("phone2")
                );
                return trainer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainer";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Trainer trainer = new Trainer(
                    rs.getInt("trainerID"),
                    rs.getString("trainerFirstName"),
                    rs.getString("trainerLastName"),
                    rs.getInt("numberOfVehiclesOwns"),
                    rs.getString("cityAddress"),
                    rs.getString("streetAddress"),
                    rs.getInt("phone1"),
                    rs.getInt("phone2")
                );
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }

    public void updateTrainer(Trainer trainer) {
        String sql = "UPDATE trainer SET trainerFirstName = ?, trainerLastName = ?, numberOfVehiclesOwns = ?, cityAddress = ?, streetAddress = ?, phone1 = ?, phone2 = ? WHERE trainerID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainer.getTrainerFirstName());
            pstmt.setString(2, trainer.getTrainerLastName());
            pstmt.setInt(3, trainer.getNumberOfVehiclesOwns());
            pstmt.setString(4, trainer.getCityAddress());
            pstmt.setString(5, trainer.getStreetAddress());
            pstmt.setInt(6, trainer.getPhone1());
            if (trainer.getPhone2() != null) {
                pstmt.setInt(7, trainer.getPhone2());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            pstmt.setInt(8, trainer.getTrainerID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainer(int trainerID) {
        String sql = "DELETE FROM trainer WHERE trainerID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
