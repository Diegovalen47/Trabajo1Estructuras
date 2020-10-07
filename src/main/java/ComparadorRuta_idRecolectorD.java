import java.util.Comparator;

public class ComparadorRuta_idRecolectorD implements Comparator<Ruta> {
    @Override
    public int compare(Ruta ruta1,Ruta ruta2) {
        int diferencia= -(ruta1.recolector_id - ruta2.recolector_id);
        return diferencia;
    }
    
}
