package vista;

import Controlador.Controlador;
import javax.swing.*;

public class winRegister extends JFrame implements InterfazVista{
    
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblConfPassword;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblHeader1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtConfPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(winRegister.class.getName());

    
    public winRegister() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // <editor-fold defaultstate="collapsed" desc="Variables (Labels, Buttons, etc)">                          
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtConfPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        lblHeader = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblConfPassword = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        lblHeader1 = new javax.swing.JLabel();

        btnRegister.setText("Register");
        lblUsername.setText("Set username");
        lblPassword.setText("Set password");
        lblConfPassword.setText("Confirm password");
        btnCancel.setText("Cancel");
        
        
        
        lblHeader.setFont(new java.awt.Font("Franklin Gothic Medium", 2, 36)); 
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("GLASS GROUP");
        
        lblHeader1.setFont(new java.awt.Font("Franklin Gothic Medium", 2, 18));
        lblHeader1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader1.setText("Control Command");
        // </editor-fold>       
        
        // <editor-fold defaultstate="collapsed" desc="[!!!] ACTION COMMANDS (Importante)">
        
        btnRegister.setActionCommand("_REGISTERIN");
        btnCancel.setActionCommand("_CANCEL");
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) Organizator (No le den bola a esto)">                          
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsername)
                    .addComponent(lblPassword)
                    .addComponent(lblConfPassword)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword)
                    .addComponent(txtConfPassword))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnRegister))
                .addGap(18, 18, 18))
        );
        // </editor-fold>       
        
        // <editor-fold defaultstate="collapsed" desc="(NO TOCAR) Style Selector (Windows)">                          
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
        // </editor-fold>       
        
        setTitle("G.G.C.C - Register");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }
    
    //####################################################################################################################
    //                     SETTERS // GETTERS
    //####################################################################################################################

    @Override
    public void setControlador(Controlador c) {
        btnCancel.addActionListener(c);
        btnRegister.addActionListener(c);
    }

    @Override
    public String getUsername() {
        return txtUsername.getText();
    }
    
    @Override
    public String getPassword(){
        return txtPassword.getText();
    }
    @Override
    public String getConfPassword(){
        return txtConfPassword.getText();
    }

    @Override
    public void mensajeAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
