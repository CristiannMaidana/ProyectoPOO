import javax.swing.*;

public class Cliente {
    static Alumnos alumno1;
    static AlumnosRegistrados cargoAlumnoNuevo;
    static AlmacenCarreras almacenCarreras = new AlmacenCarreras(null);

    public static void main (String[]args) {
        cargoAlumnosRegistrados();//Cargo los alumnos en la clase "AlumnosRegistrados"
        VCreoCarreras panelCreoCarreras;
        //VperfilUsuario panelUsuario;
        Vlogeo panelLogearse = ejecutoPanelLogearse();
        while (panelLogearse.getBotones() || panelLogearse.getCrearUsuario()) {
            //Si ingreso un admin creara carreras:
            if (verificoAdmin(panelLogearse)){
                panelCreoCarreras = ejecutoPanelCreoCarreras();
                if (panelCreoCarreras.getBoton())
                    creoCarrera(panelCreoCarreras);
                else
                    panelLogearse = ejecutoPanelLogearse();
            }
            //Si no es admin o muestra perfil si se logea o crea usuario
            else {
                if (panelLogearse.getCrearUsuario()) {
                    VInscripcion panelInscripcion = ejecutoPanelInscripcion();

                    if (panelInscripcion.getBoton())
                        almacenoNuevoAlumno(panelInscripcion);

                    panelLogearse.crearUsuario = false;
                    panelLogearse = ejecutoPanelLogearse();
                }

                if (verificoAdmin(panelLogearse)){
                    panelCreoCarreras = ejecutoPanelCreoCarreras();
                    if (panelCreoCarreras.getBoton())
                        creoCarrera(panelCreoCarreras);
                    else
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
    }

    static boolean verificoAdmin(Vlogeo panelLogearse) {
        if (panelLogearse.getNombreUsuario().equals("admin"))
            return true;
        else
            return false;
    }

    static void creoCarrera(VCreoCarreras panelCarreras) {
        int annio, cuatri;
        String nombre, planDeEstudio;

        annio = panelCarreras.getAnnioCarrera();
        cuatri = panelCarreras.getCuatriCarrera();
        nombre = panelCarreras.getNombreCarrera();
        planDeEstudio = panelCarreras.getPlanEstudioCarrera();

        Carreras newCarrera;
        if (cuatri == 2) {
             newCarrera= new Carreras(nombre, annio, 6) {
                @Override
                public float getCantMateriasOptativas() {
                    return 0;
                }
            };
        }else {
            newCarrera = new Carreras(nombre, annio, 3) {
                @Override
                public float getCantMateriasOptativas() {
                    return 0;
                }
            };
        }

        newCarrera.generoMaterias();

        switch (planDeEstudio){
            case "Plan A": {
                newCarrera.setPlanDeEstudio(new PlanDeEstudioA(newCarrera));
                break;
            }
            case "Plan B": {
                newCarrera.setPlanDeEstudio(new PlanDeEstudioB(newCarrera));
                break;
            }
            case "Plan C": {
                newCarrera.setPlanDeEstudio(new PlanDeEstudioC(newCarrera,null));
                break;
            }
            case "Plan D": {
                newCarrera.setPlanDeEstudio(new PlanDeEstudioD(newCarrera,null));
                break;
            }
            case "Plan E": {
                newCarrera.setPlanDeEstudio(new PlanDeEstudioE(newCarrera,null));
                break;
            }
        }

        almacenCarreras.add(newCarrera);
    }

    static void cargoAlumnosRegistrados (){
        cargoAlumnoNuevo = new AlumnosRegistrados(creoAlumnoSofia());
        //admin 123456
        Alumnos admin = new Alumnos("admin", 123456, "123456");
        cargoAlumnoNuevo.add(admin);
    }

    static void almacenoNuevoAlumno (VInscripcion panelInscripcion){
        alumno1 = new Alumnos(panelInscripcion.getNombre() + " " + panelInscripcion.getApellido(),
                panelInscripcion.getDni(), panelInscripcion.getContrasenna());
        alumno1.setCarrera(almacenCarreras.getCarreraPorNombre(panelInscripcion.getCarrera()));
        switch (panelInscripcion.getPlanElegido()){
            case "Plan de estudio ¨A¨": {
                alumno1.getCarrera().getPlanDeEstudio().setCarrera(alumno1.getCarrera());

                break;
            }
            case "Plan de estudio ¨B¨": {
                alumno1.getCarrera().getPlanDeEstudio().setCarrera(alumno1.getCarrera());
                break;
            }
            case "Plan de estudio ¨C¨": {
                alumno1.getCarrera().getPlanDeEstudio().setCarrera(alumno1.getCarrera());
                alumno1.getCarrera().getPlanDeEstudio().setAlumno(alumno1);
                break;
            }
            case "Plan de estudio ¨D¨": {
                alumno1.getCarrera().getPlanDeEstudio().setCarrera(alumno1.getCarrera());
                alumno1.getCarrera().getPlanDeEstudio().setAlumno(alumno1);
                break;
            }
            case "Plan de estudio ¨E¨": {
                alumno1.getCarrera().getPlanDeEstudio().setCarrera(alumno1.getCarrera());
                alumno1.getCarrera().getPlanDeEstudio().setAlumno(alumno1);
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
        VInscripcion panelInscripcion = new VInscripcion(almacenCarreras);
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

    static VCreoCarreras ejecutoPanelCreoCarreras(){
        VCreoCarreras panelCarreras = new VCreoCarreras();
        panelCarreras.setLocationRelativeTo(null);
        panelCarreras.setVisible(true);
        hiloEspero(panelCarreras);
        return panelCarreras;
    }
}
