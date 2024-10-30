import java.awt.Graphics; // Importa la clase Graphics para dibujar gráficos
import java.awt.Image; // Importa la clase Image para manejar imágenes
import java.awt.Rectangle; // Importa la clase Rectangle para manejar áreas rectangulares
import java.io.File; // Importa la clase File para manejar archivos
import java.io.IOException; // Importa la clase IOException para manejar excepciones de entrada/salida
import javax.imageio.ImageIO; // Importa la clase ImageIO para leer imágenes desde archivos

// Clase base para representar plataformas en el juego
public class Plataforma {

    // Atributos de la clase Plataforma
    protected int x; // Posición x de la plataforma
    protected int y; // Posición y de la plataforma
    protected int ancho; // Ancho de la plataforma
    protected int alto; // Alto de la plataforma
    protected Image imagen; // Imagen de la plataforma

    /**
     * Constructor de la clase Plataforma.
     * @param x Posición x de la plataforma.
     * @param y Posición y de la plataforma.
     * @param ancho Ancho de la plataforma.
     * @param alto Alto de la plataforma.
     * @param rutaImagen Ruta del archivo de imagen para la plataforma.
     */
    public Plataforma(int x, int y, int ancho, int alto, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        try {
            imagen = ImageIO.read(new File(rutaImagen)); // Lee la imagen desde el archivo especificado
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de la excepción si ocurre un error al leer la imagen
        }
    }

    /**
     * Método para dibujar la plataforma en pantalla.
     * @param g Objeto Graphics utilizado para dibujar.
     */
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null); // Dibuja la imagen en la posición y dimensiones especificadas
        } else {
            g.setColor(java.awt.Color.GREEN); // Si no hay imagen, dibuja un rectángulo verde
            g.fillRect(x, y, ancho, alto);
        }
    }

    /**
     * Método para obtener la posición x de la plataforma.
     * @return La posición x de la plataforma.
     */
    public int getX() {
        return x; // Devuelve la posición x de la plataforma
    }

    /**
     * Método para obtener la posición y de la plataforma.
     * @return La posición y de la plataforma.
     */
    public int getY() {
        return y; // Devuelve la posición y de la plataforma
    }

    /**
     * Método para obtener el ancho de la plataforma.
     * @return El ancho de la plataforma.
     */
    public int getAncho() {
        return ancho; // Devuelve el ancho de la plataforma
    }

    /**
     * Método para obtener el alto de la plataforma.
     * @return El alto de la plataforma.
     */
    public int getAlto() {
        return alto; // Devuelve el alto de la plataforma
    }

    /**
     * Método para obtener el área rectangular de colisión de la plataforma.
     * @return Un objeto Rectangle que representa el área de colisión de la plataforma.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto); // Devuelve un objeto Rectangle que representa el área de colisión
    }
}