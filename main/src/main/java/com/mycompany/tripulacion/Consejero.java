/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Consejero extends Cargo {
    private int consejos;
    
    public Consejero() {
        this.consejos = 0;
    }
    
    public void addConsejo(int consejos) {
        assert consejos > 0 : "Numero de consejos invalido";
        this.consejos += consejos;
    }
    
    public int getConsejos() {
        return this.consejos; 
    }
    
    public void resetConsejos() {
        this.consejos = 0;
    }
    
    @Override
    public int sueldoBase() {
        return 600 ;
    }
    @Override 
    public double adicionalCargo() {
        return 0.05 ;
    }
    @Override 
    public String getCargoTripulante() {
        return "Consejero" ;
    }
    
    public String toString() {
        return "Consejero , Numero de consejos durante el periodo actual :" + getConsejos() ;
    }
}
