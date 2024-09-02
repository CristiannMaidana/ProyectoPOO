import java.util.concurrent.CountDownLatch;

public class Cliente {
private static AlmacenCarreras almacenCarreras = new AlmacenCarreras(null);
        private static AlumnosRegistrados alumnosRegistrados = new AlumnosRegistrados(null);
        private static PaginaPrincipal paginaPrincipal;
        private static AltaDeAlumnos altaDeAlumnos;
        private static AltaDeCarreras altaDeCarreras;
        private static AltaDePlanDeEstudio altaDePlanDeEstudio;
        private static BuscoAlumnos buscoAlumnos;
        private static ModificoCarreras modificoCarreras;
        private static CountDownLatch latch = new CountDownLatch(1);


        public static void main(String[] args) throws InterruptedException {
            creoCarrera();
            creoAlumno();
            do {
                if (paginaPrincipal == null || verificoPaginaPrincipal(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)) {
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    if (paginaPrincipal != null)
                        latch = new CountDownLatch(1);
                    paginaPrincipal = new PaginaPrincipal(latch);//Envio el semaforo a la ventana
                    paginaPrincipal.setVisible(true);           //Hago visible la ventana
                    paginaPrincipal.setLocationRelativeTo(null);//La coloco en el centro de la pantalla a la ventana
                    latch.await();  //Espero a que termine con mi semaforo.
                }
                else if (verificoAltaDeAlumnos(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)) {
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    inicializoSemaforo();
                    altaDeAlumnos = new AltaDeAlumnos();            //Creo la ventana
                    altaDeAlumnos.setLatch(latch);                  //envio el semaforo inicializado
                    altaDeAlumnos.setRegistroAlumnos(alumnosRegistrados);
                    altaDeAlumnos.setVisible(true);                 //Hago visible la pagina
                    altaDeAlumnos.setLocationRelativeTo(null);      //Coloco la ventana en el medio de la pantalla

                    latch.await();                                  //espera a que termine de usar el semaforo
                    if (altaDeAlumnos.getCreoNuevoAlumno())
                        creoAlumnoNuevo(altaDeAlumnos);                 //Le manda el form para que cree el alumno

                    while (altaDeAlumnos.getCreoNuevoAlumno()) {
                        inicializoSemaforo();
                        altaDeAlumnos.setLatch(latch);
                        altaDeAlumnos.setRegistroAlumnos(alumnosRegistrados);
                        latch.await();
                        creoAlumnoNuevo(altaDeAlumnos);                 //Le manda el form para que cree el alumno
                    }
                }
                else if (verificoALtaDeCarreras(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)) {
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    inicializoSemaforo();
                    altaDeCarreras = new AltaDeCarreras();            //Creo la ventana y envio el semaforo inicializado
                    altaDeCarreras.setLatch(latch);
                    altaDeCarreras.setVisible(true);                 //Hago visible la pagina
                    altaDeCarreras.setLocationRelativeTo(null);      //Coloco la ventana en el medio de la pantalla

                    latch.await();                                  //espera a que termine de usar el semaforo
                    if (altaDeCarreras.getNuevaCarreras())
                        creoCarreraNueva(altaDeCarreras);

                    while (altaDeCarreras.getNuevaCarreras()) {
                        inicializoSemaforo();
                        altaDeCarreras.setLatch(latch);
                        latch.await();
                        if (altaDeCarreras.getNuevaCarreras())
                            creoCarreraNueva(altaDeCarreras);
                    }
                }
                else if (verificoBuscoAlumnos(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)) {
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    inicializoSemaforo();
                    buscoAlumnos = new BuscoAlumnos(alumnosRegistrados, almacenCarreras, latch);
                    buscoAlumnos.setVisible(true);
                    buscoAlumnos.setLocationRelativeTo(null);
                    latch.await();
                }
                else if (verificoAltaDePlanDeEstudio(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)) {
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    inicializoSemaforo();
                    altaDePlanDeEstudio = new AltaDePlanDeEstudio(almacenCarreras, latch);
                    altaDePlanDeEstudio.setAlumnos(alumnosRegistrados);
                    altaDePlanDeEstudio.setVisible(true);
                    altaDePlanDeEstudio.setLocationRelativeTo(null);
                    latch.await();
                }
                else if (verificoModificoCarrera(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras)){
                    reseteo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
                    inicializoSemaforo();
                    modificoCarreras = new ModificoCarreras(almacenCarreras);
                    modificoCarreras.setLatch(latch);
                    modificoCarreras.setVisible(true);
                    modificoCarreras.setLocationRelativeTo(null);
                    latch.await();
                }
            } while(todo(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras));
        }


