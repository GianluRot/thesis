package it.unibas.modello;

import it.unibas.Applicazione;
import it.unibas.Costanti;

import java.util.List;

public class CalcolatorePericolo {

    public static int calcolaPericolo() {
        int somma = 0;
        List<Metadata> lista = (List<Metadata>) Applicazione.getInstance().getModello().getBean(Costanti.METADATI_PDF);        
        for (Metadata dato : lista) {
            if (dato.getNome().equals("Author") && dato.getValore() != null) {
                somma+=2;
            }
            if (dato.getNome().equals("Producer") && dato.getValore() != null) {
                somma+=2;
            }
            if (dato.getNome().equals("Modification Date") && dato.getValore() != null) {
                somma++;
            }
            if (dato.getNome().equals("Creation Date") && dato.getValore() != null) {
                somma++;
            }
        }
        int pericolo = (5*somma)/6;
        
        return pericolo;
    }
}
