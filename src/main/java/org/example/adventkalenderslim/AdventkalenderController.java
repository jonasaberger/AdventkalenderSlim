package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.adventkalenderslim.Settings.Settings;

import java.io.FileInputStream;

public class AdventkalenderController {

    private Settings settings = new Settings();



    @FXML
    private ImageView btnMusic = new ImageView();





    public void initialize() {

    }


    //MUSIC-SETTINGS
    @FXML
    protected void toggleMusic() {
        //Is Muted => Unmute
        if(settings.musicOption().getMuted() == true) {
            settings.musicOption().setMuted(false);
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/music-on.png"))));
        }
        else {
            settings.musicOption().setMuted(true);
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/music-off.png"))));
        }
    }



    //Closes Program
    public void exit() {
        Platform.exit();
    }

}