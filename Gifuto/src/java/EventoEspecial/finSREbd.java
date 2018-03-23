/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventoEspecial;

import DAO.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "finSREbd", urlPatterns = {"/finSREbd"})
public class finSREbd extends HttpServlet {

    ResultSet cdr = null;
    Statement sentenciaSQL = null;
    Conexion conecta = new Conexion();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        conecta.Conectar();
        sentenciaSQL = conecta.getSentenciaSQL();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet finSREbd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet finSREbd at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Date dNo = new Date();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat ftt
                    = new SimpleDateFormat("yyyy-MM-dd");

            int parte = Integer.parseInt(request.getParameter("parte"));
            String precioEn = request.getParameter("precioEn");
            float totalApagar = Float.parseFloat(request.getParameter("totalApagar"));
            String tipoP = request.getParameter("tipoP");
            String domicilio = request.getParameter("domicilio");
            String horaEnvio = request.getParameter("horaEnvio");
            String fechaEnvio = request.getParameter("fechaEnvio");
            String comentarioEnvio = request.getParameter("comentarioEnvio");
            String cuentaUsuario = null;
            String codificacion = request.getParameter("cuentaUsuario");
            String passUser = request.getParameter("passUser");
            String tipoEnvioN = request.getParameter("tipoEnvioN");
            String idUsuario = null;
            float saldo = 0;
            String idEnvio = null;
            String fechaActual = ftt.format(dNo);

            cdr = sentenciaSQL.executeQuery(
                    "select cuenta from usuario where kdr ='" + codificacion + "';");

            while (cdr.next()) {
                cuentaUsuario = cdr.getString(1);
            }

            cdr = sentenciaSQL.executeQuery(
                    "select id_Usuario from usuario where cuenta='" + cuentaUsuario + "' and pass='" + passUser + "';");

            while (cdr.next()) {
                idUsuario = cdr.getString(1);
            }

