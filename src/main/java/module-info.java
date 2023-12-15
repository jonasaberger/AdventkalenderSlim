module org.example.adventkalenderslim {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.adventkalenderslim to javafx.fxml;
    exports org.example.adventkalenderslim;
}