/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.motorwarp;

/**
 *
 * @author Lucas
 */
public class MotorTransicionException extends Throwable{
    public String descripcion;
    public MotorTransicionException(String desc) {
        descripcion = desc;
    }
    
}
