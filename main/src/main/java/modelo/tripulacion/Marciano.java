package modelo.tripulacion;

public class Marciano extends PlanetaOrigen {
    
    public Marciano(Haber tripulante) {
        super(tripulante);
    }
    
    @Override
    public double sueldo() {
        return super.tripulante.sueldo() + 18;
    }
    @Override
    public String detalleHaber() {
        return this.tripulante.detalleHaber()
        +"\n:: [HABER] Subsidio: 18\n";
    }
    
    public String toString() {
        return "Origen : Marciano " + super.toString();
    }
}