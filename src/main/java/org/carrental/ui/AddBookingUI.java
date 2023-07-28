package org.carrental.ui;

import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;
import org.carrental.service.BookingService;
import org.carrental.service.CustomerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AddBookingUI extends BaseUI
{
    private final BookingService bookingService = new BookingService();

    private JFrame frame;
    private JTextField priceTF;
    private JButton bookBtn;
    private JButton backBtn;

    private JComboBox<String> vehicleDropdown;
    private JComboBox<String> customerDropdown;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private final VehicleService vehicleService = new VehicleService();
    private final CustomerService customerService = new CustomerService();
    public AddBookingUI(){
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Add Booking");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        JLabel selectVehicleLB = createStyledLabel("Select Vehicle");
        JLabel selectCustomerLB = createStyledLabel("Select Customer");
        JLabel priceLB = createStyledLabel("Select Price");
        JLabel startDateLB = createStyledLabel("Select Booking Date");
        JLabel endDateLB = createStyledLabel("Select Complete Date");

        // Create the JComboBox for customers
        customerDropdown = new JComboBox<>();
        Vector<String> customerItems = new Vector<>();
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            customerItems.add(customer.getId() + ": " + customer.getName());
        }
        customerDropdown.setModel(new DefaultComboBoxModel<>(customerItems));

        // Create the JComboBox for vehicles
        vehicleDropdown = new JComboBox<>();
        Vector<String> availableVehicles = new Vector<>();
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Long> bookedVehicleIds = bookingService.getBookedVehiclesId();
        for (Vehicle vehicle : vehicles) {
            availableVehicles.add(vehicle.getId() + ": " + vehicle.getVehicleName());
//            // Check if the vehicle is not booked (i.e., its ID is not in the bookedVehicleIds list)
//            if (!bookedVehicleIds.contains(vehicle.getId())) {
//                availableVehicles.add(vehicle.getId() + ": " + vehicle.getVehicleName());
//            }
        }
        vehicleDropdown.setModel(new DefaultComboBoxModel<>(availableVehicles));

        // Create the date picker using JSpinner
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yyyy");
        startDateSpinner.setEditor(dateEditor);
        startDateSpinner.setValue(new Date()); // Set the default date to today's date

        // Create the date picker using JSpinner
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yyyy");
        endDateSpinner.setEditor(dateEditor1);
        endDateSpinner.setValue(new Date()); // Set the default date to today's date

        priceTF = new JTextField(20);

        bookBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

        // creating panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // columns
        gbc.gridy = 0; // rows
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(selectCustomerLB, gbc); // 0,0
        gbc.gridx = 1;
        panel.add(customerDropdown, gbc); // 0,1
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(selectVehicleLB, gbc); // 1,0
        gbc.gridx = 1;
        panel.add(vehicleDropdown, gbc); // 1,1
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(priceLB, gbc); // 2,0
        gbc.gridx = 1;
        panel.add(priceTF, gbc); // 2,1
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(startDateLB, gbc); // 3,0
        gbc.gridx = 1;
        panel.add(startDateSpinner, gbc); // 3,1
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(endDateLB, gbc); // 4,0
        gbc.gridx = 1;
        panel.add(endDateSpinner, gbc); // 4,1
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(backBtn, gbc); // 5,0
        gbc.gridx = 1;
        panel.add(bookBtn, gbc); // 5,1

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
            new BookingUI();
        });

        bookBtn.addActionListener(event -> {

            // Get the selected date from the date picker (JSpinner)
            Date selectedStartDate = (Date) startDateSpinner.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startFormattedDate = sdf.format(selectedStartDate);

            Date selectedEndDate = (Date) endDateSpinner.getValue();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String endFormattedDate = sdf1.format(selectedEndDate);

            String selectedVehicleItem = (String) vehicleDropdown.getSelectedItem();
            String selectedCustomerItem = (String) customerDropdown.getSelectedItem();

            if (selectedVehicleItem != null && selectedCustomerItem != null) {
                // Extract ID's from selectedVehicleItem and selectedCustomerItem
                String selectedVehicleId = selectedVehicleItem.split(": ")[0];
                String selectedCustomerId = selectedCustomerItem.split(": ")[0];


                try
                {
                    String price = priceTF.getText();
                    if(!price.isEmpty()){
                        bookingService.save(selectedCustomerId,selectedVehicleId, startFormattedDate, endFormattedDate, price, "Open");
                        JOptionPane.showMessageDialog(frame, "Booking Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new BookingUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"price cannot be empty");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame,"Error Adding Booking");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please select a vehicle and a customer.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
