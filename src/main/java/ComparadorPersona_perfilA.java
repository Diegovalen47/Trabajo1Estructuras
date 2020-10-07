import java.util.Comparator;

public class ComparadorPersona_perfilA implements Comparator<Personal> {
    @Override
    public int compare(Personal persona1,Personal persona2) {
        persona1.perfil.toLowerCase();
        persona2.perfil.toLowerCase();
        int diferencia= (persona1.perfil.toCharArray()[0] - persona2.perfil.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (persona1.perfil.toCharArray()[1] - persona2.perfil.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
