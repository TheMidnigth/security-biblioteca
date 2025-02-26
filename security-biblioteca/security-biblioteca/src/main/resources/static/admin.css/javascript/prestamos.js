let accionPendiente = "";

function confirmarEdicion() {
    accionPendiente = "editar";
    document.getElementById("modalMensaje").innerText = "¿Seguro que quieres editar este libro?";
    document.getElementById("modalConfirmacion").style.display = "flex";
}

function confirmarEliminacion() {
    accionPendiente = "eliminar";
    document.getElementById("modalMensaje").innerText = "¿Seguro que quieres eliminar este libro?";
    document.getElementById("modalConfirmacion").style.display = "flex";
}

function accionConfirmada() {
    if (accionPendiente === "editar") {
        window.location.href = "../admin.html/EditarLibros.html";
    } else if (accionPendiente === "eliminar") {
        alert("Libro eliminado con éxito.");
    }
    cerrarModal();
}

function cerrarModal() {
    document.getElementById("modalConfirmacion").style.display = "none";
}
