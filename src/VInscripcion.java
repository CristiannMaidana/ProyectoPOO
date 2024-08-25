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
    String nombre, apellido, carrera, contrasenna;
    int dni;

    public VInscripcion() {
        setContentPane(panelInscripcion);
        setTitle("Inscripcion.");
        setSize(500,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cargoOpcionesPlanDeEstudio();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre == null || apellido == null || contrasenna == null) {
                    JOptionPane.showMessageDialog(null, "Debe completar los datos solicitados.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (carrera == null || carrera.equals("Seleccione su carrera:")){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (planElegido == null || planElegido.equals("Plan de estudio:")) {
                    JOptionPane.showMessageDialog(null, "Debe elegir un plan de estudio valido.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    setVisible(false);
                    dispose();
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (!textFieldNombre.getText().isEmpty()) {
                    String nombreIngresado = textFieldNombre.getText();
                    if (nombreIngresado.matches("[a-zA-Z\\s]+")) {
                        nombre = nombreIngresado;
                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre ingresado es inválido.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                if (!textFieldApellido.getText().isEmpty()) {
                    String nombreIngresado = textFieldApellido.getText();
                    if (nombreIngresado.matches("[a-zA-Z\\s]+")) {
                        apellido = nombreIngresado;
                    } else {
                        JOptionPane.showMessageDialog(null, "El apellido ingresado es inválido.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                if (!textFieldDNI.getText().isEmpty()) {
                    String input = textFieldDNI.getText();

                    if (input.matches("\\d+")) {
                        dni = Integer.parseInt(textFieldDNI.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "El dni ingresado es inválido."
                                , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                if (!passwordFieldContrasenna.getText().isEmpty()) {
                    String contrasennaIngresada = passwordFieldContrasenna.getText();
                    if (contrasennaIngresada.length() < 6) {
                        JOptionPane.showMessageDialog(null, "La contraseña debe ser de mas de 6" +
                                " caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        contrasenna = passwordFieldContrasenna.getText();
                    }
                }
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
