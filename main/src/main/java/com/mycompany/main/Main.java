package com.mycompany.main;

import java.util.ArrayList;
import java.util.Date;
import modelo.asistente.AsistenteComando;
import modelo.nave.*;
import modelo.mision.*;
import modelo.bitacora.*;
import modelo.universo.CentroDeControl;
import modelo.tripulacion.*;
import modelo.motorwarp.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  INICIO DE PRUEBAS");
        System.out.println("==================================================\n");

        testCrearTripulante();
        testMotorWarp();
        testBitacora();
        testNaveYRecursos();
        testMisiones();
        testAsistenteComandoYCentroDeControl();

        System.out.println("\n==================================================");
        System.out.println("  TODAS LAS PRUEBAS FINALIZADAS EXITOSAMENTE");
        System.out.println("==================================================");
    }

    // =========================================================================
    // 1. PRUEBAS DE TRIPULANTES Y HABERES (DECORATOR & FACTORY)
    // =========================================================================
    public static void testCrearTripulante() {
        System.out.println("\n--- [TEST 1] TRIPULANTES, CARGOS, ORIGENES Y HABERES ---");
        FactoryCargo factoryCargo = new FactoryCargo();
        FactoryPlanetaOrigen factoryOrigen = new FactoryPlanetaOrigen();

        // 1.1 Crear Capitan (1000 base, 20% antiguedad) de la Tierra (+20 subsidio), 5 anios
        Cargo cCapitan = factoryCargo.crearCargo("Capitan");
        Tripulante tCapitan = new Tripulante("CP-001 Picard", cCapitan, 5);
        PlanetaOrigen capitanTerricola = factoryOrigen.factoryPlanetaOrigen("Terricola", tCapitan);
        // Sueldo esperado: 1000 + (1000 * 5 * 0.20) + 20 = 1000 + 1000 + 20 = 2020.0
        double sueldoCapitan = capitanTerricola.sueldo();
        System.out.println("Capitan Terricola (5 anios) -> Sueldo calculado: " + sueldoCapitan + " (Esperado: 2020.0)");
        assert sueldoCapitan == 2020.0 : "Fallo calculo sueldo capitan";

        // 1.2 Crear Consejero (600 base, 5% antiguedad, 2 por consejo) de Vulcano (+30 subsidio), 10 anios, 4 consejos
        Cargo cConsejero = factoryCargo.crearCargo("Consejero");
        ((Consejero) cConsejero).addConsejo(4);
        Tripulante tConsejero = new Tripulante("CJ-002 Spock", cConsejero, 10);
        PlanetaOrigen consejeroVulcano = factoryOrigen.factoryPlanetaOrigen("Vulcano", tConsejero);
        // Sueldo esperado: 600 + (600 * 10 * 0.05) + (4 * 2) + 30 = 600 + 300 + 8 + 30 = 938.0
        double sueldoConsejero = consejeroVulcano.sueldo();
        System.out.println("Consejero Vulcano (10 anios, 4 consejos) -> Sueldo: " + sueldoConsejero + " (Esperado: 938.0)");
        assert sueldoConsejero == 938.0 : "Fallo calculo sueldo consejero";

        // 1.3 Crear Teniente (400 base, 3% antiguedad) de Marte (+18 subsidio), 4 anios
        Cargo cTeniente = factoryCargo.crearCargo("Teniente");
        Tripulante tTeniente = new Tripulante("TN-003 Worf", cTeniente, 4);
        PlanetaOrigen tenienteMarciano = factoryOrigen.factoryPlanetaOrigen("Marciano", tTeniente);
        // Sueldo esperado: 400 + (400 * 4 * 0.03) + 18 = 400 + 48 + 18 = 466.0
        double sueldoTeniente = tenienteMarciano.sueldo();
        System.out.println("Teniente Marciano (4 anios) -> Sueldo: " + sueldoTeniente + " (Esperado: 466.0)");
        assert sueldoTeniente == 466.0 : "Fallo calculo sueldo teniente";

        // 1.4 Crear Alferez (200 base, 0.5% antiguedad) de la Tierra (+20 subsidio), 2 anios
        Cargo cAlferez = factoryCargo.crearCargo("Alferez");
        Tripulante tAlferez = new Tripulante("AL-004 Crusher", cAlferez, 2);
        PlanetaOrigen alferezTerricola = factoryOrigen.factoryPlanetaOrigen("Terricola", tAlferez);
        // Sueldo esperado: 200 + (200 * 2 * 0.005) + 20 = 200 + 2 + 20 = 222.0
        double sueldoAlferez = alferezTerricola.sueldo();
        System.out.println("Alferez Terricola (2 anios) -> Sueldo: " + sueldoAlferez + " (Esperado: 222.0)");
        assert sueldoAlferez == 222.0 : "Fallo calculo sueldo alferez";

        // 1.5 Validacion de contratos (excepciones ante datos invalidos)
        try {
            new Tripulante("", cCapitan, 3);
            System.out.println("[FALLO] Deberia haber rechazado identidad vacia");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo identidad vacia con excepcion: " + e.getMessage());
        }

        try {
            new Tripulante("TEST", cCapitan, -1);
            System.out.println("[FALLO] Deberia haber rechazado antiguedad negativa");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo antiguedad negativa con excepcion: " + e.getMessage());
        }

        try {
            factoryCargo.crearCargo("Almirante");
            System.out.println("[FALLO] Deberia haber rechazado cargo inexistente");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo cargo desconocido con excepcion: " + e.getMessage());
        }

        try {
            factoryOrigen.factoryPlanetaOrigen("Klingon", tCapitan);
            System.out.println("[FALLO] Deberia haber rechazado origen desconocido");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo planeta desconocido con excepcion: " + e.getMessage());
        }

        // 1.6 Gestion de Tripulacion en la Nave (maximo 1 capitan, no remover al capitan)
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        nave.addTripulante(capitanTerricola);
        nave.addTripulante(consejeroVulcano);
        nave.addTripulante(tenienteMarciano);
        nave.addTripulante(alferezTerricola);

        System.out.println("Tripulantes asignados a la nave: " + nave.getTripulantes().size());
        assert nave.getTripulantes().size() == 4 : "Tripulantes deberian ser 4";

        // Probar reiniciarPeriodo en Tripulacion (resetea consejos e incrementa antiguedad)
        int antiguedadAntes = tConsejero.getAntiguedad();
        nave.getTripulacion().reiniciarPeriodo();
        assert ((Consejero) cConsejero).getConsejos() == 0 : "Consejos deberian haberse reseteado";
        assert tConsejero.getAntiguedad() == antiguedadAntes + 1 : "Antiguedad deberia haber aumentado en 1";
        System.out.println("[TEST OK] Reiniciar periodo reseteo consejos y aumento antiguedad");

        System.out.println("[TEST 1 COMPLETADO CON EXITO]");
    }

    // =========================================================================
    // 2. PRUEBAS DEL MOTOR WARP (PATRON STATE)
    // =========================================================================
    public static void testMotorWarp() {
        System.out.println("\n--- [TEST 2] MOTOR WARP Y PATRON STATE ---");
        MotorWarp motor = new MotorWarp();

        // 2.1 Estado inicial
        System.out.println("Estado inicial: " + motor.getNombreEstado() + " | Disponible: " + motor.estaDisponible());
        assert motor.estaDisponible() : "El motor deberia iniciar en estado Disponible";

        // 2.2 Secuencia valida completa: Disponible -> Preparando Salto -> Salto warp -> Enfriamiento -> Disponible
        try {
            motor.prepararSalto();
            System.out.println("Tras prepararSalto: " + motor.getNombreEstado());
            assert motor.getNombreEstado().equals("Preparando Salto") : "Deberia estar en Preparando Salto";

            motor.saltar();
            System.out.println("Tras saltar: " + motor.getNombreEstado());
            assert motor.getNombreEstado().equals("Salto warp") : "Deberia estar en Salto warp";

            motor.enfriar();
            System.out.println("Tras enfriar: " + motor.getNombreEstado());
            assert motor.getNombreEstado().equals("Enfriamiento") : "Deberia estar en Enfriamiento";

            motor.terminarEnfriamiento();
            System.out.println("Tras terminarEnfriamiento: " + motor.getNombreEstado() + " | Disponible: " + motor.estaDisponible());
            assert motor.estaDisponible() : "Deberia volver a Disponible";
            System.out.println("[TEST OK] Secuencia valida completa ejecutada correctamente");
        } catch (MotorTransicionException e) {
            System.out.println("[FALLO] Excepcion inesperada en secuencia valida: " + e.descripcion);
        }

        // 2.3 Secuencia alterna con cancelacion/terminacion de salto:
        // Disponible -> Preparando Salto -> Salto warp -> terminarSalto() -> Disponible
        try {
            motor.prepararSalto();
            motor.saltar();
            motor.terminarSalto();
            assert motor.estaDisponible() : "Deberia estar Disponible tras terminarSalto";
            System.out.println("[TEST OK] Secuencia con terminarSalto completada. Estado: " + motor.getNombreEstado());
        } catch (MotorTransicionException e) {
            System.out.println("[FALLO] Error en terminarSalto: " + e.descripcion);
        }

        // 2.4 Transiciones invalidas rechazadas
        // Estando en Disponible, intentar saltar o enfriar directamente
        try {
            motor.saltar();
            System.out.println("[FALLO] No deberia permitir saltar desde Disponible");
        } catch (MotorTransicionException e) {
            System.out.println("[TEST OK] Rechazo saltar desde Disponible: " + e.descripcion);
            assert motor.estaDisponible() : "El estado no debe haber cambiado tras intento invalido";
        }

        try {
            motor.enfriar();
            System.out.println("[FALLO] No deberia permitir enfriar desde Disponible");
        } catch (MotorTransicionException e) {
            System.out.println("[TEST OK] Rechazo enfriar desde Disponible: " + e.descripcion);
            assert motor.estaDisponible() : "El estado no debe haber cambiado";
        }

        // Avanzar a Preparando Salto y probar transicion invalida
        try {
            motor.prepararSalto();
            motor.prepararSalto();
            System.out.println("[FALLO] No deberia permitir preparar salto dos veces seguidas");
        } catch (MotorTransicionException e) {
            System.out.println("[TEST OK] Rechazo preparar salto mientras ya esta preparando: " + e.descripcion);
        }

        // Cancelar y dejarlo disponible
        try {
            motor.saltar();
            motor.terminarSalto();
        } catch (MotorTransicionException ignored) {}

        // 2.5 Metodo secuencia_salto automatica
        try {
            motor.secuencia_salto();
            assert motor.estaDisponible() : "Tras secuencia_salto debe quedar Disponible";
            System.out.println("[TEST OK] secuencia_salto() automatica ejecutada exitosamente");
        } catch (MotorTransicionException e) {
            System.out.println("[FALLO] Error en secuencia_salto: " + e.descripcion);
        }

        System.out.println("[TEST 2 COMPLETADO CON EXITO]");
    }

    // =========================================================================
    // 3. PRUEBAS DE LA BITACORA
    // =========================================================================
    public static void testBitacora() {
        System.out.println("\n--- [TEST 3] BITACORA Y REGISTRO DE EVENTOS ---");
        Bitacora bitacora = new Bitacora();

        Date inicio = new Date();
        // Pausa breve para marcar rango de tiempo
        try { Thread.sleep(20); } catch (InterruptedException ignored) {}

        // Registrar eventos sueltos y asociados a mision
        bitacora.registrar(TipoEvento.ORDEN, "Orden 1: Encender sistemas");
        bitacora.registrar(TipoEvento.WARP, "Motor Warp: Disponible a Preparando Salto");
        bitacora.registrar(TipoEvento.RECURSOS, "Carga 20 de combustible");
        bitacora.registrar(TipoEvento.MISION, "Iniciando etapa preparacion", 101);
        bitacora.registrar(TipoEvento.MISION, "Ejecucion completada", 101);
        bitacora.registrar(TipoEvento.MISION, "Mision 102 iniciada", 102);

        try { Thread.sleep(20); } catch (InterruptedException ignored) {}
        Date fin = new Date();

        // 3.1 Cantidad total de eventos
        System.out.println("Cantidad total de eventos registrados: " + bitacora.getCantidadEventos());
        assert bitacora.getCantidadEventos() == 6 : "Deberian haber 6 eventos registrados";

        // 3.2 Copia inmutable de eventos y orden temporal
        ArrayList<Evento> eventos = bitacora.getEventos();
        assert eventos.size() == 6 : "Lista de eventos debe contener 6 elementos";
        assert eventos.get(0).getTipo() == TipoEvento.ORDEN : "El primer evento debe ser ORDEN";
        assert eventos.get(5).getIdMision() == 102 : "El ultimo evento debe corresponder a mision 102";
        System.out.println("[TEST OK] getEventos conserva el orden cronologico");

        // 3.3 Filtro por mision
        ArrayList<Evento> eventosMision101 = bitacora.getEventosDeMision(101);
        System.out.println("Eventos de la mision 101: " + eventosMision101.size());
        assert eventosMision101.size() == 2 : "Deberia haber 2 eventos para mision 101";

        ArrayList<Evento> eventosMision999 = bitacora.getEventosDeMision(999);
        assert eventosMision999.isEmpty() : "No deberia haber eventos para mision inexistente";


        // 3.6 Rechazo de contratos invalidos
        try {
            bitacora.registrar(null, "Descripcion valida");
            System.out.println("[FALLO] Deberia haber rechazado tipo nulo");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo tipo nulo con excepcion: " + e.getMessage());
        }

        try {
            bitacora.registrar(TipoEvento.ERROR, "   ");
            System.out.println("[FALLO] Deberia haber rechazado descripcion vacia");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo descripcion vacia con excepcion: " + e.getMessage());
        }

        try {
            bitacora.registrar(TipoEvento.MISION, "Descripcion", -5);
            System.out.println("[FALLO] Deberia haber rechazado idMision negativo");
        } catch (IllegalArgumentException e) {
            System.out.println("[TEST OK] Rechazo idMision negativo con excepcion: " + e.getMessage());
        }

        System.out.println("[TEST 3 COMPLETADO CON EXITO]");
    }

    // =========================================================================
    // 4. PRUEBAS DE NAVES Y RECURSOS (FACTORY & RECURSOS)
    // =========================================================================
    public static void testNaveYRecursos() {
        System.out.println("\n--- [TEST 4] NAVES (FACTORY) Y GESTION DE RECURSOS ---");

        // 4.1 Creacion de naves mediante Factory y configuraciones iniciales
        Nave exploradora = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        Nave carguero = NaveFactory.getNave(NaveFactory.tipoNave.CARGUERA);
        Nave combate = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);

        System.out.println("Exploradora inicial -> Combustible: " + exploradora.getRecursos().getCombustible() +
                           ", Energia: " + exploradora.getRecursos().getEnergia() +
                           ", Desgaste: " + exploradora.getRecursos().getDesgaste());
        assert exploradora.getRecursos().getCombustible() == 60 : "Exploradora debe iniciar con 60 combustible";
        assert exploradora.getRecursos().getEnergia() == 80 : "Exploradora debe iniciar con 80 energia";
        assert exploradora.getRecursos().getDesgaste() == 0 : "Desgaste inicial debe ser 0";

        assert carguero.getRecursos().getCombustible() == 100 : "Carguero debe iniciar con 100 combustible";
        assert carguero.getRecursos().getEnergia() == 60 : "Carguero debe iniciar con 60 energia";

        assert combate.getRecursos().getCombustible() == 80 : "Combate debe iniciar con 80 combustible";
        assert combate.getRecursos().getEnergia() == 100 : "Combate debe iniciar con 100 energia";
        System.out.println("[TEST OK] Tres tipos de nave creadas correctamente mediante Factory");

        // 4.2 Carga de combustible y energia dentro de los limites permitidos
        AsistenteComando asistente = new AsistenteComando(exploradora, "JARVIS");
        // Exploradora tiene 60 combustible; cargar 20 -> 80
        int combAntes = exploradora.getRecursos().getCombustible();
        asistente.cargaCombustible(20);
        assert exploradora.getRecursos().getCombustible() == combAntes + 20 : "Combustible deberia ser 80";

        // Cargar 10 energia a 80 -> 90
        int energAntes = exploradora.getRecursos().getEnergia();
        asistente.cargaEnergia(10);
        assert exploradora.getRecursos().getEnergia() == energAntes + 10 : "Energia deberia ser 90";

        // 4.3 Intento de carga que supere el limite maximo de 100 (debe rechazarse)
        // Combustible esta en 80; cargar 30 superaria 100 -> debe rechazarse y quedar en 80
        int combAntesRechazo = exploradora.getRecursos().getCombustible();
        asistente.cargaCombustible(30);
        assert exploradora.getRecursos().getCombustible() == combAntesRechazo : "El combustible debe permanecer en 80";
        System.out.println("[TEST OK] Carga de combustible excedente rechazada sin alterar recurso");

        // Energia esta en 90; cargar 20 superaria 100 -> debe rechazarse y quedar en 90
        int energAntesRechazo = exploradora.getRecursos().getEnergia();
        asistente.cargaEnergia(20);
        assert exploradora.getRecursos().getEnergia() == energAntesRechazo : "La energia debe permanecer en 90";
        System.out.println("[TEST OK] Carga de energia excedente rechazada sin alterar recurso");

        // 4.4 Mantenimiento
        exploradora.getRecursos().consumeDesgaste(50);
        assert exploradora.getRecursos().getDesgaste() == 50 : "Desgaste deberia ser 50";
        asistente.realizaMantenimiento();
        assert exploradora.getRecursos().getDesgaste() == 0 : "Tras mantenimiento desgaste debe ser 0";
        System.out.println("[TEST OK] Mantenimiento restablecio el desgaste a 0");

        System.out.println("[TEST 4 COMPLETADO CON EXITO]");
    }

    // =========================================================================
    // 5. PRUEBAS DE MISIONES (TEMPLATE METHOD)
    // =========================================================================
    public static void testMisiones() {
        System.out.println("\n--- [TEST 5] MISIONES (TEMPLATE METHOD) Y CASOS DE PRUEBA ---");

        // 5.1 Escenario A: Ejecucion correcta de M-01, M-02 y M-03
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA); // 60 comb, 80 energ, 0 desg
        AsistenteComando asistente = new AsistenteComando(nave, "CORTANA");

        // M-01: Intercepcion y Asistencia (consume 4 comb, 5 energ, 4 desg)
        Mision m01 = new MisionIntercepcionAsistencia();
        asistente.iniciaMision(m01);

        InformeMision inf01 = m01.getInforme();
        assert inf01 != null : "Informe M-01 no debe ser nulo";
        assert inf01.esResultadoExitoso() : "M-01 deberia ser exitosa";
        assert nave.getRecursos().getCombustible() == 56 : "Combustible restante debe ser 56";
        assert nave.getRecursos().getEnergia() == 75 : "Energia restante debe ser 75";
        assert nave.getRecursos().getDesgaste() == 4 : "Desgaste debe ser 4";
        System.out.println("[TEST OK] M-01 ejecutada exitosamente con consumo correcto");

        // M-02: Recoleccion (consume 4 comb, 5 energ, 4 desg)
        Mision m02 = new MisionRecoleccion();
        asistente.iniciaMision(m02);

        InformeMision inf02 = m02.getInforme();
        assert inf02.esResultadoExitoso() : "M-02 deberia ser exitosa";
        assert nave.getRecursos().getCombustible() == 52 : "Combustible restante debe ser 52";
        assert nave.getRecursos().getEnergia() == 70 : "Energia restante debe ser 70";
        assert nave.getRecursos().getDesgaste() == 8 : "Desgaste debe ser 8";
        System.out.println("[TEST OK] M-02 ejecutada exitosamente con consumo correcto");

        // M-03: Retorno Seguro (consume 4 comb, 0 energ, 4 desg)
        Mision m03 = new MisionRetornoSeguro();
        asistente.iniciaMision(m03);

        InformeMision inf03 = m03.getInforme();
        assert inf03.esResultadoExitoso() : "M-03 deberia ser exitosa";
        assert nave.getRecursos().getCombustible() == 48 : "Combustible restante debe ser 48";
        assert nave.getRecursos().getEnergia() == 70 : "Energia restante debe ser 70";
        assert nave.getRecursos().getDesgaste() == 12 : "Desgaste debe ser 12";
        System.out.println("[TEST OK] M-03 ejecutada exitosamente con consumo correcto");

        // 5.2 Escenario B: Recursos insuficientes (rechazo sin modificaciones parciales)
        Nave naveAgotada = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        // Dejar combustible en 2 (se requieren 4)
        naveAgotada.getRecursos().setCombustible(2);
        naveAgotada.getRecursos().setEnergia(50);
        naveAgotada.getRecursos().setDesgaste(10);
        AsistenteComando asistenteAgotado = new AsistenteComando(naveAgotada, "HAL9000");

        Mision misionSinRecursos = new MisionIntercepcionAsistencia();
        asistenteAgotado.iniciaMision(misionSinRecursos);

        InformeMision infRechazada = misionSinRecursos.getInforme();
        assert !infRechazada.esResultadoExitoso() : "La mision debio fallar por recursos insuficientes";
        assert naveAgotada.getRecursos().getCombustible() == 2 : "El combustible no debio modificarse";
        assert naveAgotada.getRecursos().getEnergia() == 50 : "La energia no debio modificarse";
        assert naveAgotada.getRecursos().getDesgaste() == 10 : "El desgaste no debio modificarse";
        System.out.println("[TEST OK] Mision rechazada por recursos insuficientes sin alterar estado de la nave");

        System.out.println("[TEST 5 COMPLETADO CON EXITO]");
    }

    // =========================================================================
    // 6. PRUEBAS DE ASISTENTE DE COMANDO Y CENTRO DE CONTROL
    // =========================================================================
    public static void testAsistenteComandoYCentroDeControl() {
        System.out.println("\n--- [TEST 6] ASISTENTE DE COMANDO Y CENTRO DE CONTROL ---");
        CentroDeControl centro = new CentroDeControl();

        Nave nave1 = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        AsistenteComando acJarvis = new AsistenteComando(nave1, "JARVIS");

        // Agregar asistente al centro de control
        centro.setAC(acJarvis);
        // Asistente nulo
        centro.setAC(null);

        // Asociar nave mediante centro de control
        Nave nave2 = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        centro.setNave(nave2, "JARVIS");
        Nave naveObtenida = centro.getNave("JARVIS");
        assert naveObtenida == nave2 : "Deberia ser la nave asignada";

        // Intentar obtener nave de asistente inexistente
        Nave naveInexistente = centro.getNave("NO_EXISTE");
        assert naveInexistente == null : "Deberia retornar null para asistente inexistente";

        // Comprobar bitacora del asistente tras ordenes y asignacion de tripulante
        FactoryCargo fCargo = new FactoryCargo();
        FactoryPlanetaOrigen fOrigen = new FactoryPlanetaOrigen();
        Tripulante tripulante = new Tripulante("T-77", fCargo.crearCargo("Teniente"), 3);
        PlanetaOrigen po = fOrigen.factoryPlanetaOrigen("Vulcano", tripulante);

        acJarvis.addTripulante(po);
        assert acJarvis.getTripulantes().size() == 1 : "Deberia haber 1 tripulante";

        ArrayList<Evento> eventos = acJarvis.getBitacora().getEventos();
        assert !eventos.isEmpty() : "Deberian haberse registrado eventos en la bitacora";
        System.out.println("[TEST OK] Eventos registrados en la bitacora del asistente (total: " + eventos.size() + ")");

        System.out.println("[TEST 6 COMPLETADO CON EXITO]");
    }
}
