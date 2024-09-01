public class Alumnos {

    //Constantes
    private final Materias[] materiasCursando = new Materias[3];

    //Variables primitivas
    private String nombre, contrasenna, apellido;
    private int legajo;

    //Objetos
    private Carreras carreraDeAlumno;

    //Constructor
    public Alumnos(String nombre, String apellido, int legajo, String contrasenna) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.contrasenna = contrasenna;
    }

    //Metodos void
    public void vacioMaterias(){
        for (int i=0; i<3;i++){
            materiasCursando[i]=null;
        }
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

    //Metodos gets
    public int getLegajo(){
        return legajo;
    }
    public String getNombre(){
        return nombre;
    }
    public String getContrasenna(){
        return contrasenna;
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

    public String getApellido(){
        return apellido;
    }
    public Carreras getCarrera(){
        return carreraDeAlumno;
    }
    public Materias getMateriasAlmacenadas (byte i){
        return materiasCursando[i];
    }
    public Materias getMateriasDeCarrera(int annio, int materiaCuatri){
        return carreraDeAlumno.getMateriasPorAnnioYMateria(annio,materiaCuatri);
    }

    //Metodos void sets
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setContrasenna(String contrasenna){
        this.contrasenna=contrasenna;
    }
    public void setLegajo(int legajo){
        this.legajo = legajo;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
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
    public void setCarrera(Carreras carreraElegida) {
        this.carreraDeAlumno = carreraElegida;
    }


    public String toString(){
        return nombre+", legajo: "+legajo;
    }
    public boolean materiasLlenas (){
        return materiasCursando[2] != null;
    }
    public boolean inscribirAMaterias(String materiaElelgida){
        boolean puedeCursar=true;
        if(carreraDeAlumno.getPlanDeEstudio().aproboCorrelativas(carreraDeAlumno.getMateriasPorNombre(materiaElelgida))){
            almacenoMateriasActivas(carreraDeAlumno.getMateriasPorNombre(materiaElelgida));
        }
        else {
            puedeCursar=false;
        }
        return puedeCursar;
    }
    public boolean materiasVacias(){
        boolean vacio=false;
        for (byte i=0; i<3; ++i){
            if(materiasCursando[i]!=null) {
                vacio = true;
                break;
            }
        }
        return vacio;
    }
}
