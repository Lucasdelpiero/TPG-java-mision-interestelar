package modelo.motorwarp;

public interface EstadoMotor {
    boolean prepararSalto(MotorWarp motor);
    boolean saltar(MotorWarp motor);
    boolean enfriar(MotorWarp motor);
    boolean terminarSalto(MotorWarp motor);
    boolean terminarEnfriamiento(MotorWarp motor);
    boolean estaDisponible();
    String getNombre();
}