        private static void inicializoSemaforo(){
            latch = new CountDownLatch(1);                   //Inicializo el semaforo de nuevo
        }
        private static void creoCarrera(){
            Carreras carreras = new Carreras("CarreraA", 4,6);
            carreras.setPlanDeEstudio(new PlanDeEstudioA(null));
            carreras.getPlanDeEstudio().setCarrera(carreras);
            carreras.setMateriasOptativas(5);
            carreras.setMateriasObligatoria(3);
            carreras.generoMaterias();
            almacenCarreras.add(carreras);
        }
        private static void creoAlumno(){
            Alumnos alumnos = new Alumnos("Cristian", "prueba", 1, "sdfsf");
            alumnosRegistrados.add(alumnos);
        }
        private static void creoAlumnoNuevo(AltaDeAlumnos al) {
            Alumnos alumnoX = new Alumnos(al.getNombre(), al.getApellido() ,al.getDni(),al.getContrasenna());
            alumnoX.setCarrera(null);
            alumnosRegistrados.add(alumnoX);
        }
        private static void creoCarreraNueva(AltaDeCarreras altaCarrrera) {
            Carreras carreraX = new Carreras(altaCarrrera.getNombreDeCarrera(), altaCarrrera.getAnnioCarrera(),
                    altaCarrrera.getCuatriCarrera());
            carreraX.setPlanDeEstudio(null);
            carreraX.setMateriasObligatoria(altaCarrrera.getCantMatOb());
            carreraX.setMateriasOptativas(altaCarrrera.getCantMatOp());
            carreraX.generoMaterias();
            almacenCarreras.add(carreraX);
        }
        private static boolean verificoPaginaPrincipal(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                       AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                       BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getPaginaPrincipal()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getPaginaPrincipal()) ||
                    (fAltaCarreras != null && fAltaCarreras.getPaginaPrincipal()) ||
                    (fAltaPlan != null && fAltaPlan.getPaginaPrincipal()) ||
                    (fBuscoAlumnos != null && fBuscoAlumnos.getPaginaPrincipa()) ||
                    (fModificoCarreras != null && fModificoCarreras.getPaginaPrincipal());
        }
        private static boolean verificoAltaDeAlumnos(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                     AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                     BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getAltaDeAlumnosButton()) ||
                    (fAltaCarreras != null && fAltaCarreras.getAltaDeAlumnos()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getAltaDeAlumnos()) ||
                    (fAltaPlan != null && fAltaPlan.getAltaDeAlumnos()) ||
                    (fBuscoAlumnos != null && fBuscoAlumnos.getAltaDeAlumnos()) ||
                    (fModificoCarreras != null && fModificoCarreras.getAltaDeAlumnos());
        }
        private static boolean verificoALtaDeCarreras(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                      AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                      BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getAltaDeCarrerasButton()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getAltaDeCarreras()) ||
                    (fAltaPlan != null && fAltaPlan.getAltaDeCarreras()) ||
                    (fBuscoAlumnos != null && fBuscoAlumnos.getAltaDeCarreras()) ||
                    (fAltaCarreras != null && fAltaCarreras.getAltaDeCarreras()) ||
                    (fModificoCarreras != null && fModificoCarreras.getAltaDeCarreras());
        }
        private static boolean verificoAltaDePlanDeEstudio(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                           AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                           BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getAltaDePlanDeButton()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getAltaPlanDeEstudio()) ||
                    (fAltaCarreras != null && fAltaCarreras.getAltaDePlanDe()) ||
                    (fBuscoAlumnos != null && fBuscoAlumnos.getAltaDePlanDeEstudio()) ||
                    (fModificoCarreras !=null && fModificoCarreras.getAltaPlanDeEstudio());
        }
        private static boolean verificoBuscoAlumnos(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                    AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                    BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getBuscoAlumnoButton()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getBuscoAlumnos()) ||
                    (fAltaCarreras != null && fAltaCarreras.getBuscoAlumnos()) ||
                    (fAltaPlan != null && fAltaPlan.getBuscoAlumnos()) ||
                    (fModificoCarreras != null && fModificoCarreras.getBuscoAlumnos());
        }
        private static boolean verificoModificoCarrera(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                                       AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                                       BuscoAlumnos fBuscoAlumnos, ModificoCarreras fModificoCarreras) {
            return (fPrincipal != null && fPrincipal.getModificoCarreraButton()) ||
                    (fAltaAlumnos != null && fAltaAlumnos.getModificoCarrera()) ||
                    (fAltaCarreras != null && fAltaCarreras.getModificoCarrera()) ||
                    (fAltaPlan != null && fAltaPlan.getModificoCarrera()) ||
                    (fBuscoAlumnos != null && fBuscoAlumnos.getModificoCarreras()) ||
                    (fModificoCarreras != null && fModificoCarreras.getModificoCarreras());
        }
        private static boolean todo(PaginaPrincipal paginaPrincipal, AltaDeAlumnos altaDeAlumnos,
                                    AltaDeCarreras altaDeCarreras, AltaDePlanDeEstudio altaDePlanDeEstudio,
                                    BuscoAlumnos buscoAlumnos, ModificoCarreras modificoCarreras) {
            return verificoPaginaPrincipal(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras) ||
                    verificoAltaDeAlumnos(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras) ||
                    verificoALtaDeCarreras(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras) ||
                    verificoAltaDePlanDeEstudio(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras) ||
                    verificoBuscoAlumnos(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras) ||
                    verificoModificoCarrera(paginaPrincipal, altaDeAlumnos, altaDeCarreras, altaDePlanDeEstudio, buscoAlumnos, modificoCarreras);
        }
        private static void reseteo(PaginaPrincipal fPrincipal, AltaDeAlumnos fAltaAlumnos,
                                    AltaDeCarreras fAltaCarreras, AltaDePlanDeEstudio fAltaPlan,
                                    BuscoAlumnos fBuscoAlumnos, ModificoCarreras fmodificoCarreras) {
            if (fPrincipal != null) {
                fPrincipal.setPaginaPrincipal(false);
                fPrincipal.setAltaDeAlumnos(false);
                fPrincipal.setAltaDeCarreras(false);
                fPrincipal.setAltaPlanDeEstudio(false);
                fPrincipal.setBuscoAlumnos(false);
                fPrincipal.setModificoCarreras(false);
            }

            if (fAltaAlumnos != null) {
                fAltaAlumnos.setPaginaPrincipal(false);
                fAltaAlumnos.setAltaDeAlumnos(false);
                fAltaAlumnos.setAltaDeCarreras(false);
                fAltaAlumnos.setAltaPlanDeEstudio(false);
                fAltaAlumnos.setBuscoAlumnos(false);
                fAltaAlumnos.setModificoCarreras(false);
            }

            if (fAltaCarreras != null) {
                fAltaCarreras.setPaginaPrincipal(false);
                fAltaCarreras.setAltaDeAlumnos(false);
                fAltaCarreras.setAltaDeCarreras(false);
                fAltaCarreras.setAltaPlanDeEstudio(false);
                fAltaCarreras.setBuscoAlumnos(false);
                fAltaCarreras.setModificoCarreras(false);
            }

            if (fAltaPlan != null) {
                fAltaPlan.setPaginaPrincipal(false);
                fAltaPlan.setAltaDeAlumnos(false);
                fAltaPlan.setAltaDeCarreras(false);
                fAltaPlan.setAltaPlanDeEstudio(false);
                fAltaPlan.setBuscoAlumnos(false);
                fAltaPlan.setModificoCarreras(false);
            }

            if (fBuscoAlumnos != null) {
                fBuscoAlumnos.setPaginaPrincipal(false);
                fBuscoAlumnos.setAltaDeAlumnos(false);
                fBuscoAlumnos.setAltaDeCarreras(false);
                fBuscoAlumnos.setAltaPlanDeEstudio(false);
                fBuscoAlumnos.setBuscoAlumnos(false);
                fBuscoAlumnos.setModificoCarreras(false);
            }

            if (fmodificoCarreras != null) {
                fmodificoCarreras.setPaginaPrincipal(false);
                fmodificoCarreras.setAltaDeAlumnos(false);
                fmodificoCarreras.setAltaDeCarreras(false);
                fmodificoCarreras.setAltaPlanDeEstudio(false);
                fmodificoCarreras.setBuscoAlumnos(false);
                fmodificoCarreras.setModificoCarreras(false);
            }
        }
}
