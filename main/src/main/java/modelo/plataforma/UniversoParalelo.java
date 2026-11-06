package modelo.plataforma;
import java.util.ArrayList;

public class UniversoParalelo {
    private final PlataformaDeCarga plataforma;
    private final ArrayList<Thread> hilosGeneradoras;
    private final ArrayList<Thread> hilosNaves;
    
    public UniversoParalelo(PlataformaDeCarga plataforma){
        this.plataforma = plataforma;
        this.hilosGeneradoras = new ArrayList<>();
        this.hilosNaves = new ArrayList<>();
    }
    
    public void registrarObservador(ObservadorPlataforma obj){
        plataforma.registrarObservador(obj);
    }
    
    public void agregarGeneradora(){
        GeneradoraDeEnergia gen= new GeneradoraDeEnergia(plataforma);
         // Se crea el hilo con la tarea a ejecutarse y con nombre que le asigno el mismo que sera el mismo que la generadora
        Thread th = new Thread(gen, "Generadora-" + gen.getId());
        // En Java, cuando el usuario cierra la vista (con la X), el hilo principal se cierra
        // pero los hilos secundarios siguen corriendo, entonces para eso uso setDaemon(true) para indicar
        // que si el principal se cierra estos deben morir automaticamente.
        th.setDaemon(true);
        th.start(); //Lanzar ejecucion del hil
        hilosGeneradoras.add(th);
        plataforma.notificarObservadores("¡Ingresó una nueva Generadora al universo paralelo! [Generadora " + gen.getId() + "]");
    }
    
    public void agregarNaveConsumidora(){
        NaveConsumidora nave= new NaveConsumidora(plataforma);
         // Se crea el hilo con la tarea a ejecutarse y con nombre que le asigno el mismo que sera el mismo que la nave
        Thread th = new Thread(nave, "NaveConsumidora-" + nave.getId());
        th.setDaemon(true);
        th.start(); //Lanzar ejecucion del hilo
        hilosNaves.add(th);
        plataforma.notificarObservadores("¡Ingresó una nueva Nave al universo paralelo! [Nave " + nave.getId() + "]");
    }
    
    public int getCantGeneradoras(){
        return hilosGeneradoras.size();
    }
    
    public int getCantNavesConsumidoras(){
        return hilosNaves.size();
    }
}
