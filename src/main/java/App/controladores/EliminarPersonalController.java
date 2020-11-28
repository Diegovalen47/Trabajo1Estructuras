package App.controladores;

import App.App;
import App.Personal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EliminarPersonalController implements Initializable {
    @FXML
    public ChoiceBox choiceBoxPersonal;

    @FXML
    public Label WarningMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxPersonal.setItems(FXCollections.observableArrayList(Personal.PersonalCedulas.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String cedula = String.valueOf(choiceBoxPersonal.getValue());
        if (cedula == null || cedula.equals("null")) {
            WarningMessage.setVisible(true);
            WarningMessage.setText("Seleccione una opcion");
            return;
        }

        DefaultEdge arista = Personal.PersonalCedulas.get(cedula);
        Object obj = App.Grafo.getEdgeSource(arista);
        Personal personal = (Personal) obj;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atencion");
        alert.setHeaderText("¿Desea guardar los cambios?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        } else {
            return;
        }


        Personal.PersonalCedulas.remove(cedula);
        Personal.PersonalSueldo.get(personal.sueldo).remove(cedula);
        Personal.PersonalHorario.get(personal.horario).remove(cedula);
        App.Grafo.removeVertex(personal);
        App.Grafo.removeEdge(arista);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Persona eliminadoa satisfactoriamente");
        alert.setHeaderText("Persona ha sido eliminada satisfactoriamente");
        alert.setContentText("");


        alert.showAndWait();


    }
}
