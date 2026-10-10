package modelo.motorwarp;

public class EnfriamientoState implements EstadoMotor {
    
    public EnfriamientoState(){ }
    
    public String getNombre(){
        return "Enfriamiento";
    }
    
    public boolean estaDisponible(){
        return false;
    }
    
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new DisponibleState());
    }
    
}
