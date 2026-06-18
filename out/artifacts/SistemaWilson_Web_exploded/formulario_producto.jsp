<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${producto != null ? 'Actualizar' : 'Registrar'} Producto | SistemaWilson</title>
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
        .titulo-edicion { border-top-color: #0d6efd; }

        .card-formulario {
            background: #ffffff; padding: 35px 40px; border-radius: 4px;
            border: 1px solid #e0e4e8; box-shadow: 0 1px 3px rgba(0,0,0,0.05);
            max-width: 900px; margin: 0 auto 30px auto;
        }
        .form-label { font-weight: 600; color: #495057; font-size: 0.85rem; text-transform: uppercase; margin-bottom: 8px; }
        .form-control, .form-select { border: 1px solid #ced4da; padding: 10px 15px; border-radius: 4px; }
        .form-control:focus, .form-select:focus { border-color: #86b7fe; box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); }

        .btn { border-radius: 4px; font-weight: 500; padding: 10px 20px; }
        .btn-success { background-color: #198754; border-color: #198754; }
        .btn-primary { background-color: #0d6efd; border-color: #0d6efd; }
        .btn-outline-secondary { color: #6c757d; border-color: #6c757d; }

        .seccion-titulo { font-size: 1.1rem; color: #0d6efd; border-bottom: 2px solid #e9ecef; padding-bottom: 8px; margin-bottom: 20px; margin-top: 10px; }
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

            <div class="titulo-seccion d-flex align-items-center mb-4 ${producto != null ? 'titulo-edicion' : ''}">
                <h4 class="mb-0 fw-bold">
                    <i class="bi ${producto != null ? 'bi-pencil-square text-primary' : 'bi-plus-circle text-success'} me-2"></i>
                    ${producto != null ? 'Modificar Producto de Catálogo' : 'Registrar Nuevo Producto'}
                </h4>
            </div>

            <div class="card-formulario">
                <form action="ProductoServlet" method="post">

                    <input type="hidden" name="action" value="${producto != null ? 'actualizarProducto' : 'registrarProducto'}">
                    <input type="hidden" name="txtId" value="${producto.idProducto}">

                    <div class="seccion-titulo">
                        <i class="bi bi-tag-fill me-2"></i>Detalles del Producto
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-4">
                            <label class="form-label">Código SKU / Barras <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtSku" value="${producto.codigoSku}" required placeholder="Ej. CPU-INT-I9">
                        </div>
                        <div class="col-md-8">
                            <label class="form-label">Nombre del Producto <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtNombreProd" value="${producto.nombre}" required placeholder="Ej. Procesador Intel Core i9 14900K">
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">Marca <span class="text-danger">*</span></label>
                            <select class="form-select" name="cboMarca" id="cboMarca" required>
                                <option value=""></option>
                                <c:forEach items="${listaMarcas}" var="m">
                                    <option value="${m.idMarca}" ${producto != null && producto.marca.idMarca == m.idMarca ? 'selected' : ''}>
                                            ${m.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">Modelo</label>
                            <input type="text" class="form-control" name="txtModelo" value="${producto.modelo}" placeholder="Ej. Raptor Lake-S">
                        </div>

                        <div class="col-md-4">
                            <label class="form-label">Categoria <span class="text-danger">*</span></label>
                            <select class="form-select" name="cboCategoria" id="cboCategoria" required>
                                <option value=""></option>
                                <c:forEach items="${listaCategorias}" var="c">
                                    <option value="${c.idCategoria}" ${producto != null && producto.categoria.idCategoria == c.idCategoria ? 'selected' : ''}>
                                            ${c.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-12">
                            <label class="form-label">Descripción Breve</label>
                            <textarea class="form-control" name="txtDescripcion" rows="2" placeholder="Especificaciones técnicas básicas...">${producto.descripcion}</textarea>
                        </div>
                    </div>

                    <div class="seccion-titulo mt-5">
                        <i class="bi bi-cash-coin me-2"></i>Precios e Inventario
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-3">
                            <label class="form-label">Precio de Compra (S/.) <span class="text-danger">*</span></label>
                            <input type="number" step="0.01" class="form-control" name="txtPrecioCompra" value="${producto.precioCompra}" required placeholder="0.00">
                        </div>
                        <div class="col-md-3">
                            <label class="form-label">Precio de Venta (S/.) <span class="text-danger">*</span></label>
                            <input type="number" step="0.01" class="form-control" name="txtPrecioVenta" value="${producto.precioVenta}" required placeholder="0.00">
                        </div>
                        <div class="col-md-3">
                            <label class="form-label">Stock Actual <span class="text-danger">*</span></label>
                            <input type="number" class="form-control" name="txtStockActual" value="${producto.stockActual}" required placeholder="0">
                        </div>
                        <div class="col-md-3">
                            <label class="form-label">Stock Mínimo Alerta <span class="text-danger">*</span></label>
                            <input type="number" class="form-control" name="txtStockMinimo" value="${producto.stockMinimo}" required placeholder="5">
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
                        <a href="ProductoServlet?action=listar" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Cancelar
                        </a>
                        <button type="submit" class="btn ${producto != null ? 'btn-primary' : 'btn-success'}">
                            <i class="bi bi-floppy me-1"></i> ${producto != null ? 'Guardar Cambios' : 'Registrar Producto'}
                        </button>
                    </div>

                </form>
            </div>

        </div> </div> </div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
    $(document).ready(function() {
        $('#cboMarca').select2({
            theme: 'bootstrap-5',
            placeholder: 'Buscar marca...',
            allowClear: true,
            width: '100%'
        });
    });
</script>
<script>
    $(document).ready(function() {
        $('#cboCategoria').select2({
            theme: 'bootstrap-5',
            placeholder: 'Buscar categoria...',
            allowClear: true,
            width: '100%'
        });
    });
</script>
</body>
</html>