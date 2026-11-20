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
    
    @Override
    public void choque(ElementoDelUniverso elemento){
        if (elemento instanceof Nave nave){
            this.choqueNave(nave);
        } else if (elemento instanceof Asteroide asteroide){
            this.chocaAsteroide(asteroide);
        } else if (elemento instanceof EstacionDeCarga estacion){
            this.chocaEstacionDeCarga(estacion);
        }
    }
    
    /**
     * Pre: asteroide no es null
     * Post: la nave reduce su cantidad de energia o es destruida
     * @param asteroide 
     */
    public void chocaAsteroide(Asteroide asteroide){
        int costo = asteroide.getCostoChoqueEnergia();
        if (this.getRecursos().getEnergia() >= costo){
                this.getRecursos().consumeEnergia(costo);
            } else {
            this.destruirNave();
        }
    }
    
    /**
     * Pre: la estacion no es null
     * Post: a la nave se le carga como maximo la cantidad de recuross que estipula la estacion
     * @param estacion 
     */
    public void chocaEstacionDeCarga(EstacionDeCarga estacion){
        int combustible = estacion.getRecargaCombustible();
        int energia = estacion.getRecargaEnergia();
        this.getRecursos().cargaCombustible(combustible);
        this.getRecursos().cargaEnergia(energia);
    }
    
    
    public abstract void choqueNave(Nave nave);
    public abstract void chocaCarguera(Nave nave);
    public abstract void chocaExploradora(Nave nave);
    public abstract void chocaCombate(Nave nave);
    
    
    // Al perder la pelea con una nave, se destruye y pierde todo
    /**
     * PRE: tripulacion y recursos != null
     * POST: tripulacion es una lista vacia y desgaste al maximo
     * 
    */
    public void destruirNave(){
        tripulacion.matarTripulacion();
        recursos.setDesgaste(100);
    }
    
    /**
     * PRE: tripulante t != null<br>
     * POST: se agrega tripulante a la tripulacion
     * @param t es el tripulante que se quiere añadir a la tripulacion 
     */
    public void addTripulante(Haber t){
        assert(t != null): "Error: tripulando es null";
        assert(!t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan") || tripulacion.getTripulantesCargo("Capitan").isEmpty()): "Error : Ya hay un capitan";
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
     * Chequea si la nave es funcoinal o no, viendo que tenga la tripulacion adecuada y el desgaste no sea maximo
     * pre: la nave tiene un objeto tripulacion y recursos a los cuales consultar
     * post: devuelve true o false dependiendo de los componentes de nave de la nave
     * @return 
     */
    public boolean estaOperativa() { 
        return getTripulacion().esOperativa() && getRecursos().getDesgaste() < 100;
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
