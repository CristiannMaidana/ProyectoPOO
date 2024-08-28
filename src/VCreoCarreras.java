import javax.swing.*;
import java.awt.event.*;

public class VCreoCarreras extends JFrame {
    private JTextField textFieldNombre;
    private JComboBox comboBoxAnnios, comboBoxCuatri, comboBoxPlanEstudio;
    private JPanel PanelCreoCarreras;
    private JButton nuevaCarreraButton, cancelarButton;
    private String nombreCarrera, planEstudioCarrera;
    private int annioCarrera, cuatriCarrera;
    public boolean boton;

    public VCreoCarreras() {
        setContentPane(PanelCreoCarreras);
        setTitle("Inicie sesion.");
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textFieldNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String validoNombre = textFieldNombre.getText();
                if (!validoNombre.isEmpty() && !validoNombre.matches("[a-zA-Z\\s]+")) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre sin caracteres numéricos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                nombreCarrera = validoNombre;
                super.focusLost(e);
            }
        });
        comboBoxAnnios.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String annioVerifico = (String) comboBoxAnnios.getSelectedItem();
                    if (annioVerifico.equals("Cantidad de años:"))
                        JOptionPane.showMessageDialog(null, "Ingrese un año valido.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        String annio = (String) comboBoxAnnios.getSelectedItem();
                        annio = annio.substring(0, annio.length() - 1); // Elimino el °
                        annioCarrera = Integer.parseInt(annio);
                    }
                }
            }
        });

        comboBoxCuatri.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String cuatiVerifico = (String) comboBoxCuatri.getSelectedItem();
                    if (cuatiVerifico.equals("Cantidad de cuatrimestres:"))
                        JOptionPane.showMessageDialog(null, "Ingrese un cuatrimestre valido.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        String cuatri = (String) comboBoxCuatri.getSelectedItem();
                        cuatri = cuatri.substring(0, cuatri.length() - 1); // Elimino el °
                        cuatriCarrera = Integer.parseInt(cuatri);
                    }
                }
            }
        });
        comboBoxPlanEstudio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String planEstudioVerifico = (String) comboBoxPlanEstudio.getSelectedItem();
                    if (planEstudioVerifico.equals("Planes de estudio:"))
                        JOptionPane.showMessageDialog(null, "Ingrese un plan de estudio valido.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    else planEstudioCarrera = planEstudioVerifico;
                }
            }
        });
        nuevaCarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreCarrera.isEmpty() || planEstudioCarrera.isEmpty() || planEstudioCarrera.equals("Planes de estudio:") || annioCarrera <=0) {
                    JOptionPane.showMessageDialog(null, "Debe completar los datos solicitados.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    boton = true;
                    setVisible(false);
                    dispose();
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boton = false;
                setVisible(false);
                dispose();
            }
        });
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getAnnioCarrera() {
        return annioCarrera;
    }

    public int getCuatriCarrera() {
        return cuatriCarrera;
    }

    public String getPlanEstudioCarrera() {
        return planEstudioCarrera;
    }

    public boolean getBoton() {
        return boton;
    }
}

