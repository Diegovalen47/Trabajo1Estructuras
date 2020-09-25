import java.util.Arrays;

public class Recolector {
    String marca;
    boolean doble_troque;
    int id;
    boolean disponible;
    int rutaid;
    Taller talleres[];
    Parqueadero parqueaderos[];
    Ruta ruta;
    Personal personas[];

    public Recolector(String marca, boolean doble_troque, int id, boolean disponible, int rutaid, Taller[] talleres, Parqueadero[] parqueaderos, Ruta ruta, Personal[] personas) {
        this.marca = marca;
        this.doble_troque = doble_troque;
        this.id = id;
        this.disponible = disponible;
        this.rutaid = rutaid;
        this.talleres = talleres;
        this.parqueaderos = parqueaderos;
        this.ruta = ruta;
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Recolector{" +
                "marca='" + marca + '\'' +
                ", doble_troque=" + doble_troque +
                ", id=" + id +
                ", disponible=" + disponible +
                ", rutaid=" + rutaid +
                ", talleres=" + Arrays.toString(talleres) +
                ", parqueaderos=" + Arrays.toString(parqueaderos) +
                ", ruta=" + ruta +
                ", personas=" + Arrays.toString(personas) +
                '}';
    }
}
