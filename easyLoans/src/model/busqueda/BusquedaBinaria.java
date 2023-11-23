package model.busqueda;

public class BusquedaBinaria {

    public static int busquedaBinaria(String[] array, String elemento) {
        int inicio = 0;
        int fin = array.length - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int comparacion = elemento.compareTo(array[medio]);
            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1; // Elemento no encontrado
    }
}
