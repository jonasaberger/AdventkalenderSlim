package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.adventkalenderslim.Settings.Settings;

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