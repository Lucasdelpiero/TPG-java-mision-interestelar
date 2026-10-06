/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Vulcano extends PlanetaOrigen {
    
    public Vulcano(Tripulante tripulante) {
        super(tripulante);
    }
    
    @Override
    public double sueldo() {
        return super.tripulante.sueldo() + 30;
    }
    @Override
    public String detalleHaber() {
        return this.tripulante.detalleHaber()
         + "\n Subsidio 30\n";
    }
    
    public String toString() {
        return "Origen : Vulcano " + super.toString();
    }
    
    
    
}