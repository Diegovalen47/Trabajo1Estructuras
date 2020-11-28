package App.controladores;
//import App.App;
import App.App;
import App.Area;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jgrapht.graph.DefaultEdge;

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

    @FXML
    public Button send;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());
        if (idArea == null || idArea.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }
        DefaultEdge arista = Area.AreaIds.get(Long.parseLong(idArea));
        Object obj = App.Grafo.getEdgeSource(arista);
        Area area = (Area) obj;

        labelTelefono.setVisible(true);
        labelPersona.setVisible(true);
        TextTelefono.setText(String.valueOf(area.telefono)); // telefono
        TextPersonaACargo.setText(area.persona_a_cargo);// persona_a_cargo
        TextTelefono.setVisible(true);
        TextPersonaACargo.setVisible(true);
        WarningMessages.setVisible(false);
        send.setVisible(true);
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());
        String nuevo_telefono = TextTelefono.getText();
        String nueva_persona = TextPersonaACargo.getText();

        DefaultEdge arista = Area.AreaIds.get(Long.parseLong(idArea));
        Object obj = App.Grafo.getEdgeSource(arista);
        Area area = (Area) obj;

        if (nueva_persona.equals("") || nuevo_telefono.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if (nuevo_telefono.equals(String.valueOf(area.telefono)) && nueva_persona.equals(area.persona_a_cargo)) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }



        App.Grafo.removeVertex(area);
        App.Grafo.removeEdge(arista);

        Area nueva_area = new Area(Long.parseLong(idArea), nueva_persona, nuevo_telefono);

        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
        labelTelefono.setVisible(false);
        labelPersona.setVisible(false);
        WarningMessages.setVisible(false);
        TextTelefono.setVisible(false);
        TextPersonaACargo.setVisible(false);
        send.setVisible(false);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Area editada satisfactoriamente");
        alert.setHeaderText("Area ha sido editada satisfactoriamente");
        alert.setContentText(nueva_area.toString());


        alert.showAndWait();

    }

}
