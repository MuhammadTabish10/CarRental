package org.carrental.dao;

import org.carrental.domain.Booking;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.BookingMapper;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO extends BaseDAO
{
    private final BookingMapper bookingMapper = new BookingMapper();
    private final VehicleOwnerMapper vehicleOwnerMapper = new VehicleOwnerMapper();
    public List<Booking> getAllBookingsWithStartDateAndEndDate(Date startDate, Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_ALL_BOOKING_FROM_START_DATE_AND_END_DATE);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToListWithCustomerNameAndVehicleNameAndCommission(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all bookings with start and end date.", e);
        }
    }

    public List<VehicleOwner> getAllVehicleOwnersWithTotalCommission(Date startDate, Date endDate) {
        List<VehicleOwner> vehicleOwners = new ArrayList<>();
        VehicleOwner vehicleOwner = new VehicleOwner();
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.VEHICLE_OWNER_COMMISSIONS);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicleOwner.setId(rs.getLong(1));
                vehicleOwner.setOwnerName(rs.getString(2));
                vehicleOwner.setCommission(rs.getDouble(3));
                vehicleOwners.add(vehicleOwner);
                return vehicleOwners;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all Vehicle owners with commission.", e);
        }
    }

    public Double getTotalCommission(Date startDate, Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.TOTAL_COMMISSION);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return null;
        }
        catch (SQLException e){
            throw new RuntimeException("Error getting all commission from start and end date.", e);
        }
    }

    public Double getTotalPrice(Date startDate, Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.TOTAL_PRICE);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble(1);
            }
            return null;
        }
        catch (SQLException e){
            throw new RuntimeException("Error getting all price from start and end date.", e);
        }
    }

    public Double getTotalProfit(Date startDate, Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.TOTAL_PROFIT);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getDouble(1);
            }
            return null;
        }
        catch (SQLException e){
            throw new RuntimeException("Error getting all profit from start and end date.", e);
        }
    }
}
