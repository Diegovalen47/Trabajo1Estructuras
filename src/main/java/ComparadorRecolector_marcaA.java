import java.util.Comparator;

public class ComparadorRecolector_marcaA implements Comparator<Recolector> {
    @Override
    public int compare(Recolector recolector1,Recolector recolector2) {
        recolector1.marca.toLowerCase();
        recolector2.marca.toLowerCase();
        int diferencia= (recolector1.marca.toCharArray()[0] - recolector2.marca.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (recolector1.marca.toCharArray()[1] - recolector2.marca.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
