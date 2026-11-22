package modelo.universo;
import java.util.ArrayList;
import modelo.nave.Nave;
import modelo.destinoytablero.*;
import java.util.ArrayList;

public class Universo extends Observable  {
    private final Nave nave;
    private final Tablero tablero;
    private final Destino destino;
    
    public Universo(Nave nave , int filas , int columnas, ArrayList<ElementoDelUniverso> elementos) {
        if (nave == null || elementos == null)
            throw new IllegalArgumentException("La nave y la lista de elementos no pueden ser nulas");

        this.nave = nave;
        
        try {
            this.tablero = new Tablero(filas, columnas, elementos);
        }catch(DimensionesDeTableroInconsistentesException e) {
            throw new IllegalArgumentException(e.getMessage());
        }catch(CantidadDeElementosSuperaCasillaException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        
        this.destino = new Destino(this.tablero);
    }

    public Nave getNave() {
        return nave;
    }

    public Posicion getPosicionNave() {
        return tablero.getPosicionNave();
    }
    
    public Posicion getObjetivo() {
        return tablero.getObjetivo();
    }
    
    public Posicion obtenerObjetivo(){
        return tablero.obtenerObjetivo();
    }
    
    /**
     * <b>PRE</b>
     * - posicion no nula y dentro de los límites del tablero.
     * <b>POST</b>
     * - Actualiza la posición de la nave y notifica a los observadores.
     */
    public void moverNave(Posicion posicion) {
        assert posicion != null : "La posición no puede ser nula";
        assert posicion.getX() >= 0 && posicion.getX() < getX() && posicion.getY() >= 0 && posicion.getY() < getY() : "La posición se encuentra fuera de los límites del tablero";
        tablero.moverNave(posicion);
    }
    
    /**
     * <b>POST</b>
     * - Retorna el elemento en la casilla actual de la nave o null si está libre.
     */
    public ElementoDelUniverso inspeccionarPosicion() {
        return tablero.inspeccionarPosicion();
    }
    
    /**
     * <b>POST</b>
     * - Retorna matriz 3x3 centrada en la nave con los elementos del vecindario inmediato.
     */
    public ElementoDelUniverso[][] observarAlrededores() {
        return tablero.observarAlrededores();
    }
    
    /**
     * <b>POST</b>
     * - Ejecuta movimiento aleatorio de elementos y notifica a los observadores.
     */
    public void pasarTurnoDestino() {
        destino.turnoDestino();
        notificarObservadores();
    }
        
    public ElementoDelUniverso[][] verTablero() {
        return tablero.verTablero();
    }
    
    public int getX() {
        return tablero.getX();
    }
    
    public int getY() {
        return tablero.getY();
    }
    
    
}
