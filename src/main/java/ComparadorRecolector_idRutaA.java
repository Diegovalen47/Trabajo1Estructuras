import java.util.Comparator;

public class ComparadorRecolector_idRutaA  implements Comparator<Recolector> {
    @Override
    public int compare(Recolector recolector1,Recolector recolector2) {

        int diferencia= (recolector1.rutaid- recolector2.rutaid);

        return diferencia;
    }
    
}
