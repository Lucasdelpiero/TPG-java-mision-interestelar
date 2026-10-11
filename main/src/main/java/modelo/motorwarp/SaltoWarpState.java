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
        throw new MotorTransicionException("No se puede preparar otro salto: el motor ya está saltando.");
    }
    
    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede iniciar otro salto: ya hay uno en curso.");
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
        throw new MotorTransicionException("No se puede terminar el enfriamiento: el motor está realizando un salto.");
    }
    
}
