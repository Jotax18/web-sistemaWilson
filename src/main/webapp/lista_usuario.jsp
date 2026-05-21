<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Listado Usuarios</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #1d60c4; }
        .titulo-seccion {
            background: #212529;
            color: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }
        .card-filtro {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<a href="UsuarioServlet?action=listar">Ver usuarios</a>
<div class="container py-5">
    <div class="titulo-seccion text-center">
        <h2><i class="bi bi-film"></i> Listado Usuarios Wilson</h2>
    </div>
    <div class="card-filtro mb-5">
        <h5 class="mb-3 text-secondary">Listado</h5>

        <form action="UsuarioServlet" method="GET">
            <div class="row g-3 align-items-end">

                <div class="col-md-4 col-lg-4">
                    <label class="text-secondary">DNI</label>
                    <input type="hidden" name="action" value="listarUsuarioDni">
                    <input class="form-control texts" required name="txtDni" type="text" maxlength="8" minlength="8">
                    <button type="submit" class="btn btn-primary px-4">
                        <i class="bi bi-funnel"></i> Buscar DNI
                    </button>
                    <a href="UsuarioServlet" class="btn btn-outline-secondary">Limpiar</a>
                </div>

            </div>
        </form>

    </div>

    <div class="table-responsive">
        <table class="table table-hover align-middle shadow-sm">
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
                    <td>${u.email}</td>
                    <td>${u.clave}</td>
                    <td>${u.rol}</td>
                    <td>${u.estado}</td>
                    <td>${u.fecha_creacion}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaUsuario}">
                <tr>
                    <td colspan="11" class="text-center text-muted">
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