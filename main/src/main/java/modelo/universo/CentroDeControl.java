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
        try{
            int i = buscaAC(nombreAC);
            AsistenteComando Asist = listaAC.get(i);    // Puede tirar excepcion si 'i' es una pos invalida
            Asist.setNave(N);
            System.out.println(":: ["+nombre+"] NAVE agregada con exito al Asistente ["+nombreAC+"]!");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(":: ["+nombre+"] ERROR: ASISTENTE DE COMANDO inexistente");
        }
        finally{
            
        }
    }
    
    public Nave getNave(String nombreAC){
        Nave n = null;
        try{
            int i = buscaAC(nombreAC);
            AsistenteComando Asist = listaAC.get(i);    // Puede tirar excepcion si 'i' es una pos invalida
            
            n = Asist.getNave();
            System.out.println(":: ["+nombre+"] NAVE retornada con exito desde el Asistente ["+nombreAC+"]!");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(":: ["+nombre+"] ERROR: ASISTENTE DE COMANDO inexistente");
        }
        finally{
            return n;
        }
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
