<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Listado Usuarios | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        /* Tema ERP / Administrativo Claro */
        body {
            background-color: #f4f6f9; /* Fondo gris muy claro típico de sistemas web */
            color: #333333;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
        }

        /* Contenedores y Tarjetas */
        .titulo-seccion {
            background-color: #ffffff;
            color: #2c323f; /* Color oscuro del sidebar de tu imagen */
            padding: 20px 25px;
            border-radius: 4px;
            margin-bottom: 20px;
            border-top: 4px solid #0d6efd; /* Acento azul en la parte superior */
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .card-panel {
            background: #ffffff;
            padding: 20px;
            border-radius: 4px;
            border: 1px solid #e0e4e8;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
            margin-bottom: 25px;
        }

        /* Formulario y Controles */
        .form-control, .input-group-text {
            background-color: #ffffff;
            border: 1px solid #ced4da;
            color: #495057;
            border-radius: 4px;
        }
        .form-control:focus {
            border-color: #86b7fe;
            box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
        }

        /* Botones estilo corporativo */
        .btn { border-radius: 4px; font-weight: 500; letter-spacing: 0.3px; }
        .btn-primary { background-color: #0d6efd; border-color: #0d6efd; }
        .btn-primary:hover { background-color: #0b5ed7; border-color: #0a58ca; }
        .btn-success { background-color: #198754; border-color: #198754; } /* Botón Nuevo Usuario */
        .btn-outline-secondary { color: #6c757d; border-color: #6c757d; }

        /* Tabla Corporativa */
        .table { margin-bottom: 0; }
        .table-light-header th {
            background-color: #f8f9fa;
            color: #495057;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.85rem;
            border-bottom: 2px solid #dee2e6;
            padding: 12px 15px;
        }
        .table tbody tr { transition: background-color 0.15s ease-in-out; }
        .table tbody tr:hover { background-color: #f1f4f8; }
        .table td {
            vertical-align: middle;
            padding: 12px 15px;
            color: #50555a;
            border-bottom: 1px solid #e9ecef;
            font-size: 0.9rem;
        }

        /* Badges de Estado más sobrios */
        .badge-activo { background-color: #d1e7dd; color: #0f5132; }
        .badge-inactivo { background-color: #f8d7da; color: #842029; }
    </style>
</head>
<body>
<div class="container-fluid py-4 px-4 px-xl-5">

    <form action="UsuarioServlet" method="get">
        <div class="titulo-seccion d-flex align-items-center justify-content-between">
            <h4 class="mb-0 fw-bold"><i class="bi bi-people-fill me-2 text-primary"></i> Administración de Usuarios</h4>
            <a href="UsuarioServlet?action=cargarFormularioRegistrar" class="btn btn-success shadow-sm">
                <i class="bi bi-person-plus-fill me-1"></i> Nuevo Usuario
            </a>
        </div>
    </form>

    <div class="card-panel">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-end gap-3">

            <div>
                <a href="UsuarioServlet?action=listar" class="btn btn-primary px-4">
                    <i class="bi bi-list-ul me-2"></i>Ver Todo el Listado
                </a>
            </div>

            <form action="UsuarioServlet" method="get" class="m-0">
                <input type="hidden" name="action" value="listarUsuarioDni">

                <div class="d-flex flex-column flex-sm-row align-items-sm-end gap-2">
                    <div>
                        <label class="text-secondary mb-1 small fw-bold">Buscar por DNI</label>
                        <div class="input-group" style="max-width: 260px;">
                            <span class="input-group-text"><i class="bi bi-search"></i></span>
                            <input class="form-control" name="txtDni" type="text" maxlength="8" minlength="8" placeholder="Ingrese 8 dígitos" required>
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </div>

                    <div>
                        <a href="UsuarioServlet?action=listar" class="btn btn-outline-secondary" title="Limpiar filtro">
                            <i class="bi bi-eraser"></i>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="card-panel p-0 overflow-hidden">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-light-header">
                <tr>
                    <th>Id</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>DNI</th>
                    <th>Celular</th>
                    <th>Username</th>
                    <th>Clave</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Fecha Creación</th>
                    <th class="text-center">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listaUsuario}" var="u">
                    <tr>
                        <td class="fw-bold text-dark">${u.idUsuario}</td>
                        <td>${u.nombres}</td>
                        <td>${u.apellidos}</td>
                        <td>${u.dni}</td>
                        <td>${u.celular}</td>
                        <td>${u.username}</td>
                        <td class="text-muted">••••••••</td>
                        <td>${u.email}</td>
                        <td class="fw-bold">${u.rol.nombreRol}</td>
                        <td>
                            <c:choose>
                                <c:when test="${u.estado == 1}">
                                    <span class="badge badge-activo px-2 py-1">Activo</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-inactivo px-2 py-1">Inactivo</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${u.fecha_creacion}</td>

                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-1">
                                <a href="UsuarioServlet?action=cargarFormularioActualizar&id=${u.idUsuario}" class="btn btn-outline-primary btn-sm" title="Editar">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <c:if test="${u.estado == 1}">
                                    <button type="button" class="btn btn-outline-danger btn-sm"
                                            onclick="abrirModalEstado(${u.idUsuario}, '${u.nombres}', 1)" title="Desactivar">
                                        <i class="bi bi-person-x"></i>
                                    </button>
                                </c:if>

                                <c:if test="${u.estado == 0}">
                                    <button type="button" class="btn btn-outline-success btn-sm"
                                            onclick="abrirModalEstado(${u.idUsuario}, '${u.nombres}', 0)" title="Activar">
                                        <i class="bi bi-person-check"></i>
                                    </button>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty listaUsuario}">
                    <tr>
                        <td colspan="12" class="text-center py-5">
                            <i class="bi bi-inbox text-secondary" style="font-size: 2.5rem;"></i>
                            <h6 class="text-muted mt-3">No hay datos disponibles en la tabla.</h6>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<div class="modal fade" id="modalCambiarEstado" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header border-0 pb-0">
                <h5 class="modal-title fw-bold text-dark">Confirmar Acción</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center pt-4 pb-4">
                <p id="textoModalEstado" class="fs-5 mb-0"></p>
            </div>

            <div class="modal-footer border-0 d-flex justify-content-center gap-2 pb-4">
                <button type="button" class="btn btn-secondary px-4" data-bs-dismiss="modal">Cancelar</button>
                <a href="#" id="btnConfirmarEstado" class="btn px-4">Sí, confirmar</a>
            </div>

        </div>
    </div>
</div>
<script>
    function abrirModalEstado(idUsuario, nombres, estado) {
        const textoMensaje = document.getElementById('textoModalEstado');
        const btnConfirmar = document.getElementById('btnConfirmarEstado');

        // 1. Determinamos el nuevo estado (si es 1 pasa a 0, si es 0 pasa a 1)
        let nuevoEstadoEnviar = (estado == 1) ? 0 : 1;

        // 2. Personalizamos el texto y color del botón del modal (usando concatenación estándar)
        if (estado == 1) {
            textoMensaje.innerHTML = '¿Estás seguro de que deseas <strong>desactivar</strong> a ' + nombres + '?';
            btnConfirmar.className = "btn btn-danger px-4";
        } else {
            textoMensaje.innerHTML = '¿Estás seguro de que deseas <strong>activar</strong> a ' + nombres + '?';
            btnConfirmar.className = "btn btn-success px-4";
        }

        // 3. ENVIAMOS AMBOS PARAMETROS AL SERVLET (usando concatenación estándar)
        btnConfirmar.href = 'UsuarioServlet?action=cambiarEstadoUsuario&id=' + idUsuario + '&estado=' + nuevoEstadoEnviar;

        // 4. Mostramos el modal
        const modalElement = document.getElementById('modalCambiarEstado');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
    }
</script>
</body>
</html>