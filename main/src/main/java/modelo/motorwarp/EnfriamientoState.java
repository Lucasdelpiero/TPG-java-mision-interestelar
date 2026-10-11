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
        throw new MotorTransicionException("No se puede preparar el salto: el motor debe terminar de enfriarse.");
    }
    
    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede saltar: el motor está enfriándose.");
    }
    
    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede enfriar nuevamente: el motor ya está enfriándose.")
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el salto: el motor ya está en enfriamiento.");
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{

        motor.setEstado(new DisponibleState());
    }
    
}
