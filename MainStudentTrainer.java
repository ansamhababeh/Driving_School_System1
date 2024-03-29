import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainStudentTrainer {

    public static void main(String[] args) {
        // Database connection setup
        Connection connection = null;
        try {
            // Assuming you have a method to create and return a database connection
            connection = createDatabaseConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + ex.getMessage());
            return; // Exit if connection fails
        }

        // Create and set up the window
        JFrame frame = new JFrame("Student Trainer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create components and put them in the frame
        JTextField studentIdField = new JTextField(10);
        JTextField trainerIdField = new JTextField(10);

        JButton addButton = new JButton("Add Student-Trainer");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Trainer ID:"));
        panel.add(trainerIdField);
        panel.add(addButton);
        frame.getContentPane().add(panel);

        frame.setVisible(true);

        // DAO objects initialization
        StudentDAO studentDao = new StudentDAO(connection);
        TrainerAvailabilityDAO trainerDao = new TrainerAvailabilityDAO(connection);
        StudentTrainerDAO studentTrainerDao = new StudentTrainerDAO(connection);

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentId = Integer.parseInt(studentIdField.getText());
                    int trainerId = Integer.parseInt(trainerIdField.getText());
                    Connection connection = null;
                    connection = createDatabaseConnection();
                    if (studentDao.studentExists(studentId) && trainerDao.doesTrainerExist(trainerId,connection)) {
                        StudentTrainer studentTrainer = new StudentTrainer(studentId, trainerId);
                        studentTrainerDao.addStudentTrainer(studentTrainer);
                        JOptionPane.showMessageDialog(frame, "Student-Trainer added successfully");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Student or Trainer does not exist.");
                    }

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Invalid input for Student ID or Trainer ID.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error adding student-trainer: " + ex.getMessage());
                }
            }
        });
    }

    private static Connection createDatabaseConnection() throws SQLException {
        // Replace with your database details
        String url = "jdbc:mysql://localhost:3306/drivingschoolP";
        String user = "root";
        String password = "entesar1234";

        return DriverManager.getConnection(url, user, password);
    }
}
