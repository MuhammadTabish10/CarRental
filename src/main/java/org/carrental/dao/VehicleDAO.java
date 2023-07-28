package org.carrental.dao;

import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;

import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VehicleDAO extends BaseDAO implements ICrud<Vehicle>
{
    private final VehicleMapper vehicleMapper = new VehicleMapper();

    @Override
    public void insert(Vehicle obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_VEHICLE);
            ps.setString(1,obj.getVehicleName());
            ps.setInt(2,obj.getModel());
            ps.setString(3,obj.getBrand());
            ps.setString(4,obj.getColor());
            ps.setLong(5,obj.getVehicleOwnerId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Vehicle.", e);
        }
    }

    @Override
    public List<Vehicle> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLES);
            return vehicleMapper.resultSetToListWithVehicleOwnerID(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all Vehicle.", e);
        }
    }

    @Override
    public Vehicle getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToObjectWithOwnerID(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting Vehicle by ID.", e);
        }
    }

    @Override
    public void update(Vehicle obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE);
            ps.setString(1,obj.getVehicleName());
            ps.setInt(2,obj.getModel());
            ps.setString(3,obj.getBrand());
            ps.setString(4,obj.getColor());
            ps.setLong(5,obj.getVehicleOwnerId());
            ps.setInt(6,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating Vehicle.", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Vehicle by ID.", e);
        }
    }
    public List<Vehicle> getAllVehiclesAndOwners() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE_BY_ID_AND_OWNER_NAME);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all Vehicle and Owners.", e);
        }
    }

    public List<Vehicle> getByVehicleName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_NAME);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting vehicle by name.", e);
        }
    }

    public Vehicle getVehicleWhoHasBooking(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_WHO_HAS_BOOKING);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToObjectWithOwnerID(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting vehicle who has booking.", e);
        }
    }

}
