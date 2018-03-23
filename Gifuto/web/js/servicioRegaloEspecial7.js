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
//////////////////////////Seleccionar Servicio////////////////////////
function mostrarServicios() {

    crearObjeto();
    var parte = "1";
    xhr.open("POST", "TablasServicioRegalos", true);
    xhr.onreadystatechange = tablaServicio;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("parte=" + parte);
}

function tablaServicio() {
    if (xhr.readyState == 4) {
//        alert("ok");
        document.getElementById("tablaServicios").innerHTML = xhr.responseText;
        var uno = document.getElementById("siServicio").checked = false;
    }

}

function seleccionServicio(id, nom, des, cos) {
    document.getElementById("tablaServicios").innerHTML = "<br>Usted eligio\n\
<br>ID Servicio: <br/><input type='text' disabled='' id='idServ' value='" + id + "'>\n\
<br>Nombre del Servicio:<br/> <input type='text' id='nom' disabled='' value='" + nom + "'>\n\
<br>Descipcion: <br/><input type='text' disabled='' id='des' value='" + des + "'>\
<br/>Costo: <br/><input type='text' id='cos' disabled='' value='" + cos + "'>";
    var uno = document.getElementById("siServicio").checked = true;
}

function borraTablaServicios() {
    document.getElementById("tablaServicios").innerHTML = "<h3>Sin servicio<h3>";
}
///////////////////////////////////////Seleccionar Regalo//////////////////////////////////

function catalogoRegalos() {
    crearObjeto();
    var parte = "2";
    xhr.open("POST", "TablasServicioRegalos", true);
    xhr.onreadystatechange = comboRegalo;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("parte=" + parte);
    var uno = document.getElementById("siRegalo").checked = false;

}

function comboRegalo() {
    if (xhr.readyState == 4) {

        document.getElementById("catalogoRegalos").innerHTML = xhr.responseText;
    }
}

function tablaRegalos() {
    crearObjeto();
    var parte = "3";
    var categoria = document.getElementById("categoria").value;
    xhr.open("POST", "TablasServicioRegalos", true);
    xhr.onreadystatechange = mostrarTabla;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send("parte=" + parte + "&categoria=" + categoria);

}

function mostrarTabla() {
    if (xhr.readyState == 4) {

        document.getElementById("tablaRegalos").innerHTML = xhr.responseText;
    }
}

function seleccionRegalo(id, nom, des, cos, exis) {
    document.getElementById("tablaRegalos").innerHTML = "<br>Usted eligio\n\
<br>ID Regalo:<br/> <input type='text' disabled='' id='idRegalo' value='" + id + "'>\n\
<br>Nombre del Producto:<br/> <input type='text' id='nomRegalo' disabled='' value='" + nom + "'>\n\
<br>Descipcion:<br/> <input type='text' disabled='' id='desRegalo' value='" + des + "'>\
<br/>Costo:<br/> <input type='text' id='cosRegalo' disabled='' value='" + cos + "'>\n\
<input type='hidden' id='existenciaRegalo' value='" + exis + "'>";
    var uno = document.getElementById("siRegalo").checked = true;

}

function borraTablaRegalos() {
    document.getElementById("catalogoRegalos").innerHTML = "<h3>Sin<h3>";
    document.getElementById("tablaRegalos").innerHTML = "<h3>Regalo<h3>";
}
///////////////////////////////////////////////////////////////////////////////////////////
function tipoEnvio() {
    crearObjeto();
    var fecha = document.getElementById("fechaEnvio").value;
    var parte = "4";
    xhr.open("POST", "TablasServicioRegalos", true);
    xhr.onreadystatechange = tipoEnvioRes;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.send("parte=" + parte + "&fecha=" + fecha);
}

