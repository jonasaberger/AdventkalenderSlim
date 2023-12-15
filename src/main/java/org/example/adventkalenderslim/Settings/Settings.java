package org.example.adventkalenderslim.Settings;

//Class for managing all Settings
public class Settings {

    private Music _music = new Music();

    //Sets default settings
    public Settings(){
        _music.setMuted(true);
    }


    public Music musicOption(){return _music;} //Allows access to music options
}
