package com.example.guiproject.controllers;

import com.example.guiproject.DAO.BookDAO;
import com.example.guiproject.DAO.RentedBookDAO;
import com.example.guiproject.HelloApplication;
import com.example.guiproject.Models.Book;
import com.example.guiproject.Models.RentedBook;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    public TableColumn<Book, String> BookCatColumn;

    @FXML
    public TableColumn<Book, Integer> BookIdColumn;

    @FXML
    public TableColumn<Book, String> BookNameColumn;

    @FXML
    public TableColumn<Book, String> BookPublisherColumn;

    @FXML
    public TableColumn<Book, Integer> BookStatusColumn;

    @FXML
    public ToggleGroup Books;

    @FXML
    public RadioButton option1;

    @FXML
    public RadioButton option2;

    @FXML
    public RadioButton option3;

    @FXML
    public TextField searchInput;

    @FXML
    public TableView<Book> booksTable;

    @FXML
    public Label searchMsg;
    ObservableList<Book> books;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }
    @FXML
    public void reset(){

        BookDAO bookDAO = new BookDAO();
        try {
            show(bookDAO.getBooks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void show(ArrayList<Book> givenBooks){
        books =  FXCollections.observableArrayList(givenBooks);
        //casting may cause some errors
        // pay attention to this
        booksTable.setItems(books);
        BookIdColumn.setCellValueFactory(cellData -> {
            Integer id = cellData.getValue().getId();
            ObjectProperty<Integer> idProperty = new SimpleObjectProperty<>(id);
            return idProperty;
        });
        BookNameColumn.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getName();
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>(name);
            return nameProperty;
        });
        BookCatColumn.setCellValueFactory(cellData -> {
            String cat = cellData.getValue().getCategory();
            ObjectProperty<String> catProperty = new SimpleObjectProperty<>(cat);
            return catProperty;
        });
        BookPublisherColumn.setCellValueFactory(cellData -> {
            String auth = cellData.getValue().getPublisher();
            ObjectProperty<String> authProperty = new SimpleObjectProperty<>(auth);
            return authProperty;
        });
        BookStatusColumn.setCellValueFactory(cellData -> {
            Integer st = cellData.getValue().getStatus();
            ObjectProperty<Integer> stProperty = new SimpleObjectProperty<>(st);
            return stProperty;
        });


    }

    @FXML
    public void searchBook(ActionEvent event) throws SQLException {
        BookDAO bookDAO = new BookDAO();
        int num_books;
        ArrayList<Book> searchedBooks;
        if (option1.isSelected()){
            searchedBooks = bookDAO.getBooksByName(searchInput.getText());
            num_books = searchedBooks.size();
            show(searchedBooks);
            searchMsg.setText(num_books+" books found");
        } else if (option2.isSelected()) {
            searchedBooks = bookDAO.getBooksByAuthor(searchInput.getText());
            num_books = searchedBooks.size();
            show(searchedBooks);
            searchMsg.setText(num_books+" books found");
        } else if (option3.isSelected()) {
            searchedBooks = bookDAO.getBooksByCategory(searchInput.getText());
            num_books = searchedBooks.size();
            show(searchedBooks);
            searchMsg.setText(num_books+" books found");
        }else {
            searchMsg.setText("Choose a radio button!");
        }
    }
    @FXML
    public void editBook() throws IOException {
        HelloApplication main = new HelloApplication();
        main.editBook();
    }
}
