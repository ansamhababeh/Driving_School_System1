import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleDAO {

    // Method to add a new vehicle
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicle (plateNumber, trainerID, vehicleModel, productionYear, mileage, engineSize, transmissionType, fuelType, insuranceDate, licenseDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getPlateNumber());
            stmt.setInt(2, vehicle.getTrainerID());
            stmt.setString(3, vehicle.getVehicleModel());
            stmt.setInt(4, vehicle.getProductionYear());
            stmt.setInt(5, vehicle.getMileage());
            stmt.setInt(6, vehicle.getEngineSize());
            stmt.setString(7, vehicle.getTransmissionType());
            stmt.setString(8, vehicle.getFuelType());
            stmt.setDate(9, new java.sql.Date(vehicle.getInsuranceDate().getTime()));
            stmt.setDate(10, new java.sql.Date(vehicle.getLicenseDate().getTime()));

            stmt.executeUpdate();
        }
    }

    // Method to retrieve a vehicle by its plate number
    public Vehicle getVehicle(int plateNumber) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE plateNumber = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, plateNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(
                            rs.getInt("plateNumber"),
                            rs.getInt("trainerID"),
                            rs.getString("vehicleModel"),
                            rs.getInt("productionYear"),
                            rs.getInt("mileage"),
                            rs.getInt("engineSize"),
                            rs.getString("transmissionType"),
                            rs.getString("fuelType"),
                            rs.getDate("insuranceDate"),
                            rs.getDate("licenseDate")
                    );
                }
            }
        }
        return null;
    }

    // Method to update a vehicle's details
    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicle SET trainerID = ?, vehicleModel = ?, productionYear = ?, mileage = ?, engineSize = ?, transmissionType = ?, fuelType = ?, insuranceDate = ?, licenseDate = ? WHERE plateNumber = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getTrainerID());
            stmt.setString(2, vehicle.getVehicleModel());
            stmt.setInt(3, vehicle.getProductionYear());
            stmt.setInt(4, vehicle.getMileage());
            stmt.setInt(5, vehicle.getEngineSize());
            stmt.setString(6, vehicle.getTransmissionType());
            stmt.setString(7, vehicle.getFuelType());
            stmt.setDate(8, new java.sql.Date(vehicle.getInsuranceDate().getTime()));
            stmt.setDate(9, new java.sql.Date(vehicle.getLicenseDate().getTime()));
            stmt.setInt(10, vehicle.getPlateNumber());

            stmt.executeUpdate();
        }
    }

 // Method to delete a vehicle using a Vehicle object
    public void deleteVehicle(Vehicle vehicle) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE plateNumber = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getPlateNumber());
            stmt.executeUpdate();
        }
    }


    // Method to retrieve all vehicles
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("plateNumber"),
                        rs.getInt("trainerID"),
                        rs.getString("vehicleModel"),
                        rs.getInt("productionYear"),
                        rs.getInt("mileage"),
                        rs.getInt("engineSize"),
                        rs.getString("transmissionType"),
                        rs.getString("fuelType"),
                        rs.getDate("insuranceDate"),
                        rs.getDate("licenseDate")
                );
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }
}
