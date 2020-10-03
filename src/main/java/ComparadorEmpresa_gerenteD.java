import java.util.Comparator;

public class ComparadorEmpresa_gerenteD implements Comparator<EmpresaDeBasura> {
    @Override
    public int compare(EmpresaDeBasura empresa1,EmpresaDeBasura empresa2) {
        empresa1.gerente.toLowerCase();
        empresa2.gerente.toLowerCase();
        int diferencia= -(empresa1.gerente.toCharArray()[0] - empresa2.gerente.toCharArray()[0]);
        if(diferencia==0){
            diferencia= -(empresa1.gerente.toCharArray()[1] - empresa2.gerente.toCharArray()[1]);
            return diferencia;
        }
        return diferencia;
    }
    
}
