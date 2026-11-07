package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;
import modelo.universo.Universo;
import modelo.destinoytablero.Posicion;
import modelo.nave.Nave;
import java.util.Random;
import modelo.universo.ElementoDelUniverso;

// Mision M-02
public class MisionRecoleccion extends Mision{
    private final Random random = new Random();
    
    public MisionRecoleccion(Universo universo) {
        super(universo);
        nombreMision = "M-02";
        descripcion = "Mision: RECOLECCION";
        consumoEnergia = 10;

        tipo = Mision.tipoMision.RECOLECCION;
    }
    
    public Posicion obtenerPosicionObjetivo() {
        return universo.getObjetivo();
    }
    
    @Override
    public void ejecutar(){
        if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-02: RECOLECCION");
            AC.registrarMision(TipoEvento.MISION, "[MISION] Ejecutando M-02: RECOLECCION", idMision);
            
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
            System.out.println("[MISION] ABORTANDO M-02: RECOLECCION");
            AC.registrarMision(TipoEvento.MISION, "[MISION] ABORTANDO M-02: RECOLECCION", idMision);
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
            int valor = random.nextInt(81) + 20;

            nave.getRecursos().cargaGemas(valor);
        }

         super.cerrar();
     }
 
        // (Dependiendo de la ENTREGA 2, la mision ejecutada
        // puede ser un exito o podria fallar)
    

}
