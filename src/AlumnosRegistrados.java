import java.util.ArrayList;
import java.util.List;

public class AlumnosRegistrados {
    private List<Alumnos> alumnosRegistrados = new ArrayList<Alumnos>();

    public AlumnosRegistrados(Alumnos alumno) {
        if (alumno != null)
            alumnosRegistrados.add(alumno);
    }

    public Alumnos buscoAlumno(String nombreAlumno, String contrasenna){
        Alumnos alumnoEncontrado, alumnoRegistrado;
        alumnoEncontrado = null;
        for (int i=0; i<alumnosRegistrados.size(); i++){
            alumnoRegistrado = alumnosRegistrados.get(i);
                if (alumnoRegistrado.getNombre().equals(nombreAlumno)) {
                    if (alumnoRegistrado.getContrasenna().equals(contrasenna)) {
                        alumnoEncontrado = alumnoRegistrado;
                    }
            }
        }
        return alumnoEncontrado;
    }

    public void add(Alumnos alumno){
        alumnosRegistrados.add(alumno);
    }

    public Alumnos buscoPorDNI(int dni){
        Alumnos alumnoEncontrado = null, alumnoEnRegistro;
        for (int i=0; i<alumnosRegistrados.size(); i++){
            alumnoEnRegistro = alumnosRegistrados.get(i);
            if (alumnoEnRegistro.getLegajo() == dni){
                alumnoEncontrado = alumnoEnRegistro;
            }
        }
        return alumnoEncontrado;
    }
}
