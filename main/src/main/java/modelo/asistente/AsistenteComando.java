package modelo.asistente;

import modelo.nave.Nave;
import modelo.nave.Recursos;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento; // !!!
import modelo.mision.Mision;

public class AsistenteComando {
    private Nave nave;
    private Bitacora bitacora;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    public AsistenteComando(){
        // nave
        // bitacora
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------
    
    
        //---------------   MISIONES    -----------------
    
    //Esta mision es invocada desde MAIN
    /**
    * <b>Pre:</b> Mision no es nula<br>
    * <b>Post:</b> Devuelve un informe de mision a la bitacora<br>  
    * @param m una clase hija de mision
    */
    public void iniciaMision(Mision m){
        assert (m == null) : "Mision es nulo";
        try {
            m.hacerMision(this);
        }
        finally{
        
        };  
    }
    
    public boolean consultaRecursos(int consumoCombustible, int consumoDesgaste){
        return 
            (nave.getRecursos().getCombustible() - consumoCombustible > 0) 
                && 
            (nave.getRecursos().getDesgaste() + consumoDesgaste < 100);
    }   
    
        //---------------   MODIF RECURSOS    -----------------
    
    public void restaCombustible(int consumoCombustible){
        //Recursos r = nave.getRecursos();
        //r.setCombustible(r.getCombustible() - consumoCombustible);
        nave.getRecursos().setCombustible(nave.getRecursos().getCombustible() - consumoCombustible);
    }
    
    public void sumaDesgaste(int consumoDesgaste){
        //Recursos r = nave.getRecursos();
        //r.setDesgaste( r.getDesgaste() - consumoDesgaste);
        nave.getRecursos().setDesgaste(nave.getRecursos().getDesgaste() - consumoDesgaste);
    
    }
    
    public void restaEnergia(int consumoEnergia){
        //Recursos r = nave.getRecursos();
        //r.setEnergia(r.getEnergia() - consumoEnergia);
        nave.getRecursos().setEnergia(nave.getRecursos().getEnergia() - consumoEnergia);//testing
    }    
    
        //---------------   BITACORA    -----------------
    
    /**
     * <b>Pre:</b> debe ser un evento no nulo<br>
     * <b>Post:</b> agrega un evento a la bitacora<br>
     * 
     * @param evento va a ser eventualmente un evento<br>
     * 
     */
    public void registrarMision(TipoEvento e, String descripcion, String idMision){
        bitacora.registrar(e, descripcion, idMision);
    }
    
    public void registrarEvento(TipoEvento e, String descripcion){
        bitacora.registrar(e, descripcion);
    }
    
    
    //-------------------------------------------
    //          GETTERS / SETTERS
    //-------------------------------------------
    
    public Nave getNave(){
        return nave;
    }
    
    //public Recursos getRecursos(){
    //    return nave.getRecursos();
    //}
    
}   
/*
public void registrarMision(String descripcion, String idMision){
        //assert(evento == null) : "Evento es nulo"; // Se va a cambiar por un nulo 
        bitacora.registrar(TipoEvento.MISION, descripcion, idMision);
    }
*/