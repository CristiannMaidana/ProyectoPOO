public class PlanDeEstudioE extends PlanDeEstudio{
    private final byte cuatriPrevios = 3;
    private byte cantidadCuatrimestresRecorridos;
    private Alumnos alumnos;

    public PlanDeEstudioE(Carreras carrera, Alumnos alumnos) {
        super(carrera);
        this.alumnos=alumnos;
    }

    @Override
    public boolean aproboCorrelativas(Materias materias) {
        boolean aprobo1 = true, aprobo2 = true;
        aprobo1=aproboLaCursadaDeLasCorrelativas(materias, aprobo1);
        if (aprobo1) {
            aprobo2=buscoMateria(materias,aprobo2);
        }
        System.out.println(aprobo1 + ", " + aprobo2);
        return (aprobo1 && aprobo2);
    }

    @Override
    public void setCarrera(Carreras carrera) {
        this.carrera=carrera;
    }

    @Override
    public void setAlumno(Alumnos alumno) {
        this.alumnos = alumno;
    }

    @Override
    public String toString() {
        return "Plan de estudio ¨E¨";
    }

    @Override
    public String getDescripion() {
        return "El plan de estudio 'E', tiene que tener aprobado los finales de las correlativas y los finales de todas" +
                " las materias de 3 cuatrimestres previos para poder inscribirse.";
    }

    public boolean aproboLaCursadaDeLasCorrelativas (Materias materias, boolean aprobo1){
        if (materias.tieneCorrelativa){
            if (!materias.getCorrelativa().examenFinal){
                aprobo1=false;
            }
        }
        return aprobo1;
    }

    public boolean buscoMateria (Materias materias, boolean aprobo2){
        if (cantidadCuatrimestresRecorridos != cuatriPrevios) {
            cantidadCuatrimestresRecorridos = 0;
            for (byte i = 0; i < alumnos.getCarrera().getAnniosCarrera(); i++) {
                for (byte j = 0; j < 6; j += 3) {
                    // Verificamos si hay espacio suficiente para formar un conjunto de 3 elementos
                    if (j + 2 < 6) {
                        // Verificamos si los 3 elementos están en posiciones adyacentes
                        if (alumnos.getCarrera().carrera[i][j].getNotaExamenFinal() && alumnos.getCarrera().carrera[i][j + 1].getNotaExamenFinal() && alumnos.getCarrera().carrera[i][j + 2].getNotaExamenFinal()) {
                            cantidadCuatrimestresRecorridos++; // Sumamos 1 al total de conjuntos de 3 elementos consecutivos
                        }

                    }
                }
            }

            byte cuatrimestreDeMateriaACursar = alumnos.getCarrera().getCuatrimestreDeLaMateria(materias);
            if (cuatrimestreDeMateriaACursar == 1) {
                aprobo2 = true;
            }
            if (cuatrimestreDeMateriaACursar == 2) {
                aprobo2 = cantidadCuatrimestresRecorridos == 1;
            }
            if (cuatrimestreDeMateriaACursar == 3) {
                aprobo2 = cantidadCuatrimestresRecorridos == 2;
            }
            if (cuatrimestreDeMateriaACursar == 4) {
                aprobo2 = cantidadCuatrimestresRecorridos == 3;
            }
            if (cuatrimestreDeMateriaACursar == 5) {
                aprobo2 = cantidadCuatrimestresRecorridos == 4;
            }
            if (cuatrimestreDeMateriaACursar == 6) {
                aprobo2 = cantidadCuatrimestresRecorridos == 5;
            }
        }
        return aprobo2;
    }
}
