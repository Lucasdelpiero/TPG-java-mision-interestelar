/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.universo;
import java.util.ArrayList;
import modelo.nave.Nave;
import modelo.destinoytablero.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
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
        return tablero.obtenerObjetivo();
    }
    
    public void moverNave(Posicion posicion) {
        tablero.moverNave(posicion);
    }
    
    public ElementoDelUniverso inspeccionarPosicion() {
        return tablero.inspeccionarPosicion();
    }
    
    public ElementoDelUniverso[][] observarAlrededores() {
        return tablero.observarAlrededores();
    }
    
    public void pasarTurnoDestino() {
        destino.turnoDestino();
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
