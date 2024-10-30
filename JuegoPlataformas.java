import javax.swing.JFrame;

/**
 * Clase principal que inicializa y muestra la ventana del juego de plataformas.
 */
public class JuegoPlataformas extends JFrame {

    /**
     * Constructor de la clase JuegoPlataformas. Configura la ventana del juego.
     */
    public JuegoPlataformas() {
        setTitle("Juego de Plataformas");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JuegoPanel());
    }

    /**
     * Método principal que inicia la aplicación del juego.
     * 
     * @param args argumentos de la línea de comandos (no se utilizan en este caso)
     */
    public static void main(String[] args) {
        JuegoPlataformas juego = new JuegoPlataformas();
        juego.setVisible(true);
    }
}