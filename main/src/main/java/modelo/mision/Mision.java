package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento;
import modelo.nave.Recursos;
import modelo.universo.Universo;
import modelo.destinoytablero.Posicion;
import modelo.universo.ElementoDelUniverso;
import java.util.ArrayList;
import modelo.universo.Asteroide;

public abstract class Mision{
    
    public static enum tipoMision {
        INTERCEPCION,
        RECOLECCION,
        RETORNO_SEGURO
    }
    
    protected tipoMision tipo;
    protected String nombreMision = "no asignado";
    protected String descripcion = "no asignado";
    
    protected AsistenteComando AC;
    protected InformeMision informe;
    protected Universo universo;
    
    protected int consumoCombustible = 4;
    protected int consumoDesgaste = 4;
    protected int consumoEnergia = 0;
    protected boolean puedeIniciarMision;
    protected boolean misionCumplida = false;
    protected Integer idMision;
    protected static int idMisionSiguiente = 1;
    protected ArrayList<Posicion> ruta ;
    protected Posicion destino;

    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------

    public Mision(Universo universo){
        this.idMision = idMisionSiguiente++;
        this.universo = universo;
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
        informe = new InformeMision(idMision, nombreMision);
        
        assert (AC.getNave() != null) : ":: [MISION] ERROR: Nave es null";
        assert (AC.getNave().getRecursos() != null) : ":: [MISION] ERROR: Recursos es null";
        destino = obtenerPosicionObjetivo();
        
        if (destino == null) {
            puedeIniciarMision = false;
            this.AC.getBitacora().registrar(TipoEvento.MISION, "[MISION] , No exsiste elemento objetivo en el tablero " + getIdMision());
        }
        else {
            this.calcularRuta(destino);

            Recursos resViejos = AC.getNave().getRecursos();
            informe.recursosIniciales.setCombustible(resViejos.getCombustible());
            informe.recursosIniciales.setEnergia(resViejos.getEnergia());
            informe.recursosIniciales.setDesgaste(resViejos.getDesgaste());

            System.out.println("[MISION] Iniciando mision...");
            AC.registrarMision(TipoEvento.MISION, "[MISION] Iniciando mision...", idMision);

            boolean recursosOk = AC.consultaRecursos(consumoCombustible, consumoEnergia, consumoDesgaste);

            if (!recursosOk) {
                puedeIniciarMision = false;
                System.out.println("[MISION] ERROR: Recursos insuficientes");
                AC.registrarMision(TipoEvento.MISION, "[MISION] ERROR: Recursos insuficientes", idMision);
            } else {
                puedeIniciarMision = true;
                System.out.println("[MISION] Nave lista.");
                AC.registrarMision(TipoEvento.MISION, "[MISION] Nave lista.", idMision);
            }
        }
    }
    
    protected abstract void ejecutar();
    protected abstract Posicion obtenerPosicionObjetivo();
    
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
        Recursos resFinal = AC.getNave().getRecursos();
        informe.recursosFinales = new Recursos(resFinal.getCombustible(), resFinal.getEnergia(), resFinal.getDesgaste());
        System.out.println("[MISION] Evaluando resultados...");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Evaluando resultados: " + descripcion, idMision);
        
        informe.imprimirInforme();
        
        System.out.println("[MISION] Cerrando...");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Cerrando...", idMision);
    }
    
    protected void cerrar(){
        System.out.println("[MISION] Mision terminada.");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Mision terminada.", idMision);
        System.out.println("---------------------------------");
    }
    
    public boolean intentarSaltoWarp(Posicion p){
        return true;
    }
    
    public void choque() {
        
    }
    
    public boolean realizarRecorrido(Posicion objetivo) throws InterruptedException {

        int indice = 0;

        while (!universo.getPosicionNave().equals(objetivo)) {
            ElementoDelUniverso elementoActual =universo.inspeccionarPosicion();

            if (elementoActual != null) {
                choque();
            }
            
            if (indice >= ruta.size()) {
                calcularRuta(objetivo);
                indice = 0;

                if (ruta.isEmpty())
                    return false;
            }
            
            int combustibleNecesario = (ruta.size() - indice) * 2;
            if ( !AC.consultaRecursos(combustibleNecesario, 0, 0))
                return false;
            
            Posicion siguiente = ruta.get(indice);

            if (esZonaSegura(siguiente)) {

                if (!AC.consultaRecursos(2, 0, 0))
                    return false;

                AC.restaCombustible(2);

                universo.moverNave(siguiente);
                indice++;

            } else {

                if (indice + 1 >= ruta.size()) {
                    calcularRuta(objetivo);
                    indice = 0;
                    continue;
                }

                Posicion destinoWarp = ruta.get(indice + 1);

                if (intentarSaltoWarp(destinoWarp)) {
                    indice += 2;
                } else {
                    calcularRuta(objetivo);
                    indice = 0;

                    if (ruta.isEmpty())
                        return false;
                }
            }

            universo.pasarTurnoDestino();

            calcularRuta(objetivo);
            indice = 0;

            Thread.sleep(500);
        }

        return true;
    }
    
    
    public boolean esZonaSegura(Posicion posicion) {

        ElementoDelUniverso[][] tablero = universo.verTablero();

        int n = universo.getX();
        int m = universo.getY();

        int x = posicion.getX();
        int y = posicion.getY();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {

                // Ignorar posiciones fuera del tablero
                if (i < 0 || i >= n || j < 0 || j >= m)
                    continue;

                if (tablero[i][j] instanceof Asteroide)
                    return false;
            }
        }

        return true;
    }

    public void calcularRuta(Posicion objetivo) {

        ElementoDelUniverso[][] tablero = universo.verTablero();

        int n = universo.getX();
        int m = universo.getY();

        Posicion actual = universo.getPosicionNave();

        ruta = new ArrayList<>();

        while (actual.getX() != objetivo.getX()|| actual.getY() != objetivo.getY()) {

            Posicion mejor = null;
            int mejorDistancia = Integer.MAX_VALUE;

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {

                    // No evaluar la propia posición
                    if (dx == 0 && dy == 0)
                        continue;

                    int x = actual.getX() + dx;
                    int y = actual.getY() + dy;

                    // Fuera del tablero
                    if (x < 0 || x >= n || y < 0 || y >= m)
                        continue;

                    // Hay un asteroide
                    if (tablero[x][y] instanceof Asteroide)
                        continue;

                    Posicion candidata = new Posicion(x, y);

                    // Evitar volver por donde ya pasamos
                    if (ruta.contains(candidata) || !esZonaSegura(candidata))
                        continue;

                    int distancia =
                            Math.abs(objetivo.getX() - x)
                          + Math.abs(objetivo.getY() - y);

                    if (distancia < mejorDistancia) {
                        mejorDistancia = distancia;
                        mejor = candidata;
                    }
                }
            }

            // No encontró ningún camino posible
            if (mejor == null) {
                ruta.clear();
                return;
            }

            ruta.add(mejor);
            actual = mejor;
        }
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