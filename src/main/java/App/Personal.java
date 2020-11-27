package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class Personal {
    public String cedula;
    public int sueldo;
    public String horario;
    public static Hashtable<String, Personal> PersonalCedulas = new Hashtable<>();
    public static TreeMap<Integer, ArrayList<String>> PersonalSueldo = new TreeMap<>();
    public static TreeMap<String, ArrayList<String>> PersonalHorario = new TreeMap<>();

    public Personal(String cedula, String sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = Integer.parseInt(sueldo);
        this.horario = horario.toLowerCase();
        App.Grafo.addVertex(this);
        PersonalCedulas.put(cedula, this);
        if (PersonalSueldo.containsKey(Integer.parseInt(sueldo))) {
            PersonalSueldo.get(Integer.parseInt(sueldo)).add(cedula);
        } else {
            ArrayList<String> lista = new ArrayList<>();
            lista.add(cedula);
            PersonalSueldo.put(Integer.parseInt(sueldo), lista);
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