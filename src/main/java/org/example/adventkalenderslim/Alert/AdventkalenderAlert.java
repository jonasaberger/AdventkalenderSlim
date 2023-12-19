package org.example.adventkalenderslim.Alert;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AdventkalenderAlert {

    private Stage _stage;


    public AdventkalenderAlert() {

    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public void open() {
        _stage.show();
    }

    @FXML
    public void close() {
        _stage.close();
    }

}
