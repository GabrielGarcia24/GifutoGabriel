


var xhr;
// objeto http
function crearObjeto() {
    if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHttp");
    } else if ((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined) {
        xhr = new XMLHttpRequest();
    } else {
        alert("Su navegador no es compatible con AJAX");
        return;
    }

}

function IniciarSesion() {
    var cuenta = document.getElementById("sesionEmail").value;
    var pass = document.getElementById("sesionPass").value;
    xhr.open("POST", "SesionCliente", true);
    xhr.onreadystatechange = detalleSesionCliente;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    xhr.send("cuenta=" + cuenta + "&pass=" + pass);
}
function detalleSesionCliente() {
    if (xhr.readyState == 4) {
       
        if ("admin" == xhr.responseText) {
             window.location.replace("Admin");
        } else if ("Clave o/y contraseña invalida" != xhr.responseText) {
            
             window.location.replace("Cliente?kdr="+xhr.responseText);
                 
        } else {
            document.getElementById("respuestaFromularioReg").innerHTML = "<label style='color:red'>"+xhr.responseText+"</label>";
        }
    }
}

function  formularioIniciarSesion() {
    crearObjeto();

    document.getElementById("formulario").innerHTML = "<section>\
<div style='color:Black' class='container'>\
<h3>Iniciar sesión</h3>\
<table style='text-align: right'>\
<tr>\
<td>Email:</td><td><input type='text' id='sesionEmail'><br></td>\
</tr>\
<tr>\
<td>Contarseña:</td> <td><input type='password' id='sesionPass'></td>\
</tr>\
<tr>\
<td> <button class='btn btn-success' onclick='IniciarSesion()'><span>I</span>niciar sesión</button></td><td><img src='images/home/logos.png' alt='' /></td>\n\
</tr>\
</table><br/>\
<button class='btn btn-danger' onclick='formularioRegistrar()'><span><span>R</span>egistrar</button>\
</div>\
<div id='respuestaFromularioReg'></div></section>";
}

function  formularioRegistrar() {
    crearObjeto();

    document.getElementById("formulario").innerHTML = "<section>\
<div style='color:Black' class='container'>\n\
<h3>Registro de usuario</h3>\
<table style='text-align: right'>\
<tr>\
<td>Nombre:</td><td><input type='text' id='nomRegistro'><br></td>\
</tr>\
<tr>\
<td>Apellido Paterno:</td> <td><input type='text' id='aPARegistro'></td>\
</tr>\
<tr>\
<td>Apellido Materno:</td><td><input type='text' id='aMARegistro'></td>\
</tr>\
<tr>\
<td>Calle:</td> <td><input type='text' id='calleRegistro'><br></td>\
</tr>\
<tr>\
<td>Colonia:</td><td><input type='text' id='coloniaRegistro'></td>\
</tr>\
<tr>\
<td>Municipio:</td><td><input type='text' id='municipioRegistro'></td>\
</tr>\
<tr>\
<td>C&oacute;digo Postal:</td><td><input type='number' id='cpRegistro'></td>\
</tr>\
<tr>\
<td>Correo Electronico:</td><td> <input type='text' id='emailRegistro' placeholder='cuenta@dominio.com'></td>\
</tr>\
<tr>\
<td>Telefono:</td><td><input type='number' id='telRegistro' min='0' placeholder='## #### ####'><br></td>\
</tr>\
<tr><td>Numero de Tarjeta:</td><td><input type='number' min='0' id='tarjetaRegistro' placeholder='16 digitos'></td>\n\
</tr>\n\
<tr><td><button class='btn btn-success' onclick='registrar()'>Registrar</button></td><td><img src='images/home/logos.png' alt='' /></td>\n\
</tr>\n\
</table><br/>\
 <button class='btn btn-danger' onclick='formularioIniciarSesion()'><span>I</span>niciar sesión</button>\
</div>\n\
<div id='respuestaFromularioReg'></div></section>";
}
function registrar(){
    crearObjeto();
   
    var nomRegistro = document.getElementById("nomRegistro").value;
    var aPARegistro = document.getElementById("aPARegistro").value;
    var aMARegistro = document.getElementById("aMARegistro").value;
    var calleRegistro = document.getElementById("calleRegistro").value;
    var coloniaRegistro = document.getElementById("coloniaRegistro").value;
    var municipioRegistro = document.getElementById("municipioRegistro").value;
    var cpRegistro = document.getElementById("cpRegistro").value;
    var emailRegistro = document.getElementById("emailRegistro").value;
    var telRegistro = document.getElementById("telRegistro").value;
    var tarjetaRegistro = document.getElementById("tarjetaRegistro").value;
    
    xhr.open("POST", "AltaCliente", true);
    xhr.onreadystatechange = registroNuevoUsuario;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    xhr.send("nomRegistro=" + nomRegistro + "&aPARegistro=" + aPARegistro+ "&aMARegistro=" + aMARegistro+ "&calleRegistro=" + calleRegistro+ "&coloniaRegistro=" + coloniaRegistro+ "&municipioRegistro=" + municipioRegistro+ "&cpRegistro=" + cpRegistro
            + "&emailRegistro=" + emailRegistro+ "&telRegistro=" + telRegistro+ "&tarjetaRegistro=" + tarjetaRegistro);

}

function  registroNuevoUsuario(){
    if(xhr.readyState==4){
        if(xhr.responseText.length>0){
            document.getElementById("formulario").innerHTML = xhr.responseText;
        }else{
            formularioIniciarSesion();
        }
   
    }
}