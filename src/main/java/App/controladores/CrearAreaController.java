package App.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CrearAreaController {

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

        if () {

        }
    }
}
