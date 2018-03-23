package Certificado;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.IOException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarTicket {

    public static void lineaPago(String info[]) {

        try {
            FileOutputStream archivo = null;
            
            archivo = new FileOutputStream("C:\\CertificadosGifuto\\Certificado\\Certificado_No" + info[6] + "_Cliente"+info[5]+".pdf");
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, archivo);
            doc.addTitle("Comprobante de Certificado");
            doc.open();
            
            Image imagen = Image.getInstance("C:\\CertificadosGifuto\\QR\\" + info[6] + "_" + info[5] + ".png");
            imagen.setAbsolutePosition(500f, 315f);
            doc.add(imagen);
            
            Paragraph par = new Paragraph();
            Font estilo1 = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);
            par.add(new Phrase("\n\nComprobante de Certificado\n\n\nGifutoGift\n\n\nCertificado No." + info[6], estilo1));
            par.setAlignment(Element.ALIGN_LEFT);
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Phrase(Chunk.NEWLINE));
            doc.add(par);
            
            Paragraph par2 = new Paragraph();
            Font estilo2 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
            par2.add(new Phrase("\nNombre del Cliente: " + info[5], estilo2));
            par2.setAlignment(Element.ALIGN_JUSTIFIED);
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));
            doc.add(par2);
            
            Paragraph par3 = new Paragraph();
            par3.add(new Phrase("Cuenta: " + info[1], estilo2));
            par3.setAlignment(Element.ALIGN_JUSTIFIED);
            par3.add(new Phrase(Chunk.NEWLINE));
            par3.add(new Phrase(Chunk.NEWLINE));
            doc.add(par3);
            
            Paragraph par4 = new Paragraph();
            par4.add(new Phrase("Nombre del Festejado: "+info[10]+"\nNo.Mesa : " + info[2], estilo2));
            par4.setAlignment(Element.ALIGN_JUSTIFIED);
            par4.add(new Phrase(Chunk.NEWLINE));
            par4.add(new Phrase(Chunk.NEWLINE));
            doc.add(par4);
            
            PdfPTable tabla = new PdfPTable(5);
            PdfPCell celda1 = new PdfPCell(
                    new Paragraph("Concepto de Pago", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda2 = new PdfPCell(
                    new Paragraph("Tipo de pago", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda3 = new PdfPCell(
                    new Paragraph("Total", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda4 = new PdfPCell(
                    new Paragraph("Saldo anterior", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda5 = new PdfPCell(
                    new Paragraph("Saldo Nuevo", FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE)));
            
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            
            tabla.addCell(new Paragraph("Pago de Certificado", FontFactory.getFont("Arial", 15, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[7], FontFactory.getFont("Arial", 15, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[0], FontFactory.getFont("Arial", 15, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[9], FontFactory.getFont("Arial", 15, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[8], FontFactory.getFont("Arial", 15, Font.NORMAL, BaseColor.BLACK)));
            
            doc.add(tabla);
            
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarTicket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(GenerarTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
