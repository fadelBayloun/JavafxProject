package com.example.guiproject.DAO;

import com.example.guiproject.Models.RentedBook;
import com.example.guiproject.Models.Staff;

import java.sql.*;
import java.util.ArrayList;

public class RentedBookDAO {

    Connection conn;

    public RentedBookDAO() throws SQLException {
    }

    public ArrayList<RentedBook> getAllRentedBooks() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM rentedBooks;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<RentedBook> rentedBooks = new ArrayList<>();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("bookId");
                int memberId = resultSet.getInt("memberId");
                Date returnDate = resultSet.getDate("returnDate");
                RentedBook rentedBook = new RentedBook(bookId,memberId,returnDate);
                rentedBooks.add(rentedBook);
            }
            return rentedBooks;
        }
    }

    public void addRentedBook(RentedBook rentedBook) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "INSERT INTO rentedBooks VALUES (?,?,?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, rentedBook.getBookId());
            statement.setInt(2, rentedBook.getMemberId());
            statement.setDate(3, (Date) rentedBook.getReturnDate());
            statement.execute();
        }
    }

    public void removeRentedBook(RentedBook rentedBook) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "DELETE FROM rentedBooks WHERE bookId = ? AND memberID = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, rentedBook.getBookId());
            statement.setInt(2, rentedBook.getMemberId());
            statement.execute();
        }
    }

    public RentedBook getRentedBookById(int bookId) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM rentedBooks WHERE bookId = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int memberId = resultSet.getInt("memberId");
                Date returnDate = resultSet.getDate("returnDate");
                return new RentedBook(bookId, memberId, returnDate);
            } else {
                return null;
            }
        }
    }

}
