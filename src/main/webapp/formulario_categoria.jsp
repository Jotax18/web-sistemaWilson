<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${categoria != null ? 'Actualizar' : 'Registrar'} Categoría | SistemaWilson</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #f4f6f9; color: #333333; font-family: 'Segoe UI', sans-serif; margin: 0; }

        .titulo-seccion {
            background-color: #ffffff; color: #2c323f; padding: 20px 25px;
            border-radius: 4px; margin-bottom: 20px;
            border-top: 4px solid #198754; box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }
        .titulo-edicion { border-top-color: #0d6efd; }

        .card-formulario {
            background: #ffffff; padding: 35px 40px; border-radius: 4px;
            border: 1px solid #e0e4e8; box-shadow: 0 1px 3px rgba(0,0,0,0.05);
            max-width: 700px; /* Tarjeta un poco más compacta al ser pocos datos */
            margin: 0 auto 30px auto;
        }

        .form-label { font-weight: 600; color: #495057; font-size: 0.85rem; text-transform: uppercase; margin-bottom: 8px; }
        .form-control { border: 1px solid #ced4da; padding: 10px 15px; border-radius: 4px; }
        .form-control:focus { border-color: #86b7fe; box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); }

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

            <div class="titulo-seccion d-flex align-items-center mb-4 ${categoria != null ? 'titulo-edicion' : ''}">
                <h4 class="mb-0 fw-bold">
                    <i class="bi ${categoria != null ? 'bi-pencil-square text-primary' : 'bi-tags-fill text-success'} me-2"></i>
                    ${categoria != null ? 'Modificar Categoría Existente' : 'Registrar Nueva Categoría'}
                </h4>
            </div>

            <div class="card-formulario">
                <form action="CategoriaServlet" method="post">

                    <input type="hidden" name="action" value="${categoria != null ? 'actualizarCategoria' : 'registrarCategoria'}">
                    <input type="hidden" name="txtId" value="${categoria.idCategoria}">

                    <div class="seccion-titulo">
                        <i class="bi bi-info-circle me-2"></i>Datos de la Categoría
                    </div>

                    <div class="row g-4 mb-4">
                        <div class="col-md-12">
                            <label class="form-label">Nombre de Categoría <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="txtNombreCat" value="${categoria.nombre}" required placeholder="Ej. Almacenamiento SSD">
                        </div>

                        <div class="col-md-12">
                            <label class="form-label">Descripción / Alcance</label>
                            <textarea class="form-control" name="txtDescripcion" rows="4" placeholder="Indique brevemente qué tipo de componentes engloba esta categoría...">${categoria.descripcion}</textarea>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
                        <a href="CategoriaServlet?action=listar" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Cancelar
                        </a>
                        <button type="submit" class="btn ${categoria != null ? 'btn-primary' : 'btn-success'}">
                            <i class="bi bi-floppy me-1"></i> ${categoria != null ? 'Guardar Cambios' : 'Registrar Categoría'}
                        </button>
                    </div>

                </form>
            </div>

        </div> </div> </div> <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>