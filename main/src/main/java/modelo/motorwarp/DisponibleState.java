package modelo.motorwarp;

public class DisponibleState implements EstadoMotor {
    
    public DisponibleState(){ }
    
    public String getNombre(){
        return "Disponible";
    }
    
    public boolean estaDisponible(){
        return true;
    }
    
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new PreparandoSaltoState());
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
        throw new MotorTransicionException("No puede transicionar");
    }
    
}
