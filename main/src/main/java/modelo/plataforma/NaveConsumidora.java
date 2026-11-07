package modelo.plataforma;

// NaveConsumidora es Consumidor
public class NaveConsumidora implements Runnable {
    private static int ultimoId;
    private int id;
    private PlataformaDeCarga plataforma;

    public NaveConsumidora(PlataformaDeCarga plataforma) {
        //Diferentes threads pueden acceder a ultimoId simultanetamente asi que hay que proteger el acceso con synchronized
        this.id = generarId();
        this.plataforma = plataforma;
    }
    
    public void run(){
        try {
            // La nave consume energia todo el tiempo, por ello el while true sin corte tiene sentido
            while(true){
                Thread.sleep(2000); //simular tiempo de vida la nave antes de volver a requerir bateria
                plataforma.notificarObservadores("[Nave " + id + "] solicitando batería.");
                Bateria bat = plataforma.retirar(id); //retirar y obtener la bateria
                // Hacer cosas con la bateria...
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
    
    public int getId() {
        return id;
    }
}
