package org.example.adventkalenderslim;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AdventkalenderDoor {

    private Pane _door = new Pane();
    private ImageView _fog = new ImageView();



    public AdventkalenderDoor(Pane door, ImageView fog) {
        this._door = door;
        this._fog = fog;

    }
}
