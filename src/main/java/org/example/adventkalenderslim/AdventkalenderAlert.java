package org.example.adventkalenderslim;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.adventkalenderslim.Settings.AlertType;


public class AdventkalenderAlert {

    private Stage _stage;
    private AlertType _type;
    private String _doorId;

    @FXML
    Label lbAlertTitle = new Label();
    @FXML
    Label lbAlertText = new Label();
    @FXML
    ImageView ivAlertLeft = new ImageView();
    @FXML
    ImageView ivAlertRight = new ImageView();

    public AdventkalenderAlert(String doorId,AlertType type) {
        this._type = type;
        this._doorId = doorId;



    }


    public void configure() {
        if(_type.equals(AlertType.Christkind)) {
            lbAlertTitle.setText("TÜRCHEN BLOCKIERT");
            lbAlertText.setText("Das Türchen wird durch die Macht des Christkindes versiegelt.\nGedulde dich noch etwas...");
            ivAlertLeft.setImage(new Image(String.valueOf(this.getClass().getResource("/images/christkind.png"))));
            ivAlertRight.setImage(new Image(String.valueOf(this.getClass().getResource("/images/christkind.png"))));
        }

        else if(_type.equals(AlertType.Tuerchen)) {
            lbAlertTitle.setText("TÜRCHEN VERGESSEN");
            lbAlertText.setText("Es scheint als ob du ein Türchen vergessen hast.\n PS: Es ist Türchen " + _doorId);
            setImages(new Image(String.valueOf(this.getClass().getResource("/images/forgot.png"))));
        }

        else if(_type.equals(AlertType.Betrueger)) {
            lbAlertTitle.setText("BETRÜGER!");
            lbAlertText.setText("Auch wenn der Stern verlocken wirkt, Schummeln ist auf keinen Fall die Lösung!");
            setImages(new Image(String.valueOf(this.getClass().getResource("/images/disappoint.jpg"))));
        }

        else if(_type.equals(AlertType.Dezember)) {
            lbAlertTitle.setText("ETWAS GEDULD");
            lbAlertText.setText("Es ist noch nicht einmal Dezember, gedulde dich und genieße die anderen Festtage!");
        }

        else if(_type.equals(AlertType.DataBase)) {
            lbAlertTitle.setText("Kritischer ERROR");
            lbAlertText.setText("Die Datenbank konnte nicht lokalisiert werden.\nBeende das Programm, um den Reparationsmodus einzuleiten.");
            setImages(new Image(String.valueOf(this.getClass().getResource("/images/error.png"))));
        }
    }
    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void setImages(Image image) {
        ivAlertRight.setImage(image);
        ivAlertLeft.setImage(image);
    }

    public void close() {
        _stage.close();
        if(_type.equals(AlertType.Betrueger) || _type.equals(AlertType.DataBase)){
            Platform.exit();
        }
    }

}
