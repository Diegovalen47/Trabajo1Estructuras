package App;

import java.util.Hashtable;

public class Personal {
    String cedula;
    int sueldo;
    String horario;
    public static Hashtable<String, Personal> PersonalCedulas = new Hashtable<>();

    public Personal(String cedula, String sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = Integer.parseInt(sueldo);
        this.horario = horario;
        PersonalCedulas.put(cedula, this);
        App.Grafo.addVertex(this);
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