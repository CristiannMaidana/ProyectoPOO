import javax.swing.*;

public class PaginaPrincipal extends JFrame {
    private JLabel imagenUNTDFLabel;
    private JButton altaDeAlumnosButton;
    private JButton altaDeCarrerasButton;
    private JButton altaDePlanDeButton;
    private JButton buscoAlumnoButton;
    private JButton modificoCarreraButton;
    private JLabel imagenSIULabel;
    private JPanel paginaPrincipal;
    private JLabel tituloPrincipal;

    public PaginaPrincipal() {
        setContentPane(paginaPrincipal);
        setSize(850,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
