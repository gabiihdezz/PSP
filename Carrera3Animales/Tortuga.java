package Carrera3Animales;

import java.util.concurrent.Semaphore;

public class Tortuga extends Animal {

    public Tortuga(String nombre, Semaphore semaforo) {
        super(nombre, semaforo);
        this.nombre = "Kylian";
        this.velocidad = 2;
        this.distancia = 0;
    }

    @Override
    public void carrera() {
        distancia += velocidad;
        System.out.println("La tortuga "+nombre + " avanza y ha recorrido " + distancia + "m");
    }
}
