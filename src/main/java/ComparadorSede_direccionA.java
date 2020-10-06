import java.util.Comparator;

public class ComparadorSede_direccionA implements Comparator<Sede> {
    @Override
    public int compare(Sede sede1,Sede sede2) {
        sede1.direccion.toLowerCase();
        sede2.direccion.toLowerCase();
        int diferencia= (sede1.direccion.toCharArray()[0] - sede2.direccion.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (sede1.direccion.toCharArray()[1] - sede2.direccion.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
