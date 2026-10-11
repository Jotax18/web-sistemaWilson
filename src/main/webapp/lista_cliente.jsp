<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        /* Tema ERP / Administrativo Claro */
        body {
            background-color: #f4f6f9;
            color: #333333;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
            margin: 0;
        }

        /* Contenedores y Tarjetas */
        .titulo-seccion {
            background-color: #ffffff;
            color: #2c323f;
            padding: 20px 25px;
            border-radius: 4px;
            margin-bottom: 20px;
            border-top: 4px solid #0d6efd;
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
        .btn-success { background-color: #198754; border-color: #198754; }
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

        /* Badges de Tipo de Documento */
        .badge-dni { background-color: #cfe2ff; color: #084298; font-weight: 600; }
        .badge-ruc { background-color: #d1e7dd; color: #0f5132; font-weight: 600; }
        .badge-otro { background-color: #e2e3e5; color: #41464b; font-weight: 600; }
    </style>
</head>
<body>

<div class="d-flex" style="min-height: 100vh; background-color: #f4f6f9;">

    <jsp:include page="sidebar.jsp" />

    <div class="flex-grow-1 overflow-auto">

        <div class="container-fluid py-4 px-4 px-xl-5">

            <div class="titulo-seccion d-flex align-items-center justify-content-between">
                <h4 class="mb-0 fw-bold">
                    <i class="bi bi-person-vcard-fill me-2 text-primary"></i> Administración de Clientes
                </h4>
                <a href="ClienteServlet?action=cargarFormularioRegistrar" class="btn btn-success shadow-sm">
                    <i class="bi bi-person-plus-fill me-1"></i> Nuevo Cliente
                </a>
            </div>

            <div class="card-panel">
                <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-end gap-3">

                    <div>
                        <a href="ClienteServlet?action=listar" class="btn btn-primary px-4">
                            <i class="bi bi-list-ul me-2"></i>Ver Todo el Listado
                        </a>
                    </div>

                    <form action="ClienteServlet" method="get" class="m-0">
                        <input type="hidden" name="action" value="buscarCliente">

                        <div class="d-flex flex-column flex-sm-row align-items-sm-end gap-2">
                            <div>
                                <label class="text-secondary mb-1 small fw-bold">Buscar por Nombre / Razón Social o N° Documento</label>
                                <div class="input-group" style="min-width: 320px;">
                                    <span class="input-group-text"><i class="bi bi-search"></i></span>
                                    <input class="form-control" name="txtBuscarCliente" type="text"
                                           value="${param.txtBuscarCliente}"
                                           placeholder="Ingrese nombre o N° documento" required>
                                    <button type="submit" class="btn btn-primary">Buscar</button>
                                </div>
                            </div>

                            <div>
                                <a href="ClienteServlet?action=listar" class="btn btn-outline-secondary" title="Limpiar filtro">
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
                            <th style="width: 70px;">ID</th>
                            <th>Nombre / Razón Social</th>
                            <th class="text-center" style="width: 130px;">Tipo Doc.</th>
                            <th style="width: 150px;">N° Documento</th>
                            <th style="width: 160px;">Fecha Nacimiento</th>
                            <th>Email</th>
                            <th style="width: 130px;">Celular</th>
                            <th class="text-center" style="width: 110px;">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lista}" var="c">
                            <tr>
                                <td class="fw-bold text-dark">${c.idCliente}</td>
                                <td class="fw-bold text-dark">${c.nombreRazonSocial}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${c.tipoDocumento == 'DNI'}">
                                            <span class="badge badge-dni px-2 py-1">DNI</span>
                                        </c:when>
                                        <c:when test="${c.tipoDocumento == 'RUC'}">
                                            <span class="badge badge-ruc px-2 py-1">RUC</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-otro px-2 py-1">${c.tipoDocumento}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><span class="badge bg-secondary font-monospace">${c.numeroDocumento}</span></td>
                                <td>
                                    <i class="bi bi-calendar3 me-1 text-muted"></i>
                                    ${not empty c.fechaNacimiento ? c.fechaNacimiento : '-'}
                                </td>
                                <td>
                                    <i class="bi bi-envelope me-1 text-muted"></i>${c.email}
                                </td>
                                <td>
                                    <i class="bi bi-telephone me-1 text-muted"></i>${c.celular}
                                </td>
                                <td class="text-center">
                                    <div class="d-flex justify-content-center gap-1">
                                        <a href="ClienteServlet?action=cargarFormularioActualizar&idCliente=${c.idCliente}"
                                           class="btn btn-outline-primary btn-sm" title="Editar Cliente">
                                            <i class="bi bi-pencil-square"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty lista}">
                            <tr>
                                <td colspan="8" class="text-center py-5">
                                    <i class="bi bi-inbox text-secondary" style="font-size: 2.5rem;"></i>
                                    <h6 class="text-muted mt-3">No hay clientes disponibles en la tabla.</h6>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
