var userEmail = document.getElementById("si-email");
var password = document.getElementById("si-password");

function inicioSesion() {

    if (userEmail.value == 'admin@esquinallanera.com' & password.value == '333') {
        window.location = "assets/front_end/Dashboard/index.html";
    } else if(userEmail.value == 'cliente@esquinallanera.com' & password.value == '333') {
        window.location = "cliente-index.html";
    }else if(userEmail.value == 'cajero@esquinallanera.com' & password.value == '333'){
		window.location = "assets/front_end/2-dashboard-payouts-caj.html";
	}else{
        alert("error");
    }
	
	
    return false;
}
		

