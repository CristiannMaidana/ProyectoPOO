import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VperfilUsuario extends JFrame{
    private JLabel nombreUsuario;
    private JList listMateriasAprobadas;
    private JList listMateriasPendientes;
    private JButton situacionButton;
    private JLabel planDeEstudioAlumno;
    private JLabel cargoSituacion;
    private JPanel panelPerfilAlumno;
    private JButton masMateriasButton;
    private JButton materiasObligatoriasButton;
    private JButton materiasOptativasButton;
    private JList listMateriasParaGraduarse;
    private JCheckBox checkBox1;
    private boolean masMaterias= false;
    private final DefaultListModel<String> modelAprobado = new DefaultListModel<>(), modelDesaprobado = new DefaultListModel<>();

    //Constructor
    public VperfilUsuario(Alumnos alumno) {
        JScrollPane scrollPane = new JScrollPane(panelPerfilAlumno);

        setContentPane(scrollPane);
        // Crear un borde con un color específico
        LineBorder border = new LineBorder(Color.black, 2); // Borde rojo con un grosor de 2 píxeles
        listMateriasParaGraduarse.setBorder(border);
        setUndecorated(true);
        setSize(530,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nombreUsuario.setText(alumno.toString());
        buscoMateriasAprobadasDesaprobadas(alumno);
        planDeEstudioAlumno.setText(alumno.getCarrera().getPlanDeEstudio().toString());
        situacionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (alumno.getCarrera().getAlumnoGraduado()){
                    cargoSituacion.setText("Se graduo");
                }else
                    cargoSituacion.setText("Desaprobado");
            }
        });
        masMateriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                masMaterias = true;
                dispose();
            }
        });
        materiasOptativasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargoMateriasOptativasPendientes(alumno);
            }
        });
        materiasObligatoriasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargoMateriasObligatoriasPendientes(alumno);
            }
        });
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }


    public void buscoMateriasAprobadasDesaprobadas(Alumnos alumno){
        for (byte annio=0;annio<alumno.getCarrera().getAnniosCarrera();annio++){
            for (byte cuatri=0;cuatri <alumno.getCarrera().getCuatriCarrera(); cuatri++) {
                if (alumno.getMateriasDeCarrera(annio, cuatri).getCursadaAprobada()) {
                    cargoMateriasAprobadas(alumno.getMateriasDeCarrera(annio, cuatri), modelAprobado);
                    cargoMateriasDesaprobadas("", modelDesaprobado);
                }
                else
                    cargoMateriasDesaprobadas(alumno.getMateriasDeCarrera(annio, cuatri).getNombreDeMateria(), modelDesaprobado);
            }
        }
    }
    public void cargoMateriasAprobadas(Materias materias, DefaultListModel<String> model){
        switch (model.size()){
            case 0 : {
                model.addElement("Primer año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 5, 14, 23, 32, 41, 50, 58: {
                model.addElement("Segundo cuatrimestre");
                break;
            }
            case 9 : {
                model.addElement("Segundo año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 18 : {
                model.addElement("Tercer año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 27 : {
                model.addElement("Cuarto año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 36 : {
                model.addElement("Quinto año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 45 : {
                model.addElement("Sexto año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
            case 54 : {
                model.addElement("Septimo año.");
                model.addElement("Primer cuatrimestre");
                break;
            }
        }
        model.addElement(materias.getNombreDeMateria());
        listMateriasAprobadas.setModel(model);
    }
    public void cargoMateriasDesaprobadas(String nombreMateria, DefaultListModel<String> model){
        switch (model.size()){
            case 0 : {
                if (!nombreMateria.isEmpty()) {
                    model.addElement("Primer año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 5, 14, 23, 31, 40, 49, 58: {
                if (!nombreMateria.isEmpty()) {
                    model.addElement("Segundo cuatrimestre");
                }
                break;
            }
            case 9 : {
                if (!nombreMateria.isEmpty()){
                    model.addElement("Segundo año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 17 : {//18
                if (!nombreMateria.isEmpty()){
                    model.addElement("Tercer año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 26 : {
                if (!nombreMateria.isEmpty()){
                    model.addElement("Cuarto año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 35 : {
                if (!nombreMateria.isEmpty()){
                    model.addElement("Quinto año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 44 : {
                if (!nombreMateria.isEmpty()){
                    model.addElement("Sexto año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
            case 53 : {
                if (!nombreMateria.isEmpty()){
                    model.addElement("Septimo año.");
                    model.addElement("Primer cuatrimestre");
                }
                break;
            }
        }
        model.addElement(nombreMateria);
        listMateriasPendientes.setModel(model);
    }
    public void cargoMateriasOptativasPendientes(Alumnos alumno){
        // Limpiar el JList
        DefaultListModel<String> model = (DefaultListModel<String>) listMateriasParaGraduarse.getModel();
        model.clear();

        List<Materias> lista = alumno.getCarrera().getMateriasOptativas();
        for (Materias materias : lista) {
            model.addElement(materias.getNombreDeMateria());
        }
        listMateriasParaGraduarse.setModel(model);
    }
    public void cargoMateriasObligatoriasPendientes(Alumnos alumno){
        // Limpiar el JList
        DefaultListModel<String> model = (DefaultListModel<String>) listMateriasParaGraduarse.getModel();
        model.clear();

        List<Materias> lista = alumno.getCarrera().getMateriasObligatorias();
        for (Materias materias : lista) {
            model.addElement(materias.getNombreDeMateria());
        }
        listMateriasParaGraduarse.setModel(model);
    }
}