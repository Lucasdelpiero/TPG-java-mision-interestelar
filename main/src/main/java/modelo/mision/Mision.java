package modelo.mision;

import modelo.asistente.AsistenteComando;
import modelo.bitacora.Bitacora;
import modelo.bitacora.TipoEvento;
import modelo.nave.Recursos;
import modelo.universo.Universo;
import modelo.destinoytablero.Posicion;
import modelo.universo.ElementoDelUniverso;
import java.util.ArrayList;
import modelo.motorwarp.MotorWarp;
import modelo.nave.Nave;
import modelo.universo.Asteroide;
import modelo.nave.Nave;


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
    /**
     * <b>PRE</b>
     * - AC no nulo y con una nave asignada.
     * <b>POST</b>
     * - Ejecuta la secuencia completa de la misión: preparar, ejecutar, evaluar y cerrar.
     */
    public final void hacerMision(AsistenteComando AC){
        assert (AC != null): "Error: AC es null" ;
        assert (AC.getNave() != null): "Error: AC no tiene nave asignada";
        this.AC = AC;
      
        
        preparar();
        ejecutar();
        evaluar();
        cerrar();
    }
    
    //-------------------------------------------
    //          DESARROLLO MISIONES
    //-------------------------------------------
    
    /**
     * <b>PRE</b>
     * - AC inicializado con nave asignada.
     * <b>POST</b>
     * - Inicializa el informe, calcula la ruta y determina si la nave cuenta con los recursos mínimos para iniciar.
     */
    protected void preparar(){
        informe = new InformeMision(idMision, nombreMision);
        
        assert (AC != null) : ":: [MISION] ERROR: AC es null";
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
    
    
    /**
     * <b>POST</b>
     * - Registra los recursos finales en el informe y emite el balance de la misión.
     */
    protected void evaluar(){
        Recursos resFinal = AC.getNave().getRecursos();
        informe.recursosFinales = new Recursos(resFinal.getCombustible(), resFinal.getEnergia(), resFinal.getDesgaste());
        System.out.println("[MISION] Evaluando resultados...");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Evaluando resultados: " + descripcion, idMision);
        
        informe.imprimirInforme();
        
        System.out.println("[MISION] Cerrando...");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Cerrando...", idMision);
    }
    
    /**
     * <b>POST</b>
     * - Finaliza la misión y notifica la actualización del universo a los observadores.
     */
    protected void cerrar(){
        System.out.println("[MISION] Mision terminada.");
        AC.registrarMision(TipoEvento.MISION, "[MISION] Mision terminada.", idMision);
        System.out.println("---------------------------------");
        universo.notificarObservadores();
    }
    
    /**
     * <b>POST</b>
     * - Retorna true si el motor está Disponible y la nave tiene al menos 5 de energía; false en caso contrario.
     */
    public boolean intentarSaltoWarp(){
        Nave nave = AC.getNave();
        MotorWarp motor = nave.getMotor();
        return motor.estaDisponible() && nave.getRecursos().getEnergia() >= 5;
    }
    
    /**
     * <b>POST</b>
     * - Ejecuta ciclo de 3 turnos (2 preparación, 1 salto, 1 enfriamiento) y avanza 2 casilleros si es posible.
     */
    private boolean realizarSaltoWarp(int indice) throws InterruptedException{
        MotorWarp motor = AC.getNave().getMotor();
        
        // Turno 1 de preparacion
        motor.saltoWarp();
        AC.registrarMision(TipoEvento.MISION, "Motor Warp: Preparando salto (turno 1/2)", idMision);
        universo.pasarTurnoDestino();
        Thread.sleep(100);
        if (!chequearChoque())
            return false;
        
        // Turno 2 de preparacion
        motor.saltoWarp();
        AC.registrarMision(TipoEvento.MISION, "Motor Warp: Preparando salto (turno 2/2)", idMision);
        universo.pasarTurnoDestino();
        Thread.sleep(100);
        if (!chequearChoque())
            return false;
        
        // Ejecucion de Salto Warp (consume 5 de energia y transiciona a Enfriamiento)
        motor.saltoWarp();
        if (!"Enfriamiento".equals(motor.getNombreEstado())) {
            AC.registrarMision(TipoEvento.MISION, "Motor Warp: Abortado por falta de energía. Vuelve a preparación.", idMision);
            return false;
        }
        AC.registrarMision(TipoEvento.MISION, "Motor Warp: ¡Salto Warp ejecutado! Esquivando obstaculo", idMision);
        Posicion p = (indice + 1 < ruta.size()) ? ruta.get(indice + 1) : ruta.get(indice);
        universo.moverNave(p);
        if (!chequearChoque())
            return false;
        
        // Turno de Enfriamiento (consume 3 de desgaste y vuelve a Disponible)
        universo.pasarTurnoDestino();
        Thread.sleep(100);
        if (!chequearChoque())
            return false;
        motor.saltar();
        AC.registrarMision(TipoEvento.MISION, "Motor Warp: Enfriamiento finalizado (+3 desgaste). Motor disponible.", idMision);
        
        return true;
    }
    
    private boolean chequearChoque() {
        ElementoDelUniverso elementoActual = universo.inspeccionarPosicion();

        if (elementoActual != null) {
            choque();
            return !this.AC.getNave().estaInservible();
        }
        return !this.AC.getNave().estaInservible();
    }
    
    public void choque() {
        ElementoDelUniverso elementoActual = universo.inspeccionarPosicion();
        if (elementoActual != null && AC != null && AC.getNave() != null) {
            AC.getNave().choque(elementoActual);
        }
    }
    
    /**
     * <b>PRE</b>
     * - Coordenada objetivo no nula.
     * <b>POST</b>
     * - Desplaza la nave a lo largo de la ruta resolviendo saltos simples o Warp; retorna true si arriba al objetivo con vida o false si falla.
     */
    public boolean realizarRecorrido(Posicion objetivo) throws InterruptedException {
        assert objetivo != null : "El objetivo no puede ser nulo";
        int indice = 0;

    while (!universo.getPosicionNave().equals(objetivo)) {
        
        // 1. Resolver encuentro en la posición actual
        if (!chequearChoque())
            return false;
        
        // 2. Si se terminó la ruta sin llegar, falla
        if (indice >= ruta.size()) {
            return false;
        }

        Posicion siguiente = ruta.get(indice);
        ElementoDelUniverso[][] tablero = universo.verTablero();
        ElementoDelUniverso elementoSiguiente = tablero[siguiente.getX()][siguiente.getY()];

        // 3. SIGUIENTE POSICIÓN LIBRE DE ASTEROIDE (o es el destino)
        if (!(elementoSiguiente instanceof Asteroide) || indice == ruta.size() - 1){

            if (!AC.consultaRecursos(2, 0, 0)) {
                return false;
            }

            AC.restaCombustible(2);

            universo.moverNave(siguiente);
            if (!chequearChoque())
                return false;

            indice++;
        }

        // 4. HAY ASTEROIDE -> WARP
        else {

            if (intentarSaltoWarp() && (AC.getRecursos().getDesgaste() + 3 < 100)) {
                if (!realizarSaltoWarp(indice))
                    return false;
                indice += 2;
            } else {
                return false;
            }
        }

        // 5. TURNO DEL UNIVERSO
        universo.pasarTurnoDestino();

        Thread.sleep(100);
    }
    
    return chequearChoque();
}
    
    

    /**
     * <b>PRE</b>
     * - Coordenada objetivo válida y dentro del tablero.
     * <b>POST</b>
     * - Genera lista de posiciones adyacentes optimizando distancia Manhattan hacia el objetivo.
     */
    public void calcularRuta(Posicion objetivo) {
        assert objetivo != null : "El objetivo no puede ser nulo";
        assert objetivo.getX() >= 0 && objetivo.getX() < universo.getX() && objetivo.getY() >= 0 && objetivo.getY() < universo.getY() : "El objetivo se encuentra fuera de los límites del tablero";

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


                    Posicion candidata = new Posicion(x, y);

                    // Evitar volver por donde ya pasamos
                    if (ruta.contains(candidata))
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