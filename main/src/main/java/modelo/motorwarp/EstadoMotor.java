package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

    /**
     * <b>PRE</b>
     * El parametro motor no debe ser null
     * <b>POST</b>
     * Las operaciones de transicion devuelven true y modifican el estado del motor si la
     * transicion es valida para el estado actual; devuelven false y mantienen el estado
     * inalterado si la transicion no es permitida.
    */

public interface EstadoMotor {
    public void saltar(MotorWarp motor);
    
    public void saltoWarp(MotorWarp motor);
    
    boolean estaDisponible();
    String getNombre();
}
