package util;

import java.util.HashSet;
import java.util.Set;

public class DniManager {
    private Set<String> dnisValidos;

    public DniManager() {
        this.dnisValidos = new HashSet<>();
    }

    public void agregarDni(String dni) {
        dnisValidos.add(dni);
    }

    public boolean contieneDni(String dni) {
        return dnisValidos.contains(dni);
    }
}
