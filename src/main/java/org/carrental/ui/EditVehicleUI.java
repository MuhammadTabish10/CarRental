package org.carrental.ui;

import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;
import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class EditVehicleUI extends BaseUI
{
    private final VehicleService vehicleService = new VehicleService();
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    private final Vehicle vehicle;
    private JFrame frame;
    private JTextField vehicleNameTF;
    private JTextField modelTF;
    private JTextField brandTF;
    private JTextField colorTF;
    private JButton editBtn;
    private JButton backBtn;
    private JComboBox<String> vehicleOwnerDropdown;
    private final Long previouslySelectedVehicleOwnerId;

    public EditVehicleUI(Vehicle vehicle){
        this.vehicle = vehicle;
        previouslySelectedVehicleOwnerId = vehicle.getVehicleOwnerId();
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Edit Vehicle");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        // Create labels
        JLabel vehicleNameLB = createStyledLabel("Vehicle Name");
        JLabel modelLB = createStyledLabel("Model");
        JLabel brandLB = createStyledLabel("Brand");
        JLabel colorLB = createStyledLabel("Color");
        JLabel vehicleOwnerNameLB = createStyledLabel("Vehicle Owner Id");

        // Create text fields and set data from the customer object
        vehicleNameTF = new JTextField(20);
        modelTF = new JTextField(20);
        brandTF = new JTextField(20);
        colorTF = new JTextField(20);


        vehicleNameTF.setText(vehicle.getVehicleName());
        modelTF.setText(String.valueOf(vehicle.getModel()));
        brandTF.setText(vehicle.getBrand());
        colorTF.setText(vehicle.getColor());

        // Create buttons
        editBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

        vehicleOwnerDropdown = new JComboBox<>();
        Vector<String> vehicleOwners = new Vector<>();
        List<VehicleOwner> vehicleOwnerList = vehicleOwnerService.getAllVehicleOwners();
        for (VehicleOwner vehicleOwner : vehicleOwnerList) {
            vehicleOwners.add(vehicleOwner.getId() + ": " + vehicleOwner.getOwnerName());
        }
        vehicleOwnerDropdown.setModel(new DefaultComboBoxModel<>(vehicleOwners));

        vehicleOwnerDropdown.setSelectedItem(vehicle.getVehicleName());

        // setting previously selected owner id and name
        if (previouslySelectedVehicleOwnerId != null) {
            String defaultSelection = previouslySelectedVehicleOwnerId + ": ";
            int indexToSelect = -1;
            for (int i = 0; i < vehicleOwners.size(); i++) {
                if (vehicleOwners.get(i).startsWith(defaultSelection)) {
                    indexToSelect = i;
                    break;
                }
            }
            if (indexToSelect != -1) {
                vehicleOwnerDropdown.setSelectedIndex(indexToSelect);
            }
        }

        // Create panel and add components using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
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
        panel.add(vehicleOwnerNameLB, gbc); // 4,0
        gbc.gridx = 1;
        panel.add(vehicleOwnerDropdown, gbc); // 4,1
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
            new VehicleUI();
        });

        editBtn.addActionListener(event -> {
            updateVehicle();
            frame.dispose();
            new VehicleUI();
        });
    }

    private void updateVehicle() {

        String selectedVehicleOwner = (String) vehicleOwnerDropdown.getSelectedItem();

        if (selectedVehicleOwner != null){
            String selectedVehicleOwnerId = selectedVehicleOwner.split(": ")[0];

            try{
                String name = vehicleNameTF.getText();
                String model = modelTF.getText();
                String brand = brandTF.getText();
                String color = colorTF.getText();

                if(validateInput()){
                    int confirmResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to update this vehicle?", "Confirm Update", JOptionPane.YES_NO_OPTION);
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        vehicle.setVehicleName(name);
                        vehicle.setModel(Integer.valueOf(model));
                        vehicle.setBrand(brand);
                        vehicle.setColor(color);
                        vehicle.setVehicleOwnerId(Long.valueOf(selectedVehicleOwnerId));

                        vehicleService.update(vehicle, vehicle.getId());
                        JOptionPane.showMessageDialog(frame, "Vehicle updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"VehicleName, model, brand, color cannot be empty");
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(frame,"Error updating a Vehicle");
            }

        }
    }

    private boolean validateInput() {
        return !vehicleNameTF.getText().isEmpty() &&
                !modelTF.getText().isEmpty() &&
                !brandTF.getText().isEmpty() &&
                !colorTF.getText().isEmpty();
    }
}
