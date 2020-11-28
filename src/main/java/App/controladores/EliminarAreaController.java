package App.controladores;

import App.App;
import App.Area;
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

public class EliminarAreaController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxAreas;

    @FXML
    public Label WarningMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String idArea = String.valueOf(choiceBoxAreas.getValue());
        if (idArea == null || idArea.equals("null")) {
            WarningMessage.setVisible(true);
            WarningMessage.setText("Seleccione una opcion");
            return;
        }

        DefaultEdge arista = Area.AreaIds.get(Long.parseLong(idArea));
        Object obj = App.Grafo.getEdgeSource(arista);
        Area area = (Area) obj;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atencion");
        alert.setHeaderText("¿Desea guardar los cambios?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        } else {
            return;
        }


        Area.AreaIds.remove(Long.parseLong(idArea));
        Area.AreaPersonasACargo.get(area.persona_a_cargo).remove(Long.parseLong(idArea));
        Area.AreaTelefonos.get(area.telefono).remove(Long.parseLong(idArea));
        App.Grafo.removeVertex(area);
        App.Grafo.removeEdge(arista);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Area eliminada satisfactoriamente");
        alert.setHeaderText("Area ha sido eliminada satisfactoriamente");
        alert.setContentText("");


        alert.showAndWait();

        choiceBoxAreas.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
    }
}
