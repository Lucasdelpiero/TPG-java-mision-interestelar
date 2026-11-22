package modelo.usuario;

import modelo.persistencia.DAOException;
import modelo.persistencia.UsuarioDAO;
import modelo.persistencia.UsuarioDTO;
import modelo.persistencia.UsuarioMapper;

public class ServicioUsuario {
    private final UsuarioDAO dao;
    
    public ServicioUsuario(UsuarioDAO dao){
        this.dao = dao;
    }
    
    // [PREGUNTAR]:
    // Por estas validaciones, estan bien asi o deberian ir en el constructor del usuario
    // PRO: Queda claro en el servicio
    // CONTRA: Alguien puede llamar new Usuario y olvidarse de validar
    // Mitigacion: Se podria crear una clase factory de usuario y usar esta clase para crear el usuario
    // Y que esta clase llame a una funcion validar
    /**
     * <b>POST</b>
     * - Persiste el nuevo usuario a través del DAO o lanza UsuarioException ante parámetros inválidos, usuario existente o fallo de base.
     */
    public void registrar(String username, String password) throws UsuarioException{
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UsuarioException("Intento de registro con parametros Invalidos");
        }
        try {
            if (dao.obtenerPorUsername(username) != null){
                throw new UsuarioException("Nombre de usuario ya en uso");
            }
            Usuario usuario = new Usuario(username, password);
            UsuarioDTO dto = UsuarioMapper.toDTO(usuario);
            dao.registrar(dto);
        }
        catch(DAOException e){
            // Pasar el error directamente para no perder informacion, ademas tener en cuenta que e.getCause puede dar null si se creo un error sin especificar la causa
            throw new UsuarioException("Error interno al registrar usuario", e);
        }   
    }
    
    /**
     * <b>POST</b>
     * - Retorna true si las credenciales coinciden con un usuario registrado; false si no existe o no coincide; lanza UsuarioException si los datos son inválidos.
     */
    public boolean autenticar(String username, String password) throws UsuarioException {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UsuarioException("Intento de autenticación con parámetros inválidos");
        }
        try {
            UsuarioDTO dto = dao.obtenerPorUsername(username.trim());
            if (dto == null) {
                return false;
            }
            Usuario usuario = UsuarioMapper.toModel(dto);
            return usuario.getPassword().equals(password);
        } catch (DAOException e) {
            throw new UsuarioException("Error interno al autenticar usuario", e);
        }
    }
}