            if (idUsuario == null || idUsuario.equalsIgnoreCase("")) {
                out.print("Usuario y pass no coinciden");

            } else if (tipoP.equalsIgnoreCase("tarjeta")) {
                cdr = sentenciaSQL.executeQuery("Select t.monto from tarjeta t\n"
                        + "inner join usuario u on u.id_usuario=t.id_usuario\n"
                        + "where u.cuenta='" + cuentaUsuario + "';");
                while (cdr.next()) {
                    saldo = Float.parseFloat(cdr.getString(1));
                }

                if (saldo < totalApagar) {
                    out.print("El saldo de su Tarjeta es insuficiente Su saldo es igual a $" + saldo + " y la cantidad que desea cubrir es de $" + totalApagar);
                } else {
                    switch (parte) {
                        case 2:
                            String id_servicio = request.getParameter("id_servicio");
                            String sqlP2Tarjeta = "insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "', '" + tipoEnvioN + "', " + precioEn + ")";
                            sentenciaSQL.executeUpdate(sqlP2Tarjeta);

                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");
                            while (cdr.next()) {
                                idEnvio = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null,1," + idEnvio + "," + id_servicio + ", now(),'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo = saldo - totalApagar;
                            String sql4 = "update tarjeta set monto='" + saldoNuevo + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql4);

                            sentenciaSQL.executeUpdate("update tipo_servicio set status='No Disponible' where id_tipo_servicio='" + id_servicio + "'");

                            String info[] = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");
                            while (cdr.next()) {
                                info[0] = cdr.getString(1);
                            }
                            info[1] = nombreCliente(cuentaUsuario);
                            info[2] = cuentaUsuario;
                            info[3] = domicilio;
                            info[4] = tipoEnvioN;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info[0] + ";");

                            while (cdr.next()) {
                                info[7] = cdr.getString(1);
                                info[8] = cdr.getString(2);
                                info[5] = cdr.getString(3);
                                info[6] = cdr.getString(4);

                            }
                            info[9] = "" + precioEn;
                            info[10] = "" + totalApagar;
                            info[11] = "" + saldo;
                            info[12] = "" + saldoNuevo;

                            QRSRE.generarQR(info);
                           ticketSRE(info);

                            String clave = "jsjxykfcwpctmmzc";
                            Email e = new Email("gifutogifts@gmail.com", clave,
                                    "C:\\SRE\\no_Servicio" + info[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e.sendMail();

                            QRSRE.eliminaQR(info);

                            break;

                        case 3:
                            String id_regalo3 = request.getParameter("id_regalo");
                            String existenciaRegalo3 = request.getParameter("existenciaRegalo");

                            sentenciaSQL.executeUpdate("insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "','" + tipoEnvioN + "', " + precioEn + ")");
                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");

                            while (cdr.next()) {
                                idEnvio = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null," + id_regalo3 + "," + idEnvio + ", 1, now(),'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo4 = saldo - totalApagar;
                            String sql124 = "update tarjeta set monto='" + saldoNuevo4 + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql124);
                            int exiPos = Integer.parseInt(existenciaRegalo3) - 1;
                            String sql3 = "update regalos set existencia='" + exiPos + "' where id_Regalo='" + id_regalo3 + "'";
                            sentenciaSQL.executeUpdate(sql3);

                            String[] info2 = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");
                            while (cdr.next()) {
                                info2[0] = cdr.getString(1);
                            }
                            info2[1] = nombreCliente(cuentaUsuario);
                            info2[2] = cuentaUsuario;
                            info2[3] = domicilio;
                            info2[4] = tipoEnvioN;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info2[0] + ";");

                            while (cdr.next()) {
                                info2[7] = cdr.getString(1);
                                info2[8] = cdr.getString(2);
                                info2[5] = cdr.getString(3);
                                info2[6] = cdr.getString(4);

                            }
                            info2[9] = "" + precioEn;
                            info2[10] = "" + totalApagar;
                            info2[11] = "" + saldo;
                            info2[12] = "" + saldoNuevo4;

                            QRSRE.generarQR(info2);
                            ticketSRE(info2);

                            String clave2 = "jsjxykfcwpctmmzc";
                            Email e2 = new Email("gifutogifts@gmail.com", clave2,
                                    "C:\\SRE\\no_Servicio" + info2[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e2.sendMail();

                            QRSRE.eliminaQR(info2);

                            break;

                        case 4:
                            String id_servicio4 = request.getParameter("id_servicio");
                            String id_regalo4 = request.getParameter("id_regalo");
                            String existenciaRegalo4 = request.getParameter("existenciaRegalo");

                            String tipoEnvioN4 = null;
                            if (precioEn.equals("250")) {
                                tipoEnvioN4 = "Normal";
                            } else {
                                tipoEnvioN4 = "Express";
                            }
                            sentenciaSQL.executeUpdate("insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "','" + tipoEnvioN4 + "', " + precioEn + ")");

                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");

                            while (cdr.next()) {
                                idEnvio = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null," + id_regalo4 + "," + idEnvio + "," + id_servicio4 + ", now(),'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo789 = saldo - totalApagar;
                            String sql4789 = "update tarjeta set monto='" + saldoNuevo789 + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql4789);
                            sentenciaSQL.executeUpdate("update tipo_servicio set status='No Disponible' where id_tipo_servicio='" + id_servicio4 + "'");
                            int exiPos789 = Integer.parseInt(existenciaRegalo4) - 1;
                            String sql3123 = "update regalos set existencia='" + exiPos789 + "' where id_Regalo='" + id_regalo4 + "'";
                            sentenciaSQL.executeUpdate(sql3123);

                            String[] info3 = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");
                            while (cdr.next()) {
                                info3[0] = cdr.getString(1);
                            }
                            info3[1] = nombreCliente(cuentaUsuario);
                            info3[2] = cuentaUsuario;
                            info3[3] = domicilio;
                            info3[4] = tipoEnvioN4;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info3[0] + ";");

                            while (cdr.next()) {
                                info3[7] = cdr.getString(1);
                                info3[8] = cdr.getString(2);
                                info3[5] = cdr.getString(3);
                                info3[6] = cdr.getString(4);

                            }
                            info3[9] = "" + precioEn;
                            info3[10] = "" + totalApagar;
                            info3[11] = "" + saldo;
                            info3[12] = "" + saldoNuevo789;

                            QRSRE.generarQR(info3);
                         ticketSRE(info3);

                            String clave3 = "jsjxykfcwpctmmzc";
                            Email e3 = new Email("gifutogifts@gmail.com", clave3,
                                    "C:\\SRE\\no_Servicio" + info3[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e3.sendMail();

                            QRSRE.eliminaQR(info3);
                            break;
                    }
                }

            } else {
                cdr = sentenciaSQL.executeQuery("Select m.monto from monedero m\n"
                        + "inner join usuario u on u.id_usuario=m.id_usuario\n"
                        + "where u.cuenta='" + cuentaUsuario + "';");
                while (cdr.next()) {
                    saldo = Float.parseFloat(cdr.getString(1));
                }
                if (saldo < totalApagar) {
                    out.print("El saldo de su monedero es insuficiente Su saldo es igual a $" + saldo + " y la cantidad que desea cubrir es de $" + totalApagar);
                } else {
                    switch (parte) {
                        case 2:
                            String id_servicio = request.getParameter("id_servicio");
                            String sqlP2Monede = "insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "', '" + tipoEnvioN + "', " + precioEn + ")";
                            sentenciaSQL.executeUpdate(sqlP2Monede);

                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");
                            while (cdr.next()) {
                                idEnvio = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null,1," + idEnvio + "," + id_servicio + ", now(),'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo = saldo - totalApagar;
                            String sql4 = "update monedero set monto='" + saldoNuevo + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql4);
                            sentenciaSQL.executeUpdate("update tipo_servicio set status='No Disponible' where id_tipo_servicio='" + id_servicio + "'");

                            String info[] = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");
                            while (cdr.next()) {
                                info[0] = cdr.getString(1);
                            }
                            info[1] = nombreCliente(cuentaUsuario);
                            info[2] = cuentaUsuario;
                            info[3] = domicilio;
                            info[4] = tipoEnvioN;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info[0] + ";");

                            while (cdr.next()) {
                                info[7] = cdr.getString(1);
                                info[8] = cdr.getString(2);
                                info[5] = cdr.getString(3);
                                info[6] = cdr.getString(4);

                            }
                            info[9] = "" + precioEn;
                            info[10] = "" + totalApagar;
                            info[11] = "" + saldo;
                            info[12] = "" + saldoNuevo;

                            QRSRE.generarQR(info);
                            ticketSRE(info);

                            String clave = "jsjxykfcwpctmmzc";
                            Email e = new Email("gifutogifts@gmail.com", clave,
                                    "C:\\SRE\\no_Servicio" + info[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e.sendMail();

                            QRSRE.eliminaQR(info);

                            break;
                        case 3:
                            String id_regalo3 = request.getParameter("id_regalo");
                            String existenciaRegalo = request.getParameter("existenciaRegalo");

                            sentenciaSQL.executeUpdate("insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "','" + tipoEnvioN + "', " + precioEn + ")");
                            String idEnvio3 = null;

                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");

                            while (cdr.next()) {
                                idEnvio3 = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null," + id_regalo3 + "," + idEnvio3 + ",1,now(),'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo3 = saldo - totalApagar;
                            String sql8 = "update monedero set monto='" + saldoNuevo3 + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql8);
                            int exiPos = Integer.parseInt(existenciaRegalo) - 1;
                            String sql9 = "update regalos set existencia='" + exiPos + "' where id_Regalo='" + id_regalo3 + "'";
                            sentenciaSQL.executeUpdate(sql9);

                            String[] info2 = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");

                            while (cdr.next()) {
                                info2[0] = cdr.getString(1);
                            }
                            info2[1] = nombreCliente(cuentaUsuario);
                            info2[2] = cuentaUsuario;
                            info2[3] = domicilio;
                            info2[4] = tipoEnvioN;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info2[0] + ";");

                            while (cdr.next()) {
                                info2[7] = cdr.getString(1);
                                info2[8] = cdr.getString(2);
                                info2[5] = cdr.getString(3);
                                info2[6] = cdr.getString(4);

                            }
                            info2[9] = "" + precioEn;
                            info2[10] = "" + totalApagar;
                            info2[11] = "" + saldo;
                            info2[12] = "" + saldoNuevo3;

                            QRSRE.generarQR(info2);
                            ticketSRE(info2);

                            String clave2 = "jsjxykfcwpctmmzc";
                            Email e2 = new Email("gifutogifts@gmail.com", clave2,
                                    "C:\\SRE\\no_Servicio" + info2[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e2.sendMail();

                            QRSRE.eliminaQR(info2);

                            break;

                        case 4:
                            String id_servicio4 = request.getParameter("id_servicio");
                            String id_regalo4 = request.getParameter("id_regalo");
                            String existenciaRegalo4 = request.getParameter("existenciaRegalo");
                            String tipoEnvioN4 = null;
                            if (precioEn.equals("250")) {
                                tipoEnvioN4 = "Normal";
                            } else {
                                tipoEnvioN4 = "Express";
                            }

                            sentenciaSQL.executeUpdate("insert into envio values(null,'" + domicilio + "', '" + fechaEnvio + "','" + tipoEnvioN4 + "', " + precioEn + ")");

                            cdr = sentenciaSQL.executeQuery("select max(id_envio) from envio where lugar='"
                                    + domicilio + "' and fecha='" + fechaEnvio + "'");

                            while (cdr.next()) {
                                idEnvio = cdr.getString(1);
                            }

                            sentenciaSQL.executeUpdate("insert into servicio values(null," + id_regalo4 + "," + idEnvio + "," + id_servicio4 + ",now() ,"
                                    + "'" + horaEnvio + "'," + totalApagar + ",'Pagado','" + comentarioEnvio + "')");
                            float saldoNuevo4 = saldo - totalApagar;
                            String sql441 = "update monedero set monto='" + saldoNuevo4 + "' where id_usuario='" + idUsuario + "'";
                            sentenciaSQL.executeUpdate(sql441);

                            sentenciaSQL.executeUpdate("update tipo_servicio set status='No Disponible' where id_tipo_servicio='" + id_servicio4 + "'");
                            int exiPos443 = Integer.parseInt(existenciaRegalo4) - 1;
                            String sql3 = "update regalos set existencia='" + exiPos443 + "' where id_Regalo='" + id_regalo4 + "'";
                            sentenciaSQL.executeUpdate(sql3);

                            String[] info3 = new String[13];

                            cdr = sentenciaSQL.executeQuery("select max(id_servicio) from servicio where fecha='" + fechaActual + "' and total=" + totalApagar + ";");
                            while (cdr.next()) {
                                info3[0] = cdr.getString(1);
                            }
                            info3[1] = nombreCliente(cuentaUsuario);
                            info3[2] = cuentaUsuario;
                            info3[3] = domicilio;
                            info3[4] = tipoEnvioN4;
                            cdr = sentenciaSQL.executeQuery("Select concat_ws(' ',r.nombre, r.descripcion), r.costo, concat_ws(' ',ts.nombre, ts.descripcion), ts.costo from regalos r\n"
                                    + "inner join servicio s on r.id_regalo = s.id_regalo\n"
                                    + "inner join tipo_servicio ts on s.id_tipo_servicio = ts.id_tipo_servicio\n"
                                    + "where s.id_servicio=" + info3[0] + ";");

                            while (cdr.next()) {
                                info3[7] = cdr.getString(1);
                                info3[8] = cdr.getString(2);
                                info3[5] = cdr.getString(3);
                                info3[6] = cdr.getString(4);

                            }
                            info3[9] = "" + precioEn;
                            info3[10] = "" + totalApagar;
                            info3[11] = "" + saldo;
                            info3[12] = "" + saldoNuevo4;
                            QRSRE.generarQR(info3);
                       ticketSRE(info3);

                            String clave3 = "jsjxykfcwpctmmzc";
                            Email e3 = new Email("gifutogifts@gmail.com", clave3,
                                    "C:\\SRE\\no_Servicio" + info3[0] + ".pdf",
                                    "Ticket de compra.pdf", cuentaUsuario, "Ticket", "Gracias por tu compra");
                            e3.sendMail();

                            QRSRE.eliminaQR(info3);

                            break;
                    }
                }
            }

        } catch (SQLException ex) {
            out.print(ex);
        } finally {
            out.close();

        }

    }

    public void destroy() {
        conecta.cerrar();
    }
    
    public static void ticketSRE(String info[]) {
        FileOutputStream archivo = null;
        try {

            archivo = new FileOutputStream("C:\\SRE\\no_Servicio" + info[0] + ".pdf");
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, archivo);
            doc.addTitle("Comprobante de Pago");
            doc.open();

            Image imagen = Image.getInstance("C:\\SRE\\QR\\no_Servicio" + info[0] + ".png");
            imagen.setAbsolutePosition(500f, 415f);
            doc.add(imagen);

            Paragraph par = new Paragraph();
            Font estilo1 = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.BLACK);
            par.add(new Phrase("\n\nComprobante de Pago\n\n\nGifutoGift\n", estilo1));
            par.setAlignment(Element.ALIGN_LEFT);
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Phrase(Chunk.NEWLINE));
            doc.add(par);

            Paragraph par2 = new Paragraph();
            Font estilo2 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
            par2.add(new Phrase("\nNombre del Cliente: " + info[1] + "\nCuenta: " + info[2]
                    + "\nDireccion del envio: " + info[3]
                    + "\nTipo de envio: " + info[4], estilo2));
            par2.setAlignment(Element.ALIGN_JUSTIFIED);
            par2.add(new Phrase(Chunk.NEWLINE));
            par2.add(new Phrase(Chunk.NEWLINE));
            doc.add(par2);

            PdfPTable tabla = new PdfPTable(8);
            PdfPCell celda1 = new PdfPCell(
                    new Paragraph("Servicio", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda2 = new PdfPCell(
                    new Paragraph("Precio Servicio", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda3 = new PdfPCell(
                    new Paragraph("Regalo", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda4 = new PdfPCell(
                    new Paragraph("Precio Regalo", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda5 = new PdfPCell(
                    new Paragraph("Precio Envio", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda6 = new PdfPCell(
                    new Paragraph("Total a Pagar", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda7 = new PdfPCell(
                    new Paragraph("Saldo anterior", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));
            PdfPCell celda8 = new PdfPCell(
                    new Paragraph("Saldo Nuevo", FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.BLUE)));

            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            tabla.addCell(celda6);
            tabla.addCell(celda7);
            tabla.addCell(celda8);

            tabla.addCell(new Paragraph(info[5], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[6], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[7], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[8], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[9], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[10], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[11], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));
            tabla.addCell(new Paragraph(info[12], FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLACK)));

            doc.add(tabla);

            doc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(finSREbd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(finSREbd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (archivo != null) {
                    archivo.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String nombreCliente(String cuentaUsuario) {
        String l = "Select concat_WS(' ',nombre,aPat,aMat) from persona where correo='" + cuentaUsuario + "';";
        String resp = null;
        try {

            cdr = sentenciaSQL.executeQuery(l);
            while (cdr.next()) {
                resp = cdr.getString(1);
            }
            return resp;
        } catch (SQLException ex) {
            System.out.println(ex);
//        } finally {
//            conecta.cerrar();
        }
        return resp;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
