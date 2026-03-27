<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca - Menu Inicial</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="page-wrapper">
        <div class="container wide">
            <header class="header-section">
                <h1>Catálogo de Libros</h1>
                <form action="${pageContext.request.contextPath}/logout" method="POST">
                    <button type="submit" class="btn logout-btn">Cerrar Sesión</button>
                </form>
            </header>

            <p class="welcome-text">
                Bienvenido: <strong><c:out value="${name}"/> <c:out value="${surname}"/></strong>
            </p>

            <div class="book-grid">
                <c:forEach var="book" items="${books}">
                    <article class="book-card">
                        <img src="${not empty book.coverUrl ? book.coverUrl : 'https://via.placeholder.com/320x450?text=Sin+Portada'}" 
                             alt="${book.title}" 
                             class="book-cover">
                        
                        <div class="book-info">
                            <h2 class="book-title">${book.title}</h2>
                            <p class="book-author">por ${book.authors}</p>
                            
                            <div class="book-meta">
                                <span class="badge">${book.categories}</span>
                                <span class="badge">${book.language}</span>
                                <div>Publicado en: ${book.year}</div>
                            </div>
                        </div>
                    </article>
                </c:forEach>

                <c:if test="${empty books}">
                    <div class="empty-message">
                        No hay libros disponibles en este momento.
                    </div>
                </c:if>
            </div>

            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="${pageContext.request.contextPath}/book/home?page=${i}" 
                       class="page-link ${param.page == i || (empty param.page && i == 1) ? 'active' : ''}">
                        ${i}
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>