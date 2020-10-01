import java.util.Arrays;
import java.util.LinkedList;

public class Area {
    String tipo;
    boolean contratista;
    String horario;
    String persona_a_cargo;
    int telefono_persona_a_cargo;
    Sede sede;
    LinkedList<Ruta> rutas;
    LinkedList<Taller> talleres;
    LinkedList<Personal> personas;

    public Area(String tipo, boolean contratista, String horario, String persona_a_cargo, int telefono_persona_a_cargo, Sede sede, LinkedList<Ruta> rutas,LinkedList<Taller> talleres, LinkedList<Personal> personas) {
        this.tipo = tipo;
        this.contratista = contratista;
        this.horario = horario;
        this.persona_a_cargo = persona_a_cargo;
        this.telefono_persona_a_cargo = telefono_persona_a_cargo;
        this.sede = sede;
        this.rutas = new LinkedList<>();
        this.talleres = new LinkedList<>();
        this.personas = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Area{" +
                "tipo='" + tipo + '\'' +
                ", contratista=" + contratista +
                ", horario='" + horario + '\'' +
                ", persona_a_cargo='" + persona_a_cargo + '\'' +
                ", telefono_persona_a_cargo=" + telefono_persona_a_cargo +
                ", sede=" + sede +
                ", rutas=" + rutas +
                ", talleres=" + talleres +
                ", personas=" + personas +
                '}';
    }
}
