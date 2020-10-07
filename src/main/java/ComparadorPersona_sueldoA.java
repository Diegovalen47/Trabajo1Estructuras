import java.util.Comparator;

public class ComparadorPersona_sueldoA implements Comparator<Personal> {
    @Override
    public int compare(Personal persona1,Personal persona2) {

        int diferencia= (persona1.sueldo - persona2.sueldo);

        return diferencia;
    }
    
}