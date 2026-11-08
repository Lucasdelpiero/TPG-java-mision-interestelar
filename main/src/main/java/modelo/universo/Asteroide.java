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
    final int COSTO_CHOQUE_ENERGIA = 10;

    @Override
    public void chocaCarguera(Nave nave) {
        nave.getRecursos().consumeEnergia(COSTO_CHOQUE_ENERGIA);
    }

    @Override
    public void chocaExploradora(Nave nave) {
        nave.getRecursos().consumeEnergia(COSTO_CHOQUE_ENERGIA);
    }

    @Override
    public void chocaCombate(Nave nave) {
        nave.getRecursos().consumeEnergia(COSTO_CHOQUE_ENERGIA);
    }
    
    
}
