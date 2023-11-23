package model.ordenamiento;

public class Heapsort {
    public static void sort(String[] arr) {
        int n = arr.length;

        // Construir un max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extraer elementos uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Llamar a heapify en el montículo reducido
            heapify(arr, i, 0);
        }
    }

    private static void heapify(String[] arr, int n, int i) {
        int largest = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < n && arr[izquierda].compareTo(arr[largest]) > 0) {
            largest = izquierda;
        }

        if (derecha < n && arr[derecha].compareTo(arr[largest]) > 0) {
            largest = derecha;
        }

        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
