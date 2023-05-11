package com.example.guiproject.DAO;
import com.example.guiproject.Models.Library;
import com.example.guiproject.Models.Member;

import java.sql.*;
import java.util.ArrayList;

public class LibraryDAO {
    Connection conn;
    public Library getLibrary(int i) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "SELECT * FROM schedules";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Time opens = resultSet.getTime("opens");
                Time closes = resultSet.getTime("closes");
                MemberDAO memberdao = new MemberDAO();
                ArrayList<Member> Members = new ArrayList<Member>();
                Members = memberdao.getMembers();
                Library l = new Library(Members,opens,closes);
                return l;

            } else {
                return null;
            }
        }
    }

    public void updateOpenHour(Time t) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "UPDATE library SET opens=?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setTime(1,t);
            statement.execute();
        }


    }

    public void updateCloseHour(Time t)throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        String query = "UPDATE library SET closes=?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setTime(1,t);
            statement.execute();
        }
    }

}
