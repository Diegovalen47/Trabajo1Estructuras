import java.util.Arrays;

public class EmpresaDeBasura {
    String nombre;
    String ciudad;
    String gerente;
    Usuario usuarios[];
    Sede sedes[];

    public EmpresaDeBasura(String nombre, String ciudad, String gerente, Usuario[] usuarios, Sede[] sedes) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gerente = gerente;
        this.usuarios = usuarios;
        this.sedes = sedes;
    }

    @Override
    public String toString() {
        return "EmpresaDeBasura{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", gerente='" + gerente + '\'' +
                ", usuarios=" + Arrays.toString(usuarios) +
                ", sedes=" + Arrays.toString(sedes) +
                '}';
    }
}
