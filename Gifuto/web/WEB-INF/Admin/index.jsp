<%-- 
    Document   : index
    Created on : 12-Febrero-2018, 10:24:59
    Author     : Noe Aurelio Vargas Escorcia
--%>

<%@page import="java.sql.Array"%>
<%@page import="ClasesDAO.CategoriaDAO"%>
<%@page import="JavaBeans.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Inicio | GifutoGifts</title>
        <%@include file="../../WEB-INF/css.jsp"%>
    </head><!--/head-->

    <body>
        <script src="js/PerfilAdmin.js"></script>
        <%@include file="../../WEB-INF/header.jsp"%>



        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">

                    </div>

                    <div class="col-sm-10 clearfix">

                         ${resp}
                        <h3>Gestión de producto</h3>
                        <form action="ControlProducto" enctype="multipart/form-data" method="post">
                            <div class="form-one">
                              Nombre:<br/><input type="text" name="nombre" value="" placeholder="Nombre producto" required/><br/>
                              Cantidad:<br/><input type="number" name="cantidad" min="0" value="1" placeholder="Cantidad producto"/><br/>
                              Precio:<br/><input type="number" name="precio" min="0" value="0" placeholder="Precio producto"/><br/>
                              Descripción:<br/><textarea maxlength="250" name="descripcion" placeholder="Descripción producto" required></textarea><br/>
                              Categoría superior:<br/>
                                                           
                              <select name="categoriaSuperior">
                                  <option value="x">Categoria superior</option>
                                <% try{for (Categoria cat: CategoriaDAO.listar()) { %>
                                <option value="<%=cat.getId() %>"><%=cat.getNombre()%></option>
                                   <% }}catch(NullPointerException n){ %>
                                    <option value="KKK">NNN</option>

 <% } %>
                                </select><br/>
                              Visible?<input type="checkbox" name="visible" value="on" checked="checked" />
                              Recomendado?<input type="checkbox" name="recomendado"  value="on" /><br/>
                            <input type="file" name="foto" required/><hr/>
                            <input class="btn btn-success" name="accion" type="submit" value="Registrar" />
                            <input class="btn btn-default" name="accion" type="submit" value="Consular" />
                            <input class="btn btn-warning" name="accion" type="submit" value="Actualizar" />
                            <input class="btn btn-danger" name="accion" type="submit" value="Borrar" />
                            <br/><br/>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </section>

        <%@include file="../../WEB-INF/footer.jsp"%>
        <%@include file="../../WEB-INF/jquery.jsp"%>



    </body>
</html>
