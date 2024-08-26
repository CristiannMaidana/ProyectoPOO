public class PlanDeEstudioA extends PlanDeEstudio{
    public PlanDeEstudioA(Carreras carrera) {
        super(carrera);
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
}
