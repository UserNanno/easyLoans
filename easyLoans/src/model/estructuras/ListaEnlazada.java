package model.estructuras;

public class ListaEnlazada {

    private NodoListaEnlazada cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertar(String dato) {
        NodoListaEnlazada nuevoNodo = new NodoListaEnlazada(dato);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
    }

    public void mostrar() {
        NodoListaEnlazada actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    public int size() {
        int count = 0;
        NodoListaEnlazada actual = cabeza;
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }

    // Agrega este método para obtener la cabeza si realmente lo necesitas fuera de la clase
    public NodoListaEnlazada getCabeza() {
        return cabeza;
    }
}
