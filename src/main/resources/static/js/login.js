function showSuccessAlert() {
    Swal.fire({
        icon: 'success',
        title: '¡Usuario registrado con éxito!',
        showConfirmButton: false,
        timer: 7000, // Mostrar la alerta durante 3 segundos
    });
}
function showAddCartAlert() {
    Swal.fire({
        icon: 'success',
        title: '¡Agregado al carrito correctamente!',
        showConfirmButton: false,
        timer: 7000, // Mostrar la alerta durante 3 segundos
    });
}
function showCancelAlert() {
    Swal.fire({
        icon: 'info',
        title: '¿Está seguro de que desea cancelar la operación?',
        showConfirmButton: true,
        showCancelButton: true,
        confirmButtonText: 'Sí',
        cancelButtonText: 'No'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = '/login';
        }
    });
}

    function showLogoutAlert(event) {
    event.preventDefault();
    Swal.fire({
            icon: 'info',
            title: '¿Está seguro de que desea cerrar sesion?',
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonText: 'Sí',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '/login';
            }
        });
    }