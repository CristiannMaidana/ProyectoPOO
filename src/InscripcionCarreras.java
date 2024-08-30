import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class InscripcionCarreras extends JFrame {
    private JPanel inscripcionCarrera;
    private JComboBox opcionesCarrerasComboBox;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JCheckBox checkBox1;
    private String carreraElegida;
    private AlmacenCarreras almacenCarreras;
    private CountDownLatch latch;

    public InscripcionCarreras(AlmacenCarreras almacenCarreras, CountDownLatch latch) {
        this.almacenCarreras = almacenCarreras;
        this.latch = latch;
        setContentPane(inscripcionCarrera);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(300,150);
        cargoCarreras();

        checkBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });
        opcionesCarrerasComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carreraElegida = (String) opcionesCarrerasComboBox.getSelectedItem();
            }
        });
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (carreraElegida.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe elegir una carrera.");
                }
                else{
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de elegir la carrera: "
                            +carreraElegida+".?", "Aviso", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION){
                        latch.countDown();
                        dispose();
                    }
                    else{
                     cargoCarreras();
                    }
                }
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                latch.countDown();
                dispose();
            }
        });

    }

    public String getCarreraElegida() {
        return carreraElegida;
    }

    private void cargoCarreras(){
        opcionesCarrerasComboBox.removeAllItems();
        for(int i= 0; i<almacenCarreras.getCantidadCarreras(); i++){
            opcionesCarrerasComboBox.addItem(almacenCarreras.getCarrera(i).getNombre());
        }
    }
}
