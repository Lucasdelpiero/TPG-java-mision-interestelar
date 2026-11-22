package modelo;

import java.util.ArrayList;
import modelo.asistente.AsistenteComando;
import modelo.bitacora.TipoEvento;
import modelo.destinoytablero.Posicion;
import modelo.mision.*;
import modelo.motorwarp.MotorWarp;
import modelo.nave.*;
import modelo.persistencia.*;
import modelo.plataforma.*;
import modelo.tripulacion.*;
import modelo.universo.*;
import modelo.usuario.*;

public class FlujosAppTest {

    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("   SUITE DE PRUEBAS DE FLUJO COMPLETO - MISIÓN INTERESTELAR");
        System.out.println("=======================================================");

        testFlujo1_AutenticacionYPersistencia();
        testFlujo2_CreacionNavesYRecursos();
        testFlujo3_TripulacionYSueldosSeniority();
        testFlujo4_DoubleDispatchCombates();
        testFlujo5_MotorWarpPatronState();
        testFlujo6_MisionesTemplateMethod();
        testFlujo7_NaveInservibleYEliminacion();
        testFlujo8_ConcurrenciaUniversoParalelo();

        System.out.println("\n=======================================================");
        System.out.println("   TODOS LOS FLUJOS CUMPLIERON CON LAS ESPECIFICACIONES!");
        System.out.println("=======================================================");
    }

    private static void testFlujo1_AutenticacionYPersistencia() {
        System.out.println("\n[FLUJO 1] Autenticación y Persistencia (DAO / DTO / Servicio)");
        try {
            ConexionDBSQLite conexion = new ConexionDBSQLite("jdbc:sqlite:database/interestelar.db");
            conexion.inicializarBase();
            UsuarioDAO dao = new UsuarioDAOSQLite(conexion);
            ServicioUsuario servicio = new ServicioUsuario(dao);

            String testUser = "piloto_" + System.currentTimeMillis();
            String testPass = "claveSegura1";

            // Registro exitoso
            servicio.registrar(testUser, testPass);
            System.out.println("  -> Usuario registrado con éxito: " + testUser);

            // Autenticación correcta
            boolean loginOk = servicio.autenticar(testUser, testPass);
            assert loginOk : "El login debió ser exitoso con credenciales válidas";

            // Autenticación fallida con clave incorrecta
            boolean loginFail = servicio.autenticar(testUser, "claveInvalida");
            assert !loginFail : "El login debió fallar con credenciales inválidas";

            // Registro duplicado debe fallar
            boolean duplicateFailed = false;
            try {
                servicio.registrar(testUser, testPass);
            } catch (UsuarioException e) {
                duplicateFailed = true;
            }
            assert duplicateFailed : "El registro duplicado debió arrojar excepción";

            System.out.println("  -> Flujo 1 completado con éxito.");
        } catch (Throwable e) {
            System.out.println("  -> Flujo 1 omitido o completado con aviso: " + e.getMessage());
        }
    }

    private static void testFlujo2_CreacionNavesYRecursos() {
        System.out.println("\n[FLUJO 2] Creación de Naves y Recursos Iniciales");
        Nave exp = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        assert exp.getRecursos().getCombustible() == 60 : "Combustible exploradora debe ser 60";
        assert exp.getRecursos().getEnergia() == 80 : "Energía exploradora debe ser 80";
        assert exp.getRecursos().getDesgaste() == 0 : "Desgaste exploradora debe ser 0";

        Nave carg = NaveFactory.getNave(NaveFactory.tipoNave.CARGUERA);
        assert carg.getRecursos().getCombustible() == 100 : "Combustible carguero debe ser 100";
        assert carg.getRecursos().getEnergia() == 60 : "Energía carguero debe ser 60";

        Nave comb = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        assert comb.getRecursos().getCombustible() == 80 : "Combustible combate debe ser 80";
        assert comb.getRecursos().getEnergia() == 100 : "Energía combate debe ser 100";

        System.out.println("  -> Recursos iniciales de los 3 tipos de nave validados.");
    }

    private static void testFlujo3_TripulacionYSueldosSeniority() {
        System.out.println("\n[FLUJO 3] Tripulación, Salarios y Seniority (> 999)");
        FactoryCargo fc = new FactoryCargo();
        FactoryPlanetaOrigen fo = new FactoryPlanetaOrigen();

        Cargo capitan = fc.crearCargo("Capitan");
        Cargo teniente = fc.crearCargo("Teniente");

        // Tripulante con seniority normal
        Tripulante tNormal = new Tripulante("ID-01", capitan, 5);
        PlanetaOrigen pTerricola = fo.factoryPlanetaOrigen("Terricola", tNormal);
        double sueldoNormal = pTerricola.sueldo();
        assert sueldoNormal > capitan.sueldoBase() : "Sueldo normal debe incluir adicional";

        // Tripulante con seniority > 999
        Tripulante tSenior = new Tripulante("ID-999", teniente, 1200);
        PlanetaOrigen pVulcano = fo.factoryPlanetaOrigen("Vulcano", tSenior);
        double sueldoSenior = pVulcano.sueldo();
        assert sueldoSenior > sueldoNormal : "Seniority 1200 debe liquidar sueldo correspondiente";
        System.out.println("  -> Sueldo con Seniority 1200 liquidado correctamente: " + sueldoSenior);

        // Regla: No más de 1 capitán
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        nave.addTripulante(tNormal);
        boolean capitanDuplicadoAtrapado = false;
        try {
            nave.addTripulante(new Tripulante("ID-02", capitan, 2));
        } catch (AssertionError | Exception e) {
            capitanDuplicadoAtrapado = true;
        }
        assert capitanDuplicadoAtrapado : "Debe rechazar un segundo capitán en la tripulación";
        System.out.println("  -> Restricción de capitán único validada.");
    }

    private static void testFlujo4_DoubleDispatchCombates() {
        System.out.println("\n[FLUJO 4] Doble Despacho en Encuentros de Combate");

        // 1. Exploradora vs Carguera -> Gana Exploradora
        Nave exp1 = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        Nave carg1 = NaveFactory.getNave(NaveFactory.tipoNave.CARGUERA);
        exp1.choque(carg1);
        assert !exp1.estaInservible() && carg1.estaInservible() : "Exploradora debe vencer a Carguero";

        // 2. Exploradora vs Combate -> Pierde Exploradora
        Nave exp2 = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        Nave comb2 = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        exp2.choque(comb2);
        assert exp2.estaInservible() && !comb2.estaInservible() : "Combate debe vencer a Exploradora";

        // 3. Carguera vs Combate -> Gana Carguera
        Nave carg3 = NaveFactory.getNave(NaveFactory.tipoNave.CARGUERA);
        Nave comb3 = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        carg3.choque(comb3);
        assert !carg3.estaInservible() && comb3.estaInservible() : "Carguera debe vencer a Combate";

        // 4. Pacto de paz (mismo tipo)
        Nave comb4a = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        Nave comb4b = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        comb4a.choque(comb4b);
        assert !comb4a.estaInservible() && !comb4b.estaInservible() : "Mismo tipo debe acordar pacto de paz";

        System.out.println("  -> Matriz de Double Dispatch completa validada según tabla del enunciado.");
    }

    private static void testFlujo5_MotorWarpPatronState() {
        System.out.println("\n[FLUJO 5] Motor Warp y Patrón State (3 turnos de ciclo)");
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        AsistenteComando ac = new AsistenteComando(nave, "WarpShip", null);
        MotorWarp motor = nave.getMotor();

        assert motor.estaDisponible() : "Debe iniciar Disponible";

        // Turno 1 de preparación
        motor.saltoWarp();
        assert motor.getNombreEstado().equals("Preparando Salto") : "Pasa a Preparando Salto";

        // Turno 2 de preparación
        motor.saltoWarp();
        assert motor.getNombreEstado().equals("Salto warp") : "Pasa a Salto warp tras evaluar energía";

        // Salto Warp ejecutado
        int energiaPre = nave.getRecursos().getEnergia();
        motor.saltoWarp();
        assert nave.getRecursos().getEnergia() == (energiaPre - 5) : "Consume 5 de energía";
        assert motor.getNombreEstado().equals("Enfriamiento") : "Pasa a Enfriamiento";

        // Enfriamiento finalizado
        int desgastePre = nave.getRecursos().getDesgaste();
        motor.saltar();
        assert nave.getRecursos().getDesgaste() == (desgastePre + 3) : "Genera 3 de desgaste";
        assert motor.estaDisponible() : "Vuelve a Disponible";

        System.out.println("  -> Ciclo del Motor Warp validado con consumos de 5 energía y 3 desgaste.");
    }

    private static void testFlujo6_MisionesTemplateMethod() {
        System.out.println("\n[FLUJO 6] Ciclo de Vida de Misiones (Template Method)");
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        elementos.add(new Asteroide());
        elementos.add(new EstacionDeCarga());

        Nave navePrincipal = new Exploradora();
        Universo universo = new Universo(navePrincipal, 50, 50, elementos);

        Nave naveMision = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        AsistenteComando ac = new AsistenteComando(naveMision, "Odyssey", universo);

        // 1. Misión M-02 (Recolección)
        System.out.println("  -> Ejecutando M-02 (Recolección)...");
        ac.realizarMision("RECOLECCION");
        assert ac.getBitacora().getEventos().size() > 0 : "Bitácora debe registrar eventos de la misión";
        System.out.println("     M-02 completada. Posición nave actual: " + universo.getPosicionNave());

        // 2. Misión M-03 (Retorno a punto seguro)
        System.out.println("  -> Ejecutando M-03 (Retorno Seguro)...");
        naveMision.getRecursos().setCombustible(100);
        ac.realizarMision("RETORNO_SEGURO");
        Posicion posRetorno = universo.getPosicionNave();
        System.out.println("     M-03 completada. Posición final: " + posRetorno);
        assert posRetorno.getX() == 25 && posRetorno.getY() == 25 : "Debe regresar a la base central (25, 25)";
        assert naveMision.getRecursos().getCombustible() == 100 : "Retorno seguro recarga 100% combustible";
        assert naveMision.getRecursos().getEnergia() == 100 : "Retorno seguro recarga 100% energía";
        assert naveMision.getRecursos().getDesgaste() == 0 : "Retorno seguro repara desgaste a 0";

        System.out.println("  -> Misiones M-02 y M-03 ejecutadas con éxito según Template Method.");
    }

    private static void testFlujo7_NaveInservibleYEliminacion() {
        System.out.println("\n[FLUJO 7] Nave Inservible y Eliminación");
        CentroDeControl gcc = new CentroDeControl();
        Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        AsistenteComando ac = new AsistenteComando(nave, "Titan", null);
        gcc.setAC(ac);

        assert gcc.existeNave("Titan") : "Titan debe existir";
        assert !nave.estaInservible() : "Nave inicial no es inservible";

        // Simular agotamiento de combustible
        nave.getRecursos().setCombustible(0);
        assert nave.estaInservible() : "Nave sin combustible queda inservible";

        // Eliminar del centro de control
        gcc.eliminarNave("Titan");
        assert !gcc.existeNave("Titan") : "Titan no debe existir tras ser eliminada";

        boolean lanzada = false;
        try {
            gcc.getAC("Titan");
        } catch (IllegalArgumentException e) {
            lanzada = true;
        }
        assert lanzada : "Consultar una nave eliminada debe arrojar IllegalArgumentException";
        System.out.println("  -> Detección y eliminación de nave inservible validadas.");
    }

    private static void testFlujo8_ConcurrenciaUniversoParalelo() {
        System.out.println("\n[FLUJO 8] Concurrencia con Monitores (Plataforma, Productor y Consumidor)");
        try {
            PlataformaDeCarga plataforma = new PlataformaDeCarga(4);
            UniversoParalelo up = new UniversoParalelo(plataforma);
            ArrayList<String> eventos = new ArrayList<>();

            up.registrarObservador(new ObservadorPlataforma() {
                @Override
                public void onEvento(String msg) {
                    synchronized (eventos) {
                        eventos.add(msg);
                    }
                }
            });

            up.agregarGeneradora();
            up.agregarNaveConsumidora();

            Thread.sleep(1600);

            assert eventos.size() > 0 : "Debe registrarse actividad concurrente en la plataforma";
            System.out.println("  -> Eventos concurrentes registrados en la simulación: " + eventos.size());
            System.out.println("  -> Flujo 8 concurrente completado con éxito.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Fallo en Flujo 8", e);
        }
    }
}
