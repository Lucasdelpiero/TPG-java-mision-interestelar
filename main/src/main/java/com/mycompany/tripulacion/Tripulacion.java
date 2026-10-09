/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tripulacion;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class Tripulacion {

    private ArrayList<PlanetaOrigen> tripulantes;
    
    public Tripulacion() {
        this.tripulantes = new ArrayList<>();
    }
    
// PRE: po no es null y contiene un Tripulante valido.
// POST: po queda agregado al final de la lista.
//       La cantidad de elementos aumenta en uno.
//       Los elementos anteriores conservan su orden.    
    public void addTripulante(PlanetaOrigen po) {
        tripulantes.add(po);
    }
    
    
// PRE: id no es null ni esta en blanco.
//      Existe al menos un tripulante con ese identificador.
// POST: se elimina la primera entrada con ese identificador.
//       La cantidad de elementos disminuye en uno.
//       Los elementos restantes conservan su orden.
    public void removeTripulante(String id) {
        assert id != null && !id.isBlank() : "El identificador es invalido";
        int i = 0 , n = this.tripulantes.size();
        while (i < n && !this.tripulantes.get(i).getTripulante().getId().equals(id)) {
            i ++;
        }
        assert i < n : "El tripulante con ese identificador no se encuentra en esta nave";
        
        this.tripulantes.remove(i);
    }
    
// PRE: cargo puede ser null; si no lo es, no debe estar en blanco.
// POST: devuelve una nueva lista con todos los tripulantes
//       cuyo cargo coincide con el solicitado.
//       Si cargo es null, devuelve a todos.
//       Si no hay coincidencias, devuelve una lista vacia.
//       No modifica la tripulacion.    
    public ArrayList<Tripulante> getTripulantesCargo(String cargo) {
        Cargo c;
        Tripulante t;
        ArrayList<Tripulante> tripulantesCargo = new ArrayList<>();
        assert cargo == null || !cargo.isBlank() : "Cargo invalido";
        for (PlanetaOrigen po : this.tripulantes) {
            t = po.getTripulante();
            c = t.getCargo();
            if (cargo == null || c.getCargoTripulante().equals(cargo))
                tripulantesCargo.add(t);
        }
        return tripulantesCargo;
    }
    
// PRE: id no es null ni esta en blanco.
// POST: devuelve el primer tripulante con ese identificador.
//       Si no existe, devuelve null, incluso si la lista esta vacia.
//       No modifica la tripulacion.    
    public Tripulante getTripulante(String id) {
        int i = 0 , n = this.tripulantes.size();
        assert id != null && !id.isBlank(): "Identificador invalido";        
        while (i < n && !this.tripulantes.get(i).getTripulante().getId().equals(id)) {
            i += 1;
        }
        if (i >= n)
            return null;
        else
            return this.tripulantes.get(i).getTripulante();
    }
    
    public void reiniciarPeriodo() {
        ArrayList<Tripulante> consejeros = getTripulantesCargo(null);
        Cargo c;
        for (Tripulante t : consejeros) {
            c = t.getCargo();
            if (c.getCargoTripulante().equals("Consejero"))
                ((Consejero) t.getCargo()).resetConsejos();
            t.addAntiguedad(1);
        }
    }
    
    public String toString() {
        String tripulantes = "Tripulacion : \n";
        for (PlanetaOrigen po : this.tripulantes)
            tripulantes += po.toString() + "\n";
        return tripulantes;
    }
}
