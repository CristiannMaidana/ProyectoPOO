import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class VInscripcionMaterias extends JFrame {
    private JList listMateriasCursadas;
    private JList listMateriasNotas;
    private JComboBox comboBoxAnnios;
    private JComboBox comboBoxCuatri;
    private JList listMateriasNuevas;
    private JLabel nombreAlumno;
    private JPanel panelMaterias;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JComboBox comboBoxSituacion;
    private JPanel panelMateriasCursadas;
    private JCheckBox checkBox1;
    private int annioElegido=0, cuatriElegido=0;
    private final Alumnos alumno;
    private boolean situacionB = false, faltaCursar = false, cancelar = false, aceptar = false;
    private CountDownLatch latch;

    //Constructor
    public VInscripcionMaterias(Alumnos alumno, CountDownLatch latch) {
        this.alumno=alumno;
        this.latch=latch;
        JScrollPane scrollPane = new JScrollPane(panelMaterias);

        setContentPane(scrollPane);
        setUndecorated(true);
        setTitle("Inscripcion de materias");
        setSize(550,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cargoMateriasAprobadasAnterioresYNotas();
        nombreAlumno.setText(alumno.toString());
        cargoAnniosEnPantalla(alumno.getCarrera().getAnniosCarrera());//cargo los años de la carrera que eligio
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String situaciones = (String) comboBoxSituacion.getSelectedItem();
                switch (situaciones){
                    case "Sin cursar/Desaprobadas" : {faltaCursar = true;situacionB = false;break;}
                    case "Debe examen." : {situacionB = true;faltaCursar = false;break;}
                }
                String annioS = (String) comboBoxAnnios.getSelectedItem();
                switch (annioS){
                    case "Primer año" : {annioElegido = 0;break;}
                    case "Segundo año" : {annioElegido = 1;break;}
                    case "Tercer año" : {annioElegido = 2;break;}
                    case "Cuarto año" : {annioElegido = 3;break;}
                    case "Quinto año" : {annioElegido = 4;break;}
                    case "Sexto año" : {annioElegido = 5;break;}
                }
                String cuatriS = (String) comboBoxCuatri.getSelectedItem();
                switch (cuatriS){
                    case "Primer cuatrimestre.": {cuatriElegido=1;break;}
                    case "Segundo cuatrimestre.": {cuatriElegido=2;break;}
                }
                if (faltaCursar){
                    if (cuatriElegido >= 0 && annioElegido >= 0)
                        cargoMateriasEnPantalla(annioElegido, cuatriElegido);
                } else if (situacionB) {
                    if (cuatriElegido >= 0 && annioElegido >= 0)
                        cargoMateriasEnPantallaFaltaExamen(annioElegido, cuatriElegido);
                }else {
                    if (cuatriElegido >= 0 && annioElegido >= 0)
                        cargoMateriasEnPantalla(annioElegido, cuatriElegido);
                }
            }
        };//Tuve que hacer un listener nuevo para que ejecute cada que cambia de materias etc.
        comboBoxAnnios.addActionListener(listener);
        comboBoxCuatri.addActionListener(listener);
        comboBoxSituacion.addActionListener(listener);

        listMateriasNuevas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = listMateriasNuevas.getSelectedIndex();
                String nMateria = (String) listMateriasNuevas.getModel().getElementAt(index);
                cargoMateriasAlAlumno(nMateria, alumno);
                if (alumno.materiasLlenas()) {
                    JOptionPane.showMessageDialog(VInscripcionMaterias.this,
                            "Cargo las materias maximas por cuatrimestre. ",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                aceptar = true;
                setVisible(false);
                latch.countDown();
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                cancelar = true;
                latch.countDown();
                dispose();
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
    private void cargoAnniosEnPantalla(int annios){
        for (int i=0; i<annios; i++) {
            switch (i){
                case 0 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Primer año");
                    }
                    break;
                }
                case 1 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Segundo año");
                    }
                    break;
                }
                case 2 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Tercer año");
                    }
                    break;
                }
                case 3 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Cuarto año");
                    }
                    break;
                }
                case 4 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Quinto año");
                    }
                    break;
                }
                case 5 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Sexto año");
                    }
                    break;
                }
                case 6 : {
                    if (!alumno.getAnioAprobado(i)) {
                        comboBoxAnnios.addItem("Septimo año");
                    }
                    break;
                }
            }
        }
    }
    private void cargoMateriasEnPantalla( int annio, int cuatri){
        DefaultListModel<String> model = new DefaultListModel<>();
        if (cuatri == 1){
            for (int i=0; i<3;i++){
                if (!alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getCursadaAprobada() && !alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).parcial)
                    model.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNombreDeMateria());
            }
        }
        else if (cuatri == 2){
            for (int i=3; i<6;i++){
                if (!alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getCursadaAprobada() && !alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).parcial)
                    model.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNombreDeMateria());
            }
        }
        listMateriasNuevas.setModel(model);
    }
    private void cargoMateriasEnPantallaFaltaExamen( int annio, int cuatri){
        DefaultListModel<String> model = new DefaultListModel<>();
        if (cuatri == 1){
            for (int i=0; i<3;i++)
                if (alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).parcial && !alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNotaExamenFinal())
                    model.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNombreDeMateria());
        }
        else if (cuatri == 2){
            for (int i=3; i<6;i++){
                //antes alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getCursadaAprobada(), hubo un cambio de
                //como se aprueba la cursada, ahora solo se aprueba la cursada si das examen final y parcial
                //o si promocionas, la forma correcta es asi.
                if (alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).parcial && !alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNotaExamenFinal())
                    model.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(annio,i).getNombreDeMateria());
            }
        }
        listMateriasNuevas.setModel(model);
    }
    private void cargoMateriasAlAlumno(String nombre, Alumnos alumno){
        boolean noHyaMateriaRepetidas = true;
        if (!alumno.materiasLlenas()) {//si esta llena no deja ingresar
            for (byte i = 0; i < 3; i++) {//verifico que no sea una materia repetida o que ya esta almacenada
                if (alumno.getMateriasAlmacenadas(i) == null)
                    break;
                if (alumno.getMateriasAlmacenadas(i).getNombreDeMateria().equals(nombre)) {
                    JOptionPane.showMessageDialog(VInscripcionMaterias.this,
                            "No puede inscribirse porque ya esta cursando la materia  " + alumno.getCarrera().getMateriasPorNombre(nombre).getNombreDeMateria() + ".",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                    noHyaMateriaRepetidas = false;
                }
            }
            if (noHyaMateriaRepetidas) {
                if (!alumno.inscribirAMaterias(nombre)) {
                    if (alumno.getCarrera().getMateriasPorNombre(nombre).getCorrelativa().getCursadaAprobada()){
                        JOptionPane.showMessageDialog(VInscripcionMaterias.this,
                                "No puede inscribirse porque debe tener los cuatrimestres previos aprobados (examenes) .",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(VInscripcionMaterias.this,
                                "No puede inscribirse porque debe la materia  " + alumno.getCarrera().getMateriasPorNombre(nombre).getCorrelativa().getNombreDeMateria() + ".",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VInscripcionMaterias.this,
                            "Se inscribio correctamente a la materia  " + alumno.getCarrera().getMateriasPorNombre(nombre).getNombreDeMateria() + ".",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    private void cargoMateriasAprobadasAnterioresYNotas(){
        DefaultListModel<String> modelCursada = new DefaultListModel<>();
        DefaultListModel<String> modelNotas = new DefaultListModel<>();
        for (int i = 0; i <alumno.getCarrera().getAnniosCarrera(); i++){
            for (int j = 0; j <alumno.getCarrera().getCuatriCarrera(); j++){
                if (alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getCursadaAprobada() && !alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNotaExamenFinal()){
                    modelCursada.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNombreDeMateria());
                    modelNotas.addElement("Debe examen.");
                } else if (alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNotaExamenFinal()){
                    modelCursada.addElement(alumno.getCarrera().getMateriasPorAnnioYMateria(i, j).getNombreDeMateria());
                    modelNotas.addElement("Aprobado.");
                }
            }
        }
        listMateriasCursadas.setModel(modelCursada);
        listMateriasNotas.setModel(modelNotas);
    }
}
