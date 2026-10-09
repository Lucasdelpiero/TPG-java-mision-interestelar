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
    boolean prepararSalto(MotorWarp motor);
    boolean saltar(MotorWarp motor);
    boolean enfriar(MotorWarp motor);
    boolean terminarSalto(MotorWarp motor);
    boolean terminarEnfriamiento(MotorWarp motor);
    boolean estaDisponible();
    String getNombre();
}
