import java.util.LinkedList;

public class Recolector {
    String marca;
    boolean doble_troque;
    int id;
    boolean disponible;
    int rutaid;
    LinkedList<Taller> talleres;
    Ruta ruta;
    LinkedList<Personal> personas;

    public Recolector(String marca, boolean doble_troque, int id, boolean disponible, int rutaid, LinkedList<Taller> talleres, Ruta ruta, LinkedList<Personal> personas) {
        this.marca = marca;
        this.doble_troque = doble_troque;
        this.id = id;
        this.disponible = disponible;
        this.rutaid = rutaid;
        this.talleres = new LinkedList<>();
        this.ruta = ruta;
        this.personas = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Recolector{" +
                "marca='" + marca + '\'' +
                ", doble_troque=" + doble_troque +
                ", id=" + id +
                ", disponible=" + disponible +
                ", rutaid=" + rutaid +
                ", talleres=" + talleres +
                ", ruta=" + ruta +
                ", personas=" + personas +
                '}';
    }
}
