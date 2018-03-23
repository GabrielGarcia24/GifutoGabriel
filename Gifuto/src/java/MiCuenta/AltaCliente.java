/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiCuenta;

import DAO.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "AltaCliente", urlPatterns = {"/AltaCliente"})
public class AltaCliente extends HttpServlet {

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
            out.println("<title>Servlet AltaCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AltaCliente at " + request.getContextPath() + "</h1>");
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
        ///sdfdf
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

            String nomRegistro = request.getParameter("nomRegistro");
            String aPARegistro = request.getParameter("aPARegistro");
            String aMARegistro = request.getParameter("aMARegistro");
            String calleRegistro = request.getParameter("calleRegistro");
            String coloniaRegistro = request.getParameter("coloniaRegistro");
            String municipioRegistro = request.getParameter("municipioRegistro");
            String cpRegistro = request.getParameter("cpRegistro");
            String emailRegistro = request.getParameter("emailRegistro");
            String telRegistro = request.getParameter("telRegistro");
            String tarjetaRegistro = request.getParameter("tarjetaRegistro");
            String ok = null;
            String idUsuario = "";
            String domicilio = "Calle " + calleRegistro + " Colonia " + coloniaRegistro + " Municipio " + municipioRegistro + " CP. " + cpRegistro;

            cdr = sentenciaSQL.executeQuery("select correo from persona where correo='" + emailRegistro + "'");

            while (cdr.next()) {
                ok = cdr.getString(1);

            }

            if (ok == null) {

                sentenciaSQL.executeUpdate("insert into persona values(null,'" + nomRegistro + "', '" + aPARegistro + "', '" + aMARegistro + "',"
                        + " '" + domicilio + "', '" + emailRegistro + "', '" + telRegistro + "')");

                String sql2 = "select id_Usuario from usuario where cuenta='" + emailRegistro + "';";
                cdr = sentenciaSQL.executeQuery(sql2);

                while (cdr.next()) {
                    idUsuario = cdr.getString(1);
                }

                String sql3 = "insert into tarjeta values('" + tarjetaRegistro + "', " + idUsuario + ", '1234', 10000, '" + compararFechasConDate() + "')";
                sentenciaSQL.executeUpdate(sql3);

            } else {
                out.print("Ya existe un usuario utilizando ese correo");
            }

        } catch (Exception e) {
        }
    }

    private static String compararFechasConDate() throws ParseException {
        String ahora = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaMasMes = formateador.parse(ahora);
        fechaMasMes.setDate(fechaMasMes.getDate() + 1465);
        return new SimpleDateFormat("yyyy-MM-dd").format(fechaMasMes);
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
