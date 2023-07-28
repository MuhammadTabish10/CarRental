package org.carrental;

import org.carrental.dao.BookingDAO;
import org.carrental.dao.CustomerDAO;
import org.carrental.dao.VehicleOwnerDAO;
import org.carrental.domain.Customer;
import org.carrental.domain.VehicleOwner;
import org.carrental.ui.BookingUI;
import org.carrental.ui.CustomerUI;
import org.carrental.ui.HomeUI;
import org.carrental.ui.LoginUI;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {

//        BookingDAO bookingDAO = new BookingDAO();
//        bookingDAO.getAll().forEach(System.out::println);

//        VehicleOwnerDAO vehicleOwnerDAO = new VehicleOwnerDAO();
//        VehicleOwner vehicleOwner = vehicleOwnerDAO.getById(3L);
//                vehicleOwner.setOwnerName("Humna");
//                vehicleOwner.setPhone("1926319236");
//                vehicleOwner.setCnic("371298371297391");
//                vehicleOwner.setAddress("Fedral b area");
//        vehicleOwnerDAO.update(vehicleOwner,3L);
//
//        vehicleOwnerDAO.getAll().forEach(System.out::println);

        //System.out.println(vehicleOwnerDAO.getById(2L));

//        CustomerDAO customerDAO = new CustomerDAO();

//        Customer newCus = Customer.builder()
//                .name("TabishRashid")
//                .address("Gulistan e johar")
//                .phoneNumber("03312455643")
//                .cnic("39842732347637")
//                .referencePhoneNumber("03353189753")
//                .build();

//        customerDAO.insert(newCus);
//        customerDAO.getAll().forEach(System.out::println);

//        System.out.println(customerDAO.getById(9L));

//        Customer newCus = customerDAO.getById(10L);
//        newCus.setName("TabishRashid");
//        newCus.setAddress("Gulistan e johar");
//        newCus.setPhoneNumber("03312455643");
//        newCus.setCnic("39842732347637");
//        newCus.setReferencePhoneNumber("03353189753");

//        customerDAO.update(newCus,10L);
//        customerDAO.getAll().forEach(System.out::println);

        //customerDAO.deleteById(10L);
//        customerDAO.getAll().forEach(System.out::println);


        // UI
       //new LoginUI();
        new HomeUI();
        //new CustomerUI();
        //new BookingUI();
    }
}