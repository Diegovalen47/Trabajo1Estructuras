package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class Area {
    int id;
    String persona_a_cargo;
    int telefono;
    public static Hashtable<Integer, Area> AreaIds = new Hashtable<>();
    public static TreeMap<String, ArrayList<Area>> AreaPersonasACargo = new TreeMap<>();
    public static TreeMap<Integer, ArrayList<Area>> AreaTelefonos = new TreeMap<>();

    public Area(int id, String persona_a_cargo, String telefono) {
        this.persona_a_cargo = persona_a_cargo;
        this.telefono = Integer.parseInt(telefono);
        this.id = id;
        AreaIds.put(id, this);
        App.Grafo.addVertex(this);
        if (AreaPersonasACargo.containsKey(persona_a_cargo)) {
            AreaPersonasACargo.get(persona_a_cargo).add(this);
        } else {
            ArrayList<Area> lista = new ArrayList<>();
            lista.add(this);
            AreaPersonasACargo.put(persona_a_cargo, lista);
        }
        if (AreaTelefonos.containsKey(Integer.parseInt(telefono))) {
            AreaTelefonos.get(Integer.parseInt(telefono)).add(this);
        } else {
            ArrayList<Area> lista = new ArrayList<>();
            lista.add(this);
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
