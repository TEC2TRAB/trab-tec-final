/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Dao.ClienteDao;
import javax.swing.JOptionPane;
import Classes.*;
import ModuloDePessoas.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar.nascimento
 */
public class Cons_Clien extends javax.swing.JFrame {

    /**
     * Creates new form Cons_Clien
     */
    public Cons_Clien() {
        initComponents();
        ButtonColumnEditClien bc = new ButtonColumnEditClien(jTableCliente,2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1NomeCliente = new javax.swing.JLabel();
        jTextCPF = new javax.swing.JTextField();
        jLabel1CPF = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jRadioButtonCPF = new javax.swing.JRadioButton();
        jRadioButtonNome = new javax.swing.JRadioButton();
        jLabel1Selecione = new javax.swing.JLabel();
        jButtonConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jButtonCancelar = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1NomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1NomeCliente.setText("CPF:");

        jTextCPF.setName(""); // NOI18N

        jLabel1CPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1CPF.setText("Nome:");

        jTextNome.setName(""); // NOI18N

        jRadioButtonCPF.setText("CPF");
        jRadioButtonCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCPFActionPerformed(evt);
            }
        });

        jRadioButtonNome.setText("Nome");
        jRadioButtonNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNomeActionPerformed(evt);
            }
        });

        jLabel1Selecione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1Selecione.setText("Selecione: ");

        jButtonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        jButtonConsultar.setText("Consultar");
        jButtonConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonConsultarMousePressed(evt);
            }
        });

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Editar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.setRowHeight(24);
        jScrollPane1.setViewportView(jTableCliente);

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCancelarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1NomeCliente)
                                .addComponent(jLabel1Selecione))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButtonCPF)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonNome))
                                .addComponent(jTextCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonConsultar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1CPF)
                                .addGap(18, 18, 18)
                                .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1Selecione)
                    .addComponent(jRadioButtonCPF)
                    .addComponent(jRadioButtonNome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1NomeCliente)
                    .addComponent(jTextCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1CPF)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultar)
                    .addComponent(jButtonCancelar))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCPFActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableCliente.getModel();
        model.setNumRows(0);
        if(jRadioButtonCPF.isSelected()==true){
            jTextCPF.setText("");
            jTextNome.setText("");
            jRadioButtonNome.setSelected(false);
            jTextCPF.setEnabled(true);
            jTextNome.setEnabled(false);
        }else{
            jTextNome.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonCPFActionPerformed

    private void jRadioButtonNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNomeActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableCliente.getModel();
        model.setNumRows(0);
        if(jRadioButtonNome.isSelected()==true){
            jTextCPF.setText("");
            jTextNome.setText("");
            jRadioButtonCPF.setSelected(false);
            jTextNome.setEnabled(true);
            jTextCPF.setEnabled(false);
        }else{
            jTextCPF.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonNomeActionPerformed

    private void jButtonConsultarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonConsultarMousePressed
        ClienteDao c = new ClienteDao();
        List<Cliente> clientes = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) jTableCliente.getModel();
        model.setNumRows(0);
        int passa = 0;
        if(jRadioButtonCPF.isSelected()==false && jRadioButtonNome.isSelected()==false){
            JOptionPane.showMessageDialog(this,"Você precisa selecionar uma forma de pesquisa antes de clicar em \"Consultar\", por CPF ou por Nome.", "Alerta", JOptionPane.WARNING_MESSAGE);
            passa = 1;
        }
        if(jRadioButtonCPF.isSelected()==true && jTextCPF.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Campo CPF em branco, por favor, preencha e pesquise novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
            jTextCPF.requestFocus();
            passa = 1;
        }else if(jRadioButtonCPF.isSelected()==true){
            if(ValidadorCPF.TestaCPF(jTextCPF.getText())==true){
                clientes = c.consultar(Long.parseLong(jTextCPF.getText()));
                passa = 0;
            }else{
               JOptionPane.showMessageDialog(this,"CPF inválido, por favor, digite um CPF válido e pesquise novamente.", "Alerta", JOptionPane.WARNING_MESSAGE); 
               jTextCPF.setText("");
               jTextCPF.requestFocus();
               passa = 1;
            }
        }
        
        if(jRadioButtonNome.isSelected()==true && jTextNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Campo Nome em branco, por favor, preencha e pesquise novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
            passa = 1;
        }else if(jRadioButtonNome.isSelected()==true){
            clientes = c.consultar(jTextNome.getText());
        }
        if(passa == 0 && clientes.isEmpty()){
            JOptionPane.showMessageDialog(this, "A pesquisa não retornou dados, tente novamente.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }else{
            Object vec[] = new Object[2];
            String cpf;
            for(Cliente cl : clientes){
                cpf = Long.toString(cl.getCPF());
                if(cpf.length()!=11){
                    while(cpf.length()!=11){
                        cpf = "0"+cpf;
                    }
                }
                vec[0] = cl.getNome();
                vec[1] = cpf;
                model.addRow(vec);
            }
        }
    }//GEN-LAST:event_jButtonConsultarMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jRadioButtonCPF.setSelected(true);
        jTextNome.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void jButtonCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelarMousePressed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarMousePressed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JLabel jLabel1CPF;
    private javax.swing.JLabel jLabel1NomeCliente;
    private javax.swing.JLabel jLabel1Selecione;
    private javax.swing.JRadioButton jRadioButtonCPF;
    private javax.swing.JRadioButton jRadioButtonNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextField jTextCPF;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables
}
