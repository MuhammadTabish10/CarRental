package org.carrental.ui;

import org.carrental.domain.Customer;
import org.carrental.service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomerUI extends BaseUI {

    private final CustomerService customerService = new CustomerService();
    private final String[] columnNames = { "Id", "Name", "Phone", "Cnic", "Address", "Reference Phone"};
    private String[][] data;
    private String[] selectedRecord;

    private JFrame frame;
    private DefaultTableModel dtm;
    private JTable table;
    private JButton insertBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton backBtn;
    private TextField searchTF;

    public CustomerUI() {
        initializeUI();
        loadTableData();
        addListeners();
    }

    private void initializeUI() {
        frame = new JFrame("Customer Dashboard");
        frame.getContentPane().setBackground(new Color(52, 73, 94));
        frame.setLayout(new BorderLayout());

        // Table
        dtm = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable.
            }
        };
        table = new JTable(dtm);
        table.setEnabled(true);
        JScrollPane scrollTablePane = new JScrollPane(table);
        JViewport viewport = scrollTablePane.getViewport();
        viewport.setBackground(new Color(52, 73, 94));

        // Buttons
        insertBtn = createStyledButton("Insert");
        updateBtn = createStyledButton("Update");
        deleteBtn = createStyledButton("Delete");
        backBtn = createStyledButton("Back");

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonsPanel.setBackground(new Color(52, 73, 94));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.add(insertBtn);
        buttonsPanel.add(updateBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(backBtn);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(52, 73, 94));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        searchTF = new TextField(20);
        JButton searchBtn = createStyledButton("Search");
        searchPanel.add(searchTF);
        searchPanel.add(searchBtn);

        // Add panels to the frame
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollTablePane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void loadTableData() {
        data = customerService.getAllCustomerForJTable();
        dtm.setDataVector(data, columnNames);
    }

    private void addListeners() {
        searchTF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] searchData = customerService.searchByName(searchTF.getText());
                dtm.setDataVector(searchData, columnNames);
            }
        });

        insertBtn.addActionListener(event -> {
            frame.dispose();
            new AddCustomerUI();
        });

        updateBtn.addActionListener(event -> {
            if (selectedRecord != null) {
                // Get the ID from the first column of the selected record
                long customerId = Long.parseLong(selectedRecord[0]);
                // Retrieve the customer from the database
                Customer customer = customerService.getById(customerId);
                // Open the update form with the selected customer's data
                frame.dispose();
                new EditCustomerUI(customer);
            } else {
                JOptionPane.showMessageDialog(table, "Please select a record to update.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backBtn.addActionListener(event -> {
            frame.dispose();
            new HomeUI();
        });

        deleteBtn.setEnabled(false);
        updateBtn.setEnabled(false);

        // Add ListSelectionListener to the table to handle record selection
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                // Enable the delete button if a row is selected
                deleteBtn.setEnabled(true);
                updateBtn.setEnabled(true);
                // Save the selected record in the variable
                selectedRecord = new String[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    selectedRecord[i] = (String) table.getValueAt(selectedRow, i);
                }
            } else {
                // Disable the delete button if no row is selected
                deleteBtn.setEnabled(false);
                updateBtn.setEnabled(false);
                // Clear the selected record variable when no row is selected
                selectedRecord = null;
            }
        });

        deleteBtn.addActionListener(event -> {
            if (selectedRecord != null) {
                // Confirm with the user before deletion
                int choice = JOptionPane.showConfirmDialog(table, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    if(customerService.hasBooking(Long.parseLong(selectedRecord[0]))){
                        JOptionPane.showMessageDialog(frame,"This Customer cannot be deleted because he has bookings");
                    }
                    else{
                        // Perform the deletion operation in the database through CustomerService
                        customerService.deleteById(Long.valueOf(selectedRecord[0])); // Assuming the first column contains the ID
                        // Remove the row from the table's DefaultTableModel
                        dtm.removeRow(table.getSelectedRow());
                        // Reset the selectedRecord variable and disable the delete button
                        JOptionPane.showMessageDialog(frame, "Customer deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                        selectedRecord = null;
                        deleteBtn.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    public JButton createStyledButton(String name) {
        JButton button = super.createStyledButton(name);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        return button;
    }
}
