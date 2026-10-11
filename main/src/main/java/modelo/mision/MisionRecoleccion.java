package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

// Mision M-02
public class MisionRecoleccion extends Mision{
    
    public MisionRecoleccion() {
        id = idSiguiente++;
        nombreMision = "M-02";
        descripcion = "Mision: RECOLECCION";
        consumoEnergia = 5;

        tipo = Mision.tipoMision.RECOLECCION;
    }
    
    
    
    @Override
    public void ejecutar(){
        if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-02: RECOLECCION");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Ejecutando M-02: RECOLECCION", id);
            
            AC.restaCombustible(consumoCombustible);
            String descConsumo = "Consume %d de combustible";
            descConsumo = String.format(descConsumo, consumoCombustible);
            AC.getBitacora().registrar(TipoEvento.RECURSOS, descConsumo, id);
            
            AC.restaEnergia(consumoEnergia);
            descConsumo = "Consume %d de energia";
            descConsumo = String.format(descConsumo, consumoCombustible);
            AC.getBitacora().registrar(TipoEvento.RECURSOS, descConsumo, id);
            
            AC.sumaDesgaste(consumoDesgaste);
            descConsumo = "Consume %d de desgaste";
            descConsumo = String.format(descConsumo, consumoCombustible);
            AC.getBitacora().registrar(TipoEvento.RECURSOS, descConsumo, id);
            
            informe.resultadoExitoso = true;
            //Si se gasta algun recurso de por medio, entonces:
            //  informe.set[recurso](valor que se consume)
        
        } else {
            System.out.println("[MISION] ABORTANDO M-02: RECOLECCION");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] ABORTANDO M-02: RECOLECCION", id);
            informe.resultadoExitoso = false;
        }
        //informe = new InformeMision(idMision);
        //Si se gasta algun recurso de por medio, entonces:
        //  informe.set[recurso](valor que se consume)
        
        //Si ocurre algo ... 
        //  misionCumplida = false (queda como esta);
        //Si se cumple la mision normalmente, entonces...
        misionCumplida = true;
    }
 
        // (Dependiendo de la ENTREGA 2, la mision ejecutada
        // puede ser un exito o podria fallar)
    

}
