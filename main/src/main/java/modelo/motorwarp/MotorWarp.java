package modelo.motorwarp;
import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

/**
 * <b>INVARIANTE</b>
 * El atributo estado nunca debe ser null
 * El estado inicial es DisponibleState
 * El estado solo cambia cuando la transicion es semanticamente valida, por ej,
 * no se puede transicionar de en preparando a disponible
 * Las transiciones validas retornan true y las invalidas retornan false
 */
public class MotorWarp {
    private EstadoMotor estado;
    private AsistenteComando AC;
    
    public MotorWarp(){
        this.estado = new DisponibleState();
    }

    public void setAC(AsistenteComando AC) {
        this.AC = AC;
    }
    
    public boolean puedeSaltar(){
        return AC.getRecursos().getCombustible() >= 2;
    }
    
    public void saltar(){
        this.estado.saltar(this);
    }
    
    public void saltoWarp(){
        this.estado.saltoWarp(this);
    }
    
    
    /**
     * Sirve para setear el estado del motor warp
     * <b>PRE</b>
     * El estado debe estar definido, debe no ser null en caso contrario se lanza una excepcion
     * @param estado 
     */
    void setEstado(EstadoMotor estado){ //Visibilidad de paquete, correcta
        if (estado == null)
            throw new IllegalArgumentException("Estado no esta definido");
        
        this.estado = estado;
    }
    

    
    /** 
     * <b>POST</b>
     * Devuelve un String no nulo ni vacío que describe el estado actual
     * "Disponible", "Preparando Salto", "Salto warp", "Enfriamiento".
     */
    public String getNombreEstado(){
        return this.estado.getNombre();
    }
    
    
    
    /** 
     * <b>POST</b>
     * Devuelve true si y solo si el estado actual es DisponibleState; false en cualquier otro estado.
     */
    public boolean estaDisponible(){
        return this.estado.estaDisponible();
    }
    
    
    public AsistenteComando getAC() {
        return AC;
    }
     
}
