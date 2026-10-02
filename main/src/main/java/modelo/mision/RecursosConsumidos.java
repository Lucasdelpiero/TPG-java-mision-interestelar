/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;

public class RecursosConsumidos {
    protected int energiaConsumida = 0;
    protected int combustibleConsumido = 0;
    protected int desgasteConsumido = 0;
    
    /**
     * <b>pre:</b> num es posivo y no se consume mas de lo que la nave tiene
     * <b>post:</b> se agrega al consumo el numero
     * @param num cantidad que se suma a lo consumido de energia
     */
    public void consumirEnergia(int num) {
       energiaConsumida += num;
    }
    /**
     * <b>pre:</b> num es posivo y no se consume mas de lo que la nave tiene
     * <b>post:</b> se agrega al consumo el numero
     * @param num cantidad que se suma a lo consumido de combustible
     */
    public void consumirCombustible(int num){
        combustibleConsumido += num;
    }
    /**
     * <b>pre:</b> num es posivo y no se consume mas de lo que la nave tiene
     * <b>post:</b> se agrega al consumo el numero
     * @param num cantidad que se suma a lo consumido de este desgaste
     */
    public void consumirDesgaste(int num){
        desgasteConsumido += 0;
    }
    
    public int getEnergiaConsumida() {
        return energiaConsumida;
    }

    public int getCombustibleConsumido() {
        return combustibleConsumido;
    }
    
    public int getDesgasteConsumido() {
        return desgasteConsumido;
    }

    public RecursosConsumidos() {
    }
    
    
}
