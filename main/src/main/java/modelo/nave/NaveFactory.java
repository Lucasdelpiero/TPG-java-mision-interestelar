package modelo.nave;

/**
 * Permite crear naves con metodos y atributos estaticos, sin instanciarla   
 * 
 */
public class NaveFactory {
    
    public static enum tipoNave {
        EXPLORADORA,
        CARGUERA,
        COMBATE
    }
   
    /**
     * Construye una nave segun el tipo que le hayan pedido<br>
     * PRE: tipo es uno de los valores validos en el enum tipoNave<br>
     * POST: se retorna una nueva nave del tipo pedido<br>
     * @param tipo se invoca con "NaveFactory.tipoNave.[TIPO]"
     * @return devuelve una nave del tipo pedido
     */
    public static Nave getNave(tipoNave tipo){
        if (tipo == tipoNave.EXPLORADORA){
            return new Exploradora();
        } else 
            if (tipo == tipoNave.CARGUERA){
                return new Carguero();
        } else 
            if (tipo == tipoNave.COMBATE){
                return new Combate();
        } else {
            System.out.println("Llamada con un tipo incorrecto");
            return null;
        }
    }
}
