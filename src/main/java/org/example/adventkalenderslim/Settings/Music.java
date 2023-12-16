package org.example.adventkalenderslim.Settings;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.adventkalenderslim.AdventkalenderApplication;
import org.example.adventkalenderslim.AdventkalenderController;

import java.io.File;

public class Music {
    private boolean _isMuted;
    private Media _music = new Media(new File("src/main/resources/sounds/jinglebells-bm.mp3").toURI().toString());
    private MediaPlayer _mediaPlayer = new MediaPlayer(_music);

    public Music() {
        _mediaPlayer.play();
        _mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mute();
    }

    public void play() {
        _mediaPlayer.play();
    }

    public void stop() {
        _mediaPlayer.stop();
    }

    public void unmute(){
        _isMuted = false;
        _mediaPlayer.setMute(false);
    }

    public void mute() {
        _isMuted = true;
        _mediaPlayer.setMute(true);
    }

    public boolean getMuted(){return this._isMuted;}
}
