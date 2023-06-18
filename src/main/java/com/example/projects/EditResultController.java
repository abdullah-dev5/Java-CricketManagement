package com.example.projects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditResultController implements Initializable {

    private  String finalEdits ="" ;
     private Connection connection = null;
   private PreparedStatement ps = null;
    private ResultSet rs = null;

    private String teamA;
    private String teamB;
    private  double updatePoints;
    private  int indexDataFetched;
@FXML
Button go_back;
    @FXML
    Button saveEditButton ;
    @FXML
    Button loginbtn;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    TextField txtMatch;

    @FXML
    ChoiceBox<String> selectCB;
    int match;
    ObservableList<String> selected = FXCollections.observableArrayList("Won", "Loss", "Draw", "Abondoned");

    @FXML
    public void loginButton(Event event) {
        try {
            if (event.getSource() == loginbtn) {
                System.out.println("LoginWorking");

                if (txtUsername.getText().equals("Admin") && txtPassword.getText().equals("pass"))
                    selectCB.setDisable(false);
                else {
                    System.out.println("Wrong username or password");
                }
            }
            else if(event.getSource()==selectCB.getValue()) {

                if (selectCB.getValue()=="Won") {
                    finalEdits = "Won";
                    System.out.println("" + finalEdits);
                } else if (selectCB.getValue().equals("Loss")) {
                    finalEdits = "Loss";
                } else if (selectCB.getValue().equals("Draw")) {
                    finalEdits = "Draw";

                } else if (selectCB.getValue().equals("Abondoned")) {
                    finalEdits = "Abondoned";

                }
            }
            else if (event.getSource()==saveEditButton)
            {
                match= Integer.parseInt(txtMatch.getText());
                System.out.println(""+finalEdits);
                updateresults(selectCB.getValue());
                findTeam();
                TeamAData();
                TeamBData();
            }
            else if (event.getSource()== go_back)
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
                CricController object = new CricController();
                object.loadStages("fix-view.fxml");
            }
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnetion.connector();
        selectCB.setItems(selected);

    }


    private void updateresults(String result) {
        PreparedStatement ps = null;
        try {
            System.out.println("Im Called update");
            System.out.println("The values of "+result+" And "+match);
//            String sql ="Update fixtures SET result = "+match+" WHERE match="+match;
            String sql = "UPDATE fixtures SET result = ? WHERE match=?";
            System.out.println("The values of After sql");
//
            ps = connection.prepareStatement(sql);
            System.out.println("The values of sql");
//
            ps.setString(1,result);
            System.out.println("The values of setStrin");
//
            ps.setInt(2,match);
            ps.execute();
            System.out.println("Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
     /*   finally {
            try {
                ps.close();
                connection.close();
                finalEdits="";
                match=0;
            }catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
*/
    }

    //Table Fixtures of Database to fetch Teams Names on that Match NUmbers.
    public void findTeam() {
        System.out.println("I'm in theFind  database method of EditResults");


        String sql = "SELECT * FROM fixtures Where match= ?";//SearchInstance will be the Search And All related Data will be printed

        System.out.println("Searching path is Okay");
        try {
            ps = connection.prepareStatement(sql);
            System.out.println("Okay?");
            ps.setInt(1,match);
            System.out.println("Parameter is settled ");
            System.out.println("MAtch No "+match);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("while Loop");
                teamA=rs.getString(2);
                System.out.println("Team A "+teamA);
                teamB=rs.getString(3);
                System.out.println("Team B "+teamB);
            }
        } catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data");
        }

    }



    void TeamAData()
    {
        if(selectCB.getValue().equals("Won"))
        {

            select(teamA,2);
            updatePoints+=3;
            System.out.println("No of matches before Update (win) "+indexDataFetched);

            indexDataFetched++;
            System.out.println("Points are  in mehtod of TeamA "+updatePoints);
            System.out.println("Yesss "+indexDataFetched);
                updateTeamDetails(teamA);
            updateTeamDetailsCols("win",teamA);

        }
        else if(selectCB.getValue().equals("Loss"))
        {
            select(teamA,3);
            updatePoints-=2.5;
            System.out.println("No of matches before Update (loss) "+indexDataFetched);

            indexDataFetched++;
            System.out.println("Points are  in mehtod of TeamA "+updatePoints);
            System.out.println("Yesss "+indexDataFetched);
            updateTeamDetails(teamA);
            updateTeamDetailsCols("loss",teamA);

        }
        else if(selectCB.getValue().equals("Draw"))
        {
            select(teamA,4);
            updatePoints+=1.5;
            System.out.println("No of matches before Update (draw) "+indexDataFetched);

            indexDataFetched++;
            System.out.println("Points are  in mehtod of TeamA "+updatePoints);
            System.out.println("Yesss "+indexDataFetched);
            updateTeamDetails(teamA);
            updateTeamDetailsCols("draw",teamA);

        }


        //Code here logic
    }
    void TeamBData()
    {
        if(selectCB.getValue().equals("Won"))
        {

            select(teamB,3);
            System.out.println("No of matches before Update (loss) "+indexDataFetched);

            updatePoints-=2.5;
            indexDataFetched++;
            System.out.println("Points are  in mehtod of TeamB "+updatePoints);
            System.out.println("Yesss "+indexDataFetched);
            updateTeamDetails(teamB);
            updateTeamDetailsCols("loss",teamB);

        }
        else if(selectCB.getValue().equals("Loss"))
        {
            select(teamB,2);

            updatePoints+=3;
            System.out.println("No of matches before Update (Won) "+indexDataFetched);

            indexDataFetched++;
            System.out.println("Points are in mehtod of TeamB "+updatePoints);
            System.out.println("Yesss "+indexDataFetched);
            updateTeamDetails(teamB);
            updateTeamDetailsCols("win",teamB);

        }
        else if(selectCB.getValue().equals("Draw"))
        {
            select(teamB,4);
            updatePoints+=1.5;
            System.out.println("No of matches before Update (draw) "+indexDataFetched);
            indexDataFetched++;
            System.out.println("Points are in mehtod of TeamB "+updatePoints);
            System.out.println("No of matches "+indexDataFetched);
            updateTeamDetails(teamB);
            updateTeamDetailsCols("draw",teamB);

        }


        //Code here logic
    }

    void select(String teamRegistSearch,int index )
    {

        String sql = "SELECT * FROM teamdetails Where teamregist= ?";

        System.out.println("Searching path is Okay");
        try {
            ps = connection.prepareStatement(sql);
            System.out.println("Okay?");
            ps.setString(1,teamRegistSearch);
            System.out.println("Parameter is settled ");
            System.out.println("Team passes to select "+teamRegistSearch);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("while Loop");
                indexDataFetched=rs.getInt(index);
                System.out.println("Index Fetch"+indexDataFetched);
                updatePoints=rs.getDouble(5);
                System.out.println("points fetch "+updatePoints);
            }
        } catch (SQLException e) {
            System.out.println("Error while executing And Fetching Data for Index fetching And Points From table teamdetails");

        }
    }



    private void updateTeamDetails(String nameTeam) {
        try {
            System.out.println("Im Called update of Teams Detail");
            System.out.println("The values of  " + updatePoints);

//            String sql = "UPDATE teamdetails SET points = ? AND "+cloumns+"=? WHERE teamregist=?";
            String sql = "UPDATE teamdetails SET points = ? WHERE teamregist=?";

            System.out.println("The values of After sql");
          System.out.println(" team Name "+nameTeam);
            ps = connection.prepareStatement(sql);
            System.out.println("The values of sql");

            ps.setDouble(1,updatePoints);
            System.out.println("The values of points "+updatePoints);


            ps.setString(2, nameTeam);
            ps.executeUpdate();
            System.out.println("Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Whether Win/loss/draw update no of win/loss/draws
    private void updateTeamDetailsCols(String cloumns,String nameTeam) {
        try {
            System.out.println("Im Called update of Teams Detail");
            System.out.println("The values of " +cloumns );

//            String sql = "UPDATE teamdetails SET points = ? AND "+cloumns+"=? WHERE teamregist=?";
            String sql = "UPDATE teamdetails SET "+cloumns+" = ? WHERE teamregist=?";

            System.out.println("The values of After sql");
            System.out.println("Columns Name is "+ cloumns+" And  team Name "+nameTeam);
            ps = connection.prepareStatement(sql);

            ps.setInt(1,indexDataFetched);
            //        ps.setInt(2,indexDataFetched);
            System.out.println("The values of index is "+indexDataFetched);

            ps.setString(2, nameTeam);
            ps.executeUpdate();
            System.out.println("Updated Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        updatePoints =0;
        indexDataFetched=0;
        System.out.println("Values are reset ! updatePoints "+updatePoints+" index Fetched "+indexDataFetched);
    }

}