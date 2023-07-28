package org.carrental.ui;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AddCustomerUI extends BaseUI
{
    private final CustomerService customerService = new CustomerService();

    private JFrame frame;
    private JTextField customerNameTF;
    private JTextField phoneTF;
    private JTextField cnicTF;
    private JTextField addressTF;
    private JTextField refPhoneTF;
    private JButton addBtn;
    private JButton backBtn;

    public AddCustomerUI(){
        initializeUI();
        addListeners();
    }

    private void initializeUI(){
        frame = new JFrame("Add Customer");
        frame.getContentPane().setBackground(new Color(52, 73, 94));

        JLabel customerNameLB = createStyledLabel("Name");
        JLabel phoneLB = createStyledLabel("Phone");
        JLabel cnicLB = createStyledLabel("Cnic");
        JLabel addressLB = createStyledLabel("Address");
        JLabel refPhoneLB = createStyledLabel("Reference Phone");

        customerNameTF = new JTextField(20);
        phoneTF = new JTextField(20);
        cnicTF = new JTextField(20);
        addressTF = new JTextField(20);
        refPhoneTF = new JTextField(20);

        addBtn = createStyledButton("Save");
        backBtn = createStyledButton("Back");

        // creating panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // columns
        gbc.gridy = 0; // rows
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
            new CustomerUI();
        });

        addBtn.addActionListener(event -> {
            try{
                String name = customerNameTF.getText();
                String phone = phoneTF.getText();
                String cnic = cnicTF.getText();
                String address = addressTF.getText();
                String refPhone = refPhoneTF.getText();

                if(validateInput()){
                    if(phone.matches("^\\d+$") && cnic.matches("^\\d+$") && (refPhone.matches("^\\d+$") || refPhone.isEmpty())){
                        if(refPhoneTF.getText().isEmpty()){
                            refPhone = "N/A";
                        }
                        customerService.Save(name,phone,cnic,address,refPhone);
                        JOptionPane.showMessageDialog(frame, "Customer Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new CustomerUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"Please enter only numeric values in phone, cnic and Ref-phone");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(frame,"Name, cnic, address and phone cannot be empty");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(frame,"Error Adding a Customer");
            }
        });
    }

    private boolean validateInput() {
        return !customerNameTF.getText().isEmpty() &&
                !phoneTF.getText().isEmpty() &&
                !cnicTF.getText().isEmpty() &&
                !addressTF.getText().isEmpty();
    }
}
