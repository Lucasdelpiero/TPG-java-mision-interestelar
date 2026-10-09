/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

import java.util.ArrayList;

public class Main {

    private static int correctas = 0;
    private static int fallidas = 0;
    private static int omitidas = 0;
    private static int avisos = 0;
    private static boolean contratosActivos;

    public static void main(String[] args) {
        contratosActivos = Tripulacion.class.desiredAssertionStatus();

        System.out.println("PRUEBAS DE TODOS LOS METODOS DE TRIPULACION");
        System.out.println("Assert de Tripulacion activos: " + contratosActivos);

        // =========================================================
        // 1. CONSTRUCTOR Y ADDTRIPULANTE
        // =========================================================

        probar("Constructor: nave vacia", () -> {
            Tripulacion nave = new Tripulacion();

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "La lista debe estar vacia"
            );

            exigir(
                    nave.getTripulante("Nadie") == null,
                    "La busqueda debe devolver null"
            );

            exigir(
                    nave.toString() != null,
                    "toString no debe devolver null"
            );
        });

        probar("Agregar: cantidad, orden y referencias", () -> {
            Tripulacion nave = new Tripulacion();
            PlanetaOrigen[] personas = ejemplo();

            for (int i = 0; i < personas.length; i++) {
                nave.addTripulante(personas[i]);

                ArrayList<Tripulante> lista =
                        nave.getTripulantesCargo(null);

                exigir(
                        lista.size() == i + 1,
                        "La cantidad debe aumentar en uno"
                );

                for (int j = 0; j <= i; j++) {
                    exigir(
                            lista.get(j) == personas[j].getTripulante(),
                            "Debe conservar el orden y los mismos objetos"
                    );
                }
            }
        });

        // =========================================================
        // 2. GETTRIPULANTE
        // Primero, intermedios, ultimo e inexistente.
        // =========================================================

        for (String id : new String[]{"Juan", "Pedro", "Ana", "Luis"}) {
            probar("Buscar a " + id, () -> {
                Tripulacion nave = crearNave();
                String antes = estado(nave);

                Tripulante resultado = nave.getTripulante(id);

                exigir(
                        resultado != null && resultado.getId().equals(id),
                        "Resultado incorrecto"
                );

                exigir(
                        estado(nave).equals(antes),
                        "Buscar no debe modificar la nave"
                );
            });
        }

        probar("Buscar un identificador inexistente", () -> {
            Tripulacion nave = crearNave();
            String antes = estado(nave);

            exigir(
                    nave.getTripulante("NoExiste") == null,
                    "Debe devolver null"
            );

            exigir(
                    estado(nave).equals(antes),
                    "La nave debe quedar igual"
            );
        });

        // =========================================================
        // 3. GETTRIPULANTESCARGO
        // =========================================================

        for (String cargo : new String[]{
            "Capitan", "Teniente", "Alferez", "Consejero"
        }) {
            probar("Filtrar por " + cargo, () -> {
                Tripulacion nave = crearNave();
                String antes = estado(nave);

                ArrayList<Tripulante> lista =
                        nave.getTripulantesCargo(cargo);

                exigir(
                        lista.size() == 1,
                        "Debe encontrar exactamente uno en este ejemplo"
                );

                exigir(
                        lista.get(0).getCargo()
                                .getCargoTripulante().equals(cargo),
                        "Cargo incorrecto"
                );

                exigir(
                        estado(nave).equals(antes),
                        "Filtrar no debe modificar la nave"
                );
            });
        }

        probar("Filtrar con null devuelve a todos", () -> {
            exigir(
                    crearNave().getTripulantesCargo(null).size() == 4,
                    "Deben aparecer los cuatro"
            );
        });

        probar("Filtrar un cargo sin coincidencias", () -> {
            exigir(
                    crearNave().getTripulantesCargo("Piloto").isEmpty(),
                    "Debe devolver lista vacia"
            );
        });

