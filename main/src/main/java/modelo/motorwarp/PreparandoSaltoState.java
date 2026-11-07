package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

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
    public void saltar(MotorWarp motor){
        AsistenteComando ac = motor.getAC();
        Recursos recursos = ac.getRecursos();
        if (recursos.getCombustible() >= 2)
            recursos.consumeCombustible(2);
    }
    
    @Override
    public void saltoWarp(MotorWarp motor){
        AsistenteComando ac = motor.getAC();
        Recursos recursos = ac.getRecursos();
        saltar(motor);
        if(recursos.getEnergia() >= 5 && recursos.getDesgaste() >= 3)
            motor.setEstado(new SaltoWarpState());
    }
    
    /*
        
    @Override
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede preparar el salto: el motor ya se está preparando.");
    }
    
    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        System.out.println("Motor warp: saltando");
        motor.setEstado(new SaltoWarpState());
    }
    

    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede enfriar: el motor está preparando el salto.");
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el salto: todavía no comenzó.");
    }
    
    @Override
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el enfriamiento: el motor está preparando el salto.");
    }
    */
}
