package modelo.persistencia;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestor de conexión a la base de datos SQLite siguiendo los lineamientos de la cátedra:
 * 1) Cargar los Drivers (Class.forName)
 * 2) Hacer una conexión (DriverManager.getConnection)
 * 3) Crear sentencias JDBC (createStatement / prepareStatement)
 * 4) Ejecutar sentencias (execute / executeQuery / executeUpdate)
 * 5) Cerrar conexión (close / try-with-resources)
 */
public class ConexionDBSQLite {
    private static final String DRIVER = "org.sqlite.JDBC";
    private final String url;

    public ConexionDBSQLite(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL de conexión no puede ser nula ni vacía");
        }
        // 1) Cargar el Driver al instanciar la conexión
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se pudo cargar el driver JDBC de SQLite: " + DRIVER, e);
        }
        this.url = url;
        asegurarDirectorio(this.url);
    }

    private void asegurarDirectorio(String jdbcUrl) {
        if (jdbcUrl.startsWith("jdbc:sqlite:")) {
            String path = jdbcUrl.substring("jdbc:sqlite:".length()).trim();
            if (!path.isEmpty() && !path.equals(":memory:")) {
                File archivo = new File(path);
                File padre = archivo.getParentFile();
                if (padre != null && !padre.exists()) {
                    padre.mkdirs();
                }
            }
        }
    }

    // 2) Hacer una conexión
    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(this.url);
    }

    public String getUrl() {
        return this.url;
    }

    /**
     * Inicializa la tabla de usuarios si no existe previamente.
     */
    public void inicializarBase() throws DAOException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "username TEXT NOT NULL UNIQUE, "
                   + "password TEXT NOT NULL)";

        // 2) Conexión, 3) Crear sentencia, 4) Ejecutar sentencia, 5) Cerrar con try-with-resources
        try (Connection conexion = getConexion();
             Statement sentencia = conexion.createStatement()) {
            sentencia.execute(sql);
        } catch (SQLException e) {
            throw new SQLiteDAOException("Error al inicializar la tabla de usuarios", e);
        }
    }

    // 5) Utilidad para cerrar conexión explícitamente como en el apunte de teoría
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }
}
