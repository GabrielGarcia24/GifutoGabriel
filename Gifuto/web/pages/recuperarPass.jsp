<%-- 
    Document   : RecuperarPass
    Created on : 05-feb-2018, 8:11:09
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar Contraseña</title>
    </head>
    <body><center> 
        <h2>RECUPERAR CONTRASEÑA</h2>
        
        <form method="get" action="RecuperarContraseña">
            <table>
                <tr>
                    <td>Ingresa tu correo: </td>
                    <td><input type="text" name="para"/></td>
                </tr>
                <tr>
                    <!--<td>DE: </td>-->
                    <td><input type="text" name="de" hidden="" value="antoniomc159807@gmail.com"></td>
                </tr>
                <tr>
                   <!-- <td>CONTRASEÑA DE: </td>-->
                   <td><input type="password" name="clave" hidden="" value="ipdmbwlkyipjbqhp"></td>
                </tr>
                <tr>
                   <!-- <td>ASUNTO: </td>-->
                   <td><input type="text" name="asunto" hidden="" value="Tu contraseña para ver tus eventos"></td>
                </tr>
                <tr>
                   <!-- <td>MENSAJE: </td>-->
                    <td>
                        <input type="text" name="mensaje" value="Tu contraseña es: " hidden="">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar confirmacion"/></td>
                </tr>
            </table>
        </form>
        
    </center>
    </body>
</html>
