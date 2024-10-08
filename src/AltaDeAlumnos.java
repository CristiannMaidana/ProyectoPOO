import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class AltaDeAlumnos extends JFrame {
    private JPanel altaDeAlumnos;
    private JButton paginaPrincipalButton;
    private JButton modificoCarreraButton;
    private JButton altaDePlanDeButton;
    private JButton altaDeCarrerasButton;
    private JButton buscoAlumnosButton;
    private JTextField textFieldDNI;
    private JTextField textFieldApellido;
    private JTextField textFieldNombre;
    private JButton crearNuevoAlumnoButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private JButton altaDeAlumnosButton;
    private JPasswordField passwordFieldContrasenna;
    private boolean paginaPrincipal=false, modificoCarrera=false, altaPlanDeEstudio=false, altaDeCarreras=false,
            buscoAlumnos=false, BaltaDeAlumnos=true, creoNuevoAlumno = false;
    private CountDownLatch latch;
    private String nombre="", apellido="", contrasenna="", usuario="", carrera="";
    private int dni=0;
    private AlumnosRegistrados alumnosRegistrados;

    public AltaDeAlumnos(){
        setUndecorated(true);
        setContentPane(altaDeAlumnos);
        setLocationRelativeTo(null);
        setSize(1300,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BaltaDeAlumnos=false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal=true;
                BaltaDeAlumnos=false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                BaltaDeAlumnos = false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                BaltaDeAlumnos=false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos=true;
                BaltaDeAlumnos=false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera=true;
                BaltaDeAlumnos=false;
                creoNuevoAlumno=false;
                latch.countDown();
                dispose();
            }
        });
        crearNuevoAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validoTodo()){
                    creoNuevoAlumno=true;
                    latch.countDown();
                } else creoNuevoAlumno=false;
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldApellido.getText().isEmpty() &&
                        new String(passwordFieldContrasenna.getPassword()).isEmpty() && textFieldDNI.getText().isEmpty() &&
                        textFieldNombre.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay nada que cancelar.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        limpiarTodo();
                    }
                }
            }
        });
        textFieldNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                nombre=textFieldNombre.getText();
            }
        });
        textFieldApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                apellido=textFieldApellido.getText();
            }
        });
        textFieldDNI.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String valido = textFieldDNI.getText();
                if (valido.matches("\\d+"))
                    dni=Integer.parseInt(valido);
                else
                    dni=0;
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina alta de alumnos.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
                contrasenna=new String(passwordFieldContrasenna.getPassword());
            }
        });
    }

    //Metodos void
    private void limpiarTodo() {
        passwordFieldContrasenna.setText("");
        textFieldDNI.setText("");
        textFieldApellido.setText("");
        textFieldNombre.setText("");
    }

    //Metodos get
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getContrasenna() {
        return contrasenna;
    }
    public String getCarrera() {
        return carrera;
    }
    public int getDni() {
        return dni;
    }
    public boolean getPaginaPrincipal() {
        return paginaPrincipal;
    }
    public boolean getModificoCarrera() {
        return modificoCarrera;
    }
    public boolean getAltaPlanDeEstudio() {
        return altaPlanDeEstudio;
    }
    public boolean getAltaDeCarreras() {
        return altaDeCarreras;
    }
    public boolean getBuscoAlumnos() {
        return buscoAlumnos;
    }
    public boolean getCreoNuevoAlumno(){
        return creoNuevoAlumno;
    }
    public boolean getAltaDeAlumnos(){
        return BaltaDeAlumnos;
    }

    //Metodos set
    public void setAltaDeAlumnos(boolean v) {
        this.BaltaDeAlumnos = v;
    }
    public void setAltaDeCarreras(boolean v) {
        this.altaDeCarreras = v;
    }
    public void setAltaPlanDeEstudio(boolean v) {
        this.altaPlanDeEstudio = v;
    }
    public void setBuscoAlumnos(boolean v) {
        this.buscoAlumnos = v;
    }
    public void setPaginaPrincipal(boolean v){
        this.paginaPrincipal=v;
    }
    public void setModificoCarreras(boolean v){
        this.modificoCarrera=v;
    }
    public void setLatch(CountDownLatch latch){
        this.latch=latch;
    }
    public void setRegistroAlumnos(AlumnosRegistrados alumnosRegistrados){
        this.alumnosRegistrados = alumnosRegistrados;
    }

    private boolean validoTodo(){
        if (textFieldApellido.getText().isEmpty() ||
                new String(passwordFieldContrasenna.getPassword()).isEmpty() || textFieldDNI.getText().isEmpty() ||
                textFieldNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!textFieldNombre.getText().matches("[a-zA-Z]*")) {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar un nombre valido",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!textFieldApellido.getText().matches("[a-zA-Z]*")) {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar un apellido valido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!textFieldDNI.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El dni debe ser un numero valido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            if (alumnosRegistrados.buscoPorDNI(Integer.parseInt(textFieldDNI.getText())) != null) {
                JOptionPane.showMessageDialog(null, "El alumno ya existe.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else {
                int respuesta = JOptionPane.showConfirmDialog(null, "Se va a crear al alumno: " +
                        getNombre() + " " + getApellido(), "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Se creo al usuario: " + getNombre() + " " + getApellido(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                    //cerrar y volver a abrir la pagina? o solo limpiar?
                    limpiarTodo();
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