function tipoEnvioRes() {
    if (xhr.readyState == 4) {

        document.getElementById("tipoEnvio").innerHTML =
                "<input type='text' disabled='' id='resTipoEnvio' value='" + xhr.responseText + "'>";
    }
}
/////////////////////////////////////////////////////////////////////////////////////
function validarOpcion() {
    var uno = document.getElementById("noServicio");
    var dos = document.getElementById("noRegalo");

    if (uno.checked && dos.checked) {
        alert("Debes elegir una opcion entre Servicio o Regalo");
        uno.checked = false;
        dos.checked = false;
    }
}

function enviarFormulario() {
//    alert("si sirve el scrip");
    var calle = document.getElementById("calle").value;
    var colonia = document.getElementById("colonia").value;
    var codigoPostal = document.getElementById("codigoPostal").value;
    var siServi = document.getElementById("siServicio");
    var siReg = document.getElementById("siRegalo");
    var tarjeta = document.getElementById("tarjeta");
    var monedero = document.getElementById("monedero");
    var comMunicipio = document.getElementById("municipio");
    var hora = document.getElementById("horaEnvio").value;
    var tipoEn = document.getElementById("fechaEnvio").value;

    var opc;
    if (siServi.checked || siReg.checked) {
        opc = true
    } else {
        opc = false;
    }
    var opc2;
    if (tarjeta.checked || monedero.checked) {
        opc2 = true
    } else {
        opc2 = false;
    }
    var opc3;
    if (comMunicipio.value == "0") {
        opc3 = false;
    } else {
        opc3 = true;
    }


    if (calle == null || calle.length == 0 || /^\s+$/.test(calle)) {
        alert('ERROR: El campo Calle no debe ir vacío o lleno de solamente espacios en blanco');
        document.getElementById("calle").focus();
    } else if (colonia == null || colonia.length == 0 || /^\s+$/.test(colonia)) {
        alert('ERROR: El campo colonia no debe ir vacío o lleno de solamente espacios en blanco');
        document.getElementById("colonia").focus();
    } else if (codigoPostal == null || codigoPostal.length < 5 || /^\s+$/.test(codigoPostal)) {
        alert('ERROR: El campo codigoPostal no debe ir vacío o lleno de solamente espacios en blanco min 5 digitos');
        document.getElementById("codigoPostal").focus();
    } else if (!opc) {
        alert('ERROR: Debes elegir un regalo o un servicio');
        siServi.focus();
    } else if (!opc2) {
        alert('ERROR: Debes elegir un tipo de pago');
        tarjeta.focus();
    } else if (!opc3) {
        alert('ERROR: Debes elegir un Municipio');
        comMunicipio.focus();
    } else if (tipoEn == null || tipoEn.length == 0) {
        alert('ERROR: Elige una fecha para la entrega');
        document.getElementById("tipoEnvio").focus();
    } else if (hora == null || hora.length == 0) {
        alert('ERROR: Elige una hora de envio');
        document.getElementById("horaEnvio").focus();
    } else {
        alert("todoValido");
        var parte = "1";
        crearObjeto();
        xhr.open("POST", "ProcesarDatosSRE", true);
        xhr.onreadystatechange = respValidacionCCC;
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("parte=" + parte + "&calle=" + calle + "&colonia=" + colonia
                + "&codigoPostal=" + codigoPostal);
    }

}

