<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca - Crear usuario</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container wide">
        <h1>Crear usuario</h1>
        <form action="create-user" method="post">

            <div class="form-row">
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <input type="text" id="name" name="name" required placeholder="Nombre">
                </div>
                <div class="form-group">
                    <label for="middle_name">Segundo nombre</label>
                    <input type="text" id="middle_name" name="middle_name" placeholder="Segundo nombre">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="surname">Apellido paterno</label>
                    <input type="text" id="surname" name="surname" required placeholder="Apellido paterno">
                </div>
                <div class="form-group">
                    <label for="second_surname">Apellido materno</label>
                    <input type="text" id="second_surname" name="second_surname" placeholder="Apellido materno">
                </div>
            </div>

            <div class="form-group">
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" required placeholder="correo@ejemplo.com">
            </div>

            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required placeholder="••••••••">
            </div>

            <div class="form-group">
                <label for="type">Tipo de usuario</label>
                <select id="type" name="type" onchange="toggleAccessLevel(this.value)">
                    <option value="student">Estudiante</option>
                    <option value="librarian">Bibliotecario</option>
                </select>
            </div>

            <div class="form-group" id="access-level-group" style="display:none;">
                <label for="access_level">Nivel de acceso</label>
                <select id="access_level" name="access_level">
                    <option value="1">1</option>
                    <option value="2">2</option>
                </select>
            </div>

            <button type="submit" class="btn">Crear usuario</button>
        </form>
        <div class="link-row">
            ¿Ya tienes cuenta? <a href="index.jsp">Iniciar sesión</a>
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
    </script>
</body>
</html>
