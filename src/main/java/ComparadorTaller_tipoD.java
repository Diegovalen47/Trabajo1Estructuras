import java.util.Comparator;

public class ComparadorTaller_tipoD implements Comparator<Taller> {
    @Override
    public int compare(Taller taller1,Taller taller2) {
        taller1.tipo.toLowerCase();
        taller2.tipo.toLowerCase();
        int diferencia= -(taller1.tipo.toCharArray()[0] - taller2.tipo.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(taller1.tipo.toCharArray()[1] - taller2.tipo.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
