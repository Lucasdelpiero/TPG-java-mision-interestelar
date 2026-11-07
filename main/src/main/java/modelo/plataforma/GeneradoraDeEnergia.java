package modelo.plataforma;

// GeneradoraDeEnergia es Productor
public class GeneradoraDeEnergia implements Runnable{
    private static int ultimoId;
    private int id;
    private PlataformaDeCarga plataforma;
    
    public GeneradoraDeEnergia(PlataformaDeCarga plataforma){
        //Diferentes threads pueden acceder a ultimoId simultanetamente asi que hay que proteger el acceso con synchronized
        this.id = generarId();
        this.plataforma = plataforma;
    }
    
    public void run(){
        try {
            // La generadora fabrica baterias todo el tiempo, por ello no se sale del bucle
            while(true){
                Thread.sleep(1500); //simular creacion, 1.5 seg fijos
                Bateria bat = generarBateria();
                plataforma.notificarObservadores("Batería producida por [Generadora " + id + "].");
                plataforma.depositar(bat, id);
            }
        }
        catch(InterruptedException e){
            // Marcar como interrumpido de nuevo, buena practica aunque es prescindible aqui
            // Dado que no se ejecuta nada mas en el catch, en este caso no hace falta
            Thread.currentThread().interrupt();
        }
    }
    
    private static synchronized int generarId(){
        ultimoId++;
        return ultimoId;
    }
    
    private Bateria generarBateria(){
        return new Bateria();
    }
    
    public int getId() {
        return id;
    }
}
