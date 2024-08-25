import javax.swing.*;

public class Cliente {
    static Alumnos alumno1;
    static Carreras carreras;
    static AlumnosRegistrados cargoAlumnoNuevo;

    public static void main (String[]args) {
        cargoAlumnosRegistrados();//Cargo los alumnos en la clase "AlumnosRegistrados"

        Vlogeo panelLogearse = ejecutoPanelLogearse();
        while (panelLogearse.getBotones() || panelLogearse.getCrearUsuario()) {
            if (panelLogearse.getCrearUsuario()) {
                VInscripcion panelInscripcion = ejecutoPanelInscripcion();

                if (panelInscripcion.getBoton())
                    almacenoNuevoAlumno(panelInscripcion);

                panelLogearse.crearUsuario = false;
                panelLogearse = ejecutoPanelLogearse();
            }

            if (panelLogearse.getBotones()) {
                alumno1 = cargoAlumnoNuevo.buscoAlumno(panelLogearse.getNombreUsuario(), panelLogearse.getContrasennaUsuario());

                VperfilUsuario panelUsuario = ejecutoPanelUsuario();

                if (panelUsuario.getCargoMasMaterias()) {
                    VInscripcionMaterias panelCargoMaterias = ejecutoPanelInscripcionMaterias();

                    if (!panelCargoMaterias.getBotonAceptar())
                        if (!panelCargoMaterias.getBotonCancelar() || panelCargoMaterias.getBotonCargoNotas()) {
                        VCargoNotas panelCargoNotas = ejecutoPanelCargoNotas();

                        boolean repito = cargoMateriasNotasMuestroHistorial(panelCargoNotas, panelCargoMaterias);
                        while (repito) {
                            repito = cargoMateriasNotasMuestroHistorial(panelCargoNotas, panelCargoMaterias);
                        }
                    }
                }
                if (panelUsuario.getBoton()) {
                    panelLogearse = ejecutoPanelLogearse();
                }
            }
        }
    }

    static void cargoAlumnosRegistrados (){
        cargoAlumnoNuevo = new AlumnosRegistrados(creoAlumnoSofia());
    }

    static void almacenoNuevoAlumno (VInscripcion panelInscripcion){
        alumno1 = new Alumnos(panelInscripcion.getNombre() + " " + panelInscripcion.getApellido(),
                panelInscripcion.getDni(), panelInscripcion.getContrasenna());
        switch (panelInscripcion.getCarrera()) {
            case "Licenciatura en sistemas.": {
                alumno1.setCarrera(carreras = new CarreraSistemas(5, 6));
                break;
            }
            case "Ingenieria Industrial.": {
                alumno1.setCarrera(carreras = new CarreraSistemas(6, 6));
                break;
            }
            case "Turismo.": {
                alumno1.setCarrera(carreras = new CarreraPruebas(4, 6));
                break;
            }
            default: break;
        }//fin switch Inscripcion Carrera al alumno
        switch (panelInscripcion.getPlanElegido()){
            case "Plan de estudio A": {
                alumno1.getCarrera().setPlanDeEstudio(new PlanDeEstudioA(alumno1.getCarrera()));
                break;
            }
            case "Plan de estudio B": {
                alumno1.getCarrera().setPlanDeEstudio(new PlanDeEstudioB(alumno1.getCarrera()));
                break;
            }
            case "Plan de estudio C": {
                alumno1.getCarrera().setPlanDeEstudio(new PlanDeEstudioC(alumno1.getCarrera(),alumno1));
                break;
            }
            case "Plan de estudio D": {
                alumno1.getCarrera().setPlanDeEstudio(new PlanDeEstudioD(alumno1.getCarrera(), alumno1));
                break;
            }
            case "Plan de estudio E": {
                alumno1.getCarrera().setPlanDeEstudio(new PlanDeEstudioE(alumno1.getCarrera(), alumno1));
                break;
            }
        }//fin switch Incripcion Plan de Estudio al alumno
        cargoAlumnoNuevo.add(alumno1);
    }

    static Alumnos creoAlumnoSofia (){
        Alumnos alumno2 = new Alumnos("Sofia Martinez", 43248234, "123456");
        alumno2.setCarrera(new CarreraPruebas(4,6));
        alumno2.getCarrera().setPlanDeEstudio(new PlanDeEstudioB(alumno2.getCarrera()));
        materiasAlumnos(alumno2);
        return alumno2;
    }

