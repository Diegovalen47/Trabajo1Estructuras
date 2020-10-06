import java.util.Comparator;

public class ComparadorArea_telefonoD implements Comparator<Area> {
    @Override
    public int compare(Area area1,Area area2) {
        area1.telefono_persona_a_cargo.toLowerCase();
        area2.telefono_persona_a_cargo.toLowerCase();
        int diferencia= -(area1.telefono_persona_a_cargo.toCharArray()[0] - area2.telefono_persona_a_cargo.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(area1.telefono_persona_a_cargo.toCharArray()[1] - area2.telefono_persona_a_cargo.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}