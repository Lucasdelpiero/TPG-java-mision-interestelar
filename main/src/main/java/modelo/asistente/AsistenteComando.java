/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.asistente;

import modelo.nave.Nave;
import modelo.nave.Recursos;

import modelo.bitacora.Bitacora;
import modelo.mision.Mision;

public class AsistenteComando {
    protected Nave nave;
    protected Bitacora bitacora;
    
    /**
     * <b>Pre:</b> debe ser un evento no nulo<br>
     * <b>Post:</b> agrega un evento a la bitacora<br>
     * 
     * @param evento va a ser eventualmente un evento<br>
     * 
     */
    public void agregarEvento(Object evento){
        assert(evento == null) : "Evento es nulo"; // Se va a cambiar por un nulo 
        //nave.agregarEvento(evento)
    }
    
    /**
     *<b>Pre:</b> Mision no es nula<br>
     *<b>Post:</b> Devuelve un informe de mision a la bitacora<br>  
     * @param mision una clase hija de mision
     */
    public void iniciaMision(Mision mision){
        assert (mision == null) : "Mision es nulo";
        try {mision.hacerMision(nave);}
        finally{};
        
    }
}
