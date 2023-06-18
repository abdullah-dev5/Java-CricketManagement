package com.example.projects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Fixtures {
    private SimpleStringProperty TeamOne,TeamSecond,Date,Avenue,Results;
    private SimpleIntegerProperty matchNo;

    public String getTeamOne() {
        return TeamOne.get();
    }

    public SimpleStringProperty teamOneProperty() {
        return TeamOne;
    }

    public void setTeamOne(String teamOne) {
        this.TeamOne.set(teamOne);
    }

    public String getTeamSecond() {
        return TeamSecond.get();
    }

    public SimpleStringProperty teamSecondProperty() {
        return TeamSecond;
    }

    public void setTeamSecond(String teamSecond) {
        this.TeamSecond.set(teamSecond);
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty dateProperty() {
        return Date;
    }

    public void setDate(String date) {
        this.Date.set(date);
    }

    public String getAvenue() {
        return Avenue.get();
    }

    public SimpleStringProperty avenueProperty() {
        return Avenue;
    }

    public void setAvenue(String avenue) {
        this.Avenue.set(avenue);
    }

    public String getResults() {
        return Results.get();
    }

    public SimpleStringProperty resultsProperty() {
        return Results;
    }

    public void setResults(String results) {
        this.Results.set(results);
    }

    public int getMatchNo() {
        return matchNo.get();
    }

    public SimpleIntegerProperty matchNoProperty() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo.set(matchNo);
    }

    Fixtures(int matchNo, String teamOne, String teamSecond, String date, String avenue, String results)
    {
        this.matchNo =new SimpleIntegerProperty(matchNo);
        this.TeamOne =new SimpleStringProperty(teamOne);
        this.TeamSecond =new SimpleStringProperty(teamSecond);
        this.Date =new SimpleStringProperty(date);
        this.Avenue= new SimpleStringProperty(avenue);
        this.Results = new SimpleStringProperty(results);
    }
//default

    }

