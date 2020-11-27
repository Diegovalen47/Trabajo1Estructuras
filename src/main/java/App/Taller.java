package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class Taller {
    public String nombre;
    public String sistema_asociado;
    public int dinero_fallas_menores;
    public static Hashtable<String, Taller> TallerNombres = new Hashtable<>();
    public static TreeMap<String, ArrayList<String>> TallerSistemas = new TreeMap<>();
    public static TreeMap<Integer, ArrayList<String>> TallerDinero = new TreeMap<>();


    public Taller(String nombre, String sistema_asociado, String dinero_fallas_menores) {
        this.nombre = nombre.toLowerCase();
        this.sistema_asociado = sistema_asociado;
        this.dinero_fallas_menores = Integer.parseInt(dinero_fallas_menores);
        App.Grafo.addVertex(this);
        TallerNombres.put(this.nombre, this);
        if (TallerSistemas.containsKey(sistema_asociado.toLowerCase())) {
            TallerSistemas.get(sistema_asociado.toLowerCase()).add(this.nombre);
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(this.nombre);
            TallerSistemas.put(sistema_asociado.toLowerCase(), lista);
        }
        if (TallerDinero.containsKey(Integer.parseInt(dinero_fallas_menores))) {
            TallerDinero.get(Integer.parseInt(dinero_fallas_menores)).add(this.nombre);
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(this.nombre);
            TallerDinero.put(Integer.parseInt(dinero_fallas_menores), lista);
        }

    }

    public void conectar(Area area) {
        App.Grafo.addEdge(this, area);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Taller)) {
            return false;
        }
        Taller taller = (Taller) obj;
        return taller.nombre.toLowerCase().equals(this.nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return  "\tTALLER" + '\n' +
                "\t-Nombre : " + nombre + '\n' +
                "\t-Sistema asociado : " + sistema_asociado + '\n' +
                "\t-Dinero fallas menores : " + dinero_fallas_menores;
    }
}
