import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentGUI {

    private JFrame frame;
    private JTextField pidField, studentIdField, amountField, dateField;
    private JTextArea resultArea;
    private PaymentDAO paymentDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PaymentGUI() {
        try {
            Connection connection = DBConnection.getConnection();
            paymentDAO = new PaymentDAO(connection);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to establish database connection: " + e.getMessage());
            return;
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Payment Management System");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));

        inputPanel.add(new JLabel("Payment ID:"));
        pidField = new JTextField(10);
        inputPanel.add(pidField);

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        dateField = new JTextField(10);
        inputPanel.add(dateField);

        JButton addButton = new JButton("Add Payment");
        addButton.addActionListener(this::addPayment);
        inputPanel.add(addButton);

        JButton viewButton = new JButton("View Payment");
        viewButton.addActionListener(this::viewPayment);
        inputPanel.add(viewButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addPayment(ActionEvent event) {
        try {
            int pid = Integer.parseInt(pidField.getText());
            int studentId = Integer.parseInt(studentIdField.getText());
            
            // Check if the student exists
            if (!paymentDAO.studentExists(studentId)) {
                JOptionPane.showMessageDialog(frame, "Student ID does not exist.");
                return;
            }

            int amount = Integer.parseInt(amountField.getText());
            Date date = dateFormat.parse(dateField.getText());

            Payment payment = new Payment(pid, studentId, amount, date);
            paymentDAO.addPayment(payment);

            resultArea.setText("Payment added successfully.");
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid data: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }


    private void viewPayment(ActionEvent event) {
        try {
            int pid = Integer.parseInt(pidField.getText());
            Payment payment = paymentDAO.getPayment(pid);

            if (payment != null) {
                resultArea.setText("Payment Details:\n" +
                    "Payment ID: " + payment.getPid() + "\n" +
                    "Student ID: " + payment.getStudentId() + "\n" +
                    "Amount: " + payment.getAmount() + "\n" +
                    "Date: " + dateFormat.format(payment.getPdate()));
            } else {
                resultArea.setText("Payment not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Payment ID.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaymentGUI::new);
    }
}
