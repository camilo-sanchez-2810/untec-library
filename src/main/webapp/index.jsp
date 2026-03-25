<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca - Iniciar sesión</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

    <c:if test="${not empty message}">
        <div class="toast toast-success" id="toast">
            <svg class="toast-icon" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"/>
            </svg>
            <div class="toast-body">
                <div class="toast-title">Éxito</div>
                <div class="toast-message">${message}</div>
            </div>
            <button class="toast-close" onclick="dismissToast()">&times;</button>
            <div class="toast-progress"></div>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="toast toast-error" id="toast">
            <svg class="toast-icon" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd"/>
            </svg>
            <div class="toast-body">
                <div class="toast-title">Error</div>
                <div class="toast-message">${error}</div>
            </div>
            <button class="toast-close" onclick="dismissToast()">&times;</button>
            <div class="toast-progress"></div>
        </div>
    </c:if>

    <div class="page-wrapper">
        <div class="container">
            <h1>Iniciar sesión</h1>

            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Correo electrónico</label>
                    <input type="email" id="email" name="email" required placeholder="correo@ejemplo.com"
                           class="<%= request.getAttribute("errorEmail") != null ? "input-error" : "" %>">
                    <c:if test="${not empty errorEmail}"><span class="field-error">${errorEmail}</span></c:if>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" required placeholder="••••••••"
                           class="<%= request.getAttribute("errorPassword") != null ? "input-error" : "" %>">
                    <c:if test="${not empty errorPassword}"><span class="field-error">${errorPassword}</span></c:if>
                </div>

                <button type="submit" class="btn">Iniciar sesión</button>
            </form>
        </div>
    </div>

    <script>
        const toast = document.getElementById('toast');
        function dismissToast() {
            if (!toast) return;
            toast.classList.add('toast-hide');
            setTimeout(() => toast.remove(), 400);
        }
        if (toast) {
            setTimeout(dismissToast, 3500);
        }
    </script>
</body>
</html>
