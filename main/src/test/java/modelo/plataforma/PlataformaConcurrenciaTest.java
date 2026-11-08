package modelo.plataforma;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import modelo.plataforma.PlataformaDeCarga;

public class PlataformaConcurrenciaTest {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL BLOQUE 2 (CONCURRENCIA Y PLATAFORMA) ===");
        try {
            int capacidadMaxima = 3;
            PlataformaDeCarga plataforma = new PlataformaDeCarga(capacidadMaxima);
            UniversoParalelo universo = new UniversoParalelo(plataforma);

            List<String> eventosRecibidos = new CopyOnWriteArrayList<>();

            // 1. Registrar observador
            universo.registrarObservador(mensaje -> {
                eventosRecibidos.add(mensaje);
                System.out.println("› " + mensaje);
            });

            // 2. Verificar estado inicial del gestor
            assert universo.getCantGeneradoras() == 0 : "No debería haber generadoras iniciales";
            assert universo.getCantNavesConsumidoras() == 0 : "No debería haber naves iniciales";

            System.out.println("1. Plataforma e hilos inicializados.");

            // 3. Agregar generadoras y naves al universo paralelo
            universo.agregarGeneradora();
            universo.agregarGeneradora();
            universo.agregarNaveConsumidora();

            assert universo.getCantGeneradoras() == 2 : "Debería haber 2 generadoras";
            assert universo.getCantNavesConsumidoras() == 1 : "Debería haber 1 nave";

            System.out.println("2. Entidades lanzadas. Esperando simulación concurrente (3.5 segundos)...");

            // 4. Permitir que los hilos productores y consumidores interactúen
            Thread.sleep(3500);

            // 5. Validaciones post-ejecución
            System.out.println("3. Cantidad de eventos registrados por el observador: " + eventosRecibidos.size());
            assert !eventosRecibidos.isEmpty() : "El observador debió capturar eventos";

            boolean hayIngreso = eventosRecibidos.stream().anyMatch(e -> e.contains("¡Ingresó una nueva"));
            boolean hayProduccion = eventosRecibidos.stream().anyMatch(e -> e.contains("producida"));
            boolean hayDeposito = eventosRecibidos.stream().anyMatch(e -> e.contains("depositada") || e.contains("Depositada"));
            boolean hayRetiro = eventosRecibidos.stream().anyMatch(e -> e.contains("retirada"));

            System.out.println("4. Verificación de tipos de eventos:");
            System.out.println("   - Notificación de ingreso: " + hayIngreso);
            System.out.println("   - Notificación de producción: " + hayProduccion);
            System.out.println("   - Notificación de depósito: " + hayDeposito);
            System.out.println("   - Notificación de retiro: " + hayRetiro);

            assert hayIngreso : "Debe haber eventos de ingreso al universo";
            assert hayProduccion : "Debe haber eventos de producción";
            assert hayDeposito : "Debe haber eventos de depósito";
            assert hayRetiro : "Debe haber eventos de retiro";

            System.out.println("=== TODAS LAS PRUEBAS DE CONCURRENCIA COMPLETADAS CON ÉXITO ===");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
