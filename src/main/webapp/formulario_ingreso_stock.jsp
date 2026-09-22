<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Ingreso de Stock | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
    <style>
        body { background-color: #f4f6f9; color: #333333; font-family: 'Segoe UI', sans-serif; }

        .titulo-seccion {
            background-color: #ffffff; color: #2c323f; padding: 20px 25px;
            border-radius: 4px; margin-bottom: 20px;
            border-top: 4px solid #198754; box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .card-formulario {
            background: #ffffff; padding: 35px 40px; border-radius: 4px;
            border: 1px solid #e0e4e8; box-shadow: 0 1px 3px rgba(0,0,0,0.05);
            max-width: 1100px; margin: 0 auto 30px auto;
        }

        .form-label { font-weight: 600; color: #495057; font-size: 0.85rem; text-transform: uppercase; margin-bottom: 8px; }
        .form-control, .form-select { border: 1px solid #ced4da; padding: 10px 15px; border-radius: 4px; }
        .form-control:focus, .form-select:focus { border-color: #86b7fe; box-shadow: 0 0 0 0.25rem rgba(13,110,253,0.25); }

        .btn { border-radius: 4px; font-weight: 500; padding: 10px 20px; }
        .btn-success { background-color: #198754; border-color: #198754; }
        .btn-outline-secondary { color: #6c757d; border-color: #6c757d; }

        .seccion-titulo { font-size: 1.1rem; color: #0d6efd; border-bottom: 2px solid #e9ecef; padding-bottom: 8px; margin-bottom: 20px; margin-top: 10px; }

        /* Tabla de detalle */
        .tabla-detalle thead th { background-color: #f8f9fa; font-size: 0.78rem; text-transform: uppercase; color: #6c757d; font-weight: 600; border-bottom: 2px solid #dee2e6; }
        .tabla-detalle tbody tr { vertical-align: middle; }
        .tabla-detalle .form-control, .tabla-detalle .form-select { padding: 7px 10px; font-size: 0.9rem; }

        .btn-agregar-fila { border: 2px dashed #198754; color: #198754; background: transparent; border-radius: 4px; padding: 8px 20px; font-weight: 600; transition: all 0.2s; }
        .btn-agregar-fila:hover { background-color: #198754; color: #fff; }

        .btn-quitar-fila { background: none; border: none; color: #dc3545; font-size: 1.1rem; padding: 4px 8px; }
        .btn-quitar-fila:hover { color: #a71d2a; }

        .precio-ref { font-size: 0.78rem; color: #6c757d; margin-top: 4px; }
        .precio-ref span { font-weight: 700; color: #198754; }

        .subtotal-celda { font-weight: 700; color: #0d6efd; font-size: 0.92rem; }

        .total-footer { background-color: #f0f4ff; border-top: 2px solid #0d6efd; }
        .total-footer td { font-weight: 700; color: #0d6efd; font-size: 1rem; padding: 12px 16px; }
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
                    Registrar Ingreso de Stock
                </h4>
            </div>

            <div class="card-formulario">
                <form action="IngresoStockServlet" method="post" id="formIngreso">

                    <input type="hidden" name="action" value="registrarIngreso">

                    <%-- CABECERA --%>
                    <div class="seccion-titulo">
                        <i class="bi bi-file-earmark-text me-2"></i>Datos del Ingreso
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-4">
                            <label class="form-label">N° de Lote <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtLoteStock" required
                                   placeholder="Ej. LOTE-2026-001" autocomplete="off">
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Fecha de Ingreso</label>
                            <input type="text" class="form-control"
                                   value='<%= java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) %>'
                                   readonly style="background:#f8f9fa;">
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Registrado por</label>
                            <input type="text" class="form-control"
                                   value="${sessionScope.usuarioSesion.nombres} ${sessionScope.usuarioSesion.apellidos}"
                                   readonly style="background:#f8f9fa;">
                        </div>
                    </div>

                    <%-- DETALLE --%>
                    <div class="seccion-titulo mt-4">
                        <i class="bi bi-list-ul me-2"></i>Detalle de Productos
                    </div>

                    <div class="table-responsive mb-3">
                        <table class="table tabla-detalle" id="tablaDetalle">
                            <thead>
                            <tr>
                                <th style="width: 40%">Producto</th>
                                <th style="width: 15%">Cantidad</th>
                                <th style="width: 20%">Costo Unitario (S/.)</th>
                                <th style="width: 15%" class="text-end">Subtotal (S/.)</th>
                                <th style="width: 10%"></th>
                            </tr>
                            </thead>
                            <tbody id="tbodyDetalle">
                            <%-- Las filas se agregan dinámicamente con JS --%>
                            </tbody>
                            <tfoot>usuarioSesion
                            <tr class="total-footer">
                                <td colspan="3" class="text-end">TOTAL INGRESO:</td>
                                <td class="text-end" id="totalGeneral">S/. 0.00</td>
                                <td></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                    <div class="mb-4">
                        <button type="button" class="btn-agregar-fila" id="btnAgregarFila">
                            <i class="bi bi-plus-circle me-1"></i> Agregar Producto
                        </button>
                    </div>

                    <div id="alertaVacia" class="alert alert-warning d-none" role="alert">
                        <i class="bi bi-exclamation-triangle me-2"></i>
                        Debe agregar al menos un producto al detalle.
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
                        <a href="IngresoStockServlet?action=listar" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Cancelar
                        </a>
                        <button type="submit" class="btn btn-success">
                            <i class="bi bi-floppy me-1"></i> Registrar Ingreso
                        </button>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>

<%--
    Las opciones se renderizan como HTML y se reutilizan al crear cada fila.
    Así, nombres o SKU con comillas no rompen el JavaScript.
--%>
<template id="opcionesProducto">
    <option value=""></option>
    <c:forEach items="${listaProductos}" var="p">
        <option value="<c:out value="${p.idProducto}"/>"
                data-precio-compra="<c:out value="${p.precioCompra}"/>">[<c:out value="${p.codigoSku}"/>] <c:out value="${p.nombre}"/></option>
    </c:forEach>
</template>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
    $(document).ready(function () {

        var filaIndex = 0;

        function inicializarSelect2(selector) {
            $(selector).select2({
                theme: 'bootstrap-5',
                placeholder: 'Buscar producto...',
                allowClear: true,
                width: '100%'
            }).on('change', function () {
                var fila = $(this).closest('tr');
                var precioCompra = parseFloat($(this).find('option:selected').attr('data-precio-compra'));
                if (!isNaN(precioCompra)) {
                    fila.find('.precio-ref-valor').text('S/. ' + precioCompra.toFixed(2));
                    var costoInput = fila.find('.costo-unitario');
                    if (!costoInput.val()) {
                        costoInput.val(precioCompra.toFixed(2));
                    }
                } else {
                    fila.find('.precio-ref-valor').text('-');
                }
                calcularSubtotal(fila);
            });
        }

        function calcularSubtotal(fila) {
            var cantidad = parseFloat(fila.find('.cantidad').val()) || 0;
            var costo = parseFloat(fila.find('.costo-unitario').val()) || 0;
            var subtotal = cantidad * costo;
            fila.find('.subtotal-valor').text('S/. ' + subtotal.toFixed(2));
            fila.find('.subtotal-hidden').val(subtotal.toFixed(2));
            calcularTotal();
        }

        function calcularTotal() {
            var total = 0;
            $('#tbodyDetalle .subtotal-hidden').each(function () {
                total += parseFloat($(this).val()) || 0;
            });
            $('#totalGeneral').text('S/. ' + total.toFixed(2));
        }

        function agregarFila() {
                    var idx = filaIndex++;
                    var opcionesProducto = $('#opcionesProducto').html();
                    var fila = '<tr id="fila-' + idx + '">' +
                    '<td>' +
                    '<select class="form-select select-producto" name="idProducto" required>' +
                    opcionesProducto +
                    '</select>' +
                            '<div class="precio-ref mt-1">Precio ref: <span class="precio-ref-valor">-</span></div>' +
                            '</td>' +
                            '<td>' +
                            '<input type="number" class="form-control cantidad" name="cantidad" min="1" placeholder="0" required>' +
                    '</td>' +
                            '<td>' +
                            '<input type="number" step="0.01" class="form-control costo-unitario" name="costoUnitario" min="0.01" placeholder="0.00" required>' +
                            '</td>' +
                            '<td class="text-end subtotal-celda">' +
                            '<span class="subtotal-valor">S/. 0.00</span>' +
                                    '<input type="hidden" class="subtotal-hidden" name="subtotal" value="0">' +
                                    '</td>' +
                                    '<td class="text-center">' +
                                    '<button type="button" class="btn-quitar-fila" onclick="quitarFila(\'fila-' + idx + '\')">' +
                            '<i class="bi bi-trash3-fill"></i>' +
                            '</button>' +
                            '</td>' +
                            '</tr>';

                                    $('#tbodyDetalle').append(fila);

                            setTimeout(function() {
                            inicializarSelect2('#fila-' + idx + ' .select-producto');
                            }, 50);

                            $('#fila-' + idx + ' .cantidad, #fila-' + idx + ' .costo-unitario').on('input', function () {
                            calcularSubtotal($(this).closest('tr'));
                            });
                            }
        window.agregarFila = agregarFila;

        window.quitarFila = function(idFila) {
            if ($('#tbodyDetalle tr').length <= 1) {
                alert('Debe haber al menos un producto en el detalle.');
                return;
            }
            $('#' + idFila).remove();
            calcularTotal();
        };

        agregarFila();

        $('#btnAgregarFila').on('click', function () {
            agregarFila();
        });

        $('#formIngreso').on('submit', function (e) {
            var filas = $('#tbodyDetalle tr').length;
            if (filas === 0) {
                e.preventDefault();
                $('#alertaVacia').removeClass('d-none');
                return false;
            }
            $('#alertaVacia').addClass('d-none');
        });
    });
</script>
</body>
</html>
