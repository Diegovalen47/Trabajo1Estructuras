package App;

public class Taller {
    String nombre;
    String sistema_asociado;
    int dinero_fallas_menores;


    public Taller(String nombre, String sistema_asociado, int dinero_fallas_menores) {
        this.nombre = nombre;
        this.sistema_asociado = sistema_asociado;
        this.dinero_fallas_menores = dinero_fallas_menores;
    }

    @Override
    public String toString() {
        return  "\tTALLER" + '\n' +
                "\t-Nombre : " + nombre + '\n' +
                "\t-Sistema_asociado : " + sistema_asociado + '\n' +
                "\t-Dinero fallas menores : " + dinero_fallas_menores;
    }

}
