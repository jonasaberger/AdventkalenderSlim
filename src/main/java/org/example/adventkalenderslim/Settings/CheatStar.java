package org.example.adventkalenderslim.Settings;

//TODO Maybe add AdventAlert for the cheating star
public class CheatStar {

    private boolean _status;
    public CheatStar() {
        _status = false;
    }
    public boolean getStatus() {
        return _status;
    }
    public void shine() {
        _status = true;
        System.out.println("The Cheating-Stars light is shining bright...");
    }
    public void fade() {
        _status = false;
        System.out.println("The light of the Cheating-Star is fading...");
    }
}
