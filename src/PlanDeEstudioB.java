public class PlanDeEstudioB extends PlanDeEstudio{
    public PlanDeEstudioB(Carreras carrera) {
        super(carrera);
    }

    //Metodos get
    @Override
    public String getDescripion() {
        return "En el plan de etudio 'B', tiene que tener aprobado los finales de las correlativas para poder inscribirse.";
    }

    //Metodos set
    @Override
    public void setCarrera(Carreras carrera) {
        this.carrera=carrera;
    }
    @Override
    public void setAlumno(Alumnos alumno) {
    }

    @Override
    public String toString() {
        return "Plan de estudio ¨B¨";
    }
    @Override
    public boolean aproboCorrelativas(Materias materias) {
        aprobo=true;
        if (materias.tieneCorrelativa){
            if (!materias.getCorrelativa().examenFinal){
                aprobo=false;
            }
        }
        return aprobo;
    }
}

