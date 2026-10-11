package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento;
import modelo.nave.Recursos;

public abstract class Mision{
    
    public static enum tipoMision {
        INTERCEPCION,
        RECOLECCION,
        RETORNO_SEGURO
    }
    
    protected tipoMision tipo;
    protected int id;
    protected static int idSiguiente = 1;
    protected String nombreMision = "no asignado";
    protected String descripcion = "no asignado";
    
    protected AsistenteComando AC;
    protected InformeMision informe;
    
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected int consumoEnergia = 0;
    protected boolean puedeIniciarMision;
    protected boolean misionCumplida = false;
    protected Integer idMision;
    protected static int idMisionSiguiente = 1;

    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------

    public Mision(){
        this.idMision = idMisionSiguiente++;
    }


    //-------------------------------------------
    //          CICLO PRINCIPAL
    //-------------------------------------------
    // idea:
    // preparar() y ejecutar() dentro de TRY
    // evaluar() y cerrar() dentro de FINALY

    // Posibles excepciones (ejemplo):
    //  - Nave con recursos insuficientes
    public void hacerMision(AsistenteComando AC){
        assert (AC != null): "Error: AC es null" ;
        this.AC = AC;
        
        preparar();
        ejecutar();
        evaluar();
        cerrar();
    }
    
    //-------------------------------------------
    //          DESARROLLO MISIONES
    //-------------------------------------------
    
    // PREPARAR: idea
        //    INTENTAR APLICAR TRY/FINALLY PARA VERIFICAR RECURSOS.
        // EN CASO DE NO CONTAR CON RECURSOS SUFICIENTES, CORTAR
        // LA EJECUCION DIRECTAMENTE Y PROPAGAR LA EXCEPCION AL
        // METODO hacerMision().
        // CON ESO, IGNORAR EL USO DEL BOOLEANO 'puedeHacerMision'
    protected void preparar(){
        puedeIniciarMision = (AC.consultaRecursos(consumoCombustible, consumoEnergia,consumoDesgaste));
        
        informe = new InformeMision(id, nombreMision);
        
        Recursos resViejos = AC.getNave().getRecursos();
        
        informe.recursosIniciales.setCombustible(resViejos.getCombustible());
        informe.recursosIniciales.setEnergia(resViejos.getEnergia());
        informe.recursosIniciales.setDesgaste(resViejos.getDesgaste());
        

        System.out.println("[MISION] Iniciando mision...");
        AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Iniciando mision...", id);
        
        assert (AC.getNave() != null) :    ":: [MISION] ERROR: Nave es null";
        assert (AC.getNave().getRecursos() != null): ":: [MISION] ERROR: Recursos es null";
        
        if (AC.consultaRecursos(consumoCombustible, consumoEnergia,consumoDesgaste)){
            System.out.println("[MISION] Nave lista.");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Nave lista.", id);

            puedeIniciarMision = true;
        }
        else {
            puedeIniciarMision = false;
            System.out.println("[MISION] ERROR: Recursos insuficientes");
            AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] ERROR: Recursos insuficientes", id);
        }
    }
    
    protected abstract void ejecutar();
    
    // EVAULAR: idea
    //  CADA MISION TIENE SU PROPIO INFORME Y DISTINTOS
    //  DATOS A GUARDAR EN BITACORA
        //      Registrar INFORME DE MISION en este paso. Anotar:
        //      -   Mision ejecutada
        //      -   Resultado
        //      -   Acciones principales
        //      -   Recursos consumidos
        //      -   Estado final de la nave
        //      -   Observaciones relevantes
        //      Aplicar diferencias entre el estado inicial y el
        //  estado final para poder registrarlo.
        //  - Agregar Desgaste, disminuir Combustible, energia?
    protected void evaluar(){  
        informe.recursosFinales = AC.getNave().getRecursos();
        System.out.println("[MISION] Evaluando resultados...");
        AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Evaluando resultados...", id);
        

        AC.registrarMision(
            TipoEvento.MISION,
            descripcion,         // agregar descripcion sobre la mision realizada
            id
        );   
        
        informe.imprimirInforme();
        
        System.out.println("[MISION] Cerrando...");
        AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Cerrando...", id);
    }
    
    protected void cerrar(){
        System.out.println("[MISION] Mision terminada.");
        AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] Mision terminada.", id);
        System.out.println("---------------------------------");
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------

    public InformeMision getInforme() {
        return informe;
    }
    
    public Integer getIdMision(){
        return this.idMision;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    


}

