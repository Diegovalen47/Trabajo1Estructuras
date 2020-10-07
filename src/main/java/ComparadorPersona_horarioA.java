import java.util.Comparator;

public class ComparadorPersona_horarioA implements Comparator<Personal> {
    @Override
    public int compare(Personal persona1,Personal persona2) {
        persona1.perfil.toLowerCase();
        persona2.perfil.toLowerCase();
        int diferencia= (persona1.horario.toCharArray()[0] - persona2.horario.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (persona1.horario.toCharArray()[1] - persona2.horario.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
