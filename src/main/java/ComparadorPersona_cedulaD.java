import java.util.Comparator;

public class ComparadorPersona_cedulaD implements Comparator<Personal> {
    @Override
    public int compare(Personal persona1,Personal persona2) {

        int diferencia= -(persona1.cedula - persona2.cedula);

        return diferencia;
    }
    
}
