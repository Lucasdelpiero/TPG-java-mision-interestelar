package modelo.tripulacion;

public class Teniente extends Cargo {
    
    public Teniente() {
    }
 
    @Override
    public int sueldoBase() {
        return 400;
    }
    @Override 
    public double adicionalCargo() {
        return 0.03 ;
    }
    @Override
    public String getCargoTripulante() {
        return "Teniente";
    }
}
