import java.util.*;

public class EmpresaDeBasura {
    String nombre;
    String ciudad;
    String gerente;
    Usuario usuario;
    LinkedList<Sede> sedes = new LinkedList<>();

    public EmpresaDeBasura(String nombre, String ciudad, String gerente) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gerente = gerente;
    }

    public void setSedes(Sede sede) {
        sedes.add(sede);
        sede.setEmpresaDeBasura(this);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getGerente() {
        return gerente;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
