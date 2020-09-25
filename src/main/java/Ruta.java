public class Ruta {
    int id;
    String horario;
    String dia;
    int recolector_id;
    boolean estado_recolector;
    Recolector recolector;
    Area area;

    public Ruta(int id, String horario, String dia, int recolector_id, boolean estado_recolector, Recolector recolector, Area area) {
        this.id = id;
        this.horario = horario;
        this.dia = dia;
        this.recolector_id = recolector_id;
        this.estado_recolector = estado_recolector;
        this.recolector = recolector;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                ", dia='" + dia + '\'' +
                ", recolector_id=" + recolector_id +
                ", estado_recolector=" + estado_recolector +
                ", recolector=" + recolector +
                ", area=" + area +
                '}';
    }
}
