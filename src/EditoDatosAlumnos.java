import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private boolean hayCambiosDatos;

    public EditoDatosAlumnos(Alumnos alumno) {
        this.usuario = alumno;
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
                //Si algo esta cambiado y no vacio caso correcto
                if (datosCargados() && hayCambios()) {
                    JOptionPane.showMessageDialog(null, "Se guardaron los cambios.", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                    guardarCambios();
                    reseteoPagina();
                    hayCambiosDatos = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "No hay cambios por hacer,", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
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

    private boolean datosCargados(){
        boolean datosCargados = false;
        if (!cambiarApellidoTextField.getText().isEmpty() || !cambiarUsuarioTextField.getText().isEmpty() ||
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
                actualContrasenna = usuario.getContrasenna(), actualApellido;
        int actualLegajo= usuario.getLegajo();

        //extraigo el apellido almacenado
         actualApellido = actualNombre.substring(actualNombre.indexOf(" ") + 1);
         //Dejo el nombre solo.
         actualNombre = actualNombre.substring(0, actualNombre.indexOf(" "));
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
                actualContrasenna = usuario.getContrasenna(), actualApellido;
        int actualLegajo= usuario.getLegajo();
        //extraigo el apellido almacenado
        actualApellido = actualNombre.substring(actualNombre.indexOf(" ") + 1);
        //Dejo el nombre solo.
        actualNombre = actualNombre.substring(0, actualNombre.indexOf(" "));

        if (!nuevoNombre.equals(actualNombre)){
            usuario.setNombre(nuevoNombre + actualApellido);
        }
        if( !nuevoApellido.equals(actualApellido) ){
            usuario.setNombre(actualNombre+nuevoApellido);
        }
        if (!nuevoContrasenna.equals(actualContrasenna)) {
            usuario.setContrasenna(nuevoContrasenna);
        }
        if(!nuevoLegajo.equals(actualLegajo)){
            usuario.setLegajo(Integer.parseInt(nuevoLegajo));
        }
    }

    private void reseteoPagina(){
        cambiarNombreTextField.setText("");
        cambiarLegajoTextField.setText("");
        cambiarApellidoTextField.setText("");
        cambiarContrasennaPasswordField.setText("");
    }
}
