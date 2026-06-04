<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            min-height: 100vh;
        }
        .card-login {
            border-radius: 1rem;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center login-container">
    <div class="col-md-6 col-lg-4">
        <div class="card card-login border-0">
            <div class="card-body p-5">
                <h3 class="text-center mb-4 fw-bold">Bienvenido</h3>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
                            ${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${not empty msje_logout}">
                    <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                            ${msje_logout}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <form action="LoginServlet" method="POST">

                    <div class="mb-3">
                        <label for="txtUsername" class="form-label text-muted">Usuario</label>
                        <input type="text" class="form-control form-control-lg" id="txtUsername" name="txtUsername" placeholder="Ingresa tu usuario" required autofocus>
                    </div>

                    <div class="mb-4">
                        <label for="txtPassword" class="form-label text-muted">Contraseña</label>
                        <input type="password" class="form-control form-control-lg" id="txtPassword" name="txtPassword" placeholder="Ingresa tu contraseña" required>
                    </div>

                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>