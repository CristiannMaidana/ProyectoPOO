import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class VCargoNotas extends JFrame{
    private JLabel nombreAlumno;
    private JLabel nombreMateria;
    private JTextField textFieldNotaParcial;
    private JTextField textFieldNotaExamen;
    private JList listMateriasParaSeleccionar;
    private JList listMateriasAprobadas;
    private JPanel panelCargoNotas;
    private JButton cargoNotasButton;
    private JButton aceptarButton;
    private JList listMateriasFaltaExamen;
    private JList listMateriasDesaprobadas;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private double notaParcial, notaExamen;
    private String nMateria;
    private final DefaultListModel<String> modelAprobado = new DefaultListModel<>(),
            modelExamen = new DefaultListModel<>(),modelDesaprobado = new DefaultListModel<>();
    private final Alumnos alumno;
    private boolean opciones, masMaterias;
    private CountDownLatch latch;

    //Constructor
    public VCargoNotas(Alumnos alumno, CountDownLatch latch){
        this.alumno=alumno;
        this.latch=latch;
        cargoMateriasAprobadasAnteriores(modelAprobado);
        JScrollPane scrollPane = new JScrollPane(panelCargoNotas);

        setContentPane(scrollPane);
        setUndecorated(true);
        setTitle("Ingrese notas de alumno");
        setSize(700,450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nombreAlumno.setText(alumno.toString());
        cargoMateriasEnCurso(alumno);
        textFieldNotaParcial.setText("");
        setVisible(true);
        nombreMateria.setText("Seleccione una materia.");
        textFieldNotaExamen.setEnabled(false);
        listMateriasParaSeleccionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = listMateriasParaSeleccionar.getSelectedIndex();
                if (notaParcial == 0) {
                    nMateria = (String) listMateriasParaSeleccionar.getModel().getElementAt(index);
                    nombreMateria.setText(nMateria);
                }
                else {
                    JOptionPane.showMessageDialog(VCargoNotas.this,
                            "Debe cargar la nota del examen antes de poder seleccionar otra materia. ",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
                if (alumno.getCarrera().getMateriasPorNombre(nMateria).getCursadaAprobada() && !alumno.getCarrera().getMateriasPorNombre(nMateria).getNotaExamenFinal()) {
                    textFieldNotaParcial.setEnabled(false);
                    textFieldNotaExamen.setEnabled(true);
                    if (textFieldNotaExamen.isEnabled()) {
                        if (textFieldNotaExamen.isEnabled() && !textFieldNotaExamen.getText().isEmpty()) {
                            String notaExamenIngresada = textFieldNotaExamen.getText();
                            if (notaExamenIngresada.matches("\\d+")) {
                                notaExamen = Integer.parseInt(textFieldNotaExamen.getText());
                            } else{
                                JOptionPane.showMessageDialog(null, "Ingrese una nota numerica " +
                                        "valida.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                if (textFieldNotaParcial.isEnabled()) {
                    textFieldNotaParcial.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            super.focusLost(e);
                            if (textFieldNotaParcial.isEnabled() && !textFieldNotaParcial.getText().isEmpty()) {
                                String notaIngresada = textFieldNotaParcial.getText();
                                if (notaIngresada.matches("\\d+")){
                                    notaParcial = Integer.parseInt(textFieldNotaParcial.getText());
                                } else{
                                    JOptionPane.showMessageDialog(null, "Ingrese una nota" +
                                            " numerica valida.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    });
                }
            }
        });
        cargoNotasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldNotaParcial.isEnabled() && textFieldNotaParcial.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingreso una nota valida para poder " +
                            "cargar las notas", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (textFieldNotaExamen.isEnabled() && textFieldNotaExamen.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingreso una nota valida para poder " +
                            "cargar las notas", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    opciones = true;
                    if (notaParcial != 0 && !textFieldNotaExamen.isEnabled())
                        opcionesNotaParcial(opciones);
                    else if (notaExamen != 0 && !textFieldNotaParcial.isEnabled())
                        opcionesNotaExamen(opciones);
                }
            }
        });
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultListModel<String> model = (DefaultListModel<String>) listMateriasParaSeleccionar.getModel();
                if (model.isEmpty()) {
                    masMaterias = true;
                    latch.countDown();
                    dispose();
                }
                else {
                    masMaterias = false;
                    JOptionPane.showMessageDialog(VCargoNotas.this,
                            "No se puede cargar más materias hasta terminar de cargar las notas de las materias seleccionadas.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        textFieldNotaExamen.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (textFieldNotaExamen.isEnabled() && !textFieldNotaExamen.getText().isEmpty()) {
                    notaExamen = Integer.parseInt(textFieldNotaExamen.getText());
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la carga de" +
                        " notas?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    latch.countDown();
                    dispose();
                }
            }
        });
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
    }

    //Metodos void
    private void cargoMateriasAprobadasAnteriores(DefaultListModel<String> model){
        for (int i = 0; i <alumno.getCarrera().getAnniosCarrera(); i++){
            for (int j = 0; j <alumno.getCarrera().getCuatriCarrera(); j++){
                if (alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNotaExamenFinal())
                    model.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNombreDeMateria());
            }
        }
        listMateriasAprobadas.setModel(model);
    }
    public void cargoMateriasEnCurso(Alumnos alumnos){
        DefaultListModel <String> model = new DefaultListModel<>();
        for (byte i=0;i<3;i++){
            if (alumnos.getMateriasAlmacenadas(i) != null)
                model.addElement(alumnos.getMateriasAlmacenadas(i).getNombreDeMateria());
        }
        listMateriasParaSeleccionar.setModel(model);
    }
    public void cargoMateriasAprobadas (String materiaAprobada,  DefaultListModel<String> model ){
        model.addElement(materiaAprobada);
        listMateriasAprobadas.setModel(model);
    }
    public void cargoMateriasDesaprobadas(String materiaAprobada,  DefaultListModel<String> model ){
        model.addElement(materiaAprobada);
        listMateriasDesaprobadas.setModel(model);
    }
    public void cargoMateriasExamen (String materiaExamen, DefaultListModel<String> model ){
        model.addElement(materiaExamen);
        listMateriasFaltaExamen.setModel(model);
    }
    public void opcionesNotaParcial(boolean opciones){
        if (opciones){
            if (nMateria != null && notaParcial != 0){
                alumno.setNotasParcial(notaParcial,nMateria);
                    if (notaParcial >=8){
                        textFieldNotaParcial.setText("");
                        textFieldNotaParcial.setEnabled(true);
                        JOptionPane.showMessageDialog(VCargoNotas.this,
                                "Promociono la materia, no debe cargar nota del examen final.",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                        cargoMateriasAprobadas(nMateria, modelAprobado);
                        cargoMateriasEnCurso(alumno);
                        notaParcial=0;
                    }

                    else if (notaParcial<4){
                        JOptionPane.showMessageDialog(VCargoNotas.this,
                                "Desaprobo la cursada",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                        cargoMateriasDesaprobadas(nMateria,modelExamen);
                        cargoMateriasEnCurso(alumno);
                        textFieldNotaParcial.setText("");
                        textFieldNotaParcial.setEnabled(true);
                        notaParcial=0;
                    }
                    else {
                        JOptionPane.showMessageDialog(VCargoNotas.this,
                                "Aprobo la cursada, pero debe cargar nota del examen final",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                        cargoMateriasExamen(nMateria,modelExamen);
                        cargoMateriasEnCurso(alumno);
                        textFieldNotaParcial.setText("");
                        textFieldNotaParcial.setEnabled(true);
                        notaParcial=0;
                    }
            }
            opciones=false;
        }
    }
    public void opcionesNotaExamen(boolean opciones){
        if (opciones){
            if (nMateria != null && notaExamen != 0){
                alumno.setNotasExamen(notaExamen,nMateria);
                    if (notaExamen >=4){
                        textFieldNotaExamen.setText("");
                        textFieldNotaParcial.setEnabled(true);
                        textFieldNotaExamen.setEnabled(false);
                        cargoMateriasAprobadas(nMateria, modelAprobado);
                        cargoMateriasEnCurso(alumno);
                        notaParcial=0;
                        notaExamen=0;
                    }
                    else {
                        cargoMateriasDesaprobadas(nMateria,modelExamen);
                        //pasos para seleccionar en lista desaprobado y ingresar otro examen
                    }
            }
            opciones=false;
        }
    }
}
