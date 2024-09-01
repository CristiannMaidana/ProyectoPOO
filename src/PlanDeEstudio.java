public abstract class PlanDeEstudio {
    protected boolean aprobo;
    protected Carreras carrera;

    //Constructor
    public PlanDeEstudio(Carreras carrera) {
        this.carrera = carrera;
    }

    //Metodos get
    public abstract String getDescripion();

    //Metodos set
    public abstract void setCarrera(Carreras carrera);
    public abstract void setAlumno(Alumnos alumno);


    public abstract String toString();
    public abstract boolean aproboCorrelativas(Materias materias);
}
