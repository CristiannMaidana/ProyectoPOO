import javax.swing.*;
import java.awt.event.*;
public class Vlogeo extends JFrame{
    private JTextField textFieldNombreUsuario;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JButton crearUsuarioButton;
    private JPanel panelLogearsee;
    private JPasswordField passwordFieldContrasenna;
    private String nombreUsuario, contrasennaUsuario;
    public boolean crearUsuario = false;
    private boolean  boton = false;
    private AlumnosRegistrados registro;

    public Vlogeo(AlumnosRegistrados registro) {
        this.registro = registro;
        setContentPane(panelLogearsee);
        setTitle("Inicie sesion.");
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (nombreUsuario == null || nombreUsuario.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe ingresar un usuario.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (contrasennaUsuario == null || contrasennaUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog( null, "Debe ingresar una contraseña.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (registro.buscoAlumno(nombreUsuario, contrasennaUsuario) == null) {
                    JOptionPane.showMessageDialog(null, "El usuario o contraseña es invalido.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    boton=true;
                    setVisible(false);
                    dispose();
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boton =false;
                setVisible(false);
                dispose();
            }
        });
        crearUsuarioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                crearUsuario = true;
                setVisible(false);
                dispose();
            }
        });
        textFieldNombreUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                nombreUsuario= textFieldNombreUsuario.getText();
            }
        });

        passwordFieldContrasenna.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                contrasennaUsuario=passwordFieldContrasenna.getText();
            }
        });
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public String getContrasennaUsuario(){
        return contrasennaUsuario;
    }

    public boolean getCrearUsuario(){
        return crearUsuario;
    }

    public boolean getBotones(){
        return boton;
    }
}
