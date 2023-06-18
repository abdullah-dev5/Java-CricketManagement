 package com.example.projects;

import javafx.fxml.FXML;
import javafx.event.Event;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

 public class CricController implements Initializable {


    @FXML
    private Button newTeam;

    @FXML
    private Button fixture;
    @FXML
    private Button performance;
    @FXML
    private Button teamSqaud;
    @FXML
    private Button go_Back;



    @FXML
    protected void onHelloButtonClick(Event events) {

        if (events.getSource() == newTeam) {
            ((Node)events.getSource()).getScene().getWindow().hide();//Hide Previous Window
            loadStages("newteam-view.fxml");

        } else if (events.getSource() == fixture) {
            ((Node)events.getSource()).getScene().getWindow().hide();

            loadStages("fix-view.fxml");
        } else if (events.getSource() == performance) {
            ((Node)events.getSource()).getScene().getWindow().hide();

            loadStages("perform-view.fxml");
        } else if (events.getSource() == teamSqaud) {
            ((Node)events.getSource()).getScene().getWindow().hide();

            loadStages("teamSquad-view.fxml");

        }
    }

  public  void loadStages(String fxml)  {

        {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(fxml));
                // FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("player-view.fxml"));
                //Scene scene = new Scene(fxmlLoader.load(), 400, 400);
                Stage stage = new Stage();
                //stage.setTitle("Hello!");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                System.err.println(e.getMessage());
               // e.printStackTrace();
            }

        }
    }

     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {


     }
 }
