package model.estructuras;

public class EntradaTabla {
    private final String clave;
    private final String valor;

    public EntradaTabla(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public String getClave() {
        return clave;
    }

    public String getValor() {
        return valor;
    }
}