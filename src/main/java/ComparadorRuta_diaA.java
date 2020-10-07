import java.util.Comparator;

public class ComparadorRuta_diaA implements Comparator<Ruta> {
    @Override
    public int compare(Ruta ruta1,Ruta ruta2) {
        ruta1.dia.toLowerCase();
        ruta2.dia.toLowerCase();
        int diferencia= (ruta1.dia.toCharArray()[0] - ruta2.dia.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (ruta1.dia.toCharArray()[1] - ruta2.dia.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}