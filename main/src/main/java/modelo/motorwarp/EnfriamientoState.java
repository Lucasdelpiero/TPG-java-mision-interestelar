package modelo.motorwarp;

public class EnfriamientoState implements EstadoMotor {
    
    public EnfriamientoState(){ }
    
    public String getNombre(){
        return "Enfriamiento";
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
        return false;
    }
    
    public boolean terminarSalto(MotorWarp motor){
        return false;
    }
    
    public boolean terminarEnfriamiento(MotorWarp motor){
        motor.setEstado(new DisponibleState());
        return true;
    }
    
}
