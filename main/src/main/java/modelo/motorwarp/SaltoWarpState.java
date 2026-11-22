package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

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
    public void saltar(MotorWarp motor){
        assert motor != null : "El motor no puede ser nulo";
        AsistenteComando ac = motor.getAC();
        if (ac != null && ac.getRecursos() != null) {
            Recursos recursos = ac.getRecursos();
            if (recursos.getCombustible() >= 2)
                recursos.consumeCombustible(2);
        }
        motor.setEstado(new PreparandoSaltoState(1));
    }
    
    @Override
    public void saltoWarp(MotorWarp motor){
        assert motor != null : "El motor no puede ser nulo";
        AsistenteComando ac = motor.getAC();
        if (ac != null && ac.getRecursos() != null) {
            Recursos recursos = ac.getRecursos();
            if (recursos.getEnergia() >= 5){
                recursos.consumeEnergia(5);
                motor.setEstado(new EnfriamientoState());
            } else {
                motor.setEstado(new PreparandoSaltoState(1));
            }
        }
    }
    
    /*
    
    @Override
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede preparar otro salto: el motor ya está saltando.");
    }
    
    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede iniciar otro salto: ya hay uno en curso.");
    }
    
    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        System.out.println("Motor warp: enfriando");
        motor.setEstado(new EnfriamientoState());
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        motor.setEstado(new DisponibleState());
    }
    
    @Override
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el enfriamiento: el motor está realizando un salto.");
    }
    */
}
