package com.example.guiproject.controllers;

import com.example.guiproject.DAO.StaffDAO;
import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public Button login;
    @FXML
    public Button clear;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;

    @FXML
    public Label test;

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        String name;
        String pass;
        if(username.getText() != null )
            name = username.getText();
        else
        {
            name="";
            test.setText("life is not bambi");
        }

        if(password.getText()!= null )
            pass = password.getText();
        else
        {
            pass="";
            test.setText("life is not bambi");
        }

        StaffDAO dao = new StaffDAO();
        Staff staff = dao.authenticate(name,pass);
        if(staff !=null){
            HelloApplication app= new HelloApplication();
            app.home();

        }
        else{
            test.setText("Wrong credentials!");
        }
    }

    public void clear(){
        username.setText("");
        password.setText("");


    }
}
