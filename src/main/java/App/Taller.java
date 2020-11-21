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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Taller)) {
            return false;
        }
        Taller taller = (Taller) obj;
        return taller.nombre.toLowerCase().equals(this.nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return  "\tTALLER" + '\n' +
                "\t-Nombre : " + nombre + '\n' +
                "\t-Sistema_asociado : " + sistema_asociado + '\n' +
                "\t-Dinero fallas menores : " + dinero_fallas_menores;
    }

}
