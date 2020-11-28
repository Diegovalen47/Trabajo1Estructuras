package App.controladores;

import App.App;
import App.Area;
import App.Personal;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
        App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String cedula = String.valueOf(choiceBoxPersonal.getValue());
        if (cedula == null || cedula.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }

        DefaultEdge arista = Personal.PersonalCedulas.get(cedula);
        Object obj = App.Grafo.getEdgeSource(arista);
        Personal personal = (Personal) obj;


        labelCedula.setVisible(true);
        labelSueldo.setVisible(true);
        labelHorario.setVisible(true);
        TextCedula.setText(personal.cedula);
        TextSueldo.setText(String.valueOf(personal.sueldo));
        TextHorario.setText(personal.horario);
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

        DefaultEdge arista = Personal.PersonalCedulas.get(cedula);
        Object obj = App.Grafo.getEdgeSource(arista);
        Personal personal = (Personal) obj;


        if (nueva_cedula.equals("") || nuevo_sueldo.equals("") || nuevo_horario.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if ( nueva_cedula.equals(personal.cedula)&& nuevo_sueldo.equals(String.valueOf(personal.sueldo))  && nuevo_horario.equals(personal.horario)) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atencion");
        alert.setHeaderText("¿Desea guardar los cambios?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        } else {
            return;
        }


        App.Grafo.removeVertex(personal);
        App.Grafo.removeEdge(arista);

        Personal nueva_persona = new Personal(nueva_cedula, nuevo_sueldo, nuevo_horario);


        choiceBoxPersonal.setItems(FXCollections.observableArrayList(Personal.PersonalCedulas.keySet()));
        labelCedula.setVisible(false);
        labelSueldo.setVisible(false);
        labelHorario.setVisible(false);
        TextCedula.setVisible(false);
        TextSueldo.setVisible(false);
        TextHorario.setVisible(false);
        WarningMessages.setVisible(false);
        send.setVisible(false);


        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Persona editada satisfactoriamente");
        alert.setHeaderText("Persona ha sido editada satisfactoriamente");
        alert.setContentText(nueva_persona.toString());


        alert.showAndWait();

    }

}
