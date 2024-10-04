module com.example.filmlistesi {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;

    opens com.example.filmlistesi to javafx.fxml;
    exports com.example.filmlistesi;
}