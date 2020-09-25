import java.util.Arrays;

public class Taller {
    String nombre;
    String sistema_asociado;
    String interno_sede;
    boolean reparar_en_ruta;
    int dinero_fallas_menores;
    Area area;
    Recolector recolectores[];
    Personal personas[];

    public Taller(String nombre, String sistema_asociado, String interno_sede, boolean reparar_en_ruta, int dinero_fallas_menores, Area area, Recolector[] recolectores, Personal[] personas) {
        this.nombre = nombre;
        this.sistema_asociado = sistema_asociado;
        this.interno_sede = interno_sede;
        this.reparar_en_ruta = reparar_en_ruta;
        this.dinero_fallas_menores = dinero_fallas_menores;
        this.area = area;
        this.recolectores = recolectores;
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", sistema_asociado='" + sistema_asociado + '\'' +
                ", interno_sede='" + interno_sede + '\'' +
                ", reparar_en_ruta=" + reparar_en_ruta +
                ", dinero_fallas_menores=" + dinero_fallas_menores +
                ", area=" + area +
                ", recolectores=" + Arrays.toString(recolectores) +
                ", personas=" + Arrays.toString(personas) +
                '}';
    }
}
