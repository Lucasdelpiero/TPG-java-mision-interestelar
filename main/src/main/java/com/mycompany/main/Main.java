package com.mycompany.main;

import java.util.ArrayList;
import modelo.asistente.AsistenteComando;
import modelo.destinoytablero.*;
import modelo.mision.MisionRecoleccion;
import modelo.nave.*;
import modelo.universo.*;

import Controlador.Controlador;
import vista.*;

public class Main {
    public static void main(String[] args) {
        // =====================================================
        // 1. CREAR ELEMENTOS
        // =====================================================
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        Asteroide a1 = new Asteroide();
        Asteroide a2 = new Asteroide();
        EstacionDeCarga e1 = new EstacionDeCarga();
        EstacionDeCarga e2 = new EstacionDeCarga();
        //elementos.add(a1);
        //elementos.add(a2);
        //elementos.add(e1);
        //elementos.add(e2);
        
        for(int i=0; i<20;i++){
            elementos.add(a1);
        }
        
        
        // =====================================================
        // 12. PROBAR UNIVERSO
        // =====================================================
        Nave nave = new Exploradora();
        ArrayList<ElementoDelUniverso> elementosUniverso = new ArrayList<>();
        //elementosUniverso.add(new Asteroide());
        //elementosUniverso.add(new EstacionDeCarga());
        
        Universo universo = new Universo(nave, 50, 50, elementos); // (nave, FILAS, COLUMNAS, elems)

        // =====================================================
        // INICIO VENTANA LOGIN
        // =====================================================
        
        InterfazVista w1 = new winLogin();
        Controlador control = new Controlador(w1, universo);
        
        w1.setControlador(control);
        w1.setVisible(true);
        
    }
}


