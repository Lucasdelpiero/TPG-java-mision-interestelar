/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Capitan extends Cargo {
    
    private int sueldo;
    private double adicional;
    
    public Capitan() {
        this.sueldo = 1000;
        this.adicional = 0.2;
    } 
    
    @Override
    public int sueldoBase() {
        return this.sueldo;
    }
    
    @Override 
    public double adicionalCargo() {
        return this.adicional;
    }
    @Override
    public String getCargoTripulante() {
        return "Capitan";
    }
    
}
