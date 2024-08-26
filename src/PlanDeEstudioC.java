public class PlanDeEstudioC extends PlanDeEstudio{

    private byte cuatrimestreDeMateriaACursar =0,  cantidadCuatrimestresRecorridos=0;
    private Alumnos alumnos;

    public PlanDeEstudioC(Carreras carrera, Alumnos alumnos) {
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
        return "Plan de estudio ¨C¨";
    }

    public boolean aproboLaCursadaDeLasCorrelativas (Materias materias, boolean aprobo1){
        if (materias.tieneCorrelativa) {
            if (!materias.getCorrelativa().cursadaAprobada) {
                aprobo1 = false;
            }
        }
        return aprobo1;
    }

    public boolean buscoMateria (Materias materias, boolean aprobo2){
        int annioDeCArrera = alumnos.getCarrera().getAnniosCarrera();
        for (byte i = 0; i < annioDeCArrera; i++) {
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
        cuatrimestreDeMateriaACursar = alumnos.getCarrera().getCuatrimestreDeLaMateria(materias);
        if (cuatrimestreDeMateriaACursar == 1) {
            aprobo2 = true;
        }
        if (cuatrimestreDeMateriaACursar == 2) {
            if (cantidadCuatrimestresRecorridos == 1) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 3) {
            if (cantidadCuatrimestresRecorridos == 2) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 4) {
            if (cantidadCuatrimestresRecorridos == 3) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 5) {
            if (cantidadCuatrimestresRecorridos == 4) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 6) {
            if (cantidadCuatrimestresRecorridos == 5) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 7) {
            if (cantidadCuatrimestresRecorridos == 6) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 8) {
            if (cantidadCuatrimestresRecorridos == 7) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 9) {
            if (cantidadCuatrimestresRecorridos == 8) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 10) {
            if (cantidadCuatrimestresRecorridos == 9) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        if (cuatrimestreDeMateriaACursar == 11) {
            if (cantidadCuatrimestresRecorridos == 10) {
                aprobo2 = true;
            }
            else aprobo2=false;
        }
        return aprobo2;
    }
}
