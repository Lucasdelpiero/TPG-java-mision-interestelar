package modelo.persistencia;

import modelo.usuario.ServicioUsuario;
import modelo.usuario.UsuarioException;

public class PersistenciaTest {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL BLOQUE 1 (PERSISTENCIA Y SERVICIO) ===");
        try {
            // Pasamos la URL explícitamente desde el Main/llamador
            String urlDB = "jdbc:sqlite:database/interestelar.db";
            ConexionDBSQLite conexion = new ConexionDBSQLite(urlDB);
            System.out.println("1. Conexión instanciada con URL: " + conexion.getUrl());

            // 1. Inicializar esquema
            conexion.inicializarBase();
            System.out.println("2. Esquema inicializado correctamente.");

            UsuarioDAO dao = new UsuarioDAOSQLite(conexion);
            ServicioUsuario servicio = new ServicioUsuario(dao);

            // Generamos usuarios de prueba únicos
            String usernameDAO = "martin_dao_" + System.currentTimeMillis();
            String usernameServicio = "martin_srv_" + System.currentTimeMillis();

            // 2. Probar DAO directo
            UsuarioDTO usuarioDTO = new UsuarioDTO(usernameDAO, "clave123");
            dao.registrar(usuarioDTO);
            assert usuarioDTO.getId() > 0 : "El ID generado debería ser mayor a 0";
            System.out.println("3. DAO registrar OK. ID: " + usuarioDTO.getId());

            UsuarioDTO obtenido = dao.obtenerPorUsername(usernameDAO);
            assert obtenido != null : "obtenerPorUsername debió encontrar el usuario";
            assert obtenido.getUsername().equals(usernameDAO) : "El username no coincide";
            System.out.println("4. DAO obtenerPorUsername OK: " + obtenido.getUsername());

            // 3. Probar ServicioUsuario (BusinessObject): Registro
            servicio.registrar(usernameServicio, "pass456");
            System.out.println("5. Servicio registrar OK: " + usernameServicio);

            // 4. Probar ServicioUsuario: Autenticar correcto
            boolean loginOk = servicio.autenticar(usernameServicio, "pass456");
            assert loginOk : "La autenticación correcta debió retornar true";
            System.out.println("6. Servicio autenticar exitoso: " + loginOk);

            // 5. Probar ServicioUsuario: Autenticar incorrecto
            boolean loginFail = servicio.autenticar(usernameServicio, "pass_erronea");
            assert !loginFail : "La autenticación con clave errónea debió retornar false";
            System.out.println("7. Servicio autenticar clave errónea rechazada: OK");

            // 6. Probar ServicioUsuario: Autenticar usuario inexistente
            boolean loginInexistente = servicio.autenticar("no_existe_" + System.currentTimeMillis(), "pass456");
            assert !loginInexistente : "Autenticar usuario inexistente debió retornar false";
            System.out.println("8. Servicio autenticar usuario inexistente: OK (retornó false)");

            // 7. Probar ServicioUsuario: Rechazo de usuario duplicado (UsuarioException)
            try {
                servicio.registrar(usernameServicio, "otraClave");
                throw new AssertionError("Debió lanzar UsuarioException por usuario duplicado");
            } catch (UsuarioException e) {
                System.out.println("9. Servicio rechazó usuario duplicado correctamente: " + e.getMessage());
            }

            // 8. Probar ServicioUsuario: Parámetros inválidos
            try {
                servicio.registrar("", "123");
                throw new AssertionError("Debió lanzar UsuarioException por username vacío");
            } catch (UsuarioException e) {
                System.out.println("10. Servicio rechazó parámetros inválidos correctamente: " + e.getMessage());
            }

            System.out.println("=== TODAS LAS PRUEBAS DE PERSISTENCIA COMPLETADAS CON ÉXITO ===");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
