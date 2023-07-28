package org.carrental.ui;

import org.carrental.domain.VehicleOwner;
import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class AddVehicleUI extends BaseUI
{
    private final VehicleService vehicleService = new VehicleService();
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    private JFrame frame;
    private JTextField vehicleNameTF;
    private JTextField modelTF;
    private JTextField brandTF;
    private JTextField colorTF;
    private JButton addBtn;
    private JButton backBtn;
    private JComboBox<String> vehicleOwnerDropdown;

    public AddVehicleUI(){
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Add Vehicle");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        JLabel vehicleNameLB = createStyledLabel("Name");
        JLabel modelLB = createStyledLabel("Model");
        JLabel brandLB = createStyledLabel("Brand");
        JLabel colorLB = createStyledLabel("Color");
        JLabel vehicleOwnerIdLB = createStyledLabel("Vehicle Owner Id");

        vehicleNameTF = new JTextField(20);
        modelTF = new JTextField(20);
        brandTF = new JTextField(20);
        colorTF = new JTextField(20);

        addBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

        vehicleOwnerDropdown = new JComboBox<>();
        Vector<String> vehicleOwners = new Vector<>();
        List<VehicleOwner> vehicleOwnerList = vehicleOwnerService.getAllVehicleOwners();
        for (VehicleOwner vehicleOwner : vehicleOwnerList) {
            vehicleOwners.add(vehicleOwner.getId() + ": " + vehicleOwner.getOwnerName());
        }
        vehicleOwnerDropdown.setModel(new DefaultComboBoxModel<>(vehicleOwners));

        // creating panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // columns
        gbc.gridy = 0; // rows
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(vehicleNameLB, gbc); // 0,0
        gbc.gridx = 1;
        panel.add(vehicleNameTF, gbc); // 0,1
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(modelLB, gbc); // 1,0
        gbc.gridx = 1;
        panel.add(modelTF, gbc); // 1,1
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(brandLB, gbc); // 2,0
        gbc.gridx = 1;
        panel.add(brandTF, gbc); // 2,1
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(colorLB, gbc); // 3,0
        gbc.gridx = 1;
        panel.add(colorTF, gbc); // 3,1
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(vehicleOwnerIdLB, gbc); // 4,0
        gbc.gridx = 1;
        panel.add(vehicleOwnerDropdown, gbc); // 4,1
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(backBtn, gbc); // 5,0
        gbc.gridx = 1;
        panel.add(addBtn, gbc); // 5,0

        // Add the panel to the frame
        frame.add(panel);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addListeners(){
        backBtn.addActionListener(event -> {
            frame.dispose();
            new VehicleOwnerUI();
        });

        addBtn.addActionListener(event -> {

            String name = vehicleNameTF.getText();
            String model = modelTF.getText();
            String brand = brandTF.getText();
            String color = colorTF.getText();

            String selectedVehicleOwner = (String) vehicleOwnerDropdown.getSelectedItem();

            if (selectedVehicleOwner != null)
            {
                String selectedVehicleOwnerId = selectedVehicleOwner.split(": ")[0];

                try{
                    if(validateInput()){
                        vehicleService.Save(name,model,brand,color,selectedVehicleOwnerId);
                        JOptionPane.showMessageDialog(frame, "Vehicle Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new VehicleUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"VehicleName, model, brand, color cannot be empty");
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(frame,"Error Adding a Vehicle");
                }
            }
        });
    }

    private boolean validateInput() {
        return !vehicleNameTF.getText().isEmpty() &&
                !modelTF.getText().isEmpty() &&
                !brandTF.getText().isEmpty() &&
                !colorTF.getText().isEmpty();
    }
}
