package org.example.adventkalenderslim.Settings;

//TODO Maybe add Alert for the cheating star
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
        System.out.println("The Cheating-Star is shining...");
    }

    public void fade() {
        _status = false;
        System.out.println("The Cheating-Star is fading...");

    }


}
