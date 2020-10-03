import java.util.*;

public class EmpresaDeBasura {
    String nombre;
    String ciudad;
    String gerente;
    LinkedList<Sede> sedes = new LinkedList<>();

    public EmpresaDeBasura(String nombre, String ciudad, String gerente) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gerente = gerente;
    }

    public void setSedes(Sede sede) {
        sedes.add(sede);
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


    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getGerente() {
        return gerente;
    }



}