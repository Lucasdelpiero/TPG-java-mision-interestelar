package modelo.asistente;

import modelo.nave.Nave;
import modelo.nave.Recursos;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento; // !!!
import modelo.mision.Mision;
import modelo.motorwarp.MotorTransicionException;
import modelo.tripulacion.*;

import java.util.ArrayList;

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
        if (nave != null && nave.getMotor() != null) {
            nave.getMotor().setAC(this);
        }
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
        this.bitacora.registrar(TipoEvento.ORDEN, "Orden recibida: Iniciar mision " + m.getIdMision(), m.getIdMision());
        this.bitacora.registrar(TipoEvento.MISION, "Iniciando mision " + m.getNombreMision(), m.getIdMision());
        try {
            m.hacerMision(this);
            // Si la mision es exitosa, va a hacer un salto warp
            if (m.getInforme().esResultadoExitoso()){
                motor_secuencia_salto();
            }
        }
        catch (Exception e){
            this.bitacora.registrar(TipoEvento.ERROR, "Error al Iniciar Mision " + m.getIdMision());
        } finally {
            
        }
    }
    
    public boolean consultaRecursos(int consumoCombustible,int consumoEnergia, int consumoDesgaste){
        if (nave == null || nave.getRecursos() == null) return false;
        return 
            (nave.getRecursos().getCombustible() - consumoCombustible >= 0) 
                && 
            (nave.getRecursos().getDesgaste() + consumoDesgaste <= 100) &&
            (nave.getRecursos().getEnergia() - consumoEnergia >= 0)    
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
    
    public void cargaCombustible(int valor){
        System.out.println(":: ["+nombre+"] CARGA DE COMBUSTIBLE");
        System.out.println(":: ["+nombre+"] Actual: "+nave.getRecursos().getCombustible());
        int prev = nave.getRecursos().getCombustible();
        nave.getRecursos().cargaCombustible(valor);
        System.out.println(":: ["+nombre+"] Cargando combustible...");
        System.out.println(":: ["+nombre+"] Nuevo: "+nave.getRecursos().getCombustible()+"\n");
        
        if (nave.getRecursos().getCombustible() > prev) {
            this.registrarEvento(TipoEvento.RECURSOS, "Carga " + valor + " de combustible");
        } else {
            this.registrarEvento(TipoEvento.RECURSOS, "RECHAZADA: Carga " + valor + " de combustible excede capacidad o es invalida");
        }
    }
    
    public void cargaEnergia(int valor){
        System.out.println(":: ["+nombre+"] CARGA DE ENERGIA");
        System.out.println(":: ["+nombre+"] Actual: "+nave.getRecursos().getEnergia());
        int prev = nave.getRecursos().getEnergia();
        nave.getRecursos().cargaEnergia(valor);
        System.out.println(":: ["+nombre+"] Cargando energia...");
        System.out.println(":: ["+nombre+"] Nuevo: "+nave.getRecursos().getEnergia()+"\n");
        
        if (nave.getRecursos().getEnergia() > prev) {
            this.registrarEvento(TipoEvento.RECURSOS, "Carga " + valor + " de energia");
        } else {
            this.registrarEvento(TipoEvento.RECURSOS, "RECHAZADA: Carga " + valor + " de energia excede capacidad o es invalida");
        }
    }
    
    public void realizaMantenimiento(){
        System.out.println(":: ["+nombre+"] MANTENIMIENTO A LA NAVE");
        System.out.println(":: ["+nombre+"] Desgaste: "+nave.getRecursos().getDesgaste());
        int prev = nave.getRecursos().getDesgaste();
        nave.getRecursos().realizaMantenimiento();
        System.out.println(":: ["+nombre+"] Realizando mantenimiento...");
        System.out.println(":: ["+nombre+"] Desgaste: "+nave.getRecursos().getDesgaste()+"\n");
        this.registrarEvento(TipoEvento.RECURSOS, "Mantenimiento realizado. Desgaste: " + prev + " -> " + nave.getRecursos().getDesgaste());
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
    public Tripulacion getTripulacion(){
        return nave.getTripulacion();
    }
    
    // Devuelve la lista en un array de los tripulantes
    public ArrayList<PlanetaOrigen> getTripulantes(){
        return nave.getTripulantes();
    }

    /**
     * PRE: tripulante t != null<br>
     * POST: se agrega tripulante a la tripulacion
     * @param t es el tripulante que se quiere añadir a la tripulacion 
     */
    public void addTripulante(PlanetaOrigen t){
        assert(t != null): "Error: tripulando es null";
        assert(!t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan") || nave.getTripulacion().getTripulantesCargo("Capitan").isEmpty()):"Error : Ya hay un capitan";
        nave.getTripulacion().addTripulante(t);
        this.registrarEvento(TipoEvento.TRIPULACION, "Tripulante " + t.getTripulante().getId() + " añadido");
    }
    
    public void removeTripulante(PlanetaOrigen t){
        assert(t != null): "Error: tripulando es null";
        assert(!t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan")) :"Error : No se puede eliminar el capitan";
        nave.getTripulacion().removeTripulante(t.getTripulante().getId());
        this.registrarEvento(TipoEvento.TRIPULACION, "Tripulante " + t.getTripulante().getId() + " removido");
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
        if (nave != null && nave.getMotor() != null) {
            nave.getMotor().setAC(this);
        }
    }
    
    public Bitacora getBitacora(){
        return this.bitacora;
    }
    //public Recursos getRecursos(){
    //    return nave.getRecursos();
    //}
    
}   