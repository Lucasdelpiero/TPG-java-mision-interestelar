package modelo.asistente;

import modelo.nave.Nave;
import modelo.nave.Recursos;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento; // !!!
import modelo.mision.Mision;
import modelo.mision.FactoryMision;
import modelo.motorwarp.MotorTransicionException;
import modelo.universo.Universo;
import modelo.tripulacion.*;

import java.util.ArrayList;

public class AsistenteComando {
    private String nombre;
    private Nave nave;
    private Bitacora bitacora;
    
    private final FactoryMision fm = new FactoryMision();
    private Mision misionActual = null;
    private Universo universo;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    public AsistenteComando(Nave nave, String nombre, Universo universo){
        assert nave != null : "La nave no puede ser nula";
        this.universo = universo;
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
    
    /**
     * <b>POST</b>
     * - Instancia y ejecuta la misión indicada, o lanza IllegalStateException si la nave no está apta, no tiene Capitán o hay una misión en curso.
     */
    public void realizarMision(String texto) {
        if (nave == null || !nave.estaOperativa()) {
            if (nave != null && nave.getTripulacion().getTripulantesCargo("Capitan").isEmpty()) {
                throw new IllegalStateException("La nave no puede iniciar la misión: requiere obligatoriamente 1 Capitán asignado.");
            }
            throw new IllegalStateException("La nave no se encuentra operativa para realizar misiones.");
        }
        if (misionActual != null) {
            this.bitacora.registrar(TipoEvento.ERROR, "Error otra mision en progreso " + misionActual.getIdMision());
            throw new IllegalStateException("Ya hay una misión en curso en esta nave.");
        }
        misionActual = fm.crearMision(texto, universo);
        iniciaMision(misionActual);
    }
    
    public boolean hayMisionEnCurso() {
        return misionActual != null;
    }
    private void iniciaMision(Mision m){
        assert (m != null) : "Mision no puede ser nula";
        this.bitacora.registrar(TipoEvento.ORDEN, "Orden recibida: Iniciar mision " + m.getIdMision(), m.getIdMision());
        this.bitacora.registrar(TipoEvento.MISION, "Iniciando mision " + m.getNombreMision(), m.getIdMision());
        try {
            m.hacerMision(this);
        }
        catch (Exception e){
            this.bitacora.registrar(TipoEvento.ERROR, "Error al Iniciar Mision " + m.getIdMision());
        } finally {
            misionActual = null;      // se libera recién cuando la misión realmente terminó
        }
    }
    
    /**
     * <b>POST</b>
     * - Retorna true si la nave conserva recursos suficientes y desgaste menor o igual a 100 tras el consumo; false en caso contrario.
     */
    public boolean consultaRecursos(int consumoCombustible,int consumoEnergia, int consumoDesgaste){
        if (nave == null || nave.getRecursos() == null) return false;
        return 
            (nave.getRecursos().getCombustible() - consumoCombustible >= 0) 
                && 
            (nave.getRecursos().getDesgaste() + consumoDesgaste <= 100) &&
            (nave.getRecursos().getEnergia() - consumoEnergia >= 0);
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
    
    /**
     * <b>POST</b>
     * - Intenta cargar combustible en la nave y registra el resultado del intento en bitácora.
     */
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
    
    /**
     * <b>POST</b>
     * - Intenta cargar energía en la nave y registra el resultado del intento en bitácora.
     */
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
    
    /**
     * <b>POST</b>
     * - Restablece el desgaste de la nave a 0 y registra la acción en bitácora.
     */
    public void realizaMantenimiento(){
        System.out.println(":: ["+nombre+"] MANTENIMIENTO A LA NAVE");
        System.out.println(":: ["+nombre+"] Desgaste: "+nave.getRecursos().getDesgaste());
        int prev = nave.getRecursos().getDesgaste();
        nave.getRecursos().realizaMantenimiento();
        System.out.println(":: ["+nombre+"] Realizando mantenimiento...");
        System.out.println(":: ["+nombre+"] Desgaste: "+nave.getRecursos().getDesgaste()+"\n");
        this.registrarEvento(TipoEvento.RECURSOS, "Mantenimiento realizado. Desgaste: " + prev + " -> " + nave.getRecursos().getDesgaste());
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
    public ArrayList<Haber> getTripulantes(){
        return nave.getTripulantes();
    }

    /**
     * <b>POST</b>
     * - Agrega el tripulante a la nave y registra en bitácora, o lanza excepción si infringe restricciones.
     */
    public void addTripulante(Haber t){
        if (t == null) {
            throw new IllegalArgumentException("El tripulante no puede ser nulo.");
        }
        if (nave == null || nave.estaInservible()) {
            throw new IllegalStateException("No se pueden agregar tripulantes a una nave inservible.");
        }
        if (nave.getTripulacion().getTripulantes().size() >= 5) {
            throw new IllegalStateException("La tripulación ya está completa (máximo 5 tripulantes permitidos).");
        }
        if (t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan") 
                && !nave.getTripulacion().getTripulantesCargo("Capitan").isEmpty()) {
            throw new IllegalStateException("La nave ya posee un Capitán. Solo se permite un Capitán por tripulación.");
        }
        if (nave.getTripulacion().getTripulante(t.getTripulante().getId()) != null) {
            throw new IllegalArgumentException("Ya existe un tripulante con el ID [" + t.getTripulante().getId() + "] en esta nave.");
        }
        nave.getTripulacion().addTripulante(t);
        this.registrarEvento(TipoEvento.TRIPULACION, "Tripulante " + t.getTripulante().getId() + " añadido");
    }
    
    /**
     * <b>POST</b>
     * - Remueve el tripulante de la nave y registra en bitácora, o lanza excepción si es nulo o corresponde al Capitán.
     */
    public void removeTripulante(Haber t){
        if (t == null) {
            throw new IllegalArgumentException("El tripulante no puede ser nulo.");
        }
        if (t.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan")) {
            throw new IllegalStateException("No se puede eliminar al Capitán de la nave.");
        }
        nave.getTripulacion().removeTripulante(t.getTripulante().getId());
        this.registrarEvento(TipoEvento.TRIPULACION, "Tripulante " + t.getTripulante().getId() + " removido");
    }
    
   //---------------   BITACORA    -----------------
    
    /**
     * <b>POST</b>
     * - Inserta el evento asociado a la misión especificada en la bitácora.
     */
    public void registrarMision(TipoEvento e, String descripcion, int idMision){
        this.bitacora.registrar(e, descripcion, idMision);
    }
    
    /**
     * <b>POST</b>
     * - Inserta el evento general en la bitácora.
     */
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
        assert nave != null : "La nave no puede ser nula";
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
