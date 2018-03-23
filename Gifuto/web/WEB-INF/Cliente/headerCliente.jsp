<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>

<%
    Date dNo = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dNo);
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    Date ok = calendar.getTime();
    SimpleDateFormat ftt
            = new SimpleDateFormat("yyyy-MM-dd");

    Date dN = new Date();
    Calendar calenda = Calendar.getInstance();
    calenda.setTime(dN);
    calenda.add(Calendar.YEAR, 1);
    Date okk = calenda.getTime();
    SimpleDateFormat fttt
            = new SimpleDateFormat("yyyy-MM-dd");

    String fechadia = ftt.format(ok);
    String fechaano = fttt.format(okk);
%>

<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href="#"><i class="fa fa-phone"></i> (55) 54 23 32 12</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> gifutogifts@gmail.com</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div><!--/header_top-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a ><img src="images/home/logos.png" alt="" /></a>
                    </div>


                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="#" onclick="DinamicoDiv('MiCuenta');mostrarCuenta();"><i class="fa fa-user"></i> Mi cuenta</a></li>
                            <li><a href="Inicio" onclick='cerrarsesion()'><i class="fa fa-user"></i> Cerrar sesión</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Navegación</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>

                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="#" onclick="DinamicoDiv('crear');">Crear mesa de regalos</a></li>
                            <li><a href="#" onclick="DinamicoDiv('miMesa');">Mi mesa de regalos</a></li>
                            <li><a href="#" onclick="DinamicoDiv('servicios');">Servicios especiales</a></li>
                            <li><a href="#" onclick="cagarcomboMesa();DinamicoDiv('buscar');">Buscar mesa de regalos</a></li>

                        </ul>
                    </div>

                </div>


            </div>
            <div id="crear" style="display: none">
                <h3>Crear mesa de regalos</h3><br/>

            </div>
            
            <div id="miMesa" style="display: none">
                <h3>Mi mesa de regalos</h3>
               

            </div>
            
            <div id="servicios" style="display: none">
                <h3>Servicios especiales</h3>

                <div id="formulario" style="text-align-last: center">
                   

                    <h2>SERVICIO</h2> 
                    Desea un servicio?<br>
                    SI<input type="radio" required="" name="SE" id="siServicio" onclick="mostrarServicios();">
                    NO<input type="radio" required="" name="SE" id="noServicio" onclick="borraTablaServicios();validarOpcion();" value="No">
                    <div id="tablaServicios"></div>
                    <div id="seleccionServicio"></div>

                    <h2>REGALO</h2> 
                    Desea un regalo?<br>
                    SI<input type="radio" required="" name="RE" id="siRegalo" onclick="catalogoRegalos();"> 
                    NO<input type="radio" required="" name="RE" id="noRegalo" onclick="borraTablaRegalos();validarOpcion();" value="S/C">
                    <div id="catalogoRegalos"></div>
                    <div id="tablaRegalos"></div>
                    <div id="seleccionRegalo"></div>

                    <h2>TIPO PAGO</h2>
                    <input type="radio" id="tarjeta" name="tipoPago" value="tarjeta" required="">Tarjeta<br/>
                    <input type="radio" id="monedero" name="tipoPago" value="monedero" required="">Monedero Electronico<br/>

                    <h2>DIRECCI&Oacute;N DEL ENVIO</h2>
                    Calle:<br/> <input type="text" id="calle" required="" onk/><br/>
                    Colonia:<br/> <input type="text" id="colonia" required=""/><br/>
                    Codigo Postal:<br/> <input size="5" maxlength="5" type="text" id="codigoPostal" required=""/><br/>
                    Municipio:<br/> <select required="" id="municipio">
                        <option value="0">Seleciona</option>
                        <option value="Ecatepec">Ecatepec</option>
                        <option value="Tecamac">Tecamac</option>
                    </select>

                    <h2>DETALLES DE ENVIO</h2>
                    Hora de entrega:<br/> <input  style="text-align-last: right" type="number" min="10" max="22" id="horaEnvio" />:00h<br/>
                    Fecha Entrega:<br/> <input type="date" id="fechaEnvio" required="" onblur="tipoEnvio();" min="<%=fechadia%>" max="<%=fechaano%>"/><br/>
                    Tipo de envio<br/> <a id="tipoEnvio"><input readonly></a> <br/><br/>
                    Comentarios o instruciones especiales para el evento:
                    <br/><textarea  id="comentarioEnvio" rows="10" cols="50" required="" style="resize: none" maxlength="250"></textarea>
                    <br><input class="bottom btn-success" style="width: 200px" type="submit" value="Continuar" onclick="enviarFormulario();">
                    <input class="bottom btn-danger" onclick="cancelar();" value="Cancelar">

                </div>

                <div id="solicitarPass" style="display: none">


                </div>

                <div id="respuestaPAssFinal" >

                </div>

            </div>
                    
            <div id="buscar" style="display: none">
                <h3>Buscar mesa de regalos</h3>
                
                <div id="1">
                        
                </div>

            </div>
            <div id="MiCuenta" style="display: none">
                <h3>Mi cuenta</h3>



            </div>
        </div>
    </div><!--/header-bottom-->
</header><!--/header-->