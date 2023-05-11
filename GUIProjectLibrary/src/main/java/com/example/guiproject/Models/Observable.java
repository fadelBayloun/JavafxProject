package com.example.guiproject.Models;

import java.sql.SQLException;

public interface Observable {
    public void notifyAllStaff (Notification notification) throws SQLException;
}





