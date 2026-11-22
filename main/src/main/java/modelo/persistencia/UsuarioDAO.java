package modelo.persistencia;

public interface UsuarioDAO {
    /**
     * <b>POST</b>
     * - Inserta el registro del usuario en la base de persistencia o lanza excepción si es inválido, duplicado o falla la base.
     */
    void registrar(UsuarioDTO usuario) throws DAOException;

    /**
     * <b>POST</b>
     * - Retorna true si las credenciales coinciden con la base de datos; false si el usuario es nulo o no coincide.
     */
    boolean autenticar(UsuarioDTO usuario) throws DAOException;

    /**
     * <b>POST</b>
     * - Retorna true si coincide el usuario y clave en la base de datos; false si son nulos o no coinciden.
     */
    boolean autenticar(String username, String password) throws DAOException;

    /**
     * <b>POST</b>
     * - Retorna el DTO del usuario encontrado o null si no existe o el username es nulo/vacío.
     */
    UsuarioDTO obtenerPorUsername(String username) throws DAOException;
}
