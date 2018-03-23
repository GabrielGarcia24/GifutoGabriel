/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventoEspecial;

import DAO.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "ProcesarDatosSRE", urlPatterns = {"/ProcesarDatosSRE"})
public class ProcesarDatosSRE extends HttpServlet {

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
            out.println("<title>Servlet ProcesarDatosSRE</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcesarDatosSRE at " + request.getContextPath() + "</h1>");
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

                    String calle = request.getParameter("calle");
                    String colonia = request.getParameter("colonia");
                    String codigoPostal = request.getParameter("codigoPostal");

                    if (validacionesSRE.isAlphabetic(calle) == true) {

                    } else {
                        out.print("1");
                    }

                    if (validacionesSRE.isAlphabetic(colonia) == true) {

                    } else {
                        out.print("2");
                    }

                    if (validacionesSRE.isDigit(codigoPostal) == true) {

                    } else {
                        out.print("3");
                    }

                    break;

                case 2:
                    String tipoPago2 = request.getParameter("tipoPago");
                    String tipoEnvio2 = request.getParameter("tipoEnvio");
                    float envioP2 = 0;

                    if (tipoEnvio2.equalsIgnoreCase("Express")) {
                        envioP2 = 500;
                    } else {
                        envioP2 = 250;
                    }
                    /////////////////Servicio//////
                    String nomP2 = request.getParameter("nom");
                    String desP2 = request.getParameter("des");
                    float cosP2 = Float.parseFloat(request.getParameter("cos"));
                    float totalP2 = 0;
                    totalP2 = cosP2 + envioP2;

                    out.print("Servicios Usted eligio un Servicio<br>");
                    out.print("Servicio: <input type='text' disabled='' id='servicioP2' value='" + nomP2 + " " + desP2 + "'><br>");
                    out.print("Costo Del Servicio $<input type='text' disabled='' value='" + cosP2 + "'><br>");
                    out.print("Costo de envio: $<input type='text' disabled='' id='costoEnvioP2' value='" + envioP2 + "'><br>");
                    out.print("Tipo de pago: <input type='text' disabled='' id='tipoPago2' value='" + tipoPago2 + "'><br>");
                    out.print("Total: $<input type='text' disabled='' id='totalP2' value='" + totalP2 + "'><br>");
                    out.print("<br>Ingresa tu contrasena<br><input type=\"password\" id=\"passUser\" ><button class=\"bottom btn-success\" onclick=\"ejectuarPass('2',"+totalP2+","+tipoPago2+","+envioP2+");\">OK</button>\n"
                            + " <button class=\"bottom btn-danger\" onclick=\"cancelar();\">Cancelar</button><br/>");

                    break;

                case 3:

                    String tipoEnvio3 = request.getParameter("tipoEnvio");
                    String tipoPago3 = request.getParameter("tipoPago");
                    float envioP3 = 0;

                    if (tipoEnvio3.equalsIgnoreCase("Express")) {
                        envioP3 = 500;
                    } else {
                        envioP3 = 250;
                    }

                    //////////////////Regalo/////////////////////////
                    String nomRegaloP3 = request.getParameter("nomRegalo");
                    String desRegaloP3 = request.getParameter("desRegalo");
                    float cosRegaloP3 = Float.parseFloat(request.getParameter("cosRegalo"));
                    float totalP3 = envioP3 + cosRegaloP3;

                    out.print("Servicios Usted eligio Regalo<br>");
                    out.print("Regalo: <input type='text' disabled='' id='desRegaloP3' value='" + nomRegaloP3 + " " + desRegaloP3 + "'><br>");
                    out.print("Costo del regalo $<input type='text' disabled='' value='" + cosRegaloP3 + "'><br>");
                    out.print("Costo de envio: $<input type='text' disabled='' id='costoEnvioP3' value='" + envioP3 + "'><br>");
                    out.print("Tipo de pago: <input type='text' disabled='' id='tipoPago3' value='" + tipoPago3 + "'><br>");
                    out.print("Total: $<input type='text' disabled='' id='totalP3' value='" + totalP3 + "'><br>");
                    out.print("<br>Ingresa tu contrasena<br><input type=\"password\" id=\"passUser\" ><button class=\"bottom btn-success\" onclick=\"ejectuarPass('3',"+totalP3+","+tipoPago3+","+envioP3+");\">OK</button>\n"
                            + " <button class=\"bottom btn-danger\" onclick=\"cancelar();\">Cancelar</button><br/>");

                    break;

                case 4:

                    String tipoEnvio4 = request.getParameter("tipoEnvio");
                    String tipoPago4 = request.getParameter("tipoPago");
                    float envioP4 = 0;

                    if (tipoEnvio4.equalsIgnoreCase("Express")) {
                        envioP4 = 500;
                    } else {
                        envioP4 = 250;
                    }
                    /////////////////Servicio//////
                    String nomP4 = request.getParameter("nom");
                    String desP4 = request.getParameter("des");
                    float cosP4 = Float.parseFloat(request.getParameter("cos"));
                    //////////////////Regalo/////////////////////////
                    String nomRegaloP4 = request.getParameter("nomRegalo");
                    String desRegaloP4 = request.getParameter("desRegalo");
                    float cosRegaloP4 = Float.parseFloat(request.getParameter("cosRegalo"));
                    float totalP4 = envioP4 + cosRegaloP4 + cosP4;

                    out.print("Servicios Usted eligio un servicio y un regalo<br>");
                    out.print("Servicio: <input type='text' disabled='' id='servicioP4' value='" + nomP4 + " " + desP4 + "'><br>");
                    out.print("Costo Del Servicio $<input type='text' disabled='' value='" + cosP4 + "'><br>");
                    out.print("Regalo: <input type='text' disabled='' id='desRegaloP4' value='" + nomRegaloP4 + " " + desRegaloP4 + "'><br>");
                    out.print("Costo Del Regalo $<input type='text' disabled='' value='" + cosRegaloP4 + "'><br>");
                    out.print("Costo de envio: $<input type='text' disabled='' id='costoEnvioP4' value='" + envioP4 + "'><br>");
                    out.print("Tipo de pago: <input type='text' disabled='' id='tipoPago4' value='" + tipoPago4 + "'><br>");
                    out.print("Total: $<input type='text' disabled='' id='totalP4' value='" + totalP4 + "'><br>");
                    out.print("<br>Ingresa tu contrasena<br><input type=\"password\" id=\"passUser\" ><button class=\"bottom btn-success\" onclick=\"ejectuarPass('4',"+totalP4+","+tipoPago4+","+envioP4+");\">OK</button>\n"
                            + " <button class=\"bottom btn-danger\" onclick=\"cancelar();\">Cancelar</button><br/>");

                    break;

            }

//        } catch (SQLException ex) {
            //          Logger.getLogger(TablasServicioRegalos.class.getName()).log(Level.SEVERE, null, ex);
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

}
