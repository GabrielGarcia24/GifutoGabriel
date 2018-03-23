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
@WebServlet(name = "mi_cuenta_info", urlPatterns = {"/mi_cuenta_info"})
public class mi_cuenta_info extends HttpServlet {

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
            out.println("<title>Servlet mi_cuenta_info</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mi_cuenta_info at " + request.getContextPath() + "</h1>");
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
        String[] modi = new String[6];
        try {
            int parte = Integer.parseInt(request.getParameter("parte"));
            String kdr = request.getParameter("kdr");

            switch (parte) {
                case 1:

                    cdr = sentenciaSQL.executeQuery("select p.nombre, p.apat, p.amat, p.direccion, p.correo, p.telefono from persona p \n"
                            + "inner join usuario u on p.id_persona=u.id_persona \n"
                            + "where u.kdr='" + kdr + "';");

                    while (cdr.next()) {
                        modi[0] = cdr.getString(1);
                        modi[1] = cdr.getString(2);
                        modi[2] = cdr.getString(3);
                        modi[3] = cdr.getString(4);
                        modi[4] = cdr.getString(5);
                        modi[5] = cdr.getString(6);

                    }

                    out.println("<h3>Modificar</h3>");
                    out.print("<table>"
                            + "<tr>"
                            + "<td>Nombre</td><td><input readonly type=\"text\"value=\"" + modi[0] + "\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>Apellido Paterno</td><td><input readonly type=\"text\"value=\"" + modi[1] + "\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>Apellido Materno<td><input readonly type=\"text\"value=\"" + modi[2] + "\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>Direccion</td><td><input id='domicilio' style=\"width: 300px\" type=\"text\"value=\"" + modi[3] + "\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>Correo</td><td><input style=\"width: 300px\" readonly type=\"text\"value=\"" + modi[4] + "\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>Telefono<td><input id='telefono' type=\"text\"value=\"" + modi[5] + "\"></td>"
                            + "</tr>"
                            + "</table><br>"
                            + " <input class=\"bottom btn-warning\" style=\"width: 100px\" type=\"submit\" value=\"Modificar\" onclick=\"enviarModificacion();\">\n"
                            + " <input class=\"bottom btn-danger\" style=\"width: 100px\" onclick=\"cancelar();\" value=\"Cancelar\">");

                    break;
                case 2:

                    String domicilio = request.getParameter("domicilio");
                    String telefono = request.getParameter("telefono");
                    String cuenta = null;

                    cdr = sentenciaSQL.executeQuery("select cuenta from usuario where kdr='" + kdr + "';");

                    while (cdr.next()) {
                        cuenta = cdr.getString(1);

                    }
                    sentenciaSQL.executeUpdate("update persona set direccion='" + domicilio + "', telefono='" + telefono + "' where correo='" + cuenta + "'");

                    break;

            }
        } catch (Exception e) {
        }
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
