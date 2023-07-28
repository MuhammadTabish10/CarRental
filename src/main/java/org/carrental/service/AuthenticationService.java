package org.carrental.service;

import org.carrental.dao.UserDAO;
import org.carrental.domain.User;

public class AuthenticationService
{
    private final UserDAO userDAO = new UserDAO();

    public boolean checkLogin(String username, String password){
       User user = userDAO.getUserByUsernameAndPassword(username,password);
       if(user != null){
           return Boolean.TRUE;
       }
       return Boolean.FALSE;
    }

}
