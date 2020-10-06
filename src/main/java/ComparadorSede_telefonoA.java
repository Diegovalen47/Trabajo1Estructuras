import java.util.Comparator;

public class ComparadorSede_telefonoA implements Comparator<Sede> {
    @Override
    public int compare(Sede sede1,Sede sede2) {
        sede1.telefono.toLowerCase();
        sede2.telefono.toLowerCase();
        int diferencia= (sede1.telefono.toCharArray()[0] - sede2.telefono.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (sede1.telefono.toCharArray()[1] - sede2.telefono.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}