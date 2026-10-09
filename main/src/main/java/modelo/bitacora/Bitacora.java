package modelo.bitacora;
import java.util.ArrayList;

public class Bitacora {
    private ArrayList<Evento> eventos;
    
    public Bitacora(){
        this.eventos = new ArrayList<Evento>();
    }
    
    // Sobrecarga
    public void registrar(TipoEvento tipo, String descripcion){
        this.registrar(tipo, descripcion, null);
    }

    /**
     * La validacion de los datos la hace la clase Evento
     * <b>POST</b>
     * Crea un evento y lo añade al arrayList de eventos
     */
    public void registrar(TipoEvento tipo, String descripcion, String idMision){
        Evento evento = new Evento(tipo, descripcion, idMision);
        this.eventos.add(evento);
    }

/**
 *  Este manejo de errores debe hacerse en la capa de logica
 *  de negocio por el asistente de comandos, y no aqui en el paquete modelo.
        try {
            Evento evento = new Evento(tipo, descripcion, idMision);
            this.eventos.add(evento);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
              System.out.println("Error Desconocido al intentar registrar la bitacora");  
        }
 */

    /**
     * <b>POST</b>
     * Devuelve una lista con los eventos que coincidan con idMision
     * si no hay coincidencias o el id es nulo, devuelve una lista vacía
     */

    public ArrayList<Evento> getEventosDeMision(String idMision){
        ArrayList<Evento> resultado = new ArrayList<Evento>();
        for(Evento e: this.eventos){
            if (e.getIdMision() != null && e.getIdMision().equals(idMision)){
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * <b>POST</b>
     * Devuelve una copia de todos los eventos registrados en orden cronologico.
    */
    public ArrayList<Evento> getEventos(){
        return new ArrayList<Evento>(this.eventos);
    }

    public int getCantidadEventos(){
        return this.eventos.size();
    }
}
