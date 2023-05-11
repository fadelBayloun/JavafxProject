package com.example.guiproject.DAO;

import com.example.guiproject.Models.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {

    Connection conn;
    public void addBook(Book b) throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "INSERT INTO books (name,category,publisher,status) VALUES (?,?,?,?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, b.getName());
            statement.setString(2, b.getCategory());
            statement.setString(3, b.getPublisher());
            statement.setInt(4, b.getStatus());
            statement.execute();
        }
    }
    public boolean removeBook(int id) throws SQLException {
        if(this.getBookById(id)!=null) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String query = "DELETE FROM books WHERE id = ?;";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.execute();
                return true;
            }
        }
        return false;
    }
    public ArrayList<Book> getBooks() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM books;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Book> Books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String publisher = resultSet.getString("publisher");
                int status = resultSet.getInt("status");
                Book b = new Book(id,name,category,publisher,status);
                Books.add(b);
            }
            return Books;
        }

    }
    public Book getBookById(int i) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM books WHERE id = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, i);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String publisher = resultSet.getString("publisher");
                int status = resultSet.getInt("status");
                return new Book(id, name, category,publisher,status);
            } else {
                return null;
            }
        }
    }
    public ArrayList<Book> getBooksByName(String s) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM books WHERE name = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1,s);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Book> Books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String publisher = resultSet.getString("publisher");
                int status = resultSet.getInt("status");
                Book b = new Book(id,name,category,publisher,status);
                Books.add(b);
            }
            return Books;
        }

    }
    public ArrayList<Book> getBooksByCategory(String s) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM books WHERE category = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1,s);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Book> Books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String publisher = resultSet.getString("publisher");
                int status = resultSet.getInt("status");
                Book b = new Book(id,name,category,publisher,status);
                Books.add(b);
            }
            return Books;
        }

    }

    public ArrayList<Book> getBooksByAuthor(String s) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM books WHERE publisher = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1,s);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Book> Books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String publisher = resultSet.getString("publisher");
                int status = resultSet.getInt("status");
                Book b = new Book(id,name,category,publisher,status);
                Books.add(b);
            }
            return Books;
        }

    }

    public  void rentBook(int id ) throws  SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "UPDATE books SET status=1 WHERE id=?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1,id);
            statement.execute();
        }
    }

    public  void returnBook (int id ) throws  SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "UPDATE books SET status=0 WHERE id=?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1,id);
            statement.execute();
        }
    }



}
