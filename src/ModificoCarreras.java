import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private boolean paginaPrincipal=false, altaDeAlumnos = false, buscoAlumnos = false, altaDeCarreras = false, altaPlanDeEstudio = false, BmodificoCarreras=true;

    public ModificoCarreras(){
        setUndecorated(true);
        setContentPane(modificoCarreras);
        setSize(1300,400);
        setLocationRelativeTo(null);
        setVisible(true);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BmodificoCarreras=false;
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal=true;
                BmodificoCarreras=false;
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos=true;
                BmodificoCarreras=false;
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                BmodificoCarreras=false;
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                BmodificoCarreras=false;
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos=true;
                BmodificoCarreras=false;
                dispose();
            }
        });
        modificoCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina modifico" +
                        " carreras.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        comboBoxCarreras.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        modificarUnaMateriaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        modificarPlanDeEstudioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                dispose();
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }

    public boolean getPaginaPrincipal() {
        return paginaPrincipal;
    }

    public boolean getAltaDeAlumnos() {
        return altaDeAlumnos;
    }

    public boolean getAltaDeCarreras() {
        return altaDeCarreras;
    }

    public boolean getBuscoAlumnos() {
        return buscoAlumnos;
    }

    public boolean getAltaPlanDeEstudio() {
        return altaPlanDeEstudio;
    }

    public boolean getModificoCarreras() {
        return BmodificoCarreras;
    }

    public void setPaginaPrincipal(boolean paginaPrincipal) {
        this.paginaPrincipal = paginaPrincipal;
    }

    public void setAltaDeAlumnos(boolean altaDeAlumnos) {
        this.altaDeAlumnos = altaDeAlumnos;
    }

    public void setAltaDeCarreras(boolean altaDeCarreras) {
        this.altaDeCarreras = altaDeCarreras;
    }

    public void setAltaPlanDeEstudio(boolean altaPlanDeEstudio) {
        this.altaPlanDeEstudio = altaPlanDeEstudio;
    }

    public void setBuscoAlumnos(boolean buscoAlumnos) {
        this.buscoAlumnos = buscoAlumnos;
    }

    public void setModificoCarreras(boolean modificoCarreras) {
        this.BmodificoCarreras = modificoCarreras;
    }
}
