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
    
    public ArrayList<Tripulante> getTripulantesCargo(String cargo) {
        Cargo c;
        Tripulante t;
        ArrayList<Tripulante> tripulantesCargo = new ArrayList<>();
        if (cargo != null && cargo.isBlank())
            throw new IllegalArgumentException("Cargo invalido");
        for (PlanetaOrigen po : this.tripulantes) {
            t = po.getTripulante();
            c = t.getCargo();
            if (cargo == null || c.getCargoTripulante().equals(cargo))
                tripulantesCargo.add(t);
        }
        return tripulantesCargo;
    }
    
    public Tripulante getTripulante(String id) {
        int i = 0;
        if (id.isBlank() || this.tripulantes.isEmpty())
            throw new IllegalArgumentException("Cargo invalido");
        while (this.tripulantes.get(i).getTripulante().getId().equals(id)) {
            i += 1;
        }
        if (i > this.tripulantes.size())
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
