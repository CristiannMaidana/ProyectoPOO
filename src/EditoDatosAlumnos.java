import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditoDatosAlumnos extends JFrame {
    private JPanel editarDatosAlumnos;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JButton guardarCambiosButton;
    private JButton borrarAlumnoButton;
    private JButton cancelarButton;
    private JTextField textField3;
    private JPasswordField passwordField2;
    private JCheckBox checkBox1;

    public EditoDatosAlumnos(){
        setContentPane(editarDatosAlumnos);
        setUndecorated(true);
        setSize(300,300);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        guardarCambiosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        borrarAlumnoButton.addMouseListener(new MouseAdapter() {
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
