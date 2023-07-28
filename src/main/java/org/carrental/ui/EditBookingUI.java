package org.carrental.ui;

import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;
import org.carrental.service.BookingService;
import org.carrental.service.CustomerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class EditBookingUI extends BaseUI
{
    private final BookingService bookingService = new BookingService();
    private final Booking booking;

    private JFrame frame;
    private JTextField priceTF;
    private JButton bookBtn;
    private JButton backBtn;
    private String startFormattedDate;
    private String endFormattedDate;
    private JComboBox<String> vehicleDropdown;
    private JComboBox<String> customerDropdown;
    private JComboBox<String> statusDropdown;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;

    private final VehicleService vehicleService = new VehicleService();
    private final CustomerService customerService = new CustomerService();
    public EditBookingUI(Booking booking) {
        this.booking = booking;
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Edit Booking");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        JLabel selectVehicleLB = createStyledLabel("Select Vehicle");
        JLabel selectCustomerLB = createStyledLabel("Select Customer");
        JLabel priceLB = createStyledLabel("Select Price");
        JLabel startDateLB = createStyledLabel("Select Booking Date");
        JLabel endDateLB = createStyledLabel("Select Complete Date");
        JLabel statusLB = createStyledLabel("Status");

        priceTF = new JTextField(20);

        bookBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

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

        // Get the values from the 'booking' object
        String selectedVehicleId = String.valueOf(booking.getVehicleId());
        String selectedCustomerId = String.valueOf(booking.getCustomerId());
        startFormattedDate = new SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate());
        endFormattedDate = new SimpleDateFormat("yyyy-MM-dd").format(booking.getCompleteDate());
        String price = String.valueOf(booking.getPrice());

        // Create the JComboBox for customers
        customerDropdown = new JComboBox<>();
        Vector<String> customerItems = new Vector<>();
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            customerItems.add(customer.getId() + ": " + customer.getName());
        }
        customerDropdown.setModel(new DefaultComboBoxModel<>(customerItems));

        statusDropdown = new JComboBox<>();
        statusDropdown.addItem("Open");
        statusDropdown.addItem("Complete");

        // Set the values in the corresponding UI components
        vehicleDropdown.setSelectedItem(selectedVehicleId + ": " + getVehicleName(booking.getVehicleId()));
        customerDropdown.setSelectedItem(selectedCustomerId + ": " + getCustomerName(String.valueOf(booking.getCustomerId())));
        statusDropdown.setSelectedItem(booking.getStatus());
        priceTF.setText(price);

        // Create the date picker using JSpinner
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yyyy");
        startDateSpinner.setEditor(dateEditor);

        // Create the date picker using JSpinner
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yyyy");
        endDateSpinner.setEditor(dateEditor1);

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startFormattedDate);
            startDateSpinner.setValue(date); // Set the default date to the booking date
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endFormattedDate);
            endDateSpinner.setValue(date1); // Set the default date to the end date
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        panel.add(statusLB, gbc); // 5,0
        gbc.gridx = 1;
        panel.add(statusDropdown, gbc); // 5,1
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(backBtn, gbc); // 6,0
        gbc.gridx = 1;
        panel.add(bookBtn, gbc); // 6,1

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

            Date startSelectedDate = (Date) startDateSpinner.getValue();
            Date endSelectedDate = (Date) endDateSpinner.getValue();

            String selectedVehicleItem = (String) vehicleDropdown.getSelectedItem();
            String selectedCustomerItem = (String) customerDropdown.getSelectedItem();
            String selectedStatusItem = (String) statusDropdown.getSelectedItem();

            if (selectedVehicleItem != null && selectedCustomerItem != null) {
                // Extract ID's from selectedVehicleItem and selectedCustomerItem
                Integer selectedVehicleId = Integer.valueOf(selectedVehicleItem.split(": ")[0]);
                Integer selectedCustomerId = Integer.valueOf(selectedCustomerItem.split(": ")[0]);

                try {
                    if(booking.getPrice() != null){
                        booking.setVehicleId(selectedVehicleId);
                        booking.setCustomerId(selectedCustomerId);
                        booking.setBookingDate(startSelectedDate);
                        booking.setCompleteDate(endSelectedDate);
                        booking.setPrice(Double.valueOf(priceTF.getText()));
                        booking.setStatus(selectedStatusItem);
                        bookingService.update(booking, booking.getId());
                        JOptionPane.showMessageDialog(frame, "Booking updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new BookingUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "price cannot be empty");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error updating Booking");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please select a vehicle and a customer.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private String getVehicleName(int vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return vehicle.getVehicleName();
    }

    private String getCustomerName(String customerName) {
        Customer customer = customerService.getCustomerById(Integer.parseInt(customerName));
        return customer.getName();
    }

}
