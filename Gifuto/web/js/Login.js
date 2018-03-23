
var xhr;

function crearObjeto() {
    if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHttp");
    } else if ((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined) {

        xhr = new XMLHttpRequest();
       // alert("1");
    } else {
        alert("Su navegador no es compatible con AJAX");
        return;
    }
}

function ingresar() {
    crearObjeto();
    var user = document.getElementById("usua");
    var us = user.value;
    var contra = document.getElementById("contra");
    var con = contra.value;
    xhr.open("POST","NewLogin", true);
    xhr.onreadystatechange = verificaDetalle;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("us=" + us+"&con=" + con);
}

function verificaDetalle() {
    if (xhr.readyState == 4) {
        respuesta();
    }
}

function respuesta() {
    //window.open("entrada.jsp");
    if (xhr.responseText == "SI") {
      //   alert("admin");
        window.location.href="homeA.jsp";
        document.getElementById("respuesta").innerHTML = "<em>Correcto</em>";
        //window.close("index.html");
    } else{
        //  alert("user" );
        if(xhr.responseText == "NO"){
        window.location.href="homeU.jsp";
         document.getElementById("respuesta").innerHTML = "<em>Correcto</em>";
        
    }else{  alert("error" );
         document.getElementById("respuesta").innerHTML = "<em>" + xhr.responseText + "</em>";
    }
}
}

function enviaPeticion(){
    var user = document.getElementById("usua");
    var us = user.value;
    var contra = document.getElementById("contra");
    var con = contra.value;
     xhr.open("POST","Servlet",true);
    xhr.onreadystatechange = verificaDetalle;
    xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    //Encripta los datos y vaiables
    xhr.send("usua"+us);
    xhr.send("contra"+con);
}