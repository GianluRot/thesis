package it.unibas.vista;

import it.unibas.Applicazione;
import it.unibas.Costanti;
import it.unibas.modello.CalcolatorePericolo;
import it.unibas.modello.Metadata;
import static java.awt.SystemColor.text;
import java.util.List;

public class FinestraEstraiMetadati extends javax.swing.JDialog {

    public FinestraEstraiMetadati(VistaFrame parent) {
        super(parent, true);
    }

    public void inizializza() {
        initComponents();
        initActions();
        
    }

    public void mostra() {
        pannelloImmagini.setVisible(true);
        aggiornaTabelle();
        
        if (Applicazione.getInstance().getModello().getBean(Costanti.STATO).equals("senza")) {
            pannelloImmagini.setVisible(false);
        }
        System.out.println("Stato: " + Applicazione.getInstance().getModello().getBean(Costanti.STATO));
        aggiornaTabelle();
        aggiornaLabel();
        this.pack();
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
    }
    
    private void aggiornaLabel(){
        int pericolo = CalcolatorePericolo.calcolaPericolo();
        labelPericolo.setText("il pericolo stimato per questo PDF in base ai dati raccolti è " + pericolo + "/5");
    }
    
    private void aggiornaTabelle(){
        aggiornaTabellaPdf();
        aggiornaTabellaFirma();
        aggiornaTabellaImmagini();
    }
    
    private void aggiornaTabellaPdf(){
        List<Metadata> listaMetadati =  (List<Metadata>) Applicazione.getInstance().getModello().getBean(Costanti.METADATI_PDF);
        ModelloTabellaMetadati modelloTabella = new ModelloTabellaMetadati(listaMetadati);
        tabellaPdf.setModel(modelloTabella);
        modelloTabella.aggiornaTabella();
        /*
        int i = 0;
        for(Metadata dato : listaMetadati){
            System.out.println(i + ")");
            System.out.println(dato);
            i++;
        }
*/
    }
    
    private void aggiornaTabellaFirma(){
        List<Metadata> lista = (List<Metadata>) Applicazione.getInstance().getModello().getBean(Costanti.METADATI_FIRMA);
        ModelloTabellaMetadati modelloTabellaFirma = new ModelloTabellaMetadati(lista);
        tabellaFirma.setModel(modelloTabellaFirma);
    }
    
    private void aggiornaTabellaImmagini(){
        List<Metadata> lista = (List<Metadata>) Applicazione.getInstance().getModello().getBean(Costanti.METADATI_IMMAGINE);
        ModelloTabellaMetadati modelloTabellaImmagini = new ModelloTabellaMetadati(lista);
        tabellaImmagini.setModel(modelloTabellaImmagini);
    }

    private void initActions() {
        bottoneElimina.setAction(Applicazione.getInstance().getControlloSelezionaPdf().getAzioneApriEliminaMetadati());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaPdf = new javax.swing.JTable();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabellaFirma = new javax.swing.JTable();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        labelPericolo = new javax.swing.JLabel();
        bottoneElimina = new javax.swing.JButton();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        pannelloImmagini = new javax.swing.JPanel();
        labelImmagini = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabellaImmagini = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabellaPdf.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabellaPdf);

        jLabel1.setText("PDF:");

        jLabel2.setText("Firma:");

        tabellaFirma.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabellaFirma);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelPericolo.setText("jLabel4");

        bottoneElimina.setText("jButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPericolo, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bottoneElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelPericolo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottoneElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Ecco un riepilogo di tutti i metadati recuperati");

        labelImmagini.setText("Immagini:");

        tabellaImmagini.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabellaImmagini);

        javax.swing.GroupLayout pannelloImmaginiLayout = new javax.swing.GroupLayout(pannelloImmagini);
        pannelloImmagini.setLayout(pannelloImmaginiLayout);
        pannelloImmaginiLayout.setHorizontalGroup(
            pannelloImmaginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
            .addGroup(pannelloImmaginiLayout.createSequentialGroup()
                .addComponent(labelImmagini)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pannelloImmaginiLayout.setVerticalGroup(
            pannelloImmaginiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloImmaginiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImmagini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 330, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(pannelloImmagini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pannelloImmagini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneElimina;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelImmagini;
    private javax.swing.JLabel labelPericolo;
    private javax.swing.JPanel pannelloImmagini;
    private javax.swing.JTable tabellaFirma;
    private javax.swing.JTable tabellaImmagini;
    private javax.swing.JTable tabellaPdf;
    // End of variables declaration//GEN-END:variables
}
