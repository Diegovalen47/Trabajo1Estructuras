package App;

public class Area {
    int id = 0;
    String persona_a_cargo;
    String telefono;

    public Area(String persona_a_cargo, String telefono) {
        id++;
        this.persona_a_cargo = persona_a_cargo;
        this.telefono = telefono;
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
