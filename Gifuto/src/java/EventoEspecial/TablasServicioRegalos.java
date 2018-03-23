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

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "TablasServicioRegalos", urlPatterns = {"/TablasServicioRegalos"})
public class TablasServicioRegalos extends HttpServlet {

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
            out.println("<title>Servlet TablasServicioRegalos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TablasServicioRegalos at " + request.getContextPath() + "</h1>");
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
                    String sql1 = "Select id_tipo_servicio, nombre, descripcion, costo from tipo_servicio where status = 'Disponible';";
                    cdr = sentenciaSQL.executeQuery(sql1);
                    out.println("<table width=100% border=1><tr><td>ID</td><td>Nombre</td>"
                            + "<td>Descripcion</td><td>Costo</td><td>Opc</td></tr>");

                    while (cdr.next()) {

                        out.println("<tr>");

                        out.println("<td width=10%>"
                                + cdr.getString(1) + "</td>");
                        out.println("<td width=30%>"
                                + cdr.getString(2) + "</td>");
                        out.println("<td width=40%>"
                                + cdr.getString(3) + "</td>");
                        out.println("<td width=10%>"
                                + cdr.getString(4) + "</td>");
                        out.println("<td width=30%><button class=\"bottom btn-success\" onclick='seleccionServicio(" + cdr.getString(1) + ",\"" + cdr.getString(2) + "\",\"" + cdr.getString(3) + "\",\"" + cdr.getString(4) + "\");'>selecion</button></td>");
                        out.println("</tr>");

                    }
                    out.println("</table>");

                    break;

                case 2:
                    out.print("Selecciona una categoria para tu regalo: <select onchange=\"tablaRegalos();\" id=\"categoria\">");
                    String sql2 = "select nombre from categorias;";
                    cdr = sentenciaSQL.executeQuery(sql2);
                    while (cdr.next()) {
                        out.print("<option value=\"" + cdr.getString(1) + "\">" + cdr.getString(1) + "</option>");
                    }
                    out.print("</select>");
                    break;
                case 3:

                    String categoria = request.getParameter("categoria");
                    out.println("<table width=100% border=1><tr><td>ID</td><td>Nombre</td>"
                            + "<td>Descripcion</td><td>Costo</td><td>Opc</td></tr>");

                    String sql3 = "select r.id_regalo, r.nombre, r.descripcion, r.costo, r.existencia from regalos r\n"
                            + "inner join detalle_categoria_regalos dct on r.id_regalo=dct.id_regalo\n"
                            + "inner join categorias c on c.id_categoria=dct.id_categoria\n"
                            + "where c.nombre='" + categoria + "' and r.existencia > 0;";
                    cdr = sentenciaSQL.executeQuery(sql3);
                    while (cdr.next()) {

                        out.println("<tr>");

                        out.println("<td width=10%>"
                                + cdr.getString(1) + "</td>");
                        out.println("<td width=30%>"
                                + cdr.getString(2) + "</td>");
                        out.println("<td width=20%>"
                                + cdr.getString(3) + "</td>");
                        out.println("<td width=30%>"
                                + cdr.getString(4) + "</td>");

                        out.println("<td width=30%><button class=\"bottom btn-success\" onclick='seleccionRegalo(" + cdr.getString(1) + ",\"" + cdr.getString(2) + "\",\"" + cdr.getString(3) + "\",\"" + cdr.getString(4) + "\",\"" + cdr.getString(5) + "\");'>selecion</button></td>");

                        out.println("</tr>");
                    }
                    out.println("</table>");
                    break;
                    
                case 4:
                    String fecha = request.getParameter("fecha");
                    if (validacionesSRE.compararFechasConDate(fecha)== true) {
                        out.print("Normal");
                    }else{
                        out.print("Express");
                    }
                    break;

            }

        } catch (SQLException ex) {
            Logger.getLogger(TablasServicioRegalos.class.getName()).log(Level.SEVERE, null, ex);
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
