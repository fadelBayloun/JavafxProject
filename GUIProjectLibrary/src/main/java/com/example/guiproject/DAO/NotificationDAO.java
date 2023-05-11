package com.example.guiproject.DAO;

import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Member;
import com.example.guiproject.Models.Notification;

import java.sql.*;
import java.util.ArrayList;

public class NotificationDAO {
    Connection conn;
    public void addNotification(Notification notification,int id) throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "INSERT INTO Notifications (libId,title,body,isread) VALUES (?,?,?,0);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, notification.getTitle());
            statement.setString(3, notification.getBody());
            statement.execute();
        }
    }

    public ArrayList<Notification> getAllNotifications( int lIBid) throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "SELECT * FROM Notifications WHERE libId = ? And isread=0;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, lIBid);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Notification> notifications = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                notifications.add(new Notification(id, title, body));
            }
            return notifications;
        }
    }

    public void setAsRead() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        String query = "UPDATE Notifications SET isread=1 WHERE  libId= ? ;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, HelloApplication.user.getId());
            statement.execute();
        }
    }

}
