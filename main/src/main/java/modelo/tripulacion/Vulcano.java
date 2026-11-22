package modelo.tripulacion;

public class Vulcano extends PlanetaOrigen {
    
    public Vulcano(Haber tripulante) {
        super(tripulante);
    }
    
    @Override
    public double sueldo() {
        return super.tripulante.sueldo() + 30;
    }
    @Override
    public String detalleHaber() {
        return this.tripulante.detalleHaber()
        +"\n:: [HABER] Subsidio: 30\n";
    }
    
    public String toString() {
        return "Origen : Vulcano " + super.toString();
    }
    
    
    
}