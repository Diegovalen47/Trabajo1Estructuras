import java.util.Arrays;

public class Area {
    String tipo;
    boolean contratista;
    String horario;
    String persona_a_cargo;
    int telefono_persona_a_cargo;
    Sede sede;
    Ruta rutas[];
    Taller talleres[];
    Parqueadero parqueaderos[];
    Personal personas[];

    public Area(String tipo, boolean contratista, String horario, String persona_a_cargo, int telefono_persona_a_cargo, Sede sede, Ruta[] rutas, Taller[] talleres, Parqueadero[] parqueaderos, Personal[] personas) {
        this.tipo = tipo;
        this.contratista = contratista;
        this.horario = horario;
        this.persona_a_cargo = persona_a_cargo;
        this.telefono_persona_a_cargo = telefono_persona_a_cargo;
        this.sede = sede;
        this.rutas = rutas;
        this.talleres = talleres;
        this.parqueaderos = parqueaderos;
        this.personas = personas;
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
                ", rutas=" + Arrays.toString(rutas) +
                ", talleres=" + Arrays.toString(talleres) +
                ", parqueaderos=" + Arrays.toString(parqueaderos) +
                ", personas=" + Arrays.toString(personas) +
                '}';
    }
}
