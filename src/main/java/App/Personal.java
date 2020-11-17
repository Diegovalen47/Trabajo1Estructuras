package App;

public class Personal {
    int cedula;
    int sueldo;
    String horario;

    public Personal(int cedula, int sueldo, String horario) {
        this.cedula = cedula;
        this.sueldo = sueldo;
        this.horario = horario;
    }

    @Override
    public String toString() {
        return  "\tPERSONAL" + '\n' +
                "\t-Cedula : " + cedula + '\n' +
                "\t-Sueldo : " + sueldo + '\n' +
                "\t-Horario : " + horario;
    }
}