package com.example.guiproject.Models;

import com.example.guiproject.DAO.MemberDAO;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Library {
    private ArrayList<Member> Members;
    private Time openingHour;
    private Time closingHour;

    public Library(ArrayList<Member> Members, Time openingHour, Time closingHour) {
        this.Members = Members;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
        notifyMembers();
    }

    public Time getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
        notifyMembers();
    }

    public void addMember(Member m) throws SQLException {
        Members.add(m);
        MemberDAO dao = new MemberDAO();
        dao.addMember(m);
    }

    public void removeMember(Member m) throws SQLException {
        Members.remove(m);
        MemberDAO dao = new MemberDAO();
        dao.removeMember(m.getId());
    }
    public void notifyMembers(){
        String s= "The new working hours are from: "+openingHour+ " till: "+ closingHour;
        for(Member m : Members){
            m.sendMessage(s);
        }

    }
    public void sendMessage(String s) {
        for (Member m : Members) {
            m.sendMessage(s);
        }
    }

}
