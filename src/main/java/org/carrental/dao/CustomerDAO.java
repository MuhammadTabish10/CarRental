package org.carrental.dao;

import org.carrental.domain.Customer;
import org.carrental.mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CustomerDAO extends BaseDAO implements ICrud<Customer>
{
    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_CUSTOMER);
            ps.setString(1,obj.getName());
            ps.setString(2,obj.getPhoneNumber());
            ps.setString(3,obj.getCnic());
            ps.setString(4,obj.getAddress());
            ps.setString(5,obj.getReferencePhoneNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting customer.", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CUSTOMERS);
             return customerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all customers.", e);
        }
    }

    @Override
    public Customer getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer by ID.", e);
        }
    }

    public List<Customer> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_CUSTOMER_BY_NAME);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer by ID.", e);
        }
    }

    @Override
    public void update(Customer obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_CUSTOMER);
            ps.setString(1,obj.getName());
            ps.setString(2,obj.getPhoneNumber());
            ps.setString(3,obj.getCnic());
            ps.setString(4,obj.getAddress());
            ps.setString(5,obj.getReferencePhoneNumber());
            ps.setInt(6,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer.", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SqlQueryConstant.DELETE_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer by ID.", e);
        }
    }

    public Customer getCustomerWhoHasBooking(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_CUSTOMER_WHO_HAS_BOOKING);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer who has booking.", e);
        }
    }

}
