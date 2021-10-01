package it.unibas.vista;

import it.unibas.Applicazione;



public class PannelloPrincipale extends javax.swing.JPanel {

    public void inizializza() {
        initComponents();
        initActions();
    }

    
    public void initActions(){
        bottoneApriPannelloSelezionaPdf.setAction(Applicazione.getInstance().getControlloPrincipale().getAzioneApriSenza());
        bottoneApriPannelloSelezionaPdfI.setAction(Applicazione.getInstance().getControlloPrincipale().getAzioneApriCon());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bottoneApriPannelloSelezionaPdf = new javax.swing.JButton();
        bottoneApriPannelloSelezionaPdfI = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel2.setText("Scegli il tipo di pdf che vuoi analizzare:");

        bottoneApriPannelloSelezionaPdf.setText("PDF semplice");

        bottoneApriPannelloSelezionaPdfI.setText("PDF con immagini");
        bottoneApriPannelloSelezionaPdfI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneApriPannelloSelezionaPdfIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bottoneApriPannelloSelezionaPdfI, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addComponent(bottoneApriPannelloSelezionaPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(bottoneApriPannelloSelezionaPdf)
                .addGap(26, 26, 26)
                .addComponent(bottoneApriPannelloSelezionaPdfI)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bottoneApriPannelloSelezionaPdfIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneApriPannelloSelezionaPdfIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bottoneApriPannelloSelezionaPdfIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneApriPannelloSelezionaPdf;
    private javax.swing.JButton bottoneApriPannelloSelezionaPdfI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
