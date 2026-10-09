package modelo.motorwarp;

public class PreparandoSaltoState implements EstadoMotor {
    
    public PreparandoSaltoState(){ }
    
    @Override
    public String getNombre(){
        return "Preparando Salto";
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
        motor.setEstado(new SaltoWarpState());
        return true;
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
