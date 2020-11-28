package App.controladores;

import App.App;
import App.Taller;
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

public class EliminarTallerController implements Initializable {
    @FXML
    public ChoiceBox choiceBoxTalleres;

    @FXML
    public Label WarningMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxTalleres.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String nombreTaller = String.valueOf(choiceBoxTalleres.getValue());
        if (nombreTaller == null || nombreTaller.equals("null")) {
            WarningMessage.setVisible(true);
            WarningMessage.setText("Seleccione una opcion");
            return;
        }

        DefaultEdge arista = Taller.TallerNombres.get(nombreTaller);
        Object obj = App.Grafo.getEdgeSource(arista);
        Taller taller = (Taller) obj;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atencion");
        alert.setHeaderText("¿Desea guardar los cambios?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        } else {
            return;
        }


        Taller.TallerNombres.remove(nombreTaller);
        Taller.TallerSistemas.get(taller.sistema_asociado).remove(nombreTaller);
        Taller.TallerDinero.get(taller.dinero_fallas_menores).remove(nombreTaller);
        App.Grafo.removeVertex(taller);
        App.Grafo.removeEdge(arista);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Taller eliminado satisfactoriamente");
        alert.setHeaderText("Taller ha sido eliminado satisfactoriamente");
        alert.setContentText("");


        alert.showAndWait();

        choiceBoxTalleres.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));
    }

}
