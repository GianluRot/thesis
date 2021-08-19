
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.SwingUtilities;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class Applicazione {

    private static Applicazione instance = new Applicazione();

    public Applicazione() {
    }

    public void inizializza() throws Exception {
          File file = new File("C:/Users/rotun/Desktop/file-sample_150kB.pdf");
        //File file = new File("C:/Users/rotun/Desktop/REGOLAMENTO DIDATTICO CDL L-31 2019-2020.pdf");
        //File file = new File("C:/Users/rotun/Desktop/REGOLAMENTO DIDATTICO CDLM LM-32 2019-2020.pdf");

        FileInputStream in = new FileInputStream(file);
        PDDocument pdf = PDDocument.load(in);
        System.out.println("numero di pagine: " + pdf.getNumberOfPages() + "\n");
        System.out.println("numero di versione: " + pdf.getVersion() + "\n");
        System.out.println(pdf.getVersion() + "\n");

        PDDocumentInformation metadati = pdf.getDocumentInformation();
        System.out.println("Title=" + metadati.getTitle());
        System.out.println("Author=" + metadati.getAuthor());
        System.out.println("Subject=" + metadati.getSubject());
        System.out.println("Keywords=" + metadati.getKeywords());
        System.out.println("Creator=" + metadati.getCreator());
        System.out.println("Producer=" + metadati.getProducer());
        DateFormat dfCreazione = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Creation Date=" + dfCreazione.format(metadati.getCreationDate().getTime()));
        DateFormat dfModifica = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Modification Date=" + dfModifica.format(metadati.getModificationDate().getTime()));
        System.out.println("Trapped=" + metadati.getTrapped());

        metadati.setAuthor(null);
        metadati.setCreationDate(new GregorianCalendar(0, 0, 0));
        metadati.setModificationDate(new GregorianCalendar(0, 0, 0));
        metadati.setTitle(null);
        metadati.setSubject(null);
        metadati.setCreator(null);
        metadati.setProducer(null);
        metadati.setKeywords(null);

        pdf.save(file);
        pdf.close();
        in.close();
    }

    public static Applicazione getInstance() {
        return instance;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    instance.inizializza();
                } catch (Exception ex) {
                    System.out.println("-----------------------------ERRORE-----------------------------");
                }
            }
        });
    }
}
