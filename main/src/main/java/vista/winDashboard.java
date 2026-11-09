package vista;

// IMPORTANTE
import Controlador.Controlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
// IMPORTANTE

// AGREGADO
import modelo.universo.*;
import modelo.destinoytablero.*;
import modelo.nave.*;
// AGREGADO


public class winDashboard extends JFrame implements InterfazVistaDashboard{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(winDashboard.class.getName());
    private static final int SIZE_TABLERO = 540; //(pixeles) NO TOCAR
    
    
    // USERNAME obtenido desde LOGIN
    // UNIVERSO obtenido desde CONTROLADOR
    
    // CONSTRUCTOR
    public winDashboard(String username, Universo universo) {
        initComponents();
        setLocationRelativeTo(null);
        /// Cosas nuevardas
        dibujaTablero(universo);
        lblUsername.setText(username);
    }

    private void dibujaTablero(Universo universo){
        int dimX = universo.getX();
        int dimY = universo.getY();
        ElementoDelUniverso[][] tablero = universo.verTablero();
        int posX = universo.getPosicionNave().getX();
        int posY = universo.getPosicionNave().getY();
        JPanel cuadricula = new JPanel(new GridLayout(dimX,dimY,0,0));
        for (int i = 0; i < dimX; i++){
            for(int j = 0; j < dimY;j++){
                JPanel p = new JPanel();
                ElementoDelUniverso objActual = tablero[i][j];
                if(tablero[i][j] != null){
                    if (objActual instanceof EstacionDeCarga _) {
                        p.setBackground(Color.decode("0xb757eb"));
                    } 
                    else if (objActual instanceof Asteroide _) {
                        p.setBackground(Color.decode("0x9e9378"));
                    }
                    else if (objActual instanceof Nave _) {
                        p.setBackground(Color.decode("0x78cfbc"));
                    }
                }
                else{
                    if(i%2 == j%2){
                        p.setBackground(Color.decode("0x252525"));
                    }
                    else{
                        p.setBackground(Color.decode("0x303030"));
                    }
                }
                if(i == posX && j==posY){
                    p.setBackground(Color.GREEN);
                }
                cuadricula.add(p);
            }        
        }
        JScrollPane scroll = new JScrollPane(cuadricula);
        scroll.setPreferredSize(new Dimension( SIZE_TABLERO , SIZE_TABLERO ));        
        panGrid.add(scroll);            // panGrid tiene FlowLayout
        panGrid.setVisible(true);
        // PANGRID contiene a SCROLL, y SCROLL contiene a CUADRICULA
        // NO TOCAR PANGRID ni SIZETABLERO porque los mato
    }
    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="[!!!] Inicializacion de componentes">                          
    private void initComponents() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glass Group Control Center");
        setPreferredSize(new java.awt.Dimension(1080, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1080, 800));
        
        
        // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) CREACION DE ENTIDADES (Botones, cuadros, etc)">                    
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        panBoard = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblBoard = new javax.swing.JLabel();
        panGrid = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnConsumidora = new javax.swing.JButton();
        btnGeneradora = new javax.swing.JButton();
        lblAddShip = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaEvents = new javax.swing.JTextArea();
        lblEvents = new javax.swing.JLabel();
        panSetShip = new javax.swing.JPanel();
        tabMain = new javax.swing.JTabbedPane();
        panDashboard = new javax.swing.JPanel();
        panResources = new javax.swing.JPanel();
        lblResources = new javax.swing.JLabel();
        lblFuel = new javax.swing.JLabel();
        lblEnergy = new javax.swing.JLabel();
        lblDamage = new javax.swing.JLabel();
        barFuel = new javax.swing.JProgressBar();
        barEnergy = new javax.swing.JProgressBar();
        barDamage = new javax.swing.JProgressBar();
        panStartMission = new javax.swing.JPanel();
        lblMission = new javax.swing.JLabel();
        btnStartMission = new javax.swing.JButton();
        boxMission = new javax.swing.JComboBox<>();
        tabReports = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBitacora = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        sepHor2 = new javax.swing.JSeparator();
        sepHor1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        panCrewmateList = new javax.swing.JPanel();
        lblCrewList = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCrewmates = new javax.swing.JTable();
        panAddCrewmate = new javax.swing.JPanel();
        lblAddCrewmate = new javax.swing.JLabel();
        boxOrigin = new javax.swing.JComboBox<>();
        lblCrewmateOrigin = new javax.swing.JLabel();
        lblCrewmateSenority = new javax.swing.JLabel();
        lblCrewmateEmploy = new javax.swing.JLabel();
        lblCrewmateID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        boxEmploy = new javax.swing.JComboBox<>();
        btnAddCrewmate = new javax.swing.JButton();
        intSenority = new javax.swing.JFormattedTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        lblSetShip = new javax.swing.JLabel();
        boxSetShip = new javax.swing.JComboBox<>();
        btnSetShip = new javax.swing.JButton();
        panCreateShip = new javax.swing.JPanel();
        lblCreateShip = new javax.swing.JLabel();
        boxCreate = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        sepVer1 = new javax.swing.JSeparator();
        sepVer0 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblGems = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblCantGems = new javax.swing.JLabel();
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="[!!!] Setteador de valores">
        sepVer0.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sepVer1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        
        // HEADER
        lblTitle.setFont(new java.awt.Font("Arial", 1, 18));
        lblTitle.setText("G.G.C.C - Dashboard");
        lblGems.setText("GEMS: ");
        lblCantGems.setText("0"); // MODIFICABLE MODELO
        lblUser.setText("USER:");
        lblUsername.setText("-"); // MODIFICABLE MODELO
        
