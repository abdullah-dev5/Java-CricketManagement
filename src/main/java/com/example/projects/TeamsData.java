package com.example.projects;
import java.util.ArrayList;
import java.util.List;

public class TeamsData {

    private String name;
    private int points;

    public TeamsData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

}





