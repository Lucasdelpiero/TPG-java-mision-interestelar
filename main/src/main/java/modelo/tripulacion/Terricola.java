/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Terricola extends PlanetaOrigen {
    
    public Terricola(Tripulante tripulante) {
        super(tripulante);
    }
    
    @Override
    public double sueldo() {
        return super.tripulante.sueldo() + 20;
    }
    @Override
    public String detalleHaber() {
        return this.tripulante.detalleHaber()
         + "\n Subsidio 20\n";
    }
    
    
    @Override
    public String toString() {
        return "Origen : Terricola " + super.toString();
    }
}