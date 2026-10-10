package modelo.motorwarp;

public interface EstadoMotor {
    void prepararSalto(MotorWarp motor) throws MotorTransicionException;
    void saltar(MotorWarp motor) throws MotorTransicionException;
    void enfriar(MotorWarp motor) throws MotorTransicionException;
    void terminarSalto(MotorWarp motor) throws MotorTransicionException;
    void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException;
    boolean estaDisponible();
    String getNombre();
}
