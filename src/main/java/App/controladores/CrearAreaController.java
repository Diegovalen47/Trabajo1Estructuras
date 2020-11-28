package App.controladores;

import App.Area;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.InputMismatchException;


public class CrearAreaController {

    public static long idArea = 3;

    @FXML
    public TextField TextTelefono;

    @FXML
    public TextField TextPersonaACargo;

    @FXML
    public Label WarningMessages;

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String telefono = TextTelefono.getText();
        String persona_a_cargo = TextPersonaACargo.getText();

        if(telefono.equals("") || persona_a_cargo.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        //Al crear no hay necesidad de verificar existencia ya que es con id autogenerado incremental
        try {
            long tel = Long.parseLong(telefono);
        } catch (NumberFormatException e) {
            WarningMessages.setText("El telefono deben ser un valor numerico");
            WarningMessages.setVisible(true);
            return;
        }

        Area area = new Area(idArea, persona_a_cargo, telefono);
        idArea++;

        TextTelefono.setText("");
        TextPersonaACargo.setText("");
        WarningMessages.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Area creada satisfactoriamente");
        alert.setHeaderText("Area ha sido creada satisfactoriamente");
        alert.setContentText(area.toString());


        alert.showAndWait();
    }
}
