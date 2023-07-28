package org.carrental.mapper;

import org.carrental.domain.Booking;
import org.carrental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking>
{
    private final static String ID = "b_id";
    private final static String CUSTOMER_ID = "c_id";
    private final static String VEHICLE_ID = "v_id";
    private final static String BOOKING_DATE = "b_date";
    private final static String COMPLETE_DATE = "complete_date";
    private final static String PRICE = "price";
    private final static String STATUS = "status";
    private final static String CUSTOMER_NAME = "c_name";
    private final static String VEHICLE_NAME = "v_name";
    private final static String COMMISSION = "commission";

    @Override
    public List<Booking> resultSetToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()   //object banaya
                    .id(rs.getInt(ID))
                    .customerId(rs.getInt(CUSTOMER_ID))
                    .vehicleId(rs.getInt(VEHICLE_ID))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    @Override
    public Booking resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Booking.builder()   //object banaya
                    .id(rs.getInt(ID))
                    .customerId(rs.getInt(CUSTOMER_ID))
                    .vehicleId(rs.getInt(VEHICLE_ID))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .build();
        }
        return null;
    }

    public List<Booking> resultSetToListWithCustomerNameAndVehicleName(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()   //object banaya
                    .id(rs.getInt(ID))
//                    .customerId(rs.getInt(CUSTOMER_ID))
//                    .vehicleId(rs.getInt(VEHICLE_ID))
                    .customerName(rs.getString(CUSTOMER_NAME))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public List<Booking> resultSetToListWithCustomerNameAndVehicleNameAndCommission(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()   //object banaya
                    .id(rs.getInt(ID))
                    .customerName(rs.getString(CUSTOMER_NAME))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .commission(rs.getDouble(COMMISSION))
                    .status(rs.getString(STATUS))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public Booking resultSetToObjectWithCustomerNameAndVehicleName(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Booking.builder()   //object banaya
                    .id(rs.getInt(ID))
                    //.customerId(rs.getInt(CUSTOMER_ID))
                    //.vehicleId(rs.getInt(VEHICLE_ID))
                    .customerName(rs.getString(CUSTOMER_NAME))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .build();
        }
        return null;
    }


}
