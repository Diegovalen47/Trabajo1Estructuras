import java.util.Comparator;

public class ComparadorTaller_dineroA implements Comparator<Taller> {
    @Override
    public int compare(Taller taller1,Taller taller2) {
        
        int diferencia= (taller1.dinero_fallas_menores - taller2.dinero_fallas_menores);

        return diferencia;
    }
    
}