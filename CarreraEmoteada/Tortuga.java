package CarreraEmoteada;

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
        int avance = velocidad; // velocidad base = 2
        
        // Regla del Charco: cada 10 metros
        if (distancia % 10 == 0 && distancia > 0) {
            avance += 3; // +3 m/s de bonus
            System.out.println("La tortuga "+nombre + " pisa un charco y obtiene un BONUS (+3m/s).");
        }
        
        distancia += avance;
        System.out.println("La tortuga "+nombre + " avanza y ha recorrido " + distancia + "m (v: " + avance + "m/s)");
    }}
