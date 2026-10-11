/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tripulacion;

/**
 *
 * @author ASUS
 */
public class Tripulante implements Haber {
    private int antiguedad;
    private String identidad;
    private Cargo cargo;
    
    public Tripulante(String identidad , Cargo cargo , int antiguedad) {
        if (identidad == null || identidad.isBlank()) {
            throw new IllegalArgumentException("Identidad invalida");
        }

        if (antiguedad < 0) {
            throw new IllegalArgumentException("Antiguedad invalida");
        }
      
        this.cargo = cargo;
    
        this.identidad = identidad;
        this.antiguedad = antiguedad;
    }
    
    protected void addAntiguedad(int anios) {  
        if (anios <= 0)
            throw new IllegalArgumentException("anios invalidos");
        this.antiguedad += anios; // Sumo los años en servicio cuando se ejecuta la nave
    }
    
    @Override
    public double sueldo() {
        double sueldo = this.cargo.sueldoBase();
        
        double adicional = sueldo * (this.antiguedad * this.cargo.adicionalCargo());
        
        if (this.cargo.getCargoTripulante().equals("Consejero"))
            adicional += ((Consejero) this.cargo).getConsejos() * 2;
        
        return sueldo + adicional;
    }
    @Override
    public String detalleHaber() {
        double base = cargo.sueldoBase();
        double adicionalAntiguedad = base * cargo.adicionalCargo() * antiguedad;
        if (this.cargo.getCargoTripulante().equals("Consejero")) {
            double adicionalConsejos = ((Consejero) this.cargo).getConsejos() * 2;
            return ":: [HABER] CALCULO DE HABERES"
               + "\n:: [HABER] Cargo: " + this.cargo.getCargoTripulante()
               + "\n:: [HABER] Sueldo base: " + base
               + "\n:: [HABER] Adicional por antiguedad: " + adicionalAntiguedad 
               + "\n:: [HABER] Adicional por numero de consejos " + adicionalConsejos + "\n";
        }
        else
            return ":: [HABER] CALCULO DE HABERES:"
               + "\n:: [HABER] Cargo: " + this.cargo.getCargoTripulante()
               + "\n:: [HABER] Sueldo base: " + base
               + "\n:: [HABER] Adicional por antiguedad: " + adicionalAntiguedad ;
    }

    public void setCargo(Cargo cargo) {
        assert cargo != null : "ERROR: Cargo null";
        this.cargo = cargo;
    }
    
    public void setAntiguedad(int antiguedad) {
        assert antiguedad >= 0 : "ERROR : Antiguedad negativa";
        this.antiguedad = antiguedad;
    }
    
    public void setId(String id) {
        assert (!id.isBlank()) : "ERROR : Identificador invalido";
        this.identidad = id;
    }
    
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public int getAntiguedad() {
        return this.antiguedad;
    }
    
    public String getId() {
        return this.identidad;
    }
    
    public String toString() {
        
        return "Cargo :" + this.cargo.getCargoTripulante() + "\n Identidad :" + this.identidad + 
                "\n anios en servicio :" + this.antiguedad + "\n Sueldo : " + sueldo() + "\n" + 
                detalleHaber() ;
    }
    
}
