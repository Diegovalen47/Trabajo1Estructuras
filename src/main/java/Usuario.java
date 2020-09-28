public class Usuario {
    int cedula;
    String nombre;
    String apellido;
    String correo;
    String clave;
    EmpresaDeBasura empresa_de_basura;

    public Usuario(int cedula, String nombre, String apellido, String correo, String clave, EmpresaDeBasura empresa_de_basura) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.empresa_de_basura = empresa_de_basura;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", empresa_de_basura=" + empresa_de_basura +
                '}';
    }
}
