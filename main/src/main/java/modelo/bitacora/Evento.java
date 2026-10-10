package modelo.bitacora;
import java.util.Date;

/**
 * <b>PRE</b>
 * Tipo debe ser algunos de los tipos soportados por el Enum TipoEvento
 * La descripcion no debe estar vacia
 * El idMision puede ser null pero de no serlo no puede estar vacio
 */
public class Evento {
    private final Date momento;
    private final TipoEvento tipo;
    private final String descripcion;
    private final Integer idMision; // Podria ser null si el evento no esta relacionado a una mision

    //-------------------------------------------
    //          CONSTRUCTOR / VALIDACION
    //-------------------------------------------    
    // Sobrecarga
    public Evento(TipoEvento tipo, String descripcion) {
        this(tipo, descripcion, null);
    }
    
    public Evento(TipoEvento tipo, String descripcion, Integer idMision) {
        validar(tipo, descripcion, idMision);
        this.momento = new Date();
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idMision = idMision;
    }
    
    private void validar(TipoEvento tipo, String descripcion, Integer idMision){
        if (tipo == null || 
            descripcion == null ||
            descripcion.trim().isEmpty() ||
            idMision != null && idMision <= 0) {

            throw new IllegalArgumentException("El evento que se intenta registrar tiene datos invalidos");
        }
    }
    
    //-------------------------------------------
    //          GETTERS / SETTERS
    //-------------------------------------------    
    
    /**
     * 
     * @return Devuelve el tiempo actual al Date creado en el constructor
     */
    public Date getMomento(){
        return new Date(this.momento.getTime()); // Se crea un nuevo Date() no se devuelve la referencia al atributo
    }
    
    public TipoEvento getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getIdMision() {
        return idMision;
    }
}
