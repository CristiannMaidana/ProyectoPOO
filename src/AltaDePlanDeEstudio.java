import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

public class AltaDePlanDeEstudio extends JFrame {
    private JPanel altaDePlanDeEstudio;
    private JButton paginaPrincipalButton;
    private JButton altaDeAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton buscoAlumnosButton;
    private JButton modificoCarreraButton;
    private JComboBox comboBoxPlanDeEstudio;
    private JLabel descripcionPlanDeEstudio;
    private JList listDeCarreras;
    private JLabel nombreDeCarreraElegida;
    private JButton asignarCarreraButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private JButton altaDePlanDeButton;
    private AlmacenCarreras almacenCarreras;
    private String planDeEstudio;
    private PlanDeEstudio clasePlanDeEstudio;
    private Carreras carreraElegida;
    private boolean paginaPrincipal=false, altaDeAlumnos=false, altaDeCarreras=false, buscoAlumnos=false, modificoCarreras=false, altaPlanDeEstudio = false;
    private CountDownLatch latch;
    private AlumnosRegistrados alumnosRegistrados;

    //Constructor
    public AltaDePlanDeEstudio(AlmacenCarreras almacenCarreras, CountDownLatch latch) {
        listDeCarreras.setBorder(new LineBorder(Color.BLACK, 1)); // Color y grosor del borde
        this.almacenCarreras = almacenCarreras;
        this.latch = latch;

        setContentPane(altaDePlanDeEstudio);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(1300,500);

        cargoPlanDeEstudio();
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
        comboBoxPlanDeEstudio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planDeEstudio = (String) comboBoxPlanDeEstudio.getSelectedItem();
                if (!planDeEstudio.equals("Plan de Estudio:") && !planDeEstudio.isEmpty()) {
                    cargoCarreras();
                    cargoDescripcionPlanDeEstudio();
                }
                else {
                    limpiarTodo();
                }
            }
        });
        listDeCarreras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = listDeCarreras.getSelectedIndex();
                String nMateria = (String) listDeCarreras.getModel().getElementAt(index);
                carreraElegida =almacenCarreras.getCarreraPorNombre(nMateria);
                nombreDeCarreraElegida.setText("La carrera para asignarle el plan de estudio es: "+nMateria);
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                paginaPrincipal = true;
                latch.countDown();
                dispose();
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeAlumnos = true;
                latch.countDown();
                dispose();
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaDeCarreras = true;
                latch.countDown();
                dispose();
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                buscoAlumnos = true;
                latch.countDown();
                dispose();
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modificoCarreras = true;
                latch.countDown();
                dispose();
            }
        });
        asignarCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (clasePlanDeEstudio == null) {
                    JOptionPane.showMessageDialog(null, "Elija un plan de estudio.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (carreraElegida == null){
                    JOptionPane.showMessageDialog(null, "Elija una carrera para asignarle el" +
                            " plan de estudio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (carreraElegida.getPlanDeEstudio() != null){
                        if (buscoAlumnosCursandoCarrera()){
                            JOptionPane.showMessageDialog(null,"No puede cambiar el plan de" +
                                            " estudio de la carrera porque hay alumnos inscriptos", "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            int respuesta = JOptionPane.showConfirmDialog(null, "La carrera " +
                                    "ya tiene un plan de estudio, ¿Desea cambiarlo?", "Aviso",
                                    JOptionPane.YES_NO_OPTION);
                            if (respuesta == JOptionPane.YES_OPTION){
                                clasePlanDeEstudio.setCarrera(carreraElegida);
                                carreraElegida.setPlanDeEstudio(clasePlanDeEstudio);
                                JOptionPane.showMessageDialog(null, "Se asigno correctamente " +
                                        "la carrera al plan de estudio.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                limpiarTodo();
                            }
                        }
                    }
                    else {
                        clasePlanDeEstudio.setCarrera(carreraElegida);
                        carreraElegida.setPlanDeEstudio(clasePlanDeEstudio);
                        JOptionPane.showMessageDialog(null, "Se asigno correctamente la carrera " +
                                "al plan de estudio.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        limpiarTodo();
                    }
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String validoPlanDeEstudio = (String) comboBoxPlanDeEstudio.getSelectedItem();
                if (validoPlanDeEstudio.equals("Plan de Estudio:")) {
                    JOptionPane.showMessageDialog(null, "No hay nada que cancelar.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Cancelar el alta del" +
                            " plan de estudio?", "Aviso", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        limpiarTodo();
                    }
                }
            }
        });
        altaDePlanDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Ya se encuentra en la pagina alta de plan " +
                        "de estudio.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //Metodos void
    private void cargoPlanDeEstudio(){
        comboBoxPlanDeEstudio.addItem("Plan de Estudio:");
        comboBoxPlanDeEstudio.addItem("Plan de estudio 'A'.");
        comboBoxPlanDeEstudio.addItem("Plan de estudio 'B'.");
        comboBoxPlanDeEstudio.addItem("Plan de estudio 'C'.");
        comboBoxPlanDeEstudio.addItem("Plan de estudio 'D'.");
        comboBoxPlanDeEstudio.addItem("Plan de estudio 'E'.");
    }
    private void cargoCarreras(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i=0; i<almacenCarreras.getCantidadCarreras(); i++){
            model.addElement(almacenCarreras.getCarrera(i).getNombre());
        }
        listDeCarreras.setModel(model);
    }
    private void cargoDescripcionPlanDeEstudio(){
        String texto = descripcionPlan();
        int maxLineLength = 100; // Longitud máxima de caracteres por línea

        String[] words = texto.split(" ");
        StringBuilder formattedText = new StringBuilder("<html>");
        int lineLength = 0;

        for (String word : words) {
            if (lineLength + word.length() > maxLineLength) {
                formattedText.append("<br>");
                lineLength = 0;
            }
            formattedText.append(word).append(" ");
            lineLength += word.length() + 1; // +1 por el espacio
        }

        formattedText.append("</html>");
        descripcionPlanDeEstudio.setText(formattedText.toString());

    }
    private void limpiarTodo(){
        DefaultListModel<String> model = new DefaultListModel<>();
        comboBoxPlanDeEstudio.setSelectedIndex(0);
        listDeCarreras.setModel(model);
        nombreDeCarreraElegida.setText("");
        descripcionPlanDeEstudio.setText("");
    }

    //Metodos get
    public boolean getPaginaPrincipal(){
        return paginaPrincipal;
    }
    public boolean getAltaDeAlumnos(){
        return altaDeAlumnos;
    }
    public boolean getAltaDeCarreras(){
        return altaDeCarreras;
    }
    public boolean getBuscoAlumnos(){
        return buscoAlumnos;
    }
    public boolean getModificoCarrera(){
        return modificoCarreras;
    }
    public boolean getAltaDeEstudios(){
        return altaPlanDeEstudio;
    }

    //Metodos set
    public void setAltaDeAlumnos(boolean v) {
        this.altaDeAlumnos = v;
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
        this.modificoCarreras=v;
    }
    public void setAlumnos(AlumnosRegistrados almacenAlumnos){
        this.alumnosRegistrados = almacenAlumnos;
    }


    private String descripcionPlan(){
        String texto = "";
        switch (planDeEstudio){
            case "Plan de estudio 'A'.": {
                clasePlanDeEstudio = new PlanDeEstudioA(null);
                texto =  clasePlanDeEstudio.getDescripion();
                break;
            }
            case "Plan de estudio 'B'.": {
                clasePlanDeEstudio = new PlanDeEstudioB(null);
                texto =  clasePlanDeEstudio.getDescripion();
                break;
            }
            case "Plan de estudio 'C'.": {
                clasePlanDeEstudio = new PlanDeEstudioC(null, null);
                texto = clasePlanDeEstudio.getDescripion();
                break;
            }
            case "Plan de estudio 'D'.": {
                clasePlanDeEstudio = new PlanDeEstudioD(null, null);
                texto = clasePlanDeEstudio.getDescripion();
                break;
            }
            case "Plan de estudio 'E'.": {
                clasePlanDeEstudio = new PlanDeEstudioE(null, null);
                texto = clasePlanDeEstudio.getDescripion();
                break;
            }
        }
        return texto;
    }
    private boolean buscoAlumnosCursandoCarrera(){
        boolean encontrado=false;
        for (int i=0; i<alumnosRegistrados.getSize(); i++){
            if (alumnosRegistrados.getAlumno(i).getCarrera() == carreraElegida){
                encontrado=true;
                break;
            }
        }
        return encontrado;
    }
}
