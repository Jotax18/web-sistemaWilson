<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${usuario != null ? 'Actualizar' : 'Registrar'} Usuario | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #f4f6f9;
            color: #333333;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
        }

        .titulo-seccion {
            background-color: #ffffff;
            color: #2c323f;
            padding: 20px 25px;
            border-radius: 4px;
            margin-bottom: 20px;
            border-top: 4px solid #198754;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .card-formulario {
            background: #ffffff;
            padding: 35px 40px;
            border-radius: 4px;
            border: 1px solid #e0e4e8;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
            max-width: 900px;
            margin: 0 auto 30px auto;
        }

        /* Controles limpios */
        .form-label {
            font-weight: 600;
            color: #495057;
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 8px;
        }
        .form-control, .form-select, .input-group-text {
            background-color: #ffffff;
            border: 1px solid #ced4da;
            color: #495057;
            padding: 10px 15px;
        }
        .form-control:focus, .form-select:focus {
            border-color: #86b7fe;
            box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
            z-index: 3;
        }

        /* Botones corporativos */
        .btn { border-radius: 4px; font-weight: 500; padding: 10px 20px; }
        .btn-success { background-color: #198754; border-color: #198754; }
        .btn-success:hover { background-color: #157347; border-color: #146c43; }
        .btn-outline-secondary { color: #6c757d; border-color: #6c757d; }

        .seccion-titulo {
            font-size: 1.1rem;
            color: #0d6efd;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 8px;
            margin-bottom: 20px;
            margin-top: 10px;
        }

        /* Ajuste para el botón del ojito */
        .btn-toggle-password {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            color: #6c757d;
            padding: 10px 15px;
        }
        .btn-toggle-password:hover {
            background-color: #e9ecef;
            color: #495057;
        }
    </style>
</head>
<body>
<div class="d-flex" style="min-height: 100vh; background-color: #f4f6f9;">

    <jsp:include page="sidebar.jsp" />

    <div class="flex-grow-1 overflow-auto">

        <div class="container-fluid py-4 px-4 px-xl-5">

            <div class="titulo-seccion d-flex align-items-center mb-4 ${usuario != null ? 'titulo-edicion' : ''}">
                <h4 class="mb-0 fw-bold">
                    <i class="bi ${usuario != null ? 'bi-pencil-square text-primary' : 'bi-person-plus text-success'} me-2"></i>
                    ${usuario != null ? 'Actualizar Usuario' : 'Registrar Nuevo Usuario'}
                </h4>
            </div>

            <div class="card-formulario">

                <form action="UsuarioServlet" method="post" id="formUsuario" onsubmit="return validarFormulario(event)">

                    <input type="hidden" name="action" value="${usuario != null ? 'actualizarUsuario' : 'registrarUsuario'}">
                    <input type="hidden" name="txtIdUsuario" value="${usuario.idUsuario}">

                    <div class="seccion-titulo">
                        <i class="bi bi-person-vcard me-2"></i>Datos Personales
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-6">
                            <label class="form-label">Nombres Completos <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtNombres" value="${usuario.nombres}" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Apellidos <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtApellidos" value="${usuario.apellidos}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">DNI <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtDni" value="${usuario.dni}" maxlength="8" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Celular</label>
                            <input type="text" class="form-control" name="txtCelular" value="${usuario.celular}"  maxlength="9" minlength="9" required>
                        </div>
                    </div>

                    <div class="seccion-titulo mt-5">
                        <i class="bi bi-shield-lock me-2"></i>Credenciales de Acceso
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-6">
                            <label class="form-label">Username <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtUsuario" value="${usuario.username}" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Correo Electrónico <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" name="txtEmail" value="${usuario.email}" required>
                        </div>

                        <div class="col-md-12">
                            <label class="form-label">Rol del Sistema <span class="text-danger">*</span></label>
                            <select class="form-select" name="cboRol" required>
                                <option value="">Seleccione un rol...</option>
                                <option value="1" ${usuario.rol.idRol == 1 ? 'selected' : ''}>Administrador</option>
                                <option value="2" ${usuario.rol.idRol == 2 ? 'selected' : ''}>Vendedor</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">${usuario != null ? 'Nueva ' : ''}Contraseña <span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="password" class="form-control" name="txtClave" id="txtClave" required placeholder="Mínimo 8 caracteres">
                                <button class="btn btn-toggle-password" type="button" onclick="togglePassword('txtClave', 'iconClave')">
                                    <i class="bi bi-eye" id="iconClave"></i>
                                </button>
                            </div>
                            <c:if test="${usuario != null}">
                                <small class="text-muted">Si no desea cambiarla del usuario, repita la misma.</small>
                            </c:if>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Confirmar Contraseña <span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="txtConfirmarClave" required placeholder="Repita la contraseña">
                                <button class="btn btn-toggle-password" type="button" onclick="togglePassword('txtConfirmarClave', 'iconConfirmar')">
                                    <i class="bi bi-eye" id="iconConfirmar"></i>
                                </button>
                            </div>
                            <div id="msjErrorClave" class="text-danger small mt-1 d-none fw-bold">
                                <i class="bi bi-exclamation-triangle"></i> Las contraseñas no coinciden.
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
                        <a href="UsuarioServlet?action=listar" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Volver al Listado
                        </a>

                        <button type="submit" id="btnGuardar" class="btn ${usuario != null ? 'btn-primary' : 'btn-success'}">
                            <i class="bi bi-floppy me-1"></i> ${usuario != null ? 'Guardar Cambios' : 'Registrar Usuario'}
                        </button>
                    </div>
                </form>
            </div>

        </div> </div> </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Función para mostrar/ocultar contraseña (El ojito)
    function togglePassword(inputId, iconId) {
        const input = document.getElementById(inputId);
        const icon = document.getElementById(iconId);

        if (input.type === "password") {
            input.type = "text";
            icon.classList.remove("bi-eye");
            icon.classList.add("bi-eye-slash");
        } else {
            input.type = "password";
            icon.classList.remove("bi-eye-slash");
            icon.classList.add("bi-eye");
        }
    }

    // Función para validar que las contraseñas sean iguales antes de enviar al Servlet
    function validarFormulario(event) {
        // 1. DETENEMOS EL ENVÍO INMEDIATAMENTE
        event.preventDefault();

        const clave = document.getElementById('txtClave').value;
        const confirmar = document.getElementById('txtConfirmarClave').value;
        const msjError = document.getElementById('msjErrorClave');
        const inputConfirmar = document.getElementById('txtConfirmarClave');

        // 2. Validamos las contraseñas
        if (clave !== confirmar) {
            // Si hay error, mostramos las alertas rojas
            msjError.classList.remove('d-none');
            inputConfirmar.classList.add('is-invalid');
            return false;
        } else {
            // Si las contraseñas coinciden, limpiamos las alertas rojas
            msjError.classList.add('d-none');
            inputConfirmar.classList.remove('is-invalid');

            // 3. MOSTRAMOS EL MODAL DE BOOTSTRAP
            // (Este código asume que estás usando Bootstrap 5)
            const modalElement = document.getElementById('modalConfirmacion');
            const modal = new bootstrap.Modal(modalElement);
            modal.show();

            // Retornamos false porque el formulario no se enviará todavía.
            // Se enviará solo cuando hagan clic en el botón dentro del modal.
            return false;
        }
    }

    // 4. ESCUCHAMOS EL CLIC DEL BOTÓN "SÍ, GUARDAR" DEL MODAL
    document.addEventListener("DOMContentLoaded", function() {
        const btnConfirmar = document.getElementById('btnConfirmarGuardar');
        const formulario = document.getElementById('formUsuario'); // El ID de tu form

        if (btnConfirmar && formulario) {
            btnConfirmar.addEventListener('click', function() {
                // Cuando hagan clic en "Sí, guardar", enviamos el formulario a tu UsuarioServlet.
                // Usar .submit() directamente aquí ignora el "onsubmit" del HTML,
                // evitando que se vuelva a abrir el modal en un bucle infinito.
                formulario.submit();
            });
        }
    });

</script>
<div class="modal fade" id="modalConfirmacion" tabindex="-1" aria-labelledby="tituloModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="tituloModal">Confirmar acción</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas guardar los datos de este usuario?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="btnConfirmarGuardar">Sí, guardar</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>