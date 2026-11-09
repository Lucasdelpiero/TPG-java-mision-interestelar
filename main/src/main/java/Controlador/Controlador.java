package Controlador;

// importante
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.*;
import modelo.usuario.*;
import modelo.plataforma.*;
import modelo.persistencia.*;
// importante

//
import modelo.universo.*;
import modelo.asistente.*;
import modelo.bitacora.*;
import modelo.mision.*;
import modelo.nave.*;
import modelo.tripulacion.*;
//

public class Controlador implements ActionListener{
    private InterfazVista vista;
    private InterfazVistaDashboard vistaDashboard;
    
    private ServicioUsuario servicioUsuario;
    private PlataformaDeCarga plataforma;
    private UniversoParalelo universoParalelo;
    
    private Universo universo;
    private CentroDeControl GGCC;
    private FactoryCargo factoryCargo;
    private FactoryPlanetaOrigen factoryOrigen;
    private FactoryMision factoryMision;
    
    public Controlador(InterfazVista vista, Universo universo) {
        try{
            ConexionDBSQLite conexion = new ConexionDBSQLite("jdbc:sqlite:database/interestelar.db");
            conexion.inicializarBase();
            UsuarioDAO usuarioDAO = new UsuarioDAOSQLite(conexion);
            
            servicioUsuario = new ServicioUsuario(usuarioDAO);
            plataforma = new PlataformaDeCarga(8);
            universoParalelo = new UniversoParalelo(plataforma);
        }
        catch(DAOException e){
            System.out.println("Fallo la conexion");
        }
        this.universo = universo;
        this.vista = vista;
        GGCC = new CentroDeControl();
        factoryCargo = new FactoryCargo();
        factoryOrigen = new FactoryPlanetaOrigen();
        factoryMision = new FactoryMision();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // <editor-fold defaultstate="collapsed" desc="LOGIN">
        if(e.getActionCommand().equals("_LOGIN")){
            String username = vista.getUsername();
            String password = vista.getPassword();
            try{
                boolean intento = servicioUsuario.autenticar(username, password);
                if(intento){
                    switchToDashboard(username);
                }
                else{
                    vista.mensajeAlerta("Usuario o password incorrectos");
                }
            }
            catch(UsuarioException exc){
                vista.mensajeAlerta(exc.getMessage());
            }
        }
        else if(e.getActionCommand().equals("_REGISTER")){
            switchToRegister();
        } 
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="REGISTER">
        else if(e.getActionCommand().equals("_CANCEL")){
            switchToLogin();
        }
        else if(e.getActionCommand().equals("_REGISTERIN")){
            String username = vista.getUsername();
            String password = vista.getPassword();
            String confPassword = vista.getConfPassword(); // agregar
            
            try{
                servicioUsuario.registrar(username, password);
                vista.mensajeAlerta("Registro exitoso");
                switchToLogin();
            }
            catch(UsuarioException exc){
                vista.mensajeAlerta(exc.getMessage());
            }
        }
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="DASHBOARD">
        
        // <editor-fold defaultstate="collapsed" desc="DASHBOARD - Consola eventos">
        else if(e.getActionCommand().equals("_ADDCONSUMIDORA")){
            universoParalelo.agregarNaveConsumidora();
        }
        
        else if(e.getActionCommand().equals("_ADDGENERADORA")){
            universoParalelo.agregarGeneradora();
        }
        // </editor-fold>     
        
        // <editor-fold defaultstate="collapsed" desc="DASHBOARD - Creacion/Seteo naves">
        else if(e.getActionCommand().equals("_CREATESHIP")){
            String tipo = vistaDashboard.getTipoNave();
            String nombre = vistaDashboard.mensajeConTexto("Ingrese nombre para nave", "Creacion nave");
            
            Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.valueOf(tipo));
            AsistenteComando AC = new AsistenteComando(nave, nombre, universo);
            GGCC.setAC(AC);
            
            vistaDashboard.addNaveCreada(nombre,tipo);
        
        }   // FALTA MODIFICAR MODELO
        
        else if(e.getActionCommand().equals("_SETSHIP")){
            vistaDashboard.limpiaTablaCrew();
            vistaDashboard.limpiaTablaBinnacle();
            vistaDashboard.getAreaReportes().setText("");
            recuperaRecursos();
            recuperaTripulantes();
            recuperaBitacora();
            recuperaReportes();
        }
        // </editor-fold>     
        
