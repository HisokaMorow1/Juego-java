import java.io.BufferedReader; // Importa la clase BufferedReader para leer el archivo de texto línea por línea
import java.io.FileReader; // Importa la clase FileReader para leer caracteres desde un archivo
import java.io.IOException; // Importa la clase IOException para manejar excepciones de entrada/salida

/**
 * Clase utilitaria para cargar un mapa desde un archivo de texto.
 */
public class MapLoader {

    /**
     * Método estático que carga un mapa desde un archivo de texto y lo devuelve como una matriz de caracteres.
     * @param archivo Nombre del archivo desde donde se carga el mapa.
     * @return Matriz de caracteres que representa el mapa cargado desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    public static char[][] cargarMapa(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo)); // Crea un BufferedReader para leer el archivo
        String linea;
        int numFilas = 0;
        int numColumnas = 0;

        // Primera pasada: contar filas y determinar la longitud máxima de columna
        while ((linea = br.readLine()) != null) {
            numFilas++;
            numColumnas = Math.max(numColumnas, linea.length()); // Determina la longitud máxima de columna en cada línea
        }

        br.close(); // Cierra el BufferedReader después de contar las filas y columnas

        // Segunda pasada: cargar el mapa en una matriz de caracteres
        char[][] mapa = new char[numFilas][numColumnas];
        br = new BufferedReader(new FileReader(archivo)); // Vuelve a abrir el BufferedReader para cargar el mapa
        int fila = 0;

        while ((linea = br.readLine()) != null) {
            char[] filaMapa = linea.toCharArray(); // Convierte la línea en un arreglo de caracteres
            for (int col = 0; col < filaMapa.length; col++) {
                mapa[fila][col] = filaMapa[col]; // Copia cada caracter a la matriz de mapa
            }
            fila++;
        }

        br.close(); // Cierra el BufferedReader después de cargar el mapa

        return mapa; // Devuelve la matriz de caracteres que representa el mapa cargado
    }

    /**
     * Método principal para probar la carga del mapa desde un archivo.
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        try {
            char[][] mapa = cargarMapa("mapa.txt"); // Intenta cargar el mapa desde el archivo "mapa.txt"
            
            // Imprime el mapa cargado línea por línea
            for (char[] fila : mapa) {
                System.out.println(new String(fila));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepción si ocurre un error al cargar el mapa desde el archivo
        }
    }
}