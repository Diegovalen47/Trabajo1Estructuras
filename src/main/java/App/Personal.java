package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

import org.jgrapht.graph.DefaultEdge;

public class Personal {
    public String cedula;
    public long sueldo;
    public String horario;
    public static Hashtable<String, DefaultEdge> PersonalCedulas = new Hashtable<>();
    public static TreeMap<Long, ArrayList<String>> PersonalSueldo = new TreeMap<>();
    public static TreeMap<String, ArrayList<String>> PersonalHorario = new TreeMap<>();

    public Personal(String cedula, String sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = Long.parseLong(sueldo);
        this.horario = horario.toLowerCase();
        App.Grafo.addVertex(this);
        PersonalCedulas.put(cedula,App.Grafo.addEdge(this, this));
        
        if (PersonalSueldo.containsKey(Long.parseLong(sueldo))) {
            PersonalSueldo.get(Long.parseLong(sueldo)).add(cedula);
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(cedula);
            PersonalSueldo.put(Long.parseLong(sueldo), lista);
        }
        if (PersonalHorario.containsKey(horario.toLowerCase())) {
            PersonalHorario.get(horario.toLowerCase()).add(cedula);
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(cedula);
            PersonalHorario.put(horario.toLowerCase(), lista);
        }
    }

    public void conectar(Object obj) {
        App.Grafo.addEdge(this, obj);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Personal)) {
            return false;
        }
        Personal personal = (Personal) obj;
        return personal.cedula.toLowerCase().equals(this.cedula.toLowerCase());
    }

    @Override
    public String toString() {
        return  "\tPERSONAL" + '\n' +
                "\t-Cedula : " + cedula + '\n' +
                "\t-Sueldo : " + sueldo + '\n' +
                "\t-Horario : " + horario;
    }
}