/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;
import modelo.nave.Nave;
import modelo.nave.Recursos;

public abstract class Mision {
    protected Nave nave;                    // A la nave le consultara sobre los recursos
    protected EstadoNave estadoNaveInicial, estadoNaveFinal;
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected int consumoEnergia = 0;
    protected boolean puedeHacerMision = true;
   
    public void hacerMision(Nave nave){
        preparar(nave);
        ejecutar(nave);
        evaluar(nave);
        cerrar(nave);
    }
    
    /**
     *<b>Pre:</b> Nave != null<br>
     *<b>Post:</b> Se guarda en una variable si hay recursos suficientes o no<br>  
     * @param nave La nave a la cual se le pregunta por sus recursos
     */
    public void preparar(Nave nave){
        assert (nave == null) : "Nave es null";
        Recursos recursos = new Recursos(nave);
        assert(recursos != null): "recursos es null";

        
 
        if (tieneRecursosSuficientes(recursos)){
            System.out.println("Nave lista para comenzar mision");
        } else {
            System.out.println("No hay recursos suficientes");
            puedeHacerMision = false;
        }
   }
   
   public void ejecutar(Nave nave){
       if (puedeHacerMision){
           // Consume 4 de combustible y desgaste
           nave.getRecursos().consumeCombustible(consumoCombustible);
           nave.getRecursos().consumeDesgaste(consumoDesgaste);
           System.out.println("Se gastaron 4 de combustible y 4 de desgaste");
           // Recursos gastados++
       } else {
           System.out.println("No se pudo ejecutar la mision");
       }
   }
   
   public void evaluar(Nave nave){
       System.out.println("Se evalua(?");
   }
   
   public void cerrar(Nave nave){
       System.out.println("Si es exitoso se suma 5 de energia en misiones 1 y 2");
       System.out.println("Se genera informe de mision");
   }
   
   /**
     *<b>Pre:</b> recursos != null<br>
     *<b>Post:</b> devuelve si puede o no hacer la mision<br>  
    * @param recursos la cantidad de recursos actual de la nave
    */
   public boolean tieneRecursosSuficientes(Recursos recursos){
       return (recursos.getCombustible() >= consumoCombustible &&
               recursos.getEnergia() >= consumoEnergia  &&
               recursos.getDesgaste() >= consumoDesgaste
               );
   }
    public Mision(Nave nave) {
        this.nave = nave;
        estadoNaveInicial = new EstadoNave(100, 80, 0);
    }
   
}
