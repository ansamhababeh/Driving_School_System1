import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class MainStudent {

    private static final String ADD_CARD = "Add Student";
    private static final String DELETE_CARD = "Delete Student";
    private static final String EXIT_CARD = "Exit";
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Manage Students");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.add(createButton(ADD_CARD));
        menuPanel.add(createButton(DELETE_CARD));
        menuPanel.add(createButton(EXIT_CARD));

        // Card panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createAddPanel(), ADD_CARD);
        cardPanel.add(createDeletePanel(), DELETE_CARD);
     
        cardPanel.add(createExitPanel(), EXIT_CARD);

        frame.setLayout(new BorderLayout());
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(e -> switchCard(e.getActionCommand()));
        return button;
    }

    private static void switchCard(String card) {
        cardLayout.show(cardPanel, card);
    }
    private static JPanel createAddPanel() {
        JPanel addPanel = new JPanel(new GridLayout(0, 2));
        
        // Add fields for student information
        addLabelAndTextField("Student ID:", addPanel);
        addLabelAndTextField("First Name:", addPanel);
        addLabelAndTextField("Middle Name:", addPanel);
        addLabelAndTextField("Last Name:", addPanel);
        addLabelAndTextField("City Address:", addPanel);
        addLabelAndTextField("Street Address:", addPanel);
        addLabelAndTextField("Phone 1:", addPanel);
        addLabelAndTextField("Phone 2 (optional):", addPanel);
        addLabelAndTextField("Wallet:", addPanel);
        addLabelAndTextField("Gender:", addPanel);
        addLabelAndTextField("Birthdate (YYYY-MM-DD):", addPanel);

        // Add button to submit form
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent(addPanel));
        addPanel.add(addButton);

        return addPanel;
    }

    private static void addStudent(JPanel panel) {
        try (Connection connection = DBConnection.getConnection()) {
            StudentDAO studentDao = new StudentDAO(connection);

            // Extracting data from text fields
            int studentId = Integer.parseInt(getTextFieldValue(panel, "Student ID:"));
            String firstName = getTextFieldValue(panel, "First Name:");
            String middleName = getTextFieldValue(panel, "Middle Name:");
            String lastName = getTextFieldValue(panel, "Last Name:");
            String cityAddress = getTextFieldValue(panel, "City Address:");
            String streetAddress = getTextFieldValue(panel, "Street Address:");
            int phone1 = Integer.parseInt(getTextFieldValue(panel, "Phone 1:"));
            String phone2Str = getTextFieldValue(panel, "Phone 2 (optional):");
            Integer phone2 = phone2Str.isEmpty() ? null : Integer.parseInt(phone2Str);
            int wallet = Integer.parseInt(getTextFieldValue(panel, "Wallet:"));
            String gender = getTextFieldValue(panel, "Gender:");
            Date birthdate = Date.valueOf(getTextFieldValue(panel, "Birthdate (YYYY-MM-DD):"));

            // Creating a new Student object
            Student newStudent = new Student(
                studentId, firstName, middleName, lastName, cityAddress, streetAddress,
                phone1, phone2, wallet, gender, birthdate
            );

            // Adding student to the database
            studentDao.addStudent(newStudent);

            JOptionPane.showMessageDialog(null, "Student added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding student: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please check your input: " + ex.getMessage());
        }
    }


    private static JPanel createDeletePanel() {
        JPanel deletePanel = new JPanel(new GridLayout(0, 2));

        // Add field for student ID
        addLabelAndTextField("Student ID:", deletePanel);

        // Add button to submit form for deletion
        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> deleteStudent(deletePanel));
        deletePanel.add(deleteButton);

        return deletePanel;
    }

    private static void deleteStudent(JPanel panel) {
        try (Connection connection = DBConnection.getConnection()) {
            StudentDAO studentDao = new StudentDAO(connection);

            // Extracting student ID from text field
            int studentId = Integer.parseInt(getTextFieldValue(panel, "Student ID:"));

            // Checking if student exists before deletion
            if (studentDao.studentExists(studentId)) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?");
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Delete the student
                    studentDao.deleteStudent(studentId);
                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student with this ID does not exist.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting student: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please check your input: " + ex.getMessage());
        }
    }


  
 

    private static int validateAndGetInteger(String value) {
        if (value.isEmpty() || !value.matches("\\d+")) {
            throw new NumberFormatException("Input is not a valid integer: " + value);
        }
        return Integer.parseInt(value);
    }


    private static JPanel createExitPanel() {
        JPanel exitPanel = new JPanel();
        JLabel exitLabel = new JLabel("Are you sure you want to exit?");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        exitPanel.add(exitLabel);
        exitPanel.add(exitButton);
        return exitPanel;
    }

    private static void addLabelAndTextField(String labelText, JPanel panel) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(10);
        textField.setName(labelText);
        panel.add(label);
        panel.add(textField);
    }

    private static String getTextFieldValue(JPanel panel, String fieldName) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField && comp.getName().equals(fieldName)) {
                return ((JTextField) comp).getText();
            }
        }
        return "";
    }
}
