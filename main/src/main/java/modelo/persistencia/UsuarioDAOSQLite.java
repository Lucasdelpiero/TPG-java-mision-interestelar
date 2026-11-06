package modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación concreta de UsuarioDAO para SQLite siguiendo las convenciones de JDBC de la cátedra:
 * - Uso de getConexion()
 * - Sentencias parametrizadas (PreparedStatement) para prevenir inyección SQL
 * - Try-with-resources para garantizar el cierre de conexiones, sentencias y resultados
 * - Hashing seguro mediante SHA-256 (SeguridadUtil)
 */
public class UsuarioDAOSQLite implements UsuarioDAO {

    private final ConexionDBSQLite conexion;

    public UsuarioDAOSQLite(ConexionDBSQLite conexion) {
        if (conexion == null) {
            throw new IllegalArgumentException("La conexión no puede ser nula");
        }
        this.conexion = conexion;
    }

    @Override
    public void registrar(UsuarioDTO usuario) throws DAOException {
        if (usuario == null || usuario.getUsername() == null || usuario.getPassword() == null) {
            throw new IllegalArgumentException("El usuario y sus credenciales no pueden ser nulos");
        }
        if (existeUsuario(usuario.getUsername())) {
            throw new SQLiteDAOException("El nombre de usuario '" + usuario.getUsername() + "' ya está registrado");
        }

        String sql = "INSERT INTO usuarios (username, password) VALUES(?, ?)";
        String passwordHasheada = SeguridadUtil.hashear(usuario.getPassword());

        // 2) Conexión, 3) Crear sentencia, 4) Ejecutar sentencia, 5) Cerrar recursos
        try (Connection conn = conexion.getConexion();
             PreparedStatement sentencia = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, usuario.getUsername()); // 1er '?'
            sentencia.setString(2, passwordHasheada);       // 2do '?'
            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    usuario.setId(resultado.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new SQLiteDAOException("Error al registrar usuario", e);
        }
    }

    @Override
    public boolean existeUsuario(String username) throws DAOException {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String sql = "SELECT 1 FROM usuarios WHERE usuarios.username = ?";
        try (Connection conn = conexion.getConexion();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1, username); // 1er '?'
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next(); // true si hay fila disponible
            }
        } catch (SQLException e) {
            throw new SQLiteDAOException("Error al obtener si el usuario existe", e);
        }
    }
    
    // Sobrecarga
    @Override
    public boolean autenticar(UsuarioDTO usuario) throws DAOException {
        if (usuario == null || usuario.getUsername() == null || usuario.getPassword() == null) {
            return false;
        }
        String sql = "SELECT password FROM usuarios WHERE username = ?";
        try (Connection conn = conexion.getConexion();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1, usuario.getUsername());
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    String hashAlmacenado = resultado.getString("password");
                    return SeguridadUtil.verificar(usuario.getPassword(), hashAlmacenado);
                }
                return false;
            }
        } catch (SQLException e) {
            throw new SQLiteDAOException("Error al autenticar usuario", e);
        }
    }
    
    @Override
    public boolean autenticar(String username, String password) throws DAOException {
        if (username == null || password == null) {
            return false;
        }
        String sql = "SELECT password FROM usuarios WHERE username = ?";
        try (Connection conn = conexion.getConexion();
             PreparedStatement sentencia = conn.prepareStatement(sql)) {
            sentencia.setString(1, username);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    String hashAlmacenado = resultado.getString("password");
                    return SeguridadUtil.verificar(password, hashAlmacenado);
                }
                return false;
            }
        } catch (SQLException e) {
            throw new SQLiteDAOException("Error al autenticar usuario", e);
        }
    }
}
