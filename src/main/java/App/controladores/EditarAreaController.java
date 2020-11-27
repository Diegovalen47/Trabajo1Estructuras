package App.controladores;

import App.Area;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarAreaController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxAreas;

    @FXML
    public Label labelTelefono;

    @FXML
    public Label labelPersona;

    @FXML
    public Label WarningMessages;

    @FXML
    public TextField TextTelefono;

    @FXML
    public TextField TextPersonaACargo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());
        if (idArea == null || idArea.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }
        labelTelefono.setVisible(true);
        labelPersona.setVisible(true);
        TextTelefono.setText(String.valueOf(Area.AreaIds.get(Integer.parseInt(idArea)).telefono));
        TextPersonaACargo.setText(Area.AreaIds.get(Integer.parseInt(idArea)).persona_a_cargo);
        TextTelefono.setVisible(true);
        TextPersonaACargo.setVisible(true);
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());



    }

}
