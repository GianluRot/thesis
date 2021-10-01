package it.unibas.vista;

import it.unibas.Applicazione;
import it.unibas.Costanti;
import it.unibas.modello.Metadata;
import java.util.List;

public class FinestraEliminaMetadati extends javax.swing.JDialog {

    public FinestraEliminaMetadati(VistaFrame parent) {
        super(parent, true);
    }
    
    public void inizializza(){
        initComponents();
        ModelloTabellaMetadati modelloTabella = new ModelloTabellaMetadati(null);
        
    }
    public void mostra(){
        aggiornaTabella();
        this.pack();
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }
    
    public void aggiornaTabella(){
        List<Metadata> listaMetadati =  (List<Metadata>) Applicazione.getInstance().getModello().getBean(Costanti.METADATI_PDF);
        ModelloTabellaMetadati modelloTabella = new ModelloTabellaMetadati(listaMetadati);
        tabellaMetadati.setModel(modelloTabella);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaMetadati = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabellaMetadati.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabellaMetadati);

        jLabel1.setText("Ecco i tuoi metadati ora:");

        jLabel2.setText("(Un campo vuoto implica che il valore corrispondente è stato eliminato correttamente)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabellaMetadati;
    // End of variables declaration//GEN-END:variables
}
