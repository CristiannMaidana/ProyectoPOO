import javax.swing.*;
import java.awt.*;

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
    }
}
