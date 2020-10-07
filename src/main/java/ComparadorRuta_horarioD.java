import java.util.Comparator;
public class ComparadorRuta_horarioD implements Comparator<Ruta> {
    @Override
    public int compare(Ruta ruta1,Ruta ruta2) {
        ruta1.horario.toLowerCase();
        ruta2.horario.toLowerCase();
        int diferencia= -(ruta1.horario.toCharArray()[0] - ruta2.horario.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(ruta1.horario.toCharArray()[1] - ruta2.horario.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}