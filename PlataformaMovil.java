import java.awt.Graphics; // Importa la clase Graphics para dibujar gráficos

// Clase que extiende Plataforma para representar plataformas móviles en el juego
public class PlataformaMovil extends Plataforma {

    // Atributos adicionales de la clase PlataformaMovil
    private int velocidadX; // Velocidad horizontal de la plataforma
    private int velocidadY; // Velocidad vertical de la plataforma
    private int limiteIzquierda; // Límite izquierdo de movimiento
    private int limiteDerecha; // Límite derecho de movimiento
    private int limiteArriba; // Límite superior de movimiento
    private int limiteAbajo; // Límite inferior de movimiento

    /**
     * Constructor de la clase PlataformaMovil.
     * @param x Posición x inicial de la plataforma móvil.
     * @param y Posición y inicial de la plataforma móvil.
     * @param ancho Ancho de la plataforma móvil.
     * @param alto Alto de la plataforma móvil.
     * @param velocidadX Velocidad horizontal de la plataforma móvil.
     * @param velocidadY Velocidad vertical de la plataforma móvil.
     * @param limiteIzquierda Límite izquierdo de movimiento de la plataforma móvil.
     * @param limiteDerecha Límite derecho de movimiento de la plataforma móvil.
     * @param limiteArriba Límite superior de movimiento de la plataforma móvil.
     * @param limiteAbajo Límite inferior de movimiento de la plataforma móvil.
     * @param rutaImagen Ruta del archivo de imagen para la plataforma móvil.
     */
    public PlataformaMovil(int x, int y, int ancho, int alto, int velocidadX, int velocidadY, int limiteIzquierda, int limiteDerecha, int limiteArriba, int limiteAbajo, String rutaImagen) {
        // Llama al constructor de la clase base Plataforma para inicializar sus atributos
        super(x, y, ancho, alto, rutaImagen);
        
        // Inicializa los atributos específicos de PlataformaMovil
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.limiteIzquierda = limiteIzquierda;
        this.limiteDerecha = limiteDerecha;
        this.limiteArriba = limiteArriba;
        this.limiteAbajo = limiteAbajo;
    }

    /**
     * Método para actualizar la posición de la plataforma móvil según su velocidad.
     * Invierte la dirección si alcanza los límites definidos.
     */
    public void actualizar() {
        x += velocidadX; // Mueve la plataforma horizontalmente según la velocidadX
        y += velocidadY; // Mueve la plataforma verticalmente según la velocidadY

        // Revierte la dirección horizontal si la plataforma alcanza los límites izquierdo o derecho
        if (x < limiteIzquierda || x + ancho > limiteDerecha) {
            velocidadX = -velocidadX; // Invierte la velocidad horizontal
        }

        // Revierte la dirección vertical si la plataforma alcanza los límites superior o inferior
        if (y < limiteArriba || y + alto > limiteAbajo) {
            velocidadY = -velocidadY; // Invierte la velocidad vertical
        }
    }

    /**
     * Método para dibujar la plataforma móvil en pantalla.
     * @param g Objeto Graphics utilizado para dibujar.
     */
    
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null); // Dibuja la imagen de la plataforma móvil
        } else {
            g.setColor(java.awt.Color.RED); // Si no hay imagen, dibuja un rectángulo rojo
            g.fillRect(x, y, ancho, alto);
        }
    }
}