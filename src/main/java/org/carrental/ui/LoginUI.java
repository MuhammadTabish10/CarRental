package org.carrental.ui;

import org.carrental.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI
{
    private final AuthenticationService authenticationService = new AuthenticationService();
    public LoginUI()
    {
        // defining frame
        JFrame frame = new JFrame("Car Rental Login");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        // defining text fields
        JTextField userTF = new JTextField(20);
        JPasswordField passTF = new JPasswordField(20);

        // creating button
        JButton loginBtn = new JButton("login");
        loginBtn.setBackground(new Color(46, 204, 113));
        loginBtn.setForeground(Color.WHITE);

        // creating labels
        Label userLb = new Label("Username");
        userLb.setForeground(Color.WHITE);

        Label passLb = new Label("Password");
        passLb.setForeground(Color.WHITE);

        // creating panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // columns
        gbc.gridy = 0; // rows
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(userLb, gbc);
        gbc.gridx = 1;
        panel.add(userTF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLb, gbc);
        gbc.gridx = 1;
        panel.add(passTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginBtn, gbc);

        // Add the panel to the frame
        frame.add(panel);

        //Check Login
        loginBtn.addActionListener(event -> {
            String username = userTF.getText();
            String password = new String(passTF.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
            }
            else if (authenticationService.checkLogin(username, password)) {
                frame.dispose();
                new HomeUI();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Incorrect Credentials. Please try again.");
            }
        });


        // basic properties
        //frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
