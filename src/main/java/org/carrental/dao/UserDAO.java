package org.carrental.dao;

import org.carrental.domain.User;
import org.carrental.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO extends BaseDAO implements ICrud<User>
{
    private final UserMapper userMapper = new UserMapper();

    public User getUserByUsernameAndPassword(String username, String password){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_USER_BY_USERNAME_AND_PASSWORD);
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_USER);
            ps.setString(1,obj.getUsername());
            ps.setString(2,obj.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting User.", e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_USERS);
            return userMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all User.", e);
        }
    }

    @Override
    public User getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_USER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting User by ID.", e);
        }
    }

    @Override
    public void update(User obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_USER);
            ps.setString(1,obj.getUsername());
            ps.setString(2,obj.getPassword());
            ps.setInt(3,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating User.", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SqlQueryConstant.DELETE_USER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting User by ID.", e);
        }
    }
}
