public abstract class PlanDeEstudio {
    protected Carreras carrera;
    protected boolean aprobo;

    public PlanDeEstudio(Carreras carrera) {
        this.carrera = carrera;
    }

    public abstract boolean aproboCorrelativas(Materias materias);

    public abstract String toString();
}
