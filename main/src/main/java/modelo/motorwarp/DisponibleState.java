package modelo.motorwarp;

public class DisponibleState implements EstadoMotor {
    
    public DisponibleState(){ }
    
    @Override
    public String getNombre(){
        return "Disponible";
    }
    
    @Override
    public boolean estaDisponible(){
        return true;
    }
    

    @Override
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new PreparandoSaltoState());
    }
    

    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede saltar: primero debe preparar el motor.");
    }
    
    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede enfriar: el motor ya está disponible.");
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el salto: no hay un salto activo.");
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el enfriamiento: el motor no está enfriándose.");

    }
    
}
