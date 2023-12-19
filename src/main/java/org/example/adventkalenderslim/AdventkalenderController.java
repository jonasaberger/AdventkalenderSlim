package org.example.adventkalenderslim;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.adventkalenderslim.Settings.AlertType;
import org.example.adventkalenderslim.Settings.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdventkalenderController {

    //SETTINGS
    private Settings settings = new Settings();
    @FXML
    private ImageView btnMusic = new ImageView();
    @FXML
    private ImageView btnStar = new ImageView();
    @FXML
    private ImageView btnSnow = new ImageView();
    @FXML
    private ImageView ivBackground = new ImageView();


    @FXML
    private AnchorPane _main = new AnchorPane();


    @FXML
    private Pane _adventkalender = new Pane();
    private ArrayList<AdventkalenderDoor> _adventkalenderDoors = new ArrayList<>();
    private String _currentDoor = "1";
    //private String _currentTime = new SimpleDateFormat("dd.MM").format(Calendar.getInstance().getTime());
    private String currentDay = (new SimpleDateFormat("dd.MM").format(Calendar.getInstance().getTime()).split("\\.")[0]);
    private String currentMonth = (new SimpleDateFormat("dd.MM").format(Calendar.getInstance().getTime()).split("\\.")[1]);

    public void initialize() {


        //Initialize the adventkalender
        for (Node door : _adventkalender.getChildren()) {
            //Set ID for each door
            door.setId(String.valueOf(_adventkalenderDoors.size()+1));

            //For all Doors except 24 -> cause Door-Fog layer is not same
            if(!(door.getId()).equals("24")) {
                _adventkalenderDoors.add(new AdventkalenderDoor((Pane)door,(ImageView) ((((Pane) door).getChildren()).get(1))));
            }
            else {
                _adventkalenderDoors.add(new AdventkalenderDoor((Pane)door,(ImageView) ((((Pane) door).getChildren()).get(2)),(ImageView) ((((Pane) door).getChildren()).get(3))));
            }
        }



        //"Unfog" the available doors
        //Check if its even December
        if(currentMonth.equals("12")) {
            //Unfog all the current doors
            for(int i = 1; i <= Integer.valueOf(currentDay); i++) {
                _adventkalenderDoors.get(i-1).setReady();
            }

        }
        else {
            //Make its not December-Exception AdventAlert
            //Unfog all the current doors
            for(int i = 1; i <= Integer.valueOf(currentDay); i++) {
                _adventkalenderDoors.get(i-1).setNotReady();
            }
            createAlert(AlertType.Dezember);
        }

        //Check if the db is present
        File f = new File("./src/main/resources/data/db.txt");
        if(f.exists() && !f.isDirectory()) {
            //DB present
            _currentDoor = readDoorNumber();

            //Check for Snow
            if(Integer.valueOf(_currentDoor) < 5){btnSnow.setVisible(false);}

            //Anti-Manipulation Check
            if(Integer.valueOf(_currentDoor) > 24 || Integer.valueOf(_currentDoor) > (Integer.valueOf(currentDay)+1)) {
                //"Christkind-Betrüger" Exception
                createAlert(AlertType.Betrueger);
                resetData();
            }
            else {

                for(int i = 0; i < Integer.valueOf(_currentDoor)-1; i++) {
                    _adventkalenderDoors.get(i).removeDoor();
                }
            }
        }
        else
        {
            //Throw db not located Exception
            createAlert(AlertType.DataBase);
            resetData();
        } //Database not present -> creating default one
    }


    @FXML
    protected void clickDoor(MouseEvent mouseEvent) {
        int doorId = Integer.valueOf(((Pane)(mouseEvent.getSource())).getId());
        AdventkalenderDoor clickedDoor = _adventkalenderDoors.get(doorId-1);

        //Update Snowfall
        if(Integer.valueOf(_currentDoor) >= 5) {
            btnSnow.setVisible(true);
        }
        else {
            btnSnow.setVisible(false);
        }

        //Do the normal checking-routine
            //Check if all doors are opened
            if(doorId == Integer.valueOf(_currentDoor) || doorId == Integer.parseInt(_currentDoor) || settings.starOption().getStatus()) {

                //Create and Set Controller to the clicked-door
                createDoor(clickedDoor);

                //Get Information for specific Door from file -> Text + Image
                clickedDoor.setTitle("Tuerchen " + doorId);
                clickedDoor.setText(readDoorContent(doorId));
                try {clickedDoor.setImage(new Image(String.valueOf(this.getClass().getResource(("/images/door-content/" + doorId + ".jpg")))));}
                catch(Exception e) {System.out.println("Door-Image not found!");}

                clickedDoor.open(); //Open the clicked door

                clickedDoor.removeDoor();
                _currentDoor = String.valueOf((Integer.valueOf(_currentDoor)+1));
                writeDoorNumber(); //Update DoorNumber
            }
            else {
                //Protected by Power of "Christkind" AdventAlert
                if(!_adventkalenderDoors.get(doorId-1).getReady()) {
                    createAlert(AlertType.Christkind);
                }
                else {
                    //Forgot Door AdventAlert
                    createAlert(AlertType.Tuerchen);
                }
            }

    }


    @FXML
    protected String readDoorContent(int index) {

        File content = new File("./src/main/resources/data/content.txt");
        if(content.exists() && !content.isDirectory()) {
            try {
                FileReader fileReader = new FileReader(content);
                int nextChar;
                String fString = ""; //Full-String
                String pString []; //Part-String -> String with all the index parts

                while((nextChar = fileReader.read()) != -1) {
                    fString += ((char)nextChar);
                }
                pString = fString.split(".*\\n");
                fileReader.close();
                return pString[index-1];
            }
            catch(IOException ioException) {
                System.out.println("An IO-Exception was thrown! [readData]");
            }
        }
        System.out.println("Couldn't locate door-content file [readDoorContent]");
        return "There was an ERROR trying to load the Door-Content...";
    }

    @FXML
    protected void createDoor(AdventkalenderDoor clickedDoor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adventkalender-popup.fxml"));
            Stage stage = new Stage();
            fxmlLoader.setController(clickedDoor);
            Scene scene = new Scene(fxmlLoader.load(),500,333);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            clickedDoor.setStage(stage);
        }
        catch(IOException e) {
            System.out.println("CANT LOAD TÜRCHEN");
            e.printStackTrace();
        }
    }

    protected void createAlert(AlertType alertType) {
        try {
            AdventkalenderAlert adventkalenderAlert = new AdventkalenderAlert(_currentDoor, alertType);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adventkalender-alert.fxml"));
            Stage stage = new Stage();

            fxmlLoader.setController(adventkalenderAlert);
            Scene scene = new Scene(fxmlLoader.load(),300,200);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            adventkalenderAlert.setStage(stage);
            stage.setAlwaysOnTop(true);
            adventkalenderAlert.configure();
            stage.show();
        }
        catch(IOException e) {
            System.out.println("CANT LOAD ALERT");
            e.printStackTrace();
        }
    }

    //Writes next to open door in DB
    @FXML
    protected void writeDoorNumber() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/data/db.txt",false);
            fileWriter.write(_currentDoor);
            fileWriter.close();
        }
        catch(IOException ioException) {
            System.out.println("An IO-Exception was thrown! [writeData]");
        }
    }

    //Reads DB for the next door
    @FXML
    protected String readDoorNumber() {
        try {
            FileReader fileReader = new FileReader("./src/main/resources/data/db.txt");
            int nextChar;
            String fString = "";

            while((nextChar = fileReader.read()) != -1) {
                fString += ((char)nextChar);
            }
            return fString;
        }
        catch(IOException ioException) {
            System.out.println("An IO-Exception was thrown! [readData]");
        }
        return "ERROR";
    }

    //Initializes / Resets DB-Data
    @FXML
    protected void resetData() {
        System.out.println("RESETTINGG...");
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/data/db.txt",false);
            fileWriter.write("1");
            fileWriter.close();
        }
        catch(IOException ioException) {
            System.out.println("An IO-Exception was thrown! [resetData]");
        }
    }
    //CHEATING-STAR -> activate the cheat-mode
    @FXML
    protected void toggleStar() {
        //Is Shining => Fade
        if(settings.starOption().getStatus()) {
            settings.starOption().fade();
            btnStar.setOpacity(0);
            _main.setCursor(new ImageCursor());
            for(AdventkalenderDoor door : _adventkalenderDoors) {
                door.getFog().setCursor(Cursor.NONE);
                door.getFogDoor().setCursor(Cursor.OPEN_HAND);
            }

        }
        else { //Is Faded => Shine
            settings.starOption().shine();
            _currentDoor = "666"; //If Program is restarted -> called out as cheater
            writeDoorNumber();
            btnStar.setOpacity(1);
            _main.setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
            for(AdventkalenderDoor door : _adventkalenderDoors) {
                door.getFog().setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
                door.getFogDoor().setCursor(new ImageCursor(new Image(String.valueOf(this.getClass().getResource("/images/cheating-star.png"))),24,24));
            }
        }
    }
    @FXML
    protected void toggleSnow() {

        if(ivBackground.getImage().getUrl().equals(String.valueOf(this.getClass().getResource("/images/snowing-gif.gif")))) {
            ivBackground.setImage(new Image(String.valueOf(this.getClass().getResource("/images/background.jpg"))));
        }
        else {
            ivBackground.setImage(new Image(String.valueOf(this.getClass().getResource("/images/snowing-gif.gif"))));
        }
    }

    //MUSIC-SETTINGS -> have to be in Controller for changing icons
    @FXML
    protected void toggleMusic() {
        //Is Muted => Unmute
        if(settings.musicOption().getMuted() == true) {
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/images/music-on.png"))));
            System.out.println("Music Playing!");
            settings.musicOption().unmute();
        }
        else {
            btnMusic.setImage(new Image(String.valueOf(this.getClass().getResource("/images/music-off.png"))));
            System.out.println("Music Muted!");
            settings.musicOption().mute();
        }
    }

    //Closes Program
    public void exit() {
        Platform.exit();
    }
}