package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class AdventkalenderDoor {
    //Door Itself
    @FXML
    private Pane _door;
    @FXML
    private ImageView _fog;
    @FXML
    private ImageView _fogDoor = new ImageView();

    private Stage _stage;

    //Door Content & Body -> Information from Controller
    @FXML
    private Label lbDoorTitle;
    @FXML
    private ImageView ivDoorImage;
    @FXML
    private Label lbDoorText;
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

    public ImageView getFogDoor() {
        return _fogDoor;
    }
    //PHYSICAL FUNCTIONS
    public void setTitle(String title) {
        lbDoorTitle.setText(title);
    }
    public void setText(String text) {
        lbDoorText.setText(text);
    }
    public void setImage(Image image) {
        ivDoorImage.setImage(image);
    }
    public void setStage(Stage stage){this._stage = stage;}

    @FXML
    public void open(){
        _stage.show();}

    @FXML
    public void close() {
        _stage.close();
    }



    //SOFT-BACKEND FUNCTIONS
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
    public void setNotReady() {
        _isReady = false;
        _fog.setVisible(true);
        _fogDoor.setVisible(true);
    }
    public boolean getReady() {
        return _isReady;
    }
}
