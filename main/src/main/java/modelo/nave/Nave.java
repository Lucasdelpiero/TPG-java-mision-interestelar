package modelo.nave;

import java.util.ArrayList;
import modelo.tripulacion.*;
import modelo.motorwarp.*;

public abstract class Nave {
    
    protected Recursos recursos;
    protected MotorWarp motorwarp;
    protected ArrayList<Tripulante>tripulacion;
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
        
        tripulacion = new ArrayList<>();
        recursos = new Recursos(combustible, energia, desgaste);
        this.id = idSiguiente++;
        this.motorwarp = new MotorWarp();
    }


    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------    
    
    /**
     * PRE: tripulante t != null<br>
     * POST: se agrega tripulante a la tripulacion
     * @param t es el tripulante que se quiere añadir a la tripulacion 
     */
    public void addTripulante(Tripulante t){
        assert(t != null): "Error: tripulando es null";
        
        tripulacion.add(t);
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

    public ArrayList<Tripulante> getTripulacion() {
        return tripulacion;
    }

    public Componentes getComponentes() {
        return componentes;
    }

    public int getId() {
        return id;
    }
    
    
    //---------------------------------------------
    
    
    public void setRecursos(Recursos recursos) {
        this.recursos = recursos;
    }

    public void setMotor(MotorWarp motor) {
        this.motorwarp = motor;
    }

    public void setTripulacion(ArrayList<Tripulante> tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void setComponentes(Componentes componentes) {
        this.componentes = componentes;
    }
    
}
