<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Inventario de Productos | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        /* Tema ERP / Administrativo Claro */
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

        .form-control, .form-select {
            background-color: #ffffff;
            border: 1px solid #ced4da;
            color: #495057;
            border-radius: 4px;
        }

        /* Botones */
        .btn { border-radius: 4px; font-weight: 500; }
        .btn-primary { background-color: #0d6efd; border-color: #0d6efd; }
        .btn-success { background-color: #198754; border-color: #198754; }

        /* Tabla de Productos */
        .table-light-header th {
            background-color: #f8f9fa;
            color: #495057;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.82rem;
            border-bottom: 2px solid #dee2e6;
            padding: 12px 10px;
        }
        .table tbody tr:hover { background-color: #f1f4f8; }
        .table td {
            vertical-align: middle;
            padding: 12px 10px;
            color: #50555a;
            border-bottom: 1px solid #e9ecef;
            font-size: 0.88rem;
        }

        /* Alertas de inventario */
        .stock-critico {
            color: #dc3545 !important;
            font-weight: bold;
            background-color: #f8d7da;
            padding: 4px 8px;
            border-radius: 4px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container-fluid py-4 px-4 px-xl-5">

    <div class="titulo-seccion d-flex align-items-center justify-content-between">
        <h4 class="mb-0 fw-bold">
            <i class="bi bi-box-seam-fill me-2 text-primary"></i>
            Control de Inventario (Productos)
        </h4>
        <a href="ProductoServlet?action=registrarProducto" class="btn btn-success shadow-sm">
            <i class="bi bi-plus-circle me-1"></i> Nuevo Producto
        </a>
    </div>

    <div class="card-panel">
        <div class="row g-3 align-items-end">
            <div class="col-md-4">
                <a href="ProductoServlet?action=listar" class="btn btn-primary">
                    <i class="bi bi-arrow-clockwise me-1"></i> Actualizar Tabla
                </a>
            </div>
            <div class="col-md-8">
                <form action="ProductoServlet" method="GET" class="m-0 d-flex justify-content-md-end gap-2">
                    <input type="hidden" name="action" value="buscarSku">
                    <div style="max-width: 300px;" class="input-group">
                        <input type="text" class="form-control" name="txtSku" placeholder="Buscar por SKU..." maxlength="20">
                        <button type="submit" class="btn btn-primary"><i class="bi bi-search"></i></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="card-panel p-0 overflow-hidden">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-light-header">
                <tr>
                    <th style="width: 50px;">ID</th>
                    <th>SKU</th>
                    <th>Producto</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Categoría</th>
                    <th>P. Compra</th>
                    <th>P. Venta</th>
                    <th class="text-center">Stock Act.</th>
                    <th class="text-center">Stock Mín.</th>
                    <th>Fecha Creación</th>
                    <th class="text-center" style="width: 100px;">Acciones</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${listaProducto}" var="p">
                    <tr>
                        <td class="fw-bold text-dark">${p.idProducto}</td>
                        <td><span class="badge bg-secondary font-monospace">${p.codigoSku}</span></td>
                        <td class="fw-bold text-dark">${p.nombre}</td>
                        <td>${p.marca.nombre}</td>
                        <td>${p.modelo}</td>
                        <td>${p.categoria.nombre}</td>

                        <td class="fw-bold text-secondary">S/. ${p.precioCompra}</td>
                        <td class="fw-bold text-primary">S/. ${p.precioVenta}</td>

                        <td class="text-center">
                            <c:choose>
                                <c:when test="${p.stockActual <= p.stockMinimo}">
                                    <span class="stock-critico" title="¡Por debajo del stock mínimo!">
                                        <i class="bi bi-exclamation-triangle-fill me-1"></i>${p.stockActual}
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="fw-bold text-success">${p.stockActual}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td class="text-center text-muted">${p.stockMinimo}</td>
                        <td class="small text-secondary">${p.fechaCreacion}</td>

                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-1">
                                <a href="ProductoServlet?action=cargarFormularioActualizar&id=${p.idProducto}"
                                   class="btn btn-outline-primary btn-sm" title="Editar Producto">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <c:if test="${p.estado == 1}">
                                    <button type="button" class="btn btn-outline-danger btn-sm"
                                            onclick="abrirModalEstado(${p.idProducto}, '${p.nombre}', 1)" title="Desactivar">
                                        <i class="bi bi-person-x"></i>
                                    </button>
                                </c:if>

                                <c:if test="${p.estado == 0}">
                                    <button type="button" class="btn btn-outline-success btn-sm"
                                            onclick="abrirModalEstado(${p.idProducto}, '${p.nombre}', 0)" title="Activar">
                                        <i class="bi bi-person-check"></i>
                                    </button>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty listaProducto}">
                    <tr>
                        <td colspan="12" class="text-center py-5">
                            <i class="bi bi-box text-secondary" style="font-size: 2.5rem;"></i>
                            <h6 class="text-muted mt-3">No hay productos registrados o activos en el sistema.</h6>
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<div class="modal fade" id="modalCambiarEstado" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form action="ProductoServlet" method="post">
                <div class="modal-header border-0 pb-0">
                    <h5 class="modal-title fw-bold text-dark">Confirmar Acción</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body text-center pt-4 pb-4">
                    <p id="textoModalEstado" class="fs-5 mb-0"></p>

                    <input type="hidden" name="action" value="cambiarEstadoProducto">
                    <input type="hidden" name="id" id="modalInputId">
                    <input type="hidden" name="estado" id="modalInputEstado">
                </div>

                <div class="modal-footer border-0 d-flex justify-content-center gap-2 pb-4">
                    <button type="button" class="btn btn-secondary px-4" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" id="btnConfirmarEstado" class="btn px-4">Sí, confirmar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function abrirModalEstado(idProducto, nombres, estado) {
        const textoMensaje = document.getElementById('textoModalEstado');
        const btnConfirmar = document.getElementById('btnConfirmarEstado');

        // Capturamos los inputs ocultos
        const inputId = document.getElementById('modalInputId');
        const inputEstado = document.getElementById('modalInputEstado');

        let nuevoEstadoEnviar = (estado == 1) ? 0 : 1;

        if (estado == 1) {
            textoMensaje.innerHTML = '¿Estás seguro de que deseas <strong>desactivar</strong> producto ' + nombres + '?';
            btnConfirmar.className = "btn btn-danger px-4";
        } else {
            textoMensaje.innerHTML = '¿Estás seguro de que deseas <strong>activar</strong> producto ' + nombres + '?';
            btnConfirmar.className = "btn btn-success px-4";
        }

        // LLENAMOS LOS DATOS OCULTOS PARA EL POST
        inputId.value = idProducto;
        inputEstado.value = nuevoEstadoEnviar;

        const modalElement = document.getElementById('modalCambiarEstado');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
    }
</script>
</body>
</html>