<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca - Crear usuario</title>
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
        <div class="container wide">
            <h1>Crear usuario</h1>

            <form action="create-user" method="post">

                <div class="form-row">
                    <div class="form-group">
                        <label for="name">Nombre</label>
                        <input type="text" id="name" name="name" required placeholder="Nombre"
                               value="${formValues.name}"
                               class="<%= request.getAttribute("errorName") != null ? "input-error" : "" %>">
                        <c:if test="${not empty errorName}"><span class="field-error">${errorName}</span></c:if>
                    </div>
                    <div class="form-group">
                        <label for="middle_name">Segundo nombre</label>
                        <input type="text" id="middle_name" name="middle_name" placeholder="Segundo nombre"
                               value="${formValues.middleName}"
                               class="<%= request.getAttribute("errorMiddleName") != null ? "input-error" : "" %>">
                        <c:if test="${not empty errorMiddleName}"><span class="field-error">${errorMiddleName}</span></c:if>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="surname">Apellido paterno</label>
                        <input type="text" id="surname" name="surname" required placeholder="Apellido paterno"
                               value="${formValues.surname}"
                               class="<%= request.getAttribute("errorSurname") != null ? "input-error" : "" %>">
                        <c:if test="${not empty errorSurname}"><span class="field-error">${errorSurname}</span></c:if>
                    </div>
                    <div class="form-group">
                        <label for="second_surname">Apellido materno</label>
                        <input type="text" id="second_surname" name="second_surname" placeholder="Apellido materno"
                               value="${formValues.secondSurname}"
                               class="<%= request.getAttribute("errorSecondSurname") != null ? "input-error" : "" %>">
                        <c:if test="${not empty errorSecondSurname}"><span class="field-error">${errorSecondSurname}</span></c:if>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">Correo electrónico</label>
                    <input type="email" id="email" name="email" required placeholder="correo@ejemplo.com"
                           value="${formValues.email}"
                           class="<%= request.getAttribute("errorEmail") != null ? "input-error" : "" %>">
                    <c:if test="${not empty errorEmail}"><span class="field-error">${errorEmail}</span></c:if>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" required placeholder="••••••••"
                           class="<%= request.getAttribute("errorPassword") != null ? "input-error" : "" %>">
                    <c:if test="${not empty errorPassword}"><span class="field-error">${errorPassword}</span></c:if>
                </div>

                <div class="form-group">
                    <label for="type">Tipo de usuario</label>
                    <select id="type" name="type" onchange="toggleAccessLevel(this.value)">
                        <option value="student" ${formValues.type == 'student' ? 'selected' : ''}>Estudiante</option>
                        <option value="librarian" ${formValues.type == 'librarian' ? 'selected' : ''}>Bibliotecario</option>
                    </select>
                </div>

                <div class="form-group" id="access-level-group" style="display:none;">
                    <label for="access_level">Nivel de acceso</label>
                    <select id="access_level" name="access_level">
                        <option value="1" ${formValues.accessLevel == 1 ? 'selected' : ''}>1</option>
                        <option value="2" ${formValues.accessLevel == 2 ? 'selected' : ''}>2</option>
                    </select>
                </div>

                <button type="submit" class="btn">Crear usuario</button>
            </form>
        </div>
    </div>

    <script>
        function toggleAccessLevel(type) {
            const group = document.getElementById('access-level-group');
            const select = document.getElementById('access_level');
            const isLibrarian = type === 'librarian';
            group.style.display = isLibrarian ? 'block' : 'none';
            select.disabled = !isLibrarian;
        }

        toggleAccessLevel(document.getElementById('type').value);

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
