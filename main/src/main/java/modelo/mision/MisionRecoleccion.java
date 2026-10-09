package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;

// Mision M-01
public class MisionRecoleccion extends Mision{
    private String idMision = "M-02";
    private int consumoEnergia = 5;
    
    @Override
    public void ejecutar(AsistenteComando AC){
        System.out.println("[MISION] Ejecutando M-02: RECOLECCION");
        informe = new InformeMision(idMision);
        //Si se gasta algun recurso de por medio, entonces:
        //  informe.set[recurso](valor que se consume)
        
        //Si ocurre algo ... 
        //  misionCumplida = false (queda como esta);
        //Si se cumple la mision normalmente, entonces...
        misionCumplida = true;
    }
 
        // (Dependiendo de la ENTREGA 2, la mision ejecutada
        // puede ser un exito o podria fallar)
    @Override
    protected void evaluar(AsistenteComando AC){  
        System.out.println("[MISION] Evaluando resultados...");
        
        AC.registrarMision(
            TipoEvento.MISION,
            "Mision: RECOLECCION",         // agregar descripcion sobre la mision realizada
            idMision
        );
        
        //  Para cada recurso:
        //  -   Se resta el recurso (lo que consume la mision) de la nave
        //  -   Se registra la reduccion del recurso en la bitacora
        //  -   Se agrega el consumo (lo que consume la mision) para el informe
        
        AC.restaCombustible(consumoCombustible);
        AC.registrarEvento(
            TipoEvento.RECURSOS,
                "Reduccion de combustible en "+idMision+" : "+"-"+consumoCombustible
        );
        informe.addConsumoCombustible(consumoCombustible);
        
        AC.sumaDesgaste(consumoDesgaste);
        AC.registrarEvento(
            TipoEvento.RECURSOS,
            "Incremento de desgaste en "+idMision+" : "+"+"+consumoDesgaste
        );
        informe.addConsumoDesgaste(consumoDesgaste);
        
        AC.restaEnergia(consumoEnergia);    // !!!  ESTA MISION RESTA 5 puntos de energia
        AC.registrarEvento(                 
            TipoEvento.RECURSOS,
            "Reduccion de energia en "+idMision+" : "+"-"+consumoEnergia
        );
        informe.addConsumoCombustible(consumoEnergia);

        informe.getInforme(
                AC.getNave().getRecursos().getCombustible(),
                AC.getNave().getRecursos().getDesgaste(),
                AC.getNave().getRecursos().getEnergia(),
                misionCumplida
        );
        
        System.out.println("[MISION] Cerrando...");
    }

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