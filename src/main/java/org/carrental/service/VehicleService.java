package org.carrental.service;

import org.carrental.dao.VehicleDAO;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.util.List;

public class VehicleService
{
    VehicleDAO dao = new VehicleDAO();

    public String[][] transformToJTable(List<Vehicle> vehicleList, int columnSize)
    {
        String[][] data = new String[vehicleList.size()][columnSize];

        for (int i = 0; i < vehicleList.size(); i++)
        {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehicleName();
            data[i][2] = String.valueOf(vehicleList.get(i).getModel());
            data[i][3] = vehicleList.get(i).getBrand();
            data[i][4] = vehicleList.get(i).getColor();
            data[i][5] = String.valueOf((vehicleList.get(i).getVehicleOwnerId()));
        }
        return data;
    }

    public String[][] transformToJTableForVehiclesAndOwners(List<Vehicle> vehicleList, int columnSize)
    {
        String[][] data = new String[vehicleList.size()][columnSize];

        for (int i = 0; i < vehicleList.size(); i++)
        {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehicleName();
            data[i][2] = String.valueOf(vehicleList.get(i).getModel());
            data[i][3] = vehicleList.get(i).getBrand();
            data[i][4] = vehicleList.get(i).getColor();
            data[i][5] = vehicleList.get(i).getOwnerName();
        }
        return data;
    }

    public String[][] getAllVehicleForJTable()
    {
        List<Vehicle> vehicleList = dao.getAll();
        return transformToJTable(vehicleList,6);
    }

    public String[][] getAllVehicleWithOwnerForJTable()
    {
        List<Vehicle> vehicleList = dao.getAllVehiclesAndOwners();
        return transformToJTableForVehiclesAndOwners(vehicleList,6);
    }

    public Vehicle getById(Long id){
        return dao.getById(id);
    }

    public void update(Vehicle obj, Long id){
        dao.update(obj, id);
    }

    public void deleteById(Long id){
        dao.deleteById(id);
    }

    public String[][] searchByName(String name){
        List<Vehicle> vehicleList = dao.getByVehicleName(name);
        return transformToJTableForVehiclesAndOwners(vehicleList,6);
    }

    public void Save(String vehicleName, String model, String brand, String color, String vehicleOwnerId)
    {
        Vehicle vehicle = Vehicle.builder()
                .vehicleName(vehicleName)
                .model(Integer.valueOf(model))
                .brand(brand)
                .color(color)
                .vehicleOwnerId(Long.valueOf(vehicleOwnerId))
                .build();

        dao.insert(vehicle);
    }

    public boolean hasBooking(long id){
        Vehicle vehicle = dao.getVehicleWhoHasBooking(id);
        return vehicle != null;
    }

    public List<Vehicle> getAllVehicles()
    {
        return dao.getAll();
    }

    public Vehicle getVehicleById(int vehicleId)
    {
        return dao.getById((long) vehicleId);
    }
}
