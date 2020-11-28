package App.controladores;

import App.App;
import App.Area;
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

public class CrearTallerController implements Initializable {

    @FXML
    public TextField TextNombre;

    @FXML
    public TextField TextDineroFallasMenores;

    @FXML
    public TextField TextSistemaAsociado;

    @FXML
    public Label WarningMessages;

    @FXML
    public ChoiceBox choiceBoxAreasDisponibles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAreasDisponibles.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String nombre = TextNombre.getText();
        String dinero_fallas_menores = TextDineroFallasMenores.getText();
        String sistema_asociado = TextSistemaAsociado.getText();
        String area_asociada =  String.valueOf(choiceBoxAreasDisponibles.getValue());

        if(nombre.equals("") || dinero_fallas_menores.equals("") || sistema_asociado.equals("") || (area_asociada == null ||area_asociada.equals("null"))) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if (Taller.TallerNombres.containsKey(nombre.toLowerCase())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atencion");
            alert.setHeaderText("Ya existe ese taller");
            alert.setContentText("");

            alert.showAndWait();
            return;
        }

        try {
            long dinero = Long.parseLong(dinero_fallas_menores);
        } catch (NumberFormatException e) {
            WarningMessages.setText("El dinero debe ser un valor numerico");
            WarningMessages.setVisible(true);
            return;
        }

        DefaultEdge arista = Area.AreaIds.get(Long.parseLong(area_asociada));
        Object obj = App.Grafo.getEdgeSource(arista);
        Area area = (Area) obj;

        Taller taller = new Taller(nombre, sistema_asociado, dinero_fallas_menores);
        taller.conectar(area);


        TextNombre.setText("");
        TextDineroFallasMenores.setText("");
        TextSistemaAsociado.setText("");
        choiceBoxAreasDisponibles.setItems(FXCollections.observableArrayList(Area.AreaIds.keySet()));
        WarningMessages.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Taller creado satisfactoriamente");
        alert.setHeaderText("El taller ha sido creado satisfactoriamente");
        alert.setContentText(taller.toString());


        alert.showAndWait();
    }
}
