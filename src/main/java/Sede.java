import java.util.Arrays;

public class Sede {
    int telefono;
    String direccion;
    String persona_a_cargo;
    Area areas[];
    EmpresaDeBasura empresa_de_basura;

    public Sede(int telefono, String direccion, String persona_a_cargo, Area[] areas, EmpresaDeBasura empresa_de_basura) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.persona_a_cargo = persona_a_cargo;
        this.areas = areas;
        this.empresa_de_basura = empresa_de_basura;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", persona_a_cargo='" + persona_a_cargo + '\'' +
                ", areas=" + Arrays.toString(areas) +
                '}';
    }
}
