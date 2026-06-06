<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cotizaciones - Sistema Wilson</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm border-0">
        <div class="card-body p-4">
            <h3 class="card-title text-primary mb-3">Cotización de Ventas, Bienvenido de nuevo, ${usuarioSesion.nombres}</h3>
            <p class="text-muted"></p>
            <hr>

            <form action="PrincipalServlet" method="get" class="row g-3 mt-2">

                <div class="col-md-6">
                    <label class="form-label fw-bold">Buscar Componente</label>
                    <input type="text" class="form-control" name="nombreProducto" placeholder="Ej. Placa Madre, Memoria RAM..." required>
                </div>

                <div class="col-md-3">
                    <label class="form-label fw-bold">Cantidad</label>
                    <input type="number" class="form-control" name="cantidad" min="1" value="1" required>
                </div>

                <input type="hidden" name="action" value="buscar_precio">

                <div class="col-12 mt-4">
                    <button type="submit" class="btn btn-success px-4">Generar Cotización</button>
                </div>

            </form>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
