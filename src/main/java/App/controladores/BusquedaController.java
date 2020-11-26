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
    public Label WarningMessage;

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
    public void menu_2(String choise) {
        if (choise.equals("Area")) {
            MenuAtributo.setItems(FXCollections.observableArrayList("Identificacion", "Persona a cargo", "Teléfono"));
        }else if(choise.equals("Taller")){
            MenuAtributo.setItems(FXCollections.observableArrayList("Nombre", "Sistema asociado", "Dinero fallas menores"));
        }else if(choise.equals("Personal")){
            MenuAtributo.setItems(FXCollections.observableArrayList("Cedula", "Sueldo", "Horario"));
        }
        
    }
}
