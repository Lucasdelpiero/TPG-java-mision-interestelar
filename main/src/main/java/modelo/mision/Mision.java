/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;
import modelo.nave.Nave;

public abstract class Mision {
    protected Nave nave;                    // A la nave le consultara sobre los recursos
    protected EstadoNave estadoNaveInicial, estadoNaveFinal;
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected boolean puedeHacerMision = true;
   
    public void hacerMision(){
        preparar();
        ejecutar();
        evaluar();
        cerrar();
    }
    
    public void preparar(){
        //private int naveEnergia = 80;
        //private int naveCombustible = 100;
        //private int naveDesgaste = 0;
        //estadoNaveInicial = new EstadoNave(naveEnergia, naveCombustible, naveDesgaste); 
        
        boolean tieneRecursosSuficientes = true;  // nave.tieneRecursos(args)
        if (tieneRecursosSuficientes){
            System.out.println("Nave lista para comenzar mision");
        } else {
            System.out.println("No hay recursos suficientes");
            puedeHacerMision = false;
        }
   }
   
   public void ejecutar(){
       if (puedeHacerMision){
           // Consume 4 de combustible y desgaste
           System.out.println("Se gastaron 4 de combustible y 4 de desgaste");
           // Recursos gastados++
       } else {
           System.out.println("No se pudo ejecutar la mision");
       }
   }
   
   public void evaluar(){
       System.out.println("Se evalua(?");
   }
   
   public void cerrar(){
       System.out.println("Si es exitoso se suma 5 de energia en misiones 1 y 2");
       System.out.println("Se genera informe de mision");
   }

    public Mision(Nave nave) {
        this.nave = nave;
        estadoNaveInicial = new EstadoNave(100, 80, 0);
    }
   
}
