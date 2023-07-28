package org.carrental.ui;

import org.carrental.domain.VehicleOwner;
import org.carrental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class EditVehicleOwnerUI extends BaseUI
{
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    private final VehicleOwner vehicleOwner;
    private JFrame frame;
    private JTextField vehicleOwnerNameTF;
    private JTextField phoneTF;
    private JTextField cnicTF;
    private JTextField addressTF;
    private JTextField commissionTF;
    private JButton editBtn;
    private JButton backBtn;

    public EditVehicleOwnerUI(VehicleOwner vehicleOwner){
        this.vehicleOwner = vehicleOwner;
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Edit Vehicle Owner");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        // Create labels
        JLabel customerNameLB = createStyledLabel("Name");
        JLabel phoneLB = createStyledLabel("Phone");
        JLabel cnicLB = createStyledLabel("Cnic");
        JLabel addressLB = createStyledLabel("Address");
        JLabel commissionLB = createStyledLabel("Commission");

        // Create text fields and set data from the customer object
        vehicleOwnerNameTF = new JTextField(20);
        phoneTF = new JTextField(20);
        cnicTF = new JTextField(20);
        addressTF = new JTextField(20);
        commissionTF = new JTextField(20);

        vehicleOwnerNameTF.setText(vehicleOwner.getOwnerName());
        phoneTF.setText(vehicleOwner.getPhone());
        cnicTF.setText(vehicleOwner.getCnic());
        addressTF.setText(vehicleOwner.getAddress());
        commissionTF.setText(String.valueOf(vehicleOwner.getCommission()));

        // Create buttons
        editBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

        // Create panel and add components using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(customerNameLB, gbc); // 0,0
        gbc.gridx = 1;
        panel.add(vehicleOwnerNameTF, gbc); // 0,1
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(phoneLB, gbc); // 1,0
        gbc.gridx = 1;
        panel.add(phoneTF, gbc); // 1,1
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cnicLB, gbc); // 2,0
        gbc.gridx = 1;
        panel.add(cnicTF, gbc); // 2,1
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(addressLB, gbc); // 3,0
        gbc.gridx = 1;
        panel.add(addressTF, gbc); // 3,1
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(commissionLB, gbc); // 4,0
        gbc.gridx = 1;
        panel.add(commissionTF, gbc); // 4,1
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(backBtn, gbc); // 5,0
        gbc.gridx = 1;
        panel.add(editBtn, gbc); // 5,1

        // Add the panel to the frame
        frame.add(panel);

        // Set frame properties and make it visible
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addListeners(){
        // Add action listeners
        backBtn.addActionListener(event -> {
            frame.dispose();
            new VehicleOwnerUI();
        });

        editBtn.addActionListener(event -> {
            updateVehicleOwner();
            frame.dispose();
            new VehicleOwnerUI();
        });
    }

    private void updateVehicleOwner() {
        String name = vehicleOwnerNameTF.getText();
        String phoneNumber = phoneTF.getText();
        String cnic = cnicTF.getText();
        String address = addressTF.getText();
        String commission = commissionTF.getText();

            int confirmResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to update this Vehicle Owner?", "Confirm Update", JOptionPane.YES_NO_OPTION);
            if(validateInput()){
                if(phoneNumber.matches("^\\d+$") && cnic.matches("^\\d+$") && commission.matches("^\\d+$")){
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        vehicleOwner.setOwnerName(name);
                        vehicleOwner.setPhone(phoneNumber);
                        vehicleOwner.setCnic(cnic);
                        vehicleOwner.setAddress(address);
                        vehicleOwner.setCommission(Double.valueOf(commission));

                        vehicleOwnerService.update(vehicleOwner, vehicleOwner.getId());
                        JOptionPane.showMessageDialog(frame, "Vehicle Owner updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Please enter only numeric values in phone, cnic and commission");
                }
            }
            else{
                JOptionPane.showMessageDialog(frame,"Name, cnic, address, phone and commission cannot be empty");
            }
    }
    private boolean validateInput() {
        return !vehicleOwnerNameTF.getText().isEmpty() &&
                !phoneTF.getText().isEmpty() &&
                !cnicTF.getText().isEmpty() &&
                !addressTF.getText().isEmpty() &&
                !commissionTF.getText().isEmpty();
    }
}