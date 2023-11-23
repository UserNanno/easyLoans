
package arbolBinario;

import java.util.Random;

public class pruebaEstres {
    public static void main(String[] args) {
        
        System.out.println("Prueba Insercion");
        pruebaInsertar(100);
        pruebaInsertar(1000);//mil
        pruebaInsertar(10000);
        pruebaInsertar(100000);
        pruebaInsertar(1000000);//un millon
        pruebaInsertar(10000000);//diez millones
        pruebaInsertar(100000000);//cien millones

    }
    
    public static void pruebaInsertar(int num){
        ArbolBinario arbol = new ArbolBinario();
        Random random = new Random();
        
        long inicio = System.currentTimeMillis();
        
        // Insertar datos aleatorios de 8 dígitos con un bucle for
        for (int i = 0; i < num; i++) {
            int valor = 10000000 + random.nextInt(90000000); // Genera números aleatorios entre 10000000 y 99999999
            //System.out.println(valor);
            arbol.insertar(valor);
        }
        
        long fin = System.currentTimeMillis();
        long tiempo = (fin - inicio);
        
        System.out.println("Tiempo transcurrido en milisegundos: " + tiempo);
    
    }
    
    
    
    public static void pruebaBusqueda(int num) {
    ArbolBinario arbol = new ArbolBinario();
    Random random = new Random();
    
    for (int i = 0; i < num; i++) {
            int valor = 10000000 + random.nextInt(90000000); // Genera números aleatorios entre 10000000 y 99999999
            //System.out.println(valor);
            arbol.insertar(valor);
        }

    long inicio = System.nanoTime();

    arbol.imprimirOrden();

    long fin = System.nanoTime();
    long tiempo = fin - inicio;

    System.out.println("Tiempo transcurrido en nanosegundos: " + tiempo);
    
    }

}
