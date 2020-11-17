package App;

public class Area {
    int id;
    String persona_a_cargo;
    String telefono;

    public Area(int id, String persona_a_cargo, String telefono) {
        this.id = id;
        this.persona_a_cargo = persona_a_cargo;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return  "\n\tAREA" + '\n' +
                "\t-Id : " + id + '\n' +
                "\t-Persona a cargo : " + persona_a_cargo + '\n' +
                "\t-Telefono : " + telefono;
    }


}
