/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;

import java.util.ArrayList;
import modelo.nave.Recursos;

public class InformeMision {
    //protected EstadoNave estadoFinalNave;        // Por ahora se entiende como recursos al final de la mision
    //protected ArrayList<String> accionesPrincipales ; // Tipo es temporal
    //protected ArrayList<String> observacinesImportantes; // Tipo es temporal
    
    protected Integer idMision;
    protected String nombreMision;
    protected boolean resultadoExitoso = true;
    protected RecursosConsumidos recursosConsumidos;   
    
    protected Recursos recursosIniciales;
    protected Recursos recursosFinales;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
 
    
    public InformeMision(Integer idMision, String nombre){
        this.nombreMision = nombre;
        this.idMision = idMision;
        this.recursosIniciales = new Recursos(0, 0, 0);
        this.recursosFinales = new Recursos(0, 0, 0);
    }
    
    //-------------------------------------------
    //          ADICIONAL
    //-------------------------------------------
    // PARA BORRAR DESPUES
    public void imprimirInforme(){
        //int prevCombustible = recursosConsumidos.getCombustibleConsumido() + actCombustible;
        //int prevDesgaste = recursosConsumidos.getDesgasteConsumido() + actDesgaste;
        //int prevEnergia = recursosConsumidos.getEnergiaConsumida() + actEnergia;
        System.out.println("______________________________________________");
        System.out.println(":: Mision: " + nombreMision);
        System.out.println("----------------------------------------------");
        System.out.println(":: Resultado: "+ (resultadoExitoso ? "Exito" : "Fracaso") );
        
        System.out.println(":: Recursos consumidos totales (acumulado): ");    
        System.out.println("    * Combustible: "+ 
                                (recursosIniciales.getCombustible() -
                                recursosFinales.getCombustible() ) );
        System.out.println("    * Energia: "+ 
                                (recursosIniciales.getEnergia() -
                                recursosFinales.getEnergia() ) );
        System.out.println("    * Desgaste: "+ 
                                (recursosFinales.getDesgaste() -
                                recursosIniciales.getDesgaste() ) );
        
        
        System.out.println(":: Estado de recursos (Antes // Despues):");
        System.out.println("    * Combustible: "+ 
                            recursosIniciales.getCombustible() +" --> "+ 
                            recursosFinales.getCombustible());
        System.out.println("    * Desgaste: "+ 
                            recursosIniciales.getDesgaste() +" --> "+ 
                            recursosFinales.getDesgaste());
        System.out.println("    * Energia: "+ 
                            recursosIniciales.getEnergia() +" --> "+
                            recursosFinales.getEnergia());
        System.out.println("______________________________________________");
    }
    
    
    public void addConsumoEnergia(int valor){
        recursosConsumidos.consumirEnergia(valor);
    }
    
    public void addConsumoDesgaste(int valor){
        recursosConsumidos.consumirDesgaste(valor);
    }
    
    public void addConsumoCombustible(int valor){
        recursosConsumidos.consumirCombustible(valor);
    }
    
    //-------------------------------------------
    //          GETTERS/SETTERS
    //-------------------------------------------    
    
    public void setNombreMision(String id){
        this.nombreMision = id;
    }

    public boolean esResultadoExitoso() {
        return resultadoExitoso;
    }
    
}
