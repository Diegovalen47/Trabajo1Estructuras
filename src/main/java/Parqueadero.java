import java.util.Arrays;

public class Parqueadero {
    int cantidad_coches;
    int cantidad_motos;
    int cantidad_pisos;
    Recolector recolectores[];
    Area areas[];

    public Parqueadero(int cantidad_coches, int cantidad_motos, int cantidad_pisos, Recolector[] recolectores, Area[] areas) {
        this.cantidad_coches = cantidad_coches;
        this.cantidad_motos = cantidad_motos;
        this.cantidad_pisos = cantidad_pisos;
        this.recolectores = recolectores;
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "Parqueadero{" +
                "cantidad_coches=" + cantidad_coches +
                ", cantidad_motos=" + cantidad_motos +
                ", cantidad_pisos=" + cantidad_pisos +
                ", recolectores=" + Arrays.toString(recolectores) +
                ", areas=" + Arrays.toString(areas) +
                '}';
    }
}
