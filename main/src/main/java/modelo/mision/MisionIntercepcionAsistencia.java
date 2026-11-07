package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;
import modelo.destinoytablero.Posicion;
import modelo.universo.Universo;
import modelo.universo.ElementoDelUniverso;
import modelo.nave.Nave;
import java.util.Random;

// Mision M-01
public class MisionIntercepcionAsistencia extends Mision{
    private Random random = new Random();
    private int energiaRequerida , combustibleRequerido , energiaDisponible , combustibleDisponible;
    
    public MisionIntercepcionAsistencia(Universo universo) {
        super(universo);
        nombreMision = "M-01";
        descripcion = "Mision: INTERCEPCION Y ASISTENCIA";
        consumoEnergia = 5;
        tipo = Mision.tipoMision.INTERCEPCION;
    }
    
    
    
    @Override
    public void ejecutar(){
        if (puedeIniciarMision){
            System.out.println("[MISION] Ejecutando M-01: ASISTENCIA");
            AC.registrarMision(TipoEvento.MISION, "[MISION] Ejecutando M-01: ASISTENCIA", idMision);
            
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
                
                Nave nave = this.AC.getNave();
                energiaDisponible = nave.getRecursos().getEnergia();
                combustibleDisponible = nave.getRecursos().getCombustible();
                 energiaRequerida = random.nextInt(51); 
                 combustibleRequerido = random.nextInt(51); 
                if (energiaDisponible <= energiaRequerida && combustibleDisponible <= combustibleRequerido) {
                    this.AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] IMPOSIBLE DE COMPLETAR , RECURSOS INSUFICIENTES");
                    informe.resultadoExitoso = false;
                }
                else
                    informe.resultadoExitoso = resultado;
                
            }catch(InterruptedException e) {
                informe.resultadoExitoso = false;
            }
            //Si se gasta algun recurso de por medio, entonces:
            //  informe.set[recurso](valor que se consume)
        
        } else {
            System.out.println("[MISION] ABORTANDO M-01: ASISTENCIA");
            AC.registrarMision(TipoEvento.MISION, "[MISION] ABORTANDO M-01: ASISTENCIA", idMision);
            informe.resultadoExitoso = false;
        }
        
        //Si ocurre algo ... 
        //  misionCumplida = false (queda como esta);
        //Si se cumple la mision normalmente, entonces...
    }
    
    @Override
    protected void cerrar() {
        if (informe.resultadoExitoso) {
            Nave nave = this.AC.getNave();
            if (energiaDisponible > energiaRequerida)
                nave.getRecursos().setEnergia(energiaDisponible - energiaRequerida);
            
            if (combustibleDisponible > combustibleRequerido)
                nave.getRecursos().setCombustible(combustibleDisponible - combustibleRequerido);
        }
         
         super.cerrar();
     }
    
    public Posicion obtenerPosicionObjetivo() {
        ElementoDelUniverso[][] tablero = universo.verTablero();
        Posicion nave = universo.getPosicionNave();

        int n = universo.getX();
        int m = universo.getY();

        int x0 = nave.getX();
        int y0 = nave.getY();

        int maxRadio = Math.max(n, m);

        for (int radio = 0; radio <= maxRadio; radio++) {

            int filaMin = x0 - radio;
            int filaMax = x0 + radio;
            int colMin = y0 - radio;
            int colMax = y0 + radio;

            // borde superior e inferior
            for (int j = colMin; j <= colMax; j++) {

                if (filaMin >= 0 && filaMin < n &&
                    j >= 0 && j < m &&
                    tablero[filaMin][j] instanceof Nave) {

                    return new Posicion(filaMin, j);
                }

                if (filaMax >= 0 && filaMax < n &&
                    j >= 0 && j < m &&
                    tablero[filaMax][j] instanceof Nave) {

                    return new Posicion(filaMax, j);
                }
            }

            // borde izquierdo y derecho
            for (int i = filaMin + 1; i < filaMax; i++) {

                if (i >= 0 && i < n &&
                    colMin >= 0 && colMin < m &&
                    tablero[i][colMin] instanceof Nave) {

                    return new Posicion(i, colMin);
                }

                if (i >= 0 && i < n &&
                    colMax >= 0 && colMax < m &&
                    tablero[i][colMax] instanceof Nave) {

                    return new Posicion(i, colMax);
                }
            }
        }

        return null;
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