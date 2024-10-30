import java.util.List;

/**
 * Clase encargada de verificar colisiones entre el jugador y las plataformas.
 */
public class Colision {

    /**
     * Método para verificar colisiones entre el jugador y las plataformas.
     * 
     * @param jugador El jugador cuya colisión se verificará.
     * @param plataformas La lista de plataformas contra las cuales se verificará la colisión.
     */
    public void checkCollision(Jugador jugador, List<Plataforma> plataformas) {
        boolean colision = false;
        
        for (Plataforma plataforma : plataformas) {
            // Verificar si los límites del jugador intersectan con los límites de la plataforma
            if (jugador.getBounds().intersects(plataforma.getBounds())) {
                colision = true;
                // Ajustar la posición vertical del jugador para que esté justo encima de la plataforma
                jugador.setY(plataforma.getY() - jugador.getAlto());
                // Establecer la plataforma sobre la cual está el jugador
                jugador.setPlataformaSobreLaQueEsta(plataforma);
                // Indicar que el jugador está en el suelo (para control de saltos)
                jugador.setEnElSuelo(true);
                break;  // Salir del bucle una vez que se encuentre la primera colisión
            }
        }
        
        // Si no hubo colisión con ninguna plataforma, ajustar los estados del jugador
        if (!colision) {
            jugador.setEnElSuelo(false);
            jugador.setPlataformaSobreLaQueEsta(null);
        }
    }
}