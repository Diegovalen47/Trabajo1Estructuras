import java.util.LinkedList;

public class Sede {
    String telefono;
    String direccion;
    String persona_a_cargo;
    EmpresaDeBasura empresaDeBasura;
    LinkedList<Area> areas = new LinkedList<>() ;

    public Sede(String telefono, String direccion, String persona_a_cargo) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.persona_a_cargo = persona_a_cargo;
    }

    public void setAreas(Area area) {
        areas.add(area);
    }

    public void setEmpresaDeBasura(EmpresaDeBasura empresaDeBasura) {
        this.empresaDeBasura = empresaDeBasura;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPersona_a_cargo(String persona_a_cargo) {
        this.persona_a_cargo = persona_a_cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPersona_a_cargo() {
        return persona_a_cargo;
    }

    public EmpresaDeBasura getEmpresaDeBasura() {
        return empresaDeBasura;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", persona_a_cargo='" + persona_a_cargo + '\'' +
                '}';
    }
}