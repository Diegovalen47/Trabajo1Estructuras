package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class Personal {
    String cedula;
    int sueldo;
    String horario;
    public static Hashtable<String, Personal> PersonalCedulas = new Hashtable<>();
    public static TreeMap<Integer, ArrayList<Personal>> PersonalSueldo = new TreeMap<>();
    public static TreeMap<String, ArrayList<Personal>> PersonalHorario = new TreeMap<>();

    public Personal(String cedula, String sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = Integer.parseInt(sueldo);
        this.horario = horario.toLowerCase();
        PersonalCedulas.put(cedula, this);
        App.Grafo.addVertex(this);
        if (PersonalSueldo.containsKey(Integer.parseInt(sueldo))) {
            PersonalSueldo.get(Integer.parseInt(sueldo)).add(this);
        } else {
            ArrayList<Personal> lista = new ArrayList<>();
            lista.add(this);
            PersonalSueldo.put(Integer.parseInt(sueldo), lista);
        }
        if (PersonalHorario.containsKey(horario.toLowerCase())) {
            PersonalHorario.get(horario.toLowerCase()).add(this);
        } else {
            ArrayList<Personal> lista = new ArrayList<>();
            lista.add(this);
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