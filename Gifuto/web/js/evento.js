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

function buscar() {
    crearObjeto();
    var nombre = document.getElementById("nombre");
    var nom = nombre.value;
    xhr.open("POST","BuscarE", true);
    xhr.onreadystatechange = verificaDetalle;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send("nombre=" + nom);
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

function prueba(){
    alert("prueba");
}

/// Liz er si sire :( 

function createRequest() {
    request = false;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        //alert("Creating request");
    }
    return request;
}

var reqSave;
function sendData() {
    //alert("send save");
    reqSave =createRequest() ;
    reqSave.open("POST", "../VerTabla", true);
    //alert("open save");
    //  Solo peticiones de tipo POST
    reqSave.setRequestHeader("Content-Type", 
            "application/x-www-form-urlencoded");
    //alert("save header ready");
    reqSave.onreadystatechange =receiveList;
    //alert("onready save");
    parameters = "nombre1=" + document.getElementById("nombre").value;
    //alert(parameters);
    reqSave.send(parameters);
}
function receiveList() {
    //readyState: nos dice en que estado esta la petición 
    //Si manda un 4 la respuesta ya esta lista
    //
    //
    //alert("ready = " + reqList.readyState);
    if (reqSave.readyState === 4) {
        div = document.getElementById("list");
        //Nos dice el estado de la respuesta
        //Error 404 no existe el archivo(servlet)
        //502 error de conexion
        //cuando hace lo que yo le pedi me regresa un 200
       // alert("status = " + reqList.status);
        if (reqSave.status === 200 ) {
            //alert(reqList.responseText);
            div.innerHTML = reqSave.responseText;
        } else {
            div.innerHTML ="Error " + reqSave.status 
                + ": " + reqSave.statusText;
        }
    }
}

