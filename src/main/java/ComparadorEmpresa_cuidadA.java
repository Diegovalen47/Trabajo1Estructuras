import java.util.Comparator;

public class ComparadorEmpresa_cuidadA implements Comparator<EmpresaDeBasura> {
    @Override
    public int compare(EmpresaDeBasura empresa1,EmpresaDeBasura empresa2) {
        empresa1.ciudad.toLowerCase();
        empresa2.ciudad.toLowerCase();
        int diferencia= (empresa1.ciudad.toCharArray()[0] - empresa2.ciudad.toCharArray()[0]);
        if(diferencia==0){
            diferencia= (empresa1.ciudad.toCharArray()[1] - empresa2.ciudad.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}