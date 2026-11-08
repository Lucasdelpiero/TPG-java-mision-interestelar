/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.universo;

import modelo.nave.Nave;

/**
 *
 * @author ASUS
 */
public class EstacionDeCarga implements ElementoDelUniverso {
    final int RECARGA_COMBUSTIBLE = 50;
    final int RECARGA_ENERGIA = 50;

    @Override
    public void chocaCarguera(Nave nave) {
        nave.getRecursos().cargaCombustible(RECARGA_COMBUSTIBLE);
        nave.getRecursos().cargaEnergia(RECARGA_ENERGIA);
    }

    @Override
    public void chocaExploradora(Nave nave) {
        nave.getRecursos().cargaCombustible(RECARGA_COMBUSTIBLE);
        nave.getRecursos().cargaEnergia(RECARGA_ENERGIA);
    }

    @Override
    public void chocaCombate(Nave nave) {
        nave.getRecursos().cargaCombustible(RECARGA_COMBUSTIBLE);
        nave.getRecursos().cargaEnergia(RECARGA_ENERGIA);
    }
    
    
}
