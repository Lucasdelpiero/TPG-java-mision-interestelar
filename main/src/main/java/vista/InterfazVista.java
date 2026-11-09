package vista;

import Controlador.Controlador;

public interface InterfazVista {
    public void setControlador(Controlador c);
    public void setVisible(boolean b);

    public String getUsername();
    public String getPassword();
    public String getConfPassword();

    public void mensajeAlerta(String msg);
}
