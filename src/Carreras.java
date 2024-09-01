import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class   Carreras {
    private final int annio, cuatri;
    private final String nombre;
    private PlanDeEstudio planDeEstudioElegido;
    public Materias[][] carrera;
    private int  mObligatorias, mOptativas;

    public Carreras(String nombre, int annio, int cuatri) {
        this.nombre = nombre;
        this.annio=annio;
        this.cuatri=cuatri;
        carrera = new Materias[annio][cuatri];
    }

    public Materias getMateriasPorNombre(String nombreMateriaBuscar){
        Materias materia=null;
        for (byte annio=0; annio<getAnniosCarrera();annio ++){
            for (byte cuatri=0;cuatri<getCuatriCarrera();cuatri++){
                if (carrera[annio][cuatri].getNombreDeMateria().equals(nombreMateriaBuscar)) {
                    materia=carrera[annio][cuatri];
                    break;
                }
            }
            if (materia != null)
                break;
        }
        return materia;
    }

    public final void setPlanDeEstudio(PlanDeEstudio planDeEstudioElegido) {
        this.planDeEstudioElegido= planDeEstudioElegido;
    }

    public void actualizoMaterias(Materias[] materiasConNotas){
        int annioDeCarrera=getAnniosCarrera();
        for (byte annio=0;annio<annioDeCarrera;annio++){
            for (byte cuatri=0;cuatri<getCuatriCarrera();cuatri++) {
                for (byte cantMaterias=0;cantMaterias<3;cantMaterias++){
                    if(materiasConNotas[cantMaterias] !=null)
                        if(materiasConNotas[cantMaterias].getNombreDeMateria().equals(carrera[annio][cuatri].getNombreDeMateria())){
                            carrera[annio][cuatri]=materiasConNotas[cantMaterias];
                            break;
                        }
                }
            }
        }
    }

    public void buscoActualizoCorrelativa(Materias[] materiasConNotas){
        int annioDeCarrera=getAnniosCarrera();
        for (byte annio=0;annio<annioDeCarrera;annio++){
            for (byte cuatri=0;cuatri<getCuatriCarrera();cuatri++) {
                if (carrera[annio][cuatri].tieneCorrelativa) {
                    for (byte cantMaterias=0;cantMaterias<3;cantMaterias++){
                        if (materiasConNotas[cantMaterias] == carrera[annio][cuatri]){
                            if (carrera[annio][cuatri].getCorrelativa().getNombreDeMateria().equals(materiasConNotas[cantMaterias].getNombreDeMateria())){
                                carrera[annio][cuatri].setCorrelativa(materiasConNotas[cantMaterias]);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }//si quiero hacer correlativas con mas de 1 materia dejar como antes

    public boolean getAlumnoGraduado(){
        byte cantMateriaOptativaAprobadas = 0;
        boolean aprobado=true;
        for (Materias[] fila : carrera) {
            for (Materias materia : fila) {
                if (materia.obligatoria) if (!materia.cursadaAprobada) {
                    aprobado = false;
                    break;
                }
                if (materia.optativa) if (materia.cursadaAprobada) {
                    cantMateriaOptativaAprobadas++;
                }
            }
            if (!aprobado)//solo para cortar el bucle
                break;
        }
        if (aprobado) if (cantMateriaOptativaAprobadas < getCantMateriasOptativas()){
                aprobado = false;
        }
        return aprobado;
    }

    public Materias getMateriasPorAnnioYMateria(int annio, int materia){
        return carrera[annio][materia];
    }

    public int getAnniosCarrera(){
        return annio;
    }

    public int getCuatriCarrera(){
        return cuatri;
    }

    public PlanDeEstudio getPlanDeEstudio(){
        return planDeEstudioElegido;
    }

    public byte getCuatrimestreDeLaMateria (Materias materias) {
        byte cuatri=0, annio=0;
        for (byte i=0; i<getAnniosCarrera();i++){
            annio=i;
            for (byte j=0; j<getCuatriCarrera();j++){
                if (carrera[i][j].getNombreDeMateria().equals(materias.getNombreDeMateria())) {
                    cuatri = j;
                    break;
                }
            }
            if (carrera[i][cuatri].getNombreDeMateria().equals(materias.getNombreDeMateria())) break;
        }
        switch (annio){
            case 0 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 1;
                    else cuatri = 2;
                break;
            }
            case 1 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 3;
                    else cuatri = 4;
                break;
            }
            case 2 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 5;
                    else cuatri = 6;
                break;
            }
            case 3 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 7;
                    else cuatri = 8;
                break;
            }
            case 4 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 9;
                    else cuatri = 10;
                break;
            }
            case 5 :{if (cuatri==0 || cuatri==1 || cuatri==2)
                        cuatri = 11;
                    else cuatri = 12;
                break;
            }
        }
        return cuatri;
    }

    public abstract float getCantMateriasOptativas ();

    public void generoMaterias (){
        Random random1 = new Random();
        Random random2 = new Random();
        int annio = getAnniosCarrera();
        int cuatri = getCuatriCarrera();
        int cantidadPositivasOptativas=0, cantidadPositivasObligatorias=0;
        for (byte annios = 0; annios<annio; annios++){
            for (byte cuatris = 0; cuatris<cuatri; cuatris++){
                if (annios>0){
                    String nombre = "materia" + annios + cuatris;
                    Materias materia = new Materias(nombre);
                    if(random1.nextBoolean() && cantidadPositivasOptativas < mOptativas){
                        cantidadPositivasOptativas++;
                        materia.setOptativa(true);
                    }
                    if(random2.nextBoolean() && cantidadPositivasObligatorias < mObligatorias){
                        cantidadPositivasObligatorias++;
                        materia.setObligatoria(true);
                    }
                    carrera[annios][cuatris] = materia;
                    carrera[annios][cuatris].setCorrelativa(carrera[annios-1][cuatris]);
                }else{
                    String nombre = "materia" + annios + cuatris;
                    Materias materia = new Materias(nombre);
                    carrera[annios][cuatris] = materia;
                }
            }
        }
    }

    public String getNombre(){
        return nombre;
    }

    public List<Materias> getMateriasObligatorias(){
        List<Materias> materiasObligatorias = new ArrayList<Materias>();
        int annio = getAnniosCarrera();
        int cuatri = getCuatriCarrera();
        for (byte annios = 0; annios<annio; annios++){
            for (byte cuatris = 0; cuatris<cuatri; cuatris++){
                if (carrera[annios][cuatris].obligatoria)
                    if (!carrera[annios][cuatris].cursadaAprobada)
                        materiasObligatorias.add(carrera[annios][cuatris]);
            }
        }
        return materiasObligatorias;
    }

    public List<Materias> getMateriasOptativas(){
        List<Materias> materiasOptativas = new ArrayList<Materias>();
        int annio = getAnniosCarrera();
        int cuatri = getCuatriCarrera();
        for (byte annios = 0; annios<annio; annios++){
            for (byte cuatris = 0; cuatris<cuatri; cuatris++){
                if (carrera[annios][cuatris].optativa)
                    if (!carrera[annios][cuatris].cursadaAprobada)
                        materiasOptativas.add(carrera[annios][cuatris]);
            }
        }
        return materiasOptativas;
    }

    public void setMateriasObligatoria(int mObligatorias){
        this.mObligatorias = mObligatorias;
    }

    public void setMateriasOptativas (int mOptativas){
        this.mOptativas = mOptativas;
    }

    public void imprimo(){
        int annio = getAnniosCarrera();
        int cuatri = getCuatriCarrera();
        for (byte annios = 0; annios<annio; annios++){
            for (byte cuatris = 0; cuatris<cuatri; cuatris++){
                System.out.println(carrera[annios][cuatris].getNombreDeMateria());
            }
        }
    }
}