package modelo.asistente;

import modelo.nave.Nave;
import modelo.nave.Recursos;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento; // !!!
import modelo.mision.InformeMision;
import modelo.mision.Mision;
import modelo.motorwarp.MotorTransicionException;

public class AsistenteComando {
    private String nombre;
    private Nave nave;
    private Bitacora bitacora;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    public AsistenteComando(Nave nave, String nombre){
        this.nave = nave;
        this.nombre = nombre;
        this.bitacora = new Bitacora();
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------
    
    
        //---------------   MISIONES    -----------------
    
    //Esta mision es invocada desde MAIN
    /**
    * PRE: Mision no es nula<br>
    * POST: Devuelve un informe de mision a la bitacora<br>  
    * @param m una clase hija de mision
    */
    public void iniciaMision(Mision m){
        assert (m != null) : "Mision no puede ser nula";
        this.bitacora.registrar(TipoEvento.ORDEN, "Orden recibida: Iniciar mision " + m.getIdMision());
        this.bitacora.registrar(TipoEvento.MISION, m.getNombreMision(), m.getIdMision());
        try {
            m.hacerMision(this);
            // Si la mision es exitosa, va a hacer un salto warp
            if (m.getInforme().esResultadoExitoso()){
                //bitacora.registrar(TipoEvento.WARP, "Hace warp");
                motor_secuencia_salto();
            }
        }
        catch (Exception e){
            this.bitacora.registrar(TipoEvento.ERROR, "Error al Iniciar Mision " + m.getIdMision());
        } finally {
            
        }
    }
    
    public boolean consultaRecursos(int consumoCombustible,int consumoEnergia, int consumoDesgaste){
        return 
            (nave.getRecursos().getCombustible() - consumoCombustible >= 0) 
                && 
            (nave.getRecursos().getDesgaste() + consumoDesgaste <= 100) &&
            (nave.getRecursos().getEnergia() + consumoEnergia >= 0)    
                ;
    }   
    
        //---------------   MODIF RECURSOS    -----------------
    
    public void restaCombustible(int consumoCombustible){
        nave.getRecursos().consumeCombustible(consumoCombustible);
    }
    
    public void restaEnergia(int consumoEnergia){
        nave.getRecursos().consumeEnergia(consumoEnergia);
    }
    
    public void sumaDesgaste(int consumoDesgaste){
        nave.getRecursos().consumeDesgaste(consumoDesgaste);
    }
        //---------------   MOTOR WARP    -----------------
    /**
     * PRE: nave y motor no son nulos
     * POST: ejecuta cambio en motor warp si es posible
     * Pide al motor que ejecute un salto
     */
    public void motor_secuencia_salto(){
        try {
            getNave().getMotor().secuencia_salto();
        }
        catch(MotorTransicionException e){
            System.out.println(e.descripcion);
        }
        
    }
    
    public void motor_preparar_salto() throws MotorTransicionException{
        getNave().getMotor().prepararSalto();
        System.out.println(getNave().getMotor().getNombreEstado());
    }
    
    public void motor_saltar() throws MotorTransicionException{
        getNave().getMotor().saltar();
        System.out.println(getNave().getMotor().getNombreEstado());
    }
    
    /**
     * PRE: nave y motor no son nulos
     * POST: cancela el salto del motor warp
     */
    public void motor_cancelar_salto() throws MotorTransicionException{
        getNave().getMotor().terminarSalto();
    }
    
    //---------------   RECURSOS    -----------------
    public Recursos getRecursos(){
        return nave.getRecursos();
    }   
    public void imprimeRecursos(){
        nave.getRecursos().imprimeRecursos();
    }
    
    //---------------   Tripulacion    -----------------
    
    // Devuelve objeto tripulacion que contiene metodos para relacionarse con la lista tripulacion
    public void getTripulante(){
        nave.getTripulacion();
    }
    
    // Devuelve la lista en un array de los tripulantes
    public void getTripulacion(){
        nave.getTripulantes();
    }
    
   //---------------   BITACORA    -----------------
    
    /**
     * PRE: debe ser un evento no nulo, descripcion != 0 <br>
     * POST: agrega un evento a la bitacora<br>
     * 
     * @param evento va a ser eventualmente un evento<br>
     * 
     */
    public void registrarMision(TipoEvento e, String descripcion, int idMision){
        this.bitacora.registrar(e, descripcion, idMision);
    }
    
    public void registrarEvento(TipoEvento e, String descripcion){
        this.bitacora.registrar(e, descripcion);
    }
    
    //-------------------------------------------
    //          GETTERS / SETTERS
    //-------------------------------------------
    public String getNombre(){
        return nombre;
    }
    
    public Nave getNave(){
        return nave;
    }
    
    public void setNave(Nave nave){
        this.nave = nave;
    }
    
    public Bitacora getBitacora(){
        return this.bitacora;
    }
    //public Recursos getRecursos(){
    //    return nave.getRecursos();
    //}
    
}   