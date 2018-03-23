/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class GenerarNotificacion {
    public static void notificacion(String info[]) {
    try {
            FileOutputStream archivo = null;
            
            archivo = new FileOutputStream("C:\\CertificadosGifuto\\Notificacion\\Notificacion_No" + info[6] + "_Cliente"+info[5]+".pdf");
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, archivo);
            doc.addTitle("Certificado");
            doc.open();
            
            Image imagen = Image.getInstance("C:\\CertificadosGifuto\\QR\\" + info[6] + "_" + info[5] + ".png");
            imagen.setAbsolutePosition(500f, 315f);
            doc.add(imagen);
            
            Paragraph par = new Paragraph();
            Font estilo1 = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);
            par.add(new Phrase("\n\nCertificado\n\n\nGifutoGift\n\n\nNo.Certificado" + info[6], estilo1));
            par.setAlignment(Element.ALIGN_LEFT);
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Phrase(Chunk.NEWLINE));
            doc.add(par);
            
            Paragraph par2 = new Paragraph();
            Font estilo2 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
            par2.add(new Phrase("\nDE: " + info[5], estilo2));
            par2.setAlignment(Element.ALIGN_JUSTIFIED);
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));
            doc.add(par2);
            
            Paragraph par3 = new Paragraph();
            par3.add(new Phrase("Para: " + info[10], estilo2));
            par3.setAlignment(Element.ALIGN_JUSTIFIED);
            par3.add(new Phrase(Chunk.NEWLINE));
            par3.add(new Phrase(Chunk.NEWLINE));
            doc.add(par3);
            
            Paragraph par4 = new Paragraph();
            par4.add(new Phrase("No.Mesa : " + info[2]+"\n"+info[10]+" te manda el siguiente mensaje: "+info[3], estilo2));
            par4.setAlignment(Element.ALIGN_JUSTIFIED);
            par4.add(new Phrase(Chunk.NEWLINE));
            par4.add(new Phrase(Chunk.NEWLINE));
            doc.add(par4);

            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarTicket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(GenerarTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}