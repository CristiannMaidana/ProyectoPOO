import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscripcionCarreras extends JFrame {
    private JPanel inscripcionCarrera;
    private JComboBox comboBox1;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;

    public InscripcionCarreras(){
        setContentPane(inscripcionCarrera);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(400,400);

        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }
}
