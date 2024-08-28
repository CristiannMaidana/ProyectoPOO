import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaginaPrincipal extends JFrame {
    private JLabel imagenUNTDFLabel;
    private JButton altaDeAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton altaDePlanDeButton;
    private JButton buscoAlumnoButton;
    private JButton modificoCarreraButton;
    private JLabel imagenSIULabel;
    private JPanel paginaPrincipal;
    private JCheckBox cierroPestaña;
    private JLabel tituloPrincipal;
    private boolean altaDeAlumnos = false, altaDeCarreras = false, altaDePlanDeEstudio = false, buscoAlumnos = false, modificoCarrera = false;

    public PaginaPrincipal() {
        setUndecorated(true);
        setContentPane(paginaPrincipal);
        setSize(850,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos = true;
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras = true;
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDePlanDeEstudio = true;
                dispose();
            }
        });
        buscoAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos = true;
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera = true;
                dispose();
            }
        });
        cierroPestaña.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }

    public boolean getAltaDeAlumnosButton() {
        return altaDeAlumnos;
    }

    public boolean getAltaDeCarrerasButton() {
        return altaDeCarreras;
    }

    public boolean getAltaDePlanDeButton() {
        return altaDePlanDeEstudio;
    }

    public boolean getBuscoAlumnoButton() {
        return buscoAlumnos;
    }

    public boolean getModificoCarreraButton() {
        return modificoCarrera;
    }
}
