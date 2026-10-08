package modelo.nave;

import modelo.motorwarp.*;
/**
 *   
 *  PRE:
 *      'id' es un string correcto.
 *      'MotorWarp' ??
 * 
 *  POST: Devuelve un objeto tipo Nave
 */

public class NaveFactory {
    
    public Nave getNave(String tipo, String id, MotorWarp motorwarp){
        if(tipo == null){
            return null;
        }
        if(tipo.equalsIgnoreCase("Exploradora")){
            //  Combustible = 60;
            //  Energia = 80;
            //  Desgaste = 0;           
            return new Exploradora(id, motorwarp, 60, 80, 0);
        }
        else if(tipo.equalsIgnoreCase("Carguero")){
            //  Combustible = 100;
            //  Energia = 60;
            //  Desgaste = 0;
            return new Carguero(id, motorwarp, 100, 60, 0);
        }
        else if(tipo.equalsIgnoreCase("Combate")){
            //  Combustible = 80;
            //  Energia = 100;
            //  Desgaste = 0;
            return new Combate(id, motorwarp, 80, 100, 0);
        }
        return null;
    }
}
