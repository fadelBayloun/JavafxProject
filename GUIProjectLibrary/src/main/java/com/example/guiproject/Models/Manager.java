package com.example.guiproject.Models;

import com.example.guiproject.DAO.StaffDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Manager extends Staff implements Observable{
    public ArrayList<Librarian> librarians;
    public Manager(int id, String username, String password) throws SQLException {
        super(id, username, password);
        StaffDAO sdao = new StaffDAO();
        librarians= sdao.getAllLibrarians();

    }

    public  void  addLibrarian( Librarian lib){
        librarians.add(lib);
    }
    public  void  removeLibrarian( Librarian lib){
        librarians.remove(lib);
    }

    public void notifyAllStaff (Notification notification) throws SQLException {
        for (Librarian b : librarians) {
            b.getNotified(notification);
        }
    }
}
