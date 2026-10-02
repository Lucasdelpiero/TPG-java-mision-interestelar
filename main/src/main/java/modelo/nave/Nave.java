package modelo.nave;

import java.util.ArrayList;
import modelo.tripulacion.*;
import modelo.motorwarp.*;

public abstract class Nave {
    
    protected Recursos recursos;
    protected MotorWarp motor;
    protected ArrayList<Tripulante>tripulacion;
    protected Componentes componentes;
    protected String id;
    
    //-------------------------------------------
    //          CONSTRUCTOR/VALIDADOR
    //-------------------------------------------
    
    //Creacion de nave proviene de NAVE-FACTORY
    public Nave(String id, MotorWarp motorwarp, int combustible, int energia, int desgaste){
        
        validacion(id, motorwarp);
        
        tripulacion = new ArrayList<>();
        recursos = new Recursos(combustible, energia, desgaste);
        this.id = id;
        this.motor = motorwarp;
    }
    
    private void validacion(String id, MotorWarp motorwarp){
        if(id == null || id == "" || motorwarp == null){
            throw new IllegalArgumentException("ERROR: Campos invalidos (id/MotorWarp)");
        }
    }


    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------    
    
    
    public void addTripulante(Tripulante t){
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
        return motor;
    }

    public ArrayList<Tripulante> getTripulacion() {
        return tripulacion;
    }

    public Componentes getComponentes() {
        return componentes;
    }

    public String getId() {
        return id;
    }
    
    
    //---------------------------------------------
    
    
    public void setRecursos(Recursos recursos) {
        this.recursos = recursos;
    }

    public void setMotor(MotorWarp motor) {
        this.motor = motor;
    }

    public void setTripulacion(ArrayList<Tripulante> tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void setComponentes(Componentes componentes) {
        this.componentes = componentes;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
