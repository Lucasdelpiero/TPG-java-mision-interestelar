package modelo.universo;

import java.util.ArrayList;
import modelo.asistente.AsistenteComando;
import modelo.nave.Nave;

public class CentroDeControl {
    private final String nombre = "Glass Group Command Control";
    private ArrayList<AsistenteComando>listaAC;
    
    //-------------------------------
    //         CONSTRUCTOR (SINGLETON?)
    //-------------------------------
    
    public CentroDeControl(){
        listaAC = new ArrayList<AsistenteComando>();
    }
   
    //-------------------------------
    //      GETTERS / SETTERS
    //-------------------------------
    
    /**
     * <b>POST</b>
     * - Registra el AsistenteComando en la lista del centro de control si no es nulo.
     */
    public void setAC(AsistenteComando AC){
        if(AC == null)
            System.out.println(":: ["+nombre+"] ASISTENTE DE COMANDO ingresado es INVALIDO");
        else{
            listaAC.add(AC);
            System.out.println(":: ["+nombre+"] "+ AC.getNombre() +" ingresado con EXITO");
        }
    }
    
    /**
     * <b>POST</b>
     * - Asigna la nave al asistente de comando correspondiente o lanza IllegalArgumentException si no existe.
     */
    public void setNave(Nave N, String nombreAC){
        int i = buscaAC(nombreAC);
        if (i < listaAC.size()) {
            AsistenteComando Asist = listaAC.get(i);
            Asist.setNave(N);
            System.out.println(":: ["+nombre+"] NAVE agregada con exito al Asistente ["+nombreAC+"]!");
        } else {
            throw new IllegalArgumentException("No existe la nave: " + nombreAC);
        }
    }
    
    /**
     * <b>POST</b>
     * - Retorna el AsistenteComando correspondiente o lanza IllegalArgumentException si el nombre es inválido o no existe.
     */
    public AsistenteComando getAC(String nombreAC){
        if (nombreAC == null || nombreAC.isBlank()) {
            throw new IllegalArgumentException("No se ha seleccionado ninguna nave.");
        }
        int i = buscaAC(nombreAC);
        if (i >= listaAC.size()) {
            throw new IllegalArgumentException("La nave no existe: " + nombreAC);
        }
        return listaAC.get(i);
    }
    
    /**
     * <b>POST</b>
     * - Retorna la nave asociada al AsistenteComando o lanza IllegalArgumentException si no existe.
     */
    public Nave getNave(String nombreAC){
        AsistenteComando Asist = getAC(nombreAC);
        return Asist.getNave();
    }

    /**
     * <b>POST</b>
     * - Remueve el AC y su nave asociada del registro si se encuentra presente.
     */
    public void eliminarNave(String nombreAC){
        if (nombreAC == null || nombreAC.isBlank()) return;
        int i = buscaAC(nombreAC);
        if (i < listaAC.size()) {
            listaAC.remove(i);
            System.out.println(":: ["+nombre+"] Nave/AC ["+nombreAC+"] eliminada del Centro de Control.");
        }
    }

    /**
     * <b>POST</b>
     * - Retorna true si la nave existe en el centro de control; false si no existe o el nombre es inválido.
     */
    public boolean existeNave(String nombreAC){
        if (nombreAC == null || nombreAC.isBlank()) return false;
        return buscaAC(nombreAC) < listaAC.size();
    }
    
    //-------------------------------
    //         VALIDACIONES
    //-------------------------------
    private int buscaAC(String nombre){
        int i = 0;
        while(i < listaAC.size() && !(listaAC.get(i).getNombre().equalsIgnoreCase(nombre)) ){
            i++;
        }
        return i;
    }
    
}
