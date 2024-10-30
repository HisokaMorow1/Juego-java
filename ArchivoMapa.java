import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que representa un mapa cargado desde un archivo de texto.
 */
public class ArchivoMapa {
    private int FILAS;
    private int COLUMNAS;
    private char[][] matriz;

    /**
     * Constructor por defecto de la clase ArchivoMapa.
     */
    public ArchivoMapa() {}

    /**
     * Carga un mapa desde un archivo de texto y lo almacena en una matriz de caracteres.
     * 
     * @param nombreArchivo el nombre del archivo de texto que contiene el mapa
     */
    public void cargarMapa(String nombreArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            FILAS = 0;
            COLUMNAS = 0;

            // Determinar el número de filas y columnas
            while ((linea = br.readLine()) != null) {
                FILAS++;
                if (linea.length() > COLUMNAS) {
                    COLUMNAS = linea.length();
                }
            }
            br.close();

            matriz = new char[FILAS][COLUMNAS];
            br = new BufferedReader(new FileReader(nombreArchivo));
            int fila = 0;
            
            // Leer el archivo nuevamente y llenar la matriz
            while ((linea = br.readLine()) != null) {
                for (int col = 0; col < linea.length(); col++) {
                    matriz[fila][col] = linea.charAt(col);
                }
                fila++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica si el bloque en la posición (x, y) es un bloque de finalización ('F').
     * 
     * @param x coordenada x
     * @param y coordenada y
     * @return true si el bloque es un bloque de finalización, false en caso contrario
     */
    public boolean checkForFinishBlock(int x, int y) {
        int row = y / 100;
        int col = x / 100;
        if (row < FILAS && col < COLUMNAS) {
            return matriz[row][col] == 'F';
        }
        return false;
    }

    /**
     * Imprime la matriz del mapa en la consola.
     */
    public void imprimirMatriz() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                System.out.print(matriz[fila][columna]);
            }
            System.out.println();
        }
    }

    /**
     * Devuelve el número de filas del mapa.
     * 
     * @return el número de filas
     */
    public int getFILAS() {
        return FILAS;
    }

    /**
     * Devuelve el número de columnas del mapa.
     * 
     * @return el número de columnas
     */
    public int getCOLUMNAS() {
        return COLUMNAS;
    }

    /**
     * Devuelve la matriz que representa el mapa.
     * 
     * @return la matriz del mapa
     */
    public char[][] getMatriz() {
        return matriz;
    }
}