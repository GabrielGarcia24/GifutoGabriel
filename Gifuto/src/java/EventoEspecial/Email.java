/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventoEspecial;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Email {

    static String usuarioCorreo;
    static String password;
    static String rutaArchivo;
    static String nombreArchivo;
    static String destinatario;
    static String asunto;
    static String mensaje;

    public Email() {
        super();
    }

    public Email(
            String usuarioCorreo, 
            String password, 
            String rutaArchivo, 
            String nombreArchivo, 
            String destinatario,
            String asunto, 
            String mensaje) {
        
        super();
        this.usuarioCorreo = usuarioCorreo;
        this.password = password;
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

//    public Email(String usuarioCorreo, String password, String destinatario, String mensaje) {
//        super();
//        this.usuarioCorreo = usuarioCorreo;
//        this.password = password;
//        this.destinatario = destinatario;
//        this.mensaje = mensaje;
//    }
//    public Email(String usuarioCorreo, String password, String destinatario, String asunto, String mensaje) {
//        super();
//        this.usuarioCorreo = usuarioCorreo;
//        this.password = password;
//        this.destinatario = destinatario;
//        this.asunto = asunto;
//        this.mensaje = mensaje;
//    }
    public static boolean sendMail() {

        try {
            Properties pro = new Properties();

            pro.put("mail.smtp.host", "smtp.gmail.com");
            pro.setProperty("mail.smtp.starttls.enable", "true");
            pro.setProperty("mail.smtp.port", "587");
            pro.setProperty("mail.smtp.user", usuarioCorreo);
            pro.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(pro, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);

            BodyPart adjunto = new MimeBodyPart();

//          Se utiliza para enviar mensajes con adjuntos.  
            if (!rutaArchivo.equals("")) {
                adjunto.setDataHandler(
                        new DataHandler(new FileDataSource(rutaArchivo)));
                adjunto.setFileName(nombreArchivo);
            }
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);

//          Se utiliza para enviar mensajes con adjuntos. 
            if (!rutaArchivo.equals("")) {
                m.addBodyPart(adjunto);
            }
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuarioCorreo));
            mensaje.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setContent(m);

            Transport t = session.getTransport("smtp");
            t.connect(usuarioCorreo, password);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;

        } catch (Exception e) {
            System.out.println("Error----" + e);
            return false;
        }
    }

//    public static void main(String[] args) {
//        String clave = "xpiipkjhqrjstrfo";
//        Email e = new Email("devugcomp@gmail.com", clave, "2517160004gabrielgg@gmail.com", "prueba tutorial");
//        if (e.sendMail()) {
//            JOptionPane.showMessageDialog(null, "Se envio bien");
//        } else {
//            JOptionPane.showMessageDialog(null, "Valioverga");
//        }
//    }
}
