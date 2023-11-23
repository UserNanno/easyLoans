package model.estructuras;

public class NodoArbolBinario {
    int valor;
    NodoArbolBinario izquierda, derecha;

    public NodoArbolBinario(int valor) {
        this.valor = valor;
        izquierda = derecha = null;
    }
}

