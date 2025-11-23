package Carrera3Animales;

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
    public void carrera() {
        try {
            if (random.nextInt(5) == 0) { 
                int vuelo = random.nextInt(10);

                if (vuelo < 3) {
                    distancia -= 10;
                    if (distancia < 0) distancia = 0;
                    System.out.println("El pajaro "+ nombre + " vuela hacia la izquierda. Ahora está en " + distancia+"m");
                } else {
                    distancia += 10;
                    System.out.println("El pajaro "+ nombre + " vuela hacia la derecha. Ahora está en " + distancia+"m");
                }

            } else {
                distancia += velocidad;
                System.out.println("El pajaro "+ nombre + " avanza caminando y ha recorrido " + distancia + "m");
            }

            Thread.sleep(900);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
