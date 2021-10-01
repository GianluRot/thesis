package it.unibas.controllo;

import it.unibas.Applicazione;
import it.unibas.Costanti;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.KeyStroke;

public class ControlloPrincipale {
    
    private Action azioneApriSenza = new AzioneApriSenza();
    private Action azioneApriCon = new AzioneApriCon();

    public Action getAzioneApriSenza() {
        return azioneApriSenza;
    }

    public Action getAzioneApriCon() {
        return azioneApriCon;
    }
    
    private class AzioneApriSenza extends AbstractAction{
        
        public AzioneApriSenza(){
            this.putValue(NAME, "PDF SENZA IMMAGINI");
            this.putValue(SHORT_DESCRIPTION, "permette di effettuare operazioni su pdf che NON contengono immagini");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl a"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getModello().putBean(Costanti.STATO, "senza");
            Applicazione.getInstance().getFinestraSelezionaPdf().mostra();
        }
        
    }
    
    private class AzioneApriCon extends AbstractAction{
        
        public AzioneApriCon(){
            this.putValue(NAME, "PDF CON IMMAGINI");
            this.putValue(SHORT_DESCRIPTION, "permette di effettuare operazioni su pdf che  CONTENGONO immagini");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl b"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_B);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getModello().putBean(Costanti.STATO, "con");
            Applicazione.getInstance().getFinestraSelezionaPdf().mostra();
        }
        
    }
    
}
