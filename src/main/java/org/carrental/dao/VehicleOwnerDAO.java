package org.carrental.dao;

import org.carrental.domain.Customer;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VehicleOwnerDAO extends BaseDAO implements ICrud<VehicleOwner>
{
    private final VehicleOwnerMapper vehicleOwnerMapper = new VehicleOwnerMapper();

    @Override
    public void insert(VehicleOwner obj)
    {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_VEHICLE_OWNER);
            ps.setString(1,obj.getOwnerName());
            ps.setString(2,obj.getPhone());
            ps.setString(3,obj.getCnic());
            ps.setString(4,obj.getAddress());
            ps.setDouble(5,obj.getCommission());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting VehicleOwner.", e);
        }
    }

    @Override
    public List<VehicleOwner> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE_OWNERS);
            return vehicleOwnerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all VehicleOwner.", e);
        }
    }

    @Override
    public VehicleOwner getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting VehicleOwner by ID.", e);
        }
    }

    @Override
    public void update(VehicleOwner obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_OWNER);
            ps.setString(1,obj.getOwnerName());
            ps.setString(2,obj.getPhone());
            ps.setString(3,obj.getCnic());
            ps.setString(4,obj.getAddress());
            ps.setDouble(5,obj.getCommission());
            ps.setInt(6,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating VehicleOwner.", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting VehicleOwner by ID.", e);
        }
    }

    public List<VehicleOwner> getByName(String name)
    {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicle_owner WHERE o_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting Vehicle Owner by ID.", e);
        }
    }

    public VehicleOwner getVehicleOwnerWhoHasBooking(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_OWNER_WHO_HAS_BOOKING);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting vehicle owner who has booking.", e);
        }
    }
}
