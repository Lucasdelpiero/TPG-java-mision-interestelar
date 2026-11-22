package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

/**
 * Representa los estados del ciclo de operación del Motor Warp.
 */
public interface EstadoMotor {
    /**
     * <b>PRE</b>
     * - motor no nulo.
     * <b>POST</b>
     * - Ejecuta comportamiento del salto simple según la fase operativa del motor.
     */
    public void saltar(MotorWarp motor);
    
    /**
     * <b>PRE</b>
     * - motor no nulo.
     * <b>POST</b>
     * - Ejecuta o prepara salto warp transicionando de estado si se cumplen las condiciones de recursos.
     */
    public void saltoWarp(MotorWarp motor);
    
    /**
     * <b>POST</b>
     * - Retorna true si el motor se encuentra en estado disponible.
     */
    boolean estaDisponible();

    /**
     * <b>POST</b>
     * - Retorna el nombre representativo del estado.
     */
    String getNombre();
}
