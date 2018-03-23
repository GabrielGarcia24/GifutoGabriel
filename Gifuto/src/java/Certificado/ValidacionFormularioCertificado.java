/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

import DAO.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "ValidacionFormularioCertificado", urlPatterns = {"/ValidacionFormularioCertificado"})
public class ValidacionFormularioCertificado extends HttpServlet {

    ResultSet cdr = null;
    Statement sentenciaSQL = null;
    Conexion conecta = new Conexion();
    ValidacionesCerti vC = new ValidacionesCerti();

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
            out.println("<title>Servlet ValidacionFormularioCertificado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidacionFormularioCertificado at " + request.getContextPath() + "</h1>");
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

            int parte = Integer.parseInt(request.getParameter("parte"));

            switch (parte) {
                case 1:
                    float cantidad = 0;
                    String montoIngresado = request.getParameter("monto");

                    if (vC.isDigit(montoIngresado) == true) {
                        cantidad = Float.parseFloat(montoIngresado);
                    } else {
                        out.print("no");
                    }
                    if (cantidad <= 15000 && cantidad >= 1500) {

                    } else {
                        out.print("no");
                    }
                    break;

                case 2:
                    String montoIn = request.getParameter("monto");
                    float cant = Float.parseFloat(montoIn);
                    cant = cant + 250;
                    out.print("" + cant);
                    break;

                case 3:

                    float total = Float.parseFloat(request.getParameter("monto"));//total a pagar
                    String cuentaUsuario = request.getParameter("cuentaUsuario");//cuenta del cliente
                    String pass = request.getParameter("pass");//pass del cliente
                    String noMesa = request.getParameter("noMesa");//numero de mesa
                    String desc = request.getParameter("mensaje");//mensaje para el festejado
                    String tipoPago = request.getParameter("tipoPago");//forma de pago
                    String idUsuario = null;
                    float saldo = 0;
                    float nuevosaldo = 0;
////////////////////////////Si es tarjeta//////////////////////////////
                    if (tipoPago.equalsIgnoreCase("tarjeta")) {
                        cdr = sentenciaSQL.executeQuery("Select t.monto from tarjeta t\n"
                                + "inner join usuario u on u.id_usuario=t.id_usuario\n"
                                + "where u.cuenta='" + cuentaUsuario + "';");
                        while (cdr.next()) {
                            saldo = Float.parseFloat(cdr.getString(1));
                        }

                        if (saldo < total) {
                            out.print("El saldo de su Tarjeta es insuficiente Su saldo es igual a $" + saldo + " y la cantidad que desea cubrir es de $" + total);
                        } else {
                            cdr = sentenciaSQL.executeQuery("select id_Usuario from usuario where cuenta='" + cuentaUsuario + "' and pass='" + pass + "';");

                            while (cdr.next()) {
                                idUsuario = cdr.getString(1);
                            }

                            if (idUsuario == null || idUsuario.equalsIgnoreCase("")) {
                                out.print("Usuario y pass no coinciden");
                            } else {
                                sentenciaSQL.executeUpdate("insert into certificado values(null," + idUsuario + ", " + noMesa + ",now()," + total + ",'" + desc + "');");
                                nuevosaldo = saldo - total;
                                sentenciaSQL.executeUpdate("update tarjeta set monto=" + nuevosaldo + " where id_usuario=" + idUsuario);
                                String infoQR[] = new String[11];
                                String destinario = correoDestino(noMesa);
                                String nombreCliente = nombreCliente(cuentaUsuario);
                                String nombreFestejado = nombreCliente(destinario);

                                infoQR[0] = "" + total;
                                infoQR[1] = cuentaUsuario;
                                infoQR[2] = noMesa;
                                infoQR[3] = desc;
                                infoQR[4] = destinario;
                                infoQR[5] = nombreCliente;
                                infoQR[6] = noCertificado(idUsuario, noMesa, "" + total);
                                infoQR[7] = tipoPago;
                                infoQR[8] = "" + nuevosaldo;
                                infoQR[9] = ""+saldo;
                                infoQR[10] = nombreFestejado;

                                codigoQR.generarQR(infoQR);
                                GenerarTicket.lineaPago(infoQR);
                                GenerarNotificacion.notificacion(infoQR);
                                
                                String clave = "unoowzjgchmflnmm";
                                Email e = new Email("ganasitcorp@gmail.com", clave,
                                        "C:\\CertificadosGifuto\\Notificacion\\Notificacion_No" + infoQR[6] + "_Cliente"+infoQR[5]+".pdf",
                                        "Notificacion_No" + infoQR[6] + "_Cliente"+infoQR[5]+".pdf", infoQR[4], "Certificado", "Felicidades te ah Bonificado un Certificado\nMensjae del cliente: " + infoQR[3]);
                                e.sendMail();
                                codigoQR.eliminaQR(infoQR);

                                out.print("ok");

                            }
                            out.print("ok");

                        }
                        
//////////////////////////////////////////////Si es Monedero!/////////////////////////
                    } else {
                        cdr = sentenciaSQL.executeQuery("Select m.monto from monedero m\n"
                                + "inner join usuario u on u.id_usuario=m.id_usuario\n"
                                + "where u.cuenta=''" + cuentaUsuario + "';");
                        while (cdr.next()) {
                            saldo = Float.parseFloat(cdr.getString(1));
                        }

                        if (saldo < total) {
                            out.print("El saldo de su Monedero es insuficiente Su saldo es igual a $" + saldo + " y la cantidad que desea cubrir es de $" + total);
                        } else {

                            cdr = sentenciaSQL.executeQuery("select id_Usuario from usuario where cuenta='" + cuentaUsuario + "' and pass='" + pass + "';");

                            while (cdr.next()) {
                                idUsuario = cdr.getString(1);
                            }

                            if (idUsuario == null || idUsuario.equalsIgnoreCase("")) {
                                out.print("Usuario y pass no coiciden");
                            } else {
                                sentenciaSQL.executeUpdate("insert into certificado values(null," + idUsuario + ", " + noMesa + ",now()," + total + ",'" + desc + "');");
                                nuevosaldo = saldo - total;
                                sentenciaSQL.executeUpdate("update monedero set monto=" + nuevosaldo + " where id_usuario=" + idUsuario);
                                String infoQR[] = new String[11];
                                String destinario = correoDestino(noMesa);
                                String nombreCliente = nombreCliente(cuentaUsuario);
                                String nombreFestejado = nombreCliente(destinario);

                                infoQR[0] = "" + total;
                                infoQR[1] = cuentaUsuario;
                                infoQR[2] = noMesa;
                                infoQR[3] = desc;
                                infoQR[4] = destinario;
                                infoQR[5] = nombreCliente;
                                infoQR[6] = noCertificado(idUsuario, noMesa, "" + total);
                                infoQR[7] = tipoPago;
                                infoQR[8] = "" + nuevosaldo;
                                infoQR[9] = ""+saldo;
                                infoQR[10] = nombreFestejado;

                                codigoQR.generarQR(infoQR);
                                GenerarTicket.lineaPago(infoQR);
                                GenerarNotificacion.notificacion(infoQR);
                                
                                String clave = "unoowzjgchmflnmm";
                                Email e = new Email(
                                        "ganasitcorp@gmail.com", 
                                        clave,
                                        "C:\\CertificadosGifuto\\Notificacion\\Notificacion_No" + infoQR[6] + "_Cliente"+infoQR[5]+".pdf",
                                        "Certificado_No" + infoQR[6] + "_Cliente" + infoQR[5] + ".pdf", infoQR[4], 
                                        "Certificado", 
                                        "Felicidades te ah Bonificado un Certificado\nMensjae de "+infoQR[5]+": " + infoQR[3]
                                );
                                e.sendMail();
                                codigoQR.eliminaQR(infoQR);
                                out.print("ok");

                            }
                            out.print("ok");
                        }
                        
                    }

                    break;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ValidacionFormularioCertificado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public void destroy() {
        conecta.cerrar();
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

    private String correoDestino(String noMesa) {
        String l = "select u.cuenta from usuario u \n"
                + "inner join mesa m on m.id_usuario=u.id_usuario\n"
                + "where id_mesa=" + noMesa + ";  ";
        String resp = null;
        try {

            cdr = sentenciaSQL.executeQuery(l);
            while (cdr.next()) {
                resp = cdr.getString(1);
            }
            return resp;
        } catch (SQLException ex) {
            Logger.getLogger(ValidacionFormularioCertificado.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            conecta.cerrar();
        }
        return resp;
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

    private String noCertificado(String idUsuario, String noMesa, String total) {
        String l = "SELECT max(id_certificado) from certificado where id_usuario=" + idUsuario + " and id_Mesa=" + noMesa + " and monto=" + total + ";";
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

}