        // TABLERO LABEL
        lblBoard.setFont(new java.awt.Font("Arial", 1, 18));
        lblBoard.setText("Real-Time board");
        
        // EVENTOS (Agregacion naves / Consola de eventos)
        areaEvents.setFont(new java.awt.Font("Consolas", 1, 11));
        areaEvents.setColumns(20);
        areaEvents.setRows(5);
        areaEvents.setEditable(false);
        jScrollPane3.setViewportView(areaEvents);
        DefaultCaret caret = (DefaultCaret) areaEvents.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        lblAddShip.setFont(new java.awt.Font("Arial", 1, 18));
        lblAddShip.setText("Add ship");
        lblEvents.setText("Events");
        btnConsumidora.setText("CONSUMIDORA");  // !!!
        btnGeneradora.setText("GENERADORA");    // !!!
        
        
        
        // RECURSOS
        lblResources.setFont(new java.awt.Font("Arial", 1, 18));
        lblFuel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEnergy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDamage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblResources.setText("Resources");
        lblFuel.setText("Fuel");
        lblEnergy.setText("Energy");
        lblDamage.setText("Damage");
        barFuel.setStringPainted(true);
        barEnergy.setStringPainted(true);
        barDamage.setStringPainted(true);
        
        barFuel.setValue(0);   // MODIFICABLE MODELO
        barEnergy.setValue(0); // MODIFICABLE MODELO
        barDamage.setValue(0); // MODIFICABLE MODELO
        
        // BITACORA
        tableBitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                //"ID", "Date", "Tipo", "Desc"
                "Date", "Tipo", "Desc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                //false, false, false, false
                false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBitacora.setShowGrid(false);
        jScrollPane1.setViewportView(tableBitacora);
        
        
        // MISIONES
        lblMission.setFont(new java.awt.Font("Arial", 1, 18));
        lblMission.setText("Start mission");
        boxMission.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INTERCEPCION", "RECOLECCION", "RETORNO_SEGURO" }));      
        btnStartMission.setText("Start"); /// IMPORTANTE
        
        // PESTANIAS BITACORA/EVENTOS
        tabReports.addTab("Binnacle", jPanel1);
        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        //
        jTextArea1.setFont(new java.awt.Font("Consolas", 1, 11));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEditable(false);
        
        jScrollPane5.setViewportView(jTextArea1);
        
