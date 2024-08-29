import javax.swing.*;
import java.awt.event.*;
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
    private String nombre="", apellido="", contrasenna="", usuario="", carrera="";
    private int dni=0;

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

        textFieldUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario=textFieldUsuario.getText();
            }
        });
        textFieldNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre=textFieldNombre.getText();
            }
        });
        textFieldApellido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellido=textFieldApellido.getText();
            }
        });
        textFieldDNI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valido = textFieldDNI.getText();
                if(!valido.matches("")){
                    dni=Integer.parseInt(valido);
                }
            }
        });
        textFieldContrasenna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contrasenna=textFieldContrasenna.getText();
            }
        });
        comboBoxCarrera.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String valido =comboBoxCarrera.getSelectedItem().toString();
                    if(!valido.equals("Seleccione una carrera:")){
                        carrera=valido;
                    }
                }
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
