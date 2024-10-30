import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase que representa al jugador en el juego de plataformas.
 */
public class Jugador {
    private int x;
    private int y;
    private final int ancho = 50;
    private final int alto = 50;
    private int velocidadY = 0;
    private boolean enElSuelo = false;
    private final int GRAVEDAD = 1;
    private final int FUERZA_SALTO = -15;
    private final int VELOCIDAD_MOVIMIENTO = 10;
    private boolean izquierda = false;
    private boolean derecha = false;
    private Image imagen;

    private int xInicial;
    private int yInicial;

    @SuppressWarnings("unused")
    private Plataforma plataformaSobreLaQueEsta;

    /**
     * Constructor que inicializa al jugador en una posición específica.
     * 
     * @param x coordenada X inicial del jugador
     * @param y coordenada Y inicial del jugador
     */
    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        xInicial = x;
        yInicial = y;
        try {
            imagen = ImageIO.read(new File("C:\\Users\\colmi\\Desktop\\pou\\uwu\\programajuego\\src\\pou.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que dibuja al jugador en el contexto gráfico dado.
     * 
     * @param g contexto gráfico donde se dibuja el jugador
     */
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(java.awt.Color.RED);
            g.fillRect(x, y, ancho, alto);
        }
    }

    /**
     * Método que actualiza la posición y estado del jugador en el juego.
     * 
     * @param alturaMapa altura máxima permitida en el mapa
     */
    public void actualizar(int alturaMapa) {
        if (!enElSuelo) {
            velocidadY += GRAVEDAD;
        } else {
            saltar();
        }

        y += velocidadY;

        if (izquierda) {
            x -= VELOCIDAD_MOVIMIENTO;
        }
        if (derecha) {
            x += VELOCIDAD_MOVIMIENTO;
        }

        if (y > alturaMapa) {
            resetPosition();
        }
    }

    /**
     * Método que hace que el jugador realice un salto.
     */
    public void saltar() {
        velocidadY = FUERZA_SALTO;
        enElSuelo = false;
    }

    /**
     * Establece si el jugador se está moviendo hacia la izquierda.
     * 
     * @param izquierda true si el jugador se mueve hacia la izquierda, false de lo contrario
     */
    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Establece si el jugador se está moviendo hacia la derecha.
     * 
     * @param derecha true si el jugador se mueve hacia la derecha, false de lo contrario
     */
    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    /**
     * Establece si el jugador está en el suelo.
     * 
     * @param enElSuelo true si el jugador está en el suelo, false de lo contrario
     */
    public void setEnElSuelo(boolean enElSuelo) {
        this.enElSuelo = enElSuelo;
    }

    /**
     * Obtiene la coordenada X actual del jugador.
     * 
     * @return la coordenada X del jugador
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada X del jugador.
     * 
     * @param x nueva coordenada X del jugador
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada Y actual del jugador.
     * 
     * @return la coordenada Y del jugador
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada Y del jugador.
     * 
     * @param y nueva coordenada Y del jugador
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtiene el ancho del jugador.
     * 
     * @return el ancho del jugador
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene el alto del jugador.
     * 
     * @return el alto del jugador
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Obtiene los límites del jugador como un objeto Rectangle para detección de colisiones.
     * 
     * @return los límites del jugador como un Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    /**
     * Establece la plataforma sobre la cual está el jugador actualmente.
     * 
     * @param plataforma la plataforma sobre la cual está el jugador
     */
    public void setPlataformaSobreLaQueEsta(Plataforma plataforma) {
        this.plataformaSobreLaQueEsta = plataforma;
    }

    /**
     * Reinicia la posición inicial del jugador.
     */
    public void resetPosition() {
        x = xInicial;
        y = yInicial;
        velocidadY = 0;
        enElSuelo = false;
    }

    /**
     * Verifica si el jugador ha alcanzado el bloque final del juego.
     * 
     * @param archivoMapa el archivo de mapa que contiene la información del juego
     * @return true si el jugador ha alcanzado el bloque final, false de lo contrario
     */
    public boolean checkGameEnd(ArchivoMapa archivoMapa) {
        return archivoMapa.checkForFinishBlock(x, y);
    }
}