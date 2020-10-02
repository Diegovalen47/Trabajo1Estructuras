import java.util.LinkedList;

public class Usuario {
    int cedula;
    String nombre;
    String apellido;
    String correo;
    String clave;
    LinkedList<EmpresaDeBasura> empresasDeBasura = new LinkedList<>();

    public Usuario(int cedula, String nombre, String apellido, String correo, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
    }

    public void setEmpresaDeBasura (EmpresaDeBasura empresaDeBasura) {
        empresasDeBasura.add(empresaDeBasura);
        empresaDeBasura.setUsuario(this);

    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }
}
