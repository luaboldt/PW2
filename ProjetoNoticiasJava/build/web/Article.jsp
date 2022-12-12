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
        <title>Notícia</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    </head>
    <body>
    
    <div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
            <c:if test="${usuarioLogado.nivelPermissao == 2}">
                <a class="link-secondary" href="Admin.jsp">Administração</a>
            </c:if>
          </div>
          <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="ArticleServlet?acao=listar">Portal de Notícias</a>
          </div>
          <div class="col-4 d-flex justify-content-end align-items-center">
               <div class="col-4 pt-1">
                <c:if test="${usuarioLogado.ativo == 1}">
                    <a class="link-secondary" href="UsersServlet?acao=deslogar">Logout</a>
                </c:if>
                <c:if test="${usuarioLogado.ativo == null || usuarioLogado.ativo == 0}">
                    <a class="link-secondary" href="Login.jsp">Login</a>
                </c:if>
             </div>
          </div>
        </div>
      </header>

      <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
            <!-- Adicionar foreach para capturar as categorias para buscar-->
            <c:forEach items="${listaCategorias}" var="categoria">
                <a class="p-2 link-secondary" href="ArticleServlet?acao=listar&category=${categoria.idCategoria}">${categoria.nomeCategoria}</a>
            </c:forEach>
        </nav>
      </div>
    </div>

    <main class="container">
            <a class="btn btn-dark" href="ArticleServlet?acao=listar"><i class="bi bi-arrow-90deg-left"></i></a>
            <c:if test="${usuarioLogado.nivelPermissao >= 1}">
                <a class="btn btn-warning" href="ArticleServlet?acao=editar&idNoticia=${idNoticia}"><i class="bi bi-pencil-fill"></i></a>
            </c:if>
            <c:if test="${usuarioLogado.nivelPermissao == 2}">
                <a class="btn btn-danger" href="ArticleServlet?acao=excluir&idNoticia=${idNoticia}"><i class="bi bi-trash-fill"></i></a>
            </c:if>
      <div class="row g-5">
        <div class="col-md-12">
          <article class="blog-post">
            <h2 class="blog-post-title display-1">${titulo}</h2>
            <p class="blog-post-meta">${dataCriacao} <a href="#">${autor}</a></p>

            <p>${descricao}</p>
            
            <hr>
            <p>${texto}</p>
          </article>

        </div>

      </div>
      <a href="#"><i class="bi bi-arrow-bar-up" width="16" height="16" ></i></a>
    </main>



    
  

</body>
</html>
