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
    public boolean prepararSalto(MotorWarp motor){
        return false;
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
        motor.setEstado(new DisponibleState());
        return true;
    }
    
}
