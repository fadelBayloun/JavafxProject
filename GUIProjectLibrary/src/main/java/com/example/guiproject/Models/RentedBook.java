package com.example.guiproject.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class RentedBook {
    private int bookId;
    private int memberId;
    private Date returnDate;


    public RentedBook(int bookId, int memberId, Date returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public IntegerProperty bookIdProperty() {
        return new SimpleIntegerProperty(bookId);
    }


}
