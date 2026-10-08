package modelo.motorwarp;

public class PreparandoSaltoState implements EstadoMotor {
    
    public PreparandoSaltoState(){ }
    
    public String getNombre(){
        return "Preparando Salto";
    }
    
    public boolean estaDisponible(){
        return false;
    }
    
    public boolean prepararSalto(MotorWarp motor){
        return false;
    }
    
    public boolean saltar(MotorWarp motor){
        motor.setEstado(new SaltoWarpState());
        return true;
    }
    
    public boolean enfriar(MotorWarp motor){
        return false;
    }
    
    public boolean terminarSalto(MotorWarp motor){
        return false;
    }
    
    public boolean terminarEnfriamiento(MotorWarp motor){
        return false;
    }
    
}
