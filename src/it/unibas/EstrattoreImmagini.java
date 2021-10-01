package it.unibas;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
 
import javax.imageio.ImageIO;

public class EstrattoreImmagini extends PDFStreamEngine{
        
    public EstrattoreImmagini() throws IOException{}
 
    public int imageNumber = 1;

    public  void estrai(FileInputStream in) throws IOException {
        PDDocument document = null;
        try{
            
            document = PDDocument.load(in);
            System.out.println("errore qui");
            EstrattoreImmagini estrattore = new EstrattoreImmagini();
            int pageNum = 0;
            for( PDPage page : document.getPages() ){
                pageNum++;
                System.out.println( "Processing page: " + pageNum );
                estrattore.processPage(page);
            }
        }
        finally{
            if( document != null ){
                document.close();
            }
        }
    }
 
    @Override
    protected void processOperator( Operator operator, List<COSBase> operands) throws IOException{
        String operation = operator.getName();
        if( "Do".equals(operation) ){
            COSName objectName = (COSName) operands.get( 0 );
            PDXObject xobject = getResources().getXObject( objectName );
            if( xobject instanceof PDImageXObject){
                PDImageXObject image = (PDImageXObject)xobject;
 
                // same image to local
                BufferedImage bImage = image.getImage();
                ImageIO.write(bImage,"JPEG",new File("image_"+imageNumber+".jpg"));
                System.out.println("Image saved.");
                imageNumber++;
 
            }
            else if(xobject instanceof PDFormXObject){
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else{
            super.processOperator(operator, operands);
        }
    }
 
}
