package modelo.motorwarp;

/**
 * El atributo estado nunca debe ser null
 * El estado inicial es DisponibleState
 * El estado solo cambia cuando la transicion es semanticamente valida, por ej,
 * no se puede transicionar de en preparando a disponible
 * Las transiciones validas retornan true y las invalidas retornan false
 * @author defin
 */
public class MotorWarp {
    private EstadoMotor estado;
    
    public MotorWarp(){
        this.estado = new DisponibleState();
    }
    
    /**
     * Sirve para setear el estado del motor warp
     * <b>PRE</b>
     * - El estado debe estar definido, debe no ser null en caso contrario se lanza una excepcion
     * @param estado 
     */
    void setEstado(EstadoMotor estado){ //Visibilidad de paquete, correcta
        if (estado == null)
            throw new IllegalArgumentException("Estado no esta definido");
        
        this.estado = estado;
    }
    
    public boolean prepararSalto(){
        return this.estado.prepararSalto(this);
    }
    
    public boolean saltar(){
        return this.estado.saltar(this);
    }
    
    public boolean enfriar(){
        return this.estado.enfriar(this);
    }
    
    public boolean terminarSalto(){
        return this.estado.terminarSalto(this);
    }
    
    public boolean terminarEnfriamiento(){
        return this.estado.terminarEnfriamiento(this);
    }
    
    /** 
     * <b>POST</b>
     * - Devuelve Devuelve un String no nulo ni vacío que describe el estado actual
     * - "Disponible", "Preparando Salto", "Salto warp", "Enfriamiento".
     * @return 
     */
    public String getNombreEstado(){
        return this.estado.getNombre();
    }
    
    /** 
     * <b>POST</b>
     * - Devuelve true si y solo si el estado actual es DisponibleState; false en cualquier otro estado.
     * @return 
     */
    public boolean estaDisponible(){
        return this.estado.estaDisponible();
    }
}
