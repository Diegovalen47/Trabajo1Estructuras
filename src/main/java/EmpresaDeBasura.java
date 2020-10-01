import java.util.*;

public class EmpresaDeBasura {
    String nombre="Basura S.A.S";
    String ciudad= "Medellín";
    String gerente= "Juan Carlos Olarte";
    LinkedList<Usuario> usuarios= new LinkedList<>();
    LinkedList<Sede> sedes= new LinkedList<>();

    //public EmpresaDeBasura(String nombre, String ciudad, String gerente, LinkedList<Usuario> usuarios, LinkedList<Sede> sedes) {
        //this.nombre = nombre;
        //this.ciudad = ciudad;
        //this.gerente = gerente;
        //this.usuarios = usuarios;
        //this.sedes = sedes;
    }

    @Override
    public String toString() {
        return "EmpresaDeBasura{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", gerente='" + gerente + '\'' +
                ", usuarios registrados=" + usuarios.size() +
                ", sedes=" + sedes +
                '}';
    }
}
