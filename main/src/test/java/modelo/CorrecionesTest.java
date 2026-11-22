package modelo;

import modelo.nave.*;
import modelo.tripulacion.*;
import modelo.motorwarp.*;
import modelo.universo.*;
import modelo.asistente.*;

public class CorrecionesTest {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DE CORRECCIONES ===");

        // 1. Seniority > 999
        System.out.println("Test 1: Seniority con valor > 999");
        Cargo cargo = new FactoryCargo().crearCargo("Teniente");
        Tripulante tripulante = new Tripulante("T-100", cargo, 1500);
        assert tripulante.sueldo() > cargo.sueldoBase() : "El sueldo con antiguedad 1500 debe ser mayor al basico";
        System.out.println("-> OK: Sueldo para seniority 1500 calculado correctamente: " + tripulante.sueldo());

        // 2. CentroDeControl: excepciones cuando no existe nave
        System.out.println("\nTest 2: Excepciones en CentroDeControl para nave inexistente");
        CentroDeControl gcc = new CentroDeControl();
        boolean lanzoExcepcion = false;
        try {
            gcc.getAC("NaveFantasma");
        } catch (IllegalArgumentException e) {
            lanzoExcepcion = true;
            System.out.println("-> OK: Capturada excepcion esperada: " + e.getMessage());
        }
        assert lanzoExcepcion : "Debe lanzar IllegalArgumentException si la nave no existe";

        // 3. Nave inservible y eliminacion
        System.out.println("\nTest 3: Nave inservible y eliminacion");
        Nave naveExploradora = NaveFactory.getNave(NaveFactory.tipoNave.EXPLORADORA);
        assert !naveExploradora.estaInservible() : "Nave recien construida no debe estar inservible";

        // Asignamos tripulacion completa para validar operatividad
        naveExploradora.addTripulante(new Tripulante("C1", new FactoryCargo().crearCargo("Capitan"), 5));
        for (int i = 1; i <= 4; i++) {
            naveExploradora.addTripulante(new Tripulante("T" + i, new FactoryCargo().crearCargo("Teniente"), 2));
        }
        assert naveExploradora.estaOperativa() : "Nave con tripulacion completa y recursos debe estar operativa";

        naveExploradora.getRecursos().setCombustible(0);
        assert naveExploradora.estaInservible() : "Nave sin combustible debe estar inservible";
        assert !naveExploradora.estaOperativa() : "Nave inservible no puede estar operativa";
        naveExploradora.getRecursos().setCombustible(100);
        naveExploradora.getRecursos().setDesgaste(100);
        assert naveExploradora.estaInservible() : "Nave con 100 de desgaste debe estar inservible";

        AsistenteComando ac = new AsistenteComando(naveExploradora, "Enterprise", null);
        gcc.setAC(ac);
        assert gcc.existeNave("Enterprise") : "Enterprise debe existir en GCC";
        gcc.eliminarNave("Enterprise");
        assert !gcc.existeNave("Enterprise") : "Enterprise no debe existir tras ser eliminada";
        System.out.println("-> OK: Deteccion de nave inservible y eliminacion funcionando.");

        // 4. Motor Warp: ciclo y transiciones
        System.out.println("\nTest 4: Motor Warp - Ciclo de 3 turnos (2 preparacion, 1 enfriamiento)");
        Nave naveCombate = NaveFactory.getNave(NaveFactory.tipoNave.COMBATE);
        AsistenteComando acWarp = new AsistenteComando(naveCombate, "Defiant", null);
        MotorWarp motor = naveCombate.getMotor();

        assert motor.estaDisponible() : "Motor debe iniciar Disponible";
        assert motor.getNombreEstado().equals("Disponible") : "Nombre de estado debe ser Disponible";

        // Turno 1 preparacion
        motor.saltoWarp();
        assert motor.getNombreEstado().equals("Preparando Salto") : "Debe transicionar a Preparando Salto";

        // Turno 2 preparacion
        motor.saltoWarp();
        assert motor.getNombreEstado().equals("Salto warp") : "Tras 2 turnos con energia >= 5 debe estar listo en Salto warp";

        // Ejecutar salto warp
        int energiaPrevia = naveCombate.getRecursos().getEnergia();
        motor.saltoWarp(); // Consume 5 de energia y pasa a Enfriamiento
        assert naveCombate.getRecursos().getEnergia() == (energiaPrevia - 5) : "Debe consumir 5 de energia";
        assert motor.getNombreEstado().equals("Enfriamiento") : "Debe transicionar a Enfriamiento";

        // Turno de enfriamiento
        int desgastePrevio = naveCombate.getRecursos().getDesgaste();
        motor.saltar(); // Genera 3 de desgaste y vuelve a Disponible
        assert naveCombate.getRecursos().getDesgaste() == (desgastePrevio + 3) : "Debe generar 3 de desgaste";
        assert motor.estaDisponible() : "Debe volver a Disponible";
        assert motor.getNombreEstado().equals("Disponible") : "Nombre de estado debe ser Disponible";
        System.out.println("-> OK: Motor Warp completo sus 3 turnos de ciclo con costos exactos de energia (-5) y desgaste (+3).");

        // 5. Interaccion con asteroide
        System.out.println("\nTest 5: Choque con asteroide");
        Asteroide asteroide = new Asteroide();
        int energiaAntesAsteroide = naveCombate.getRecursos().getEnergia();
        naveCombate.choque(asteroide);
        assert naveCombate.getRecursos().getEnergia() == (energiaAntesAsteroide - 10) : "Asteroide debe descontar 10 de energia";
        System.out.println("-> OK: Choque con asteroide desconto 10 de energia correctamente.");

        System.out.println("\n=== TODAS LAS PRUEBAS FINALIZARON CON EXITO ===");
    }
}
