import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VperfilUsuario extends JFrame{
    private JLabel nombreUsuario;
    private JList listMateriasAprobadas;
    private JList listMateriasPendientes;
    private JButton situacionButton;
    private JLabel planDeEstudioAlumno;
    private JLabel cargoSituacion;
    private JPanel panelPerfilAlumno;
    private JButton aceptarButton;
    private JButton masMateriasButton;
    private JButton cancelarButton;
    private boolean activarSituacion=false, botonOpciones, masMaterias;
    private final DefaultListModel<String> modelAprobado = new DefaultListModel<>(), modelDesaprobado = new DefaultListModel<>();


    public VperfilUsuario(Alumnos alumno) {
        setContentPane(panelPerfilAlumno);
        setTitle("Usuario.");
        setSize(500,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nombreUsuario.setText(alumno.toString());
        buscoMateriasAprobadasDesaprobadas(alumno);
        planDeEstudioAlumno.setText(alumno.getCarrera().getPlanDeEstudio().toString());
        situacionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                activarSituacion=true;
                if (alumno.getCarrera().getAlumnoGraduado()){
                    cargoSituacion.setText("Se graduo");
                }else
                    cargoSituacion.setText("Desaprobado");
            }
        });
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                botonOpciones=true;
                masMaterias=false;
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                botonOpciones=false;
                dispose();
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
    }

    public void buscoMateriasAprobadasDesaprobadas(Alumnos alumno){
        for (byte annio=0;annio<alumno.getCarrera().getAnniosCarrera();annio++){
            for (byte cuatri=0;cuatri <6; cuatri++) {
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

    public boolean getCargoMasMaterias(){
        return masMaterias;
    }
}
