import java.util.LinkedList;

public class Taller {
    String nombre;
    String sistema_asociado;
    String interno_sede;
    boolean reparar_en_ruta;
    int dinero_fallas_menores;
    Area area;
    LinkedList<Recolector> recolectores = new LinkedList<>();
    LinkedList<Personal> personas = new LinkedList<>();

    public Taller(String nombre, String sistema_asociado, String interno_sede, boolean reparar_en_ruta, int dinero_fallas_menores) {
        this.nombre = nombre;
        this.sistema_asociado = sistema_asociado;
        this.interno_sede = interno_sede;
        this.reparar_en_ruta = reparar_en_ruta;
        this.dinero_fallas_menores = dinero_fallas_menores;
    }

    public void setRecolectores (Recolector recolector) {
        recolectores.add(recolector);

    }

    public void setPersonas (Personal persona) {
        personas.add(persona);

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSistema_asociado(String sistema_asociado) {
        this.sistema_asociado = sistema_asociado;
    }

    public void setInterno_sede(String interno_sede) {
        this.interno_sede = interno_sede;
    }

    public void setReparar_en_ruta(boolean reparar_en_ruta) {
        this.reparar_en_ruta = reparar_en_ruta;
    }

    public void setDinero_fallas_menores(int dinero_fallas_menores) {
        this.dinero_fallas_menores = dinero_fallas_menores;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSistema_asociado() {
        return sistema_asociado;
    }

    public String getInterno_sede() {
        return interno_sede;
    }

    public String isReparar_en_ruta() {
        if (reparar_en_ruta) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public int getDinero_fallas_menores() {
        return dinero_fallas_menores;
    }

    public Area getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", sistema_asociado='" + sistema_asociado + '\'' +
                ", interno_sede='" + interno_sede + '\'' +
                ", reparar_en_ruta=" + reparar_en_ruta +
                ", dinero_fallas_menores=" + dinero_fallas_menores +
                '}';
    }
}