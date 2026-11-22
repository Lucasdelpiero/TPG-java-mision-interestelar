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
    
    /**
     * <b>PRE</b>
     * - AC asignado con recursos válidos.
     * <b>POST</b>
     * - Retorna true si la nave posee al menos 2 unidades de combustible.
     */
    public boolean puedeSaltar(){
        return AC.getRecursos().getCombustible() >= 2;
    }
    
    /**
     * <b>POST</b>
     * - Delega el salto normal en el estado actual del motor.
     */
    public void saltar(){
        this.estado.saltar(this);
    }
    
    /**
     * <b>POST</b>
     * - Delega la acción de salto warp en el estado actual del motor.
     */
    public void saltoWarp(){
        this.estado.saltoWarp(this);
    }

    /**
     * <b>POST</b>
     * - Alias público de saltoWarp requerido por el enunciado.
     */
    public void saltarWarp(){
        this.saltoWarp();
    }
    
    
    /**
     * <b>POST</b>
     * - Actualiza el estado operativo del motor o lanza IllegalArgumentException si el estado es nulo.
     * @param estado nuevo estado del motor
     */
    void setEstado(EstadoMotor estado){ //Visibilidad de paquete, correcta
        if (estado == null)
            throw new IllegalArgumentException("Estado no esta definido");
        
        this.estado = estado;
    }
    

    
    /** 
     * <b>POST</b>
     * - Devuelve descripción del estado actual ("Disponible", "Preparando Salto", "Salto warp", "Enfriamiento").
     */
    public String getNombreEstado(){
        return this.estado.getNombre();
    }
    
    
    
    /** 
     * <b>POST</b>
     * - Devuelve true si el estado actual es DisponibleState; false en otro caso.
     */
    public boolean estaDisponible(){
        return this.estado.estaDisponible();
    }
    
    
    public AsistenteComando getAC() {
        return AC;
    }
     
}
