package com.example.guiproject.DAO;

import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Librarian;
import com.example.guiproject.Models.Manager;
import com.example.guiproject.Models.Staff;

import java.sql.*;
import java.util.ArrayList;


public class StaffDAO {
    Connection conn;

    public StaffDAO() throws SQLException {
    }

    public Staff getStaffByUsername(String username) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "SELECT * FROM staff WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("username");
            String password = rs.getString("password");
            boolean isManger = rs.getBoolean("isManager");
            if (isManger) {
                return new Manager(id, name, password);
            } else {
                return new Librarian(id, name, password);
            }
        } else {
            return null;
        }
    }

    public Staff authenticate(String username, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "SELECT * FROM staff WHERE username = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String staffUsername = resultSet.getString("username");
                String staffPassword = resultSet.getString("password");
                boolean role = resultSet.getBoolean("isManager");
                if (role) {
                    HelloApplication.user = new Manager(id, staffUsername, staffPassword);
                    return HelloApplication.user;
                }
                HelloApplication.user = new Librarian(id, staffUsername, staffPassword);
                return HelloApplication.user;
            } else {
                return null;
            }
        }
    }

    public ArrayList<Librarian> getAllLibrarians() throws SQLException {
        ArrayList<Librarian> librarians = new ArrayList<>();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "SELECT * FROM staff WHERE isManager = false"; // check syntax
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String librarianUsername = resultSet.getString("username");
                String librarianPassword = resultSet.getString("password");
                librarians.add(new Librarian(id, librarianUsername, librarianPassword));
            }
            return librarians;
        }
    }

    public void addStaff(Staff staff) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "INSERT INTO staff (username,password,isManager) VALUES (?,?,?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, staff.getUsername());
            statement.setString(2, staff.getPassword());
            if (staff instanceof Manager) {
                statement.setInt(3, 1);
            } else {
                statement.setInt(3, 0);
            }

            statement.execute();

        }
    }

    public boolean removeStaff(String name) throws SQLException {
        if(getStaffByUsername(name)!=null) {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String query = "DELETE FROM staff WHERE username = ?;";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, name);
                statement.execute();

            }
            return true;
        }else return false;

    }

    public void  makeManager(String UserName) throws SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "UPDATE staff SET isManager=1 WHERE  username= ? ;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, UserName);
            statement.execute();
        }
    }

    public void  makeLibrarian(String UserName) throws SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "UPDATE staff SET isManager=0 WHERE  username= ? ;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, UserName);
            statement.execute();
        }
    }
    public ArrayList<Staff> getAllStaff() throws SQLException {
        ArrayList<Staff> staffs = new ArrayList<>();
        Staff staff;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "SELECT * FROM staff"; // check syntax
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name  = resultSet.getString("username");
                String Password = resultSet.getString("password");
                boolean isManager = resultSet.getBoolean("isManager");
                if(isManager) {
                    staff= new Manager(id, name, Password);
                }else {
                    staff= new Librarian(id, name, Password);
                }
                staffs.add(staff);
            }
            return staffs;
        }
    }

}

