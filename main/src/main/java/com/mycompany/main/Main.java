/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import modelo.asistente.AsistenteComando;
import modelo.nave.*;
import modelo.mision.*;

public class Main {

    public static void main(String[] args) {
       AsistenteComando asistente = new AsistenteComando();
       Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
       asistente.setNave(nave);
       Mision mision = new MisionIntercepcionAsistencia();
       
       asistente.iniciaMision(mision);
       
       mision = new MisionRecoleccion();
       asistente.iniciaMision(mision);
       
       mision = new MisionRetornoSeguro();
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       asistente.iniciaMision(mision);
       
    }
}
