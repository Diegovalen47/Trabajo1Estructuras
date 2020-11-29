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

public class EditarTallerController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxTalleres;

    @FXML
    public Label labelNombre;

    @FXML
    public Label labelSistema;

    @FXML
    public Label labelDinero;

    @FXML
    public Label WarningMessages;

    @FXML
    public TextField TextNombre;

    @FXML
    public TextField TextSistema;

    @FXML
    public TextField TextDinero;

    @FXML
    public Button send;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxTalleres.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));

    }

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String nombreTaller = String.valueOf(choiceBoxTalleres.getValue());
        if (nombreTaller == null || nombreTaller.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }

        DefaultEdge arista = Taller.TallerNombres.get(nombreTaller);
        Object obj = App.Grafo.getEdgeSource(arista);
        Taller taller = (Taller) obj;

        labelNombre.setVisible(true);
        labelSistema.setVisible(true);
        labelDinero.setVisible(true);
        TextNombre.setText(taller.nombre);
        TextSistema.setText(taller.sistema_asociado);
        TextDinero.setText(String.valueOf(taller.dinero_fallas_menores));
        TextNombre.setVisible(true);
        TextSistema.setVisible(true);
        TextDinero.setVisible(true);
        WarningMessages.setVisible(false);
        send.setVisible(true);
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String nombreTaller = (String) choiceBoxTalleres.getValue();
        String nuevo_nombre = TextNombre.getText();
        String nuevo_sistema = TextSistema.getText();
        String nuevo_dinero = TextDinero.getText();

        DefaultEdge arista = Taller.TallerNombres.get(nombreTaller);
        Object obj = App.Grafo.getEdgeSource(arista);
        Taller taller = (Taller) obj;

        if (nuevo_nombre.equals("") || nuevo_sistema.equals("") || nuevo_dinero.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if (nuevo_nombre.equals(taller.nombre) && nuevo_sistema.equals(taller.sistema_asociado)   && nuevo_dinero.equals(String.valueOf(taller.dinero_fallas_menores))) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }


        if (Taller.TallerNombres.containsKey(nuevo_nombre.toLowerCase())) {
            WarningMessages.setText("Ese taller ya existe");
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


        Taller.TallerNombres.remove(nombreTaller);
        Taller.TallerSistemas.get(taller.sistema_asociado).remove(nombreTaller);
        Taller.TallerDinero.get(taller.dinero_fallas_menores).remove(nombreTaller);
        App.Grafo.removeVertex(taller);
        App.Grafo.removeEdge(arista);

        Taller nuevo_taller = new Taller(nuevo_nombre, nuevo_sistema, nuevo_dinero);


        choiceBoxTalleres.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));
        labelNombre.setVisible(false);
        labelSistema.setVisible(false);
        labelDinero.setVisible(false);
        TextNombre.setVisible(false);
        TextSistema.setVisible(false);
        TextDinero.setVisible(false);
        WarningMessages.setVisible(false);
        send.setVisible(false);


        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Taller editado satisfactoriamente");
        alert.setHeaderText("Taller ha sido editado satisfactoriamente");
        alert.setContentText(taller.toString());


        alert.showAndWait();

    }

}