package vista;

import Controlador.Controlador;
import javax.swing.JTextArea;

public interface InterfazVistaDashboard {
    
    public void setControlador(Controlador c);
    public void setVisible(boolean b);
    
    public void mensajeAlerta(String msg);

    public void getNombreAC();

    public String getTipoNave();

    public String mensajeConTexto(String msg, String title);

    public void addNaveCreada(String nombre, String tipo);

    public String getID();

    public String getOrigen();

    public String getEmploy();

    public String getAntiguedad();

    public void agregaEmpleado(Object[] empleado);
    
    public JTextArea getAreaConsola();

    public String getMision();

    public String getNombreNave();

    public void setRecursos(int combus, int desgas, int energi, int gemas);

    public void limpiaTablaBinnacle();
    public void limpiaTablaCrew();

    public void agregaEvento(Object[] row);

    public JTextArea getAreaReportes();
}
