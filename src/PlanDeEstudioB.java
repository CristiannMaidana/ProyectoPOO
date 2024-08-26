public class PlanDeEstudioB extends PlanDeEstudio{
    public PlanDeEstudioB(Carreras carrera) {
        super(carrera);
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

    @Override
    public void setAlumno(Alumnos alumno) {
    }

    @Override
    public void setCarrera(Carreras carrera) {
        this.carrera=carrera;
    }

    @Override
    public String toString() {
        return "Plan de estudio ¨B¨";
    }
}