        caret = (DefaultCaret) jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //
        tabReports.addTab("Final reports", jPanel4);
        tabMain.addTab("Dashboard", panDashboard);

        // TABLA DE TRIPULANTES (Variable por cada nave)
        lblCrewList.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCrewList.setText("Crewmate list");
        tableCrewmates.setModel(
            new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"ID", "Employment", "Origin", "Senority", "Salary"}
            ){
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            }
        );
        
        jScrollPane2.setViewportView(tableCrewmates);
        
        
        // CREACION DE TRIPULANTE (Modifica lista Tripulante)
        lblAddCrewmate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAddCrewmate.setText("Add crewmate");
        lblCrewmateOrigin.setText("Origin");
        lblCrewmateSenority.setText("Senority");
        lblCrewmateEmploy.setText("Employment");
        lblCrewmateID.setText("ID");
        boxOrigin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terricola", "Marciano", "Vulcano" }));
        boxEmploy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Capitan", "Teniente", "Alferez", "Consejero" }));
        intSenority.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        btnAddCrewmate.setText("Add"); /// IMPORTANTE
        
        tabMain.addTab("Crew", jPanel5);
        
        // SELECCIONADOR DE NAVES (modifica recursos)
        lblSetShip.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSetShip.setText("Select ship");
        boxSetShip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        btnSetShip.setText("Set ship"); /// IMPORTANTISIMO
        
        // CREACION DE NAVES
        lblCreateShip.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCreateShip.setText("Create new ship");
        boxCreate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CARGUERA", "COMBATE", "EXPLORADORA" }));
        btnCreate.setText("Create"); /// IMPORTANTE (Agrega una nueva nave en el BOX "SETSHIP")
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="[!!!] ACTION COMMANDS">
        btnCreate.setActionCommand("_CREATESHIP"); //........... listo
        btnSetShip.setActionCommand("_SETSHIP");    
        btnConsumidora.setActionCommand("_ADDCONSUMIDORA");//... listo
        btnGeneradora.setActionCommand("_ADDGENERADORA");// .... listo
        btnAddCrewmate.setActionCommand("_ADDCREWMATE"); //..... casi listo
        btnStartMission.setActionCommand("_STARTMISSION");
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) POSICIONADOR DE OBJETOS (Delicado)">
        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGeneradora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsumidora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lblAddShip)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddShip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsumidora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGeneradora, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblEvents)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(lblEvents)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout panBoardLayout = new javax.swing.GroupLayout(panBoard);
        panBoard.setLayout(panBoardLayout);
        panBoardLayout.setHorizontalGroup(
            panBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panBoardLayout.setVerticalGroup(
            panBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBoardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panResourcesLayout = new javax.swing.GroupLayout(panResources);
        panResources.setLayout(panResourcesLayout);
        panResourcesLayout.setHorizontalGroup(
            panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResourcesLayout.createSequentialGroup()
                        .addComponent(lblResources, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panResourcesLayout.createSequentialGroup()
                        .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(barFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFuel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        panResourcesLayout.setVerticalGroup(
            panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResources, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDamage)
                    .addComponent(barDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panStartMissionLayout = new javax.swing.GroupLayout(panStartMission);
        panStartMission.setLayout(panStartMissionLayout);
        panStartMissionLayout.setHorizontalGroup(
            panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panStartMissionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxMission, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panStartMissionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStartMission))
                    .addGroup(panStartMissionLayout.createSequentialGroup()
                        .addComponent(lblMission, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panStartMissionLayout.setVerticalGroup(
            panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panStartMissionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMission, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxMission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnStartMission)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panDashboardLayout = new javax.swing.GroupLayout(panDashboard);
        panDashboard.setLayout(panDashboardLayout);
        panDashboardLayout.setHorizontalGroup(
            panDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepHor2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panResources, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panStartMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabReports)
                    .addComponent(sepHor1))
                .addContainerGap())
        );
        panDashboardLayout.setVerticalGroup(
            panDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sepHor2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panStartMission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sepHor1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabReports)
                .addContainerGap())
        );

        javax.swing.GroupLayout panCrewmateListLayout = new javax.swing.GroupLayout(panCrewmateList);
        panCrewmateList.setLayout(panCrewmateListLayout);
        panCrewmateListLayout.setHorizontalGroup(
            panCrewmateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCrewmateListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCrewmateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panCrewmateListLayout.createSequentialGroup()
                        .addComponent(lblCrewList)
                        .addGap(0, 202, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panCrewmateListLayout.setVerticalGroup(
            panCrewmateListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCrewmateListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCrewList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        javax.swing.GroupLayout panAddCrewmateLayout = new javax.swing.GroupLayout(panAddCrewmate);
        panAddCrewmate.setLayout(panAddCrewmateLayout);
        panAddCrewmateLayout.setHorizontalGroup(
            panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddCrewmateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panAddCrewmateLayout.createSequentialGroup()
                        .addComponent(lblAddCrewmate)
                        .addGap(0, 190, Short.MAX_VALUE))
                    .addGroup(panAddCrewmateLayout.createSequentialGroup()
                        .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrewmateEmploy)
                            .addComponent(lblCrewmateID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCrewmateOrigin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCrewmateSenority, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxOrigin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID)
                            .addComponent(boxEmploy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(intSenority)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddCrewmateLayout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btnAddCrewmate)))
                .addContainerGap())
        );
        panAddCrewmateLayout.setVerticalGroup(
            panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddCrewmateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddCrewmate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateOrigin)
                    .addComponent(boxOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxEmploy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrewmateEmploy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAddCrewmateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateSenority)
                    .addComponent(intSenority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddCrewmate)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panAddCrewmate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panCrewmateList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panCrewmateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panAddCrewmate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxSetShip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblSetShip)
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnSetShip)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSetShip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxSetShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetShip)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panCreateShipLayout = new javax.swing.GroupLayout(panCreateShip);
        panCreateShip.setLayout(panCreateShipLayout);
        panCreateShipLayout.setHorizontalGroup(
            panCreateShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCreateShipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCreateShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxCreate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panCreateShipLayout.createSequentialGroup()
                        .addGroup(panCreateShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCreateShip)
                            .addGroup(panCreateShipLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnCreate)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panCreateShipLayout.setVerticalGroup(
            panCreateShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCreateShipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCreateShip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panSetShipLayout = new javax.swing.GroupLayout(panSetShip);
        panSetShip.setLayout(panSetShipLayout);
        panSetShipLayout.setHorizontalGroup(
            panSetShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSetShipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSetShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabMain)
                    .addGroup(panSetShipLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(sepVer1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panCreateShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panSetShipLayout.setVerticalGroup(
            panSetShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSetShipLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panSetShipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panCreateShip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sepVer1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGems)
                    .addComponent(lblUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCantGems, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGems)
                    .addComponent(lblCantGems)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panSetShip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(sepVer0, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panSetShip, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(sepVer0)
                    .addComponent(panBoard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 748, Short.MAX_VALUE))
                .addContainerGap())
        );
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) SETTEADOR DE STYLE (Windows)">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold> 
        
        pack();
    }                       
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) DECLARACION DE VARIABLES (Botones, cuadros, etc)">                    
    private javax.swing.JTextArea areaEvents;
    private javax.swing.JProgressBar barDamage;
    private javax.swing.JProgressBar barEnergy;
    private javax.swing.JProgressBar barFuel;
    private javax.swing.JComboBox<String> boxCreate;
    private javax.swing.JComboBox<String> boxEmploy;
    private javax.swing.JComboBox<String> boxMission;
    private javax.swing.JComboBox<String> boxOrigin;
    private javax.swing.JComboBox<String> boxSetShip;
    private javax.swing.JButton btnAddCrewmate;
    private javax.swing.JButton btnConsumidora;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnGeneradora;
    private javax.swing.JButton btnSetShip;
    private javax.swing.JButton btnStartMission;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JFormattedTextField intSenority;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAddCrewmate;
    private javax.swing.JLabel lblAddShip;
    private javax.swing.JLabel lblBoard;
    private javax.swing.JLabel lblCantGems;
    private javax.swing.JLabel lblCreateShip;
    private javax.swing.JLabel lblCrewList;
    private javax.swing.JLabel lblCrewmateEmploy;
    private javax.swing.JLabel lblCrewmateID;
    private javax.swing.JLabel lblCrewmateOrigin;
    private javax.swing.JLabel lblCrewmateSenority;
    private javax.swing.JLabel lblDamage;
    private javax.swing.JLabel lblEnergy;
    private javax.swing.JLabel lblEvents;
    private javax.swing.JLabel lblFuel;
    private javax.swing.JLabel lblGems;
    private javax.swing.JLabel lblMission;
    private javax.swing.JLabel lblResources;
    private javax.swing.JLabel lblSetShip;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel panAddCrewmate;
    private javax.swing.JPanel panBoard;
    private javax.swing.JPanel panCreateShip;
    private javax.swing.JPanel panCrewmateList;
    private javax.swing.JPanel panDashboard;
    private javax.swing.JPanel panGrid;
    private javax.swing.JPanel panResources;
    private javax.swing.JPanel panSetShip;
    private javax.swing.JPanel panStartMission;
    private javax.swing.JSeparator sepHor1;
    private javax.swing.JSeparator sepHor2;
    private javax.swing.JSeparator sepVer0;
    private javax.swing.JSeparator sepVer1;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTabbedPane tabReports;
    private javax.swing.JTable tableBitacora;
    private javax.swing.JTable tableCrewmates;
    private javax.swing.JTextField txtID;
    // </editor-fold>   
    

    @Override
    public void setControlador(Controlador c) {
        btnSetShip.addActionListener(c); //...... _SETSHIP
        btnCreate.addActionListener(c); //....... _CREATESHIP
        btnConsumidora.addActionListener(c);  //. _ADDCONSUMIDORA
        btnGeneradora.addActionListener(c); //... _ADDGENERADORA
        btnAddCrewmate.addActionListener(c); //.. _ADDCREWMATE
        btnStartMission.addActionListener(c);//.. _STARTMISSION
    }

    
    // ASISTENTE COMANDO
    @Override
    public void getNombreAC() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // NAVE
    
    @Override
    public void setRecursos(int combus, int desgas, int energi, int gemas) {
        barFuel.setValue(combus);
        barDamage.setValue(desgas);
        barEnergy.setValue(energi);
        lblCantGems.setText(String.valueOf(gemas));
    }
    
    @Override
    public String getTipoNave() {
        Object itemSeleccionado = boxCreate.getSelectedItem();
        return itemSeleccionado.toString();
    }
    
    @Override
    public String getNombreNave() {
        Object itemSeleccionado = boxSetShip.getSelectedItem();
        String nombreNave = itemSeleccionado.toString();
        int indiceParentesis = nombreNave.indexOf("(");
            nombreNave = (indiceParentesis != -1)
                    ? nombreNave.substring(0, indiceParentesis).trim()
                    : nombreNave.trim();
        return nombreNave;
    }
    
    @Override
    public void addNaveCreada(String nombre, String tipo) {
        boxSetShip.addItem(nombre+"("+tipo+")");
    }
    
    
    // EMPLEADOS

    @Override
    public String getID() {
        return txtID.getText();
    }

    @Override
    public String getOrigen() {
        Object itemSeleccionado = boxOrigin.getSelectedItem();
        return itemSeleccionado.toString();
    }

    @Override
    public String getEmploy() {
        Object itemSeleccionado = boxEmploy.getSelectedItem();
        return itemSeleccionado.toString();
    }

    @Override
    public String getAntiguedad() {
        return intSenority.getText();
    }

    @Override
    public void agregaEmpleado(Object[] empleado) {
        DefaultTableModel modelo = (DefaultTableModel) tableCrewmates.getModel();
        modelo.addRow(empleado);
        intSenority.setText("");
        txtID.setText("");
    }

    // CONSOLA EVENTOS
    
    @Override
    public JTextArea getAreaConsola() {
        return areaEvents;
    }

    // MISIONES
    
    @Override
    public String getMision() {
        Object itemSeleccionado = boxMission.getSelectedItem();
        return itemSeleccionado.toString();
    }
    
    // POP-UPs
    @Override
    public void mensajeAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public String mensajeConTexto(String msg, String title){
        String respuesta = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
        return respuesta;
    }

    @Override
    public void limpiaTablaBinnacle() {
        DefaultTableModel tablarda = (DefaultTableModel) tableBitacora.getModel();
        tablarda.setRowCount(0);
    }
    
    @Override
    public void limpiaTablaCrew(){
        DefaultTableModel tablarda = (DefaultTableModel) tableCrewmates.getModel();
        tablarda.setRowCount(0);
    }
    

    @Override
    public void agregaEvento(Object[] evento) {
        DefaultTableModel modelo = (DefaultTableModel) tableBitacora.getModel();
        modelo.addRow(evento);
    }

    @Override
    public JTextArea getAreaReportes() {
        return jTextArea1;
    }

    

    
    
}


