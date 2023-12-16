package org.example.adventkalenderslim.Settings;

//Class for managing all Settings
public class Settings {

    private Music _music = new Music();
    private CheatStar _star = new CheatStar();


    //Sets default settings
    public Settings(){

    }


    public Music musicOption(){return _music;} //Allows access to music options

    public CheatStar starOption(){return _star;} //Allows access to cheating(star) options
}