/*
package com.mycompany.main;

import java.util.ArrayList;

import modelo.asistente.AsistenteComando;
import modelo.destinoytablero.CantidadDeElementosSuperaCasillaException;
import modelo.destinoytablero.Destino;
import modelo.destinoytablero.DimensionesDeTableroInconsistentesException;
import modelo.destinoytablero.Posicion;
import modelo.destinoytablero.Tablero;

import modelo.mision.MisionRecoleccion;

import modelo.nave.Exploradora;
import modelo.nave.Nave;

import modelo.universo.Asteroide;
import modelo.universo.ElementoDelUniverso;
import modelo.universo.EstacionDeCarga;
import modelo.universo.Universo;

public class Main {

    public static void main(String[] args) {

        int ok = 0;
        int error = 0;

        System.out.println("======================================");
        System.out.println("      PRUEBA COMPLETA DEL TABLERO");
        System.out.println("      WARP NO SE PRUEBA");
        System.out.println("======================================");

        try {
            // =====================================================
            // 1. CREAR ELEMENTOS
            // =====================================================
            ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
            Asteroide a1 = new Asteroide();
            Asteroide a2 = new Asteroide();
            EstacionDeCarga e1 = new EstacionDeCarga();
            EstacionDeCarga e2 = new EstacionDeCarga();
            elementos.add(a1);
            elementos.add(a2);
            elementos.add(e1);
            elementos.add(e2);    
            

            

            System.out.println("\n[1] Elementos creados correctamente");
            ok++;


            // =====================================================
            // 2. CREAR TABLERO
            // =====================================================

            Tablero tablero = new Tablero(10, 10, elementos);

            if (tablero.getX() == 10 && tablero.getY() == 10) {
                System.out.println("[2] Dimensiones correctas");
                ok++;
            } else {
                System.out.println("[ERROR] Dimensiones incorrectas");
                error++;
            }


            // =====================================================
            // 3. POSICION INICIAL DE LA NAVE
            // =====================================================

            Posicion inicial = tablero.getPosicionNave();

            System.out.println("[3] Posicion inicial nave: "
                    + inicial.getX() + ", " + inicial.getY());

            if (inicial.equals(new Posicion(5, 5))) {
                System.out.println("    OK: Nave en centro");
                ok++;
            } else {
                System.out.println("    ERROR: Nave no esta en centro");
                error++;
            }


            // =====================================================
            // 4. VER TODO EL TABLERO
            // =====================================================

            ElementoDelUniverso[][] matriz = tablero.verTablero();

            int cantidad = 0;

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {

                    if (matriz[i][j] != null) {
                        cantidad++;

                        System.out.println(
                                "Elemento "
                                + matriz[i][j].getClass().getSimpleName()
                                + " en (" + i + "," + j + ")"
                        );
                    }
                }
            }

            if (cantidad == elementos.size()) {
                System.out.println("[4] Todos los elementos estan en el tablero");
                ok++;
            } else {
                System.out.println("[ERROR] Faltan o sobran elementos");
                error++;
            }


            // =====================================================
            // 5. OBTENER OBJETIVO
            // =====================================================

            Posicion objetivo = tablero.obtenerObjetivo();

            System.out.println(
                    "[5] Objetivo generado: "
                    + objetivo.getX()
                    + ", "
                    + objetivo.getY()
            );

            if (matriz[objetivo.getX()][objetivo.getY()] == null) {
                System.out.println("    OK: objetivo en posicion libre");
                ok++;
            } else {
                System.out.println("    ERROR: objetivo ocupado");
                error++;
            }


            // =====================================================
            // 6. MOVER LA NAVE
            // =====================================================

            Posicion nueva = new Posicion(2, 2);

            tablero.moverNave(nueva);

            if (tablero.getPosicionNave().equals(nueva)) {
                System.out.println("[6] moverNave funciona");
                ok++;
            } else {
                System.out.println("[ERROR] moverNave no funciona");
                error++;
            }


            // =====================================================
            // 7. INSPECCIONAR POSICION VACIA
            // =====================================================

            ElementoDelUniverso encontrado =
                    tablero.inspeccionarPosicion();

            if (encontrado == null) {
                System.out.println("[7] inspeccionarPosicion: posicion vacia OK");
                ok++;
            } else {
                System.out.println(
                        "[7] Hay elemento: "
                        + encontrado.getClass().getSimpleName()
                );
                ok++;
            }


            // =====================================================
            // 8. PROBAR INSPECCION SOBRE UN ELEMENTO
            // =====================================================

            matriz = tablero.verTablero();

            Posicion posicionElemento = null;
            ElementoDelUniverso elementoEsperado = null;

            for (int i = 0; i < matriz.length && posicionElemento == null; i++) {
                for (int j = 0; j < matriz[i].length; j++) {

                    if (matriz[i][j] != null) {

                        posicionElemento = new Posicion(i, j);
                        elementoEsperado = matriz[i][j];

                        break;
                    }
                }
            }

            if (posicionElemento != null) {

                tablero.moverNave(posicionElemento);

                ElementoDelUniverso inspeccion =
                        tablero.inspeccionarPosicion();

                if (inspeccion == elementoEsperado) {
                    System.out.println("[8] inspeccionarPosicion detecta elemento correctamente");
                    ok++;
                } else {
                    System.out.println("[ERROR] inspeccionarPosicion incorrecto");
                    error++;
                }
            }


            // =====================================================
            // 9. OBSERVAR ALREDEDORES
            // =====================================================

            ElementoDelUniverso[][] radar =
                    tablero.observarAlrededores();

            if (radar.length == 3 && radar[0].length == 3) {
                System.out.println("[9] observarAlrededores devuelve 3x3");
                ok++;
            } else {
                System.out.println("[ERROR] Radar no es 3x3");
                error++;
            }

            System.out.println("Radar actual:");

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    if (radar[i][j] == null) {
                        System.out.print("[ VACIO ] ");
                    } else {
                        System.out.print(
                                "[" + radar[i][j]
                                        .getClass()
                                        .getSimpleName() + "] "
                        );
                    }
                }

                System.out.println();
            }


            // =====================================================
            // 10. PROBAR DESTINO
            // =====================================================

            Destino destino = new Destino(tablero);

            int cantidadAntes = 0;

            matriz = tablero.verTablero();

            for (ElementoDelUniverso[] fila : matriz) {
                for (ElementoDelUniverso elem : fila) {
                    if (elem != null)
                        cantidadAntes++;
                }
            }

            for (int turno = 0; turno < 100; turno++) {
                destino.turnoDestino();
            }

            int cantidadDespues = 0;

            matriz = tablero.verTablero();

            for (ElementoDelUniverso[] fila : matriz) {
                for (ElementoDelUniverso elem : fila) {
                    if (elem != null)
                        cantidadDespues++;
                }
            }

            if (cantidadAntes == cantidadDespues) {
                System.out.println("[10] Destino no pierde ni duplica elementos");
                ok++;
            } else {
                System.out.println("[ERROR] Destino altero cantidad de elementos");
                error++;
            }


            // =====================================================
            // 11. PROBAR MUCHOS TURNOS
            // =====================================================

            boolean fueraTablero = false;

            for (int turno = 0; turno < 500; turno++) {

                destino.turnoDestino();

                ElementoDelUniverso[][] estado =
                        tablero.verTablero();

                if (estado.length != 10
                        || estado[0].length != 10) {
                    fueraTablero = true;
                    break;
                }
            }

            if (!fueraTablero) {
                System.out.println("[11] 500 turnos sin errores de limites");
                ok++;
            } else {
                System.out.println("[ERROR] Error de limites");
                error++;
            }


            // =====================================================
            // 12. PROBAR UNIVERSO
            // =====================================================

            Nave nave = new Exploradora();

            ArrayList<ElementoDelUniverso> elementosUniverso =
                    new ArrayList<>();

            elementosUniverso.add(new Asteroide());
            elementosUniverso.add(new EstacionDeCarga());

            Universo universo =
                    new Universo(
                            nave,
                            10,
                            10,
                            elementosUniverso
                    );

            if (universo.getNave() == nave) {
                System.out.println("[12] Universo conserva la nave");
                ok++;
            } else {
                System.out.println("[ERROR] Universo no conserva la nave");
                error++;
            }


            // =====================================================
            // 13. UNIVERSO - POSICION
            // =====================================================

            Posicion posUniverso =
                    universo.getPosicionNave();

            System.out.println(
                    "[13] Posicion nave Universo: "
                    + posUniverso.getX()
                    + ","
                    + posUniverso.getY()
            );

            ok++;


            // =====================================================
            // 14. UNIVERSO - MOVER
            // =====================================================

            Posicion pruebaMovimiento =
                    new Posicion(4, 4);

            universo.moverNave(pruebaMovimiento);

            if (universo.getPosicionNave()
                    .equals(pruebaMovimiento)) {

                System.out.println("[14] Universo.moverNave funciona");
                ok++;

            } else {

                System.out.println("[ERROR] Universo.moverNave");
                error++;
            }


            // =====================================================
            // 15. UNIVERSO - PASAR TURNO
            // =====================================================

            universo.pasarTurnoDestino();

            System.out.println("[15] pasarTurnoDestino ejecutado sin errores");
            ok++;


            // =====================================================
            // 16. RUTA SIN WARP
            // =====================================================

            
             // Para esta prueba usamos un universo sin elementos
             // para garantizar que nunca se intente Warp.
             

            Nave naveRuta = new Exploradora();

            Universo universoRuta =
                    new Universo(
                            naveRuta,
                            7,
                            7,
                            new ArrayList<>()
                    );

            MisionRecoleccion mision =
                    new MisionRecoleccion(universoRuta);

            Posicion objetivoRuta =
                    mision.obtenerPosicionObjetivo();

            mision.calcularRuta(objetivoRuta);

            System.out.println(
                    "[16] Ruta calculada hasta: "
                    + objetivoRuta.getX()
                    + ","
                    + objetivoRuta.getY()
            );

            ok++;


            // =====================================================
            // 17. RECORRIDO NORMAL
            // =====================================================

            AsistenteComando asistente =
                    new AsistenteComando(
                            naveRuta,
                            "TEST",
                            universoRuta
                    );

            
             // Si AC es protected y tu misión lo asigna en hacerMision(),
             //podés probar directamente la misión completa.
             
             // No agregamos asteroides para que NUNCA se intente Warp.
             

            System.out.println(
                    "[17] Combustible antes: "
                    + naveRuta.getRecursos().getCombustible()
            );

            asistente.realizarMision("RECOLECCION");

            System.out.println(
                    "Combustible despues: "
                    + naveRuta.getRecursos().getCombustible()
            );

            System.out.println(
                    "Energia despues: "
                    + naveRuta.getRecursos().getEnergia()
            );

            System.out.println(
                    "Desgaste despues: "
                    + naveRuta.getRecursos().getDesgaste()
            );

            System.out.println(
                    "Gemas despues: "
                    + naveRuta.getRecursos().getGemas()
            );

            System.out.println("[17] Mision Recoleccion ejecutada");
            ok++;


            // =====================================================
            // 18. DIMENSIONES INVALIDAS
            // =====================================================

            boolean exceptionCorrecta = false;

            try {

                Tablero invalido =
                        new Tablero(
                                3,
                                3,
                                new ArrayList<>()
                        );

            } catch (DimensionesDeTableroInconsistentesException ex) {

                exceptionCorrecta = true;
            }

            if (exceptionCorrecta) {
                System.out.println("[18] Dimensiones invalidas rechazadas");
                ok++;
            } else {
                System.out.println("[ERROR] Acepto dimensiones invalidas");
                error++;
            }


            // =====================================================
            // RESULTADO
            // =====================================================

            System.out.println("\n======================================");
            System.out.println("RESULTADO FINAL");
            System.out.println("OK: " + ok);
            System.out.println("ERRORES: " + error);
            System.out.println("======================================");

        }

        catch (Exception e) {

            System.out.println("\nERROR GENERAL:");
            e.printStackTrace();
        }
        
        // =====================================================
        // 19. SIN COMBUSTIBLE SUFICIENTE
        // =====================================================

        Nave naveSinCombustible = new Exploradora();
        naveSinCombustible.getRecursos().setCombustible(3);

        Universo universoSinCombustible =
                new Universo(
                        naveSinCombustible,
                        7,
                        7,
                        new ArrayList<>()
                );

        AsistenteComando acSinCombustible =
                new AsistenteComando(
                        naveSinCombustible,
                        "TEST-SIN-COMBUSTIBLE",
                        universoSinCombustible
                );

        Posicion posicionAntes =
                universoSinCombustible.getPosicionNave();

        acSinCombustible.realizarMision("RECOLECCION");

        Posicion posicionDespues =
                universoSinCombustible.getPosicionNave();

        if (posicionAntes.equals(posicionDespues)) {
            System.out.println("[19] OK: no se movio sin recursos suficientes");
            ok++;
        } else {
            System.out.println("[ERROR] Se movio sin recursos suficientes");
            error++;
        }
        
        // =====================================================
        // 20. RETORNO SEGURO
        // =====================================================

        Nave naveRetorno = new Exploradora();

        Universo universoRetorno =
                new Universo(
                        naveRetorno,
                        7,
                        7,
                        new ArrayList<>()
                );

        universoRetorno.moverNave(new Posicion(1, 1));

        naveRetorno.getRecursos().setCombustible(40);
        naveRetorno.getRecursos().setEnergia(30);
        naveRetorno.getRecursos().setDesgaste(50);

        AsistenteComando acRetorno =
                new AsistenteComando(
                        naveRetorno,
                        "TEST-M3",
                        universoRetorno
                );

        acRetorno.realizarMision("RETORNO_SEGURO");

        Posicion base = new Posicion(
                universoRetorno.getX() / 2,
                universoRetorno.getY() / 2
        );

        boolean llego =
                universoRetorno.getPosicionNave().equals(base);

        boolean recargada =
                naveRetorno.getRecursos().getCombustible() == 100
                && naveRetorno.getRecursos().getEnergia() == 100
                && naveRetorno.getRecursos().getDesgaste() == 0;

        if (llego && recargada) {
            System.out.println("[20] OK: retorno seguro correcto");
            ok++;
        } else {
            System.out.println("[ERROR] Retorno seguro incorrecto");
            error++;
        }
        
        // =====================================================
        // 21. M-01 - RECURSOS ALTOS: DEBERIA PODER COMPLETARSE
        // =====================================================

        Nave naveM1Alta = new Exploradora();

        naveM1Alta.getRecursos().setEnergia(100);
        naveM1Alta.getRecursos().setCombustible(100);

        ArrayList<ElementoDelUniverso> elementosM1Alta = new ArrayList<>();

        // Necesitamos una nave objetivo
        elementosM1Alta.add(new Exploradora());

        Universo universoM1Alta =
                new Universo(
                        naveM1Alta,
                        7,
                        7,
                        elementosM1Alta
                );

        AsistenteComando acM1Alta =
                new AsistenteComando(
                        naveM1Alta,
                        "TEST-M1-ALTA",
                        universoM1Alta
                );

        int energiaAntesAlta =
                naveM1Alta.getRecursos().getEnergia();

        int combustibleAntesAlta =
                naveM1Alta.getRecursos().getCombustible();

        acM1Alta.realizarMision("INTERCEPCION");

        int energiaDespuesAlta =
                naveM1Alta.getRecursos().getEnergia();

        int combustibleDespuesAlta =
                naveM1Alta.getRecursos().getCombustible();

        if (energiaDespuesAlta < energiaAntesAlta
                || combustibleDespuesAlta < combustibleAntesAlta) {

            System.out.println(
                    "[21] OK: M-01 pudo entregar recursos"
            );

            ok++;

        } else {

            System.out.println(
                    "[ERROR] M-01 no entrego ningun recurso"
            );

            error++;
        }


        // =====================================================
        // 22. M-01 - SOLO ENERGIA DISPONIBLE
        // =====================================================

        Nave naveM1Energia = new Exploradora();

        naveM1Energia.getRecursos().setEnergia(100);

        // Dejamos combustible muy bajo
        naveM1Energia.getRecursos().setCombustible(5);

        ArrayList<ElementoDelUniverso> elementosM1Energia =
                new ArrayList<>();

        elementosM1Energia.add(new Exploradora());

        Universo universoM1Energia =
                new Universo(
                        naveM1Energia,
                        7,
                        7,
                        elementosM1Energia
                );

        AsistenteComando acM1Energia =
                new AsistenteComando(
                        naveM1Energia,
                        "TEST-M1-SOLO-ENERGIA",
                        universoM1Energia
                );

        int energiaAntes =
                naveM1Energia.getRecursos().getEnergia();

        acM1Energia.realizarMision("INTERCEPCION");

        int energiaDespues =
                naveM1Energia.getRecursos().getEnergia();

        if (energiaDespues < energiaAntes) {

            System.out.println(
                    "[22] OK: M-01 pudo completar usando energia"
            );

            ok++;

        } else {

            System.out.println(
                    "[22] Revisar: no se transfirio energia"
            );
        }


        // =====================================================
        // 23. M-01 - SIN RECURSOS PARA ENTREGAR
        // =====================================================

        Nave naveM1SinRecursos = new Exploradora();

        naveM1SinRecursos.getRecursos().setEnergia(6);
        naveM1SinRecursos.getRecursos().setCombustible(5);

        ArrayList<ElementoDelUniverso> elementosM1Sin =
                new ArrayList<>();

        elementosM1Sin.add(new Exploradora());

        Universo universoM1Sin =
                new Universo(
                        naveM1SinRecursos,
                        7,
                        7,
                        elementosM1Sin
                );

        AsistenteComando acM1Sin =
                new AsistenteComando(
                        naveM1SinRecursos,
                        "TEST-M1-SIN-RECURSOS",
                        universoM1Sin
                );

        acM1Sin.realizarMision("INTERCEPCION");

        // M-01 consume inicialmente:
        // 4 combustible y 5 energia
        // Por lo tanto, si NO entrega recursos adicionales,
        // debe quedar exactamente en:
        // combustible = 1
        // energia = 1

        int energiaFinal =
                naveM1SinRecursos.getRecursos().getEnergia();

        int combustibleFinal =
                naveM1SinRecursos.getRecursos().getCombustible();

        if (energiaFinal == 1 && combustibleFinal == 1) {

            System.out.println(
                    "[23] OK: M-01 fallo y no entrego recursos adicionales"
            );

            ok++;

        } else {

            System.out.println(
                    "[ERROR] M-01 desconto recursos de asistencia cuando no podia"
            );

            error++;
        }
    }
}
*/