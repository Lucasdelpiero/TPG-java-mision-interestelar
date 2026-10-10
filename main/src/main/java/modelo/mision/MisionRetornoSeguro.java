package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

// Mision M-03
public class MisionRetornoSeguro extends Mision{

    public MisionRetornoSeguro() {
        tipo = Mision.tipoMision.RETORNO_SEGURO;
    }
    
    @Override
    public void ejecutar(){  
       if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-03: RETORNO SEGURO");
            AC.restaCombustible(consumoCombustible);
            AC.restaEnergia(consumoEnergia);
            AC.sumaDesgaste(consumoDesgaste);
            informe.resultadoExitoso = true;
            //Si se gasta algun recurso de por medio, entonces:
            //  informe.set[recurso](valor que se consume)
        
        } else {
            System.out.println("[MISION] ABORTANDO M-03: RETORNO SEGURO");
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

/* ------------------ lucas part ------------------
public class MisionIntercepcionAsistencia extends Mision{
  
    @Override
    public void ejecutar(AsistenteComando AC){
        System.out.println("* EJECUTA MISION M-1 *");
    }
    
    @Override
    public void cerrar(AsistenteComando AC){
        AC.getRecursos().cargaEnergia(5);
        System.out.println("Se añaden 5 de energia adicional");
    }
    
    public MisionIntercepcionAsistencia(AsistenteComando AC) {
        super(AC);
    }
    
}
*/