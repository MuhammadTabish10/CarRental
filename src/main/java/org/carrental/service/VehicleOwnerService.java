package org.carrental.service;

import org.carrental.dao.VehicleOwnerDAO;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.util.List;

public class VehicleOwnerService
{
    VehicleOwnerDAO dao = new VehicleOwnerDAO();

    public String[][] transformToJTable(List<VehicleOwner> vehicleOwnerList, int columnSize)
    {
        String[][] data = new String[vehicleOwnerList.size()][columnSize];

        for (int i = 0; i < vehicleOwnerList.size(); i++)
        {
            data[i][0] = String.valueOf(vehicleOwnerList.get(i).getId());
            data[i][1] = vehicleOwnerList.get(i).getOwnerName();
            data[i][2] = vehicleOwnerList.get(i).getPhone();
            data[i][3] = vehicleOwnerList.get(i).getCnic();
            data[i][4] = vehicleOwnerList.get(i).getAddress();
            data[i][5] = String.valueOf(vehicleOwnerList.get(i).getCommission());
        }
        return data;
    }

    public String[][] getAllVehicleOwnerForJTable()
    {
        List<VehicleOwner> vehicleOwnerList = dao.getAll();
        return transformToJTable(vehicleOwnerList,6);
    }

    public VehicleOwner getById(Long id){
        return dao.getById(id);
    }

    public void update(VehicleOwner obj, Long id){
        dao.update(obj, id);
    }

    public void deleteById(Long id){
        dao.deleteById(id);
    }

    public String[][] searchByName(String name){
        List<VehicleOwner> vehicleOwnerList = dao.getByName(name);
        return transformToJTable(vehicleOwnerList,6);
    }

    public boolean hasBooking(long id){
        VehicleOwner vehicleOwner = dao.getVehicleOwnerWhoHasBooking(id);
        return vehicleOwner != null;
    }

    public List<VehicleOwner> getAllVehicleOwners()
    {
        return dao.getAll();
    }


    public void Save(String vehicleOwnerName, String phone, String cnic, String address, String commission)
    {
        VehicleOwner vehicleOwner = VehicleOwner.builder()
                .ownerName(vehicleOwnerName)
                .phone(phone)
                .cnic(cnic)
                .address(address)
                .commission(Double.valueOf(commission))
                .build();

        dao.insert(vehicleOwner);
    }
}
