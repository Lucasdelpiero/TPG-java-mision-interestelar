/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public abstract class PlanetaOrigen implements Haber {
    
    protected Tripulante tripulante;
    
    public PlanetaOrigen(Tripulante tripulante) {
        this.tripulante = tripulante;
    }
    
    public Tripulante getTripulante() {
        return this.tripulante;
    }
    public String toString() {
        return this.tripulante.toString();
    }

}
