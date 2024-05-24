import javax.swing.*;

public class Cliente {
    static Alumnos alumno1;
    static Carreras carreras;
    static boolean existeUsuario= false, creoUsuario=false;

    public static void main (String[]args) {
        Vlogeo panelLogearse = new Vlogeo();
        panelLogearse.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
        panelLogearse.setVisible(true);
        hiloEspero(panelLogearse);
        if (panelLogearse.getCrearUsuario()) {
            VInscripcion panelInscripcion = new VInscripcion();
            panelInscripcion.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
            panelInscripcion.setVisible(true);
            hiloEspero(panelInscripcion);
            alumno1 = new Alumnos(panelInscripcion.getNombre() + " " + panelInscripcion.getApellido(), panelInscripcion.getDni());
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
            }//fin switch Inscripcion Carrera
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
            }//fin switch Incripcion Plan de Estudio
            VInscripcionMaterias panelCargoMaterias = new VInscripcionMaterias(alumno1);
            panelCargoMaterias.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
            panelCargoMaterias.setVisible(true);
            hiloEspero(panelCargoMaterias);

            VCargoNotas panelCargoNotas = new VCargoNotas(alumno1);
            panelCargoNotas.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
            panelCargoNotas.setVisible(true);
            hiloEspero(panelCargoNotas);
            while (panelCargoNotas.repitoCargaMaterias()) {
                panelCargoNotas = null;
                panelCargoMaterias = null;//borro para que no se llene la memoria
                panelCargoMaterias = new VInscripcionMaterias(alumno1);
                panelCargoMaterias.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                panelCargoMaterias.setVisible(true);
                hiloEspero(panelCargoMaterias);
                panelCargoNotas = new VCargoNotas(alumno1);
                panelCargoNotas.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                panelCargoNotas.setVisible(true);
                hiloEspero(panelCargoNotas);
            }

            VperfilUsuario panelUsuario = new VperfilUsuario(alumno1);
            panelUsuario.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
            panelUsuario.setVisible(true);

        }else if (panelLogearse.getBotones()) {
            buscoUsuario(panelLogearse.getNombreUsuario(), panelLogearse.getContrasennaUsuario());}
//        } else if (creoUsuario) {
//            VInscripcionMaterias panelCargoMaterias = new VInscripcionMaterias(alumno1);
//            panelCargoMaterias.setVisible(true);
//            hiloEspero(panelCargoMaterias);
//
//            VCargoNotas panelCargoNotas = new VCargoNotas(alumno1);
//            panelCargoNotas.setVisible(true);
//            hiloEspero(panelCargoNotas);
//
//            while (panelCargoNotas.repitoCargaMaterias()) {
//                panelCargoNotas = null;
//                panelCargoMaterias = null;//borro para que no se llene la memoria
//                panelCargoMaterias = new VInscripcionMaterias(alumno1);
//                panelCargoMaterias.setVisible(true);
//                hiloEspero(panelCargoMaterias);
//                panelCargoNotas = new VCargoNotas(alumno1);
//                panelCargoNotas.setVisible(true);
//                hiloEspero(panelCargoNotas);
//            }
//
//            VperfilUsuario panelUsuario = new VperfilUsuario(alumno1);
//            panelUsuario.setVisible(true);
//        }
        if (existeUsuario){
            alumno1 = creoAlumnoSofia();
            VperfilUsuario panelUsuario = new VperfilUsuario(alumno1);
            panelUsuario.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
            panelUsuario.setVisible(true);
            hiloEspero(panelUsuario);
            if(panelUsuario.getCargoMasMaterias()){
                VInscripcionMaterias  panelCargoMaterias = new VInscripcionMaterias(alumno1);
                panelCargoMaterias.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                panelCargoMaterias.setVisible(true);
                hiloEspero(panelCargoMaterias);
                VCargoNotas  panelCargoNotas = new VCargoNotas(alumno1);
                panelCargoNotas.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                panelCargoNotas.setVisible(true);
                hiloEspero(panelCargoNotas);

                while (panelCargoNotas.repitoCargaMaterias()) {
                    System.out.println("hola");
                    panelCargoNotas = null;
                    panelCargoMaterias = null;//borro para que no se llene la memoria
                    panelCargoMaterias = new VInscripcionMaterias(alumno1);
                    panelCargoMaterias.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                    panelCargoMaterias.setVisible(true);
                    hiloEspero(panelCargoMaterias);
                    panelCargoNotas = new VCargoNotas(alumno1);
                    panelCargoNotas.setLocationRelativeTo(null);//Me ejecuta en el medio de la pantalla mi frame
                    panelCargoNotas.setVisible(true);
                    hiloEspero(panelCargoNotas);
                }

                panelUsuario.setVisible(true);
            }
        }
    }

    static void buscoUsuario (String nombreUsuario, String contrasenna){
        switch (nombreUsuario){
            case "Sofia Martinez" : {if (contrasenna.equals("123456"))
                existeUsuario= true;
            }
        }
    }
    static Alumnos creoAlumnoSofia (){
        Alumnos alumno2 = new Alumnos("Sofia Martinez", 43248234);
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
}