function respValidacionCCC() {

    if (xhr.readyState == 4) {

        if (xhr.responseText.length == 0) {
            solicitarPass();
        } else if (xhr.responseText == '1') {
            alert("ERROR: campo calleSOLO LETRAS y NUMEROS");
            document.getElementById("calle").focus();
        } else if (xhr.responseText == '2') {
            alert("ERROR: campo colonia SOLO LETRAS y NUMEROS");
            document.getElementById("colonia").focus();
        } else if (xhr.responseText == '3') {
            alert("ERROR: campo codigo Postal SOLO NUMEROS");
            document.getElementById("codigoPostal").focus();
        } else if (xhr.responseText == '12') {
            alert("ERROR: campo calle y colonia SOLO LETRAS y NUMEROS");
            document.getElementById("calle").focus();
            document.getElementById("colonia").focus();
        } else if (xhr.responseText == '13') {
            alert("ERROR: campo, calle SOLO LETRAS y NUMEROS | codigo Postal SOLO NUMEROS");
            document.getElementById("calle").focus();
            document.getElementById("codigoPostal").focus();
        } else if (xhr.responseText == '23') {
            alert("ERROR: colonia SOLO LETRAS y NUMEROS | codigo Postal SOLO NUMEROS");
            document.getElementById("colonia").focus();
            document.getElementById("codigoPostal").focus();

        } else if (xhr.responseText == '123') {
            alert("ERROR: campo calle, colonia SOLO LETRAS y NUMEROS | codigo Postal SOLO NUMEROS");
            document.getElementById("calle").focus();
            document.getElementById("colonia").focus();
            document.getElementById("codigoPostal").focus();

//        } else {
//            alert("TodoValido de new");
//            solicitarPass();
//        }
        }
    }
}
//////////////////////////////////////////////////////////////////////
function solicitarPass() {
    var siRegalo = document.getElementById("siRegalo");
    var siServicio = document.getElementById("siServicio");
    var parte;
    var tipoEnvio = document.getElementById("resTipoEnvio").value;
    var tarjeta = document.getElementById("tarjeta");
    var monedero = document.getElementById("monedero");
    var tipopago;
    if (tarjeta.checked) {
        tipopago = tarjeta.value;
    } else {
        tipopago = monedero.value;
    }

    if (siRegalo.checked && siServicio.checked) {
        parte = "4";
        crearObjeto();
        //////////////////Servicios////////////////////////
        var nom = document.getElementById("nom").value;
        var des = document.getElementById("des").value;
        var cos = document.getElementById("cos").value;
        //////////////////Regalo/////////////////////////
        var nomRegalo = document.getElementById("nomRegalo").value;
        var desRegalo = document.getElementById("desRegalo").value;
        var cosRegalo = document.getElementById("cosRegalo").value;

        xhr.open("POST", "ProcesarDatosSRE", true);
        xhr.onreadystatechange = crearDetalleSRE;
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("parte=" + parte + "&nom=" + nom
                + "&des=" + des + "&cos=" + cos + "&nomRegalo=" + nomRegalo +
                "&desRegalo=" + desRegalo + "&cosRegalo=" + cosRegalo + "&tipoEnvio=" + tipoEnvio + "&tipoPago=" + tipopago);


    } else if (siRegalo.checked) {
        crearObjeto();
        parte = "3";

        var nomRegalo = document.getElementById("nomRegalo").value;
        var desRegalo = document.getElementById("desRegalo").value;
        var cosRegalo = document.getElementById("cosRegalo").value;
        xhr.open("POST", "ProcesarDatosSRE", true);
        xhr.onreadystatechange = crearDetalleSRE;
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("parte=" + parte + "&nomRegalo=" + nomRegalo +
                "&desRegalo=" + desRegalo + "&cosRegalo=" + cosRegalo + "&tipoEnvio=" + tipoEnvio + "&tipoPago=" + tipopago);


    } else if (siServicio.checked) {
        crearObjeto();
        parte = "2";
        var nom = document.getElementById("nom").value;
        var des = document.getElementById("des").value;
        var cos = document.getElementById("cos").value;
        xhr.open("POST", "ProcesarDatosSRE", true);
        xhr.onreadystatechange = crearDetalleSRE;
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("parte=" + parte + "&nom=" + nom
                + "&des=" + des + "&cos=" + cos + "&tipoEnvio=" + tipoEnvio + "&tipoPago=" + tipopago);

    } else {

    }
}

function crearDetalleSRE() {
    if (xhr.readyState == 4) {
        document.getElementById("formulario").style.display = 'none';
        document.getElementById("solicitarPass").style.display = 'block';
        document.getElementById("solicitarPass").innerHTML = xhr.responseText;
    }
}

