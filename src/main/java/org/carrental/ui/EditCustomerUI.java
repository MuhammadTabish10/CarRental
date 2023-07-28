package org.carrental.ui;

import org.carrental.domain.Customer;
import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class EditCustomerUI extends BaseUI {
    private final CustomerService customerService = new CustomerService();
    private final Customer customer;
    private JFrame frame;
    private JTextField customerNameTF;
    private JTextField phoneTF;
    private JTextField cnicTF;
    private JTextField addressTF;
    private JTextField refPhoneTF;
    private JButton editBtn;
    private JButton backBtn;

    public EditCustomerUI(Customer customer){
        this.customer = customer;
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Edit Customer");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        // Create labels
        JLabel customerNameLB = createStyledLabel("Name");
        JLabel phoneLB = createStyledLabel("Phone");
        JLabel cnicLB = createStyledLabel("Cnic");
        JLabel addressLB = createStyledLabel("Address");
        JLabel refPhoneLB = createStyledLabel("Reference Number");

        // Create text fields and set data from the customer object
        customerNameTF = new JTextField(20);
        phoneTF = new JTextField(20);
        cnicTF = new JTextField(20);
        addressTF = new JTextField(20);
        refPhoneTF = new JTextField(20);

        customerNameTF.setText(customer.getName());
        phoneTF.setText(customer.getPhoneNumber());
        cnicTF.setText(customer.getCnic());
        addressTF.setText(customer.getAddress());
        refPhoneTF.setText(customer.getReferencePhoneNumber());

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
        panel.add(customerNameTF, gbc); // 0,1
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
        panel.add(refPhoneLB, gbc); // 4,0
        gbc.gridx = 1;
        panel.add(refPhoneTF, gbc); // 4,1
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
            new CustomerUI();
        });

        editBtn.addActionListener(event -> {
            updateCustomer();
            frame.dispose();
            new CustomerUI();
        });
    }

    private void updateCustomer() {
        String name = customerNameTF.getText();
        String phoneNumber = phoneTF.getText();
        String cnic = cnicTF.getText();
        String address = addressTF.getText();
        String referencePhoneNumber = refPhoneTF.getText();

        int confirmResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to update this customer?", "Confirm Update", JOptionPane.YES_NO_OPTION);
        if(validateInput()){
            if(phoneNumber.matches("^\\d+$") && cnic.matches("^\\d+$") && (referencePhoneNumber.matches("^\\d+$") || referencePhoneNumber.isEmpty())){
                if(refPhoneTF.getText().isEmpty()){
                    referencePhoneNumber = "N/A";
                }
                if (confirmResult == JOptionPane.YES_OPTION) {
                    customer.setName(name);
                    customer.setPhoneNumber(phoneNumber);
                    customer.setCnic(cnic);
                    customer.setAddress(address);
                    customer.setReferencePhoneNumber(referencePhoneNumber);

                    customerService.update(customer, customer.getId());
                    JOptionPane.showMessageDialog(frame, "Customer updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(frame,"Please enter only numeric values in phone, cnic and Ref-phone");
            }

        }
        else{
            JOptionPane.showMessageDialog(frame,"Name, cnic, address and phone cannot be empty");
        }
    }

    private boolean validateInput() {
        return !customerNameTF.getText().isEmpty() &&
                !phoneTF.getText().isEmpty() &&
                !cnicTF.getText().isEmpty() &&
                !addressTF.getText().isEmpty();
    }
}
