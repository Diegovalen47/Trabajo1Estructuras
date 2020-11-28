package App.controladores;

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
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class AdministracionController implements Initializable {

    @FXML
    public ChoiceBox choiceBoxClase;

    @FXML
    public ChoiceBox choiceBoxAccion;

    @FXML
    public Label WarningMessage;

    @FXML
    public ListView listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxClase.setItems(FXCollections.observableArrayList("Area", "Taller", "Personal"));
        choiceBoxAccion.setItems(FXCollections.observableArrayList("Ver", "Crear", "Editar", "Eliminar"));
    }


    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("menu_principal");
    }


    @FXML
    public void Enviar(ActionEvent event) throws IOException {
        String clase_elegida = (String) choiceBoxClase.getValue();
        String accion_elegida = (String) choiceBoxAccion.getValue();

        if(clase_elegida == (null) || accion_elegida == (null)) {
            WarningMessage.setText("Los campos no pueden estar vacios");
            WarningMessage.setVisible(true);
            return;
        }

        listView.getItems().clear();

        if (accion_elegida.equals("Ver")) {
            if (clase_elegida.equals("Area")) {
                WarningMessage.setVisible(false);
                if (Area.AreaIds.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay areas para mostrar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    for (Object obj : new ArrayList<>(App.App.Grafo.vertexSet())) {
                        if (obj instanceof Area) {
                            listView.getItems().add(obj);
                        }
                    }

                }
            } else if (clase_elegida.equals("Taller")) {
                WarningMessage.setVisible(false);
                if (Taller.TallerNombres.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay Talleres para mostrar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    for (Object obj : new ArrayList<>(App.App.Grafo.vertexSet())) {
                        if (obj instanceof Taller) {
                            listView.getItems().add(obj);
                        }
                    }
                }
            } else {
                WarningMessage.setVisible(false);
                if (Personal.PersonalCedulas.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay personas para mostrar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    for (Object obj : new ArrayList<>(App.App.Grafo.vertexSet())) {
                        if (obj instanceof Personal) {
                            listView.getItems().add(obj);
                        }
                    }
                }
            }
        } else if (accion_elegida.equals("Crear")) {
            if (clase_elegida.equals("Area")) {
                App.App.setRoot("crear_area");
            } else if (clase_elegida.equals("Taller")) {
                App.App.setRoot("crear_taller");
            } else {
                App.App.setRoot("crear_personal");
            }
        } else if (accion_elegida.equals("Editar")) {
            if (clase_elegida.equals("Area")) {
                WarningMessage.setVisible(false);
                if (Area.AreaIds.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay areas para editar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("editar_area");
                }
            } else if (clase_elegida.equals("Taller")) {
                WarningMessage.setVisible(false);
                if (Taller.TallerNombres.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay Talleres para editar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("editar_taller");
                }
            } else {
                WarningMessage.setVisible(false);
                if (Personal.PersonalCedulas.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay personas para editar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("editar_personal");
                }
            }
        } else if (accion_elegida.equals("Eliminar")) {
            if (clase_elegida.equals("Area")) {
                WarningMessage.setVisible(false);
                if (Area.AreaIds.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay areas para eliminar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("eliminar_area");
                }
            } else if (clase_elegida.equals("Taller")) {
                WarningMessage.setVisible(false);
                if (Taller.TallerNombres.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay Talleres para eliminar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("eliminar_taller");
                }
            } else {
                WarningMessage.setVisible(false);
                if (Personal.PersonalCedulas.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("No hay personas para eliminar");
                    alert.setContentText("");

                    alert.showAndWait();
                } else {
                    App.App.setRoot("eliminar_personal");
                }
            }
        }
    }
}









