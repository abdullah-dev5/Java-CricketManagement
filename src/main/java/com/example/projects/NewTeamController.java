package com.example.projects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewTeamController implements Initializable {


    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;


    private String choicebox;
    private String teamName;
    @FXML
    private Button teamButton;
    @FXML
    private Button playerButton;


    @FXML
    private Button go_Back;


    @FXML
    private  TableView<Player> tableView;

    @FXML
    TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, String> ageColumn;
    @FXML
    private TableColumn<Player, String> iDColumn;
    @FXML
    private TableColumn<Player, String> dominantHandColumn;
    @FXML
    private TableColumn<Player, String> rollColumn;

    @FXML
    private  TextField addTeamName;

    @FXML
    private TextField addPlayerName;

    @FXML
    private TextField addAge;
    @FXML
    private TextField addID;
    @FXML
    private  TextField addDominantHand;

    @FXML
    private ChoiceBox<String> addRollCB;

    @FXML
    private CheckBox checkCap;
    @FXML


    private Boolean check=false;

    private ObservableList<String> listRoll = FXCollections.observableArrayList("AllRounder", "Batter", "Bowler");

    private ObservableList<Player> player = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnetion.connector();
        setCellDatatable();

       // loadDataFromTable();
    }

    private void setCellDatatable() {
        addRollCB.setItems(listRoll);
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("PlayerName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerAge"));
        iDColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerID"));
        dominantHandColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerDomiHand"));
        rollColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerRoll"));
        tableView.setItems(player);

    }

    @FXML
    public void handleButtons(Event event) {

        if (event.getSource() == playerButton) {
            player.add(new Player(addPlayerName.getText(), addAge.getText(), addID.getText(), addDominantHand.getText(), addRollCB.getValue()));

            insertPlayer(addPlayerName.getText(), addAge.getText(), addID.getText(), addDominantHand.getText(), addRollCB.getValue());
            System.out.println("Saved method called Successfully");
            addPlayerName.clear();
            addAge.clear();
            addID.clear();
            addDominantHand.clear();

        } else if (event.getSource() == teamButton) {


            playerButton.setDisable(false);
         teamName = addTeamName.getText();

            findTeam();
            System.out.println("Value of Check is "+check);
            if(check == false)
            {
                insertTeamDetail(teamName,0,0,0,0);
            }

        }
        // Choice Buttons rolls
        else if (event.getSource() == addRollCB.getValue()) {

            if (addRollCB.getValue() == "AllRounder") {
                choicebox = addRollCB.getValue();

            } else if (addRollCB.getValue() == "Batter") {
                choicebox = addRollCB.getValue();

            } else if (addRollCB.getValue() == "Bowler") {
                choicebox = addRollCB.getValue();
            }

        } else if (event.getSource()==go_Back) {
            //back buttons
            ((Node)event.getSource()).getScene().getWindow().hide();
            CricController object = new CricController();
            object.loadStages("cric-view.fxml");
        }

           else if (checkCap.isSelected())
          {//selecting captian checkbox
          addPlayerName.setText(addPlayerName.getText()+" "+" (C) ");


        }


    }


    public void insertPlayer(String playername, String age, String id, String domihand, String roll) {


         connection = DBConnetion.connector();
         ps = null;
        try {

            System.out.println("B Ps= conn");
            String sql = "INSERT INTO newteam(teamnamedb,playername, age,id,dominant,roll) VALUES(?,?,?,?,?,?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1, teamName);
            ps.setString(2, playername);
            ps.setString(3, age);
            ps.setString(4, id);
            ps.setString(5, domihand);
            ps.setString(6, roll);
            ps.execute();
            System.out.println("Inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Problem : " + ex.getMessage());
        }

    }

    public void findTeam() {
        System.out.println("I'm in theFind  database method of Perform");
        System.out.println("teamName: " + teamName);

        String sql = "SELECT teamregist FROM teamdetails Where teamregist LIKE ?";//SearchInstance will be the Search And All related Data will be printed

        System.out.println("Searching path is Okay");
        try {
            ps = connection.prepareStatement(sql);
            System.out.println("Okay?");
            ps.setString(1,teamName);
            System.out.println("Parameter is settled");

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("while Loop");
                String team = rs.getString(1);
                if (teamName.equals(team)) {
                    System.out.println("Already Exists");
                    check = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data");
        }

    }

    public void insertTeamDetail(String teamRegist,int win,int lose,int draw,double points) {


        try {

            System.out.println("B Ps= conn");

            String sql = "INSERT INTO teamdetails(teamregist,win,loss,draw,points) VALUES(?,?,?,?,?);";
            ps = connection.prepareStatement(sql);

            ps.setString(1, teamRegist);
            ps.setInt(2, win);
            ps.setInt(3,lose);
            ps.setInt(4, draw);
            ps.setDouble(5,points);

            ps.execute();
            System.out.println("Inserted Successfully");
        } catch (SQLException ex) {
            System.out.println("Problem : " + ex.getMessage());
        }


    }
    void printname(String name) {
    System.out.println("PrintName "+name);
    }

}


