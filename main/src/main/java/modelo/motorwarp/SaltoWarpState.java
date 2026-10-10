package modelo.motorwarp;

public class SaltoWarpState implements EstadoMotor {
    
    public SaltoWarpState(){ }
    
    @Override
    public String getNombre(){
        return "Salto warp";
    }
    
    @Override
    public boolean estaDisponible(){
        return false;
    }
    
    @Override
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new EnfriamientoState());
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new DisponibleState());
    }
    
    @Override
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
}
