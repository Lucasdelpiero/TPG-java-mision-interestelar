package modelo.tripulacion;

public abstract class PlanetaOrigen implements Haber {
    
    protected Haber tripulante;
    
    public PlanetaOrigen(Haber tripulante) {
        this.tripulante = tripulante;
    }
    
    @Override
    public Tripulante getTripulante() {
        Haber actual = this.tripulante;
        while (!(actual instanceof Tripulante)) {
            actual = ((PlanetaOrigen) actual).tripulante;
        }
        return (Tripulante) actual;
    }
    
    @Override
    public String toString() {
        return this.tripulante.toString();
    }
}
