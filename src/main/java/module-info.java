module com.example.albumsong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.albumsong to javafx.fxml;
    exports com.example.albumsong;
}