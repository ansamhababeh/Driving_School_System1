import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainVehicle {

    private JFrame frame;
    private JTextField plateNumberField, trainerIdField, vehicleModelField, productionYearField, mileageField, engineSizeField, transmissionTypeField, fuelTypeField, insuranceDateField, licenseDateField;
    private VehicleDAO vehicleDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MainVehicle() {
        vehicleDAO = new VehicleDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Vehicle Management System");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);

        // Add Panel
        JPanel addPanel = createAddPanel();
        tabbedPane.addTab("Add Vehicle", addPanel);

        // Update Panel
        JPanel updatePanel = createUpdatePanel();
        tabbedPane.addTab("Update Vehicle", updatePanel);

        // Delete Panel
        JPanel deletePanel = createDeletePanel();
        tabbedPane.addTab("Delete Vehicle", deletePanel);

        // View Panel
        JPanel viewPanel = createViewPanel();
        tabbedPane.addTab("View Vehicle", viewPanel);

        frame.pack(); // Adjusts the frame size to fit all components
        frame.setVisible(true);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Plate Number
        JLabel lblPlateNumber = new JLabel("Plate Number:");
        panel.add(lblPlateNumber);
        plateNumberField = new JTextField();
        panel.add(plateNumberField);
        plateNumberField.setColumns(10);

        // Trainer ID
        JLabel lblTrainerId = new JLabel("Trainer ID:");
        panel.add(lblTrainerId);
        trainerIdField = new JTextField();
        panel.add(trainerIdField);
        trainerIdField.setColumns(10);

        // Vehicle Model
        JLabel lblVehicleModel = new JLabel("Vehicle Model:");
        panel.add(lblVehicleModel);
        vehicleModelField = new JTextField();
        panel.add(vehicleModelField);
        vehicleModelField.setColumns(10);

        // Production Year
        JLabel lblProductionYear = new JLabel("Production Year:");
        panel.add(lblProductionYear);
        productionYearField = new JTextField();
        panel.add(productionYearField);
        productionYearField.setColumns(10);

        // Mileage
        JLabel lblMileage = new JLabel("Mileage:");
        panel.add(lblMileage);
        mileageField = new JTextField();
        panel.add(mileageField);
        mileageField.setColumns(10);

        // Engine Size
        JLabel lblEngineSize = new JLabel("Engine Size:");
        panel.add(lblEngineSize);
        engineSizeField = new JTextField();
        panel.add(engineSizeField);
        engineSizeField.setColumns(10);

        // Transmission Type
        JLabel lblTransmissionType = new JLabel("Transmission Type:");
        panel.add(lblTransmissionType);
        transmissionTypeField = new JTextField();
        panel.add(transmissionTypeField);
        transmissionTypeField.setColumns(10);

        // Fuel Type
        JLabel lblFuelType = new JLabel("Fuel Type:");
        panel.add(lblFuelType);
        fuelTypeField =  new JTextField();
        panel.add(fuelTypeField);
        fuelTypeField.setColumns(10);

        // Insurance Date
        JLabel lblInsuranceDate = new JLabel("Insurance Date (yyyy-mm-dd):");
        panel.add(lblInsuranceDate);
        insuranceDateField = new JTextField();
        panel.add(insuranceDateField);
        insuranceDateField.setColumns(10);

        // License Date
        JLabel lblLicenseDate = new JLabel("License Date (yyyy-mm-dd):");
        panel.add(lblLicenseDate);
        licenseDateField = new JTextField();
        panel.add(licenseDateField);
        licenseDateField.setColumns(10);

        // Add Vehicle Button
        JButton addButton = new JButton("Add Vehicle");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });
        panel.add(addButton);

        return panel;
    }


    private void addVehicle() {
        try {
            int plateNumber = Integer.parseInt(plateNumberField.getText().trim());
            int trainerID = Integer.parseInt(trainerIdField.getText().trim());
            String vehicleModel = vehicleModelField.getText().trim();
            int productionYear = Integer.parseInt(productionYearField.getText().trim());
            int mileage = Integer.parseInt(mileageField.getText().trim());
            int engineSize = Integer.parseInt(engineSizeField.getText().trim());
            String transmissionType = transmissionTypeField.getText().trim();
            String fuelType = fuelTypeField.getText().trim();

            Date insuranceDate = null;
            if (!insuranceDateField.getText().trim().isEmpty()) {
                insuranceDate = dateFormat.parse(insuranceDateField.getText().trim());
            }

            Date licenseDate = null;
            if (!licenseDateField.getText().trim().isEmpty()) {
                licenseDate = dateFormat.parse(licenseDateField.getText().trim());
            }

            Vehicle vehicle = new Vehicle(plateNumber, trainerID, vehicleModel, productionYear, mileage, engineSize, transmissionType, fuelType, insuranceDate, licenseDate);
            vehicleDAO.addVehicle(vehicle);

            JOptionPane.showMessageDialog(frame, "Vehicle added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numerical values.");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Please enter the date in the correct format (yyyy-mm-dd).");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error adding vehicle to the database: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }


    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Plate Number - used to identify the vehicle
        JLabel lblUpdatePlateNumber = new JLabel("Plate Number:");
        panel.add(lblUpdatePlateNumber);
        JTextField updatePlateNumberField = new JTextField();
        panel.add(updatePlateNumberField);
        updatePlateNumberField.setColumns(10);

        // Trainer ID
        JLabel lblUpdateTrainerId = new JLabel("Trainer ID:");
        panel.add(lblUpdateTrainerId);
        JTextField updateTrainerIdField = new JTextField();
        panel.add(updateTrainerIdField);
        updateTrainerIdField.setColumns(10);

        // Vehicle Model
        JLabel lblUpdateVehicleModel = new JLabel("Vehicle Model:");
        panel.add(lblUpdateVehicleModel);
        JTextField updateVehicleModelField = new JTextField();
        panel.add(updateVehicleModelField);
        updateVehicleModelField.setColumns(10);

        // Production Year
        JLabel lblUpdateProductionYear = new JLabel("Production Year:");
        panel.add(lblUpdateProductionYear);
        JTextField updateProductionYearField = new JTextField();
        panel.add(updateProductionYearField);
        updateProductionYearField.setColumns(10);

        // Mileage
        JLabel lblUpdateMileage = new JLabel("Mileage:");
        panel.add(lblUpdateMileage);
        JTextField updateMileageField = new JTextField();
        panel.add(updateMileageField);
        updateMileageField.setColumns(10);

        // Engine Size
        JLabel lblUpdateEngineSize = new JLabel("Engine Size:");
        panel.add(lblUpdateEngineSize);
        JTextField updateEngineSizeField = new JTextField();
        panel.add(updateEngineSizeField);
        updateEngineSizeField.setColumns(10);

        // Transmission Type
        JLabel lblUpdateTransmissionType = new JLabel("Transmission Type:");
        panel.add(lblUpdateTransmissionType);
        JTextField updateTransmissionTypeField = new JTextField();
        panel.add(updateTransmissionTypeField);
        updateTransmissionTypeField.setColumns(10);

        // Fuel Type
        JLabel lblUpdateFuelType = new JLabel("Fuel Type:");
        panel.add(lblUpdateFuelType);
        JTextField updateFuelTypeField = new JTextField();
        panel.add(updateFuelTypeField);
        updateFuelTypeField.setColumns(10);

        // Insurance Date
        JLabel lblUpdateInsuranceDate = new JLabel("Insurance Date (yyyy-mm-dd):");
        panel.add(lblUpdateInsuranceDate);
        JTextField updateInsuranceDateField = new JTextField();
        panel.add(updateInsuranceDateField);
        updateInsuranceDateField.setColumns(10);

        // License Date
        JLabel lblUpdateLicenseDate = new JLabel("License Date (yyyy-mm-dd):");
        panel.add(lblUpdateLicenseDate);
        JTextField updateLicenseDateField = new JTextField();
        panel.add(updateLicenseDateField);
        updateLicenseDateField.setColumns(10);

        // Update Button
        JButton updateButton = new JButton("Update Vehicle");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateVehicle(updatePlateNumberField.getText(),
                              updateTrainerIdField.getText(),
                              updateVehicleModelField.getText(),
                              updateProductionYearField.getText(),
                              updateMileageField.getText(),
                              updateEngineSizeField.getText(),
                              updateTransmissionTypeField.getText(),
                              updateFuelTypeField.getText(),
                              updateInsuranceDateField.getText(),
                              updateLicenseDateField.getText());
            }
        });
        panel.add(updateButton);

        return panel;
    }



    private void updateVehicle(String plateNumberStr, String trainerIdStr, String vehicleModel,
            String productionYearStr, String mileageStr, String engineSizeStr,
            String transmissionType, String fuelType, String insuranceDateStr,
            String licenseDateStr) {
try {
int plateNumber = Integer.parseInt(plateNumberStr.trim());
int trainerID = trainerIdStr.isEmpty() ? -1 : Integer.parseInt(trainerIdStr.trim());
int productionYear = productionYearStr.isEmpty() ? 0 : Integer.parseInt(productionYearStr.trim());
int mileage = mileageStr.isEmpty() ? 0 : Integer.parseInt(mileageStr.trim());
int engineSize = engineSizeStr.isEmpty() ? 0 : Integer.parseInt(engineSizeStr.trim());

Date insuranceDate = null;
if (!insuranceDateStr.trim().isEmpty()) {
insuranceDate = dateFormat.parse(insuranceDateStr.trim());
}

Date licenseDate = null;
if (!licenseDateStr.trim().isEmpty()) {
licenseDate = dateFormat.parse(licenseDateStr.trim());
}

// Assuming a constructor for Vehicle that takes all these parameters
Vehicle vehicle = new Vehicle(plateNumber, trainerID, vehicleModel, productionYear, mileage, engineSize, transmissionType, fuelType, insuranceDate, licenseDate);

// Assuming a method in VehicleDAO for updating a vehicle
vehicleDAO.updateVehicle(vehicle);

JOptionPane.showMessageDialog(frame, "Vehicle updated successfully.");
} catch (NumberFormatException e) {
JOptionPane.showMessageDialog(frame, "Please enter valid numerical values.");
} catch (ParseException e) {
JOptionPane.showMessageDialog(frame, "Please enter the date in the correct format (yyyy-mm-dd).");
} catch (SQLException e) {
JOptionPane.showMessageDialog(frame, "Error updating vehicle: " + e.getMessage());
} catch (Exception e) {
JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
}
}


    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel lblPlateNumber = new JLabel("Plate Number:");
        panel.add(lblPlateNumber);

        JTextField deletePlateNumberField = new JTextField();
        panel.add(deletePlateNumberField);

        JButton deleteButton = new JButton("Delete Vehicle");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteVehicle(deletePlateNumberField.getText());
            }
        });
        panel.add(deleteButton);

        return panel;
    }

    private void deleteVehicle(String plateNumberStr) {
        try {
            int plateNumber = Integer.parseInt(plateNumberStr.trim());

            // Create a Vehicle object with the plate number
            Vehicle vehicle = new Vehicle();
            vehicle.setPlateNumber(plateNumber);

            // Now call deleteVehicle with the Vehicle object
            vehicleDAO.deleteVehicle(vehicle);

            JOptionPane.showMessageDialog(frame, "Vehicle deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid plate number format. Please enter a valid number.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error deleting vehicle: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }



    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel lblPlateNumber = new JLabel("Plate Number:");
        panel.add(lblPlateNumber);

        JTextField viewPlateNumberField = new JTextField();
        panel.add(viewPlateNumberField);

        JTextArea vehicleDetailsArea = new JTextArea(10, 30);
        vehicleDetailsArea.setEditable(false);
        panel.add(new JScrollPane(vehicleDetailsArea));

        JButton viewButton = new JButton("View Vehicle");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewVehicle(viewPlateNumberField.getText(), vehicleDetailsArea);
            }
        });
        panel.add(viewButton);

        return panel;
    }

    private void viewVehicle(String plateNumberStr, JTextArea vehicleDetailsArea) {
        try {
            int plateNumber = Integer.parseInt(plateNumberStr.trim());

            // Assuming a method in VehicleDAO for getting a vehicle by its plate number
            Vehicle vehicle = vehicleDAO.getVehicle(plateNumber);

            if (vehicle != null) {
                // Assuming getters in the Vehicle class to retrieve its attributes
                StringBuilder vehicleDetails = new StringBuilder();
                vehicleDetails.append("Plate Number: ").append(vehicle.getPlateNumber()).append("\n");
                vehicleDetails.append("Trainer ID: ").append(vehicle.getTrainerID()).append("\n");
                vehicleDetails.append("Vehicle Model: ").append(vehicle.getVehicleModel()).append("\n");
                vehicleDetails.append("Production Year: ").append(vehicle.getProductionYear()).append("\n");
                vehicleDetails.append("Mileage: ").append(vehicle.getMileage()).append("\n");
                vehicleDetails.append("Engine Size: ").append(vehicle.getEngineSize()).append("\n");
                vehicleDetails.append("Transmission Type: ").append(vehicle.getTransmissionType()).append("\n");
                vehicleDetails.append("Fuel Type: ").append(vehicle.getFuelType()).append("\n");
                vehicleDetails.append("Insurance Date: ").append(dateFormat.format(vehicle.getInsuranceDate())).append("\n");
                vehicleDetails.append("License Date: ").append(dateFormat.format(vehicle.getLicenseDate())).append("\n");

                vehicleDetailsArea.setText(vehicleDetails.toString());
            } else {
                vehicleDetailsArea.setText("Vehicle not found.");
            }
        } catch (NumberFormatException e) {
            vehicleDetailsArea.setText("Invalid plate number format. Please enter a valid number.");
        } catch (SQLException e) {
            vehicleDetailsArea.setText("Error retrieving vehicle: " + e.getMessage());
        } catch (Exception e) {
            vehicleDetailsArea.setText("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainVehicle window = new MainVehicle();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // ... similarly implement methods for update, delete, view
}
