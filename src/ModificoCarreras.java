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
    private JButton modificarPlanDeEstudioButton;
    private JTextField textFieldCambiaMateriaCarrera;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JList listMaterias;
    private JCheckBox checkBox1;
    private JLabel seleccioneUnaMateiraLabel;
    private boolean paginaPrincipal=false, altaDeAlumnos = false, buscoAlumnos = false, altaDeCarreras = false,
            altaPlanDeEstudio = false, BmodificoCarreras=false, modificarUnaMateria=false, modificarUnaPlan=false;
    private AlmacenCarreras almacenCarreras;
    private String nombreCarreraElegida="";
    private Carreras carreraElegida;
    private CountDownLatch latch;

    public ModificoCarreras(AlmacenCarreras almacenCarreras){
        this.almacenCarreras = almacenCarreras;
        setUndecorated(true);
        setContentPane(modificoCarreras);
        setSize(1300,400);
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
                modificarUnaPlan=false;
                if (nombreCarreraElegida.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Elija una carrera antes.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    cargoMaterias();
                    JOptionPane.showMessageDialog(null, "Se cargaron las materias de la carrera:" +
                            " "+nombreCarreraElegida+" exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    seleccioneUnaMateiraLabel.setText("Seleccione una materia:");
                    aceptarButton.setText("Hacer obligatoria");
                    cancelarButton.setText("Hacer optativa");
                }
            }
        });
        modificarPlanDeEstudioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificarUnaMateria=false;
                modificarUnaPlan=true;
                if (nombreCarreraElegida.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Elija una carrera antes.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    cargoPlanes();
                    JOptionPane.showMessageDialog(null, "Se cargaron los planes de estudio " +
                            "exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    seleccioneUnaMateiraLabel.setText("Seleccione un plan de estudio:");
                    aceptarButton.setText("Asignar plan de estudio");
                    cancelarButton.setText("Cancelar");
                }
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
                else if (modificarUnaPlan){
                    String nombrePlan = (String) listMaterias.getModel().getElementAt(index);
                    textFieldCambiaMateriaCarrera.setText(nombrePlan);
                    JOptionPane.showMessageDialog(null, "La carrera tiene el plan de estudio: "+
                                    carreraElegida.getPlanDeEstudio().toString()+ ".", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
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

    public void setAlmacenCarreras(AlmacenCarreras almacenCarreras) {
        this.almacenCarreras = almacenCarreras;
    }

    public void cargoCarreras(){
        for(int i=0; i<almacenCarreras.getCantidadCarreras(); i++){
            comboBoxCarreras.addItem(almacenCarreras.getCarrera(i).getNombre());
        }
    }

    public void cargoMaterias(){
        DefaultListModel modelo = new DefaultListModel();
        for (int i=0; i<carreraElegida.getAnniosCarrera(); i++){
            for(int j=0; j<carreraElegida.getCuatriCarrera(); j++){
                modelo.addElement(carreraElegida.getMateriasPorAnnioYMateria(i,j).getNombreDeMateria());
            }
        }
        listMaterias.setModel(modelo);
    }

    public void reseteoModificarMaterias(){
        textFieldCambiaMateriaCarrera.setText("");
        cargoMaterias();
    }

    public void setLatch(CountDownLatch latch){
        this.latch = latch;
    }

    public void cargoPlanes(){
        DefaultListModel modelo = new DefaultListModel();
        modelo.addElement("Plan de estudio 'A'");
        modelo.addElement("Plan de estudio 'B'");
        modelo.addElement("Plan de estudio 'C'");
        modelo.addElement("Plan de estudio 'D'");
        modelo.addElement("Plan de estudio 'E'");
        listMaterias.setModel(modelo);
    }
}
