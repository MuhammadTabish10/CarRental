package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class ReportUI extends BaseUI
{
    public ReportUI()
    {
        JFrame frame = new JFrame("Car Rental Reports");
        frame.setLayout(new GridLayout(2, 3));
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        JButton monthlyRepBtn = createStyledButton("Monthly Report");
        JButton commissionRepBtn = createStyledButton("Commission Report");
        JButton carAvailabilityRepBtn = createStyledButton("Car Availability Report");
        JButton backBtn = createStyledButton("Back");

        frame.add(monthlyRepBtn);
        frame.add(commissionRepBtn);
        frame.add(carAvailabilityRepBtn);
        frame.add(backBtn);

        // Add action listeners
        monthlyRepBtn.addActionListener(event -> {
            frame.dispose();
            new MonthlyReportUI();
        });

        commissionRepBtn.addActionListener(event -> {
            frame.dispose();
            new CommissionReport();
        });

        carAvailabilityRepBtn.addActionListener(event -> {
            frame.dispose();
            new CarAvailabilityReport();
        });

        backBtn.addActionListener(event -> {
            frame.dispose();
            new HomeUI();
        });

        frame.setSize(1100, 650);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
