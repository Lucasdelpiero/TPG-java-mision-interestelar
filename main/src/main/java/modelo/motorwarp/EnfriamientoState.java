package modelo.motorwarp;

public class EnfriamientoState implements EstadoMotor {
    
    public EnfriamientoState(){ }
    
    @Override
    public String getNombre(){
        return "Enfriamiento";
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
        throw new MotorTransicionException("No puede transicionar");
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No puede transicionar");
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{

        motor.setEstado(new DisponibleState());
    }
    
}
