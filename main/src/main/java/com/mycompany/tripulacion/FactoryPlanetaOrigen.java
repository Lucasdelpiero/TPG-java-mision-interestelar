/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

/**
 *
 * @author ASUS
 */
public class FactoryPlanetaOrigen {
    
    public PlanetaOrigen factoryPlanetaOrigen(String origen , Tripulante tripulante) {
        if (origen.equals("Vulcano"))
            return new Vulcano(tripulante);
        else
            if (origen.equals("Terricola"))
                return new Terricola(tripulante);
            else
                if (origen.equals("Marciano"))
                    return new Marciano(tripulante);
        throw new IllegalArgumentException("Origen invalido");
    }
}
