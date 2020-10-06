import java.util.Comparator;

public class ComparadorArea_personaA implements Comparator<Area> {
    @Override
    public int compare(Area area1,Area area2) {
        area1.persona_a_cargo.toLowerCase();
        area2.persona_a_cargo.toLowerCase();
        int diferencia= (area1.persona_a_cargo.toCharArray()[0] - area2.persona_a_cargo.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (area1.persona_a_cargo.toCharArray()[1] - area2.persona_a_cargo.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}