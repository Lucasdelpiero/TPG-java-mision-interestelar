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
    public boolean prepararSalto(MotorWarp motor){
        motor.setEstado(new PreparandoSaltoState());
        return true;
    }
    
    @Override
    public boolean saltar(MotorWarp motor){
        return false;
    }
    
    @Override
    public boolean enfriar(MotorWarp motor){
        return false;
    }
    
    @Override
    public boolean terminarSalto(MotorWarp motor){
        return false;
    }
    
    @Override
    public boolean terminarEnfriamiento(MotorWarp motor){
        return false;
    }
    
}
