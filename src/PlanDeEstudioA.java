public class PlanDeEstudioA extends PlanDeEstudio{
    public PlanDeEstudioA(Carreras carrera) {
        super(carrera);
    }

    //Metodos get
    @Override
    public String getDescripion() {
        return "En el plan de estudo 'A', tiene que tener aprobadas las cursadas de las correlativas para poder inscribirse.";
    }

    //Metodo set
    @Override
    public void setCarrera(Carreras carrera) {
        this.carrera=carrera;
    }
    @Override
    public void setAlumno(Alumnos alumno) {
    }


    @Override
    public String toString() {
        return "Plan de estudio ¨A¨";
    }
    @Override
    public boolean aproboCorrelativas(Materias materias) {
        aprobo=true;
        if (materias.tieneCorrelativa){
            if (!materias.getCorrelativa().cursadaAprobada){
                aprobo=false;
            }
        }
        return aprobo;
    }

}
