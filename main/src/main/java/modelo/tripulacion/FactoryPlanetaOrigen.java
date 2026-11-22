package modelo.tripulacion;

public class FactoryPlanetaOrigen {
    
    public PlanetaOrigen factoryPlanetaOrigen(String origen , Tripulante tripulante) {
        if (origen == null || tripulante == null) {
            throw new IllegalArgumentException("Origen o tripulante nulo");
        }
        if (origen.equalsIgnoreCase("Vulcano"))
            return new Vulcano(tripulante);
        else if (origen.equalsIgnoreCase("Terricola"))
            return new Terricola(tripulante);
        else if (origen.equalsIgnoreCase("Marciano"))
            return new Marciano(tripulante);
        throw new IllegalArgumentException("Origen invalido: " + origen);
    }
}
