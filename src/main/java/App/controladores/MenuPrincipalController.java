package App.controladores;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MenuPrincipalController {

    public void CerrarSesion(ActionEvent event) throws IOException {
        App.App.setRoot("iniciar_sesion");
    }


    
}
