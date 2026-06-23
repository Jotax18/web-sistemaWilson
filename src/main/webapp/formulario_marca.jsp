<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>${marca != null ? 'Actualizar' : 'Registrar'} Marca | SistemaWilson</title>
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
      max-width: 550px; /* Tarjeta compacta ideal para un solo input */
      margin: 0 auto 30px auto;
    }

    .form-label { font-weight: 600; color: #495057; font-size: 0.85rem; text-transform: uppercase; margin-bottom: 8px; }
    .form-control { border: 1px solid #ced4da; padding: 12px 15px; border-radius: 4px; }
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

      <div class="titulo-seccion d-flex align-items-center mb-4 ${marca != null ? 'titulo-edicion' : ''}">
        <h4 class="mb-0 fw-bold">
          <i class="bi ${marca != null ? 'bi-pencil-square text-primary' : 'bi-award-fill text-success'} me-2"></i>
          ${marca != null ? 'Modificar Marca Existente' : 'Registrar Nueva Marca'}
        </h4>
      </div>

      <div class="card-formulario">
        <form action="MarcaServlet" method="POST">

          <input type="hidden" name="action" value="${marca != null ? 'actualizarMarca' : 'registrarMarca'}">
          <input type="hidden" name="txtId" value="${marca.idMarca}">

          <div class="seccion-titulo">
            <i class="bi bi-buildings me-2"></i>Fabricante
          </div>

          <div class="mb-4">
            <label class="form-label">Nombre de la Marca <span class="text-danger">*</span></label>
            <input type="text" class="form-control form-control-lg" name="txtNombreMar" value="${marca.nombre}" required autofocus placeholder="Ej. ASUS, Corsair, Gigabyte...">
          </div>

          <div class="d-flex justify-content-end gap-3 mt-5 pt-3 border-top">
            <a href="MarcaServlet?action=listar" class="btn btn-outline-secondary">
              <i class="bi bi-arrow-left me-1"></i> Cancelar
            </a>
            <button type="submit" class="btn ${marca != null ? 'btn-primary' : 'btn-success'} shadow-sm">
              <i class="bi bi-floppy me-1"></i> ${marca != null ? 'Guardar Cambios' : 'Registrar Marca'}
            </button>
          </div>

        </form>
      </div>

    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>