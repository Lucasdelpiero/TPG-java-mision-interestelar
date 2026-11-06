package modelo.plataforma;

import java.util.ArrayList;

// PlataformaCarga es el Monitor
public class PlataformaDeCarga {
    private final int capacidadMaxima;
    private final ArrayList<Bateria> baterias;
    private final ArrayList<ObservadorPlataforma> observadores;
    
    public PlataformaDeCarga(int capacidadMaxima){
        this.capacidadMaxima = capacidadMaxima;
        this.baterias = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }
    
    // Hacemos synchronized tanto notificarObservadores como registrarObservador porque se ejecutan en hilos distintos
    // (los hilos de simulación recorren la lista y el hilo de la interfaz agrega elementos)
    
    // Recordar que java, el modificador en el metodo "synchorized" es equivalente a un synchorized(this) { }
    // dentro del metodo, es decir bloquea toda la instancia y da acceso a solo un thread en especifico
    // Esto ocurre para todos los metodos que usen synchronized, para los que no usen, todos los hilos pueden
    // acceder directamente
    
    // Debe ser synchonized para evitar casos como que la vista esta haciendo un add y justo queremos recorrer la lista
    // o casos de concurrencia similares
    public synchronized void notificarObservadores(String mensaje){
        for (ObservadorPlataforma obj: observadores){
            obj.onEvento(mensaje);
        }
    }
    
    // Debe ser synchronized para evitar que se intente registrar un observador mientras se esta recorriendo
    // el arrayList o casos de concurrencia similares
    public synchronized void registrarObservador(ObservadorPlataforma obj){
        observadores.add(obj);
    }
    
    // Se usa synchronized como modificador directamente porque es el recurso es uno solo, las baterias
    // Y solo puede haber un consumidor y/o productor al mismo tiempo
    
    public synchronized void depositar(Bateria bat, int idGeneradora) throws InterruptedException{
        while(baterias.size() >= capacidadMaxima){
            notificarObservadores("[Generadora " + idGeneradora + "] esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        baterias.add(bat);
        notificarObservadores("Batería con id: " + bat.getId() + " depositada en la Plataforma por [Generadora " + idGeneradora + "].");
        notifyAll();
    }
    
    public synchronized Bateria retirar(int idConsumidora) throws InterruptedException{
        while(baterias.isEmpty()){
            notificarObservadores("[Nave " + idConsumidora + "] esperando por falta de baterías en la Plataforma.");
            wait();
        }
        Bateria bat = baterias.removeFirst(); // Remover y obtener la bateria removida
        notificarObservadores("Batería retirada y utilizada por [Nave " + idConsumidora + "] (Batería " + bat.getId() + ").");
        notifyAll();
        return bat;
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    // Conviene hacerlo synchronized tambien por si los demas hilos necesitan este dato
    public synchronized int getCantBaterias() {
        return baterias.size();
    }
}
