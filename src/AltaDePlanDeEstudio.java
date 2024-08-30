import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AltaDePlanDeEstudio extends JFrame {
    private JPanel altaDePlanDeEstudio;
    private JButton paginaPrincipalButton;
    private JButton altaDeAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton buscoAlumnosButton;
    private JButton modificoCarreraButton;
    private JComboBox comboBox1;
    private JLabel descripcionPlanDeEstudio;
    private JList listDeCarreras;
    private JLabel nombreDeCarreraElegida;
    private JButton asignarCarreraButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;

    public AltaDePlanDeEstudio(){
        listDeCarreras.setBorder(new LineBorder(Color.BLACK, 1)); // Color y grosor del borde

        setContentPane(altaDePlanDeEstudio);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(900,350);

        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }
}
