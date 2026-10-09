/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Teniente extends Cargo {
    
    public Teniente() {
    }
 
    @Override
    public int sueldoBase() {
        return 400;
    }
    @Override 
    public double adicionalCargo() {
        return 0.03 ;
    }
    @Override
    public String getCargoTripulante() {
        return "Teniente";
    }
}
