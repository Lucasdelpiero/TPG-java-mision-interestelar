package modelo.tripulacion;

public interface Haber {
    
    public abstract double sueldo();
    public abstract String detalleHaber();
    public abstract Tripulante getTripulante();
    public abstract String toString();
}
