package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JButton submitButton, guestButton;

    public Login() {
        super("Mood-Movie Login");
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username Label
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(0x1F0802));
        usernameLabel.setBounds(50, 80, 100, 20);
        add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 200, 25);
        add(usernameField);

        // Password Label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(0x1F0802));
        passwordLabel.setBounds(50, 120, 100, 20);
        add(passwordLabel);

        // Password Field
        passwordField = new JTextField();
        passwordField.setBounds(150, 120, 200, 25);
        add(passwordField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        submitButton.setForeground(new Color(0xfee2b3));
        submitButton.setBackground(new Color(0x1F0802));
        submitButton.setBounds(100, 170, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);

        // Guest Button
        guestButton = new JButton("Guest");
        guestButton.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        guestButton.setForeground(new Color(0xfee2b3));
        guestButton.setBackground(new Color(0x1F0802));
        guestButton.setBounds(250, 170, 100, 30);
        guestButton.addActionListener(this);
        add(submitButton); // This button was added twice, corrected here.
        add(guestButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("admin") && password.equals("admin")) {
                // Open Project1 class (assuming it's implemented)
                new AdminPanel();
                this.dispose(); // Close the Login window
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password. Please try again.");
            }
        } else if (e.getSource() == guestButton) {
            // Handle guest functionality if needed (e.g., limited access)
            JOptionPane.showMessageDialog(this, "Welcome as Guest! Limited features available.");
            new Project1();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
