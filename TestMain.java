import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

    private JFrame frame;
    private JTextField tidField, studentIdField, tresultField, pdateField;
    private JTextArea resultArea;
    private TestDAO testDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TestMain() {
        try {
            Connection connection = DBConnection.getConnection();
            testDAO = new TestDAO(connection);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to establish database connection: " + e.getMessage());
            return;
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Test Management System");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));

        inputPanel.add(new JLabel("Test ID:"));
        tidField = new JTextField(10);
        inputPanel.add(tidField);

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Test Result:"));
        tresultField = new JTextField(10);
        inputPanel.add(tresultField);

        inputPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        pdateField = new JTextField(10);
        inputPanel.add(pdateField);

        JButton addButton = new JButton("Add Test");
        addButton.addActionListener(this::addTest);
        inputPanel.add(addButton);

        JButton viewButton = new JButton("View Test");
        viewButton.addActionListener(this::viewTest);
        inputPanel.add(viewButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addTest(ActionEvent event) {
        try {
            int tid = Integer.parseInt(tidField.getText());
            int studentId = Integer.parseInt(studentIdField.getText());
            if (!testDAO.studentExists(studentId)) {
                JOptionPane.showMessageDialog(frame, "Student ID does not exist.");
                return;
            }
            String tresult = tresultField.getText();
            Date date = dateFormat.parse(pdateField.getText());

            Test test = new Test(tid, tresult, date, studentId);
            testDAO.addTest(test);

            resultArea.setText("Test added successfully.");
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }

    private void viewTest(ActionEvent event) {
        try {
            int tid = Integer.parseInt(tidField.getText());
            Test test = testDAO.getTest(tid);

            if (test != null && testDAO.studentExists(test.getStudentId())) {
                resultArea.setText("Test Details:\n" +
                    "Test ID: " + test.getTid() + "\n" +
                    "Student ID: " + test.getStudentId() + "\n" +
                    "Test Result: " + test.getTresult() + "\n" +
                    "Date: " + dateFormat.format(test.getPdate()));
            } else {
                resultArea.setText("Test not found or Student ID does not exist.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Test ID.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestMain::new);
    }
}
