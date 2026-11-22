/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo.tripulacion;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class Tripulacion {
    private final int MIN_CAPITANES = 1;
    private final int MIN_TRIPULACION_TOTAL = 5; // 1 Capitan + 4 de otro cargo
    
    private ArrayList<Haber> tripulantes;

    public ArrayList<Haber> getTripulantes() {
        return tripulantes;
    }
    
    public Tripulacion() {
        this.tripulantes = new ArrayList<>();
    }
    
    public void matarTripulacion(){
        tripulantes.clear();
    }
    
    /**
     * <b>POST</b>
     * - Retorna true si posee al menos 1 Capitán y al menos 5 tripulantes en total.
     */
    public boolean esOperativa(){
        ArrayList<Tripulante> capitanes = getTripulantesCargo("Capitan");
        return (capitanes.size() >= MIN_CAPITANES) && (tripulantes.size() >= MIN_TRIPULACION_TOTAL) ;
    }
    
    /**
     * <b>PRE</b>
     * - h no nulo y contiene un Tripulante válido.
     * <b>POST</b>
     * - Agrega h al final de la lista incrementando la tripulación.
     */
    public void addTripulante(Haber h) {
        assert h != null : "Error: tripulante es null";
        assert h.getTripulante() != null : "El haber debe contener un tripulante válido";
        assert getTripulante(h.getTripulante().getId()) == null : "Identificador de tripulante ya existente";
        if (h.getTripulante().getCargo().getCargoTripulante().equalsIgnoreCase("Capitan")) {
            assert getTripulantesCargo("Capitan").isEmpty() : "Solo se permite un Capitan en la tripulacion";
        }
        tripulantes.add(h);
    }
    
    /**
     * <b>PRE</b>
     * - id no nulo ni vacío, existente en la tripulación.
     * <b>POST</b>
     * - Remueve la primera coincidencia del tripulante con ese id.
     */
    public void removeTripulante(String id) {
        assert id != null && !id.isBlank() : "El identificador es invalido";
        int i = 0 , n = this.tripulantes.size();
        while (i < n && !this.tripulantes.get(i).getTripulante().getId().equals(id)) {
            i ++;
        }
        assert i < n : "El tripulante con ese identificador no se encuentra en esta nave";
        
        this.tripulantes.remove(i);
    }
    
    /**
     * <b>PRE</b>
     * - cargo no nulo ni vacío.
     * <b>POST</b>
     * - Retorna lista con los tripulantes que desempeñan el cargo solicitado.
     */
    public ArrayList<Tripulante> getTripulantesCargo(String cargo) {
        Cargo c;
        Tripulante t;
        ArrayList<Tripulante> tripulantesCargo = new ArrayList<>();
        assert cargo != null && !cargo.isBlank() : "Cargo invalido";
        for (Haber h : this.tripulantes) {
            t = h.getTripulante();
            c = t.getCargo();
            if (c.getCargoTripulante().equalsIgnoreCase(cargo))
                tripulantesCargo.add(t);
        }
        return tripulantesCargo;
    }
    
    /**
     * <b>PRE</b>
     * - id no nulo ni vacío.
     * <b>POST</b>
     * - Retorna el tripulante con dicho id o null si no existe.
     */
    public Haber getTripulante(String id) {
        int i = 0 , n = this.tripulantes.size();
        assert id != null && !id.isBlank(): "Identificador invalido";        
        while (i < n && !this.tripulantes.get(i).getTripulante().getId().equals(id)) {
            i += 1;
        }
        if (i >= n)
            return null;
        else
            return this.tripulantes.get(i);
    }
    
    public void reiniciarPeriodo() {
        for (Haber h : this.tripulantes) {
            h.getTripulante().addAntiguedad(1);
        }
        for (Tripulante t : getTripulantesCargo("Consejero")) {
            ((Consejero) t.getCargo()).resetConsejos();
        }
    }
    
    public String toString() {
        String tripulantesStr = "Tripulacion : \n";
        for (Haber h : this.tripulantes)
            tripulantesStr += h.toString() + "\n";
        return tripulantesStr;
    }
}
