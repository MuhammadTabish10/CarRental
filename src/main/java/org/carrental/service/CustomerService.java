package org.carrental.service;

import org.carrental.dao.CustomerDAO;
import org.carrental.domain.Customer;

import java.util.List;

public class CustomerService
{
    CustomerDAO dao = new CustomerDAO();

    public String[][] transformToJTable(List<Customer> customerList, int columnSize)
    {
        String[][] data = new String[customerList.size()][columnSize];

        for (int i = 0; i < customerList.size(); i++)
        {
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getPhoneNumber();
            data[i][3] = customerList.get(i).getCnic();
            data[i][4] = customerList.get(i).getAddress();
            data[i][5] = customerList.get(i).getReferencePhoneNumber();
        }
        return data;
    }

    public String[][] getAllCustomerForJTable()
    {
        List<Customer> customerList = dao.getAll();
        return transformToJTable(customerList,6);
    }

    public Customer getById(Long id){
      return dao.getById(id);
    }

    public void update(Customer obj, Long id){
        dao.update(obj, id);
    }

    public void deleteById(Long id){
        dao.deleteById(id);
    }

    public String[][] searchByName(String name){
        List<Customer> customerList = dao.getByName(name);
        return transformToJTable(customerList,6);
    }

    public void Save(String customerName, String phone, String cnic, String address, String refPhone)
    {
        Customer customer = Customer.builder()
                .name(customerName)
                .phoneNumber(phone)
                .cnic(cnic)
                .address(address)
                .referencePhoneNumber(refPhone)
                .build();

        dao.insert(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = dao.getAll();
        return customerList;
    }

    public Customer getCustomerById(int customerId) {
        return dao.getById((long) customerId);
    }

    public boolean hasBooking(long id){
        Customer customer = dao.getCustomerWhoHasBooking(id);  //check if the customer has any bookings
        return customer != null;                               // If the customer is not null, it means the customer has bookings
    }

}
