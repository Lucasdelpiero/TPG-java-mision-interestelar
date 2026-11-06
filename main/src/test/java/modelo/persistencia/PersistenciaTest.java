package modelo.persistencia;

public class PersistenciaTest {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL BLOQUE 1 (PERSISTENCIA) ===");
        try {
            // Pasamos la URL explícitamente desde el Main/llamador
            String urlDB = "jdbc:sqlite:database/interestelar.db";
            ConexionDBSQLite conexion = new ConexionDBSQLite(urlDB);
            System.out.println("1. Conexión instanciada con URL: " + conexion.getUrl());

            // 1. Inicializar esquema
            conexion.inicializarBase();
            System.out.println("2. Esquema inicializado correctamente.");

            UsuarioDAO dao = new UsuarioDAOSQLite(conexion);

            // Generamos un usuario de prueba único
            String usernamePrueba = "martin_" + System.currentTimeMillis();

            // 2. Verificar que el usuario no exista
            boolean existeAntes = dao.existeUsuario(usernamePrueba);
            assert !existeAntes : "El usuario no debería existir aún";
            System.out.println("3. Verificación existeUsuario('" + usernamePrueba + "') antes de registrar: " + existeAntes);

            // 3. Registrar usuario con password plana
            UsuarioDTO usuario = new UsuarioDTO(usernamePrueba, "clave123");
            dao.registrar(usuario);
            System.out.println("4. Usuario registrado exitosamente. ID asignado: " + usuario.getId());
            assert usuario.getId() > 0 : "El ID generado debería ser mayor a 0";

            // 4. Verificar que ahora existe
            boolean existeDespues = dao.existeUsuario(usernamePrueba);
            assert existeDespues : "El usuario debería existir";
            System.out.println("5. Verificación existeUsuario('" + usernamePrueba + "') después de registrar: " + existeDespues);

            // 5. Autenticar con credenciales correctas (ambas sobrecargas)
            boolean loginExitosoDTO = dao.autenticar(new UsuarioDTO(usernamePrueba, "clave123"));
            boolean loginExitosoStrings = dao.autenticar(usernamePrueba, "clave123");
            assert loginExitosoDTO : "La autenticación con DTO falló";
            assert loginExitosoStrings : "La autenticación con Strings falló";
            System.out.println("6. Autenticación con credenciales correctas (DTO y Strings): OK");

            // 6. Autenticar con contraseña incorrecta
            boolean loginFallido = dao.autenticar(new UsuarioDTO(usernamePrueba, "password_invalida"));
            assert !loginFallido : "La autenticación con contraseña incorrecta no debió pasar";
            System.out.println("7. Autenticación con contraseña incorrecta rechazada: OK (retornó " + loginFallido + ")");

            // 7. Intentar registrar duplicado (debe lanzar excepción)
            try {
                dao.registrar(new UsuarioDTO(usernamePrueba, "otra_clave"));
                throw new AssertionError("Debió lanzar SQLiteDAOException por usuario duplicado");
            } catch (DAOException e) {
                System.out.println("8. Rechazo de usuario duplicado verificado: " + e.getMessage());
            }

            System.out.println("=== TODAS LAS PRUEBAS DE PERSISTENCIA COMPLETADAS CON ÉXITO ===");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
