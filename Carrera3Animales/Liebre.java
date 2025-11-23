package Carrera3Animales;

import java.util.concurrent.Semaphore;

public class Liebre extends Animal {

    private int corre = 0;
    private boolean duerme = false;

    public Liebre(String nombre, Semaphore semaforo) {
        super(nombre, semaforo);
        this.nombre = "Liebrinha";
        this.distancia = 0;
        this.velocidad = 5;
    }

    @Override
    public void carrera() {
        try {
            if (duerme) {
                System.out.println("La liebre "+nombre + " duerme durante 10 s");
                Thread.sleep(10000);
                duerme = false;
                System.out.println("La liebre "+nombre + " se despertó");
                return;
            }

            if (corre < 4) {
                distancia += velocidad;
                corre++;
                System.out.println("La liebre "+ nombre + " corre y ha recorrido" + distancia + " m");
            } else {
                duerme = true;
                corre = 0;
                System.out.println("La liebre "+nombre + " se va a dormir");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
