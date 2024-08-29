import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private boolean paginaPrincipal=false, altaDeAlumnos=false, altaDeCarreras=false, modificoCarreras=false,
            altaPlanDeEstudio=false, cargoDeNotas=false, consultarSiEstaGraduado=false, inscripcionAMaterias=false,
            inscripcionACarreras=false;
    private AlumnosRegistrados registroAlumnos;

    public BuscoAlumnos(AlumnosRegistrados registroAlumnos) {
        this.registroAlumnos = registroAlumnos;
        setUndecorated(true);
        setContentPane(buscoAlumnos);
        setSize(1000,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal=true;
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos=true;
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarreras=true;
                dispose();
            }
        });
        inscripcionACarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                inscripcionACarreras=true;
                //nuevo frame
            }
        });
        inscripcionAMateriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                inscripcionAMaterias=true;
                VInscripcionMaterias inscripcionMaterias = new VInscripcionMaterias(getAlumno());
                inscripcionMaterias.setVisible(true);
                inscripcionMaterias.setLocationRelativeTo(null);
            }
        });
        cargoDeNotasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                VCargoNotas cargoNotas = new VCargoNotas(getAlumno());
                cargoNotas.setVisible(true);
                cargoNotas.setLocationRelativeTo(null);
            }
        });
        historialAcademicoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    private Alumnos getAlumno(){
        int dni = Integer.parseInt(textFieldDNI.getText());
        return registroAlumnos.buscoPorDNI(dni);
    }
}
