package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUI extends BaseUI{
    public HomeUI() {
        JFrame frame = new JFrame("Car Rental Home");
        frame.setLayout(new GridLayout(3, 3));
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        // Create buttons
        JButton customer = createStyledButton("Customer");
        JButton vehicleOwner = createStyledButton("Vehicle Owner");
        JButton vehicle = createStyledButton("Vehicle");
        JButton booking = createStyledButton("Booking");
        JButton user = createStyledButton("User");
        JButton logout = createStyledButton("Logout");
        JButton reports = createStyledButton("Reports");

        // Add buttons to the frame
        frame.add(customer);
        frame.add(vehicleOwner);
        frame.add(vehicle);
        frame.add(booking);
        frame.add(user);
        frame.add(logout);
        frame.add(reports);

        // Add action listeners
        customer.addActionListener(event -> {
            frame.dispose();
            new CustomerUI();
        });

        reports.addActionListener(event -> {
            frame.dispose();
            new ReportUI();
        });

        logout.addActionListener(event -> {
            frame.dispose();
            new LoginUI();
        });

        vehicleOwner.addActionListener(event -> {
            frame.dispose();
            new VehicleOwnerUI();
        });

        vehicle.addActionListener(event -> {
            frame.dispose();
            new VehicleUI();
        });

        booking.addActionListener(event -> {
            frame.dispose();
            new BookingUI();
        });

        frame.setSize(1100, 650);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


