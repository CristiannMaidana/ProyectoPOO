import javax.swing.*;
import java.awt.event.*;

public class VInscripcion extends JFrame{
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDNI;
    private JComboBox comboBoxCarreras;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPanel panelInscripcion;
    private JComboBox comboBoxPlanEstudio;
    private JPasswordField passwordFieldContrasenna;
    private String planElegido;
    String nombre, apellido, mail, carrera, contrasenna;
    int dni;
    boolean fin;

    public VInscripcion() {
        setContentPane(panelInscripcion);
        setTitle("Inscripcion.");
        setSize(500,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cargoOpcionesPlanDeEstudio();
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fin=true;
                setVisible(false);
                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fin=false;
                setVisible(false);
                dispose();
            }
        });
        textFieldNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textFieldNombre.getText().isEmpty())
                    nombre = textFieldNombre.getText();
                super.focusLost(e);
            }
        });
        textFieldApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textFieldApellido.getText().isEmpty())
                    apellido=textFieldApellido.getText();
                super.focusLost(e);
            }
        });
        textFieldDNI.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textFieldDNI.getText().isEmpty())
                    dni=Integer.parseInt(textFieldDNI.getText());
                super.focusLost(e);
            }
        });
        comboBoxCarreras.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    carrera = (String) comboBoxCarreras.getSelectedItem();
                }
            }
        });
        comboBoxPlanEstudio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planElegido = (String) comboBoxPlanEstudio.getSelectedItem();;
            }
        });
        passwordFieldContrasenna.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                contrasenna = passwordFieldContrasenna.getText();
                super.focusLost(e);
            }
        });
    }

    public void cargoOpcionesPlanDeEstudio(){
        comboBoxPlanEstudio.addItem("Plan de estudio A");
        comboBoxPlanEstudio.addItem("Plan de estudio B");
        comboBoxPlanEstudio.addItem("Plan de estudio C");
        comboBoxPlanEstudio.addItem("Plan de estudio D");
        comboBoxPlanEstudio.addItem("Plan de estudio E");
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getMail(){
        return mail;
    }

    public int getDni(){
        return dni;
    }

    public String getContrasenna(){
        return contrasenna;
    }

    public String getCarrera(){
        return carrera;
    }

    public String getPlanElegido(){
        return planElegido;
    }
}
