import java.util.*;

public class EmpresaDeBasura {
    String nombre;
    String ciudad;
    String gerente;
    LinkedList<Usuario> usuarios;
    LinkedList<Sede> sedes;

    public EmpresaDeBasura(String nombre, String ciudad, String gerente, LinkedList<Usuario> usuarios, LinkedList<Sede> sedes) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gerente = gerente;
        this.usuarios = usuarios;
        this.sedes = sedes;
    }

    @Override
    public String toString() {
        return "EmpresaDeBasura{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", gerente='" + gerente + '\'' +
                ", Cantidad usuarios=" + usuarios.size() +
                ", Cantidad sedes=" + sedes.size() +
                '}';
    }
}
