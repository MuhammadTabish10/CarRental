package org.carrental.mapper;

import org.carrental.domain.Customer;
import org.carrental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleOwnerMapper implements IMapper<VehicleOwner>
{
    private final static String ID = "id";
    private final static String OWNER_NAME = "o_name";
    private final static String PHONE = "phone";
    private final static String CNIC = "cnic";
    private final static String ADDRESS = "address";
    private final static String COMMISSION = "commission";
    @Override
    public List<VehicleOwner> resultSetToList(ResultSet rs) throws SQLException {
        List<VehicleOwner> vehicleOwnerList = new ArrayList<>();
        while(rs.next()){
            VehicleOwner vehicleOwner = VehicleOwner.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .ownerName(rs.getString(OWNER_NAME))
                    .phone(rs.getString(PHONE))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getDouble(COMMISSION))
                    .build();
            vehicleOwnerList.add(vehicleOwner);
        }
        return vehicleOwnerList;
    }

    @Override
    public VehicleOwner resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return VehicleOwner.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .ownerName(rs.getString(OWNER_NAME))
                    .phone(rs.getString(PHONE))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getDouble(COMMISSION))
                    .build();
        }
        return null;
    }

    public List<VehicleOwner> resultSetToListT(ResultSet rs) throws SQLException {
        List<VehicleOwner> vehicleOwnerList = new ArrayList<>();
        while(rs.next()){
            VehicleOwner vehicleOwner = VehicleOwner.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .ownerName(rs.getString(OWNER_NAME))
                    .phone(rs.getString(PHONE))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getDouble(COMMISSION))
                    .build();
            vehicleOwnerList.add(vehicleOwner);
        }
        return vehicleOwnerList;
    }

}
