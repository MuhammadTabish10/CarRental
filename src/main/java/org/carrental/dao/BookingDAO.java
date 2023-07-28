package org.carrental.dao;

import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;
import org.carrental.mapper.BookingMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO implements ICrud<Booking>
{
    private final BookingMapper bookingMapper = new BookingMapper();

    @Override
    public void insert(Booking obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_BOOKING);
            ps.setInt(1,obj.getCustomerId());
            ps.setInt(2,obj.getVehicleId());
            ps.setDate(3, (Date) obj.getBookingDate());
            ps.setDate(4, (Date) obj.getCompleteDate());
            ps.setDouble(5,obj.getPrice());
            ps.setString(6,obj.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Booking.", e);
        }
    }

    @Override
    public List<Booking> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_BOOKINGS);
            return bookingMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all Booking.", e);
        }
    }

    @Override
    public Booking getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_BOOKING_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting Booking by ID.", e);
        }
    }

    @Override
    public void update(Booking obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_BOOKING);
            ps.setInt(1,obj.getCustomerId());
            ps.setInt(2,obj.getVehicleId());
            //Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(obj.getBookingDate().getTime());
            ps.setDate(3, sqlDate);
            java.sql.Date sqlDate1 = new java.sql.Date(obj.getCompleteDate().getTime());
            ps.setDate(4, sqlDate1);
            ps.setDouble(5,obj.getPrice());
            ps.setString(6,obj.getStatus());
            ps.setInt(7,id.intValue());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating Booking.", e);
        }
    }


    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SqlQueryConstant.DELETE_BOOKING_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Booking by ID.", e);
        }
    }

    public List<Booking> getByVehicleId(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_ID);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting booking by ID.", e);
        }
    }

    public List<Booking> getByCustomerName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_BOOKINGS_BY_CUSTOMER_NAME);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToListWithCustomerNameAndVehicleName(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting booking by customer ID.", e);
        }
    }

    public List<Booking> getAllBookingsWithCustomerNameAndVehicleName() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_BOOKINGS_WITH_CUSTOMER_NAME_AND_VEHICLE_NAME);
            return bookingMapper.resultSetToListWithCustomerNameAndVehicleName(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all Booking with customer name and vehicle name.", e);
        }
    }

    public long getLastInsertedBookingId() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_LAST_INSERTED_BOOKING_ID);
            if (rs.next()) {
                return rs.getLong(1);
            }
            return -1; // If no ID is found, return -1 (indicating failure)
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the last inserted booking ID.", e);
        }
    }

    public List<Long> getBookedVehicles() {
        List<Long> bookedVehicleIds = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_BOOKED_VEHICLES);
            while (rs.next()) {
                long bookedVehicleId = rs.getLong(1);
                bookedVehicleIds.add(bookedVehicleId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting booked vehicles", e);
        }
        return bookedVehicleIds;
    }
}