// NO LE DEN BOLA A ESTO // NO LE DEN BOLA A ESTO // NO LE DEN BOLA A ESTO // NO LE DEN BOLA A ESTO // NO LE DEN BOLA A ESTO // NO LE DEN BOLA A ESTO 
/*
package vista;

import Controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class winDashboard extends JFrame implements InterfazVistaDashboard{
          
    private javax.swing.JProgressBar barDamage;
    private javax.swing.JProgressBar barEnergy;
    private javax.swing.JProgressBar barFuel;
    private javax.swing.JComboBox<String> boxEmploy;
    private javax.swing.JComboBox<String> boxMission;
    private javax.swing.JComboBox<String> boxOrigin;
    private javax.swing.JButton btnAddCrewmate;
    private javax.swing.JButton btnStartMission;
    private javax.swing.JFormattedTextField intSenority;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddCrewmate;
    private javax.swing.JLabel lblBoard;
    private javax.swing.JLabel lblCrewList;
    private javax.swing.JLabel lblCrewmateEmploy;
    private javax.swing.JLabel lblCrewmateID;
    private javax.swing.JLabel lblCrewmateOrigin;
    private javax.swing.JLabel lblCrewmateSenority;
    private javax.swing.JLabel lblDamage;
    private javax.swing.JLabel lblEnergy;
    private javax.swing.JLabel lblFuel;
    private javax.swing.JLabel lblMission;
    private javax.swing.JLabel lblResources;
    private javax.swing.JPanel panBoard;
    private javax.swing.JPanel panResources;
    private javax.swing.JPanel panStartMission;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTabbedPane tabReports;
    private javax.swing.JTable tableCrewmates;
    private javax.swing.JTextField txtID;     
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(winDashboard.class.getName());

    public winDashboard() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        tabMain = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        panResources = new javax.swing.JPanel();
        lblResources = new javax.swing.JLabel();
        lblFuel = new javax.swing.JLabel();
        lblEnergy = new javax.swing.JLabel();
        lblDamage = new javax.swing.JLabel();
        barFuel = new javax.swing.JProgressBar();
        barEnergy = new javax.swing.JProgressBar();
        barDamage = new javax.swing.JProgressBar();
        panStartMission = new javax.swing.JPanel();
        lblMission = new javax.swing.JLabel();
        btnStartMission = new javax.swing.JButton();
        boxMission = new javax.swing.JComboBox<>();
        tabReports = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblCrewList = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCrewmates = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        lblAddCrewmate = new javax.swing.JLabel();
        boxOrigin = new javax.swing.JComboBox<>();
        lblCrewmateOrigin = new javax.swing.JLabel();
        lblCrewmateSenority = new javax.swing.JLabel();
        lblCrewmateEmploy = new javax.swing.JLabel();
        lblCrewmateID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        boxEmploy = new javax.swing.JComboBox<>();
        btnAddCrewmate = new javax.swing.JButton();
        intSenority = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        panBoard = new javax.swing.JPanel();
        lblBoard = new javax.swing.JLabel();

        
        setSize(new java.awt.Dimension(900, 600));

        lblResources.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblResources.setText("Resources");

        lblFuel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFuel.setText("Fuel");

        lblEnergy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEnergy.setText("Energy");

        lblDamage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDamage.setText("Damage");

        barFuel.setValue(67);
        barFuel.setStringPainted(true);

        barEnergy.setValue(67);
        barEnergy.setStringPainted(true);

        barDamage.setValue(67);
        barDamage.setStringPainted(true);

        javax.swing.GroupLayout panResourcesLayout = new javax.swing.GroupLayout(panResources);
        panResources.setLayout(panResourcesLayout);
        panResourcesLayout.setHorizontalGroup(
            panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResourcesLayout.createSequentialGroup()
                        .addComponent(lblResources, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panResourcesLayout.createSequentialGroup()
                        .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(barFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFuel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        panResourcesLayout.setVerticalGroup(
            panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResources, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEnergy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDamage)
                    .addComponent(barDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblMission.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMission.setText("Start mission");

        btnStartMission.setText("Start");

        boxMission.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select one>", "Assistance", "Picking", "Secure returm" }));

        javax.swing.GroupLayout panStartMissionLayout = new javax.swing.GroupLayout(panStartMission);
        panStartMission.setLayout(panStartMissionLayout);
        panStartMissionLayout.setHorizontalGroup(
            panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panStartMissionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxMission, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panStartMissionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStartMission))
                    .addGroup(panStartMissionLayout.createSequentialGroup()
                        .addComponent(lblMission, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panStartMissionLayout.setVerticalGroup(
            panStartMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panStartMissionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMission, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxMission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnStartMission)
                .addContainerGap())
        );

        jLabel5.setText("Bitacora re piola");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel5)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        tabReports.addTab("Binnacle", jPanel1);

        jLabel8.setText("Reportes de coso");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel8)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        tabReports.addTab("Final reports", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panResources, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panStartMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabReports))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panStartMission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabReports)
                .addContainerGap())
        );

        tabMain.addTab("Dashboard", jPanel2);

        lblCrewList.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCrewList.setText("Crewmate list");

        tableCrewmates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Employment", "Origin", "Senority", "Salary"
            }
        ));
        jScrollPane2.setViewportView(tableCrewmates);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblCrewList)
                        .addGap(0, 200, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCrewList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblAddCrewmate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAddCrewmate.setText("Add crewmate");

        boxOrigin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select one>", "Terricola", "Marciano", "Vulcano" }));

        lblCrewmateOrigin.setText("Origin");

        lblCrewmateSenority.setText("Senority");

        lblCrewmateEmploy.setText("Employment");

        lblCrewmateID.setText("ID");

        txtID.setText("jTextField1");

        boxEmploy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select one>", "Capitan", "Teniente", "Alferez", "Consejero" }));

        btnAddCrewmate.setText("Add");

        intSenority.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lblAddCrewmate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrewmateEmploy)
                            .addComponent(lblCrewmateID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCrewmateOrigin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCrewmateSenority, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxOrigin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID)
                            .addComponent(boxEmploy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(intSenority)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddCrewmate)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddCrewmate)
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateOrigin)
                    .addComponent(boxOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxEmploy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrewmateEmploy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrewmateSenority)
                    .addComponent(intSenority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddCrewmate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tabMain.addTab("Crew", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );

        tabMain.addTab("Visor??", jPanel6);

        lblBoard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblBoard.setText("Real-Time board");

        javax.swing.GroupLayout panBoardLayout = new javax.swing.GroupLayout(panBoard);
        panBoard.setLayout(panBoardLayout);
        panBoardLayout.setHorizontalGroup(
            panBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        panBoardLayout.setVerticalGroup(
            panBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(panBoard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glass Group Control Center");
        setResizable(false);
        pack();
    }

    @Override
    public void setControlador(Controlador c) {
     
    }
                                     

                       
}
*/