/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.universo;
import modelo.nave.Nave;
import modelo.universo.ElementoDelUniverso;
/**
 *
 * @author ASUS
 */
public class Asteroide implements ElementoDelUniverso {
    private final int COSTO_CHOQUE_ENERGIA = 10;

    public int getCostoChoqueEnergia() {
        return COSTO_CHOQUE_ENERGIA;
    }
    
    @Override
    public void choque(ElementoDelUniverso elem) {
        if (elem instanceof Nave nave)
            nave.choque(elem);
    }
    
    
}
