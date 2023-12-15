module org.example.adventkalenderslim {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens org.example.adventkalenderslim to javafx.fxml;
    exports org.example.adventkalenderslim;
    exports org.example.adventkalenderslim.Settings;
    opens org.example.adventkalenderslim.Settings to javafx.fxml;
}