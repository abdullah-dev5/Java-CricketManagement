package com.example.projects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
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

public class TeamSquadController implements Initializable {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private  String searchInst;



   @FXML
    Button seachBtn;
   @FXML
   Button go_Back;
    @FXML
    Button clearBtn;
    @FXML
    TextField txtSearch;
   @FXML
   TableView<Player> tableView;

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
    private ObservableList<Player> player = FXCollections.observableArrayList();

    @FXML
    private void handleButton(Event  event)
   {
        if(event.getSource()==seachBtn)
        {
            searchInst=txtSearch.getText();
            loadDataFromTable(searchInst);
        }
        else if(event.getSource()==clearBtn)
        {
            tableView.getItems().clear();
        } else if (event.getSource()==go_Back) {

            ((Node)event.getSource()).getScene().getWindow().hide();
            CricController object = new CricController();
            object.loadStages("cric-view.fxml");

        }
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBConnetion.connector();
     setCellDatatable();

    }
    private void setCellDatatable() {
        System.out.println("In Set");
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("PlayerName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerAge"));
        iDColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerID"));
        dominantHandColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerDomiHand"));
        rollColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerRoll"));
        tableView.setItems(player);
        System.out.println("In Set last");
    }

    public void loadDataFromTable(String team){

        System.out.println("I'm in the Load database method");
        String sql ="SELECT * FROM  newteam Where teamnamedb =?";//SearchInstance will be the Search And All related Data will be printed

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,team);
            rs = ps.executeQuery();
            while(rs.next())
            {
                player.add(new Player(rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)) );

            }
        }
        catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data");
        }
        tableView.setItems(player);
    }

}
