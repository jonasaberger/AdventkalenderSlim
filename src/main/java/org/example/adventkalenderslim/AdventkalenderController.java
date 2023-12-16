package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.adventkalenderslim.Settings.CheatStar;
import org.example.adventkalenderslim.Settings.Settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdventkalenderController {

    //SETTINGS
    private Settings settings = new Settings();
    @FXML
    private ImageView btnMusic = new ImageView();
    @FXML
    private ImageView btnStar = new ImageView();



    @FXML
    private AnchorPane _main = new AnchorPane();
    @FXML
    private Pane _adventkalender = new Pane();
    private ArrayList<AdventkalenderDoor> _adventkalenderDoors = new ArrayList<>();
    private String _currentTime = new SimpleDateFormat("dd.MM").format(Calendar.getInstance().getTime());


    public void initialize() {

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
                _adventkalenderDoors.add(new AdventkalenderDoor((Pane)door,(ImageView) ((((Pane) door).getChildren()).get(2)),(ImageView) ((((Pane) door).getChildren()).get(3))));
            }
        }

        //"Unfog" the available doors
        //Check if its even December
        if(((_currentTime.split("\\."))[1]).equals("12")) {
            //Unfog all the current doors
            for(int i = 1; i <= Integer.valueOf(_currentTime.split("\\.")[0]); i++) {
                _adventkalenderDoors.get(i-1).setReady();
            }
        }
        else {
            //TODO Make its not December-Exception Alert
        }


        //TODO Open the already opened doors -> DB
    }



    @FXML
    protected void clickDoor(MouseEvent mouseEvent) {
        int doorId = Integer.valueOf(((Pane)(mouseEvent.getSource())).getId());

        //Cheat-Star is active
        if(settings.starOption().getStatus()) {
            _adventkalenderDoors.get(doorId-1).setReady();
        }
        else { //Do the normal checking-routine

        }
    }


    //CHEATING-STAR -> activate the cheat-mode
    @FXML
    protected void toggleStar() {
        //Is Shining => Fade
        if(settings.starOption().getStatus()) {
            settings.starOption().fade();
            btnStar.setOpacity(0);
            _main.setCursor(new ImageCursor());
        }
        else { //Is Faded => Shine
            settings.starOption().shine();
            btnStar.setOpacity(1);
            _main.setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
            for(AdventkalenderDoor door : _adventkalenderDoors) {
                door.getFog().setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
                door.getFogDoor().setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
            }
        }
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