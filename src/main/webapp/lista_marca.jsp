<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Marcas | SistemaWilson</title>
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

    .btn { border-radius: 4px; font-weight: 500; letter-spacing: 0.3px; }
    .btn-primary { background-color: #0d6efd; border-color: #0d6efd; }
    .btn-success { background-color: #198754; border-color: #198754; }

    .badge-activo { background-color: #d1e7dd; color: #0f5132; }
    .badge-inactivo { background-color: #f8d7da; color: #842029; }
  </style>
</head>
<body>

<div class="d-flex" style="min-height: 100vh; background-color: #f4f6f9;">

  <jsp:include page="sidebar.jsp" />

  <div class="flex-grow-1 overflow-auto">
    <div class="container-fluid py-4 px-4 px-xl-5">

      <div class="titulo-seccion d-flex align-items-center justify-content-between">
        <h4 class="mb-0 fw-bold"><i class="bi bi-award-fill me-2 text-primary"></i> Control de Marcas Fabricantes</h4>
        <a href="MarcaServlet?action=cargarFormularioRegistrar" class="btn btn-success shadow-sm">
          <i class="bi bi-plus-circle me-1"></i> Nueva Marca
        </a>
      </div>

      <div class="card-panel p-0 overflow-hidden">
        <div class="table-responsive">
          <table class="table table-hover align-middle">
            <thead class="table-light-header">
            <tr>
              <th style="width: 100px;">ID</th>
              <th>Nombre de la Marca</th>
              <th class="text-center" style="width: 140px;">Estado</th>
              <th class="text-center" style="width: 150px;">Acciones</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${lista}" var="m">
              <tr>
                <td class="fw-bold text-dark">${m.idMarca}</td>
                <td class="fw-bold text-primary">${m.nombre}</td>
                <td class="text-center">
                  <c:choose>
                    <c:when test="${m.estado == 1}">
                      <span class="badge badge-activo px-2 py-1">Activo</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge badge-inactivo px-2 py-1">Inactivo</span>
                    </c:otherwise>
                  </c:choose>
                </td>

                <td class="text-center">
                  <div class="d-flex justify-content-center gap-1">
                    <a href="MarcaServlet?action=cargarFormularioActualizar&id=${m.idMarca}"
                       class="btn btn-outline-primary btn-sm" title="Editar Marca">
                      <i class="bi bi-pencil-square"></i>
                    </a>

                    <c:if test="${m.estado == 1}">
                      <button type="button" class="btn btn-outline-danger btn-sm"
                              onclick="abrirModalEstado(${m.idMarca}, '${m.nombre}', 1)" title="Desactivar">
                        <i class="bi bi-trash3"></i>
                      </button>
                    </c:if>

                    <c:if test="${m.estado == 0}">
                      <button type="button" class="btn btn-outline-success btn-sm"
                              onclick="abrirModalEstado(${m.idMarca}, '${m.nombre}', 0)" title="Activar">
                        <i class="bi bi-check2-square"></i>
                      </button>
                    </c:if>
                  </div>
                </td>
              </tr>
            </c:forEach>

            <c:if test="${empty lista}">
              <tr>
                <td colspan="4" class="text-center py-5">
                  <i class="bi bi-award text-secondary" style="font-size: 2.5rem;"></i>
                  <h6 class="text-muted mt-3">No hay marcas registradas en el catálogo.</h6>
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

<div class="modal fade" id="modalCambiarEstado" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form action="MarcaServlet" method="POST">
        <div class="modal-header border-0 pb-0">
          <h5 class="modal-title fw-bold text-dark">Confirmar Acción</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <div class="modal-body text-center pt-4 pb-4">
          <p id="textoModalEstado" class="fs-5 mb-0"></p>

          <input type="hidden" name="action" value="actualizarEstado">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function abrirModalEstado(idMarca, nombre, estadoActual) {
    const textoMensaje = document.getElementById('textoModalEstado');
    const btnConfirmar = document.getElementById('btnConfirmarEstado');
    const inputId = document.getElementById('modalInputId');
    const inputEstado = document.getElementById('modalInputEstado');

    let nuevoEstadoEnviar = (estadoActual == 1) ? 0 : 1;

    if (estadoActual == 1) {
      textoMensaje.innerHTML = '¿Seguro que deseas <strong>desactivar</strong> la marca "' + nombre + '"?';
      btnConfirmar.className = "btn btn-danger px-4";
    } else {
      textoMensaje.innerHTML = '¿Seguro que deseas <strong>activar</strong> la marca "' + nombre + '"?';
      btnConfirmar.className = "btn btn-success px-4";
    }

    inputId.value = idMarca;
    inputEstado.value = nuevoEstadoEnviar;

    const modal = new bootstrap.Modal(document.getElementById('modalCambiarEstado'));
    modal.show();
  }
</script>
</body>
</html>