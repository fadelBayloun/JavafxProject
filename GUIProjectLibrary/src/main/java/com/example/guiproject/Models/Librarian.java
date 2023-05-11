package com.example.guiproject.Models;

import com.example.guiproject.DAO.NotificationDAO;

import java.sql.SQLException;

public class Librarian extends Staff implements Observer{
    public Librarian(int id, String username, String password) {
        super(id, username, password);
    }

    public Librarian(String username, String password) {
        super(username, password);
    }

    public void getNotified(Notification notification) throws SQLException {
        NotificationDAO notificationDAO= new NotificationDAO();
        notificationDAO.addNotification(notification, this.id);
    }
}
