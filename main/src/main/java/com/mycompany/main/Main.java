/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import modelo.asistente.AsistenteComando;
import modelo.nave.*;
import modelo.mision.*;
import modelo.bitacora.*;
import modelo.universo.CentroDeControl;

public class Main {

    public static void main(String[] args) {
        CentroDeControl GGCC = new CentroDeControl();
        
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        Bitacora bitacora = new Bitacora();
        AsistenteComando AC1 = new AsistenteComando("JARVIS", bitacora);
        
        AsistenteComando AC2 = null;
        
        GGCC.setAC(AC1);
        GGCC.setAC(AC2);
        
        //asistente.setNave(nave);
        
        GGCC.setNave(nave, "JARVIS");
        GGCC.setNave(nave, "JAVIER");
        
        Mision mision = new MisionIntercepcionAsistencia();
       
       
    }
}
