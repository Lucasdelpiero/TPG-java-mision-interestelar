/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tripulacion;
/**
 *
 * @author ASUS
 */
public class Alferez extends Cargo {
    
    public Alferez() {
    }
    
    @Override
    public int sueldoBase() {
        return 200;
    }
    @Override 
    public double adicionalCargo() {
        return 0.005 ;
    }
    @Override
    public String getCargoTripulante() {
        return "Alferez";
    }
}
