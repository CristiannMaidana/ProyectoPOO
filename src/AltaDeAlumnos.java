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
    private JComboBox comboBoxCarrera;
    private JTextField textFieldContrasenna;
    private JTextField textFieldDNI;
    private JTextField textFieldApellido;
    private JTextField textFieldNombre;
    private JTextField textFieldUsuario;
    private JButton crearNuevoAlumnoButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private boolean paginaPrincipal=false, modificoCarrera=false, altaPlanDeEstudio=false, altaDeCarreras=false, buscoAlumnos=false, creoNuevoAlumno = false, cancelar=false;
    private CountDownLatch latch;
    private String nombre="", apellido="", contrasenna="", usuario="", carrera="";
    private int dni=0;

    public AltaDeAlumnos(CountDownLatch latch){
        this.latch = latch;
        setUndecorated(true);
        setContentPane(altaDeAlumnos);
        setLocationRelativeTo(null);
        setSize(1000,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkBox1.addMouseListener(new MouseAdapter() {
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
                paginaPrincipal=true;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos=true;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarrera=true;
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
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    limpiarTodo();
                }
            }
        });

        textFieldUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario=textFieldUsuario.getText();
            }
        });
        textFieldNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre=textFieldNombre.getText();
            }
        });
        textFieldApellido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellido=textFieldApellido.getText();
            }
        });
        textFieldDNI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valido = textFieldDNI.getText();
                if(!valido.matches("")){
                    dni=Integer.parseInt(valido);
                }
            }
        });
        textFieldContrasenna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contrasenna=textFieldContrasenna.getText();
            }
        });
    }

    private void limpiarTodo() {
        textFieldContrasenna.setText("");
        textFieldDNI.setText("");
        textFieldApellido.setText("");
        textFieldNombre.setText("");
        textFieldUsuario.setText("");
        comboBoxCarrera.setSelectedIndex(0);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public String getUsuario() {
        return usuario;
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

    private boolean validoTodo(){
        if (textFieldUsuario.getText().isEmpty() || textFieldApellido.getText().isEmpty() || textFieldContrasenna.getText().isEmpty() || textFieldDNI.getText().isEmpty() || textFieldNombre.getText().isEmpty() || textFieldUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!textFieldDNI.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El dni debe ser un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Se va a crear al alumno: " + getNombre()+" "+getApellido(), "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Se creo al usuario: "  + getNombre()+" "+getApellido(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                //cerrar y volver a abrir la pagina? o solo limpiar?
                limpiarTodo();
                return true;
            }
            else{
                return false;
            }
        }
    }
}
