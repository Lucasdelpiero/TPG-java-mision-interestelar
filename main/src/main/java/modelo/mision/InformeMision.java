/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;

import java.util.ArrayList;

public class InformeMision {
    //protected EstadoNave estadoFinalNave;        // Por ahora se entiende como recursos al final de la mision
    //protected ArrayList<String> accionesPrincipales ; // Tipo es temporal
    //protected ArrayList<String> observacinesImportantes; // Tipo es temporal
    
    protected String misionEjecutada;
    //protected boolean resultadoExitoso;
    protected RecursosConsumidos recursosConsumidos;                 
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    
    public InformeMision(String idMision){
        misionEjecutada = idMision;
        //this.resultadoExitoso = resultadoExitoso;
        recursosConsumidos = new RecursosConsumidos();
    }
    
    //-------------------------------------------
    //          ADICIONAL
    //-------------------------------------------
    public void getInforme(int actCombustible, int actDesgaste, int actEnergia, boolean misionCumplida){
        int prevCombustible = recursosConsumidos.getCombustibleConsumido() + actCombustible;
        int prevDesgaste = recursosConsumidos.getDesgasteConsumido() + actDesgaste;
        int prevEnergia = recursosConsumidos.getEnergiaConsumida() + actEnergia;
        
        System.out.println(":: Mision: "+ misionEjecutada);
        System.out.println(":: Resultado: "+ misionCumplida);
        System.out.println(":: Recursos consumidos totales (acumulado): ");
        System.out.println("    * Combustible: +"+ recursosConsumidos.getCombustibleConsumido() );
        System.out.println("    * Desgaste: -"+ recursosConsumidos.getDesgasteConsumido() );
        System.out.println("    * Energia: -"+ recursosConsumidos.getEnergiaConsumida() );
        System.out.println(":: Estado de recursos (Antes // Despues):");
        System.out.println("    * Combustible: "+ prevCombustible +" --> "+ actCombustible);
        System.out.println("    * Desgaste: "+ prevDesgaste +" --> "+ actDesgaste);
        System.out.println("    * Energia: "+ prevEnergia +" --> "+ actEnergia);
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
    
    //public void setResultadoExitoso(boolean resultado){
    //    resultadoExitoso = resultado;
    //}
    
    
}
