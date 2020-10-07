import java.util.Comparator;

public class ComparadorTaller_nombreA implements Comparator<Taller> {
    @Override
    public int compare(Taller taller1,Taller taller2) {
        taller1.nombre.toLowerCase();
        taller2.nombre.toLowerCase();
        int diferencia= (taller1.nombre.toCharArray()[0] - taller2.nombre.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (taller1.nombre.toCharArray()[1] - taller2.nombre.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}