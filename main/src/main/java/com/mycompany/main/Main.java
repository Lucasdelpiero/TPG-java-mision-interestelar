/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import modelo.asistente.AsistenteComando;
import modelo.nave.*;
import modelo.mision.*;
import modelo.bitacora.*;
import modelo.universo.CentroDeControl;
import modelo.tripulacion.*;

public class Main {

    public static void main(String[] args) {

        CentroDeControl GGCC = new CentroDeControl();
        
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        AsistenteComando AC1 = new AsistenteComando(nave, "JARVIS");
        
        FactoryPlanetaOrigen factoryOrigen = new FactoryPlanetaOrigen();
        FactoryCargo factoryCargo = new FactoryCargo();
        
        Cargo c1 = factoryCargo.crearCargo("Consejero");
        
        Tripulante tripulante = new Tripulante("Jorge", c1, 13);
        PlanetaOrigen oUno = factoryOrigen.factoryPlanetaOrigen("Vulcano", tripulante);
        
        nave.addTripulante(oUno);
        
        System.out.println(oUno.sueldo());
        System.out.println(oUno.detalleHaber());
        
        AsistenteComando AC2 = null;
        
        GGCC.setAC(AC1);
        GGCC.setAC(AC2);
        
        //asistente.setNave(nave);
        
        GGCC.setNave(nave, "JARVIS");
        GGCC.setNave(nave, "JAVIER");
        
        Mision mision = new MisionIntercepcionAsistencia();
        
        AC1.iniciaMision(mision);
        AC1.motor_secuencia_salto();
        //AC1.motor_secuencia_salto();
       
    }
}
