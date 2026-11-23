package com.mycompany.main;

import java.util.ArrayList;
import modelo.asistente.AsistenteComando;
import modelo.destinoytablero.*;
import modelo.mision.MisionRecoleccion;
import modelo.nave.*;
import modelo.universo.*;

import Controlador.Controlador;
import vista.*;

public class Main {
    public static void main(String[] args) {
        // =====================================================
        // 1. CREAR ELEMENTOS
        // =====================================================
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        Asteroide a1 = new Asteroide();
        EstacionDeCarga e1 = new EstacionDeCarga();
        //elementos.add(a1);
        //elementos.add(a2);
        //elementos.add(e1);
        //elementos.add(e2);
        
        // Añadir 10 estaciones de carga
        for(int i=0; i<5;i++){
            elementos.add(e1);
        }
        
        // Añadir 20 asteroides
        for(int i=0; i<25;i++){
            elementos.add(a1);
        }
        
        
        // =====================================================
        // 12. PROBAR UNIVERSO
        // =====================================================
        // Añadir 3 naves
        Nave nave = new Exploradora();
        Nave nave2 = new Carguero();
        Nave nave3 = new Combate();
        elementos.add(nave);
        elementos.add(nave2);
        elementos.add(nave3);
        ArrayList<ElementoDelUniverso> elementosUniverso = new ArrayList<>();
        //elementosUniverso.add(new Asteroide());
        //elementosUniverso.add(new EstacionDeCarga());
        
        Universo universo = new Universo(nave, 50, 50, elementos); // (nave, FILAS, COLUMNAS, elems)

        // =====================================================
        // INICIO VENTANA LOGIN
        // =====================================================
        
        InterfazVista w1 = new winLogin();
        Controlador control = new Controlador(w1, universo);
        
        w1.setControlador(control);
        w1.setVisible(true);
        
    }
}