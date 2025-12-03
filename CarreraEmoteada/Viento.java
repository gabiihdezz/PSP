package CarreraEmoteada;

import java.util.Random;

public class Viento extends Thread {
    // 0 = No hay, 1 = A Favor, -1 = En Contra
    public static volatile int estadoViento = 0; 

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (true) {
                // Genera una racha de viento cada 5 a 15 segundos
                Thread.sleep(5 + rand.nextInt(10)); 
                
                int racha = rand.nextInt(10); // 0, 1, 2, 3, 4
                if (racha == 0 || racha == 1) { // Viento a favor (20% de probabilidad)
                    estadoViento = 1;
                    System.out.println("🌬️ ¡VIENTO A FAVOR!");
                } else if (racha == 3) { // Viento en contra (10% de probabilidad)
                    estadoViento = -1;
                    System.out.println("💨 ¡VIENTO EN CONTRA!");
                } else { // Sin viento (60% de probabilidad)
                    estadoViento = 0;
                    System.out.println("☁️ El viento se ha calmado.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}