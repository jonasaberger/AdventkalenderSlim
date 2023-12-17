package org.example.adventkalenderslim;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AdventkalenderDoor {

    private Pane _door = new Pane();
    private ImageView _fog = new ImageView();
    private ImageView _fogDoor = new ImageView();
    private boolean _isReady;



    //For 1-23
    public AdventkalenderDoor(Pane door, ImageView fog) {
        this._door = door;
        this._fog = fog;
    }

    //For 24
    public AdventkalenderDoor(Pane door, ImageView fog, ImageView fogDoor) {
        this._door = door;
        this._fog = fog;
        this._fogDoor = fogDoor;
    }

    public Pane getDoor() {
        return _door;
    }

    public ImageView getFogDoor() {
        return _fogDoor;
    }

    //Opens the Door with the whole alert thingy -> first open
    public void openDoor() {
        _door.setVisible(false);
    }

    //Just removes the Door silently
    public void removeDoor() {
        _door.setVisible(false);
    }

    public ImageView getFog() {
        return _fog;
    }

    public void setReady() {
        _isReady = true;
        _fog.setVisible(false);
        _fogDoor.setVisible(false);
    }
}
