package App.controladores;

import App.Area;
import App.Taller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
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
        App.App.setRoot("administracion");
    }

    @FXML
    public void Continuar(ActionEvent event) throws IOException {
        String nombreTaller = String.valueOf(choiceBoxTalleres.getValue());
        if (nombreTaller == null || nombreTaller.equals("null")) {
            WarningMessages.setVisible(true);
            WarningMessages.setText("Seleccione una opcion");
            return;
        }
        labelNombre.setVisible(true);
        labelSistema.setVisible(true);
        labelDinero.setVisible(true);
        TextNombre.setText(Taller.TallerNombres.get(nombreTaller).nombre);
        TextSistema.setText(Taller.TallerNombres.get(nombreTaller).sistema_asociado);
        TextDinero.setText(String.valueOf(Taller.TallerNombres.get(nombreTaller).dinero_fallas_menores));
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

        if (nuevo_nombre.equals("") || nuevo_sistema.equals("") || nuevo_dinero.equals("")) {
            WarningMessages.setText("Los campos no pueden estar vacios");
            WarningMessages.setVisible(true);
            return;
        }

        if (nuevo_nombre.equals(Taller.TallerNombres.get(nombreTaller).nombre) && nuevo_sistema.equals(Taller.TallerNombres.get(nombreTaller).sistema_asociado)   && nuevo_dinero.equals(String.valueOf(Taller.TallerNombres.get(nombreTaller).dinero_fallas_menores))) {
            WarningMessages.setText("No se hicieron cambios");
            WarningMessages.setVisible(true);
            return;
        }

        Taller.TallerNombres.get(nombreTaller).nombre = nuevo_nombre;
        Taller.TallerNombres.get(nombreTaller).sistema_asociado = nuevo_sistema;
        Taller.TallerNombres.get(nombreTaller).dinero_fallas_menores = Integer.parseInt(nuevo_dinero);
        choiceBoxTalleres.setItems(FXCollections.observableArrayList(Taller.TallerNombres.keySet()));
        labelNombre.setVisible(false);
        labelSistema.setVisible(false);
        labelDinero.setVisible(false);
        TextNombre.setVisible(false);
        TextSistema.setVisible(false);
        TextDinero.setVisible(false);
        WarningMessages.setVisible(false);
        send.setVisible(false);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Taller editado satisfactoriamente");
        alert.setHeaderText("Taller ha sido editado satisfactoriamente");
        alert.setContentText(Taller.TallerNombres.get(nombreTaller).toString());


        alert.showAndWait();

    }

}