package com.example.guiproject.controllers;

import com.example.guiproject.DAO.BookDAO;
import com.example.guiproject.DAO.MemberDAO;
import com.example.guiproject.DAO.RentedBookDAO;
import com.example.guiproject.Models.RentedBook;
import com.example.guiproject.Models.Book;
import com.example.guiproject.Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class RentBookController {

    @FXML
    public TextField bookId;
    @FXML
    public TextField memberId;
    @FXML
    public Label warning;
    @FXML
    public Button rentButton1;

    @FXML
    public void rentButton(ActionEvent actionEvent) throws SQLException {
        BookDAO bdao = new BookDAO();
        MemberDAO mdao = new MemberDAO();
        RentedBookDAO rdao = new RentedBookDAO();
        Book b = bdao.getBookById( Integer.parseInt(bookId.getText()));
        if(b==null)
        {
            warning.setText("Book is not found");
            return;
        }
        Member m =  mdao.getMemberById(Integer.parseInt(memberId.getText()));
        if(m==null)
        {
            warning.setText("Member is not found");
            return;
        }
        if( b.getStatus()==0 ){

            LocalDate today = LocalDate.now(); // get the current date
            LocalDate twoWeeksLater = today.plusWeeks(2);
            java.sql.Date sqlDate = java.sql.Date.valueOf(twoWeeksLater);
            RentedBook rb = new RentedBook(b.getId(),m.getId(),sqlDate);
            rdao.addRentedBook(rb);
            BookDAO bookDAO= new BookDAO();
            bookDAO.rentBook(b.getId());
            warning.setText("Book Rented Successfully");


        }
        else {
            warning.setText("Book already rented or wrong ids");
        }
    }

}
