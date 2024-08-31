import javax.swing.*;
import java.awt.*;

public class ModificoCarreras extends JFrame {
    private JPanel modificoCarreras;
    private JButton paginaPrincipalButton;
    private JButton modificoCarrerasButton;
    private JButton altaDeAlumnosButton;
    private JButton buscoAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton altaDePlanDeButton;
    private JComboBox comboBoxCarreras;
    private JButton modificarUnaMateriaButton;
    private JButton modificarPlanDeEstudioButton;
    private JTextField textFieldCambiaMateriaCarrera;
    private JButton button1;
    private JButton button2;
    private JList list1;
    private JCheckBox checkBox1;

    public ModificoCarreras(){
        setUndecorated(true);
        setContentPane(modificoCarreras);
        setSize(1300,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
