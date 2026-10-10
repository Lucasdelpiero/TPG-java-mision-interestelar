package modelo.motorwarp;

    /**
     * <b>PRE</b>
     * El parametro motor no debe ser null
     * <b>POST</b>
     * Las operaciones de transicion devuelven true y modifican el estado del motor si la
     * transicion es valida para el estado actual; devuelven false y mantienen el estado
     * inalterado si la transicion no es permitida.
    */

public interface EstadoMotor {

    // Si el estado actual es Disponible, cambia el estado a preparando salto
    void prepararSalto(MotorWarp motor) throws MotorTransicionException;
    
    // Si el estado actual es Preparando Salto, cambia el estado a preparando SaltoWarp
    void saltar(MotorWarp motor) throws MotorTransicionException;
    
    // Si el estado actual es Saltando, cambia el estado a preparando Enfriando Estado
    void enfriar(MotorWarp motor) throws MotorTransicionException;
    
    // Si el estado actual es Saltando, cambia el estado a preparando Disponible Estado
    void terminarSalto(MotorWarp motor) throws MotorTransicionException;
    
    // Si el estado actual es Enfriamiento, cambia el estado a Disponible
    void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException;

    boolean estaDisponible();
    String getNombre();
}
