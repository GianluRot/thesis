package it.unibas;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import it.unibas.controllo.ControlloEliminaMetadati;
import it.unibas.controllo.ControlloEstraiMetadati;
import it.unibas.controllo.ControlloPrincipale;
import it.unibas.controllo.ControlloSelezionaPdf;
import it.unibas.modello.CalcolatorePericolo;
import it.unibas.modello.Modello;
import it.unibas.vista.FinestraEliminaMetadati;
import it.unibas.vista.FinestraEstraiMetadati;
import it.unibas.vista.FinestraSelezionaPdf;
import it.unibas.vista.VistaFrame;
import it.unibas.vista.PannelloPrincipale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;



public class Applicazione {

    private static Applicazione instance = new Applicazione();
    private Modello modello;
    
    private CalcolatorePericolo calcolatorePericolo;
    
    private VistaFrame vistaFrame;
    private PannelloPrincipale pannelloPrincipale;
    private ControlloPrincipale controlloPrincipale;

    private FinestraSelezionaPdf finestraSelezionaPdf;
    private ControlloSelezionaPdf controlloSelezionaPdf;

    private FinestraEliminaMetadati finestraEliminaMetadati;
    private ControlloEliminaMetadati controlloEliminaMetadati;

    private FinestraEstraiMetadati finestraEstraiMetadati;
    private ControlloEstraiMetadati controlloEstraiMetadati;

    public Applicazione() {
    }

    public void inizializza() {
        
        modello = new Modello();
        calcolatorePericolo = new CalcolatorePericolo();
        
        vistaFrame = new VistaFrame();
        pannelloPrincipale = new PannelloPrincipale();
        controlloPrincipale = new ControlloPrincipale();

        finestraSelezionaPdf = new FinestraSelezionaPdf(vistaFrame);
        controlloSelezionaPdf = new ControlloSelezionaPdf();

        finestraEliminaMetadati = new FinestraEliminaMetadati(vistaFrame);
        controlloEliminaMetadati = new ControlloEliminaMetadati();

        finestraEstraiMetadati = new FinestraEstraiMetadati(vistaFrame);
        controlloEstraiMetadati = new ControlloEstraiMetadati();

        finestraEstraiMetadati.inizializza();
        finestraEliminaMetadati.inizializza();
        finestraSelezionaPdf.inizializza();
        pannelloPrincipale.inizializza();
        vistaFrame.inizilizza();

        //EstrattoreImmagini estrattoreImmagini = new EstrattoreImmagini();
        //estrattoreImmagini.estrai(in);
    }

    private void cercaAutoreDalContesto(FileInputStream in) throws IOException, ClassCastException, ClassNotFoundException {
        PDDocument pdf = PDDocument.load(in);
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(1);
        stripper.setEndPage(pdf.getNumberOfPages());
        String text = stripper.getText(pdf);
        StringTokenizer st = new StringTokenizer(text);

        //lista di tutte le parole contenute nel testo
        List<String> listaParole = new ArrayList<>();
        while (st.hasMoreTokens()) {
            listaParole.add(st.nextToken());
        }

        //logger.debug(text);
        String[] example = {text};

        String serializedClassifier = "english.muc.7class.distsim.crf.ser.gz";

        AbstractSequenceClassifier classifier = CRFClassifier.getClassifier(serializedClassifier);
        /*
        String[] example = {"Buon pomeriggio Peppiniello Raina, come stai oggi?",
            "Io vado a scuola il september twelve 2021 alla Stanford University, che si trova in California."};
         */
        for (String str : example) {
            System.out.println(classifier.classifyToString(str));
        }
        System.out.println("---");

        for (String str : listaParole) {
            // This one puts in spaces and newlines between tokens, so just print not println.
            System.out.print(classifier.classifyToString(str, "slashTags", false));
        }
        System.out.println("---");

        for (String str : listaParole) {
            // This one is best for dealing with the output as a TSV (tab-separated column) file.
            // The first column gives entities, the second their classes, and the third the remaining text in a document
            System.out.print(classifier.classifyToString(str, "tabbedEntities", false));
        }
        System.out.println("---");

        for (String str : listaParole) {
            System.out.println(classifier.classifyWithInlineXML(str));
        }
        System.out.println("---");

        for (String str : listaParole) {
            System.out.println(classifier.classifyToString(str, "xml", true));
        }
        System.out.println("---");

        for (String str : listaParole) {
            System.out.print(classifier.classifyToString(str, "tsv", false));
        }
        System.out.println("---");

    }

    private void cercaAutoreDaiMetadati(FileInputStream in) throws Exception {
        PDDocument pdf = PDDocument.load(in);
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(1);
        stripper.setEndPage(pdf.getNumberOfPages());
        String text = stripper.getText(pdf);
        //System.out.println(text);

        List<String> listaParole = new ArrayList<>();

        String autore = "Interstruttura";

        PDDocumentInformation metadati = pdf.getDocumentInformation();
        String nomeAutore = metadati.getAuthor();

        StringTokenizer stAutore = new StringTokenizer(autore);
        String tokenAutore = stAutore.nextToken();
        System.out.println(tokenAutore);

        StringTokenizer st = new StringTokenizer(text);

        while (st.hasMoreTokens()) {
            listaParole.add(st.nextToken());
        }
        for (String parola : listaParole) {
            if (parola.equalsIgnoreCase(autore)) {
                System.out.println("Il documento contiene il nome dell' autore");
            }
        }
    }

    public VistaFrame getVistaFrame() {
        return vistaFrame;
    }

    public PannelloPrincipale getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public ControlloPrincipale getControlloPrincipale() {
        return controlloPrincipale;
    }

    public FinestraSelezionaPdf getFinestraSelezionaPdf() {
        return finestraSelezionaPdf;
    }

    public ControlloSelezionaPdf getControlloSelezionaPdf() {
        return controlloSelezionaPdf;
    }

    public Modello getModello() {
        return modello;
    }

    public FinestraEliminaMetadati getFinestraEliminaMetadati() {
        return finestraEliminaMetadati;
    }

    public ControlloEliminaMetadati getControlloEliminaMetadati() {
        return controlloEliminaMetadati;
    }

    public FinestraEstraiMetadati getFinestraEstraiMetadati() {
        return finestraEstraiMetadati;
    }

    public ControlloEstraiMetadati getControlloEstraiMetadati() {
        return controlloEstraiMetadati;
    }

    public CalcolatorePericolo getCalcolatorePericolo() {
        return calcolatorePericolo;
    }
    
    

    public static Applicazione getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance.inizializza();
            }
        });
    }
}
