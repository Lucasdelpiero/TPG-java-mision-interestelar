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
    
    protected String misionEjecutada;
    protected boolean resultadoExitoso = true;
    protected RecursosConsumidos recursosConsumidos;   
    
    protected Recursos recursosIniciales;
    protected Recursos recursosFinales;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    
    public InformeMision(){
        recursosIniciales = new Recursos(0, 0, 0);
        recursosFinales = new Recursos(0, 0, 0);
    }
    
    public InformeMision(String idMision){
        misionEjecutada = idMision;
        
        //this.resultadoExitoso = resultadoExitoso;
        //recursosConsumidos = new RecursosConsumidos();
    }
    
    //-------------------------------------------
    //          ADICIONAL
    //-------------------------------------------
    // PARA BORRAR DESPUES
    public void getInforme(){
        //int prevCombustible = recursosConsumidos.getCombustibleConsumido() + actCombustible;
        //int prevDesgaste = recursosConsumidos.getDesgasteConsumido() + actDesgaste;
        //int prevEnergia = recursosConsumidos.getEnergiaConsumida() + actEnergia;
        
        System.out.println(":: Mision: "+ misionEjecutada);
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
    
    public void setMisionEjecutada(String id){
        this.misionEjecutada = id;
    }
    //public void setResultadoExitoso(boolean resultado){
    //    resultadoExitoso = resultado;
    //}
    
    
}
