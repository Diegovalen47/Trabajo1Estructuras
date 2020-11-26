package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciar_sesion"), 401, 482);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Graph<Object, DefaultEdge> Grafo = new SimpleGraph<>(DefaultEdge.class);



    public static void main(String[] args) {
        Area area1 = new Area(0, "Juan Antonio Mesa", "5482449");
        Area area2 = new Area(1, "Valentin Osorio", "5482449");
        Area area3 = new Area(2, "Juan Antonio Mesa", "5482449");
        Taller taller1 = new Taller("Los Mecanicos", "Hidraulico", "540000");
        taller1.conectar(area1);
        Taller taller2 = new Taller("Chatarreros", "Hidraulico", "68000");
        taller2.conectar(area1);
        Taller taller3 = new Taller("Montaneros", "Transmision", "540000");
        taller3.conectar(area3);
        Personal persona1 = new Personal("1007290916", "562000", "Nocturno");
        persona1.conectar(taller1);
        Personal persona2 = new Personal("70901147", "562000", "Diurno");
        persona2.conectar(area2);
        Personal persona3 = new Personal("43469098", "254800", "Nocturno");
        persona3.conectar(taller2);



        launch();
    }

}