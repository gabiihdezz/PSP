package Carrera3Animales;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore semaforo = new Semaphore(1);

        Animal t1 = new Tortuga("Tortuga", semaforo);
        Animal t2 = new Pajaro("Pájaro", semaforo);
        Animal t3 = new Liebre("Liebre", semaforo);

        t1.start();
        t2.start();
        t3.start();
    

        Animal[] lista = {t1, t2, t3};

        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[j].getDistancia() > lista[i].getDistancia()) {
                    Animal tmp = lista[i];
                    lista[i] = lista[j];
                    lista[j] = tmp;
                }
            }
        }

        for (int i = 0; i < lista.length; i++) {
            System.out.println((i + 1) + "º → " + lista[i].getNombre());
        }
    }
}

