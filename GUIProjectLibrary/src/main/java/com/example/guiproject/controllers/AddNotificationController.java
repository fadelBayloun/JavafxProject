package com.example.guiproject.controllers;

import com.example.guiproject.DAO.NotificationDAO;
import com.example.guiproject.DAO.StaffDAO;
import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Librarian;
import com.example.guiproject.Models.Manager;
import com.example.guiproject.Models.Notification;
import com.example.guiproject.Models.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddNotificationController implements Initializable {
    @FXML
    public TextField msgTitle;
    @FXML
    public TextArea msgBody;
    @FXML
    public Label msgResult;
    @FXML
    public TextField addUsernameInput;
    @FXML
    public TextField makeManagerInput;
    @FXML
    public TextField removeStaffInput;
    @FXML
    public TextField makeLibrarianInput;
    @FXML
    public PasswordField addPassInput;
    @FXML
    public PasswordField addPassRepeatInput;
    @FXML
    public Label staffResult;
    @FXML
    public ListView staffList;



    @FXML
    public void sendNotification() throws SQLException {
        if (!Objects.equals(msgTitle.getText(), "")&& !Objects.equals(msgBody.getText(), "")) {
            Notification notification = new Notification(msgTitle.getText(), msgBody.getText());
            if (HelloApplication.user instanceof Manager) {
                Manager man = (Manager) HelloApplication.user;
                man.notifyAllStaff(notification);
            }
            msgTitle.setText("");
            msgBody.setText("");
            msgResult.setText("Notification hs been sent successfully");
        }else {
            msgResult.setText("Fill out both Title and Body! ");
        }


    }
    @FXML
    public  void clear(){
        msgTitle.setText("");
        msgBody.setText("");
        msgResult.setText("");

    }

    @FXML
    public void addStaff() throws SQLException {
        if (!Objects.equals(addUsernameInput.getText(), ""))
        {
                if(!Objects.equals(addPassInput.getText(), "")) {

                        if (Objects.equals(addPassInput.getText(), addPassRepeatInput.getText())) {

                            Librarian librarian = new Librarian(addUsernameInput.getText(),addPassInput.getText() );
                            StaffDAO staffDAO= new StaffDAO();
                            staffDAO.addStaff(librarian);
                            Manager m =(Manager) HelloApplication.user;
                            m.librarians= staffDAO.getAllLibrarians();
                            staffResult.setText(addUsernameInput.getText()+" is added successfully");
                            addUsernameInput.setText("");
                            addPassInput.setText("");
                            addPassRepeatInput.setText("");



                        } else {
                            staffResult.setText("Passwords don't match");

                        }
                }else{
                    staffResult.setText("Provide a password");

                }
            } else {
            staffResult.setText("Provide a user name ");

        }
        updatelist();


    }

    public void  makeManager() throws SQLException {
        StaffDAO staffDAO= new StaffDAO();
        Staff staff= staffDAO.getStaffByUsername(makeManagerInput.getText());
        if (staff!= null){
            if(staff instanceof Librarian )
            {
                staffDAO.makeManager(makeManagerInput.getText());
                staffResult.setText(makeManagerInput.getText()+ " is now a manager");
                makeManagerInput.setText("");
            }
            else {
                staffResult.setText(makeManagerInput.getText()+ " is already a manager");
                makeManagerInput.setText("");
            }
        }else {
            staffResult.setText(makeManagerInput.getText()+ " is not a staff member");
            makeManagerInput.setText("");
        }
        updatelist();





    }

    public  void  removeStaff() throws SQLException {
        StaffDAO staffDAO= new StaffDAO();
        Librarian libToRemove=null;
        boolean res= staffDAO.removeStaff(removeStaffInput.getText());
        if (res){

            staffResult.setText( removeStaffInput.getText()+" no longer a member in the library staff");
            Manager man = (Manager) HelloApplication.user;

            for (Librarian lib : man.librarians) {
                if (Objects.equals(lib.getUsername(), removeStaffInput.getText())){
                    libToRemove= lib;
                }

            }
            man.librarians.remove(libToRemove);


        }else {
            staffResult.setText("Staff member with given user name doesn't exist");
        }
        removeStaffInput.setText("");
        updatelist();


    }

    public  void makeLibrarian () throws SQLException {
        StaffDAO staffDAO= new StaffDAO();
        Staff staff= staffDAO.getStaffByUsername(makeLibrarianInput.getText());
        if (staff!= null){
            if(staff instanceof Manager )
            {
                staffDAO.makeLibrarian(makeLibrarianInput.getText());
                staffResult.setText(makeLibrarianInput.getText()+ " is no longer a Manager");
                makeLibrarianInput.setText("");
            }
            else {
                staffResult.setText(makeLibrarianInput.getText()+ " is not a Manager");
                makeLibrarianInput.setText("");
            }
        }else {
            staffResult.setText(makeLibrarianInput.getText()+ " is not a staff member");
            makeLibrarianInput.setText("");
        }
        updatelist();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            updatelist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updatelist() throws SQLException {
        ObservableList<Staff> obStaff = FXCollections.observableArrayList();
        StaffDAO staffDAO= new StaffDAO();
        try {
            ArrayList<Staff> staffs= staffDAO.getAllStaff();
            obStaff.addAll(staffs);
            staffList.setCellFactory(param -> new ListCell<Staff>() {
                @Override
                protected void updateItem(Staff staff, boolean empty) {
                    super.updateItem(staff, empty);
                    if (empty || staff == null) {
                        setText(null);
                    } else {
                        String s;
                        if(staff instanceof Manager) { s=" Manager"; }else {s="librarian";}

                        setText( staff.getUsername()+ " is "+s );
                    }
                }
            });

            staffList.setItems(obStaff);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
