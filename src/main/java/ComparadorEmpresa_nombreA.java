import java.util.Comparator;

public class ComparadorEmpresa_nombreA implements Comparator<EmpresaDeBasura> {
    @Override
    public int compare(EmpresaDeBasura empresa1,EmpresaDeBasura empresa2) {
        empresa1.nombre.toLowerCase();
        empresa2.nombre.toLowerCase();
        int diferencia= (empresa1.nombre.toCharArray()[0] - empresa2.nombre.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (empresa1.nombre.toCharArray()[1] - empresa2.nombre.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}