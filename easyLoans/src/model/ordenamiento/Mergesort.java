package model.ordenamiento;

import java.util.Arrays;

public class Mergesort {

    public static void mergesort(String[] array) {
        if (array.length <= 1) {
            return;
        }
        int medio = array.length / 2;
        String[] izquierda = Arrays.copyOfRange(array, 0, medio);
        String[] derecha = Arrays.copyOfRange(array, medio, array.length);

        mergesort(izquierda);
        mergesort(derecha);
        merge(array, izquierda, derecha);
    }

    private static void merge(String[] array, String[] izquierda, String[] derecha) {
        int i = 0, j = 0, k = 0;
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i].compareTo(derecha[j]) < 0) {
                array[k++] = izquierda[i++];
            } else {
                array[k++] = derecha[j++];
            }
        }
        while (i < izquierda.length) {
            array[k++] = izquierda[i++];
        }
        while (j < derecha.length) {
            array[k++] = derecha[j++];
        }
    }
}
