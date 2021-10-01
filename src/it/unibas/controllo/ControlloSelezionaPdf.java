package it.unibas.controllo;

import it.unibas.Applicazione;
import it.unibas.Costanti;
import it.unibas.modello.Metadata;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.KeyStroke;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class ControlloSelezionaPdf {

    private Action azioneApriEstraiMetadati = new AzioneApriEstraiMetadati();
    private Action azioneApriEliminaMetadati = new AzioneApriEliminaMetadati();

    public Action getAzioneApriEstraiMetadati() {
        return azioneApriEstraiMetadati;
    }

    public Action getAzioneApriEliminaMetadati() {
        return azioneApriEliminaMetadati;
    }

    private class AzioneApriEstraiMetadati extends AbstractAction {

        public AzioneApriEstraiMetadati() {
            this.putValue(NAME, "Estrai Metadati");
            this.putValue(SHORT_DESCRIPTION, "permette di visualizzare in una tabella tutti i metadati che è possibile estrarre dal pdf");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl s"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            File file = new File("C:/Users/rotun/Desktop/lavori per tesi/pdf vari/pulito.pdf");
            if (file == null) {
                Applicazione.getInstance().getVistaFrame().mostraMessagioErrore("Non è stato caricato nessun pdf, riprovare");
                return;
            }
            PDDocument pdf = null;
            try {
                FileInputStream in = new FileInputStream(file);
                pdf = PDDocument.load(in);
                //System.out.println("numero di pagine: " + pdf.getNumberOfPages() + "\n");
                //System.out.println("numero di versione: " + pdf.getVersion() + "\n");

                estraiMetadatiPdf(pdf);
                estraiMetadatiImmagine();
                estraiMetadatiFirma(pdf);
            } catch (IOException ex) {
                System.out.println(ex);
            } finally {
                if (pdf != null) {
                    try {
                        pdf.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
            Applicazione.getInstance().getFinestraEstraiMetadati().mostra();
        }
    }

    private void estraiMetadatiPdf(PDDocument pdf) {
        Applicazione.getInstance().getModello().putBean(Costanti.METADATI_PDF, null);
        List<Metadata> metadatiPdf = new ArrayList<>();
        PDDocumentInformation metadati = pdf.getDocumentInformation();
        metadatiPdf.add(new Metadata("Title", metadati.getTitle()));
        metadatiPdf.add(new Metadata("Author", metadati.getAuthor()));
        metadatiPdf.add(new Metadata("Subject", metadati.getSubject()));
        metadatiPdf.add(new Metadata("Keywords", metadati.getKeywords()));
        metadatiPdf.add(new Metadata("Creator", metadati.getKeywords()));
        metadatiPdf.add(new Metadata("Producer", metadati.getProducer()));
        metadatiPdf.add(new Metadata("Trapped", metadati.getTrapped()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        if (metadati.getCreationDate() == null) {
            metadatiPdf.add(new Metadata("Creation Date", null));
            metadatiPdf.add(new Metadata("Modification Date", null));
        } else {
            metadatiPdf.add(new Metadata("Creation Date", df.format(metadati.getCreationDate().getTime())));
            metadatiPdf.add(new Metadata("Modification Date", df.format(metadati.getModificationDate().getTime())));
        }

        Applicazione.getInstance().getModello().putBean(Costanti.METADATI_PDF, metadatiPdf);
    }

    private void estraiMetadatiImmagine() throws FileNotFoundException {
        Applicazione.getInstance().getModello().putBean(Costanti.METADATI_IMMAGINE, null);
        if (Applicazione.getInstance().getModello().getBean(Costanti.STATO).equals("con")) {
            List<Metadata> metadatiImmagine = new ArrayList<>();

            System.out.println("-----------INFORMAZIONI SULL' IMMAGINE--------------");

            BodyContentHandler handler = new BodyContentHandler();
            org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
            //FileInputStream inputstream = new FileInputStream(new File("C:/Users/rotun/Desktop/lavori per tesi/progetto/image_1.jpg"));
            FileInputStream inputstream = new FileInputStream(new File("C:/Users/rotun/Desktop/P_20210923_105744.jpg"));
            ParseContext pcontext = new ParseContext();

            JpegParser JpegParser = new JpegParser();
            try {
                JpegParser.parse(inputstream, handler, metadata, pcontext);
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (SAXException ex) {
                System.out.println(ex);
            } catch (TikaException ex) {
                System.out.println(ex);
            } finally {
                if (inputstream != null) {
                    //inputstream.close();
                }
            }
            System.out.println("Contenuti:");
            System.out.println("Metadati del documento:");
            String[] listaMetadatiImmagine = metadata.names();

            for (String nome : listaMetadatiImmagine) {
                metadatiImmagine.add(new Metadata(nome, metadata.get(nome)));
            }

            Applicazione.getInstance().getModello().putBean(Costanti.METADATI_IMMAGINE, metadatiImmagine);
        }
    }

    private void estraiMetadatiFirma(PDDocument pdf) {
        Applicazione.getInstance().getModello().putBean(Costanti.METADATI_FIRMA, null);
        List<Metadata> metadatiFirma = new ArrayList<>();

        System.out.println("-----------INFORMAZIONI SULLA FIRMA--------------");
        PDSignature signature = null;
        try {
            signature = pdf.getLastSignatureDictionary();

            if (signature != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");

                metadatiFirma.add(new Metadata("Name", signature.getName()));
                metadatiFirma.add(new Metadata("Signature date", df.format(signature.getSignDate().getTime())));
                metadatiFirma.add(new Metadata("Location", signature.getLocation()));

                System.out.println("contact info " + signature.getContactInfo());
                System.out.println("sub filter " + signature.getSubFilter());
                Applicazione.getInstance().getModello().putBean(Costanti.METADATI_FIRMA, metadatiFirma);
            }
        } catch (IOException ex) {
            System.out.println("errore durante il caricamento della firma");
        }

    }

    private class AzioneApriEliminaMetadati extends AbstractAction {

        public AzioneApriEliminaMetadati() {
            this.putValue(NAME, "Elimina Metadati");
            this.putValue(SHORT_DESCRIPTION, "permette di eliminare tutti i metadati dal pdf");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl e"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            File file = new File("C:/Users/rotun/Desktop/lavori per tesi/pdf vari/pulito.pdf");
            if (file == null) {
                Applicazione.getInstance().getVistaFrame().mostraMessagioErrore("Non è stato caricato nessun pdf, riprovare");
                return;
            }
            try {
                FileInputStream in = new FileInputStream(file);
                PDDocument pdf = null;
                try {
                    pdf = PDDocument.load(in);

                    System.out.println("numero di pagine: " + pdf.getNumberOfPages() + "\n");
                    System.out.println("numero di versione: " + pdf.getVersion() + "\n");

                    eliminaMetadati(pdf);
                    pdf.save(file);
                    pdf.close();
                    in.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } finally {
                /////////////////////////////////////////////TODO
            }
            Applicazione.getInstance().getFinestraEliminaMetadati().mostra();
        }
    }

    private void eliminaMetadati(PDDocument pdf) {
        System.out.println("-----------MODIFICO METADATI--------------");
        PDDocumentInformation metadati = pdf.getDocumentInformation();
        metadati.setAuthor(null);
        metadati.setCreationDate(null);
        metadati.setModificationDate(null);
        metadati.setTitle(null);
        metadati.setSubject(null);
        metadati.setCreator(null);
        metadati.setProducer(null);
        metadati.setKeywords(null);
        estraiMetadatiPdf(pdf);

        ////////////////////////////////FIRMA////////////////////////////
        //signature.setName(null);
        //
        //
        ////////////////////////////////////IMMAGINE/////////////////////
        //metadata.setAll(null);
        /*
        System.out.println("------------DATI DOPO LA MODIFICA--------------");
        System.out.println("Title=" + metadati.getTitle());
        System.out.println("Author=" + metadati.getAuthor());
        System.out.println("Subject=" + metadati.getSubject());
        System.out.println("Keywords=" + metadati.getKeywords());
        System.out.println("Creator=" + metadati.getCreator());
        System.out.println("Producer=" + metadati.getProducer());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
        if (metadati.getCreationDate() == null) {
            System.out.println("Creation Date=" + metadati.getCreationDate());
            System.out.println("Modification Date=" + metadati.getModificationDate());
        } else {
            System.out.println("Creation Date=" + df.format(metadati.getCreationDate().getTime()));
            System.out.println("Modification Date=" + df.format(metadati.getModificationDate().getTime()));
        }
         */
    }
}
