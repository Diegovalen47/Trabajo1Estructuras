import java.util.Comparator;

public class ComparadorRuta_idD implements Comparator<Ruta> {
    @Override
    public int compare(Ruta ruta1,Ruta ruta2) {
        int diferencia= -(ruta1.id - ruta2.id);
        return diferencia;
    }
    
}