    static void materiasAlumnos(Alumnos alumno1){
        alumno1.incribirAMaterias("materia1");
        alumno1.incribirAMaterias("materia2");
        alumno1.incribirAMaterias("materia3");
        alumno1.setNotasParcial(9,"materia1");
        alumno1.setNotasParcial(9,"materia2");
        alumno1.setNotasParcial(9,"materia3");
        alumno1.incribirAMaterias("materia4");
        alumno1.incribirAMaterias("materia5");
        alumno1.incribirAMaterias("materia6");
        alumno1.setNotasParcial(3,"materia4");
        alumno1.setNotasParcial(3,"materia5");
        alumno1.setNotasParcial(3,"materia6");

        alumno1.incribirAMaterias("materia21");
        alumno1.incribirAMaterias("materia22");
        alumno1.incribirAMaterias("materia23");
        alumno1.setNotasParcial(3,"materia21");
        alumno1.setNotasParcial(3,"materia22");
        alumno1.setNotasParcial(3,"materia23");



        alumno1.incribirAMaterias("materia24");
        alumno1.incribirAMaterias("materia25");
        alumno1.incribirAMaterias("materia26");
        alumno1.setNotasParcial(3,"materia24");
        alumno1.setNotasParcial(3,"materia25");
        alumno1.setNotasParcial(3,"materia26");
/*

        alumno1.incribirAMaterias("materia31");
        alumno1.incribirAMaterias("materia32");
        alumno1.incribirAMaterias("materia33");
        alumno1.setNotasParcial(9,"materia31");
        alumno1.setNotasParcial(9,"materia32");
        alumno1.setNotasParcial(9,"materia33");
        alumno1.incribirAMaterias("materia34");
        alumno1.incribirAMaterias("materia35");
        alumno1.incribirAMaterias("materia36");
        alumno1.setNotasParcial(9,"materia34");
        alumno1.setNotasParcial(9,"materia35");
        alumno1.setNotasParcial(9,"materia36");*/
    }

    static void hiloEspero(JFrame panelLogearse){
        while (panelLogearse.isVisible()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean cargoMateriasNotasMuestroHistorial(VCargoNotas panelCargoNotas, VInscripcionMaterias panelCargoMaterias){
        while (panelCargoNotas.repitoCargaMaterias() && !panelCargoMaterias.getBotonCancelar()) {
            panelCargoMaterias = ejecutoPanelInscripcionMaterias();

            if (panelCargoMaterias.getBotonCargoNotas()) {
                panelCargoNotas = ejecutoPanelCargoNotas();
            }
        }
        return panelCargoMaterias.getBotonCargoNotas();
    }

    static Vlogeo ejecutoPanelLogearse(){
        Vlogeo panelLogearse = new Vlogeo(cargoAlumnoNuevo);
        panelLogearse.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelLogearse.setVisible(true);
        hiloEspero(panelLogearse);
        return panelLogearse;
    }

    static VInscripcion ejecutoPanelInscripcion(){
        VInscripcion panelInscripcion = new VInscripcion();
        panelInscripcion.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelInscripcion.setVisible(true);
        hiloEspero(panelInscripcion);
        return panelInscripcion;
    }

    static VInscripcionMaterias ejecutoPanelInscripcionMaterias(){
        VInscripcionMaterias panelCargoMaterias = new VInscripcionMaterias(alumno1);
        panelCargoMaterias.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelCargoMaterias.setVisible(true);
        hiloEspero(panelCargoMaterias);
        return panelCargoMaterias;
    }

    static VCargoNotas ejecutoPanelCargoNotas(){
        VCargoNotas panelCargoNotas = new VCargoNotas(alumno1);
        panelCargoNotas.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelCargoNotas.setVisible(true);
        hiloEspero(panelCargoNotas);
        return panelCargoNotas;
    }

    static VperfilUsuario ejecutoPanelUsuario(){
        VperfilUsuario panelUsuario = new VperfilUsuario(alumno1);
        panelUsuario.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelUsuario.setVisible(true);
        hiloEspero(panelUsuario);
        return panelUsuario;
    }
}
