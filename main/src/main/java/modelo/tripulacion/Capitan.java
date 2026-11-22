package modelo.tripulacion;

public class Capitan extends Cargo {
    
    public Capitan() {
    } 
    
    @Override
    public int sueldoBase() {
        return 1000;
    }
    
    @Override 
    public double adicionalCargo() {
        return 0.2;
    }
    @Override
    public String getCargoTripulante() {
        return "Capitan";
    }
    
}
