import java.util.Comparator;

public class ComparadorRecolector_idD implements Comparator<Recolector> {
    @Override
    public int compare(Recolector recolector1,Recolector recolector2) {

        int diferencia= -(recolector1.id- recolector2.id);

        return diferencia;
    }
    
}
