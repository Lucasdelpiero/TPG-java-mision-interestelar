/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mision;
import modelo.nave.Nave;

// Mision M-02
public class MisionRecoleccion extends Mision{
    
    @Override
    public void cerrar(){
        System.out.println("Se añaden 5 de energia adicional");
    }
    
    public MisionRecoleccion(Nave nave) {
        super(nave);
    }
    
}
