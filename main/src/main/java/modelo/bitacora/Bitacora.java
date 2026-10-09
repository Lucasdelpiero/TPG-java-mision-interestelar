package modelo.bitacora;
import java.util.ArrayList;

public class Bitacora {
    private ArrayList<Evento> eventos;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //------------------------------------------- 
    public Bitacora(){
        this.eventos = new ArrayList<Evento>();
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //------------------------------------------- 
    
    /**
     * <b>PRE</b>
     * Requiere que el evento sea valido, la validacion ocurre en la clase Evento
     * <b>POST</b>
     * Crea un evento y lo añade al arrayList de eventos
     */
    public void registrar(TipoEvento tipo, String descripcion, String idMision){
        Evento evento = new Evento(tipo, descripcion, idMision);
        this.eventos.add(evento);
    }
    
    // Sobrecarga
    public void registrar(TipoEvento tipo, String descripcion){
        this.registrar(tipo, descripcion, null);
    }

    //-------------------------------------------
    //          GETTERS / SETTERS
    //------------------------------------------- 
    
    public ArrayList<Evento> getEventosDeMision(String idMision){
        ArrayList<Evento> resultado = new ArrayList<Evento>();
        for(Evento e: this.eventos){
            if (e.getIdMision() != null && e.getIdMision().equals(idMision)){
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    public int getCantidadEventos(){
        return this.eventos.size();
    }
}