        probar("Filtrar en una nave vacia", () -> {
            Tripulacion nave = new Tripulacion();

            exigir(
                    nave.getTripulantesCargo("Capitan").isEmpty(),
                    "Debe devolver lista vacia"
            );

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "Todos tambien debe devolver lista vacia"
            );
        });

        probar(
                "La lista devuelta es independiente, sus elementos son los mismos",
                () -> {
                    Tripulacion nave = crearNave();

                    ArrayList<Tripulante> lista =
                            nave.getTripulantesCargo(null);

                    exigir(
                            lista.get(0) == nave.getTripulante("Juan"),
                            "No debe copiar al tripulante"
                    );

                    String antes = estado(nave);

                    // Modificamos la lista que devolvio el metodo.
                    lista.clear();

                    lista.add(
                            crear("Otro", "Alferez", 0, "Terricola")
                                    .getTripulante()
                    );

                    // La coleccion interna de Tripulacion debe seguir igual.
                    exigir(
                            estado(nave).equals(antes),
                            "Cambiar la lista devuelta no debe cambiar la nave"
                    );
                }
        );

        // =========================================================
        // 4. REMOVETRIPULANTE
        // Cada prueba utiliza una nave nueva.
        // =========================================================

        for (String id : new String[]{"Juan", "Pedro", "Ana", "Luis"}) {
            probar("Eliminar a " + id + " y conservar el resto", () -> {
                Tripulacion nave = crearNave();

                ArrayList<Tripulante> esperados =
                        nave.getTripulantesCargo(null);

                esperados.remove(nave.getTripulante(id));

                nave.removeTripulante(id);

                exigir(
                        nave.getTripulante(id) == null,
                        "El tripulante sigue presente"
                );

                exigir(
                        nave.getTripulantesCargo(null).equals(esperados),
                        "Deben quedar los mismos objetos restantes"
                                + " y en el mismo orden"
                );

                for (Tripulante t : esperados) {
                    exigir(
                            t.getAntiguedad()
                                    == antiguedadInicial(t.getId()),
                            "Eliminar no debe cambiar la antiguedad"
                                    + " de los demas"
                    );
                }
            });
        }

        probar("Eliminar al unico tripulante", () -> {
            Tripulacion nave = armar(
                    crear("Solo", "Capitan", 0, "Marciano")
            );

            nave.removeTripulante("Solo");

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "La nave debe quedar vacia"
            );

            exigir(
                    nave.getTripulante("Solo") == null,
                    "La busqueda debe devolver null"
            );
        });

        probar("Eliminar a todos y volver a agregar", () -> {
            Tripulacion nave = crearNave();

            for (String id : new String[]{"Juan", "Pedro", "Ana", "Luis"}) {
                nave.removeTripulante(id);
            }

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "Deben haberse eliminado todos"
            );

            nave.addTripulante(
                    crear("Nuevo", "Teniente", 0, "Vulcano")
            );

            exigir(
                    nave.getTripulantesCargo(null).size() == 1,
                    "Debe poder agregarse otro"
            );

            exigir(
                    nave.getTripulante("Nuevo") != null,
                    "No se encuentra al nuevo tripulante"
            );
        });

        // =========================================================
        // 5. REINICIARPERIODO Y SUELDOS DE LOS DECORADORES
        // =========================================================

        probar("Sueldos iniciales y dos reinicios consecutivos", () -> {
            PlanetaOrigen[] personas = ejemplo();
            Tripulacion nave = armar(personas);

            // Columnas: Juan, Pedro, Ana, Luis.
            // Filas: antes de reiniciar, primer reinicio, segundo reinicio.
            double[][] sueldos = {
                {1418, 466, 222, 746},
                {1618, 478, 223, 770},
                {1818, 490, 224, 800}
            };

            int[] antiguedadesIniciales = {2, 3, 2, 4};

            ArrayList<Tripulante> integrantes =
                    nave.getTripulantesCargo(null);

            for (int periodo = 0; periodo < 3; periodo++) {
                if (periodo > 0) {
                    nave.reiniciarPeriodo();
                }

                exigir(
                        nave.getTripulantesCargo(null).equals(integrantes),
                        "El reinicio debe conservar integrantes y orden"
                );

                for (int i = 0; i < personas.length; i++) {
                    Tripulante t = personas[i].getTripulante();

                    exigir(
                            t.getAntiguedad()
                                    == antiguedadesIniciales[i] + periodo,
                            "Antiguedad incorrecta"
                    );

                    exigir(
                            Math.abs(
                                    personas[i].sueldo()
                                            - sueldos[periodo][i]
                            ) < 0.000001,
                            "Sueldo incorrecto de " + t.getId()
                                    + " en periodo " + periodo
                    );
                }

                Consejero c =
                        (Consejero) personas[3].getTripulante().getCargo();

                exigir(
                        c.getConsejos() == (periodo == 0 ? 3 : 0),
                        "Consejos incorrectos"
                );
            }
        });

        probar("Reiniciar una nave vacia", () -> {
            Tripulacion nave = new Tripulacion();

            nave.reiniciarPeriodo();

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "Debe permanecer vacia"
            );
        });

        probar("Reiniciar una nave sin consejeros", () -> {
            PlanetaOrigen persona =
                    crear("Solo", "Alferez", 2, "Terricola");

            Tripulacion nave = armar(persona);

            nave.reiniciarPeriodo();

            exigir(
                    persona.getTripulante().getAntiguedad() == 3,
                    "Debe aumentar la antiguedad"
            );
        });

        probar("Filtrar y reiniciar varios consejeros", () -> {
            PlanetaOrigen a =
                    crear("Consejero1", "Consejero", 0, "Marciano");

            PlanetaOrigen b =
                    crear("Consejero2", "Consejero", 5, "Vulcano");

            Consejero ca = (Consejero) a.getTripulante().getCargo();
            Consejero cb = (Consejero) b.getTripulante().getCargo();

            ca.addConsejo(2);
            cb.addConsejo(7);

            Tripulacion nave = armar(a, b);

            ArrayList<Tripulante> filtrados =
                    nave.getTripulantesCargo("Consejero");

            exigir(
                    filtrados.size() == 2
                            && filtrados.get(0) == a.getTripulante()
                            && filtrados.get(1) == b.getTripulante(),
                    "Debe devolver ambos en orden"
            );

            nave.reiniciarPeriodo();

            exigir(
                    ca.getConsejos() == 0 && cb.getConsejos() == 0,
                    "Ambos contadores deben quedar en cero"
            );

            exigir(
                    a.getTripulante().getAntiguedad() == 1
                            && b.getTripulante().getAntiguedad() == 6,
                    "Ambas antiguedades deben aumentar"
            );
        });

        // =========================================================
        // 6. TOSTRING
        // =========================================================

        probar("toString de una nave poblada, sin modificar su estado", () -> {
            Tripulacion nave = crearNave();
            String antes = estado(nave);

            String texto = nave.toString();

            exigir(
                    texto != null && texto.contains("Tripulacion"),
                    "Falta la representacion de la nave"
            );

            for (String id : new String[]{"Juan", "Pedro", "Ana", "Luis"}) {
                exigir(
                        texto.contains(id),
                        "Falta " + id + " en el texto"
                );
            }

            exigir(
                    texto.contains("Marciano")
                            && texto.contains("Vulcano")
                            && texto.contains("Terricola"),
                    "Faltan los origenes"
            );

            exigir(
                    estado(nave).equals(antes),
                    "toString no debe modificar los objetos"
            );
        });

        // =========================================================
        // 7. PRECONDICIONES
        // Estas pruebas requieren ejecutar Java con -ea.
        // =========================================================

        for (String id : new String[]{null, "", " ", "\t"}) {
            probarContrato("Buscar con ID invalido: [" + id + "]", () -> {
                Tripulacion nave = crearNave();
                String antes = estado(nave);

                esperar(
                        AssertionError.class,
                        () -> nave.getTripulante(id)
                );

                exigir(
                        estado(nave).equals(antes),
                        "La operacion rechazada debe conservar el estado"
                );
            });

            probarContrato("Eliminar con ID invalido: [" + id + "]", () -> {
                Tripulacion nave = crearNave();
                String antes = estado(nave);

                esperar(
                        AssertionError.class,
                        () -> nave.removeTripulante(id)
                );

                exigir(
                        estado(nave).equals(antes),
                        "No debe eliminar ni modificar integrantes"
                );
            });
        }

        for (String cargo : new String[]{"", " ", "\t"}) {
            probarContrato("Filtrar con cargo en blanco", () -> {
                Tripulacion nave = crearNave();
                String antes = estado(nave);

                esperar(
                        AssertionError.class,
                        () -> nave.getTripulantesCargo(cargo)
                );

                exigir(
                        estado(nave).equals(antes),
                        "La nave debe quedar igual"
                );
            });
        }

        probarContrato(
                "Eliminar un ID inexistente de una nave poblada",
                () -> {
                    Tripulacion nave = crearNave();
                    String antes = estado(nave);

                    esperar(
                            AssertionError.class,
                            () -> nave.removeTripulante("NoExiste")
                    );

                    exigir(
                            estado(nave).equals(antes),
                            "No debe eliminar a otra persona"
                    );
                }
        );

        probarContrato("Eliminar de una nave vacia", () -> {
            Tripulacion nave = new Tripulacion();

            esperar(
                    AssertionError.class,
                    () -> nave.removeTripulante("NoExiste")
            );

            exigir(
                    nave.getTripulantesCargo(null).isEmpty(),
                    "Debe seguir vacia"
            );
        });

        // =========================================================
        // 8. FABRICAS Y CONSTRUCTOR UTILIZADOS POR LAS PRUEBAS
        // =========================================================

        probar("La fabrica rechaza un cargo desconocido", () -> {
            esperar(
                    IllegalArgumentException.class,
                    () -> new FactoryCargo().crearCargo("Piloto")
            );
        });

        probar("La fabrica rechaza un origen desconocido", () -> {
            esperar(
                    IllegalArgumentException.class,
                    () -> crear("Otro", "Capitan", 0, "Jupiter")
            );
        });

        probar("El constructor rechaza una identidad null", () -> {
            esperar(
                    IllegalArgumentException.class,
                    () -> crear(null, "Capitan", 0, "Marciano")
            );
        });

        probar("El constructor rechaza una antiguedad negativa", () -> {
            esperar(
                    IllegalArgumentException.class,
                    () -> crear("Otro", "Capitan", -1, "Marciano")
            );
        });

        // =========================================================
        // 9. DIAGNOSTICOS DE ALTAS INVALIDAS
        //
        // Tu contrato exige objetos validos, pero no especifica
        // como deben rechazarse los invalidos.
        // Por eso estas comprobaciones generan avisos.
        // Cada una usa una nave descartable.
        // =========================================================

        revisarAltaInvalida(
                "addTripulante(null)",
                () -> new Tripulacion().addTripulante(null)
        );

        revisarAltaInvalida(
                "Decorador con Tripulante null",
                () -> new Tripulacion().addTripulante(new Marciano(null))
        );

        revisarAltaInvalida(
                "Tripulante con Cargo null",
                () -> new Tripulacion().addTripulante(
                        new Marciano(
                                new Tripulante("SinCargo", null, 0)
                        )
                )
        );

        // =========================================================
        // 10. OBSERVACIONES SOBRE REFERENCIAS E IDS REPETIDOS
        //
        // No hay una regla de unicidad definida en tu clase.
        // Se muestra el comportamiento sin contarlo como fallo.
        // =========================================================

        System.out.println("\nOBSERVACIONES SOBRE REFERENCIAS REPETIDAS");

        Tripulante compartido =
                crear("Compartido", "Capitan", 2, "Marciano")
                        .getTripulante();

        Tripulacion repetida = armar(
                new Marciano(compartido),
                new Vulcano(compartido)
        );

        repetida.reiniciarPeriodo();

        System.out.println(
                "Mismo tripulante en dos entradas: antiguedad inicial 2, final "
                        + compartido.getAntiguedad()
        );

        PlanetaOrigen primero =
                crear("Repetido", "Capitan", 0, "Marciano");

        PlanetaOrigen segundo =
                crear("Repetido", "Teniente", 0, "Vulcano");

        Tripulacion idsRepetidos = armar(primero, segundo);

        idsRepetidos.removeTripulante("Repetido");

        System.out.println(
                "Tras eliminar un ID repetido, queda otra coincidencia: "
                        + (idsRepetidos.getTripulante("Repetido") != null)
        );

        // =========================================================
        // RESUMEN
        // =========================================================

        System.out.println("\nRESUMEN");
        System.out.println("Correctas: " + correctas);
        System.out.println("Fallidas: " + fallidas);
        System.out.println("Omitidas por falta de -ea: " + omitidas);
        System.out.println("Avisos de validacion: " + avisos);
    }

    // =============================================================
    // METODOS AUXILIARES PARA CREAR DATOS
    // =============================================================

    private static PlanetaOrigen crear(
            String id,
            String cargo,
            int antiguedad,
            String origen
    ) {
        Cargo c = new FactoryCargo().crearCargo(cargo);

        Tripulante t = new Tripulante(id, c, antiguedad);

        return new FactoryPlanetaOrigen()
                .factoryPlanetaOrigen(origen, t);
    }

    private static PlanetaOrigen[] ejemplo() {
        PlanetaOrigen[] personas = {
            crear("Juan", "Capitan", 2, "Marciano"),
            crear("Pedro", "Teniente", 3, "Vulcano"),
            crear("Ana", "Alferez", 2, "Terricola"),
            crear("Luis", "Consejero", 4, "Terricola")
        };

        Consejero consejero =
                (Consejero) personas[3].getTripulante().getCargo();

        consejero.addConsejo(3);

        return personas;
    }

    private static Tripulacion armar(PlanetaOrigen... personas) {
        Tripulacion nave = new Tripulacion();

        for (PlanetaOrigen p : personas) {
            nave.addTripulante(p);
        }

        return nave;
    }

    private static Tripulacion crearNave() {
        return armar(ejemplo());
    }

    private static int antiguedadInicial(String id) {
        if (id.equals("Pedro")) {
            return 3;
        }

        if (id.equals("Luis")) {
            return 4;
        }

        return 2;
    }

    /*
     * Guarda una representacion del estado observable.
     * Permite comprobar que una consulta o una operacion
     * rechazada no modifico la tripulacion.
     */
    private static String estado(Tripulacion nave) {
        StringBuilder resultado = new StringBuilder();

        for (Tripulante t : nave.getTripulantesCargo(null)) {
            Cargo c = t.getCargo();

            resultado.append(t.getId())
                    .append('|')
                    .append(c.getCargoTripulante())
                    .append('|')
                    .append(t.getAntiguedad());

            if (c instanceof Consejero) {
                resultado.append('|')
                        .append(((Consejero) c).getConsejos());
            }

            resultado.append(';');
        }

        return resultado.toString();
    }

    // =============================================================
    // METODOS AUXILIARES PARA EJECUTAR LAS PRUEBAS
    // =============================================================

    /*
     * Ejecuta una prueba.
     * Si falla, registra el problema y permite continuar.
     */
    private static void probar(String nombre, Runnable prueba) {
        try {
            prueba.run();

            correctas++;

            System.out.println("OK: " + nombre);

        } catch (RuntimeException | AssertionError error) {
            fallidas++;

            System.out.println(
                    "FALLO: " + nombre
                            + " -> " + error.getClass().getSimpleName()
                            + ": " + error.getMessage()
            );
        }
    }

    /*
     * Comprueba una condicion.
     * Este control funciona incluso cuando Java se ejecuta sin -ea.
     */
    private static void exigir(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    /*
     * Comprueba que una operacion lance el error esperado.
     *
     * Si no lanza nada, la prueba falla.
     * Si lanza un error de otro tipo, tambien falla.
     */
    private static void esperar(
            Class<? extends Throwable> tipo,
            Runnable operacion
    ) {
        try {
            operacion.run();

        } catch (RuntimeException | AssertionError error) {
            exigir(
                    tipo.isInstance(error),
                    "Se esperaba " + tipo.getSimpleName()
                            + " pero aparecio "
                            + error.getClass().getSimpleName()
            );

            return;
        }

        throw new AssertionError(
                "Se esperaba " + tipo.getSimpleName()
                        + " y no se lanzo"
        );
    }

    /*
     * Ejecuta las pruebas que dependen de los assert de Tripulacion.
     * Si no estan activos, informa que la prueba fue omitida.
     */
    private static void probarContrato(String nombre, Runnable prueba) {
        if (!contratosActivos) {
            omitidas++;

            System.out.println(
                    "OMITIDA: " + nombre + " (requiere -ea)"
            );

            return;
        }

        probar(nombre, prueba);
    }

    /*
     * Observa como se manejan las altas invalidas.
     * No las cuenta como fallos porque el contrato actual
     * no define un mecanismo obligatorio de rechazo.
     */
    private static void revisarAltaInvalida(
            String nombre,
            Runnable operacion
    ) {
        try {
            operacion.run();

            avisos++;

            System.out.println(
                    "AVISO: " + nombre
                            + " fue aceptado; falta una validacion explicita"
            );

        } catch (IllegalArgumentException | AssertionError error) {
            System.out.println(
                    "CONTROL: " + nombre + " fue rechazado"
            );

        } catch (RuntimeException error) {
            avisos++;

            System.out.println(
                    "AVISO: " + nombre
                            + " produjo "
                            + error.getClass().getSimpleName()
            );
        }
    }
}
