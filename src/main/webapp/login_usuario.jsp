<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceso al Sistema | SistemaWilson</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

    <style>
        /* Tema ERP / Administrativo Claro (Alineado con el resto del sistema) */
        body {
            background-color: #f4f6f9;
            color: #333333;
            font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
        }

        .login-container {
            min-height: 100vh;
        }

        /* Contenedor principal del Login */
        .card-login {
            background: #ffffff;
            border-radius: 6px;
            border: 1px solid #e0e4e8;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08); /* Sombra un poco más pronunciada para resaltar en el centro */
            overflow: hidden;
        }

        /* Encabezado de la tarjeta con branding corporativo */
        .login-header {
            background-color: #ffffff;
            padding: 40px 30px 20px 30px;
            text-align: center;
            border-bottom: 1px solid #f4f6f9;
        }

        .brand-icon {
            font-size: 3rem;
            color: #0d6efd;
            margin-bottom: 10px;
        }

        .login-header h4 {
            font-weight: 700;
            color: #2c323f;
            letter-spacing: 0.5px;
            margin-bottom: 5px;
        }

        .login-header p {
            color: #6c757d;
            font-size: 0.9rem;
            margin-bottom: 0;
        }

        /* Estilos de los inputs corporativos */
        .card-body {
            padding: 30px 40px 40px 40px;
        }

        .form-label {
            font-weight: 600;
            color: #495057;
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 8px;
        }

        .form-control, .input-group-text {
            background-color: #ffffff;
            border: 1px solid #ced4da;
            color: #495057;
            padding: 12px 15px; /* Cajas ligeramente más grandes para comodidad en el login */
            border-radius: 4px;
        }

        .form-control:focus {
            border-color: #86b7fe;
            box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
            z-index: 3;
        }

        /* Botón del ojito */
        .btn-toggle-password {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            color: #6c757d;
            padding: 12px 15px;
        }
        .btn-toggle-password:hover {
            background-color: #e9ecef;
            color: #495057;
        }

        /* Botón Principal */
        .btn-primary {
            background-color: #0d6efd;
            border-color: #0d6efd;
            border-radius: 4px;
            font-weight: 600;
            padding: 12px;
            letter-spacing: 0.5px;
            text-transform: uppercase;
            font-size: 0.95rem;
        }
        .btn-primary:hover {
            background-color: #0b5ed7;
            border-color: #0a58ca;
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center login-container">
    <div class="col-md-6 col-lg-5 col-xl-4">

        <div class="card card-login border-0">

            <div class="login-header">
                <i class="bi bi-pc-display-horizontal brand-icon"></i>
                <h4>SistemaWilson</h4>
                <p>Gestión y Control de Ventas</p>
            </div>

            <div class="card-body">

                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show d-flex align-items-center" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        <div>${error}</div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${not empty msje_logout}">
                    <div class="alert alert-success alert-dismissible fade show d-flex align-items-center" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i>
                        <div>${msje_logout}</div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <form action="LoginServlet" method="POST">

                    <div class="mb-4">
                        <label for="txtUsername" class="form-label">Usuario</label>
                        <div class="input-group">
                            <span class="input-group-text bg-light"><i class="bi bi-person text-secondary"></i></span>
                            <input type="text" class="form-control" id="txtUsername" name="txtUsername" placeholder="Ingresa tu usuario" required autofocus>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="txtPassword" class="form-label">Contraseña</label>
                        <div class="input-group">
                            <span class="input-group-text bg-light"><i class="bi bi-lock text-secondary"></i></span>
                            <input type="password" class="form-control" id="txtPassword" name="txtPassword" placeholder="Ingresa tu contraseña" required>
                            <button class="btn btn-toggle-password" type="button" onclick="togglePassword('txtPassword', 'iconClave')">
                                <i class="bi bi-eye" id="iconClave"></i>
                            </button>
                        </div>
                    </div>

                    <div class="d-grid gap-2 mt-5">
                        <button type="submit" class="btn btn-primary shadow-sm">
                            Iniciar Sesión <i class="bi bi-box-arrow-in-right ms-1"></i>
                        </button>
                    </div>

                </form>
            </div>
        </div>

        <div class="text-center mt-4">
            <small class="text-muted">&copy; 2026 SistemaWilson. Todos los derechos reservados.</small>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Función para mostrar/ocultar contraseña (El ojito)
    function togglePassword(inputId, iconId) {
        const input = document.getElementById(inputId);
        const icon = document.getElementById(iconId);

        if (input.type === "password") {
            input.type = "text";
            icon.classList.replace("bi-eye", "bi-eye-slash");
        } else {
            input.type = "password";
            icon.classList.replace("bi-eye-slash", "bi-eye");
        }
    }
</script>

</body>
</html>