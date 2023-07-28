package org.carrental.service;

import org.carrental.dao.BookingDAO;
import org.carrental.domain.Booking;
import org.carrental.domain.Customer;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingService
{
    BookingDAO dao = new BookingDAO();

    public String[][] transformToJTable(List<Booking> bookingList, int columnSize)
    {
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size(); i++)
        {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomerId());
            data[i][2] = String.valueOf(bookingList.get(i).getVehicleId());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = String.valueOf(bookingList.get(i).getStatus());
        }
        return data;
    }

    public String[][] transformToJTableWithCustomerNameAndVehicleName(List<Booking> bookingList, int columnSize)
    {
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size(); i++)
        {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
//            data[i][1] = String.valueOf(bookingList.get(i).getCustomerId());
//            data[i][2] = String.valueOf(bookingList.get(i).getVehicleId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomerName());
            data[i][2] = String.valueOf(bookingList.get(i).getVehicleName());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = String.valueOf(bookingList.get(i).getStatus());
        }
        return data;
    }

    public String[][] getAllBookingForJTable()
    {
        List<Booking> bookingList = dao.getAll();
        return transformToJTable(bookingList,7);
    }

    public String[][] getAllBookingForJTableWithCustomerNameAndVehicleName()
    {
        List<Booking> bookingList = dao.getAllBookingsWithCustomerNameAndVehicleName();
        return transformToJTableWithCustomerNameAndVehicleName(bookingList,7);
    }

    public Booking getById(Long id){
        return dao.getById(id);
    }

    public void update(Booking obj, Integer id){
        dao.update(obj, Long.valueOf(id));
    }

    public void deleteById(Long id){
        dao.deleteById(id);
    }

    public String[][] searchByVehicleId(Long id){
        List<Booking> bookingList = dao.getByVehicleId(id);
        return transformToJTable(bookingList,7);
    }

    public void save(String customerID, String vehicleId, String bookingDate, String completeDate,String price, String Status)
    {
        Booking booking = Booking.builder()
                .customerId(Integer.valueOf(customerID))
                .vehicleId(Integer.valueOf(vehicleId))
                .bookingDate(Date.valueOf(bookingDate))
                .completeDate(Date.valueOf(completeDate))
                .price(Double.valueOf(price))
                .status(Status)
                .build();

        dao.insert(booking);
    }

    public List<Long> getBookedVehiclesId(){
        return dao.getBookedVehicles();
    }



    public String[][] searchByName(String name){
        List<Booking> bookingList = dao.getByCustomerName(name);
        return transformToJTableWithCustomerNameAndVehicleName(bookingList,7);
    }

    public long saveAndGetBookingId(Booking obj) {
        dao.insert(obj);
        return dao.getLastInsertedBookingId();
    }

    public void updateBookingStatus(long bookingId, String newStatus) {
        Booking booking = dao.getById(bookingId);
        if (booking != null) {
            booking.setStatus(newStatus);
            dao.update(booking, bookingId);
        } else {
            throw new RuntimeException("Booking not found.");
        }
    }
}
