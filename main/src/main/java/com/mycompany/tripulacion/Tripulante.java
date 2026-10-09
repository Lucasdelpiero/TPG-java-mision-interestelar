/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class Tripulante implements Haber {
    private int antiguedad;
    private String identidad;
    private Cargo cargo;
    
    public Tripulante(String identidad , Cargo cargo , int antiguedad) {
        if (identidad == null || identidad.isBlank()) {
            throw new IllegalArgumentException("Identidad invalida");
        }

        if (antiguedad < 0) {
            throw new IllegalArgumentException("Antiguedad invalida");
        }
      
        this.cargo = cargo;
    
        this.identidad = identidad;
        this.antiguedad = antiguedad;
    }
    
    protected void addAntiguedad(int anios) {  
        if (anios <= 0)
            throw new IllegalArgumentException("anios invalidos");
        this.antiguedad += anios; // Sumo los años en servicio cuando se ejecuta la nave
    }
    
    @Override
    public double sueldo() {
        double sueldo = this.cargo.sueldoBase();
        
        double adicional = sueldo * (this.antiguedad * this.cargo.adicionalCargo());
        
        if (this.cargo.getCargoTripulante().equals("Consejero"))
            adicional += ((Consejero) this.cargo).getConsejos() * 2;
        
        return sueldo + adicional;
    }
    @Override
    public String detalleHaber() {
        double base = cargo.sueldoBase();
        double adicionalAntiguedad = base * cargo.adicionalCargo() * antiguedad;
        if (this.cargo.getCargoTripulante().equals("Consejero")) {
            double adicionalConsejos = ((Consejero) this.cargo).getConsejos() * 2;
            return "Sueldo base: " + base
                + "\nAdicional por antiguedad: " + adicionalAntiguedad 
                + "\nAdicional por numero de consejos " + adicionalConsejos + "\n";
        }
        else
            return "Sueldo base: " + base
                + "\nAdicional por antiguedad: " + adicionalAntiguedad ;
    }
    
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public int getAntiguedad() {
        return this.antiguedad;
    }
    
    public String getId() {
        return this.identidad;
    }
    
    public String toString() {
        
        return "Cargo :" + this.cargo.toString() + " Identidad :" + this.identidad + 
                " anios en servicio :" + this.antiguedad ;
    }
    
}
