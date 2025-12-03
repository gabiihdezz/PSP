package CarreraEmoteada;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pajaro extends Animal {

    private Random random = new Random();

    public Pajaro(String nombre, Semaphore semaforo) {
        super(nombre, semaforo);
        this.nombre = "Valverde";
        this.velocidad = 3;
        this.distancia = 0;
    }

    @Override
    public void carrera() throws InterruptedException {
        try {
            boolean volando = false;
            int avance;
            int opcion = random.nextInt(10); // 0-9

            if (opcion < 3) { // 30% de probabilidad de volar
                volando = true;
                avance = (random.nextBoolean() ? 10 : -10); // 10 m/s o -10 m/s
                
                // Efecto del Viento: solo si está volando
                int estadoViento = Viento.estadoViento;
                if (estadoViento == 1) { // A favor
                    avance += 5;
                    System.out.println("El pajaro "+nombre + " Vuela a favor del viento (+5m/s).");
                    // CORRECCIÓN: Si tienes viento a favor, no retrocedas.
                    if (avance < 0) avance = 0; 
                } else if (estadoViento == -1) { // En contra                    avance -= 5;
                    System.out.println("El pajaro "+nombre + " Vuela en contra del viento (-5m/s).");
                }
                
                distancia += avance;
                System.out.println("El pajaro "+ nombre + " VUELAAA y avanza " + avance + "m. (Dist: " + distancia+"m)");
            
            } else { // 70% de probabilidad de correr (velocidad base 3 m/s)
                avance = velocidad; // velocidad base = 3
                
                // Regla del Charco: si pisa charco y va por tierra, resbala y pierde 5s.
                if (distancia % 10 == 0 && distancia > 0) {
                    System.out.println("El pajaro "+ nombre + " pisa un charco, resbala y pierde 5s.");
                    
                    // 🚨 CORRECCIÓN CLAVE: Multiplicar 5 segundos por el factor de aceleración
                    Thread.sleep(5 * Animal.GAME_TIME_SLEEP_MS); 
                }
                
                distancia += avance;
                System.out.println("El pajaro "+ nombre + " corre por tierra y avanza " + avance + "m. (Dist: " + distancia+"m)");
            }
            
            if (distancia < 0) distancia = 0; // No puede ir a distancia negativa

        } catch (InterruptedException e) {
            throw e; // Lanzar la excepción para que sea manejada por el run() de Animal
        }
    }
}