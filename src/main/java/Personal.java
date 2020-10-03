import java.util.LinkedList;

public class Personal {
    String perfil;
    String horario;
    int cedula;
    String nombre;
    int sueldo;
    Taller taller;
    Area area;
    LinkedList<Recolector> recolectores = new LinkedList<>();

    public Personal(String perfil, String horario, int cedula, String nombre, int sueldo) {
        this.perfil = perfil;
        this.horario = horario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public void setRecolectores(Recolector recolector) {
        recolectores.add(recolector);
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getHorario() {
        return horario;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSueldo() {
        return sueldo;
    }

    public Taller getTaller() {
        return taller;
    }

    public Area getArea() {
        return area;
    }
}