package modelo.plataforma;

public class Bateria {
    private static int ultimoId;
    private final int id; // Para dintinguir distintas baterias entre si
    
    public Bateria() {
        // Necesario para evitar que dos thread intenten acceder al ultimoId y se termine creando dos bats con el mismo id
        synchronized(Bateria.class){
            ultimoId++;
            this.id = ultimoId;
        }
    }

    public int getId() {
        return id;
    }
    
    public String toString(){
        return "Bateria nro: " + id;
    }
}
