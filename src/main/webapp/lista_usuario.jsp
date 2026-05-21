<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Gestión de Usuarios | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        /* Tema Premium Dark - PC Hardware Store */
        body {
            background-color: #0b0f19;
            color: #e2e8f0;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
        }

        /* Contenedores y Tarjetas */
        .titulo-seccion {
            background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
            color: #38bdf8;
            padding: 25px;
            border-radius: 12px;
            margin-bottom: 30px;
            border-left: 4px solid #38bdf8;
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
            text-transform: uppercase;
            letter-spacing: 1px;
            font-weight: bold;
        }

        .card-filtro {
            background: #1e293b;
            padding: 25px;
            border-radius: 12px;
            border: 1px solid #334155;
            box-shadow: 0 4px 6px rgba(0,0,0,0.3);
        }

        /* Formulario y Controles */
        .form-control {
            background-color: #0f172a;
            border: 1px solid #334155;
            color: #e2e8f0;
        }
        .form-control:focus {
            background-color: #0f172a;
            border-color: #38bdf8;
            color: white;
            box-shadow: 0 0 0 0.25rem rgba(56, 189, 248, 0.25);
        }

        /* Botones personalizados */
        .btn-primary {
            background-color: #0284c7;
            border-color: #0284c7;
        }
        .btn-primary:hover {
            background-color: #0369a1;
            border-color: #0369a1;
        }
        .btn-outline-secondary {
            color: #94a3b8;
            border-color: #475569;
        }
        .btn-outline-secondary:hover {
            background-color: #475569;
            color: white;
        }

        /* Tabla Premium */
        .table {
            color: #cbd5e1;
            border-collapse: separate;
            border-spacing: 0;
        }
        .table-dark {
            background-color: #8d9098;
            color: #f8fafc;
        }
        .table-dark th {
            border-bottom: 2px solid #38bdf8;
            text-transform: uppercase;
            font-size: 0.85rem;
            letter-spacing: 0.5px;
            padding: 15px;
        }
        .table tbody tr {
            background-color: #1e293b;
            transition: all 0.2s ease-in-out;
        }
        .table tbody tr:hover {
            background-color: #334155;
            transform: scale(1.005);
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        }
        .table td {
            border-bottom: 1px solid #334155;
            padding: 12px 15px;
            vertical-align: middle;
        }

        /* Badges de Estado */
        .badge-activo { background-color: rgba(16, 185, 129, 0.2); color: #34d399; border: 1px solid #10b981; }
        .badge-inactivo { background-color: rgba(239, 68, 68, 0.2); color: #f87171; border: 1px solid #ef4444; }
    </style>
</head>
<body>
<div class="container-fluid py-4 px-4 px-xl-5 mt-4">

    <div class="titulo-seccion d-flex align-items-center justify-content-center gap-3">
        <i class="bi bi-person-badge-fill fs-2"></i>
        <h2 class="mb-0">Listado Usuarios Wilson</h2>
    </div>

    <div class="card-filtro mb-4">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-end gap-3">

            <div>
                <a href="UsuarioServlet?action=listar" class="btn btn-primary px-4 shadow-sm">
                    <i class="bi bi-list-ul me-2"></i>Listar Todos
                </a>
            </div>

            <form action="UsuarioServlet" method="get" class="m-0">
                <input type="hidden" name="action" value="listarUsuarioDni">

                <div class="d-flex flex-column flex-sm-row align-items-sm-end gap-2">
                    <div>
                        <label class="text-secondary mb-1 small fw-bold text-uppercase" style="letter-spacing: 0.5px;">Filtrar por DNI</label>
                        <div class="input-group shadow-sm" style="max-width: 260px;">
                            <span class="input-group-text border-secondary text-info" style="background-color: #0f172a;">
                                <i class="bi bi-person-vcard"></i>
                            </span>
                            <input class="form-control" name="txtDni" type="text" maxlength="8" minlength="8" placeholder="8 dígitos" required autocomplete="off">
                            <button type="submit" class="btn btn-primary">
                                Buscar
                            </button>
                        </div>
                    </div>

                    <div>
                        <a href="UsuarioServlet?action=listar" class="btn btn-outline-secondary shadow-sm" title="Limpiar filtro">
                            <i class="bi bi-eraser"></i>
                        </a>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <div class="table-responsive shadow-lg rounded-3" style="overflow: hidden;">
        <table class="table table-sm table-hover align-middle mb-0">
            <thead class="table-dark">
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
                <th>Fecha Creacion</th>
                <th class="text-center text-nowrap">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaUsuario}" var="u">
                <tr>
                    <td>${u.idUsuario}</td>
                    <td>${u.nombres}</td>
                    <td>${u.apellidos}</td>
                    <td>${u.dni}</td>
                    <td>${u.celular}</td>
                    <td>${u.username}</td>
                    <td>${u.clave}</td>
                    <td>${u.email}</td>
                    <td>${u.rol.nombreRol}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.estado == 1}">
                                <span class="badge badge-activo px-2 py-1 rounded-pill">Activo</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-inactivo px-2 py-1 rounded-pill">Inactivo</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${u.fecha_creacion}</td>

                    <td class="text-center text-nowrap">
                        <div class="d-flex justify-content-center gap-2">
                            <a href="UsuarioServlet?action=actualizarUsuario=${u.idUsuario}" class="btn btn-outline-info btn-sm">
                                <i class="bi bi-pencil-square"></i> Editar
                            </a>

                            <c:if test="${u.estado == 1}">
                                <a href="UsuarioServlet?action=cambiarEstadoUsuario=${u.idUsuario}"
                                   class="btn btn-outline-danger btn-sm"
                                   onclick="return confirm('¿Estás seguro de que deseas desactivar a ${u.nombres}?');">
                                    <i class="bi bi-person-x"></i> Desactivar
                                </a>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty listaUsuario}">
                <tr>
                    <td colspan="12" class="text-center text-muted py-4">
                        <i class="bi bi-exclamation-circle fs-4 d-block mb-2"></i>
                        No se encontraron Usuarios.
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

</div>
<br/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>