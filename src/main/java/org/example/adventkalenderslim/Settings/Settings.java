package org.example.adventkalenderslim.Settings;

//Class for managing all Settings
public class Settings {

    private Music _music = new Music();
    //TODO Add Cheating-Tool for doors

    //Sets default settings
    public Settings(){

    }


    public Music musicOption(){return _music;} //Allows access to music options
}
