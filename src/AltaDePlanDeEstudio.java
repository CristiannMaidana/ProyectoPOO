import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private AlmacenCarreras almacenCarreras;
    private String planDeEstudio;
    private PlanDeEstudio clasePlanDeEstudio;

    public AltaDePlanDeEstudio(AlmacenCarreras almacenCarreras) {
        listDeCarreras.setBorder(new LineBorder(Color.BLACK, 1)); // Color y grosor del borde
        this.almacenCarreras = almacenCarreras;

        setContentPane(altaDePlanDeEstudio);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(900,350);

        cargoPlanDeEstudio();
        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        comboBoxPlanDeEstudio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planDeEstudio = (String) comboBoxPlanDeEstudio.getSelectedItem();
                if (!planDeEstudio.equals("Plan de Estudio") && !planDeEstudio.isEmpty()) {
                    cargoCarreras();
                    descripcionPlanDeEstudio.setText("El plan de estudio es: "+descripcionPlan());
                }
            }
        });
        listDeCarreras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = listDeCarreras.getSelectedIndex();
                String nMateria = (String) listDeCarreras.getModel().getElementAt(index);
                nombreDeCarreraElegida.setText(nMateria);
            }
        });
        paginaPrincipalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        altaDeAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        altaDeCarrerasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        buscoAlumnosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        modificoCarreraButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        asignarCarreraButton.addMouseListener(new MouseAdapter() {
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

    private String descripcionPlan(){
        switch (planDeEstudio){
            case "Plan de Estudio 'A'": {
                clasePlanDeEstudio = new PlanDeEstudioA();
                return clasePlanDeEstudio.getDescripion();
            }
        }
    }
}
