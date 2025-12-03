package CarreraEmoteada;

import java.util.concurrent.Semaphore;

public abstract class Animal extends Thread {
	
    protected static final int META = 300; 
    
    // CAMBIO CLAVE: Factor de velocidad de la simulación. 
    // 1 ms simula 1 segundo de juego (¡1000 veces más rápido!)
    public static final int GAME_TIME_SLEEP_MS = 1; 

    protected String nombre;
    protected volatile int distancia;
    protected int velocidad;
    protected Semaphore semaforo;
    
    // Nuevo campo para registrar el tiempo final (en ms desde el inicio de la carrera)
    private volatile long finishTime = 0; 

    public Animal(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
        this.distancia = 0;
    }

        public String getNombre() {
    		return nombre;
    	}

    	public void setNombre(String nombre) {
    		this.nombre = nombre;
    	}

    	public int getDistancia() {
    		return distancia;
    	}

    	public void setDistancia(int distancia) {
    		this.distancia = distancia;
    	}

    	public int getVelocidad() {
    		return velocidad;
    	}

    	public void setVelocidad(int velocidad) {
    		this.velocidad = velocidad;
    	}

    	public Semaphore getSemaforo() {
    		return semaforo;
    	}

    	public void setSemaforo(Semaphore semaforo) {
    		this.semaforo = semaforo;
    		}
    	
    	public long getFinishTime() {
        return finishTime;
    }
    

	protected abstract void carrera() throws InterruptedException; // Ahora abstract

    @Override
    public void run() {
        try {
            while (distancia < META) {
                
                // Lógica del túnel simplificada para la entrada (M50)
                if (distancia == 50) {
                    System.out.println(nombre + " ha llegado al túnel (50m) y espera su turno...");
                    semaforo.acquire(); 
                    System.out.println(nombre + " ha ENTRADO en el túnel.");
                }
                
                // Ejecutar la acción del animal
                carrera(); 

                // LÓGICA DE FINALIZACIÓN Y REGISTRO DE TIEMPO
                if (distancia >= META && finishTime == 0) {
                    // Registra el tiempo si es la primera vez que cruza la meta
                    finishTime = System.currentTimeMillis() - Main.startTime;
                    System.out.println("🏁 " + nombre + " ha llegado a la meta en " + finishTime + " ms!");
                    break; 
                }

                // Lógica del túnel para la SALIDA (M150)
                if (distancia >= 150) { 
                    System.out.println(nombre + " ha SALIDO del túnel.");
                    semaforo.release(); 
                }

                // Mostrar estado actual
                if (distancia > 50 && distancia < 150) {
                    System.out.println(nombre + " avanza DENTRO del túnel (" + distancia + "m)");
                } else if (distancia < META) {
                    System.out.println(nombre + " corre FUERA del túnel (" + distancia + "m)");
                }

                // CAMBIO CLAVE: Pausa para simular el paso del tiempo (Acelerado)
                Thread.sleep(GAME_TIME_SLEEP_MS); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}