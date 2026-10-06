/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tripulacion;

public class Prueba {

    public static void main(String[] args) {

        Tripulante capitan = new Tripulante("Juan", "Capitan", 2);
        Tripulante teniente = new Tripulante("Pedro", "Teniente", 3) ;
        Tripulante alferez = new Tripulante("Ana", "Alferez", 2);
        Tripulante consejero = new Tripulante("Luis", "Consejero", 4);

        ((Consejero) consejero.getCargo()).addConsejo(3);

        Haber h1 = new Marciano(capitan);
        Haber h2 = new Vulcano(teniente);
        Haber h3 = new Terricola(alferez);
        Haber h4 = new Terricola(consejero);

        probar("Capitan Marciano", h1);
        probar("Teniente Vulcano", h2);
        probar("Alferez Terricola", h3);
        probar("Consejero Terricola", h4);
    }

    public static void probar(String nombre, Haber haber) {
        System.out.println("\n--- " + nombre + " ---");
        System.out.println(haber.detalleHaber());
        System.out.println("TOTAL: " + haber.sueldo());
    }
}