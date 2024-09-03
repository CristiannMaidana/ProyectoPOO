import java.util.ArrayList;
import java.util.List;

public class AlumnosRegistrados {
    private List<Alumnos> alumnosRegistrados = new ArrayList<Alumnos>();

    //Constructor
    public AlumnosRegistrados(Alumnos alumno) {
        if (alumno != null)
            alumnosRegistrados.add(alumno);
    }

    //Metodos void
    public void add(Alumnos alumno){
        alumnosRegistrados.add(alumno);
    }
    public void remove(int legajo){
        alumnosRegistrados.removeIf(alumno -> alumno.getLegajo() == legajo);
    }

    //Metodos gets
    public int getSize(){
        return alumnosRegistrados.size();
    }
    public Alumnos getAlumno(int i){
        return alumnosRegistrados.get(i);
    }


    public Alumnos buscoPorDNI(int dni){
        Alumnos alumnoEncontrado = null, alumnoEnRegistro;
        for (Alumnos alumnosRegistrado : alumnosRegistrados) {
            alumnoEnRegistro = alumnosRegistrado;
            if (alumnoEnRegistro.getLegajo() == dni) {
                alumnoEncontrado = alumnoEnRegistro;
            }
        }
        return alumnoEncontrado;
    }
}
