package CarreraEmoteada;

import java.util.concurrent.Semaphore;

public class Liebre extends Animal {

    private int corre = 0;
    private boolean duerme = false;
    
    // El tiempo de sueño será de 5 segundos de juego (5000 ms)
    // Usaremos el factor de aceleración (Animal.GAME_TIME_SLEEP_MS) 
    // Si la velocidad es 1ms = 1s, entonces 5000ms de juego serán 5ms de simulación real.
    private static final int TIEMPO_SUENO_MS = 5000 / 100 * Animal.GAME_TIME_SLEEP_MS;

    public Liebre(String nombre, Semaphore semaforo) {
        super(nombre, semaforo);
        this.nombre = "Liebrinha";
        this.distancia = 0;
        this.velocidad = 5;
    }

    @Override
    public void carrera() throws InterruptedException {
        try {
            // 1. Lógica del Charco (Se mantiene, asumiendo 5s de pérdida)
            if (distancia % 10 == 0 && distancia > 0) {
                System.out.println("La liebre "+nombre + " pisa un charco, resbala y pierde 5s.");
                // 5 segundos de juego de penalización
                Thread.sleep(5000 / 1000 * Animal.GAME_TIME_SLEEP_MS); 
            }
            
            // 2. Comportamiento de Sueño (Modificado)
            if (duerme) {
                System.out.println("La liebre "+nombre + " duerme durante 5 s...");
                // Duerme por 5 segundos de juego (TIEMPO_SUENO_MS en ms reales)
                Thread.sleep(TIEMPO_SUENO_MS);
                duerme = false;
                corre = 0; // Se resetea el contador de carreras
                System.out.println("La liebre "+nombre + " se despertó.");
                return; // Sale del turno para que el bucle principal haga la pausa de 1s
            }

            // 3. Comportamiento de Carrera
            if (corre < 4) {
                distancia += velocidad;
                corre++;
                System.out.println("La liebre "+ nombre + " corre y ha recorrido " + distancia + " m");
            } else {
                duerme = true;
                System.out.println("La liebre "+nombre + " se va a dormir.");
            }

        } catch (InterruptedException e) {
            throw e; // Lanza la excepción para que sea manejada por el run() de Animal
        }
    }
}