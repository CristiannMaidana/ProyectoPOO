import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

public class BuscoAlumnos extends JFrame {
    private JPanel buscoAlumnos;
    private JButton paginaPrincipalButton;
    private JButton altaDeAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton modificoCarreraButton;
    private JButton altaDePlanDeButton;
    private JTextField textFieldDNI;
    private JList listDatosAlumno;
    private JButton inscripcionACarrerasButton;
    private JButton inscripcionAMateriasButton;
    private JButton cargoDeNotasButton;
    private JButton historialAcademicoButton;
    private JCheckBox checkBox1;
    private JButton buscarButton;
    private JButton buscoAlumnosButton;
    private JButton darDeBajaMateriasButton;
    private JButton editarAlumnoButton;
    private boolean paginaPrincipal=false, altaDeAlumnos=false, altaDeCarreras=false, modificoCarreras=false,
            altaPlanDeEstudio=false, inscripcionAMaterias=false,
            inscripcionACarreras=false, BbuscoAlumnos=false;
    private AlumnosRegistrados registroAlumnos;
    private Alumnos usuario = null;
    private DefaultListModel<String> modelDatosAlumno = new DefaultListModel<>();
    private AlmacenCarreras almacenCarreras = null;
    private CountDownLatch latch;

    //Constructor
    public BuscoAlumnos(AlumnosRegistrados registroAlumnos, AlmacenCarreras almacenCarreras, CountDownLatch latch) {
        this.registroAlumnos = registroAlumnos;
        this.almacenCarreras = almacenCarreras;
        this.latch = latch;
        listDatosAlumno.setBorder(new LineBorder(Color.BLACK, 1)); // Color y grosor del borde

        setUndecorated(true);
        setContentPane(buscoAlumnos);
        setSize(1300,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal=true;
                latch.countDown();
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos=true;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarreras=true;
                latch.countDown();
                dispose();
            }
        });
        inscripcionACarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null){
                    JOptionPane.showMessageDialog(null, "Busque a un alumno.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (usuario.getCarrera() !=null){
                    JOptionPane.showMessageDialog(null, "Ya esta inscripto a una carrera.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    inscripcionACarreras = true;
                    CountDownLatch latch = new CountDownLatch(1);
                    InscripcionCarreras inscripcionCarreras = new InscripcionCarreras(almacenCarreras, latch);
                    inscripcionCarreras.setVisible(true);
                    inscripcionCarreras.setLocationRelativeTo(null);

                    // Usar SwingWorker para esperar de forma asíncrona
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            try {
                                latch.await(); // Espera de manera asíncrona
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void done() {
                            usuario.setCarrera(almacenCarreras.getCarreraPorNombre(inscripcionCarreras.getCarreraElegida()));
                            cargoDatosAlumnos(); // Actualiza los datos después de que se cierre el frame secundario
                        }
                    };

                    worker.execute(); // Iniciar el SwingWorker
                }
            }
        });
        inscripcionAMateriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Busque a un alumno.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (usuario.getCarrera() == null){
                    JOptionPane.showMessageDialog(null, "Debe estar inscripto a una carrera.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (usuario.getCarrera().getPlanDeEstudio() == null){
                    JOptionPane.showMessageDialog(null, "La carrera no tiene asignado ningun" +
                            " plan de estudio. Cargue uno para avanzar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    inscripcionAMaterias = true;
                    CountDownLatch latch = new CountDownLatch(1);

                    VInscripcionMaterias inscripcionMaterias = new VInscripcionMaterias(usuario, latch);
                    inscripcionMaterias.setVisible(true);
                    inscripcionMaterias.setLocationRelativeTo(null);

                    // Usar SwingWorker para esperar de forma asíncrona
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            try {
                                latch.await(); // Espera de manera asíncrona
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void done() {
                            cargoDatosAlumnos(); // Actualiza los datos después de que se cierre el frame secundario
                        }
                    };

                    worker.execute(); // Iniciar el SwingWorker
                }
            }
        });
        cargoDeNotasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null){
                    JOptionPane.showMessageDialog(null, "Busque a un alumno.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!usuario.materiasLlenas() && !usuario.materiasVacias()){
                    JOptionPane.showMessageDialog(null, "Necesita estar inscripto a alguna " +
                            "materia.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    CountDownLatch latch = new CountDownLatch(1);
                    VCargoNotas cargoNotas = new VCargoNotas(usuario, latch);
                    cargoNotas.setVisible(true);
                    cargoNotas.setLocationRelativeTo(null);

                    // Usar SwingWorker para esperar de forma asíncrona
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            try {
                                latch.await(); // Espera de manera asíncrona
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void done() {
                            cargoDatosAlumnos(); // Actualiza los datos después de que se cierre el frame secundario
                        }
                    };

                    worker.execute(); // Iniciar el SwingWorker
                }
            }
        });
        historialAcademicoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null){
                    JOptionPane.showMessageDialog(null, "Busque a un alumno.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (usuario.getCarrera() == null){
                    JOptionPane.showMessageDialog(null, "Debe estar inscripto a una carrera.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (usuario.getCarrera().getPlanDeEstudio() == null){
                        JOptionPane.showMessageDialog(null, "La carrera no tiene asignado ningun" +
                                " plan de estudio. Cargue uno para avanzar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        VperfilUsuario perfilUsuario = new VperfilUsuario(usuario);
                        perfilUsuario.setVisible(true);
                        perfilUsuario.setLocationRelativeTo(null);
                    }
                }
            }
        });
        buscarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldDNI.getText().isEmpty() || !textFieldDNI.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (getAlumno() != null){
                    JOptionPane.showMessageDialog(null, "Alumno encontrado!", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                    usuario = getAlumno();
                    cargoDatosAlumnos();
                }
                else {
                    limpioTodo();
                    JOptionPane.showMessageDialog(null, "El DNI es incorrecto: No existe alumno.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina busco alumnos.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        darDeBajaMateriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Busque un alumno antes.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }  else if (!usuario.materiasLlenas() && !usuario.materiasVacias()) {
                    JOptionPane.showMessageDialog(null, "No está inscripto a ninguna materia.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Dar de baja todas las " +
                            "materias?", "Aviso", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        usuario.vacioMaterias();
                        cargoDatosAlumnos();
                    }
                }
            }
        });
        editarAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Busque un alumno antes.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    CountDownLatch latch = new CountDownLatch(1);
                    EditoDatosAlumnos editoDatosAlumnos = new EditoDatosAlumnos(usuario, latch);
                    editoDatosAlumnos.setVisible(true);
                    editoDatosAlumnos.setLocationRelativeTo(null);

                    // Usar SwingWorker para esperar de forma asíncrona
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            try {
                                latch.await(); // Espera de manera asíncrona
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void done() {
                            if (editoDatosAlumnos.getHayCambios()){
                                cargoDatosAlumnos();
                            }
                            else if (editoDatosAlumnos.getBorrarAlumno()){
                                registroAlumnos.remove(registroAlumnos.buscoPorDNI(usuario.getLegajo()));
                                limpioTodo();
                            }
                            else cargoDatosAlumnos();
                        }
                    };

                    worker.execute(); // Iniciar el SwingWorker
                }
            }
        });
    }

    //Metodos void
    public void limpioTodo(){
        modelDatosAlumno.clear();
        listDatosAlumno.setModel(modelDatosAlumno);
    }
    private void cargoDatosAlumnos(){
        boolean siHabiaMaterias=false;
        modelDatosAlumno.clear();
        if (!usuario.getNombre().isEmpty())
            modelDatosAlumno.addElement("Nombre: "+usuario.getNombre()+" "+usuario.getApellido());
        if (usuario.getLegajo() > 0)
            modelDatosAlumno.addElement("Legajo: "+usuario.getLegajo());
        if (usuario.getCarrera() != null)
            modelDatosAlumno.addElement("Carrera: "+usuario.getCarrera().getNombre());
        else
            modelDatosAlumno.addElement("Carrera: No esta inscripto a ninguna carrera.");
        modelDatosAlumno.addElement("Materias inscriptas: ");
        if (usuario.materiasLlenas()){
            for (byte i=0;i<3;i++){
                if (usuario.getMateriasAlmacenadas(i) != null)
                    modelDatosAlumno.addElement("   - "+usuario.getMateriasAlmacenadas(i).getNombreDeMateria());
            }
            siHabiaMaterias=true;
        }
        else if(!siHabiaMaterias){
            for (byte i=0;i<3;i++){
                if (usuario.getMateriasAlmacenadas(i) != null) {
                    modelDatosAlumno.addElement("   - " + usuario.getMateriasAlmacenadas(i).getNombreDeMateria());
                    siHabiaMaterias=true;
                }
            }
        }
        if(!siHabiaMaterias)
            modelDatosAlumno.addElement("No esta inscripto a ninguna materia.");
        listDatosAlumno.setModel(modelDatosAlumno);
    }

    //Metodos get
    private Alumnos getAlumno(){
        int dni = Integer.parseInt(textFieldDNI.getText());
        return registroAlumnos.buscoPorDNI(dni);
    }
    public boolean getPaginaPrincipa(){
        return paginaPrincipal;
    }
    public boolean getAltaDeAlumnos(){
        return altaDeAlumnos;
    }
    public boolean getAltaDeCarreras(){
        return altaDeCarreras;
    }
    public boolean getModificoCarreras(){
        return modificoCarreras;
    }
    public boolean getAltaDePlanDeEstudio(){
        return altaPlanDeEstudio;
    }

    //Metodos set
    public void setAltaDeAlumnos(boolean v) {
        this.altaDeAlumnos = v;
    }
    public void setAltaDeCarreras(boolean v) {
        this.altaDeCarreras = v;
    }
    public void setAltaPlanDeEstudio(boolean v) {
        this.altaPlanDeEstudio = v;
    }
    public void setBuscoAlumnos(boolean v) {
        this.BbuscoAlumnos = v;
    }
    public void setPaginaPrincipal(boolean v){
        this.paginaPrincipal=v;
    }
    public void setModificoCarreras(boolean v){
        this.modificoCarreras=v;
    }
}
