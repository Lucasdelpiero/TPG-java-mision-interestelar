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
    
    public void setAC(AsistenteComando AC){
        if(AC == null)
            System.out.println(":: ["+nombre+"] ASISTENTE DE COMANDO ingresado es INVALIDO");
        else{
            listaAC.add(AC);
            System.out.println(":: ["+nombre+"] "+ AC.getNombre() +" ingresado con EXITO");
        }
    }
    
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
    
    public Nave getNave(String nombreAC){
        AsistenteComando Asist = getAC(nombreAC);
        return Asist.getNave();
    }

    public void eliminarNave(String nombreAC){
        if (nombreAC == null || nombreAC.isBlank()) return;
        int i = buscaAC(nombreAC);
        if (i < listaAC.size()) {
            listaAC.remove(i);
            System.out.println(":: ["+nombre+"] Nave/AC ["+nombreAC+"] eliminada del Centro de Control.");
        }
    }

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
