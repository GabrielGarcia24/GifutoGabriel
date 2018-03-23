<%-- 
    Document   : index
    Created on : 12-Febrero-2018, 10:24:59
    Author     : Christian Camilo Gámez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Inicio | GifutoGifts</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/icono.png">
        <link rel="icon" href="images/icono.png" type="image/x-icon">

        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

        <style>
            .modalDialog {
	position: fixed;
        
	font-family: Arial, Helvetica, sans-serif;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;   
}
.modalDialog:target {
	opacity:1;
	pointer-events: auto;
}
.modalDialog > div {
	width: 400px;
	position: relative;
	margin: 10% auto;
        color: #000;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #fff);
	background: -webkit-linear-gradient(#fff, #fff);
	background: -o-linear-gradient(#fff, #fff);
  -webkit-transition: opacity 400ms ease-in;
-moz-transition: opacity 400ms ease-in;
transition: opacity 400ms ease-in;
}
.close {
	background: #606061;
	color: #000;
	line-height: 25px;
	position: absolute;
	right: -12px;
	text-align: center;
	top: -10px;
	width: 24px;
	text-decoration: none;
	font-weight: bold;
	-webkit-border-radius: 12px;
	-moz-border-radius: 12px;
	border-radius: 12px;
	-moz-box-shadow: 1px 1px 3px #000;
	-webkit-box-shadow: 1px 1px 3px #000;
	box-shadow: 1px 1px 3px #000;
}
        </style>
    </head><!--/head-->

    <body>
        <script src="js/FormularioInicio.js"></script>

        <div id="openModal" class="modalDialog">
            <div>
                <a href="Inicio" title="Close" class="close">X</a>
                <div id="formulario"></div>
            </div>
        </div>
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
                                    <li><a href="#openModal" onclick="formularioRegistrar()"><i class="fa fa-user"></i> Registrar</a></li>


                                    <li><a href="#openModal" onclick="formularioIniciarSesion()"><i class="fa fa-lock"></i> Iniciar sesión</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->


        </header><!--/header-->

        <%@include file="../sliderInicio.jsp"%>

        <hr/><!--confianza-->
        <p align="center">                                     
            <img alt="Elementos de Confianza" src="images/home/confianza.png">
            <img alt="Elementos de Confianza" src="images/home/confianza2.png">
            <img alt="Elementos de Confianza" src="images/home/confianza3.png">
            <img alt="Elementos de Confianza" src="images/home/confianza4.png">
        </p><!--/confianza-->
        <hr/>
        <!--Texto-->
        <section id="feature" class="section-padding wow fadeIn delay-05s">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="wrap-item text-center">
                            <div class="item-img">
                                <img   src="images/home/ser01.png">
                            </div>
                            <h3 class="pad-bt15">Conceptos creativos</h3>
                            <p>Contamos con una seccion donde se generaran las inviaciones de acuerdo al gusto del festejado.</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="wrap-item text-center">
                            <div class="item-img">
                                <img   src="images/home/ser02.png">
                            </div>
                            <h3 class="pad-bt15">Facilidad de organizacion</h3>
                            <p>Te facilitamos la adminsitracion y organizacion de
                                tus eventos con una serie de herramientas que te ayudaran a estar al pendiente de todos los cambios de este.</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="wrap-item text-center">
                            <div class="item-img">
                                <img   src="images/home/ser03.png">
                            </div>
                            <h3 class="pad-bt15">Administracion de costo</h3>
                            <p>Contamos con un catalogo con productos y precios actualizados para poderlos consultar y agregarlos a la mesa o evento.</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="wrap-item text-center">
                            <div class="item-img">
                                <img   src="images/home/ser04.png">
                            </div>
                            <h3 class="pad-bt15">Seguridad</h3>
                            <p>Protejemos tus datos personales asi como de contacto y de transacciones para poder brindarte la mejor seguridad..</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--texto-->
        <br/>


        <!--img-->
        <style>
            .columna img:hover{
                opacity: 1;
            }
        </style>
    <center>
        <table>
            <tr>
                <td class="columna"><img  src="images/home/1.jpg" alt=""></td>
                <td><img  src="images/home/2.jpg" alt=""></td>
                <td><img  src="images/home/3.jpg" alt=""></td>
                <td><img  src="images/home/4.jpg" alt=""></td>
            </tr>
            <tr>
                <td> <img  src="images/home/5.jpg" alt=""></td>
                <td> <img  src="images/home/6.jpg" alt=""></td>
                <td><img  src="images/home/7.jpg" alt=""></td>
                <td><img  src="images/home/8.jpg" alt=""></td>
            </tr>
        </table>
    </center>



    <%@include file="../footer.jsp"%>


    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>

    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
