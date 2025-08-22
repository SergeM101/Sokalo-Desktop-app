module com.sokalo.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.sokalo.main to javafx.fxml;
    exports com.sokalo.main;
}