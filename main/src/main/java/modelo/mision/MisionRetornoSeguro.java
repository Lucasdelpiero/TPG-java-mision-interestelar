package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;
import modelo.universo.Universo;
import modelo.universo.ElementoDelUniverso;
import modelo.universo.EstacionDeCarga;
import modelo.destinoytablero.Posicion;
import modelo.nave.Nave;

// Mision M-03
public class MisionRetornoSeguro extends Mision{
    public MisionRetornoSeguro(Universo universo) {
        super(universo);
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
            try {
                boolean resultado = realizarRecorrido(this.destino);
                informe.resultadoExitoso = resultado;
                
            }catch(InterruptedException e) {
                informe.resultadoExitoso = false;
            }
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
    }
    
    @Override
     protected void cerrar() {
         if (informe.resultadoExitoso) {
            Nave nave = this.AC.getNave();
            nave.getRecursos().setCombustible(100);
            nave.getRecursos().setEnergia(100);
            nave.getRecursos().realizaMantenimiento();
         }
         super.cerrar();
     }
    
    
    public Posicion obtenerPosicionObjetivo() {

        int n = universo.getX();
        int m = universo.getY();
        
        n = (int) n / 2;
        m = (int) m / 2;

        return new Posicion(n , m);
    }
 
        // (Dependiendo de la ENTREGA 2, la mision ejecutada
        // puede ser un exito o podria fallar)
    

}
