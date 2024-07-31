public class Alumnos {
    private final String nombre;
    private final Materias[] materiasCursando = new Materias[3];
    private final int legajo;
    private Carreras carreraDeAlumno;

    public Alumnos(String nombre, int legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public void setCarrera(Carreras carreraElegida) {
        this.carreraDeAlumno = carreraElegida;
    }

    public boolean incribirAMaterias(String materiaElelgida){
        boolean puedeCursar=true;
        if(carreraDeAlumno.getPlanDeEstudio().aproboCorrelativas(carreraDeAlumno.getMateriasPorNombre(materiaElelgida))){
            almacenoMateriasActivas(carreraDeAlumno.getMateriasPorNombre(materiaElelgida));
        }
        else {
            puedeCursar=false;
        }
        return puedeCursar;
    }

    public void almacenoMateriasActivas(Materias materias){
        for (int pos=0; pos<6;pos++){
            if (materiasCursando[pos] == null) {
                materiasCursando[pos] = materias;
                break;
            }
            else if (materiasCursando[pos] != null && pos == 2) {
                break;
            }
        }
    }

    public void setNotasParcial(double nota, String nombreMateria){
        for (byte i=0; i<3;i++){
            if (materiasCursando[i]!=null && nombreMateria.equals( materiasCursando[i].getNombreDeMateria()) && !materiasCursando[i].parcial){
                materiasCursando[i].setNotaParcial(nota);
                break;
            }
        }//busca la materia por nombre y asigna nota
        carreraDeAlumno.buscoActualizoCorrelativa(materiasCursando);
        carreraDeAlumno.actualizoMaterias(materiasCursando);//aca actualiza las materias de carrera.
        for (byte i=0; i<3;i++) {
            if (materiasCursando[i]!=null) {
                materiasCursando[i]=null;
                break;
            }
        }//vacia la materia aprobada
    }

    public void setNotasExamen(double nota, String nombreMateria){
        for (byte i=0; i<3;i++){
            if (materiasCursando[i]!=null && nombreMateria.equals( materiasCursando[i].getNombreDeMateria()) && !materiasCursando[i].examenFinal)
                materiasCursando[i].setExamenFinal(nota);
        }//busca la materia por nombre y asigna nota
        carreraDeAlumno.buscoActualizoCorrelativa(materiasCursando);
        carreraDeAlumno.actualizoMaterias(materiasCursando);//aca actualiza las materias de carrera.
        for (byte i=0; i<3;i++) {
            if (materiasCursando[i]!=null && materiasCursando[i].examenFinal) {
                materiasCursando[i]=null;
            }
        }//vacia la materia aprobada
    }

    public Carreras getCarrera(){
        return carreraDeAlumno;
    }

    public String toString(){
        return nombre+", legajo: "+legajo;
    }

    public boolean materiasLlenas (){
        return materiasCursando[2] != null;
    }

    public Materias getMateriasAlmacenadas (byte i){
        return materiasCursando[i];
    }

    public Materias getMateriasDeCarrera(int annio, int materiaCuatri){
        return carreraDeAlumno.getMateriasPorAnnioYMateria(annio,materiaCuatri);
    }

    public boolean getAnioAprobado (int annio){
        boolean annioAprobado=true;
        for (byte i=0; i<6; i++){
            if (!carreraDeAlumno.carrera[annio][i].getNotaExamenFinal()){
                annioAprobado=false;
                break;
            }
        }
        return annioAprobado;
    }
}
