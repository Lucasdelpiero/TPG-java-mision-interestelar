package modelo.tripulacion;

public class FactoryCargo {
    public Cargo crearCargo(String cargo) {
        if (cargo.equalsIgnoreCase("Capitan"))
            return new Capitan();
        else
            if (cargo.equalsIgnoreCase("Teniente"))
                return new Teniente();
            else
                if (cargo.equalsIgnoreCase("Alferez"))
                    return new Alferez();
                else
                    if (cargo.equalsIgnoreCase("Consejero"))
                        return new Consejero();
                    else
                        throw new IllegalArgumentException("Cargo invalida");
    }    
}
