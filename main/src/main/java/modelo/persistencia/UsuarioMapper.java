package modelo.persistencia;

import modelo.usuario.Usuario;

// Clase utilitaria para pasar de modelo Usuario y el UsuarioDTO y viceversa
public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario u){
        if (u == null) return null;
        return new UsuarioDTO(
                u.getId(),
                u.getUsername(),
                u.getPassword()
        );
    }
    
    public static Usuario toModel(UsuarioDTO dto){
        if (dto == null) return null;
        return new Usuario(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword()
        );
    }
}
