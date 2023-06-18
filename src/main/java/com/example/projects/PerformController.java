package com.example.projects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PerformController implements Initializable {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    @FXML
    Button go_Back;
    private int rankno;

    @FXML
    TableView<PointsTable> tablePointsView;
    @FXML
    TableColumn<PointsTable, String> teamNameColumns;
    @FXML
    TableColumn<PointsTable, Integer> WinColumns;
    @FXML
    TableColumn<PointsTable, Integer> LossColumns;
    @FXML
    TableColumn<PointsTable, Integer> DrawColumn;
    @FXML
    TableColumn<PointsTable, Double> PointsColumn;
    ObservableList<PointsTable> pointsTable= FXCollections.observableArrayList();




    @FXML
    private  void handButtonClick(Event event)
    {

        ((Node)event.getSource()).getScene().getWindow().hide();
        CricController object = new CricController();
        object.loadStages("cric-view.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = DBConnetion.connector();
        setCellDataTable();
        loadDataFromTable();

    }
    private     void setCellDataTable()
    {

        teamNameColumns.setCellValueFactory(new PropertyValueFactory<PointsTable,String>("team"));
        WinColumns.setCellValueFactory(new PropertyValueFactory<PointsTable,Integer>("win"));
        LossColumns.setCellValueFactory(new PropertyValueFactory<PointsTable,Integer>("loss"));
        DrawColumn.setCellValueFactory(new PropertyValueFactory<PointsTable,Integer>("draw"));
        PointsColumn.setCellValueFactory(new PropertyValueFactory<PointsTable,Double>("points"));

         tablePointsView.setItems(pointsTable);

    }



    public void loadDataFromTable(){

        System.out.println("I'm in the Load database method");
        String sql ="SELECT * FROM  teamdetails ORDER BY points DESC";//SearchInstance will be the Search And All related Data will be printed

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                pointsTable.add(new PointsTable(rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5)) );

            }
        }
        catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data");
        }
        tablePointsView.setItems(pointsTable);
    }

    public void insertTeamDetail(String teamRegist,int win,int lose,int draw,double points) {


        try {

            System.out.println("B Ps= conn");

            String sql = "INSERT INTO teamdetails(teamname,win,loss,draw,points) VALUES(?,?,?,?,?);";
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

    void rankNo()
    {
        String sql="Select count() from teamdetails";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            rankno += rs.getInt(1);
            System.out.println("row Number in Teamdetails "+rankno);



        }
        catch (SQLException e) {


        System.err.println("Error in Teamdetails");
        }

    }

}


















