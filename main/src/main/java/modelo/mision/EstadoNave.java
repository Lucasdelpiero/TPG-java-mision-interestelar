/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;


public class EstadoNave {
    protected int energia;
    protected int combustible;
    protected int desgaste;

    public int getEnergia() {
        return energia;
    }

    public int getCombustible() {
        return combustible;
    }

    public int getDesgaste() {
        return desgaste;
    }
    
    
    /**
     * <b>pre:</b> Se asume energia, combustible y desgaste como >= 0
     * <b>post:</b> Se crea un objeto que guarda la cantidad de recursos al finalizar la mision
     * @param energia cantidad de energia al momento de finalizar mision
     * @param combustible cantidad de combustible al momento de finalizar mision
     * @param desgaste cantidad de desgaste al momento de finalizar mision
     */
    public EstadoNave(int energia, int combustible, int desgaste) {
        this.energia = energia;
        this.combustible = combustible;
        this.desgaste = desgaste;
    }
    
    
}
