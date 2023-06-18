package com.example.projects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class PointsTable {

private SimpleStringProperty team;
private  SimpleIntegerProperty win,loss,draw;
private SimpleDoubleProperty points;

    public String getTeam() {
        return team.get();
    }

    public SimpleStringProperty teamProperty() {
        return team;
    }

    public void setTeam(String team) {
        this.team.set(team);
    }

    public int getWin() {
        return win.get();
    }

    public SimpleIntegerProperty winProperty() {
        return win;
    }

    public void setWin(int win) {
        this.win.set(win);
    }

    public int getLoss() {
        return loss.get();
    }

    public SimpleIntegerProperty lossProperty() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss.set(loss);
    }

    public int getDraw() {
        return draw.get();
    }

    public SimpleIntegerProperty drawProperty() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw.set(draw);
    }

    public double getPoints() {
        return points.get();
    }

    public SimpleDoubleProperty pointsProperty() {
        return points;
    }

    public void setPoints(double points) {
        this.points.set(points);
    }

PointsTable(String team,int win,int loss,int draw,double points)
    {
    this.team= new SimpleStringProperty(team);
    this.win= new SimpleIntegerProperty(win);
    this.loss= new SimpleIntegerProperty(loss);
    this.draw= new SimpleIntegerProperty(draw);
    this.points = new SimpleDoubleProperty(points);

    }
}
