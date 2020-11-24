module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jgrapht.core;

    opens App to javafx.fxml;
    opens App.controladores to javafx.fxml;
    exports App;
}