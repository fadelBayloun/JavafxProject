package com.example.guiproject.controllers;

import com.example.guiproject.DAO.NotificationDAO;
import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Manager;
import com.example.guiproject.Models.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    public AnchorPane container;
    @FXML
    public ListView NotificationList;
    ArrayList<Notification> notifications;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(HelloApplication.user instanceof Manager) {
            HelloApplication main = new HelloApplication();
            FXMLLoader loader = main.getLoader();
            try {
                Parent content = loader.load();
                container.getChildren().setAll(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
             ObservableList<Notification> oBnotifications = FXCollections.observableArrayList();
             NotificationDAO notificationDAO= new NotificationDAO();
            try {
                notifications= notificationDAO.getAllNotifications(HelloApplication.user.getId());
                oBnotifications.addAll(notifications);
                NotificationList.setCellFactory(param -> new ListCell<Notification>() {
                    @Override
                    protected void updateItem(Notification notification, boolean empty) {
                        super.updateItem(notification, empty);
                        if (empty || notification == null) {
                            setText(null);
                        } else {
                            setText(notification.getTitle() + "\n" + notification.getBody());
                        }
                    }
                });

                NotificationList.setItems(oBnotifications);
                notificationDAO.setAsRead();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
}
