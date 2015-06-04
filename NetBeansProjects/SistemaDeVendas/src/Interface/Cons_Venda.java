/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author cesar.nascimento
 */
public class Cons_Venda extends javax.swing.JFrame {

    /**
     * Creates new form Cons_Venda
     */
    public Cons_Venda() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabeID = new javax.swing.JLabel();
        jTextID = new javax.swing.JTextField();
        jRadioButtonCPF = new javax.swing.JRadioButton();
        jRadioButtonID = new javax.swing.JRadioButton();
        jLabelCPF = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelSelecione = new javax.swing.JLabel();
        jTextCPF = new javax.swing.JTextField();
        jButtonConsultar = new javax.swing.JButton();
        jLabelData = new javax.swing.JLabel();
        jTextData = new javax.swing.JTextField();
        jRadioButtonData = new javax.swing.JRadioButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Vendas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabeID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabeID.setText("ID - Vendedor:");

        jTextID.setName(""); // NOI18N

        jRadioButtonCPF.setText("CPF");
        jRadioButtonCPF.setToolTipText("");
        jRadioButtonCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCPFActionPerformed(evt);
            }
        });

        jRadioButtonID.setText("ID");
        jRadioButtonID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIDActionPerformed(evt);
            }
        });

        jLabelCPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCPF.setText("CPF - Cliente:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID - Vendedor", "CPF - Cliente", "Data da Venda", "Ver Mais"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabelSelecione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSelecione.setText("Selecione: ");

        jTextCPF.setName(""); // NOI18N

        jButtonConsultar.setText("Consultar");

        jLabelData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelData.setText("Data da Venda:");

        jTextData.setName(""); // NOI18N

        jRadioButtonData.setText("Data");
        jRadioButtonData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDataActionPerformed(evt);
            }
        });

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonConsultar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelCPF)
                                .addComponent(jLabelSelecione))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButtonCPF)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonID)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonData))
                                .addComponent(jTextCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabeID)
                            .addGap(18, 18, 18)
                            .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelData)
                            .addGap(18, 18, 18)
                            .addComponent(jTextData, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSelecione)
                    .addComponent(jRadioButtonCPF)
                    .addComponent(jRadioButtonID)
                    .addComponent(jRadioButtonData))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCPF)
                    .addComponent(jTextCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabeID)
                    .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextData, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultar)
                    .addComponent(jButtonCancelar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCPFActionPerformed
        if(jRadioButtonCPF.isSelected()==true){
            jRadioButtonID.setSelected(false);
            jRadioButtonData.setSelected(false);
            jTextCPF.setEnabled(true);
            jTextID.setEnabled(false);
            jTextData.setEnabled(false);
        }else{
            jTextID.setEnabled(true);
            jTextData.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonCPFActionPerformed

    private void jRadioButtonIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonIDActionPerformed
        if(jRadioButtonID.isSelected()==true){
            jRadioButtonCPF.setSelected(false);
            jRadioButtonData.setSelected(false);
            jTextID.setEnabled(true);
            jTextCPF.setEnabled(false);
            jTextData.setEnabled(false);
        }else{
            jTextCPF.setEnabled(true);
            jTextData.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonIDActionPerformed

    private void jRadioButtonDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDataActionPerformed
        if(jRadioButtonData.isSelected()==true){
            jRadioButtonCPF.setSelected(false);
            jRadioButtonID.setSelected(false);
            jTextData.setEnabled(true);
            jTextCPF.setEnabled(false);
            jTextID.setEnabled(false);
        }else{
            jTextCPF.setEnabled(true);
            jTextID.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonDataActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jRadioButtonCPF.setSelected(true);
        jTextID.setEnabled(false);
        jTextData.setEnabled(false);
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
    private javax.swing.JLabel jLabeID;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelSelecione;
    private javax.swing.JRadioButton jRadioButtonCPF;
    private javax.swing.JRadioButton jRadioButtonData;
    private javax.swing.JRadioButton jRadioButtonID;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextCPF;
    private javax.swing.JTextField jTextData;
    private javax.swing.JTextField jTextID;
    // End of variables declaration//GEN-END:variables
}
