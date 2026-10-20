<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ingresos de Stock | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #f4f6f9; color: #333333; font-family: 'Segoe UI', sans-serif; }

        .titulo-seccion {
            background-color: #ffffff; color: #2c323f; padding: 20px 25px;
            border-radius: 4px; margin-bottom: 20px;
            border-top: 4px solid #198754; box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .card-tabla {
            background: #ffffff; border-radius: 4px;
            border: 1px solid #e0e4e8; box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }

        .card-tabla-header {
            padding: 16px 20px; border-bottom: 1px solid #e0e4e8;
            display: flex; align-items: center; justify-content: space-between;
            flex-wrap: wrap; gap: 12px;
        }

        .tabla-principal thead th {
            background-color: #f8f9fa; font-size: 0.78rem;
            text-transform: uppercase; color: #6c757d;
            font-weight: 600; border-bottom: 2px solid #dee2e6;
            padding: 12px 16px;
        }

        .tabla-principal tbody td { padding: 12px 16px; vertical-align: middle; }
        .tabla-principal tbody tr:hover { background-color: #f8f9fa; }

        .badge-activo { background-color: #d1e7dd; color: #0a3622; font-size: 0.75rem; padding: 4px 10px; border-radius: 20px; font-weight: 600; }
        .badge-anulado { background-color: #f8d7da; color: #58151c; font-size: 0.75rem; padding: 4px 10px; border-radius: 20px; font-weight: 600; }

        .btn { border-radius: 4px; font-weight: 500; }
        .btn-success { background-color: #198754; border-color: #198754; }

        .input-buscar { border: 1px solid #ced4da; border-radius: 4px; padding: 8px 14px; font-size: 0.9rem; }
        .input-buscar:focus { border-color: #86b7fe; box-shadow: 0 0 0 0.25rem rgba(13,110,253,0.25); outline: none; }

        .lote-badge {
            font-family: monospace; font-size: 0.85rem;
            background-color: #e8f0fe; color: #1a56db;
            padding: 3px 8px; border-radius: 4px; font-weight: 600;
        }

        .empty-state { text-align: center; padding: 60px 20px; color: #6c757d; }
        .empty-state i { font-size: 3rem; margin-bottom: 16px; display: block; color: #adb5bd; }
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

            <div class="titulo-seccion d-flex align-items-center mb-4">
                <h4 class="mb-0 fw-bold">
                    <i class="bi bi-box-arrow-in-down text-success me-2"></i>
                    Gestión de Ingresos de Stock
                </h4>
            </div>

            <div class="card-tabla">
                <div class="card-tabla-header">
                    <%-- Buscador --%>
                    <form action="IngresoStockServlet" method="get" class="d-flex gap-2">
                        <input type="hidden" name="action" value="buscarIngreso">
                        <input type="text" class="input-buscar" name="txtIngresoGeneral"
                               placeholder="Buscar por N° de lote..."
                               value="${param.txtIngresoGeneral}" style="min-width: 260px;">
                        <button type="submit" class="btn btn-outline-primary btn-sm px-3">
                            <i class="bi bi-search me-1"></i> Buscar
                        </button>
                        <a href="IngresoStockServlet?action=listar" class="btn btn-outline-secondary btn-sm px-3">
                            <i class="bi bi-arrow-counterclockwise me-1"></i> Limpiar
                        </a>
                    </form>

                    <%-- Botón nuevo ingreso --%>
                    <a href="IngresoStockServlet?action=cargarFormularioRegistrar" class="btn btn-success btn-sm px-3">
                        <i class="bi bi-plus-circle me-1"></i> Nuevo Ingreso
                    </a>
                </div>

                <div class="table-responsive">
                    <table class="table tabla-principal mb-0">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>N° Lote</th>
                            <th>Registrado por</th>
                            <th>Fecha de Ingreso</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty lista}">
                                <tr>
                                    <td colspan="6">
                                        <div class="empty-state">
                                            <i class="bi bi-inbox"></i>
                                            <p class="mb-1 fw-semibold">No hay ingresos registrados</p>
                                            <p class="mb-0 small">Haz clic en "Nuevo Ingreso" para registrar el primero.</p>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${lista}" var="ing" varStatus="loop">
                                    <tr>
                                        <td class="text-muted small">${loop.count}</td>
                                        <td><span class="lote-badge">${ing.loteStock}</span></td>
                                        <td>
                                            <i class="bi bi-person-circle me-1 text-muted"></i>
                                                ${ing.usuario.nombres}
                                        </td>
                                        <td>
                                            <i class="bi bi-calendar3 me-1 text-muted"></i>
                                                ${ing.fechaIngreso}
                                        </td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${ing.estado == 1}">
                                                    <span class="badge-activo"><i class="bi bi-check-circle me-1"></i>Activo</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge-anulado"><i class="bi bi-x-circle me-1"></i>Inactivo</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <div class="d-flex justify-content-center gap-1">
                                                    <%-- Ver detalle --%>
                                                <a href="IngresoStockServlet?action=verDetalle&id=${ing.idIngresoStock}"
                                                   class="btn btn-outline-info btn-sm" title="Ver detalle">
                                                    <i class="bi bi-eye"></i>
                                                </a>
                                                    <%-- Anular solo si está activo --%>
                                                        <c:if test="${ing.estado == 1}">
                                                            <button type="button" class="btn btn-outline-danger btn-sm"
                                                                    onclick="abrirModalEstado(${ing.idIngresoStock}, 1)" title="Desactivar">
                                                                <i class="bi bi-person-x"></i>
                                                            </button>
                                                        </c:if>

                                                        <c:if test="${ing.estado == 0}">
                                                            <button type="button" class="btn btn-outline-success btn-sm"
                                                                    onclick="abrirModalEstado(${ing.idIngresoStock}, 0)" title="Activar">
                                                                <i class="bi bi-person-check"></i>
                                                            </button>
                                                        </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="modal fade" id="modalCambiarEstado" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form action="IngresoStockServlet" method="post">
                <div class="modal-header border-0 pb-0">
                    <h5 class="modal-title fw-bold text-dark">Confirmar Acción</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body text-center pt-4 pb-4">
                    <p id="textoModalEstado" class="fs-5 mb-0"></p>

                    <input type="hidden" name="action" value="cambiarEstadoIngreso">
                    <input type="hidden" name="txtIdIngresoStock" id="modalInputId">
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
    function abrirModalEstado(idIngresoStock, estado) {
        const textoMensaje = document.getElementById('textoModalEstado');
        const btnConfirmar = document.getElementById('btnConfirmarEstado');

        const inputId = document.getElementById('modalInputId');
        const inputEstado = document.getElementById('modalInputEstado');

        let nuevoEstadoEnviar = (estado == 1) ? 0 : 1;

        if (estado == 1) {
            textoMensaje.innerHTML = '¿Estás seguro de que deseas <strong>inabilitar</strong> el ingreso de stock';
            btnConfirmar.className = "btn btn-danger px-4";
        } else {
            textoMensaje.innerHTML = '<strong> Este Ingreso ya fue anulado, contacte con el administrador</strong>';
            btnConfirmar.className = "btn btn-danger px-4";
        }

        inputId.value = idIngresoStock;
        inputEstado.value = nuevoEstadoEnviar;

        const modalElement = document.getElementById('modalCambiarEstado');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
    }
</script>
</body>
</html>
