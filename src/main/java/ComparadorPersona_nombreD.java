import java.util.Comparator;

public class ComparadorPersona_nombreD implements Comparator<Personal> {
    @Override
    public int compare(Personal persona1,Personal persona2) {
        persona1.nombre.toLowerCase();
        persona2.nombre.toLowerCase();
        int diferencia= -(persona1.nombre.toCharArray()[0] - persona2.nombre.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(persona1.nombre.toCharArray()[1] - persona2.nombre.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
