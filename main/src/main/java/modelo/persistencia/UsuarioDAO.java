package modelo.persistencia;

public interface UsuarioDAO {
    void registrar(UsuarioDTO usuario) throws DAOException;
    boolean autenticar(UsuarioDTO usuario) throws DAOException;
    boolean autenticar(String username, String password) throws DAOException;
    boolean existeUsuario(String username) throws DAOException;
}
