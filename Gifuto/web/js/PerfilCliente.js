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
function cerrarsesion() {
    crearObjeto();

    var cuenta = document.getElementById("cuenta").value;

    xhr.open("POST", "Cliente", true);
    xhr.onreadystatechange = detalleSesionCliente;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("cuenta=" + cuenta);
}

function detalleSesionCliente() {
    if (xhr.readyState == 4) {
        alert("GifutoGifts");
    }
}

var divInicial = "crear";
// bloques
function DinamicoDiv(divActual) {
    crearObjeto();
    document.getElementById(divActual).style.display = 'block';
    if (divInicial != divActual) {
        document.getElementById(divInicial).style.display = 'none';
    }
    divInicial = divActual;

}

function mostrarCuenta() {
    var kdr = document.getElementById("cuenta").value;
    var parte = "1";
    crearObjeto();

    xhr.open("POST", "mi_cuenta_info", true);
    xhr.onreadystatechange = detalleCuenta;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("kdr=" + kdr+"&parte="+parte);

}


function detalleCuenta() {
    if (xhr.readyState == 4) {

        document.getElementById("MiCuenta").innerHTML = xhr.responseText;
    }
}

function enviarModificacion(){
    var kdr = document.getElementById("cuenta").value;
    var parte = "2";
    var domicilio = document.getElementById("domicilio").value;
    var telefono= document.getElementById("telefono").value;
    crearObjeto();

    xhr.open("POST", "mi_cuenta_info", true);
    xhr.onreadystatechange = finModificacion;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("kdr=" + kdr+"&parte="+parte+"&domicilio="+domicilio+"&telefono="+telefono);
    
}
function finModificacion(){
  if (xhr.readyState == 4) {

        document.getElementById("MiCuenta").innerHTML = xhr.responseText;
    }
}   

function cagarcomboMesa(){
    alert("lkjlk");
    
    var kdr = document.getElementById("cuenta").value;
    alert(kdr);
    crearObjeto();
    xhr.open("POST", "\BuscarMesaPORID", true);
    xhr.onreadystatechange = cargaelcombo;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("kdr=" + kdr);
   
       
}

function cargaelcombo(){
  if (xhr.readyState == 4) {

        document.getElementById("1").innerHTML ="<p>"+"lklkjlkjl"+xhr.responseText+"</p>";
    }
}