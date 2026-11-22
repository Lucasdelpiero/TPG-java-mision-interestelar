package modelo.motorwarp;

import modelo.asistente.AsistenteComando;
import modelo.nave.Recursos;

public class PreparandoSaltoState implements EstadoMotor {
    private int turnoPreparacion;
    
    public PreparandoSaltoState(){
        this(1);
    }

    public PreparandoSaltoState(int turno){
        this.turnoPreparacion = turno;
    }
    
    @Override
    public String getNombre(){
        return "Preparando Salto";
    }
    
    @Override
    public boolean estaDisponible(){
        return false;
    }

    public int getTurnoPreparacion() {
        return turnoPreparacion;
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
    }
    
    @Override
    public void saltoWarp(MotorWarp motor){
        assert motor != null : "El motor no puede ser nulo";
        AsistenteComando ac = motor.getAC();
        if (ac != null && ac.getRecursos() != null) {
            Recursos recursos = ac.getRecursos();
            if (recursos.getEnergia() < 5) {
                // Si no dispone de 5 de energia, la maniobra no continua y se queda en modo preparacion
                return;
            }
            motor.setEstado(new SaltoWarpState());
        }
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
