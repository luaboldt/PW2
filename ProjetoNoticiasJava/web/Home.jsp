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
        <title>Portal de Notícias</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
       <!--biblioteca ìcones do bootstrap 5-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <!-- onde pegar ícones: https://icons.getbootstrap.com
            Tutorial: https://www.tutorialrepublic.com/twitter-bootstrap-tutorial/bootstrap-icons.php
        -->
    </head>
    <body>
    
    <div class="container">
      <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
          <div class="col-4 pt-1">
            <a class="link-secondary" href="CategoryServlet?acao=listar">Administração</a>
          </div>
          <div class="col-4 text-center">
            <a class="blog-header-logo text-dark" href="#">Portal de Notícias</a>
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
        <div class="col-md-8">
            <a class="btn btn-primary btn-lg" href="./ArticleCreate.jsp">Criar post</a>
            <c:if test="${!empty retorno}">
                <div class="alert alert-success" role="alert">
                    ${retorno}
                </div>
            </c:if>
            
            
            <h3 class="pb-4 mb-4 fst-italic border-bottom">
              Os mais recentes!
            </h3>
            <c:forEach items="${listaNoticias}" var="noticia">
                <article class="blog-post">
                    <h2 class="blog-post-title">${noticia.titulo}</h2>
                    <p class="blog-post-meta">January 1, 2021 by <a href="#">${noticia.autor}</a></p>

                    <p>${noticia.descricao}</p>

                    <a class="btn btn-info btn-sm" href="#"><i class="bi bi-eye-fill"></i></a>
                    <a class="btn btn-warning btn-sm" href="#"><i class="bi bi-pencil-fill"></i></a>
                    <a class="btn btn-danger btn-sm" href="#"><i class="bi bi-trash-fill"></i></a>
                </article>
                <hr>
            </c:forEach>
          <article class="blog-post">
            <h2 class="blog-post-title">Sample blog post</h2>
            <p class="blog-post-meta">January 1, 2021 by <a href="#">Mark</a></p>

            <p>This blog post shows a few different types of content that’s supported and styled with Bootstrap. Basic typography, lists, tables, images, code, and more are all supported as expected.</p>
            
            <a class="btn btn-info btn-sm" href="#"><i class="bi bi-eye-fill"></i></a>
            <a class="btn btn-warning btn-sm" href="#"><i class="bi bi-pencil-fill"></i></a>
            <a class="btn btn-danger btn-sm" href="#"><i class="bi bi-trash-fill"></i></a>
          </article>
          <hr>
            <article class="blog-post">
            <h2 class="blog-post-title">Sample blog post</h2>
            <p class="blog-post-meta">January 1, 2021 by <a href="#">Mark</a></p>

            <p>This blog post shows a few different types of content that’s supported and styled with Bootstrap. Basic typography, lists, tables, images, code, and more are all supported as expected.</p>

            <a class="btn btn-info btn-sm" href="#"><i class="bi bi-eye-fill"></i></a>
            <a class="btn btn-warning btn-sm" href="#"><i class="bi bi-pencil-fill"></i></a>
            <a class="btn btn-danger btn-sm" href="#"><i class="bi bi-trash-fill"></i></a>
          </article>
          <hr>
          <nav class="blog-pagination" aria-label="Pagination">
            <a class="btn btn-outline-primary" href="#">Antigos</a>
            <a class="btn btn-outline-secondary disabled" href="#" tabindex="-1" aria-disabled="true">Novos</a>
          </nav>

        </div>

        <div class="col-md-4">
          <div class="position-sticky" style="top: 2rem;">
            <div class="p-4 mb-3 bg-light rounded">
              <h4 class="fst-italic">Sobre</h4>
              <p class="mb-0">As notícias mais confiáveis do Brasil em um só lugar de variados assuntos aqui, no Portal de Notícias!</p>
            </div>

            <div class="p-4">
              <h4 class="fst-italic">Arquivos</h4>
              <ol class="list-unstyled mb-0">
                <li><a href="#">March 2021</a></li>
                <li><a href="#">February 2021</a></li>
                <li><a href="#">January 2021</a></li>
                <li><a href="#">December 2020</a></li>
                <li><a href="#">November 2020</a></li>
                <li><a href="#">October 2020</a></li>
                <li><a href="#">September 2020</a></li>
                <li><a href="#">August 2020</a></li>
                <li><a href="#">July 2020</a></li>
                <li><a href="#">June 2020</a></li>
                <li><a href="#">May 2020</a></li>
                <li><a href="#">April 2020</a></li>
              </ol>
            </div>

            <div class="p-4">
              <h4 class="fst-italic">Redes Sociais</h4>
              <ol class="list-unstyled">
                <li><a href="#">GitHub</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Facebook</a></li>
              </ol>
            </div>
          </div>
        </div>
      </div>

    </main>

    <footer class="blog-footer">
      <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      <p>
        <a href="#">Back to top</a>
      </p>
    </footer>


    
  

</body>
</html>
