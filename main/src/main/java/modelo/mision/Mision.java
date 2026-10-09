package modelo.mision;

import modelo.asistente.AsistenteComando;

public abstract class Mision{
    
    protected AsistenteComando AC;
    protected InformeMision informe;
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected int consumoEnergia = 0;
    protected boolean misionCumplida = false;
    //-------------------------------------------
    //          CICLO PRINCIPAL
    //-------------------------------------------
    // idea:
    // preparar() y ejecutar() dentro de TRY
    // evaluar() y cerrar() dentro de FINALY

    // Posibles excepciones (ejemplo):
    //  - Nave con recursos insuficientes
    public void hacerMision(AsistenteComando AC){
        preparar(AC);
        ejecutar(AC);
        evaluar(AC);
        cerrar(AC);
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
    protected void preparar(AsistenteComando AC){
        System.out.println("[MISION] Iniciando mision...");
        
        assert (AC.getNave() == null) :    "[MISION] ERROR: Nave es null";
        assert (AC.getNave().getRecursos() == null): "[MISION] ERROR: Recursos es null";
        
        if (AC.consultaRecursos(consumoCombustible, consumoDesgaste))
            System.out.println("[MISION] Nave lista.");
        else {
            System.out.println("[MISION] ERROR: Recursos insuficientes");
        }
    }
    
    protected abstract void ejecutar(AsistenteComando AC);
    
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
    protected abstract void evaluar(AsistenteComando AC);
    
    protected void cerrar(AsistenteComando AC){
        System.out.println("[MISION] Mision terminada.");
    }
    
    

}

/*
//----------------------LUCAS PART-------------------------

//import modelo.nave.Nave;
//import modelo.nave.Recursos;

public abstract class Mision {
    //protected Nave nave;                    // A la nave le consultara sobre los recursos
    //protected EstadoNave estadoNaveInicial, estadoNaveFinal;
    protected AsistenteComando AC;
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected int consumoEnergia = 0;
    protected boolean puedeHacerMision = true;
   
    //-------------------------------------------
    //          CONSTRUCTOR/VALIDADOR
    //-------------------------------------------
    public Mision(AsistenteComando AC) {
        this.AC = AC;
    }

    //-------------------------------------------
    //          CICLO PRINCIPAL
    //-------------------------------------------
    
    public void hacerMision(AsistenteComando AC){
        preparar(AC);
        ejecutar(AC);
        evaluar(AC);
        cerrar(AC);
    }

    //-------------------------------------------
    //          DESARROLLO DE MISIONES
    //-------------------------------------------
    
    
     <b>Pre:</b> Nave != null<br>
     <b>Post:</b> Se guarda en una variable si hay recursos suficientes o no<br>  
      @param AC : Pregunta al Asistente de Comandos la informacion que
      contiene la nave.
     

    public void preparar(AsistenteComando AC){
        assert (AC.getNave() == null) : "Nave es null";
        //Recursos recursos = new Recursos(nave);
        assert (AC.getRecursos() == null): "Recursos es null";

        if (AC.preguntaRecursos(consumoCombustible, consumoDesgaste)){
            System.out.println("Nave lista para comenzar mision");
        }
        else {
            System.out.println("No hay recursos suficientes");
            puedeHacerMision = false;
        }
   }
   
    public abstract void ejecutar(AsistenteComando AC); 
        //{
        //   RESTA DE COMBUSTIBLE?
        //   SUMA DE DESGASTE
        //if (puedeHacerMision)
        //    System.out.println("Se gastaron 4 de combustible y 4 de desgaste");     
        //else
        //    System.out.println("No se pudo ejecutar la mision");
        //}
   
*/
//----------------------------------------------
    /*
        <b>Pre:</b> recursos != null<br>
        <b>Post:</b> devuelve si puede o no hacer la mision<br>  
         @param recursos la cantidad de recursos actual de la nave
    */
    
    /*
    public boolean tieneRecursosSuficientes(Recursos recursos){
       return (recursos.getCombustible() >= consumoCombustible &&
               recursos.getEnergia() >= consumoEnergia  &&
               recursos.getDesgaste() >= consumoDesgaste
               );
    }
    

   
*/
