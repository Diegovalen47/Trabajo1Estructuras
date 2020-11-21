package App;

public class Personal {
    String cedula;
    int sueldo;
    String horario;

    public Personal(String cedula, int sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = sueldo;
        this.horario = horario;
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