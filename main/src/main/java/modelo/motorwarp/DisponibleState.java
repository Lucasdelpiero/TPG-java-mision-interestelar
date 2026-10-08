package modelo.motorwarp;

public class DisponibleState implements EstadoMotor {
    
    public DisponibleState(){ }
    
    public String getNombre(){
        return "Disponible";
    }
    
    public boolean estaDisponible(){
        return true;
    }
    
    public boolean prepararSalto(MotorWarp motor){
        motor.setEstado(new PreparandoSaltoState());
        return true;
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
        return false;
    }
    
}
