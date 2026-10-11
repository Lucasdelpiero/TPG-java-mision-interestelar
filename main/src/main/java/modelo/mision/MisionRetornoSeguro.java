package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

// Mision M-03
public class MisionRetornoSeguro extends Mision{

    public MisionRetornoSeguro() {
        nombreMision = "M-03";
        descripcion = "Mision: RETORNO SEGURO";
        tipo = Mision.tipoMision.RETORNO_SEGURO;
    }
    
    @Override
    public void ejecutar(){  
       if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-03: RETORNO SEGURO");
            AC.registrarMision(TipoEvento.MISION, "[MISION] Ejecutando M-03: RETORNO SEGURO", idMision);

            AC.restaCombustible(consumoCombustible);
            String descConsumo = String.format("Consume %d de combustible", consumoCombustible);
            AC.registrarMision(TipoEvento.RECURSOS, descConsumo, idMision);
            
            AC.restaEnergia(consumoEnergia);
            descConsumo = String.format("Consume %d de energia", consumoEnergia);
            AC.registrarMision(TipoEvento.RECURSOS, descConsumo, idMision);
            
            AC.sumaDesgaste(consumoDesgaste);
            descConsumo = String.format("Consume %d de desgaste", consumoDesgaste);
            AC.registrarMision(TipoEvento.RECURSOS, descConsumo, idMision);
            
            informe.resultadoExitoso = true;
            //Si se gasta algun recurso de por medio, entonces:
            //  informe.set[recurso](valor que se consume)
        
        } else {
            System.out.println("[MISION] ABORTANDO M-03: RETORNO SEGURO");
            AC.registrarMision(TipoEvento.MISION, "[MISION] ABORTANDO M-03: RETORNO SEGURO", idMision);
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
