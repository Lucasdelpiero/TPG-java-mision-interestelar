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
    
    public Capitan() {
    } 
    
    @Override
    public int sueldoBase() {
        return 1000;
    }
    
    @Override 
    public double adicionalCargo() {
        return 0.2;
    }
    @Override
    public String getCargoTripulante() {
        return "Capitan";
    }
    
}
