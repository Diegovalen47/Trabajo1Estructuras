import java.util.LinkedList;

public class Usuario {
    int cedula;
    String nombre;
    String apellido;
    String correo;
    String clave;
    LinkedList<EmpresaDeBasura> empresasDeBasura;

    public Usuario(int cedula, String nombre, String apellido, String correo, String clave, LinkedList<EmpresaDeBasura> empresasDeBasura ) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.empresasDeBasura = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", empresasDeBasura=" + empresasDeBasura +
                '}';
    }
}
