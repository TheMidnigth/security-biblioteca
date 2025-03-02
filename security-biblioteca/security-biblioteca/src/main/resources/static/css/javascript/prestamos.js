let accionPendiente = "";
let libroId = null;

function confirmarEdicion(id) {
    accionPendiente = "editar";
    libroId = id; // Guarda el ID del libro a editar
    document.getElementById("modalMensaje").innerText = "¿Seguro que quieres editar este libro?";
    document.getElementById("modalConfirmacion").style.display = "flex";
}

function confirmarEliminacion(id) {
    accionPendiente = "eliminar";
    libroId = id; // Guarda el ID del libro a eliminar
    document.getElementById("modalMensaje").innerText = "¿Seguro que quieres eliminar este libro?";
    document.getElementById("modalConfirmacion").style.display = "flex";
}

function accionConfirmada() {
    if (accionPendiente === "editar" && libroId !== null) {
        window.location.href = `/editarLibros/${libroId}`; // Redirigir a la página de edición
    } else if (accionPendiente === "eliminar" && libroId !== null) {
        fetch(`/eliminarLibros/${libroId}`, { method: 'GET' })
            .then(response => {
                if (response.ok) {
                    alert("Libro eliminado con éxito.");
                    window.location.reload();
                } else {
                    alert("Error al eliminar el libro.");
                }
            })
            .catch(error => console.error("Error:", error));
    }
    cerrarModal();
}

function cerrarModal() {
    document.getElementById("modalConfirmacion").style.display = "none";
}
