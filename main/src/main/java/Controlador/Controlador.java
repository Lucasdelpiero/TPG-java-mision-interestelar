package Controlador;

// importante
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.*;
import modelo.usuario.*;
import modelo.plataforma.*;
import modelo.persistencia.*;
import modelo.destinoytablero.Observador;
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
                if (username == null || username.trim().isEmpty()) {
                    throw new UsuarioException("El nombre de usuario no puede estar vacío.");
                }
                if (password == null || password.trim().isEmpty()) {
                    throw new UsuarioException("La contraseña no puede estar vacía.");
                }
                if (confPassword != null && !confPassword.isEmpty() && !password.equals(confPassword)) {
                    throw new UsuarioException("Las contraseñas ingresadas no coinciden.");
                }
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
            try {
                String tipo = vistaDashboard.getTipoNave();
                if (tipo == null || tipo.isBlank()) {
                    throw new IllegalArgumentException("Debe seleccionar un tipo de nave válido.");
                }

                String nombre = vistaDashboard.mensajeConTexto("Ingrese nombre para nave", "Creacion nave");
                if (nombre == null) {
                    return;
                }
                nombre = nombre.trim();
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre de la nave no puede estar vacío.");
                }
                if (GGCC.existeNave(nombre)) {
                    throw new IllegalArgumentException("Ya existe una nave con el nombre: " + nombre);
                }
                
                Nave nave = NaveFactory.getNave(NaveFactory.tipoNave.valueOf(tipo));
                AsistenteComando AC = new AsistenteComando(nave, nombre, universo);
                GGCC.setAC(AC);
                
                vistaDashboard.addNaveCreada(nombre, tipo);
            } catch (Exception ex) {
                vistaDashboard.mensajeAlerta(ex.getMessage() != null ? ex.getMessage() : "Error al crear nave");
            }
        }
        
        else if(e.getActionCommand().equals("_SETSHIP")){
            try {
                String nombreNave = vistaDashboard.getNombreNave();
                if (nombreNave == null || nombreNave.isBlank()) {
                    throw new IllegalArgumentException("No hay ninguna nave seleccionada en la lista.");
                }
                GGCC.getAC(nombreNave);
                vistaDashboard.limpiaTablaCrew();
                vistaDashboard.limpiaTablaBinnacle();
                vistaDashboard.getAreaReportes().setText("");
                recuperaRecursos();
                recuperaTripulantes();
                recuperaBitacora();
                recuperaReportes();
            } catch (Exception ex) {
                vistaDashboard.limpiarDatosNave();
                vistaDashboard.mensajeAlerta(ex.getMessage() != null ? ex.getMessage() : "Error al seleccionar nave");
            }
        }
        // </editor-fold>     
        
        // <editor-fold defaultstate="collapsed" desc="DASHBOARD - Tripulacion">
        else if(e.getActionCommand().equals("_ADDCREWMATE")){
            try {
                String nombreNave = vistaDashboard.getNombreNave();
                if (nombreNave == null || nombreNave.isBlank()) {
                    throw new IllegalArgumentException("Debe seleccionar una nave antes de agregar tripulantes.");
                }

                String id = vistaDashboard.getID();
                if (id == null || id.trim().isEmpty()) {
                    throw new IllegalArgumentException("Debe ingresar un identificador (ID) para el tripulante.");
                }
                id = id.trim();

                String origen = vistaDashboard.getOrigen();
                if (origen == null || origen.isBlank() || origen.equals("<Select one>")) {
                    throw new IllegalArgumentException("Debe seleccionar un planeta de origen para el tripulante.");
                }

                String employ = vistaDashboard.getEmploy();
                if (employ == null || employ.isBlank() || employ.equals("<Select one>")) {
                    throw new IllegalArgumentException("Debe seleccionar un empleo o cargo válido para el tripulante.");
                }

                String antiguedad = vistaDashboard.getAntiguedad();
                if (antiguedad == null || antiguedad.isBlank()) {
                    throw new IllegalArgumentException("Debe ingresar un valor numérico para la antigüedad (Seniority).");
                }
                int antiguedadVal;
                try {
                    antiguedadVal = Integer.parseInt(antiguedad.trim());
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("La antigüedad (Seniority) debe ser un número entero válido.");
                }
                if (antiguedadVal < 0) {
                    throw new IllegalArgumentException("La antigüedad (Seniority) debe ser mayor o igual a 0.");
                }

                AsistenteComando AC = GGCC.getAC(nombreNave);
                if (AC.getNave() == null || AC.getNave().estaInservible()) {
                    throw new IllegalStateException("No se pueden agregar tripulantes a una nave inservible.");
                }

                // Validacion de limite maximo de 5 tripulantes
                if (AC.getNave().getTripulacion().getTripulantes().size() >= 5) {
                    throw new IllegalStateException("La tripulación ya está completa (máximo 5 tripulantes permitidos).");
                }

                // Validacion explicita de Capitán único
                if (employ.equalsIgnoreCase("Capitan") && !AC.getNave().getTripulacion().getTripulantesCargo("Capitan").isEmpty()) {
                    throw new IllegalStateException("La nave ya posee un Capitán. Solo se permite un Capitán por tripulación.");
                }

                // Validacion de ID duplicado en la nave
                if (AC.getNave().getTripulacion().getTripulante(id) != null) {
                    throw new IllegalArgumentException("Ya existe un tripulante con el ID [" + id + "] en esta tripulación.");
                }
                
                Cargo c = factoryCargo.crearCargo(employ);
                Tripulante t = new Tripulante(id, c, antiguedadVal);
                PlanetaOrigen PlaOri = factoryOrigen.factoryPlanetaOrigen(origen, t);
                
                AC.addTripulante(PlaOri);
                
                double haberes = PlaOri.sueldo();
                Object empleado[] = {id, employ, origen, antiguedadVal, haberes};
                vistaDashboard.agregaEmpleado(empleado);
                
                vistaDashboard.limpiaTablaBinnacle();
                recuperaBitacora();
            } catch (Exception ex) {
                vistaDashboard.mensajeAlerta(ex.getMessage() != null ? ex.getMessage() : "Error al agregar tripulante");
            }
        }
        
        else if(e.getActionCommand().equals("_STARTMISSION")){
            try {
                String nombreNave = vistaDashboard.getNombreNave();
                if (nombreNave == null || nombreNave.isBlank()) {
                    throw new IllegalArgumentException("Debe seleccionar una nave antes de iniciar una misión.");
                }

                String mision = vistaDashboard.getMision();
                if (mision == null || mision.isBlank()) {
                    throw new IllegalArgumentException("Debe seleccionar un tipo de misión.");
                }

                AsistenteComando AC = GGCC.getAC(nombreNave);

                if (AC.getNave() == null || AC.getNave().estaInservible()) {
                    throw new IllegalStateException("La nave no se encuentra operativa para realizar misiones.");
                }

                if (AC.getNave().getTripulacion().getTripulantesCargo("Capitan").isEmpty()) {
                    throw new IllegalStateException("La nave no puede iniciar la misión: requiere obligatoriamente 1 Capitán asignado.");
                }

                if (AC.hayMisionEnCurso()) {
                    throw new IllegalStateException("Ya hay una misión en curso en esta nave.");
                }

                new javax.swing.SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        AC.realizarMision(mision);
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            get();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                        Nave nave = AC.getNave();
                        if (nave != null && nave.estaInservible()) {
                            nave.destruirNave();
                            GGCC.eliminarNave(nombreNave);
                            vistaDashboard.removerNave(nombreNave);
                            vistaDashboard.limpiarDatosNave();
                            vistaDashboard.mensajeAlerta("La nave [" + nombreNave + "] ha quedado inservible y ha sido destruida.");
                        } else {
                            recuperaRecursos();
                            vistaDashboard.limpiaTablaBinnacle();
                            recuperaBitacora();
                            vistaDashboard.getAreaReportes().setText("");
                            recuperaReportes();
                        }
                    }
                }.execute();
            } catch (Exception ex) {
                vistaDashboard.mensajeAlerta(ex.getMessage() != null ? ex.getMessage() : "Error al ejecutar mision");
            }
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
        vistaDashboard = new winDashboard(username, this);
        vistaDashboard.setControlador(this);
        vistaDashboard.setVisible(true);
        universo.agregarObservador((Observador) vistaDashboard);
        
        universoParalelo.registrarObservador(new ObservadorPlataforma(){
            @Override
            public void onEvento(String msg){
                javax.swing.SwingUtilities.invokeLater(() -> {
                    vistaDashboard.getAreaConsola().append("> "+ msg +"\n");
                });
            }
        });
    }
    
    public void recuperaRecursos(){
        String nombre = vistaDashboard.getNombreNave();            
        if (nombre == null || !GGCC.existeNave(nombre)) return;
        Nave nave = GGCC.getNave(nombre);
        if (nave == null || nave.getRecursos() == null) return;
            
        int combus = nave.getRecursos().getCombustible();
        int desgas = nave.getRecursos().getDesgaste();
        int energi = nave.getRecursos().getEnergia();
        int gemas = nave.getRecursos().getGemas();
        vistaDashboard.setRecursos(combus, desgas, energi,gemas);
    }
    
    private void recuperaTripulantes(){
        String nombre = vistaDashboard.getNombreNave();            
        if (nombre == null || !GGCC.existeNave(nombre)) return;
        Nave nave = GGCC.getNave(nombre);
        if (nave == null) return;
        
        ArrayList<Haber> t = nave.getTripulantes();
        //vistaDashboard.limpiaTablaCrew();
        for (Haber persona : t){
            String id = persona.getTripulante().getId();
            String employ = persona.getTripulante().getCargo().getCargoTripulante();
            String origen = (persona instanceof PlanetaOrigen) ? persona.getClass().getSimpleName() : "Desconocido";
            String antiguedad = String.valueOf(persona.getTripulante().getAntiguedad());
            Double haberes = persona.sueldo();
                
            Object empleado[] = {id, employ, origen, antiguedad, haberes};
            vistaDashboard.agregaEmpleado(empleado);
        }
    }
    
    private void recuperaBitacora(){
        String nombreNave = vistaDashboard.getNombreNave();            
        if (nombreNave == null || !GGCC.existeNave(nombreNave)) return;
        AsistenteComando AC = GGCC.getAC(nombreNave);
        if (AC == null || AC.getBitacora() == null) return;
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
        if (nombreNave == null || !GGCC.existeNave(nombreNave)) return;
        AsistenteComando AC = GGCC.getAC(nombreNave);
        if (AC == null || AC.getBitacora() == null) return;
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
    
    public Universo getUniverso() {
        return this.universo;
    }
}

