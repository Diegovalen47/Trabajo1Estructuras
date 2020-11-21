module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires rt;

    opens App to javafx.fxml;
    opens App.controladores to javafx.fxml;
    exports App;
}