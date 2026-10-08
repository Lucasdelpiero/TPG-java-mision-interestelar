package modelo.motorwarp;

public class SaltoWarpState implements EstadoMotor {
    
    public SaltoWarpState(){ }
    
    public String getNombre(){
        return "Salto warp";
    }
    
    public boolean estaDisponible(){
        return false;
    }
    
    public boolean prepararSalto(MotorWarp motor){
        return false;
    }
    
    public boolean saltar(MotorWarp motor){
        return false;
    }
    
    public boolean enfriar(MotorWarp motor){
        motor.setEstado(new EnfriamientoState());
        return true;
    }
    
    public boolean terminarSalto(MotorWarp motor){
        motor.setEstado(new DisponibleState());
        return true;
    }
    
    public boolean terminarEnfriamiento(MotorWarp motor){
        return false;
    }
    
}
