import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

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
    private JButton borrarCarreraButton;
    private JTextField textFieldCambiaMateriaCarrera;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JList listMaterias;
    private JCheckBox checkBox1;
    private JLabel seleccioneUnaMateiraLabel;
    private JButton filtrarOpatitvasButton;
    private JButton filtrarObligatoriasButton;
    private JButton filtrarMateriasButton;
    private boolean paginaPrincipal=false, altaDeAlumnos = false, buscoAlumnos = false, altaDeCarreras = false,
            altaPlanDeEstudio = false, BmodificoCarreras=false, modificarUnaMateria=false, borroCarrera =false;
    private AlmacenCarreras almacenCarreras;
    private AlumnosRegistrados alumnosRegistrados;
    private String nombreCarreraElegida="";
    private Carreras carreraElegida;
    private CountDownLatch latch;

    //Constructor
    public ModificoCarreras(AlmacenCarreras almacenCarreras){
        this.almacenCarreras = almacenCarreras;
        filtrarObligatoriasButton.setVisible(false);
        filtrarOpatitvasButton.setVisible(false);
        filtrarMateriasButton.setVisible(false);
        setUndecorated(true);
        setContentPane(modificoCarreras);
        setSize(1300,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cargoCarreras();
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal=true;
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos=true;
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras=true;
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaPlanDeEstudio=true;
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos=true;
                BmodificoCarreras=false;
                latch.countDown();
                dispose();
            }
        });
        modificoCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina modifico" +
                        " carreras.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        comboBoxCarreras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreCarreraElegida = (String) comboBoxCarreras.getSelectedItem();
                for(int i=0; i<almacenCarreras.getCantidadCarreras(); i++){
                    if (almacenCarreras.getCarrera(i).getNombre().equals(nombreCarreraElegida)){
                        carreraElegida = almacenCarreras.getCarrera(i);
                        break;
                    }
                }
            }
        });
        modificarUnaMateriaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificarUnaMateria=true;
                borroCarrera =false;
                if (nombreCarreraElegida.equals("Elija una carrera:") || nombreCarreraElegida.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Elija una carrera antes.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    cargoMaterias();
                    textFieldCambiaMateriaCarrera.setText("");
                    JOptionPane.showMessageDialog(null, "Se cargaron las materias de la carrera:" +
                            " "+nombreCarreraElegida+" exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    seleccioneUnaMateiraLabel.setText("Seleccione una materia:");
                    aceptarButton.setText("Hacer obligatoria");
                    cancelarButton.setText("Hacer optativa");
                    filtrarObligatoriasButton.setVisible(true);
                    filtrarOpatitvasButton.setVisible(true);
                    filtrarMateriasButton.setVisible(true);
                }
            }
        });
        borrarCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultListModel modelo = new DefaultListModel();
                listMaterias.setModel(modelo);
                textFieldCambiaMateriaCarrera.setText("");
                seleccioneUnaMateiraLabel.setText("Seleccione una carrera:");
                modificarUnaMateria=false;
                borroCarrera =true;
                aceptarButton.setText("Borrar carrera");
                cancelarButton.setText("Cancelar");
                cargoCarreraslist();
            }
        });
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(aceptarButton.getText().equals("Hacer obligatoria")){
                    if(textFieldCambiaMateriaCarrera.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Seleccione una materia antes.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String materiaACambiar = textFieldCambiaMateriaCarrera.getText();
                        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere hacer la materia: "+
                                materiaACambiar+" obligatoria?", "Aviso", JOptionPane.YES_NO_OPTION);
                        if (respuesta == JOptionPane.YES_OPTION){
                            carreraElegida.getMateriasPorNombre(materiaACambiar).setObligatoria(true);
                            carreraElegida.getMateriasPorNombre(materiaACambiar).setOptativa(false);
                            JOptionPane.showMessageDialog(null, "Se hizo la materia obligatoria" +
                                    " correctamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            reseteoModificarMaterias();
                        }
                    }
                }
                else if(aceptarButton.getText().equals("Borrar carrera")){
                    if(textFieldCambiaMateriaCarrera.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Seleccione una carrera antes.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String carreraAEliminar = textFieldCambiaMateriaCarrera.getText();
                        boolean alumnosCursando = false;
                        //Deberia buscar un metodo mas optimo de busqueda o agregar un boolean a donde corresponda y menos consultas de invocacion
                        for (int i=0; i<alumnosRegistrados.getSize(); i++){
                            if (alumnosRegistrados.getAlumno(i).getCarrera() != null)
                                if (alumnosRegistrados.getAlumno(i).getCarrera().getNombre().equals(carreraAEliminar)){
                                    alumnosCursando = true;
                                    break;
                                }
                        }
                        if (alumnosCursando){
                            JOptionPane.showMessageDialog(null, "No puede elimnar la carrera: "+
                                    carreraAEliminar+ " porque teiene alumnos inscriptos", "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar la carrera: " +
                                    carreraAEliminar + "?", "Aviso", JOptionPane.YES_NO_OPTION);
                            if (respuesta == JOptionPane.YES_OPTION) {
                                for (int i = 0; i < almacenCarreras.getCantidadCarreras(); i++) {
                                    if (almacenCarreras.getCarrera(i).getNombre().equals(carreraAEliminar)) {
                                        almacenCarreras.borrarCarrera(i);
                                        break;
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Se borro la carrera: " +
                                        carreraAEliminar + " correctamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                reseteoTodoDos();
                            }
                        }
                    }
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(cancelarButton.getText().equals("Hacer optativa")){
                    if(textFieldCambiaMateriaCarrera.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Seleccione una materia antes.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String materiaACambiar = textFieldCambiaMateriaCarrera.getText();
                        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quiere hacer la materia: "+
                                materiaACambiar+" optativa?", "Aviso", JOptionPane.YES_NO_OPTION);
                        if (respuesta == JOptionPane.YES_OPTION){
                            carreraElegida.getMateriasPorNombre(materiaACambiar).setOptativa(true);
                            carreraElegida.getMateriasPorNombre(materiaACambiar).setObligatoria(false);
                            JOptionPane.showMessageDialog(null, "Se hizo la materia optativa" +
                                    " correctamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            reseteoModificarMaterias();
                        }
                    }
                }
                else {
                    reseteoTodo();
                }

            }
        });
        listMaterias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = listMaterias.getSelectedIndex();
                if (modificarUnaMateria) {
                    String nombreMateria = (String) listMaterias.getModel().getElementAt(index);
                    textFieldCambiaMateriaCarrera.setText(nombreMateria);
                    if (carreraElegida.getMateriasPorNombre(nombreMateria).getObligatoria()) {
                        JOptionPane.showMessageDialog(null, "La materia " + nombreMateria + ", es " +
                                        "obligatoria seleccione que tipo de materia será", "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (carreraElegida.getMateriasPorNombre(nombreMateria).getOptativa()) {
                        JOptionPane.showMessageDialog(null, "La materia " + nombreMateria + ", es " +
                                "optativa seleccione que tipo de materia será", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else if (borroCarrera){
                    String nombreCarrera = (String) listMaterias.getModel().getElementAt(index);
                    textFieldCambiaMateriaCarrera.setText(nombreCarrera);
                    JOptionPane.showMessageDialog(null, "La carrera seleccionada es: "+
                                    nombreCarrera +".", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        filtrarOpatitvasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargoMateriasOptativas();
            }
        });
        filtrarObligatoriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargoMateriasObligatorias();
            }
        });
        filtrarMateriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargoMaterias();
            }
        });
    }

    //Metodos void
    private void cargoCarreras(){
        comboBoxCarreras.removeAllItems();
        comboBoxCarreras.addItem("Elija una carrera:");
        for(int i=0; i<almacenCarreras.getCantidadCarreras(); i++){
            comboBoxCarreras.addItem(almacenCarreras.getCarrera(i).getNombre());
        }
    }
    private void cargoMaterias(){
        DefaultListModel modelo = new DefaultListModel();
        for (int i=0; i<carreraElegida.getAnniosCarrera(); i++){
            for(int j=0; j<carreraElegida.getCuatriCarrera(); j++){
                modelo.addElement(carreraElegida.getMateriasPorAnnioYMateria(i,j).getNombreDeMateria());
            }
        }
        listMaterias.setModel(modelo);
    }
    private void reseteoModificarMaterias(){
        textFieldCambiaMateriaCarrera.setText("");
        cargoMaterias();
    }
    private void reseteoTodo(){
        DefaultListModel modelo = new DefaultListModel();
        comboBoxCarreras.setSelectedIndex(0);
        textFieldCambiaMateriaCarrera.setText("");
        seleccioneUnaMateiraLabel.setText("");
        listMaterias.setModel(modelo);
        aceptarButton.setText("Aceptar");
        cancelarButton.setText("Cancelar");
    }
    private void reseteoTodoDos(){
        cargoCarreras();
        cargoCarreraslist();
        textFieldCambiaMateriaCarrera.setText("");
        seleccioneUnaMateiraLabel.setText("");
        aceptarButton.setText("Aceptar");
        cancelarButton.setText("Cancelar");
    }
    private void cargoCarreraslist(){
        DefaultListModel modelo = new DefaultListModel();
        for(int i=0; i<almacenCarreras.getCantidadCarreras(); i++){
            modelo.addElement(almacenCarreras.getCarrera(i).getNombre());
        }
        listMaterias.setModel(modelo);
    }
    private void cargoMateriasOptativas(){
        DefaultListModel modelo = new DefaultListModel();
        for (int i=0; i<carreraElegida.getAnniosCarrera(); i++){
            for(int j=0; j<carreraElegida.getCuatriCarrera(); j++){
                if (carreraElegida.getMateriasPorAnnioYMateria(i,j).getOptativa()){
                    modelo.addElement(carreraElegida.getMateriasPorAnnioYMateria(i,j).getNombreDeMateria());
                }
            }
        }
        listMaterias.setModel(modelo);
    }
    private void cargoMateriasObligatorias(){
        DefaultListModel modelo = new DefaultListModel();
        for (int i=0; i<carreraElegida.getAnniosCarrera(); i++){
            for(int j=0; j<carreraElegida.getCuatriCarrera(); j++){
                if (carreraElegida.getMateriasPorAnnioYMateria(i,j).getObligatoria()){
                    modelo.addElement(carreraElegida.getMateriasPorAnnioYMateria(i,j).getNombreDeMateria());
                }
            }
        }
        listMaterias.setModel(modelo);
    }

    //Metodos gets
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

    //Metodos set
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
    public void setLatch(CountDownLatch latch){
        this.latch = latch;
    }
    public void setAlumnosRegistrados(AlumnosRegistrados alumnosRegistrados){
        this.alumnosRegistrados = alumnosRegistrados;
    }
}
