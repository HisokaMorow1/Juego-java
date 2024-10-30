import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel principal del juego que gestiona la lógica del juego y la interfaz gráfica.
 */
public class JuegoPanel extends JPanel implements KeyListener, ActionListener {
    private Jugador jugador;
    private List<Plataforma> plataformas;
    private Timer timer;
    private ArchivoMapa archivoMapa;
    private Image fondo;
    @SuppressWarnings("unused")
    private Image finalImage;
    private Clip clip;

    private static final int ANCHO_VENTANA = 800;
    private static final int ALTO_VENTANA = 800;

    private int contadorPlataformas = 0;
    private int alturaMaxima = 0;

    /**
     * Constructor del panel de juego. Inicializa los componentes, carga el mapa y las plataformas,
     * y configura el jugador y la música de fondo.
     */
    public JuegoPanel() {
        setFocusable(true);
        addKeyListener(this);

        archivoMapa = new ArchivoMapa();
        archivoMapa.cargarMapa("C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\mapa.txt");

        try {
            plataformas = cargarPlataformas();
            fondo = ImageIO.read(new File("C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\cielo.jpg"));
            finalImage = ImageIO.read(new File("C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\final.png"));
            reproducirMusica("C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\jump.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (plataformas != null && !plataformas.isEmpty()) {
            Plataforma plataformaBase = plataformas.get(plataformas.size() - 1);
            jugador = new Jugador(plataformaBase.getX() + plataformaBase.getAncho() / 2 - 300, plataformaBase.getY() - 50);
            alturaMaxima = jugador.getY() / 100;
        } else {
            jugador = new Jugador(100, 100);
            alturaMaxima = jugador.getY() / 100;
        }

        timer = new Timer(16, this);
        timer.start();
    }

    /**
     * Carga las plataformas desde el archivo de mapa cargado.
     * 
     * @return una lista de plataformas cargadas desde el archivo de mapa
     * @throws Exception si ocurre un error al cargar las plataformas
     */
    private List<Plataforma> cargarPlataformas() throws Exception {
        List<Plataforma> plataformas = new ArrayList<>();
        char[][] matriz = archivoMapa.getMatriz();
        int FILAS = archivoMapa.getFILAS();
        int COLUMNAS = archivoMapa.getCOLUMNAS();

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (matriz[fila][columna] == 'p') {
                    plataformas.add(new Plataforma(columna * 100, fila * 100, 100, 20, "C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\plata.png"));
                } else if (matriz[fila][columna] == 'm') {
                    plataformas.add(new PlataformaMovil(columna * 100, fila * 100, 100, 20, 2, 0, columna * 100 - 50, columna * 100 + 150, fila * 100 - 50, fila * 100 + 50, "C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\plata.png"));
                } else if (matriz[fila][columna] == 'F') {
                    plataformas.add(new Plataforma(columna * 100, fila * 100, 100, 100, "C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\final.png"));
                }
            }
        }
        return plataformas;
    }

    /**
     * Reproduce la música de fondo del juego.
     * 
     * @param filepath la ruta del archivo de audio a reproducir
     */
    private void reproducirMusica(String filepath) {
        try {
            File audioFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Detiene la reproducción de la música de fondo del juego.
     */
    private void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    /**
     * Método que dibuja los componentes del juego en el panel.
     * 
     * @param g el contexto gráfico donde se dibujan los componentes
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        int offsetX = ANCHO_VENTANA / 2 - jugador.getX();
        int offsetY = ALTO_VENTANA / 2 - jugador.getY();

        g.translate(offsetX, offsetY);

        if (jugador != null) {
            jugador.dibujar(g);
        }

        if (plataformas != null) {
            for (Plataforma plataforma : plataformas) {
                plataforma.dibujar(g);
            }
        }

        g.translate(-offsetX, -offsetY);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Altura: " + contadorPlataformas, 20, 30);
    }

    /**
     * Método que maneja el evento de tecla presionada por el usuario.
     * 
     * @param e el evento de tecla presionada
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A:
                jugador.setIzquierda(true);
                break;
            case KeyEvent.VK_D:
                jugador.setDerecha(true);
                break;
            case KeyEvent.VK_E:
                jugador.resetPosition();
                contadorPlataformas = 0;
                alturaMaxima = jugador.getY() / 100;
                break;
        }

        repaint();
    }

    /**
     * Método que maneja el evento de tecla liberada por el usuario.
     * 
     * @param e el evento de tecla liberada
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A:
                jugador.setIzquierda(false);
                break;
            case KeyEvent.VK_D:
                jugador.setDerecha(false);
                break;
        }
    }

    /**
     * Método que maneja el evento de tecla tipo (tecla presionada y luego liberada) por el usuario.
     * 
     * @param e el evento de tecla tipo
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * Método que maneja el evento de acción del temporizador, actualizando la lógica del juego y repintando el panel.
     * 
     * @param e el evento de acción del temporizador
     */
    public void actionPerformed(ActionEvent e) {
        if (jugador != null) {
            int alturaActual = Math.max(0, (jugador.getY() / 100));
            if (alturaActual < alturaMaxima) {
                contadorPlataformas++;
                alturaMaxima = alturaActual;
            } else if (alturaActual > alturaMaxima) {
                contadorPlataformas--;
                alturaMaxima = alturaActual;
            }

            jugador.actualizar(archivoMapa.getFILAS() * 100);
            for (Plataforma plataforma : plataformas) {
                if (plataforma instanceof PlataformaMovil) {
                    ((PlataformaMovil) plataforma).actualizar();
                }
            }

            Colision colision = new Colision();
            colision.checkCollision(jugador, plataformas);

            if (jugador.checkGameEnd(archivoMapa)) {
                timer.stop();
                detenerMusica();
                JOptionPane.showMessageDialog(this, "¡Juego Terminado! Has alcanzado el bloque final.");
                System.exit(0); // Cerrar el juego
            }
        }

        repaint();
    }
}