package App.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class IniciarSesionController {

    public static String eMail = "dosoriom@unal.edu.co";
    public static String user = "dosoriom";
    public static String documento = "1007290916";
    public static String password = "estructuras";

    @FXML
    public Button closeButton;

    @FXML
    public TextField TextUsuarioCorreo;

    @FXML
    public PasswordField Clave;

    @FXML
    public Label WarningMessage;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void IniciarSesion(ActionEvent event) throws IOException {
        WarningMessage.setVisible(false);
        String usuario = TextUsuarioCorreo.getText().trim();
        String clave = Clave.getText();


        if (usuario.equals("") || clave.equals("")) {
            WarningMessage.setText("Los campos no pueden estar vacios");
            WarningMessage.setVisible(true);
            return;
        }



        if (usuario.contains("@")) {
            if (usuario.equals(eMail)) {
                if (clave.equals(password)) {

                } else {
                    WarningMessage.setText("Contraseña incorrecta");
                    WarningMessage.setVisible(true);
                    return;
                }
            } else {
                WarningMessage.setText("El usuario no existe");
                WarningMessage.setVisible(true);
                return;
            }
        } else if (usuario.equals(user)) {
            if (clave.equals(password)) {

            } else {
                WarningMessage.setText("Contraseña incorrecta");
                WarningMessage.setVisible(true);
                return;
            }
        } else if (usuario.equals(documento)) {
            if (clave.equals(password)) {

            } else {
                WarningMessage.setText("Contraseña incorrecta");
                WarningMessage.setVisible(true);
                return;
            }
        } else {
            WarningMessage.setText("El usuario no existe");
            WarningMessage.setVisible(true);
            return;
        }

        WarningMessage.setVisible(false);
        TextUsuarioCorreo.setText("");
        Clave.setText("");

        //se valida y luego se va a la siguiente ventana
        App.App.setRoot("menu_principal");
    }

}
