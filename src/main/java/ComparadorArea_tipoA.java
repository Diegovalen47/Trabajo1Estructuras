import java.util.Comparator;

public class ComparadorArea_tipoA implements Comparator<Area> {
    @Override
    public int compare(Area area1,Area area2) {
        area1.tipo.toLowerCase();
        area2.tipo.toLowerCase();
        int diferencia= (area1.tipo.toCharArray()[0] - area2.tipo.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (area1.tipo.toCharArray()[1] - area2.tipo.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}