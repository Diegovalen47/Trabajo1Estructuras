package App.controladores;

import App.App;
import App.Area;
import App.Personal;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearPersonalController implements Initializable {

    @FXML
    public TextField TextCedula;

    @FXML
    public TextField TextSueldo;

    @FXML
    public TextField TextHorario;

    @FXML
    public Label WarningMessages;

    @FXML
    public ChoiceBox choiceBoxAreasDisponibles;

    @FXML
    public ChoiceBox choiceBoxTalleresDisponibles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAreasDisponibles.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
        choiceBoxTalleresDisponibles.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));
    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String cedula = TextCedula.getText();
        String sueldo = TextSueldo.getText();
        String horario = TextHorario.getText();
        String area_asociada = String.valueOf(choiceBoxAreasDisponibles.getValue());
        String taller_asociado = (String) choiceBoxTalleresDisponibles.getValue();

        if(cedula.equals("") || sueldo.equals("") || horario.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }
        if(area_asociada == null && taller_asociado == null) {
            WarningMessages.setText("Debe asociar el personal a un taller, area, o ambas");
            WarningMessages.setVisible(true);
            return;
        }

        if (Personal.PersonalCedulas.containsKey(cedula)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencion");
            alert.setHeaderText("Ya existe esa persona");
            alert.setContentText("");

            alert.showAndWait();
            return;
        }

        try {
            long dinero = Long.parseLong(sueldo);
        } catch (NumberFormatException e) {
            WarningMessages.setText("El suledo debe ser un valor numerico");
            WarningMessages.setVisible(true);
            return;
        }

        Personal persona = new Personal(cedula, sueldo, horario);


        if (area_asociada == null) {
            DefaultEdge arista = Area.AreaIds.get(Long.parseLong(taller_asociado));
            Object obj = App.Grafo.getEdgeSource(arista);
            Taller taller = (Taller) obj;
            persona.conectar(taller);
        } else if (taller_asociado == null) {
            DefaultEdge arista = Area.AreaIds.get(Long.parseLong(area_asociada));
            Object obj = App.Grafo.getEdgeSource(arista);
            Area area = (Area) obj;
            persona.conectar(area);
        } else {
            DefaultEdge arista = Area.AreaIds.get(Long.parseLong(taller_asociado));
            Object obj = App.Grafo.getEdgeSource(arista);
            Taller taller = (Taller) obj;
            persona.conectar(taller);
            DefaultEdge arista1 = Area.AreaIds.get(Long.parseLong(area_asociada));
            Object obj1 = App.Grafo.getEdgeSource(arista1);
            Area area = (Area) obj1;
            persona.conectar(area);
        }

        TextCedula.setText("");
        TextSueldo.setText("");
        TextHorario.setText("");
        choiceBoxAreasDisponibles.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
        choiceBoxTalleresDisponibles.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));
        WarningMessages.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Persona creada satisfactoriamente");
        alert.setHeaderText("La persona ha sido creada satisfactoriamente");
        alert.setContentText(persona.toString());


        alert.showAndWait();
    }

}
