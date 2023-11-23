package model.busqueda;

import java.util.ArrayList;

public class BusquedaBinaria {

    public static int busquedaBinariaEnArray(String[] array, String elemento) {
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
    public static int busquedaBinariaEnArrayList(ArrayList<Integer> array, int target) {
        int left = 0;
        int right = array.size() - 1;   

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array.get(mid) == target) {
                return mid; // Encontrado
            } else if (array.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // No encontrado
    }
}
