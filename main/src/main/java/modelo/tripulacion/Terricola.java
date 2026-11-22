package modelo.tripulacion;

public class Terricola extends PlanetaOrigen {
    
    public Terricola(Haber tripulante) {
        super(tripulante);
    }
    
    @Override
    public double sueldo() {
        return super.tripulante.sueldo() + 20;
    }
    @Override
    public String detalleHaber() {
        return this.tripulante.detalleHaber()
         + "\n:: [HABER] Subsidio: 20\n";
    }
    
    
    @Override
    public String toString() {
        return "Origen : Terricola " + super.toString();
    }
}