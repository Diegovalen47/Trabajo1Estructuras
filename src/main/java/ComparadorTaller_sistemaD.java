import java.util.Comparator;

public class ComparadorTaller_sistemaD implements Comparator<Taller> {
    @Override
    public int compare(Taller taller1,Taller taller2) {
        taller1.sistema_asociado.toLowerCase();
        taller2.sistema_asociado.toLowerCase();
        int diferencia= -(taller1.sistema_asociado.toCharArray()[0] - taller2.nombre.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(taller1.sistema_asociado.toCharArray()[1] - taller2.nombre.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}