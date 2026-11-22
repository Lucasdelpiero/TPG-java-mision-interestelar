package modelo.nave;

import java.util.ArrayList;
import modelo.tripulacion.*;
import modelo.motorwarp.*;
import modelo.universo.Asteroide;
import modelo.universo.ElementoDelUniverso;
import modelo.universo.EstacionDeCarga;

public abstract class Nave implements ElementoDelUniverso {
    
    protected Recursos recursos;
    protected MotorWarp motorwarp;
    protected Tripulacion tripulacion;
    protected Componentes componentes;
    protected int id;
    protected static int idSiguiente = 1;
    
    //-------------------------------------------
    //          CONSTRUCTOR/VALIDADOR
    //-------------------------------------------
    
    /**
     * PRE: combustible, energia y desgaste:  x >= 0 y con x <= 100 <br>
     * POST: se devuelve una nave
     * @param combustible combustible de la nave
     * @param energia energia de la nave
     * @param desgaste desgaste de la nave
     */
    //Creacion de nave proviene de NAVE-FACTORY
    protected Nave(int combustible, int energia, int desgaste){
        assert(combustible >= 0 && combustible <= 100): "Error: combustible no valido";
        assert(energia >= 0 && energia <= 100): "Error: energia no valida";
        assert(desgaste >= 0 && desgaste <= 100): "Error: desgaste no valido";
        
        tripulacion = new Tripulacion();
        recursos = new Recursos(combustible, energia, desgaste);
        this.id = idSiguiente++;
        this.motorwarp = new MotorWarp();
    }


    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------    
    
    /**
     * <b>PRE</b>
     * - elemento no nulo.
     * <b>POST</b>
     * - Resuelve el encuentro interactuando con nave, asteroide o estación según corresponda.
     */
    @Override
    public void choque(ElementoDelUniverso elemento){
        assert elemento != null : "El elemento del choque no puede ser nulo";
        if (elemento instanceof Nave nave){
            this.choqueNave(nave);
        } else if (elemento instanceof Asteroide asteroide){
            this.chocaAsteroide(asteroide);
        } else if (elemento instanceof EstacionDeCarga estacion){
            this.chocaEstacionDeCarga(estacion);
        }
    }
    
    /**
     * <b>PRE</b>
     * - asteroide no nulo.
     * <b>POST</b>
     * - Descuenta energía por impacto o destruye la nave si la energía es insuficiente.
     */
    public void chocaAsteroide(Asteroide asteroide){
        assert asteroide != null : "El asteroide no puede ser nulo";
        int costo = asteroide.getCostoChoqueEnergia();
        if (this.getRecursos().getEnergia() >= costo){
                this.getRecursos().consumeEnergia(costo);
            } else {
            this.destruirNave();
        }
    }
    
    /**
     * <b>PRE</b>
     * - estacion no nula.
     * <b>POST</b>
     * - Recarga combustible y energía según los suministros que ofrece la estación.
     */
    public void chocaEstacionDeCarga(EstacionDeCarga estacion){
        assert estacion != null : "La estación de carga no puede ser nula";
        int combustible = Math.min(100 - this.getRecursos().getCombustible(), estacion.getRecargaCombustible());
        int energia = Math.min(100 - this.getRecursos().getEnergia(), estacion.getRecargaEnergia());
        if (combustible > 0) {
            this.getRecursos().cargaCombustible(combustible);
        }
        if (energia > 0) {
            this.getRecursos().cargaEnergia(energia);
        }
    }
    
    
    public abstract void choqueNave(Nave nave);
    public abstract void chocaCarguera(Nave nave);
    public abstract void chocaExploradora(Nave nave);
    public abstract void chocaCombate(Nave nave);
    
    
    // Al perder la pelea con una nave, se destruye y pierde todo
    /**
     * <b>POST</b>
     * - Vacía la tripulación y fija el desgaste de la nave en 100.
     */
    public void destruirNave(){
        tripulacion.matarTripulacion();
        recursos.setDesgaste(100);
    }
    
    /**
     * <b>POST</b>
     * - Incorpora el tripulante a la nave o lanza excepción si no cumple con las restricciones operativas.
     */
    public void addTripulante(Haber t){
        if (t == null) {
            throw new IllegalArgumentException("El tripulante no puede ser nulo.");
        }
        if (this.estaInservible()) {
            throw new IllegalStateException("No se pueden agregar tripulantes a una nave inservible.");
        }
        if (tripulacion.getTripulantes().size() >= 5) {
            throw new IllegalStateException("La tripulación ya está completa (máximo 5 tripulantes permitidos).");
        }
        if (t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan") 
                && !tripulacion.getTripulantesCargo("Capitan").isEmpty()) {
            throw new IllegalStateException("La nave ya posee un Capitán. Solo se permite un Capitán por tripulación.");
        }
        if (tripulacion.getTripulante(t.getTripulante().getId()) != null) {
            throw new IllegalArgumentException("Ya existe un tripulante con el ID [" + t.getTripulante().getId() + "] en esta tripulación.");
        }
        tripulacion.addTripulante(t);
    }
    
    //public void removeTripulante(Tripulante t){
    //    
    //}
    
    
    
    //-------------------------------------------
    //          GETTERS / SETTERS
    //-------------------------------------------

    public Recursos getRecursos() {
        return recursos;
    }

    public MotorWarp getMotor() {
        return motorwarp;
    }
    
    public Tripulacion getTripulacion(){
        return tripulacion;
    }
    
    public ArrayList<Haber> getTripulantes() {
        return tripulacion.getTripulantes();
    }

    public Componentes getComponentes() {
        return componentes;
    }

    public int getId() {
        return id;
    }
    
    /**
     * <b>POST</b>
     * - Retorna true si la tripulación es operativa y la nave no está inservible.
     */
    public boolean estaOperativa() { 
        return getTripulacion().esOperativa() && !estaInservible();
    }

    /**
     * <b>POST</b>
     * - Retorna true si el desgaste es >= 100, combustible <= 0 o energía <= 0.
     */
    public boolean estaInservible() {
        return getRecursos().getDesgaste() >= 100 
                || getRecursos().getCombustible() <= 0 
                || getRecursos().getEnergia() <= 0;
    }
    
    //---------------------------------------------
    
    
    public void setRecursos(Recursos recursos) {
        this.recursos = recursos;
    }

    public void setMotor(MotorWarp motor) {
        this.motorwarp = motor;
    }

    public void setTripulacion(Tripulacion tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void setComponentes(Componentes componentes) {
        this.componentes = componentes;
    }
    
}
