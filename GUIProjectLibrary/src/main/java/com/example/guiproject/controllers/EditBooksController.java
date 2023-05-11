package com.example.guiproject.controllers;

import com.example.guiproject.DAO.BookDAO;
import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EditBooksController {

    @FXML
    public TextField bookId;

    @FXML
    public TextField category;

    @FXML
    public TextField name;

    @FXML
    public TextField publisher;
    @FXML
    public Button goBack;
    @FXML
    public Label result;

    @FXML
    public void addBook(ActionEvent event) throws SQLException {
        if(!Objects.equals(name.getText(), "") && !Objects.equals(category.getText(), "") && !Objects.equals(publisher.getText(), "")) {
            Book b = new Book(name.getText(), category.getText(), publisher.getText(), 0);
            BookDAO bookdao = new BookDAO();
            bookdao.addBook(b);
            result.setText("Book was successfully added");
            name.setText("");
            category.setText("");
            publisher.setText("");
        }else {
            result.setText("Fill out all the fields!");
        }
    }

    @FXML
    public void removeBook(ActionEvent event) throws SQLException {
        if(!Objects.equals(bookId.getText(), "")){
            BookDAO bookdao = new BookDAO();
            if(bookdao.removeBook(Integer.parseInt(bookId.getText()))){
                result.setText("Book was successfully deleted");
            }else {
                result.setText("Book was not found");
            }
            bookId.setText("");
        }
        else{
            result.setText("Enter Book Id First");

        }

    }

    public void goback(ActionEvent event) throws IOException {
        HelloApplication main= new HelloApplication();
        main.Books();

    }

}

