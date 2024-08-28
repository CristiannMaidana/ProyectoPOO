import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

public class AltaDeAlumnos extends JFrame {
    private JPanel altaDeAlumnos;
    private JButton paginaPrincipalButton;
    private JButton modificoCarreraButton;
    private JButton altaDePlanDeButton;
    private JButton altaDeCarrerasButton;
    private JButton buscoAlumnosButton;
    private JComboBox comboBoxCarrera;
    private JTextField textFieldContrasenna;
    private JTextField textFieldDNI;
    private JTextField textFieldApellido;
    private JTextField textFieldNombre;
    private JTextField textFieldUsuario;
    private JButton crearNuevoAlumnoButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private boolean paginaPrincipal=false, modificoCarrera=false, altaPlanDeEstudio=false, altaDeCarreras=false, buscoAlumnos=false, creoNuevoAlumno = false, cancelar=false;
    private CountDownLatch latch;

    public AltaDeAlumnos(CountDownLatch latch){
        this.latch = latch;
        setUndecorated(true);
        setContentPane(altaDeAlumnos);
        setLocationRelativeTo(null);
        setSize(1000,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        crearNuevoAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
