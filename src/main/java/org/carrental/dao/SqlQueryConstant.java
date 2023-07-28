package org.carrental.dao;

public class SqlQueryConstant
{
    // Customer
    public static final String GET_ALL_CUSTOMERS = "SELECT * FROM customer";
    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";
    public static final String GET_CUSTOMER_BY_NAME = "SELECT * FROM customer WHERE c_name like ?";
    public static final String GET_CUSTOMER_WHO_HAS_BOOKING = "SELECT * FROM customer c INNER JOIN booking b ON c.id = b.c_id WHERE c.id = ?";
    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE id = ?";
    public static final String UPDATE_CUSTOMER = "UPDATE customer SET c_name=?, phone=?, cnic=?, address=?, refphone=? WHERE id = ?";
    public static final String INSERT_INTO_CUSTOMER = "INSERT INTO customer (c_name, phone, cnic, address, refphone) VALUES (?, ?, ?, ?, ?)";

    // VehicleOwner
    public static final String GET_ALL_VEHICLE_OWNERS = "SELECT * FROM vehicle_owner";
    public static final String GET_VEHICLE_OWNER_BY_NAME = "SELECT * FROM vehicle_owner WHERE o_name like ?";
    public static final String GET_VEHICLE_OWNER_BY_ID = "SELECT * FROM vehicle_owner WHERE id = ?";
    public static final String GET_VEHICLE_OWNER_WHO_HAS_BOOKING = "SELECT * FROM vehicle_owner o INNER JOIN vehicle v ON o.id = v.o_id WHERE o.id = ?";
    public static final String DELETE_VEHICLE_OWNER_BY_ID = "DELETE FROM vehicle_owner WHERE id = ?";
    public static final String UPDATE_VEHICLE_OWNER = "UPDATE vehicle_owner SET o_name=?, phone=?, cnic=?, address=?, commission=? WHERE id = ?";
    public static final String INSERT_INTO_VEHICLE_OWNER = "INSERT INTO vehicle_owner (o_name, phone, cnic, address, commission) VALUES (?, ?, ?, ?, ?)";

    // Vehicle
    public static final String GET_ALL_VEHICLES = "SELECT * FROM vehicle";
    public static final String DISPLAY_VEHICLE_BY_ID_AND_NAME = "SELECT id, v_name FROM vehicle";
    public static final String SEARCH_VEHICLE_BY_NAME = "SELECT * FROM vehicle WHERE v_name like ?";
    public static final String GET_VEHICLE_WHO_HAS_BOOKING = "SELECT * FROM vehicle v INNER JOIN booking b ON v.id = b.v_id WHERE v.id = ?";
    public static final String GET_VEHICLE_BY_NAME = "SELECT v.id, v.v_name, v.model, v.brand, v.color, o.o_name FROM vehicle v INNER JOIN vehicle_owner o ON v.o_id = o.id WHERE v_name like ?";
    public static final String GET_ALL_VEHICLE_BY_ID_AND_OWNER_NAME = "SELECT v.id, v.v_name, v.model, v.brand, v.color, o.o_name FROM vehicle v INNER JOIN vehicle_owner o ON v.o_id = o.id ";
    public static final String GET_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id = ?";
    public static final String DELETE_VEHICLE_BY_ID = "DELETE FROM vehicle WHERE id = ?";
    public static final String UPDATE_VEHICLE = "UPDATE vehicle SET v_name=?, model=?, brand=?, color=?, o_id=? WHERE id = ?";
    public static final String INSERT_INTO_VEHICLE = "INSERT INTO vehicle (v_name, model, brand, color, o_id) VALUES (?, ?, ?, ?, ?)";

    // User
    public static final String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND pass = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user";

    public static final String GET_USER_BY_NAME = "SELECT * FROM user WHERE username like ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE user SET username=?, pass=? WHERE id = ?";
    public static final String INSERT_INTO_USER = "INSERT INTO user (username, pass) VALUES (?, ?)";

    // Booking
    public static final String GET_ALL_BOOKINGS = "SELECT * FROM booking";
    public static final String GET_BOOKINGS_BY_CUSTOMER_NAME =
            "SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.complete_date, (b.price * DATEDIFF(b.complete_date, b.b_date)) AS price, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id " +
            "INNER JOIN vehicle v ON b.v_id = v.id WHERE c.c_name like ?";
    public static final String GET_ALL_BOOKINGS_WITH_CUSTOMER_NAME_AND_VEHICLE_NAME =
            "SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.complete_date, (b.price * DATEDIFF(b.complete_date, b.b_date)) AS price, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id";

    public static final String GET_BOOKING_BY_ID = "SELECT * FROM booking WHERE b_id = ?";
    public static final String GET_BOOKING_BY_VEHICLE_ID_AND_CUSTOMER_ID = "SELECT b_id FROM booking WHERE v_id = ? AND c_id = ?";
    public static final String DELETE_BOOKING_BY_ID = "DELETE FROM booking WHERE b_id = ?";
    public static final String UPDATE_BOOKING = "UPDATE booking " +
            "SET c_id = ?, v_id = ?, b_date = ?, complete_date = ?, price = ?, `status` = ? " +
            "WHERE b_id = ?";
    public static final String INSERT_INTO_BOOKING = "INSERT INTO booking (c_id, v_id, b_date, complete_date, price, status) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String GET_LAST_INSERTED_BOOKING_ID = "SELECT LAST_INSERT_ID()";
    public static final String GET_BOOKED_VEHICLES = "SELECT v_id FROM booking";

    // Reports
    // Monthly Report
    public static final String GET_ALL_BOOKING_FROM_START_DATE_AND_END_DATE =
            "SELECT b.b_id, c.c_name, v.v_name, b.b_date, b.complete_date, (b.price * DATEDIFF(b.complete_date, b.b_date)) AS price, o.commission, b.status FROM booking b INNER JOIN customer c ON b.c_id = c.id INNER JOIN vehicle v ON b.v_id = v.id INNER JOIN vehicle_owner o ON b.v_id = o.id WHERE b.b_date BETWEEN ? AND ?";

    public static final String TOTAL_COMMISSION =
            "SELECT SUM(o.commission) FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id WHERE b.b_date BETWEEN ? AND ?";

    public static final String TOTAL_PRICE = "SELECT SUM(price * DATEDIFF(complete_date, b_date)) AS price FROM booking WHERE b_date BETWEEN ? AND ?";
    public static final String TOTAL_PROFIT = "SELECT SUM(b.price * DATEDIFF(b.complete_date, b.b_date)) - SUM(o.commission) AS profit FROM vehicle_owner o INNER JOIN booking b ON o.id = b.v_id WHERE b.b_date BETWEEN ? AND ?";

    // Commission Report

    public static final String VEHICLE_OWNER_COMMISSIONS = "SELECT o.o_name, SUM(o.commission) FROM vehicle_owner o INNER JOIN vehicle v ON o.id = v.o_id INNER JOIN booking b ON v.id = b.v_id WHERE b.b_date BETWEEN ? AND ? GROUP BY o.o_name";
}
