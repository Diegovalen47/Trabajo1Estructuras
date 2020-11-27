package App.controladores;

import App.Area;
import App.Personal;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarPersonalController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxPersonal;

    @FXML
    public Label labelCedula;

    @FXML
    public Label labelSueldo;

    @FXML
    public Label labelHorario;

    @FXML
    public Label WarningMessages;

    @FXML
    public TextField TextCedula;

    @FXML
    public TextField TextSueldo;

    @FXML
    public TextField TextHorario;

    @FXML
    public Button send;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxPersonal.setItems(FXCollections.observableArrayList(Personal.PersonalCedulas.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String cedula = String.valueOf(choiceBoxPersonal.getValue());
        if (cedula == null || cedula.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }
        labelCedula.setVisible(true);
        labelSueldo.setVisible(true);
        labelHorario.setVisible(true);
        TextCedula.setText(Personal.PersonalCedulas.get(cedula).cedula);
        TextSueldo.setText(String.valueOf(Personal.PersonalCedulas.get(cedula).sueldo));
        TextHorario.setText(Personal.PersonalCedulas.get(cedula).horario);
        TextCedula.setVisible(true);
        TextSueldo.setVisible(true);
        TextHorario.setVisible(true);
        WarningMessages.setVisible(false);
        send.setVisible(true);
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String cedula = (String) choiceBoxPersonal.getValue();
        String nueva_cedula = TextCedula.getText();
        String nuevo_sueldo = TextSueldo.getText();
        String nuevo_horario = TextHorario.getText();

        if (nueva_cedula.equals("") || nuevo_sueldo.equals("") || nuevo_horario.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if ( nueva_cedula.equals(Personal.PersonalCedulas.get(cedula).cedula)&& nuevo_sueldo.equals(String.valueOf(Personal.PersonalCedulas.get(cedula).sueldo))  && nuevo_horario.equals(Personal.PersonalCedulas.get(cedula).horario)) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }

        Personal.PersonalCedulas.get(cedula).cedula = nueva_cedula;
        Personal.PersonalCedulas.get(cedula).sueldo = Integer.parseInt(nuevo_sueldo);
        Personal.PersonalCedulas.get(cedula).horario = nuevo_horario;
        choiceBoxPersonal.setItems(FXCollections.observableArrayList(Personal.PersonalCedulas.keySet()));
        labelCedula.setVisible(false);
        labelSueldo.setVisible(false);
        labelHorario.setVisible(false);
        TextCedula.setVisible(false);
        TextSueldo.setVisible(false);
        TextHorario.setVisible(false);
        WarningMessages.setVisible(false);
        send.setVisible(false);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Persona editada satisfactoriamente");
        alert.setHeaderText("Persona ha sido editada satisfactoriamente");
        alert.setContentText(Personal.PersonalCedulas.get(cedula).toString());


        alert.showAndWait();

    }

}
