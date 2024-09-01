import java.util.ArrayList;
import java.util.List;

public class AlmacenCarreras {
    private List<Carreras> almacen = new ArrayList<>();

    public AlmacenCarreras(Carreras carrera) {
        if (carrera != null) {
            add(carrera);
        }
    }

    public void add (Carreras carrera) {
        almacen.add(carrera);
    }

    public Carreras getCarreraPorNombre(String nombreCarrera){
        for (Carreras carreras : almacen) {
            if (carreras.getNombre().equals(nombreCarrera)) {
                return carreras;
            }
        }
        return null;
    }

    public Carreras getCarrera(int indice){
        return almacen.get(indice);
    }

    public int getCantidadCarreras(){
        return almacen.size();
    }

    public void borrarCarrera(int indice){
        almacen.remove(indice);
    }
}
