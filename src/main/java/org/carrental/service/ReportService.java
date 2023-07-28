package org.carrental.service;

import org.carrental.dao.ReportDAO;
import org.carrental.domain.Booking;
import org.carrental.domain.VehicleOwner;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReportService
{
    ReportDAO dao = new ReportDAO();

    public String[][] transformToJTableWithCustomerNameAndVehicleNameAndCommission(List<Booking> bookingList, int columnSize)
    {
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size(); i++)
        {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomerName());
            data[i][2] = String.valueOf(bookingList.get(i).getVehicleName());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = String.valueOf(bookingList.get(i).getCommission());
            data[i][7] = String.valueOf(bookingList.get(i).getStatus());
        }
        return data;
    }

    public String[][] transformToJTableWithVehicleOwnerNameAndCommission(List<VehicleOwner> vehicleOwners, int columnSize)
    {
        String[][] data = new String[vehicleOwners.size()][columnSize];

        for (int i = 0; i < vehicleOwners.size(); i++)
        {
            data[i][0] = String.valueOf(vehicleOwners.get(i).getId());
            data[i][1] = String.valueOf(vehicleOwners.get(i).getOwnerName());
            data[i][2] = String.valueOf(vehicleOwners.get(i).getCommission());
        }
        return data;
    }

    public String[][] getAllBookingForJTableFromStartDateTillEndDate(Date start, Date end)
    {
        List<Booking> bookingList = dao.getAllBookingsWithStartDateAndEndDate(start,end);
        return transformToJTableWithCustomerNameAndVehicleNameAndCommission(bookingList,8);
    }

    public String[][] getAllVehicleOwnersWithCommission(Date start, Date end)
    {
        List<VehicleOwner> vehicleOwners = dao.getAllVehicleOwnersWithTotalCommission(start,end);
        return transformToJTableWithVehicleOwnerNameAndCommission(vehicleOwners,3);
    }

    public Double getAllCommissionForMonthlyReport(Date start, Date end){
        return dao.getTotalCommission(start, end);
    }

    public Double getAllPriceForMonthlyReport(Date start, Date end){
        return dao.getTotalPrice(start, end);
    }

    public Double getAllProfitForMonthlyReport(Date start, Date end){
        return dao.getTotalProfit(start, end);
    }

}
