/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ClasesDAO.ProductoDAO;
import JavaBeans.Producto;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Noe
 */
public class ControlProducto extends HttpServlet {

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
            out.println("<title>Servlet ControlProducto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlProducto at " + request.getContextPath() + "</h1>");
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
       recibirDatos(request);
//        int categoria;
//        int id ;
//        String nombre;
//        String descripcion;
//        boolean nuevo = (Boolean.parseBoolean(request.getAttribute("nuevo").toString())) ? true : false;
        boolean visible;
        boolean recomendado;
//        int existencia;
//        double costo;
//        double costoNuevo = Double.parseDouble(request.getAttribute("precioNuevo").toString());
        String accion = request.getAttribute("accion").toString();

        if (accion.equals("Registrar")) {

            try{
                 visible = request.getAttribute("visible").toString().equalsIgnoreCase("on");
            }catch(NullPointerException f){
                visible=false;
            }
            
            try{
                 recomendado = request.getAttribute("recomendado").toString().equalsIgnoreCase("on");
            }catch(NullPointerException f){
                recomendado=false;
            }
           
            
           

            Producto pro = new Producto(
                    Integer.parseInt(request.getAttribute("categoriaSuperior").toString()),
                    request.getAttribute("nombre").toString().toUpperCase().replaceAll(" +"," "),
                    request.getAttribute("descripcion").toString().toLowerCase().replaceAll(" +"," "),
                    visible,
                    recomendado,
                    Integer.parseInt(request.getAttribute("cantidad").toString()),
                    Double.parseDouble(request.getAttribute("precio").toString()),
                    request.getAttribute("foto").toString());
            boolean alta = ProductoDAO.registrarProducto(pro);
            if (alta) {
                
                request.setAttribute("resp", "<label  style='color:white; border-radius: 15px; padding:10px; background:green;'>Producto registrado</label>");
            } else {
                request.setAttribute("resp", "<label  style='color:white; border-radius: 15px; padding:10px; background:red;'>Producto no registrado</label>");
            }
            
            request.getRequestDispatcher("Admin").forward(request, response);

        } else if (accion.equals("Consular")) {

        } else if (accion.equals("Actualizar")) {

        } else if (accion.equals("Borrar")) {

        } else {
            request.setAttribute("resp", "<label  style='color:white; border-radius: 15px; padding:10px; background:oranje;'>Accion desconocida</label>");

            request.getRequestDispatcher("Admin").forward(request, response);
        }

    }

    private void recibirDatos(HttpServletRequest request) {
        try {
            FileItemFactory fileFactory = new DiskFileItemFactory();

            ServletFileUpload servletUpload = new ServletFileUpload(fileFactory);
            String nombre = "";
            List items = servletUpload.parseRequest(request);

            for (int i = 0; items.size() > i; i++) {
                FileItem item = (FileItem) items.get(i);
                if (!item.isFormField()) {
                    String ruta = request.getServletContext().getRealPath("/") + "foto\\";
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMyyyyhhmmss");
                    String fecha = sdf.format(new Date());
                    nombre = fecha + new Random().nextLong() + item.getName();
                    String nuevoNombre = ruta + nombre;
                    File folder = new File(ruta);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
                    File imagen = new File(nuevoNombre);
                    if (item.getContentType().contains("image")) {
                        item.write(imagen);
                        request.setAttribute(item.getFieldName(),nombre);
                        
                    }
                }else{
                  request.setAttribute(item.getFieldName(),item.getString());
                }

            }

        } catch (FileUploadException ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
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
