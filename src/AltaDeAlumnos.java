import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                paginaPrincipal=true;
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
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos=true;
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera=true;
                dispose();
            }
        });
        crearNuevoAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                creoNuevoAlumno=true;
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                limpiarTodo();
            }
        });
    }

    private void limpiarTodo() {
        textFieldContrasenna.setText("");
        textFieldDNI.setText("");
        textFieldApellido.setText("");
        textFieldNombre.setText("");
        textFieldUsuario.setText("");
        comboBoxCarrera.setSelectedIndex(0);
    }
}
