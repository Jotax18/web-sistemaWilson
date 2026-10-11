<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${clienteEncontrado != null ? 'Actualizar' : 'Registrar'} Cliente | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #f4f6f9;
            color: #333333;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
            margin: 0;
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
        .titulo-edicion {
            border-top-color: #0d6efd;
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
            border-radius: 4px;
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
        .btn-primary { background-color: #0d6efd; border-color: #0d6efd; }
        .btn-primary:hover { background-color: #0b5ed7; border-color: #0a58ca; }
        .btn-outline-secondary { color: #6c757d; border-color: #6c757d; }

        .seccion-titulo {
            font-size: 1.1rem;
            color: #0d6efd;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 8px;
            margin-bottom: 20px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="d-flex" style="min-height: 100vh; background-color: #f4f6f9;">

    <jsp:include page="sidebar.jsp" />

    <div class="flex-grow-1 overflow-auto">

        <div class="container-fluid py-4 px-4 px-xl-5">

            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show mb-4 shadow-sm" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i> ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="titulo-seccion d-flex align-items-center mb-4 ${clienteEncontrado != null ? 'titulo-edicion' : ''}">
                <h4 class="mb-0 fw-bold">
                    <i class="bi ${clienteEncontrado != null ? 'bi-pencil-square text-primary' : 'bi-person-plus text-success'} me-2"></i>
                    ${clienteEncontrado != null ? 'Actualizar Cliente' : 'Registrar Nuevo Cliente'}
                </h4>
            </div>

            <div class="card-formulario">

                <form action="ClienteServlet" method="post" id="formCliente" onsubmit="return validarFormulario(event)">

                    <input type="hidden" name="action" value="${clienteEncontrado != null ? 'actualizarCliente' : 'registrarCliente'}">
                    <input type="hidden" name="txtIdCliente" value="${clienteEncontrado.idCliente}">

                    <div class="seccion-titulo">
                        <i class="bi bi-person-vcard me-2"></i>Datos de Identificación
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-12">
                            <label class="form-label" id="lblNombreRazon">Nombre Completo o Razón Social <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtNombreRazonSocial" id="txtNombreRazonSocial"
                                   value="${clienteEncontrado.nombreRazonSocial}"
                                   placeholder="Ej. Juan Pérez Gómez o Inversiones Tecnológicas S.A.C." required>
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">Tipo de Documento <span class="text-danger">*</span></label>
                            <select class="form-select" name="txtTipoDocumento" id="txtTipoDocumento" onchange="ajustarValidacionDocumento()" required>
                                <option value="">Seleccione...</option>
                                <option value="DNI" ${clienteEncontrado.tipoDocumento == 'DNI' ? 'selected' : ''}>DNI</option>
                                <option value="RUC" ${clienteEncontrado.tipoDocumento == 'RUC' ? 'selected' : ''}>RUC</option>
                                <option value="CE" ${clienteEncontrado.tipoDocumento == 'CE' ? 'selected' : ''}>Carnet de Extranjería (CE)</option>
                                <option value="PASAPORTE" ${clienteEncontrado.tipoDocumento == 'PASAPORTE' ? 'selected' : ''}>Pasaporte</option>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">N° de Documento <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtNumeroDocumento" id="txtNumeroDocumento"
                                   value="${clienteEncontrado.numeroDocumento}"
                                   placeholder="Ingrese número de documento" required>
                            <div id="msjErrorDocumento" class="text-danger small mt-1 d-none fw-bold">
                                <i class="bi bi-exclamation-triangle"></i> <span id="textoErrorDocumento">Número de documento inválido.</span>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">Fecha de Nacimiento / Creación <span class="text-danger">*</span></label>
                            <input type="date" class="form-control" name="txtFechaNacimiento" id="txtFechaNacimiento"
                                   value="${clienteEncontrado.fechaNacimiento}" required>
                        </div>
                    </div>

                    <div class="seccion-titulo mt-5">
                        <i class="bi bi-envelope-at me-2"></i>Información de Contacto
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-6">
                            <label class="form-label">Correo Electrónico <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" name="txtEmail" id="txtEmail"
                                   value="${clienteEncontrado.email}"
                                   placeholder="cliente@ejemplo.com" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Celular <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtCelular" id="txtCelular"
                                   value="${clienteEncontrado.celular}"
                                   maxlength="9" minlength="9" placeholder="Ej. 987654321" required>
                            <div id="msjErrorCelular" class="text-danger small mt-1 d-none fw-bold">
                                <i class="bi bi-exclamation-triangle"></i> El celular debe contener 9 dígitos numéricos.
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
                        <a href="ClienteServlet?action=listar" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Volver al Listado
                        </a>

                        <button type="submit" id="btnGuardar" class="btn ${clienteEncontrado != null ? 'btn-primary' : 'btn-success'}">
                            <i class="bi bi-floppy me-1"></i> ${clienteEncontrado != null ? 'Guardar Cambios' : 'Registrar Cliente'}
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="modalConfirmacion" tabindex="-1" aria-labelledby="tituloModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title fw-bold" id="tituloModal">Confirmar acción</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas guardar los datos de este cliente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn ${clienteEncontrado != null ? 'btn-primary' : 'btn-success'}" id="btnConfirmarGuardar">Sí, guardar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function ajustarValidacionDocumento() {
        const tipoDoc = document.getElementById('txtTipoDocumento').value;
        const inputDoc = document.getElementById('txtNumeroDocumento');
        const msjErrorDoc = document.getElementById('msjErrorDocumento');

        msjErrorDoc.classList.add('d-none');
        inputDoc.classList.remove('is-invalid');

        if (tipoDoc === 'DNI') {
            inputDoc.maxLength = 8;
            inputDoc.placeholder = '8 dígitos (Ej. 71234567)';
        } else if (tipoDoc === 'RUC') {
            inputDoc.maxLength = 11;
            inputDoc.placeholder = '11 dígitos (Ej. 20123456789)';
        } else {
            inputDoc.maxLength = 15;
            inputDoc.placeholder = 'Ingrese N° de documento';
        }
    }

    function validarFormulario(event) {
        event.preventDefault();

        const tipoDoc = document.getElementById('txtTipoDocumento').value;
        const inputDoc = document.getElementById('txtNumeroDocumento');
        const numDoc = inputDoc.value.trim();
        const msjErrorDoc = document.getElementById('msjErrorDocumento');
        const textoErrorDoc = document.getElementById('textoErrorDocumento');

        const inputCelular = document.getElementById('txtCelular');
        const celular = inputCelular.value.trim();
        const msjErrorCelular = document.getElementById('msjErrorCelular');

        let esValido = true;

        // Validación según tipo de documento
        if (tipoDoc === 'DNI' && !/^\d{8}$/.test(numDoc)) {
            textoErrorDoc.textContent = 'El DNI debe contener exactamente 8 dígitos numéricos.';
            msjErrorDoc.classList.remove('d-none');
            inputDoc.classList.add('is-invalid');
            esValido = false;
        } else if (tipoDoc === 'RUC' && !/^\d{11}$/.test(numDoc)) {
            textoErrorDoc.textContent = 'El RUC debe contener exactamente 11 dígitos numéricos.';
            msjErrorDoc.classList.remove('d-none');
            inputDoc.classList.add('is-invalid');
            esValido = false;
        } else {
            msjErrorDoc.classList.add('d-none');
            inputDoc.classList.remove('is-invalid');
        }

        // Validación de celular (9 dígitos)
        if (!/^\d{9}$/.test(celular)) {
            msjErrorCelular.classList.remove('d-none');
            inputCelular.classList.add('is-invalid');
            esValido = false;
        } else {
            msjErrorCelular.classList.add('d-none');
            inputCelular.classList.remove('is-invalid');
        }

        if (!esValido) {
            return false;
        }

        const modalElement = document.getElementById('modalConfirmacion');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();

        return false;
    }

    document.addEventListener("DOMContentLoaded", function () {
        ajustarValidacionDocumento();

        const btnConfirmar = document.getElementById('btnConfirmarGuardar');
        const formulario = document.getElementById('formCliente');

        if (btnConfirmar && formulario) {
            btnConfirmar.addEventListener('click', function () {
                formulario.submit();
            });
        }
    });
</script>
</body>
</html>
