import java.util.*;

public class EmpresaDeBasura {
    String nombre;
    String ciudad;
    String gerente;
    LinkedList<Usuario> usuarios;
    LinkedList<Sede> sedes;

    public EmpresaDeBasura(String nombre, String ciudad, String gerente, Usuario usuario, LinkedList<Sede> sedes) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gerente = gerente;
        this.usuarios.add(usuario);
        this.sedes = sedes;
    }

    @Override
    public String toString() {
        return "EmpresaDeBasura{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", gerente='" + gerente + '\'' +
                ", usuarios=" + usuarios +
                ", sedes=" + sedes +
                '}';
    }
}
