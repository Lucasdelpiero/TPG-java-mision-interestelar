package modelo.motorwarp;

public class SaltoWarpState implements EstadoMotor {
    
    public SaltoWarpState(){ }
    
    public String getNombre(){
        return "Salto warp";
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
        motor.setEstado(new EnfriamientoState());
    }
    
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new DisponibleState());
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
}
