package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

import org.jgrapht.graph.DefaultEdge;

public class Area {
    public long id;
    public int idEdge=1;
    public String persona_a_cargo;
    public long telefono;
    public static Hashtable<Long, DefaultEdge> AreaIds = new Hashtable<>();
    public static TreeMap<String, ArrayList<Long>> AreaPersonasACargo = new TreeMap<>();
    public static TreeMap<Long, ArrayList<Long>> AreaTelefonos = new TreeMap<>();

    public Area(long id, String persona_a_cargo, String telefono) {
        this.persona_a_cargo = persona_a_cargo.toLowerCase();
        this.telefono = Long.parseLong(telefono);
        this.id = id;
        App.Grafo.addVertex(this);
        AreaIds.put(id,App.Grafo.addEdge(this, this));

        if (AreaPersonasACargo.containsKey(persona_a_cargo.toLowerCase())) {
            AreaPersonasACargo.get(persona_a_cargo.toLowerCase()).add(id);
        } else {
            ArrayList<Long> lista = new ArrayList<>();
            lista.add(id);
            AreaPersonasACargo.put(persona_a_cargo.toLowerCase(), lista);
        }
        if (AreaTelefonos.containsKey(Long.parseLong(telefono))) {
            AreaTelefonos.get(Long.parseLong(telefono)).add(id);
        } else {
            ArrayList<Long> lista = new ArrayList<>();
            lista.add(id);
            AreaTelefonos.put(Long.parseLong(telefono), lista);
        }
    }






    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Area)) {
            return false;
        }
        Area area = (Area) obj;
        return area.id == this.id;
    }

    @Override
    public String toString(){ 
        return  "\tAREA" + '\n' +
                "\t-Id : " + id + '\n' +
                "\t-Persona a cargo : " + persona_a_cargo + '\n' +
                "\t-Telefono : " + telefono;
    }
}
