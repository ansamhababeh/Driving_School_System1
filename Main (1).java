import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent; 
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;




public class Main {

    private static final TrainerDAO trainerDAO = new TrainerDAO();

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> createAndShowMainGUI());
       
    	
    }

    private static void createAndShowMainGUI() {
        JFrame mainFrame = new JFrame("Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application when main frame is closed
        mainFrame.setSize(400, 300);
        JPanel panel = new JPanel();
        mainFrame.add(panel);
        placeMainComponents(panel);
        mainFrame.setVisible(true); // This makes the main menu visible
    }


    private static void placeMainComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 1));

        JButton allTrainerOperationsButton = new JButton("All Trainer Operations");
        allTrainerOperationsButton.addActionListener(e -> createAndShowAllTrainerOperationsGUI());
        panel.add(allTrainerOperationsButton);

        JButton updateTrainerButton = new JButton("Update Trainer Availability");
        updateTrainerButton.addActionListener(e -> createAndShowUpdateTrainerGUI());
        panel.add(updateTrainerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);
    }

    private static void createAndShowAllTrainerOperationsGUI() {
   	 JFrame frame = new JFrame("Trainer Availability");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(300, 200);

     // Create and set up the panel
     JPanel panel = new JPanel();
     frame.add(panel);
     placeComponents1(panel);

     // Display the window
     frame.setVisible(true);
    }

    private static void createAndShowUpdateTrainerGUI() {
    	//for trainer
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel trainerIdLabel = new JLabel("Trainer ID:");
        trainerIdLabel.setBounds(10, 20, 80, 25);
        panel.add(trainerIdLabel);

        JTextField trainerIdText = new JTextField(20);
        trainerIdText.setBounds(100, 20, 165, 25);
        panel.add(trainerIdText);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setBounds(10, 50, 80, 25);
        panel.add(dayLabel);

        JTextField dayText = new JTextField(20);
        dayText.setBounds(100, 50, 165, 25);
        panel.add(dayText);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(10, 80, 80, 25);
        panel.add(timeLabel);

        JTextField timeText = new JTextField(20);
        timeText.setBounds(100, 80, 165, 25);
        panel.add(timeText);

        JButton addButton = new JButton("Add Availability");
        addButton.setBounds(10, 110, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int trainerID = Integer.parseInt(trainerIdText.getText());
                String day = dayText.getText();
                String time = timeText.getText();

                try (Connection connection = DBConnection.getConnection()) {
                    TrainerAvailabilityDAO dao = new TrainerAvailabilityDAO(connection);

                    if (dao.doesTrainerExist(trainerID,connection)) {
                        TrainerAvailability newAvailability = new TrainerAvailability(trainerID, day, time);
                        dao.addTrainerAvailability(newAvailability);
                        JOptionPane.showMessageDialog(null, "Availability added!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Trainer with ID " + trainerID + " does not exist.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private static void createAndShowGUI() {
        // Create and set up the main window
        JFrame frame = new JFrame("Driving School Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create and set up the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1)); // 6 options, 1 column
        frame.add(panel);

        // Add components to the panel
        placeComponents(panel);

        // Display the window
        frame.setVisible(true);
    }
    private static void placeComponents1(JPanel panel) {
        // Add New Trainer Button
        JButton addBtn = new JButton("Add New Trainer");
        addBtn.addActionListener(e -> addTrainer());
        panel.add(addBtn);

        // View Trainer Button
        JButton viewBtn = new JButton("View Trainer");
        viewBtn.addActionListener(e -> viewTrainer());
        panel.add(viewBtn);

        // Update Trainer Button
        JButton updateBtn = new JButton("Update Trainer");
        updateBtn.addActionListener(e -> updateTrainer());
        panel.add(updateBtn);

        // Delete Trainer Button
        JButton deleteBtn = new JButton("Delete Trainer");
        deleteBtn.addActionListener(e -> deleteTrainer());
        panel.add(deleteBtn);

        // List All Trainers Button
        JButton listBtn = new JButton("List All Trainers");
        listBtn.addActionListener(e -> listAllTrainers());
        panel.add(listBtn);

        // Exit Button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> System.exit(0));
        panel.add(exitBtn);
    }
    private static void addTrainer() {
        // Create a dialog for input
        JDialog dialog = new JDialog();
        dialog.setTitle("Add New Trainer");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2)); // Using GridLayout

        // Creating input fields for trainer details
        JTextField idField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JTextField numOfVehiclesField = new JTextField(10);
        JTextField cityAddressField = new JTextField(10);
        JTextField streetAddressField = new JTextField(10);
        JTextField primaryPhoneField = new JTextField(10);
        JTextField secondaryPhoneField = new JTextField(10);

        // Adding components to the dialog
        dialog.add(new JLabel("Trainer ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("First Name:"));
        dialog.add(firstNameField);
        dialog.add(new JLabel("Last Name:"));
        dialog.add(lastNameField);
        dialog.add(new JLabel("Number of Vehicles:"));
        dialog.add(numOfVehiclesField);
        dialog.add(new JLabel("City Address:"));
        dialog.add(cityAddressField);
        dialog.add(new JLabel("Street Address:"));
        dialog.add(streetAddressField);
        dialog.add(new JLabel("Primary Phone:"));
        dialog.add(primaryPhoneField);
        dialog.add(new JLabel("Secondary Phone:"));
        dialog.add(secondaryPhoneField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    int numberOfVehicles = Integer.parseInt(numOfVehiclesField.getText());
                    String cityAddress = cityAddressField.getText();
                    String streetAddress = streetAddressField.getText();
                    int primaryPhone = Integer.parseInt(primaryPhoneField.getText());
                    Integer secondaryPhone = secondaryPhoneField.getText().isEmpty() ? null : Integer.parseInt(secondaryPhoneField.getText());

                    // Create Trainer object and add it to the database
                    Trainer trainer = new Trainer(id, firstName, lastName, numberOfVehicles, cityAddress, streetAddress, primaryPhone, secondaryPhone);
                    trainerDAO.addTrainer(trainer);
                    JOptionPane.showMessageDialog(dialog, "Trainer added successfully!");
                    dialog.dispose(); // Close the dialog
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid input. Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(submitButton);

        // Show the dialog
        dialog.setVisible(true);
    }


    private static void viewTrainer() {
        // Ask the user for the Trainer ID
        String idStr = JOptionPane.showInputDialog(null, "Enter Trainer ID:", "View Trainer", JOptionPane.QUESTION_MESSAGE);
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);

                // Fetch the trainer details from the database
                Trainer trainer = trainerDAO.getTrainer(id);
                if (trainer != null) {
                    // Trainer found, display their details
                    String trainerDetails = "Trainer Details:\n"
                        + "ID: " + trainer.getTrainerID() + "\n"
                        + "First Name: " + trainer.getTrainerFirstName() + "\n"
                        + "Last Name: " + trainer.getTrainerLastName() + "\n"
                        + "Number of Vehicles Owned: " + trainer.getNumberOfVehiclesOwns() + "\n"
                        + "City Address: " + trainer.getCityAddress() + "\n"
                        + "Street Address: " + trainer.getStreetAddress() + "\n"
                        + "Primary Phone: " + trainer.getPhone1() + "\n"
                        + "Secondary Phone: " + (trainer.getPhone2() != null ? trainer.getPhone2() : "Not Available");
                    JOptionPane.showMessageDialog(null, trainerDetails, "Trainer Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Trainer not found
                    JOptionPane.showMessageDialog(null, "Trainer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void updateTrainer() {
        String idStr = JOptionPane.showInputDialog(null, "Enter Trainer ID to update:", "Update Trainer", JOptionPane.QUESTION_MESSAGE);
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);

                // Fetch the trainer details
                Trainer trainer = trainerDAO.getTrainer(id);
                if (trainer != null) {
                    // Create a dialog for updating trainer details
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Update Trainer");
                    dialog.setSize(400, 300);
                    dialog.setLayout(new GridLayout(0, 2));

                    // Creating input fields pre-filled with current trainer details
                    JTextField firstNameField = new JTextField(trainer.getTrainerFirstName(), 10);
                    JTextField lastNameField = new JTextField(trainer.getTrainerLastName(), 10);
                    JTextField numOfVehiclesField = new JTextField(String.valueOf(trainer.getNumberOfVehiclesOwns()), 10);
                    JTextField cityAddressField = new JTextField(trainer.getCityAddress(), 10);
                    JTextField streetAddressField = new JTextField(trainer.getStreetAddress(), 10);
                    JTextField primaryPhoneField = new JTextField(String.valueOf(trainer.getPhone1()), 10);
                    JTextField secondaryPhoneField = new JTextField(trainer.getPhone2() != null ? String.valueOf(trainer.getPhone2()) : "", 10);

                    // Adding components to the dialog
                    dialog.add(new JLabel("First Name:"));
                    dialog.add(firstNameField);
                    // Add other fields similarly
                    dialog.add(new JLabel("Last name:"));
                    dialog.add(lastNameField);
                    dialog.add(new JLabel("number of vehicles :"));
                    dialog.add(numOfVehiclesField);
                    dialog.add(new JLabel("city adress :"));
                    dialog.add(cityAddressField);
                    dialog.add(new JLabel("street adress :"));
                    dialog.add(streetAddressField);
                    dialog.add(new JLabel("primary phone:"));
                    dialog.add(primaryPhoneField);
                    dialog.add(new JLabel("secondary  phone:"));
                    dialog.add(secondaryPhoneField);
                    
                  

                    // Update button
                    JButton updateButton = new JButton("Update");
                    updateButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Update trainer details
                            trainer.setTrainerFirstName(firstNameField.getText());
                            trainer.setTrainerLastName(lastNameField.getText());
                            try {
                                int numOfVehicles = Integer.parseInt(numOfVehiclesField.getText());
                                trainer.setNumberOfVehiclesOwns(numOfVehicles);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(dialog, "Invalid number of vehicles.", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Return to prevent updating if the number is invalid
                            }
                            trainer.setCityAddress(cityAddressField.getText());
                            trainer.setStreetAddress(streetAddressField.getText());

                            try {
                                int primaryPhone = Integer.parseInt(primaryPhoneField.getText());
                                trainer.setPhone1(primaryPhone);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(dialog, "Invalid primary phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Return to prevent updating if the number is invalid
                            }

                            // For secondaryPhoneField, check if it's empty and handle accordingly
                            String secondaryPhoneStr = secondaryPhoneField.getText();
                            if (!secondaryPhoneStr.trim().isEmpty()) {
                                try {
                                    Integer secondaryPhone = Integer.parseInt(secondaryPhoneStr);
                                    trainer.setPhone2(secondaryPhone);
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(dialog, "Invalid secondary phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return; // Return to prevent updating if the number is invalid
                                }
                            } else {
                                trainer.setPhone2(null);
                            }

                            
                            trainerDAO.updateTrainer(trainer); // Update the trainer in the database
                            JOptionPane.showMessageDialog(dialog, "Trainer updated successfully!");
                            dialog.dispose(); // Close the dialog
                        }
                    });
                    dialog.add(updateButton);

                    // Show the dialog
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Trainer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private static void deleteTrainer() {
        // Ask for the Trainer ID to delete
        String idStr = JOptionPane.showInputDialog(null, "Enter Trainer ID to delete:", "Delete Trainer", JOptionPane.QUESTION_MESSAGE);
        
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);

                // Check if the trainer exists
                Trainer trainer = trainerDAO.getTrainer(id);
                if (trainer != null) {
                    // Confirm deletion
                    int confirm = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to delete Trainer with ID " + id + "?", 
                        "Confirm Deletion", 
                        JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Perform deletion
                        trainerDAO.deleteTrainer(id);
                        JOptionPane.showMessageDialog(null, "Trainer deleted successfully.");
                    }
                } else {
                    // Trainer not found
                    JOptionPane.showMessageDialog(null, "Trainer not found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                // Handle invalid ID input
                JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private static void listAllTrainers() {
        // Fetch the list of trainers
        List<Trainer> trainers = trainerDAO.getAllTrainers();

        if (trainers != null && !trainers.isEmpty()) {
            // Build a string to display the trainers
            StringBuilder trainersList = new StringBuilder("List of All Trainers:\n");
            for (Trainer trainer : trainers) {
                trainersList.append("ID: ").append(trainer.getTrainerID())
                            .append(", Name: ").append(trainer.getTrainerFirstName())
                            .append(" ").append(trainer.getTrainerLastName())
                            .append(", Number of Vehicles: ").append(trainer.getNumberOfVehiclesOwns())
                            .append(", city adress: ").append(trainer.getCityAddress())
                            .append(", street adress: ").append(trainer.getStreetAddress())
                            .append(", primary phone : ").append(trainer.getPhone1())
                            .append(", secondary phone: ").append(trainer.getPhone2())
                            .append("\n");
               
            }

            // Show the list in a message dialog
            JOptionPane.showMessageDialog(null, trainersList.toString());
        } else {
            // No trainers found
            JOptionPane.showMessageDialog(null, "No trainers found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
