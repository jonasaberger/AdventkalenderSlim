package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.adventkalenderslim.Settings.Settings;

import java.util.ArrayList;
import java.util.Date;

public class AdventkalenderController {

    //SETTINGS
    private Settings settings = new Settings();
    @FXML
    private ImageView btnMusic = new ImageView();


    @FXML
    private Pane _adventkalender = new Pane();
    private ArrayList<AdventkalenderDoor> _adventkalenderDoors = new ArrayList<>();







    public void initialize() {

        ImageView doorItems;
        System.out.println(_adventkalender.getChildren().getFirst());

        //Initialize the adventkalender
        for (Node door : _adventkalender.getChildren()) {
            //Set ID for each door
            door.setId(String.valueOf(_adventkalenderDoors.size()+1));

            //For all Doors except 24 -> cause Door-Fog layer is not same
            if(!(door.getId()).equals("24")) {
                _adventkalenderDoors.add(new AdventkalenderDoor((Pane)door,(ImageView) ((((Pane) door).getChildren()).get(1))));
            }
            else {
                System.out.println("24");
                _adventkalenderDoors.add(new AdventkalenderDoor((Pane)door,(ImageView) ((((Pane) door).getChildren()).get(2))));
            }
        }
    }



    @FXML
    protected void clickDoor(MouseEvent mouseEvent) {
        int doorId = Integer.valueOf(((Pane)(mouseEvent.getSource())).getId());

        _adventkalenderDoors.get(doorId);




    }







    //MUSIC-SETTINGS -> have to be in Controller for changing icons
    @FXML
    protected void toggleMusic() {
        //Is Muted => Unmute
        if(settings.musicOption().getMuted() == true) {
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/images/music-on.png"))));
            System.out.println("Music Playing!");
            settings.musicOption().unmute();
        }
        else {
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/images/music-off.png"))));
            System.out.println("Music Muted!");
            settings.musicOption().mute();
        }
    }


    //Closes Program
    public void exit() {
        Platform.exit();
    }
}