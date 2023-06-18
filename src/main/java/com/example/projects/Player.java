package com.example.projects;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;


public class Player {

private SimpleStringProperty playerName,playerAge,playerID,playerDomiHand,playerRoll,teamName;


    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }


    public String getPlayerID() {
        return playerID.get();
    }

    public SimpleStringProperty playerIDProperty() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID.set(playerID);
    }

    public String getPlayerDomiHand() {
        return playerDomiHand.get();
    }

    public SimpleStringProperty playerDomiHandProperty() {
        return playerDomiHand;
    }

    public void setPlayerDomiHand(String playerDomiHand) {
        this.playerDomiHand.set(playerDomiHand);
    }

    public String getPlayerRoll() {
        return playerRoll.get();
    }

    public SimpleStringProperty playerRollProperty() {
        return playerRoll;
    }

    public void setPlayerRoll(String playerRoll) {
        this.playerRoll.set(playerRoll);
    }

    public String getPlayerAge() {
        return playerAge.get();
    }

    public SimpleStringProperty playerAgeProperty() {
        return playerAge;
    }

    public void setPlayerAge(String playerAge) {
        this.playerAge.set(playerAge);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public Player(String teamName)
    {
        this.teamName = new SimpleStringProperty(teamName);;
    }
    public Player(String playerName, String playerAge, String playerID, String  playerDomiHand, String playerRoll)
    {

        this.playerName = new SimpleStringProperty(playerName);
        this.playerAge = new SimpleStringProperty(playerAge);
        this.playerID= new SimpleStringProperty(playerID);
        this.playerDomiHand= new SimpleStringProperty(playerDomiHand);
        this.playerRoll= new SimpleStringProperty(playerRoll);
    }

}
