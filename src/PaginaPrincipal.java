import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

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
    private JButton paginaPrincipalButton;
    private JLabel tituloPrincipal;
    private boolean altaDeAlumnos = false, altaDeCarreras = false, altaDePlanDeEstudio = false, buscoAlumnos = false,
            modificoCarrera = false, BpaginaPrincipal=false;
    private CountDownLatch latch;

    //Constructor
    public PaginaPrincipal(CountDownLatch latch) {
        this.latch = latch;
        setUndecorated(true);
        setContentPane(paginaPrincipal);
        setSize(1300,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos = true;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras = true;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDePlanDeEstudio = true;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos = true;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera = true;
                latch.countDown();
                dispose();
            }
        });
        cierroPestaña.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina principal.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //Metodos get
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
    public boolean getPaginaPrincipal(){
        return BpaginaPrincipal;
    }

    //Metodos set
    public void setAltaDeAlumnos(boolean v) {
        this.altaDeAlumnos = v;
    }
    public void setAltaDeCarreras(boolean v) {
        this.altaDeCarreras = v;
    }
    public void setAltaPlanDeEstudio(boolean v) {
        this.altaDePlanDeEstudio = v;
    }
    public void setBuscoAlumnos(boolean v) {
        this.buscoAlumnos = v;
    }
    public void setPaginaPrincipal(boolean v){
        this.BpaginaPrincipal=v;
    }
    public void setModificoCarreras(boolean v){
        this.modificoCarrera=v;
    }
}
