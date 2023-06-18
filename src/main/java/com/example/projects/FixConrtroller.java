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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FixConrtroller implements Initializable {



    private  Connection conn =null;
    private PreparedStatement ps = null;
    private ResultSet rs =null;
    private String resultsInstance;
    private String date="";
    private  int sizeofRows=1;//of table of Database


    @FXML
TableView< Fixtures> tablefixView;
    @FXML
    TableColumn<Fixtures,Integer>matchNoColumn;
    @FXML
TableColumn<Fixtures,String>team1Column;
    @FXML
    TableColumn<Fixtures,String>team2Column;
    @FXML
    TableColumn<Fixtures,String>dateColumn;
    @FXML
    TableColumn<Fixtures,String>avenueColumn;
    @FXML
    TableColumn<Fixtures,String>resultsColumn;
    @FXML
    TextField textmatchNo;
    @FXML
    TextField textTeam1;
    @FXML
    TextField textTeam2;
    @FXML
    DatePicker dp;
    @FXML
    TextField textAvenue;
    @FXML
    Button saveBtn;
    @FXML
    Button editResults;
    @FXML
    Button Refresh;
    @FXML
            Button go_Back;

    ObservableList<Fixtures> fixtures= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       conn = DBConnetion.connector();

        setCellDataTable();
        matchNo();
        loadDataFromDatabase();

    }


private  void setCellDataTable()
    {
        matchNoColumn.setCellValueFactory(new PropertyValueFactory<Fixtures,Integer>("matchNo"));
        team1Column.setCellValueFactory(new PropertyValueFactory<Fixtures,String>("teamOne"));
        team2Column.setCellValueFactory(new PropertyValueFactory<Fixtures,String>("teamSecond"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Fixtures,String>("date"));
        avenueColumn.setCellValueFactory(new PropertyValueFactory<Fixtures,String>("avenue"));
        resultsColumn.setCellValueFactory(new PropertyValueFactory<Fixtures,String>("results"));
        tablefixView.setItems(fixtures);

    }

    @FXML
    public void handleFixButtons(Event event) {
        if(event.getSource()==saveBtn)
        {
            LocalDate localeDate = dp.getValue();
             date =localeDate.toString();
            fixtures.add(new Fixtures(sizeofRows,textTeam1.getText(), textTeam2.getText(),date,textAvenue.getText(),"TBH"));

            //Data Inserted method
            insertfix(sizeofRows,textTeam1.getText(), textTeam2.getText(),date,textAvenue.getText(),resultsInstance);


       } else if (event.getSource()==editResults) {
            ((Node)event.getSource()).getScene().getWindow().hide();
            CricController c = new CricController();
            c.loadStages("editresult-view.fxml");
        }
        else if (event.getSource()==go_Back) {
            ((Node)event.getSource()).getScene().getWindow().hide();
            CricController object = new CricController();
            object.loadStages("cric-view.fxml");
        }
        else if (event.getSource() ==Refresh)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            CricController object = new CricController();
            object.loadStages("fix-view.fxml");

        }

    }
    public void insertfix(int match,String textTeam1, String textTeam2,String date, String textAvenue,String result)
    {

        conn = DBConnetion.connector();
        PreparedStatement ps =null;

        try {

            String sql="INSERT INTO fixtures(match,team1,team2,date,avenue,result) VALUES(?,?,?,?,?,?);";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,sizeofRows);
            ps.setString(2,textTeam1);
            ps.setString(3,textTeam2);
            ps.setString(4,date);
            ps.setString(5,textAvenue);
            ps.setString(6,result);
            ps.execute();
            System.out.println("Inserted Successfully");
        }
        catch(SQLException ex)
        {
            System.out.println("Problem : "+ex.getMessage());
        }


    }
    private  void loadDataFromDatabase()
    {
        System.out.println("I'm in the Load database method of Fixtures");
        String sql ="SELECT * FROM fixtures ";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                fixtures.add(new Fixtures(rs.getInt(1), rs.getString(2),""+rs.getString(3),""+rs.getString(4),rs.getString(5),rs.getString(6)) );

            }
        }
        catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data");
        }

        tablefixView.setItems(fixtures);


    }
    private void matchNo()
    {
        String quarry = "Select count() from fixtures ";
        try {
            ps =conn.prepareStatement(quarry);
            rs = ps.executeQuery();
             sizeofRows += rs.getInt(1);
            System.out.println("Rows Number in Fixutres "+sizeofRows);

        }
        catch (SQLException e) {
            System.err.println("Error in Fixutres getting size "+e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();

            }
            catch (SQLException e) {
                System.err.println("Error in Closing IN Fixtures MatchNo method "+e.getMessage());
            }
        }

    }
}






