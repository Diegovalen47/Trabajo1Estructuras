import java.util.Comparator;

public class ComparadorSede_personaD implements Comparator<Sede> {
    @Override
    public int compare(Sede sede1,Sede sede2) {
        sede1.persona_a_cargo.toLowerCase();
        sede2.persona_a_cargo.toLowerCase();
        int diferencia= -(sede1.persona_a_cargo.toCharArray()[0] - sede2.persona_a_cargo.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(sede1.persona_a_cargo.toCharArray()[1] - sede2.persona_a_cargo.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
