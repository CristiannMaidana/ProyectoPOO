import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

public class EditoDatosAlumnos extends JFrame {
    private JPanel editarDatosAlumnos;
    private JTextField cambiarNombreTextField;
    private JTextField cambiarLegajoTextField;
    private JPasswordField cambiarContrasennaPasswordField;
    private JButton guardarCambiosButton;
    private JButton borrarAlumnoButton;
    private JButton cancelarButton;
    private JTextField cambiarApellidoTextField;
    private JCheckBox checkBox1;
    private JTextField cambiarUsuarioTextField;
    private Alumnos usuario;
    private boolean hayCambiosDatos, borrarAlumno;
    private int alumnoAEliminar;
    private CountDownLatch latch;

    public EditoDatosAlumnos(Alumnos alumno, CountDownLatch latch) {
        this.latch = latch;
        this.usuario = alumno;
        setContentPane(editarDatosAlumnos);
        setUndecorated(true);
        setSize(500,280);
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
        guardarCambiosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (datosCargados() && hayCambios()) {
                    if (cambiarLegajoTextField.getText().matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Se guardaron los cambios.", "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                        guardarCambios();
                        reseteoPagina();
                        hayCambiosDatos = true;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "El DNI tiene que ser un numero " +
                                "valido.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "No hay cambios por hacer.", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        borrarAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Eliminar al alumno: "+
                        usuario.getLegajo()+"?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    usuario.setCarrera(null);
                    borrarAlumno = true; // sabe que hacer la pagina principal con esto
                    alumnoAEliminar = usuario.getLegajo(); //Usa eso para borrar al alumno del registro
                    latch.countDown();
                    dispose();
                }
                else {
                    reseteoPagina();
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Cancelar edición?",
                        "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    reseteoPagina();
                    latch.countDown();
                    dispose();
                }
            }
        });
    }

    private boolean datosCargados(){
        boolean datosCargados = false;
        if (!cambiarApellidoTextField.getText().isEmpty() ||
                !cambiarContrasennaPasswordField.getText().isEmpty() || !cambiarNombreTextField.getText().isEmpty() ||
                !cambiarLegajoTextField.getText().isEmpty()){
            datosCargados =true;
        }
        return datosCargados;
    }

    private boolean hayCambios(){
        boolean hayCambios = false;
        String nuevoAlumno = cambiarNombreTextField.getText(), nuevoApellido = cambiarApellidoTextField.getText(),
                nuevoContrasenna = cambiarContrasennaPasswordField.getText(),
                cambiarLegajo = cambiarLegajoTextField.getText(), actualNombre = usuario.getNombre(),
                actualContrasenna = usuario.getContrasenna(), actualApellido = usuario.getApellido();
        int actualLegajo= usuario.getLegajo();

         if (!nuevoAlumno.equals(actualNombre) || !nuevoApellido.equals(actualApellido) ||
                 !nuevoContrasenna.equals(actualContrasenna) || !cambiarLegajo.equals(actualLegajo)){
             hayCambios = true;
         }


        return hayCambios;
    }

    private void guardarCambios(){
        String nuevoNombre = cambiarNombreTextField.getText(), nuevoApellido = cambiarApellidoTextField.getText(),
                nuevoContrasenna = cambiarContrasennaPasswordField.getText(),
                nuevoLegajo = cambiarLegajoTextField.getText(), actualNombre = usuario.getNombre(),
                actualContrasenna = usuario.getContrasenna(), actualApellido = usuario.getApellido(), actualLegajoTxt;
        int actualLegajo= usuario.getLegajo();
        actualLegajoTxt = Integer.toString(actualLegajo);


        if (!nuevoNombre.equals(actualNombre) && !nuevoNombre.isEmpty()){
            usuario.setNombre(nuevoNombre);
        }
        if( !nuevoApellido.equals(actualApellido ) && !nuevoApellido.isEmpty()){
            usuario.setApellido(nuevoApellido);
        }
        if (!nuevoContrasenna.equals(actualContrasenna) && !nuevoContrasenna.isEmpty()) {
            usuario.setContrasenna(nuevoContrasenna);
        }
        if(!nuevoLegajo.equals(actualLegajoTxt) && !nuevoLegajo.isEmpty()){
            usuario.setLegajo(Integer.parseInt(nuevoLegajo));
        }
    }

    private void reseteoPagina(){
        cambiarNombreTextField.setText("");
        cambiarLegajoTextField.setText("");
        cambiarApellidoTextField.setText("");
        cambiarContrasennaPasswordField.setText("");
    }

    public boolean getHayCambios(){
        return hayCambiosDatos;
    }

    public boolean getBorrarAlumno(){
        return borrarAlumno;
    }
}
