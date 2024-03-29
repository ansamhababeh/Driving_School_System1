import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SessionGUI {

    private JFrame frame;
    private JTextField sessionIdField, studentIdField, plateNumberField, trainerIdField, sessionCostField, sessionTimeField, sessionDateField, sessionStatusField;
    private JTextArea resultArea;
    private SessionDAO sessionDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public SessionGUI() {
        try {
            Connection connection = DBConnection.getConnection();
            sessionDAO = new SessionDAO(connection);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to establish database connection: " + e.getMessage());
            return;
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Session Management System");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));

        inputPanel.add(new JLabel("Session ID:"));
        sessionIdField = new JTextField(10);
        inputPanel.add(sessionIdField);

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Plate Number:"));
        plateNumberField = new JTextField(10);
        inputPanel.add(plateNumberField);

        inputPanel.add(new JLabel("Trainer ID:"));
        trainerIdField = new JTextField(10);
        inputPanel.add(trainerIdField);

        inputPanel.add(new JLabel("Session Cost:"));
        sessionCostField = new JTextField(10);
        inputPanel.add(sessionCostField);

        inputPanel.add(new JLabel("Session Time:"));
        sessionTimeField = new JTextField(10);
        inputPanel.add(sessionTimeField);

        inputPanel.add(new JLabel("Session Date (yyyy-mm-dd):"));
        sessionDateField = new JTextField(10);
        inputPanel.add(sessionDateField);

        inputPanel.add(new JLabel("Session Status:"));
        sessionStatusField = new JTextField(10);
        inputPanel.add(sessionStatusField);

        JButton addButton = new JButton("Add Session");
        addButton.addActionListener(this::addSession);
        inputPanel.add(addButton);

        JButton viewButton = new JButton("View Session");
        viewButton.addActionListener(this::viewSession);
        inputPanel.add(viewButton);

        JButton updateButton = new JButton("Update Session");
        updateButton.addActionListener(this::updateSession);
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Session");
        deleteButton.addActionListener(this::deleteSession);
        inputPanel.add(deleteButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void viewSession(ActionEvent event) {
        try {
            int sessionId = Integer.parseInt(sessionIdField.getText());
            Session session = sessionDAO.getSession(sessionId);

            if (session != null) {
                resultArea.setText("Session Details:\n" +
                    "Session ID: " + session.getSessionId() + "\n" +
                    "Student ID: " + session.getStudentID() + "\n" +
                    "Plate Number: " + session.getPlateNumber() + "\n" +
                    "Trainer ID: " + session.getTrainerID() + "\n" +
                    "Session Cost: " + session.getSessionCost() + "\n" +
                    "Session Time: " + session.getSessionTime() + "\n" +
                    "Session Date: " + session.getSessionDate() + "\n" +
                    "Session Status: " + session.getSessionStatus());
            } else {
                resultArea.setText("Session not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Session ID.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error maybe student isnt exist or : " + e.getMessage());
        }
    }
    private void updateSession(ActionEvent event) {
        try {
            int sessionId = Integer.parseInt(sessionIdField.getText());
            int studentId = Integer.parseInt(studentIdField.getText());
            int plateNumber = Integer.parseInt(plateNumberField.getText());
            int trainerId = Integer.parseInt(trainerIdField.getText());
            int sessionCost = Integer.parseInt(sessionCostField.getText());
            String sessionTime = sessionTimeField.getText();
            String sessionDate = sessionDateField.getText();
            String sessionStatus = sessionStatusField.getText();

            Session session = new Session(sessionId, studentId, plateNumber, trainerId, sessionCost, sessionTime, sessionDate, sessionStatus);
            sessionDAO.updateSession(session);

            resultArea.setText("Session updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }
    private void deleteSession(ActionEvent event) {
        try {
            int sessionId = Integer.parseInt(sessionIdField.getText());
            sessionDAO.deleteSession(sessionId);
            resultArea.setText("Session deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Session ID.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }


    // Example implementation for addSession
    private void addSession(ActionEvent event) {
        try {
            int sessionId = Integer.parseInt(sessionIdField.getText());
            int studentId = Integer.parseInt(studentIdField.getText());
            int plateNumber = Integer.parseInt(plateNumberField.getText());
            int trainerId = Integer.parseInt(trainerIdField.getText());
            int sessionCost = Integer.parseInt(sessionCostField.getText());
            String sessionTime = sessionTimeField.getText();
            String sessionDate = sessionDateField.getText();
            String sessionStatus = sessionStatusField.getText();

           

            Session session = new Session(sessionId, studentId, plateNumber, trainerId, sessionCost, sessionTime, sessionDate, sessionStatus);
            sessionDAO.addSession(session);
            sessionDAO.addStudentSession(studentId, trainerId);

            resultArea.setText(" added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }


    // You need to implement viewSession, updateSession, deleteSession similarly

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SessionGUI::new);
    }
}
