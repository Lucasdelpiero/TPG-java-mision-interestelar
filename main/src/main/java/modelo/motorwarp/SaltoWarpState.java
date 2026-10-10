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
    public boolean prepararSalto(MotorWarp motor){
        return false;
    }
    
    @Override
    public boolean saltar(MotorWarp motor){
        return false;
    }
    
    @Override
    public boolean enfriar(MotorWarp motor){
        motor.setEstado(new EnfriamientoState());
        return true;
    }
    
    @Override
    public boolean terminarSalto(MotorWarp motor){
        motor.setEstado(new DisponibleState());
        return true;
    }
    
    @Override
    public boolean terminarEnfriamiento(MotorWarp motor){
        return false;
    }
    
}
