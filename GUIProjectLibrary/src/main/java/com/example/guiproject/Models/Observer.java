package com.example.guiproject.Models;

import java.sql.SQLException;

public interface Observer {
    public void getNotified(Notification notification) throws SQLException;
}
