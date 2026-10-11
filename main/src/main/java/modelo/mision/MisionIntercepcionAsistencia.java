package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

// Mision M-01
public class MisionIntercepcionAsistencia extends Mision{

    public MisionIntercepcionAsistencia() {
        id = idSiguiente++;
        nombreMision = "M-01";
        descripcion = "Mision: INTERCEPCION Y ASISTENCIA";
        int consumoEnergia = 5;
        tipo = Mision.tipoMision.INTERCEPCION;
    }
    
    
    
    @Override
    public void ejecutar(){
        if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-01: ASISTENCIA");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Ejecutando M-01: ASISTENCIA", id);
            
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
            System.out.println("[MISION] ABORTANDO M-01: ASISTENCIA");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] ABORTANDO M-01: ASISTENCIA", id);
            informe.resultadoExitoso = false;
        }
        
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