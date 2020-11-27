package App.controladores;

import App.Area;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        WarningMessages.setVisible(false);
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());
        String nuevo_telefono = TextTelefono.getText();
        String nueva_persona = TextPersonaACargo.getText();

        if (nueva_persona.equals("") || nuevo_telefono.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if (nuevo_telefono.equals(String.valueOf(Area.AreaIds.get(Integer.parseInt(idArea)).telefono)) && nueva_persona.equals(Area.AreaIds.get(Integer.parseInt(idArea)).persona_a_cargo)) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }

        Area.AreaIds.get(Integer.parseInt(idArea)).telefono = Integer.parseInt(nuevo_telefono);
        Area.AreaIds.get(Integer.parseInt(idArea)).persona_a_cargo= nueva_persona;
        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
        labelTelefono.setVisible(false);
        labelPersona.setVisible(false);
        WarningMessages.setVisible(false);
        TextTelefono.setVisible(false);
        TextPersonaACargo.setVisible(false);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Area editada satisfactoriamente");
        alert.setHeaderText("Area ha sido editada satisfactoriamente");
        alert.setContentText(Area.AreaIds.get(Integer.parseInt(idArea)).toString());


        alert.showAndWait();

    }

}
