package Carrera3Animales;

import java.util.concurrent.Semaphore;

public class Animal extends Thread{
	
    private static final int META = 300; 
    protected String nombre;
    protected int distancia;
    protected int velocidad;
    protected Semaphore semaforo;
    
    public Animal(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    public Animal(String nombre, int distancia, int velocidad) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.velocidad = velocidad;
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

	protected void carrera() {}

    @Override
    public void run() {
        try {
            while (distancia < META) {

              
                carrera();

                if (distancia >= META) break;

                if (distancia == 50) {
                    System.out.println(nombre + " ha llegado al túnel y espera su turno...");
                    semaforo.acquire(); 
                    System.out.println(nombre + " ha ENTRADO en el túnel.");
                }

                Thread.sleep(1000);
                distancia++;  

                if (distancia > 50 && distancia < 150) {
                    System.out.println(nombre + " avanza DENTRO del túnel (" + distancia + "m)");
                } else {
                    System.out.println(nombre + " corre FUERA del túnel (" + distancia + "m)");
                }

                if (distancia == 150) {
                    System.out.println(nombre + " ha SALIDO del túnel.");
                    semaforo.release(); 
                }

                if (distancia >= META) {
                    System.out.println(nombre + " ha llegado a la meta!");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
