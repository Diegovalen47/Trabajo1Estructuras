public class Ruta {
    int id;
    String horario;
    String dia;
    int recolector_id;
    boolean estado_recolector;
    Recolector recolector;
    Area area;

    public Ruta(int id, String horario, String dia, int recolector_id, boolean estado_recolector) {
        this.id = id;
        this.horario = horario;
        this.dia = dia;
        this.recolector_id = recolector_id;
        this.estado_recolector = estado_recolector;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setRecolector_id(int recolector_id) {
        this.recolector_id = recolector_id;
    }

    public void setEstado_recolector(boolean estado_recolector) {
        this.estado_recolector = estado_recolector;
    }

    public void setRecolector(Recolector recolector) {
        this.recolector = recolector;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public String getDia() {
        return dia;
    }

    public int getRecolector_id() {
        return recolector_id;
    }

    public boolean isEstado_recolector() {
        return estado_recolector;
    }

    public Recolector getRecolector() {
        return recolector;
    }

    public Area getArea() {
        return area;
    }
}
