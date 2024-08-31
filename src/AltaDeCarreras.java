import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class AltaDeCarreras extends JFrame {
    private JPanel altaDeCarreras;
    private JLabel imagenUNTDF;
    private JButton paginaPrincipalButton;
    private JButton altaDeAlumnosButton;
    private JButton modificoCarreraButton;
    private JButton buscoAlumnosButton;
    private JButton altaDePlanDeButton;
    private JTextField nombreCarrera;
    private JTextField cantMateriasOp;
    private JTextField cantMateriasOb;
    private JComboBox cantidadAnnios;
    private JComboBox cantidadCuatri;
    private JCheckBox checkBox1;
    private JButton crearButton;
    private JButton cancelarButton;
    private JButton altaDeCarrerasButton;
    private boolean paginaPrincipal= false, altaDeAlumnos = false, modificoCarrera = false, buscoAlumnos = false,
            altaDePlanDe = false, nuevaCarrera=false, BaltaDeCarreras=false;
    private String nombreDeCarrera="";
    private int cantMatOb=0, cantMatOp=0, cuatriCarrera=0, annioCarrera=0;
    private CountDownLatch latch;

    public AltaDeCarreras() {
        setUndecorated(true);
        setContentPane(altaDeCarreras);
        setSize(1450,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal = true;
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos = true;
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDePlanDe = true;
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos = true;
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera = true;
                nuevaCarrera = false;
                BaltaDeCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        limpiarCampos();
                    }

            }
        });
        crearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //verifico que haya completado todo.
                if(validoTodo()){
                    nuevaCarrera=true;
                    latch.countDown();
                } else nuevaCarrera=false;

                //creo materia si es correcto y cierro pestaña
            }
        });


        nombreCarrera.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                nombreDeCarrera = nombreCarrera.getText();
            }
        });
        cantMateriasOp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String texto = cantMateriasOp.getText();
                if (texto.matches("\\d+"))
                    cantMatOp = Integer.parseInt(texto);
            }
        });
        cantMateriasOb.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String texto = cantMateriasOb.getText();
                if (texto.matches("\\d+"))
                    cantMatOb = Integer.parseInt(texto);
            }
        });
        cantidadCuatri.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String cuatri = (String) cantidadCuatri.getSelectedItem();
                    if (!cuatri.equals("Cantidad de cuatrimestres:")) {
                        cuatri = cuatri.substring(0, cuatri.length() - 1); // Elimino el °
                        if(cuatri.equals("1"))
                            cuatriCarrera = 3;
                        else cuatriCarrera = 6;
                    }
                }

            }
        });
        cantidadAnnios.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String annio = (String) cantidadAnnios.getSelectedItem();
                    if (!annio.equals("Cantidad de años:")) {
                        annio = annio.substring(0, annio.length() - 1); // Elimino el °
                        annioCarrera = Integer.parseInt(annio);
                    }
                }
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina alta de " +
                        "carreras.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void limpiarCampos() {
        nombreCarrera.setText("");
        cantMateriasOp.setText("");
        cantMateriasOb.setText("");
        cantidadAnnios.setSelectedIndex(0);
        cantidadCuatri.setSelectedIndex(0);
    }

    public boolean getPaginaPrincipal() {
        return paginaPrincipal;
    }

    public boolean getAltaDeAlumnos() {
        return altaDeAlumnos;
    }

    public boolean getModificoCarrera() {
        return modificoCarrera;
    }

    public boolean getBuscoAlumnos() {
        return buscoAlumnos;
    }

    public boolean getAltaDePlanDe() {
        return altaDePlanDe;
    }

    public String getNombreDeCarrera(){
        return nombreDeCarrera;
    }

    public int getCuatriCarrera(){
        return cuatriCarrera;
    }

    public int getAnnioCarrera(){
        return annioCarrera;
    }

    private boolean validoTodo(){
        String validoAnnios = (String) cantidadAnnios.getSelectedItem();
        String validoCuatri = (String) cantidadCuatri.getSelectedItem();
        if (nombreCarrera.getText().isEmpty() || cantMateriasOb.getText().isEmpty() || cantMateriasOp.getText().isEmpty() || validoAnnios.equals("Cantidad de años:") || validoCuatri.equals("Cantidad de cuatrimestres:")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!cantMateriasOp.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Cantidad de materias optativas debe se un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!cantMateriasOb.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Cantidad de materias obligatorias debe ser un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Se va a crear la carrera: " + getNombreDeCarrera(), "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Se creo la materia: " + getNombreDeCarrera(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                //cerrar y volver a abrir la pagina? o solo limpiar?
                limpiarCampos();
                return true;
            }
            else{
                return false;
            }
        }
    }

    public int getCantMatOb(){
        return cantMatOb;
    }

    public int getCantMatOp(){
        return cantMatOp;
    }

    public boolean getNuevaCarreras(){
        return nuevaCarrera;
    }

    public void setLatch(CountDownLatch latch){
        this.latch = latch;
    }

    public void setAltaDeAlumnos(boolean v) {
        this.altaDeAlumnos = v;
    }

    public void setAltaDeCarreras(boolean v) {
        this.BaltaDeCarreras = v;
    }

    public void setAltaPlanDeEstudio(boolean v) {
        this.altaDePlanDe = v;
    }

    public void setBuscoAlumnos(boolean v) {
        this.buscoAlumnos = v;
    }

    public void setPaginaPrincipal(boolean v){
        this.paginaPrincipal=v;
    }

    public boolean getAltaDeCarreras() {
        return BaltaDeCarreras;
    }
}
