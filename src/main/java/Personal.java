import java.util.LinkedList;

public class Personal {
    String perfil;
    String horario;
    int cedula;
    String nombre;
    int sueldo;
    Taller taller;
    LinkedList<Recolector> recolectores;
    Area area;

    public Personal(String perfil, String horario, int cedula, String nombre, int sueldo, Taller taller, LinkedList<Recolector> recolectores, Area area) {
        this.perfil = perfil;
        this.horario = horario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.taller = taller;
        this.recolectores = new LinkedList<>();
        this.area = area;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "perfil='" + perfil + '\'' +
                ", horario='" + horario + '\'' +
                ", cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                ", taller=" + taller +
                ", recolectores=" + recolectores +
                ", area=" + area +
                '}';
    }
}
