/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tripulacion;
/**
 *
 * @author ASUS
 */
public class FactoryCargo {
    public Cargo crearCargo(String cargo) {
        if (cargo.equalsIgnoreCase("Capitan"))
            return new Capitan();
        else
            if (cargo.equalsIgnoreCase("Teniente"))
                return new Teniente();
            else
                if (cargo.equalsIgnoreCase("Alferez"))
                    return new Alferez();
                else
                    if (cargo.equalsIgnoreCase("Consejero"))
                        return new Consejero();
                    else
                        throw new IllegalArgumentException("Cargo invalida");
    }    
}
