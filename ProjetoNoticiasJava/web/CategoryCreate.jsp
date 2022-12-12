<%-- 
    Document   : Home
    Created on : 19 de out. de 2022, 19:53:10
    Author     : nickm
--%>
<!--
    Referência: https://getbootstrap.com/docs/5.0/examples/blog/#
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Criar Categoria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    </head>
    <body>
    
    <div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
            <a class="link-secondary" href="../ProjetoNoticiasJava/">Home</a>
          </div>
          <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="../ProjetoNoticiasJava/">Portal de Notícias</a>
          </div>
          <div class="col-4 d-flex justify-content-end align-items-center">
            <a class="link-secondary" href="#" aria-label="Search">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3" role="img" viewBox="0 0 24 24"><title>Search</title><circle cx="10.5" cy="10.5" r="7.5"></circle><path d="M21 21l-5.2-5.2"></path></svg>
            </a>
            <a class="btn btn-sm btn-outline-secondary" href="#">Inscrever-se</a>
          </div>
        </div>
      </header>

      <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
            <!-- Adicionar foreach para capturar as categorias para buscar-->
          <a class="p-2 link-secondary" href="#">World</a>
          <a class="p-2 link-secondary" href="#">U.S.</a>
          <a class="p-2 link-secondary" href="#">Technology</a>
          <a class="p-2 link-secondary" href="#">Design</a>
          <a class="p-2 link-secondary" href="#">Culture</a>
          <a class="p-2 link-secondary" href="#">Business</a>
          <a class="p-2 link-secondary" href="#">Politics</a>
          <a class="p-2 link-secondary" href="#">Opinion</a>
          <a class="p-2 link-secondary" href="#">Science</a>
          <a class="p-2 link-secondary" href="#">Health</a>
          <a class="p-2 link-secondary" href="#">Style</a>
          <a class="p-2 link-secondary" href="#">Travel</a>
        </nav>
      </div>
    </div>

    <main class="container">
      <div class="row g-5">
        <div class="col-md-6">
            <h1>Lista</h1>
            <ul class="list-group">
                <c:forEach items="${listaCategorias}" var="categoria">
                    <div class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${categoria.nomeCategoria}</h5>
                        </div>
                        <p class="mb-1">${categoria.descricaoCategoria}</p>
                    </div>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-6">
            <h1>Criar Categoria</h1>
            <c:if test="${!empty retorno}">
                <c:choose>
                    <c:when test="${erro == 0}">
                        <div class="alert alert-success" role="alert">
                            ${retorno}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-danger" role="alert">
                            ${retorno}
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <form method="POST" action="CategoryServlet?acao=incluir">
                <div class="mb-3">
                  <label for="nomeInput" class="form-label">Nome da Categoria</label>
                  <input type="text" class="form-control" value="${nome}" name="nomeInput" aria-describedby="tituloHelp">
                </div>
                <div class="mb-3">
                  <label for="descricaoInput" class="form-label">Descrição</label>
                  <input type="text" class="form-control" name="descricaoInput" value="${descricao}">
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
                <input type="hidden" id="acao" name="acao" value="incluir">
                <a href="../ProjetoNoticiasJava/" class="btn btn-dark">Cancelar</a>
              </form>
        </div>

      </div>
    </main>



    
  

</body>
</html>
