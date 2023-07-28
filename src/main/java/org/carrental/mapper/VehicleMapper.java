package org.carrental.mapper;

import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle>
{
    private final static String ID = "id";
    private final static String VEHICLE_NAME = "v_name";
    private final static String MODEL = "model";
    private final static String BRAND = "brand";
    private final static String COLOR = "color";
    private final static String VEHICLE_OWNER_ID = "o_id";
    private final static String VEHICLE_OWNER_NAME = "o_name";

    @Override
    public List<Vehicle> resultSetToList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while(rs.next()){
            Vehicle vehicle = Vehicle.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .model(rs.getInt(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                   // .vehicleOwnerId((long) rs.getInt(VEHICLE_OWNER_ID))
                    .ownerName(rs.getString(VEHICLE_OWNER_NAME))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    @Override
    public Vehicle resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Vehicle.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .model(rs.getInt(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                    //.vehicleOwnerId((long) rs.getInt(VEHICLE_OWNER_ID))
                    .ownerName(rs.getString(VEHICLE_OWNER_NAME))
                    .build();
        }
        return null;
    }

    public List<Vehicle> resultSetToListWithVehicleOwnerID(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while(rs.next()){
            Vehicle vehicle = Vehicle.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .model(rs.getInt(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                    .vehicleOwnerId((long) rs.getInt(VEHICLE_OWNER_ID))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public Vehicle resultSetToObjectWithOwnerID(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Vehicle.builder()   //object banaya
                    .id( (long) rs.getInt(ID))
                    .vehicleName(rs.getString(VEHICLE_NAME))
                    .model(rs.getInt(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                    .vehicleOwnerId((long) rs.getInt(VEHICLE_OWNER_ID))
                    .build();
        }
        return null;
    }
}
