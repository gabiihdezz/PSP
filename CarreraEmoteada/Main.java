package CarreraEmoteada;

import java.util.concurrent.Semaphore;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    
    // Campo estático para registrar el inicio de la carrera
    public static long startTime;

    public static void main(String[] args) {

        Semaphore semaforo = new Semaphore(1);
        Semaphore liana = new Semaphore(1);
        
        // Asumiendo la existencia de la clase Viento.java
        Viento viento = new Viento();
        viento.setDaemon(true); 
        viento.start();

        Animal t1 = new Tortuga("Tortuga", semaforo);
        Animal t2 = new Pajaro("Pájaro", semaforo);
        Animal t3 = new Liebre("Liebre", semaforo);
        Animal m1 = new Mono("Mono_1", liana);
        Animal m2 = new Mono("Mono_2", liana);
        Animal m3 = new Mono("Mono_3", liana);
        
        // INICIO DE LA CARRERA: Registrar el tiempo antes de arrancar los hilos
        startTime = System.currentTimeMillis(); 
        System.out.println("¡LA CARRERA HA COMENZADO!");

        t1.start();
        t2.start();
        t3.start();
        m1.start();
        m2.start();
        m3.start();
        
        // Esperar a que todos los hilos terminen
        try {
            t1.join();
            t2.join();
        	t3.join();
        	m1.join();
            m2.join();
            m3.join();

            System.out.println("\n ¡Todos han cruzado la meta! Procedemos a la clasificación final.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido mientras esperaba.");
        }
        
        qualy(t1, t2, t3, m1, m2, m3);
    }
    
    // Método qualy modificado para ordenar por tiempo (el menor gana)
    private static void qualy(Animal t1,Animal t2,Animal t3, Animal m1, Animal m2, Animal m3) {
        System.out.println("\n=== CLASIFICACIÓN FINAL (Por Tiempo)");
        
        Animal[] lista = {t1, t2, t3, m1,m2,m3};
        
        // Ordenación por tiempo de llegada (finishTime)
        Arrays.sort(lista, new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                long t1 = a1.getFinishTime();
                long t2 = a2.getFinishTime();
                
                // Manejar casos donde el animal NO TERMINÓ (DNF: finishTime == 0)
                
                // Si ambos terminaron (t1 > 0 y t2 > 0), el de menor tiempo gana (orden ascendente)
                if (t1 > 0 && t2 > 0) {
                    return Long.compare(t1, t2); 
                }
                
                // Si t1 terminó y t2 no, t1 va primero
                if (t1 > 0 && t2 == 0) return -1; 
                
                // Si t2 terminó y t1 no, t2 va primero
                if (t2 > 0 && t1 == 0) return 1; 
                
                // Si ninguno terminó (ambos t1 == 0), se ordenan por distancia recorrida (mayor distancia gana)
                return Integer.compare(a2.getDistancia(), a1.getDistancia());
            }
        });

        // Mostrar la clasificación
        for (int i = 0; i < lista.length; i++) {
        	boolean terminado = false;
        	if(lista[i].getFinishTime()>0){
        		terminado=true;
        	}

            String tiempo = terminado ? (lista[i].getFinishTime() + " segundos") : "00:00";
            String estado = terminado ? " Terminado" : " DNF"; 
            
            System.out.println((i + 1) + "º → " + lista[i].getNombre() +
                               " (Tiempo: " + tiempo +") -> "+ estado);
        }
    }
}