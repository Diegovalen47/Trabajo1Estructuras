import java.util.LinkedList;

public class Recolector {
    String marca;
    boolean doble_troque;
    int id;
    boolean disponible;
    int rutaid;
    Ruta ruta;
    LinkedList<Taller> talleres = new LinkedList<>();
    LinkedList<Personal> personas = new LinkedList<>();

    public Recolector(String marca, boolean doble_troque, int id, boolean disponible, int rutaid) {
        this.marca = marca;
        this.doble_troque = doble_troque;
        this.id = id;
        this.disponible = disponible;
        this.rutaid = rutaid;
    }

    public void setTalleres (Taller taller) {
        talleres.add(taller);
    }

    public void setPersonas (Personal persona) {
        personas.add(persona);
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDoble_troque(boolean doble_troque) {
        this.doble_troque = doble_troque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setRutaid(int rutaid) {
        this.rutaid = rutaid;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isDoble_troque() {
        return doble_troque;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getRutaid() {
        return rutaid;
    }

    public Ruta getRuta() {
        return ruta;
    }
}
