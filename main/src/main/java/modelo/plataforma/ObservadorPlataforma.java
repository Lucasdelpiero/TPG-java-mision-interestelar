package modelo.plataforma;

// Interfaz que la vista usara para escuchar eventos del backend (de la plataforma de carga)

public interface ObservadorPlataforma {
    void onEvento(String mensaje);
}
