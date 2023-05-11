package com.example.guiproject.controllers;

import com.example.guiproject.DAO.MemberDAO;
import com.example.guiproject.Models.Book;
import com.example.guiproject.Models.Member;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MembersController implements Initializable {

    @FXML
    public  TextField MemberDeleteInput;

    @FXML
    public TextField MemberEmailInput;

    @FXML
    public TextField MemberPhoneInput;

    @FXML
    public TextField MemberNameInput;

    @FXML
    public TableColumn<Member, String> MemberEmailColumn;

    @FXML
    public TableColumn<Member, Integer> MemberIdColumn;

    @FXML
    public TableColumn<Member, String> MemberNameColumn;

    @FXML
    public TableColumn<Member,String> MemberPhoneColumn;


    @FXML
    public TableView<Member> membersTable;

    @FXML
    public TextField searchButton;
    @FXML
    public TextField memberNameSearch;


    ObservableList<Member> members;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }
    @FXML
    public void reset(){
        MemberDAO memberDAO = new MemberDAO();
        try {
            show(memberDAO.getMembers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void show(ArrayList<Member> givenMembers){

              members = FXCollections.observableArrayList(givenMembers);
              membersTable.setItems(members);

              MemberIdColumn.setCellValueFactory(cellData -> {
                  Integer memberId = cellData.getValue().getId();
                  ObjectProperty<Integer> MemberIdProperty = new SimpleObjectProperty<>(memberId);
                  return MemberIdProperty;
              });
              MemberNameColumn.setCellValueFactory(cellData -> {
                  String memberName = cellData.getValue().getName();
                  ObjectProperty<String> MemberNameProperty = new SimpleObjectProperty<>(memberName);
                  return MemberNameProperty;
              });
              MemberPhoneColumn.setCellValueFactory(cellData -> {
                  String memberPhone = cellData.getValue().getPhoneNumber();
                  ObjectProperty<String> MemberPhoneProperty = new SimpleObjectProperty<>(memberPhone);
                  return MemberPhoneProperty;
              });
              MemberEmailColumn.setCellValueFactory(cellData -> {
                  String memberEmail = cellData.getValue().getEmail();
                  ObjectProperty<String> MemberEmailProperty = new SimpleObjectProperty<>(memberEmail);
                  return MemberEmailProperty;
              });





    }

    public void addMember(ActionEvent event) throws SQLException {
        if(!Objects.equals(MemberNameInput.getText(), "") && !Objects.equals(MemberPhoneInput.getText(), "") && !Objects.equals(MemberEmailInput.getText(),  "") ) {
            MemberDAO memberDAO = new MemberDAO();
            String name = MemberNameInput.getText();
            String phone = MemberPhoneInput.getText();
            String email = MemberEmailInput.getText();
            Member m = new Member(phone, email, name);
            memberDAO.addMember(m);
            this.show(memberDAO.getMembers());
            this.clear();
        }

    }
    public  void  clear(){
        MemberNameInput.setText("");
        MemberPhoneInput.setText("");
        MemberEmailInput.setText("");
    }


    public void removeMember(ActionEvent event) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        int id = Integer.parseInt(MemberDeleteInput.getText());
        memberDAO.removeMember(id);
        this.show(memberDAO.getMembers());
        MemberDeleteInput.setText("");
    }

    public void searchMember() throws SQLException {
        ArrayList<Member> givenMembers = new ArrayList<>();
        MemberDAO memberDAO = new MemberDAO();
        try {
            Integer.parseInt(memberNameSearch.getText());
            givenMembers.add(memberDAO.getMemberById(Integer.parseInt(memberNameSearch.getText())));
            show(givenMembers);
            memberNameSearch.setText("");
        } catch (Exception e) {
                givenMembers.addAll(memberDAO.getMemberByName(memberNameSearch.getText()));
                show(givenMembers);

        }


    }

}
