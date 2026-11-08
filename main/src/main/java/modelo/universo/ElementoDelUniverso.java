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
public interface ElementoDelUniverso {
    public void chocaCarguera(Nave nave);
    public void chocaExploradora(Nave nave);
    public void chocaCombate(Nave nave);
}
