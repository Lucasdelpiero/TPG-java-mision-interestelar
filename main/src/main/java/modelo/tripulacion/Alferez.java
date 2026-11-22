package modelo.tripulacion;

public class Alferez extends Cargo {
    
    public Alferez() {
    }
    
    @Override
    public int sueldoBase() {
        return 200;
    }
    @Override 
    public double adicionalCargo() {
        return 0.005 ;
    }
    @Override
    public String getCargoTripulante() {
        return "Alferez";
    }
}
