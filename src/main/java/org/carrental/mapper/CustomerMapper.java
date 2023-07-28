package org.carrental.mapper;

import org.carrental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements IMapper<Customer>
{
    // ResultSet ley k object return kareyga CustomerMapper

    private final static String ID = "id";
    private final static String C_NAME = "c_name";
    private final static String PHONE = "phone";
    private final static String CNIC = "cnic";
    private final static String ADDRESS = "address";
    private final static String REFPHONE = "refphone";

    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while(rs.next()){
            Customer customer = Customer.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phoneNumber(rs.getString(PHONE))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFPHONE))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Customer.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phoneNumber(rs.getString(PHONE))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFPHONE))
                    .build();
        }
        return null;
    }
}
