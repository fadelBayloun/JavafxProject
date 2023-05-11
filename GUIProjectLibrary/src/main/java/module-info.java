module com.example.guiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.guiproject to javafx.fxml;
    exports com.example.guiproject;
    exports com.example.guiproject.controllers;
    exports com.example.guiproject.DAO;
    exports com.example.guiproject.Models;


}