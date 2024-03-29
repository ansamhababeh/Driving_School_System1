import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Main2 {
	
    public static void main(String[] args) {
        // Create and set up the window
        JFrame frame = new JFrame("Trainer Availability");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create and set up the panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents2(panel);

        // Display the window
        frame.setVisible(true);
    	
    }
	

    private static void placeComponents2(JPanel panel) {
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
}
