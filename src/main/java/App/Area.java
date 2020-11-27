package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class Area {
    public int id;
    public int idEdge=1;
    public String persona_a_cargo;
    public int telefono;
    public static Hashtable<Integer, Integer> AreaIds = new Hashtable<>();
    public static TreeMap<String, ArrayList<Integer>> AreaPersonasACargo = new TreeMap<>();
    public static TreeMap<Integer, ArrayList<Integer>> AreaTelefonos = new TreeMap<>();

    public Area(int id, String persona_a_cargo, String telefono) {
        this.persona_a_cargo = persona_a_cargo.toLowerCase();
        this.telefono = Integer.parseInt(telefono);
        this.id = id;
        AreaIds.put(id, idEdge);
        App.Grafo.addVertex(this);
        App.Grafo.addEdge(this, this);
        App.Grafo.setEdgeWeight(this,this, idEdge);
        
        if (AreaPersonasACargo.containsKey(persona_a_cargo.toLowerCase())) {
            AreaPersonasACargo.get(persona_a_cargo.toLowerCase()).add(id);
        } else {
            ArrayList<Integer> lista = new ArrayList<>();
            lista.add(id);
            AreaPersonasACargo.put(persona_a_cargo.toLowerCase(), lista);
        }
        if (AreaTelefonos.containsKey(Integer.parseInt(telefono))) {
            AreaTelefonos.get(Integer.parseInt(telefono)).add(id);
        } else {
            ArrayList<Integer> lista = new ArrayList<>();
            lista.add(id);
            AreaTelefonos.put(Integer.parseInt(telefono), lista);
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
    public String toString() {
        return  "\tAREA" + '\n' +
                "\t-Id : " + id + '\n' +
                "\t-Persona a cargo : " + persona_a_cargo + '\n' +
                "\t-Telefono : " + telefono;
    }
}
