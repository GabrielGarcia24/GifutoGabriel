<%-- 
    Document   : index
    Created on : 12/03/2018, 03:54:13 AM
    Author     : Noe
--%>

<%@page import="ClasesDAO.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../../WEB-INF/css.jsp"%>
    </head>
    <body>
        <script src="js/PerfilCliente.js"></script>
        <script src="js/servicioRegaloEspecial7.js"></script>
        <%! String kdr;%>
        <%kdr = request.getParameter("kdr");%>
        <input type="hidden" id='cuenta' value="<%=kdr%>">
        <% if(ClienteDAO.enSesion(kdr)){ %>
        <%@include file="headerCliente.jsp"%>
        
        <% }else{ %>
        <a href="Inicio"><h1>Iniciar sesion</h1></a>
        <% } %>
        <%@include file="../../WEB-INF/footer.jsp"%>
	<%@include file="../../WEB-INF/jquery.jsp"%>
    </body>
</html>
