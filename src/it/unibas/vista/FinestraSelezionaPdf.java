package it.unibas.vista;

import it.unibas.Applicazione;


public class FinestraSelezionaPdf extends javax.swing.JDialog {

    public FinestraSelezionaPdf(VistaFrame parent) {
        super(parent, true);

    }

    public void inizializza() {
        initComponents();
        initActions();
    }

    public void mostra() {
        this.pack();
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
        
        attivaDragEDrop();
    }

    private void attivaDragEDrop() {
        

    }

    private void initActions() {
        bottoneEstrai.setAction(Applicazione.getInstance().getControlloSelezionaPdf().getAzioneApriEstraiMetadati());
        bottoneElimina.setAction(Applicazione.getInstance().getControlloSelezionaPdf().getAzioneApriEliminaMetadati());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottoneEstrai = new javax.swing.JButton();
        bottoneElimina = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        contenitorePdf = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bottoneEstrai.setText("jButton1");

        bottoneElimina.setText("jButton2");

        jLabel1.setText("Trascina il file che vuoi analizzare");

        jScrollPane2.setViewportView(contenitorePdf);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bottoneEstrai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottoneElimina))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottoneEstrai)
                    .addComponent(bottoneElimina))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneElimina;
    private javax.swing.JButton bottoneEstrai;
    private javax.swing.JList contenitorePdf;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
