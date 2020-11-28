package App.controladores;

import App.Area;
import App.Personal;
import App.Taller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class BusquedaController implements Initializable {

    public static String seleccion;
    @FXML
    public ChoiceBox MenuEntidad;

    @FXML
    public ChoiceBox MenuAtributo;

    @FXML
    public TextField elementoBuscar;

    @FXML
    public Label WarningMessage;

    @FXML
    TextArea encontrados;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuEntidad.setItems(FXCollections.observableArrayList("Area", "Taller", "Personal"));
        

        MenuEntidad.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){ 
  
            // if the item of the list is changed 
           
            
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    seleccion= (String) MenuEntidad.getValue();
                    
                    if (newValue.intValue()==0) {
                        MenuAtributo.setItems(FXCollections.observableArrayList("Identificacion", "Persona a cargo", "Teléfono"));
                    }else if(newValue.intValue()==1){
                        MenuAtributo.setItems(FXCollections.observableArrayList("Nombre", "Sistema asociado", "Dinero fallas menores"));
                    }else if(newValue.intValue()==2){
                        MenuAtributo.setItems(FXCollections.observableArrayList("Cedula", "Sueldo", "Horario"));
                    }
					
				}
            

			 
        });
    }

    @FXML
    public void Buscar(ActionEvent event) throws IOException {
        encontrados.clear();

       if ("Area".equals((String) MenuEntidad.getValue())){
            if ("Identificacion".equals((String) MenuAtributo.getValue())) {

                if (elementoBuscar.getText().trim().equals("")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Ingrese el valor a buscar");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }

                long id;

                try {
                    id=Integer.parseInt(elementoBuscar.getText().trim());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ingrese un valor numérico");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }
                

                if (App.Area.AreaIds.get(id)==null) {

                    encontrados.appendText('\n'+"          No se han encontrado valores");
                    return;
                }

                Object vertice= App.App.Grafo.getEdgeSource(App.Area.AreaIds.get(id));
                encontrados.appendText(String.valueOf(vertice));

            }else if("Persona a cargo".equals((String) MenuAtributo.getValue())){

                if (elementoBuscar.getText().trim().equals("")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Ingrese el valor a buscar");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }

                String persona;

                try {
                    persona=elementoBuscar.getText().trim();
                    persona=persona.toLowerCase();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ingrese un nombre");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }

                
                if (App.Area.AreaPersonasACargo.get(persona)==null) {

                    encontrados.appendText('\n'+"          No se han encontrado valores");
                    return;
                }

                for (Long id : App.Area.AreaPersonasACargo.get(persona)) {

                    Object vertice= App.App.Grafo.getEdgeSource(App.Area.AreaIds.get(id));
                    encontrados.appendText(String.valueOf(vertice) +'\n');
                    
                }

            }else if("Teléfono".equals((String) MenuAtributo.getValue())){

                if (elementoBuscar.getText().trim().equals("")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Ingrese el valor a buscar");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }

                Long telefono;

                try {
                    telefono=Long.parseLong(elementoBuscar.getText().trim(),10);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ingrese un valor numérico");
                    alert.setContentText("");

                    alert.showAndWait();
                    return;
                }

                
                if (App.Area.AreaTelefonos.get(telefono)==null) {

                    encontrados.appendText('\n'+"          No se han encontrado valores");
                    return;
                }

                

                for (Long id : App.Area.AreaTelefonos.get(telefono)) {

                    Object vertice= App.App.Grafo.getEdgeSource(App.Area.AreaIds.get(id));
                    encontrados.appendText(String.valueOf(vertice) +'\n');
                    
                }

            }
       }
    }

    

    @FXML
    public void Volver(ActionEvent event) throws IOException {
        App.App.setRoot("menu_principal");
    }
}
