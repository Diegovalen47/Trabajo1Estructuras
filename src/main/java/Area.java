import java.util.LinkedList;

public class Area {
    String tipo;
    boolean contratista;
    String horario;
    String persona_a_cargo;
    String telefono_persona_a_cargo;
    Sede sede;
    LinkedList<Ruta> rutas = new LinkedList<>();
    LinkedList<Taller> talleres = new LinkedList<>();
    LinkedList<Personal> personas = new LinkedList<>();

    public Area(String tipo, boolean contratista, String horario, String persona_a_cargo, String telefono_persona_a_cargo) {
        this.tipo = tipo;
        this.contratista = contratista;
        this.horario = horario;
        this.persona_a_cargo = persona_a_cargo;
        this.telefono_persona_a_cargo = telefono_persona_a_cargo;
    }

    public void setRutas(Ruta ruta) {
        rutas.add(ruta);
    }

    public void setTalleres(Taller taller) {
        talleres.add(taller);
    }

    public void setPersonas (Personal persona) {
        personas.add(persona);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setContratista(boolean contratista) {
        this.contratista = contratista;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setPersona_a_cargo(String persona_a_cargo) {
        this.persona_a_cargo = persona_a_cargo;
    }

    public void setTelefono_persona_a_cargo(String telefono_persona_a_cargo) {
        this.telefono_persona_a_cargo = telefono_persona_a_cargo;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getTipo() {
        return tipo;
    }

    public String isContratista() {
        if (contratista) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public String getHorario() {
        return horario;
    }

    public String getPersona_a_cargo() {
        return persona_a_cargo;
    }

    public String getTelefono_persona_a_cargo() {
        return telefono_persona_a_cargo;
    }

    public Sede getSede() {
        return sede;
    }


}