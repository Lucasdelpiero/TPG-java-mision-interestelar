package modelo.persistencia;

public class UsuarioDTO {
    private int id;
    private String username;
    private String password;

    // Constructor por defecto
    public UsuarioDTO() {
    }

    // Constructor usado para crear un usuario, el id lo asigna la db
    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Constructor usado para obtener de la db toda la data de un usuario ya creado
    public UsuarioDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{id=" + id + ", username='" + username + "'}";
    }
}