        // <editor-fold defaultstate="collapsed" desc="DASHBOARD - Tripulacion">
        else if(e.getActionCommand().equals("_ADDCREWMATE")){
            String id = vistaDashboard.getID();
            String origen = vistaDashboard.getOrigen();
            String employ = vistaDashboard.getEmploy();
            String antiguedad = vistaDashboard.getAntiguedad();
            String nombreNave = vistaDashboard.getNombreNave();
            
            Cargo c = factoryCargo.crearCargo(employ);
            Tripulante t = new Tripulante(id, c, Integer.parseInt(antiguedad));
            PlanetaOrigen PlaOri = factoryOrigen.factoryPlanetaOrigen(origen, t);
            
            AsistenteComando AC = GGCC.getAC(nombreNave);
            AC.addTripulante(t);
            
            double haberes = t.sueldo();
            Object empleado[] = {id, origen, employ, antiguedad, haberes};
            vistaDashboard.agregaEmpleado(empleado);
            
            vistaDashboard.limpiaTablaBinnacle();
            recuperaBitacora();
        } // FALTA AGREGAR VERIFICACION DE VALORES??
        
        else if(e.getActionCommand().equals("_STARTMISSION")){
            String mision = vistaDashboard.getMision();
            String nombreNave = vistaDashboard.getNombreNave();
            AsistenteComando AC = GGCC.getAC(nombreNave);
            
            Mision m = factoryMision.crearMision(mision, universo);
            m.hacerMision(AC);
            recuperaRecursos();
            vistaDashboard.limpiaTablaBinnacle();
            recuperaBitacora();
            vistaDashboard.getAreaReportes().setText("");
            recuperaReportes();
            // RECUPERA REPORTES FINALES
            
        } // FALTA EJECUTAR MISION, MODIFICAR RECURSOS Y ETC
        // </editor-fold>     
        

        // </editor-fold>     
    }
    
    private void switchToLogin(){
        vista.setVisible(false);
        vista = new winLogin();
        vista.setControlador(this);
        vista.setVisible(true);
    }
    
    private void switchToRegister(){
        vista.setVisible(false);
        vista = new winRegister();
        vista.setControlador(this);
        vista.setVisible(true);
    }

    private void switchToDashboard(String username){
        vista.setVisible(false);
        vistaDashboard = new winDashboard(username, universo);
        vistaDashboard.setControlador(this);
        vistaDashboard.setVisible(true);
        
        universoParalelo.registrarObservador(new ObservadorPlataforma(){
            @Override
            public void onEvento(String msg){
                vistaDashboard.getAreaConsola().append("> "+ msg +"\n");
            }
        });
    }
    
    private void recuperaRecursos(){
        String nombre = vistaDashboard.getNombreNave();            
        Nave nave = GGCC.getNave(nombre);
            
        int combus = nave.getRecursos().getCombustible();
        int desgas = nave.getRecursos().getDesgaste();
        int energi = nave.getRecursos().getEnergia();
        int gemas = nave.getRecursos().getGemas();
        vistaDashboard.setRecursos(combus, desgas, energi,gemas);
    }
    
    private void recuperaTripulantes(){
        String nombre = vistaDashboard.getNombreNave();            
        Nave nave = GGCC.getNave(nombre);
        
        ArrayList<Haber> t = nave.getTripulantes();
        //vistaDashboard.limpiaTablaCrew();
        for (Haber persona : t){
            String id = persona.getTripulante().getId();
            String employ = persona.getTripulante().getCargo().getCargoTripulante();
            String antiguedad = String.valueOf(persona.getTripulante().getAntiguedad());
            Double haberes = persona.sueldo();
                
                
            Object empleado[] = {id, "Origen?" , employ, antiguedad, haberes};
            vistaDashboard.agregaEmpleado(empleado);
        }
    }
    
    private void recuperaBitacora(){
        String nombreNave = vistaDashboard.getNombreNave();            
        AsistenteComando AC = GGCC.getAC(nombreNave);
        ArrayList<Evento> lista = AC.getBitacora().getEventos();
        //vistaDashboard.limpiaTablaBinnacle();
        for(Evento e: lista){
            if(e.getIdMision() == null){
                //String id = String.valueOf(e.getIdMision());
                String date = String.valueOf(e.getMomento());
                String tipo = String.valueOf(e.getTipo());
                String desc = e.getDescripcion();
            
                Object row[] = {date, tipo, desc};
                vistaDashboard.agregaEvento(row);
            }
        }
        
    }

    private void recuperaReportes(){
        String nombreNave = vistaDashboard.getNombreNave();            
        AsistenteComando AC = GGCC.getAC(nombreNave);
        ArrayList<Evento> lista = AC.getBitacora().getEventos();
        
        for(Evento e: lista){
            if(e.getIdMision() != null){
                String id = String.valueOf(e.getIdMision());
                String date = String.valueOf(e.getMomento());
                String tipo = String.valueOf(e.getTipo());
                String desc = e.getDescripcion();

                String msg = "["+id+"] "+date+" | "+desc;
                vistaDashboard.getAreaReportes().append("> "+ msg +"\n");
            }
        }
    }
}

