<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .sidebar-wrapper {
        width: 260px;
        min-height: 100vh;
        background-color: #212529;
        color: #ffffff;
        display: flex;
        flex-direction: column;
        box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        z-index: 1000;
    }

    .sidebar-header {
        padding: 25px 20px;
        background-color: #1a1d20;
        text-align: center;
        border-bottom: 1px solid #373b3e;
    }

    .brand-icon { font-size: 2rem; color: #0d6efd; margin-bottom: 10px; }

    .sidebar-menu { padding: 15px 0; flex-grow: 1; overflow-y: auto; }

    /* Estilo del enlace principal del módulo */
    .sidebar-link {
        color: #adb5bd;
        text-decoration: none;
        padding: 12px 25px;
        display: flex;
        align-items: center;
        font-size: 0.95rem;
        transition: all 0.2s ease-in-out;
        border-left: 4px solid transparent;
        cursor: pointer;
    }

    .sidebar-link:hover {
        color: #ffffff;
        background-color: #2c3034;
        border-left-color: #0d6efd;
    }

    /* Estilo para los sub-enlaces (Gestión de...) */
    .sidebar-sublink {
        color: #8a939b;
        text-decoration: none;
        padding: 10px 25px 10px 50px; /* Sangría a la izquierda */
        display: flex;
        align-items: center;
        font-size: 0.85rem;
        transition: all 0.2s ease;
    }

    .sidebar-sublink:hover, .sidebar-sublink.active {
        color: #ffffff;
        background-color: #2c3034;
    }

    /* Animación de la flecha al desplegar */
    .sidebar-link[aria-expanded="true"] .bi-chevron-down {
        transform: rotate(180deg);
        transition: transform 0.3s ease;
    }
    .sidebar-link[aria-expanded="false"] .bi-chevron-down {
        transition: transform 0.3s ease;
    }

    .sidebar-footer {
        padding: 15px 20px;
        background-color: #1a1d20;
        border-top: 1px solid #373b3e;
    }
</style>

<nav class="sidebar-wrapper flex-shrink-0">
    <div class="sidebar-header">
        <i class="bi bi-pc-display-horizontal brand-icon"></i>
        <h5 class="mb-0 fw-bold">SistemaWilson</h5>
        <small class="text-muted">ERP Comercial</small>
    </div>

    <div class="sidebar-menu">

        <a href="#" class="sidebar-link">
            <i class="bi bi-speedometer2 me-3"></i> Dashboard
        </a>

        <div>
            <a class="sidebar-link" data-bs-toggle="collapse" href="#moduloProductos" role="button" aria-expanded="false" aria-controls="moduloProductos">
                <i class="bi bi-box-seam me-3"></i> Productos
                <i class="bi bi-chevron-down ms-auto" style="font-size: 0.8rem;"></i>
            </a>
            <div class="collapse" id="moduloProductos">
                <div class="bg-dark bg-opacity-25 py-1">
                    <a href="ProductoServlet?action=listar" class="sidebar-sublink">
                        <i class="bi bi-circle-fill me-2" style="font-size: 0.4rem;"></i> Gestión de Productos
                    </a>
                    <a href="#" class="sidebar-sublink">
                        <i class="bi bi-circle-fill me-2" style="font-size: 0.4rem;"></i> Categorías
                    </a>
                    <a href="#" class="sidebar-sublink">
                        <i class="bi bi-circle-fill me-2" style="font-size: 0.4rem;"></i> Marcas
                    </a>
                </div>
            </div>
        </div>

        <div>
            <a class="sidebar-link" data-bs-toggle="collapse" href="#moduloUsuarios" role="button" aria-expanded="false" aria-controls="moduloUsuarios">
                <i class="bi bi-people me-3"></i> Usuarios
                <i class="bi bi-chevron-down ms-auto" style="font-size: 0.8rem;"></i>
            </a>
            <div class="collapse" id="moduloUsuarios">
                <div class="bg-dark bg-opacity-25 py-1">
                    <a href="UsuarioServlet?action=listar" class="sidebar-sublink">
                        <i class="bi bi-circle-fill me-2" style="font-size: 0.4rem;"></i> Gestión de Usuarios
                    </a>
                    <a href="#" class="sidebar-sublink">
                        <i class="bi bi-circle-fill me-2" style="font-size: 0.4rem;"></i> Roles y Permisos
                    </a>
                </div>
            </div>
        </div>

    </div>

    <div class="sidebar-footer">
        <a href="LoginServlet?action=logout" class="sidebar-link text-danger" style="padding: 5px 0;">
            <i class="bi bi-box-arrow-left me-3"></i> Cerrar Sesión
        </a>
    </div>
</nav>