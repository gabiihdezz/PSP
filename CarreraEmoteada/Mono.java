package CarreraEmoteada;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mono extends Animal{

	private Random random = new Random();
    public static final int GAME_TIME_SLEEP_MS = 1; 

    protected static final int META = 300; 
    protected volatile int opcion = 4+  random.nextInt(3); 
    //de 4 a 6 metros por segundo de manera random 
    
	public Mono(String nombre, Semaphore semaforo) {
		super(nombre, semaforo);
        this.nombre = nombre;
        this.distancia = 0;
        this.velocidad = opcion;
	}

	@Override
	protected void carrera() throws InterruptedException {
		int avance=velocidad;
		/*mono va por las lianas
		hay lianas cada20 metros
		2 monos en la misma cuerda caen, tienen que esperar, participan 3
		no tocan charcos ni tunel ni na.
		meta = 500*/		
        while (distancia < META) {
			distancia += avance;
			System.out.println("------El " +nombre+" ha recorrido " + distancia + "m (v: " + avance + "m/s)");
			if(distancia %20 !=0) {
				for(int i = 0 ; i <2; i++) {
					distancia = distancia - i ;
		            if (distancia %20==0) {
		    			System.out.println("------El " +nombre+" ha recorrido " + distancia + "m (v: " + avance + "m/s)");
		    			break;
		            }
				}
			}
            if (distancia %20==0) {
                System.out.println(nombre + " está esperando a que este la liana libre...");
                semaforo.acquire(); 
                System.out.println(nombre + " ha entrado en la liana .");
            }
            else {
            	semaforo.release();
            }
            
        }

	}
	

}
