import java.util.Comparator;

public class ComparadorArea_horarioA implements Comparator<Area> {
    @Override
    public int compare(Area area1,Area area2) {
        area1.horario.toLowerCase();
        area2.horario.toLowerCase();
        int diferencia= (area1.horario.toCharArray()[0] - area2.horario.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (area1.horario.toCharArray()[1] - area2.horario.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
