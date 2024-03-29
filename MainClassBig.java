import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClassBig extends JFrame {
    
    // Define the interface inside the class
    interface ButtonActions {
        void button1Action();
        void button2Action();
        // ... other button actions
    }

    // Constructor
    public MainClassBig() {
        // Initialize and configure the JFrame
        this.setLayout(new FlowLayout());
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add buttons and their action listeners
        JButton button1 = new JButton("Button 1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1Action();
            }
        });

        JButton button2 = new JButton("Button 2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button2Action();
            }
        });

        // Add buttons to the JFrame
        this.add(button1);
        this.add(button2);

        // Make the frame visible
        this.setVisible(true);
    }

    // Implement the actions for each button
    private void button1Action() {
        System.out.println("Button 1 action performed.");
        // Implement the action for button 1
    }

    private void button2Action() {
        System.out.println("Button 2 action performed.");
        // Implement the action for button 2
    }

    // Main method to run the program
    public static void main(String[] args) {
        new MainClassBig();
    }
}
