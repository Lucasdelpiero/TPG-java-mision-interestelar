package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

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
    public void saltar(MotorWarp motor){
        AsistenteComando ac = motor.getAC();
        if (ac != null && ac.getRecursos() != null) {
            Recursos recursos = ac.getRecursos();
            if (recursos.getCombustible() >= 2)
                recursos.consumeCombustible(2);
        }
    }
    
    @Override
    public void saltoWarp(MotorWarp motor){
        motor.setEstado(new PreparandoSaltoState(1));
    }
    
    /*
    @Override
    public void prepararSalto(MotorWarp motor) throws MotorTransicionException{
        System.out.println("Motor warp: preparando salto");
        motor.setEstado(new PreparandoSaltoState());
    }
    

    @Override
    public void saltar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede saltar: primero debe preparar el motor.");
    }
    
    @Override
    public void enfriar(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede enfriar: el motor ya está disponible.");
    }
    
    @Override
    public void terminarSalto(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el salto: no hay un salto activo.");
    }
    
    public void terminarEnfriamiento(MotorWarp motor) throws MotorTransicionException{
        throw new MotorTransicionException("No se puede terminar el enfriamiento: el motor no está enfriándose.");

    }
    */
    
}
