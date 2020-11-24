package App.controladores;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class AdministracionController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxClase;

    @FXML
    public ChoiceBox choiceBoxAccion;

    @FXML
    public Label WarningMessage;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxClase.setItems(FXCollections.observableArrayList("Area", "Taller", "Personal"));
        choiceBoxAccion.setItems(FXCollections.observableArrayList("Ver", "Crear", "Editar", "Eliminar"));
    }




    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("menu_principal");
    }
}
