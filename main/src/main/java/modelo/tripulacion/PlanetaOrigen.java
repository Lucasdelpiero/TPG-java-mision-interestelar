/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tripulacion;

/**
 *
 * @author ASUS
 */
public abstract class PlanetaOrigen implements Haber {
    
    protected Haber tripulante;
    
    public PlanetaOrigen(Haber tripulante) {
        this.tripulante = tripulante;
    }
    
    @Override
    public Tripulante getTripulante() {
        Haber actual = this.tripulante;
        while (!(actual instanceof Tripulante)) {
            actual = ((PlanetaOrigen) actual).tripulante;
        }
        return (Tripulante) actual;
    }
    
    @Override
    public String toString() {
        return this.tripulante.toString();
    }
}