function ejectuarPass(opc, total, tipoPago, precioEnvio) {
    var totalApagar = total;
    var tipoP = tipoPago.value;
    var tipoEnvioN = document.getElementById("resTipoEnvio").value;
    var calle = document.getElementById("calle").value;
    var colonia = document.getElementById("colonia").value;
    var codigoPostal = document.getElementById("codigoPostal").value;
    var municipio = document.getElementById("municipio").value;
    var horaEnvio = document.getElementById("horaEnvio").value;
    var fechaEnvio = document.getElementById("fechaEnvio").value;
    var comentarioEnvio = document.getElementById("comentarioEnvio").value;
    var cuentaUsuario = document.getElementById("cuenta").value;
    var passUser = document.getElementById("passUser").value;
    var precioEn = precioEnvio;
    var domicilio = "Calle " + calle + " Colonia " + colonia + " CP " + codigoPostal + " Municipio " + municipio;



    switch (opc) {
        case "2":
            var parte = "2";
            var id_servicio = document.getElementById("idServ").value;
            crearObjeto();
            xhr.open("POST", "finSREbd", true);
            xhr.onreadystatechange = respFinal;
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send("parte=" + parte + "&totalApagar=" + totalApagar
                    + "&tipoP=" + tipoP + "&domicilio=" + domicilio + "&horaEnvio=" + horaEnvio + "&fechaEnvio=" + fechaEnvio
                    + "&comentarioEnvio=" + comentarioEnvio + "&cuentaUsuario=" + cuentaUsuario + "&passUser=" + passUser
                    + "&precioEn=" + precioEn + "&id_servicio=" + id_servicio + "&tipoEnvioN=" + tipoEnvioN);

            break;
        case "3":
            var parte = "3";
            var id_regalo = document.getElementById("idRegalo").value;
            var existenciaRegalo = document.getElementById("existenciaRegalo").value;
            crearObjeto();
            xhr.open("POST", "finSREbd", true);
            xhr.onreadystatechange = respFinal;
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send("parte=" + parte + "&totalApagar=" + totalApagar
                    + "&tipoP=" + tipoP + "&domicilio=" + domicilio + "&horaEnvio=" + horaEnvio + "&fechaEnvio=" + fechaEnvio
                    + "&comentarioEnvio=" + comentarioEnvio + "&cuentaUsuario=" + cuentaUsuario + "&passUser=" + passUser
                    + "&precioEn=" + precioEn + "&id_regalo=" + id_regalo + "&existenciaRegalo=" + existenciaRegalo + "&tipoEnvioN=" + tipoEnvioN);

            break;
        case "4":
            var parte = "4";
            var id_regalo = document.getElementById("idRegalo").value;
            var existenciaRegalo = document.getElementById("existenciaRegalo").value;
            var id_servicio = document.getElementById("idServ").value;
            crearObjeto();
            xhr.open("POST", "finSREbd", true);
            xhr.onreadystatechange = crearDetalleSRE;
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send("parte=" + parte + "&totalApagar=" + totalApagar
                    + "&tipoP=" + tipoP + "&domicilio=" + domicilio + "&horaEnvio=" + horaEnvio + "&fechaEnvio=" + fechaEnvio
                    + "&comentarioEnvio=" + comentarioEnvio + "&cuentaUsuario=" + cuentaUsuario + "&passUser=" + passUser
                    + "&precioEn=" + precioEn + "&id_regalo=" + id_regalo + "&existenciaRegalo=" + existenciaRegalo + "&id_servicio=" + id_servicio);

            break;
    }

}
function respFinal() {
    if (xhr.readyState == 4) {
        if ("" != xhr.responseText) {
            document.getElementById("respuestaPAssFinal").innerHTML = "<p>" + xhr.responseText + "</p>";
        } else {
           window.location.reload();
        }

    }
}



function cancelar(){
//    window.location.replace("Cliente");
window.location.reload();
}