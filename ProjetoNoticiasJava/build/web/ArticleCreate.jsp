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
        <title>Criar Notícia</title>
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
      <div class="row g-5">
        <div class="col-md-12">
            <h1>Criar Notícia</h1>
            <c:if test="${!empty retorno}">
                <div class="alert alert-danger" role="alert">
                    ${retorno}
                </div>
            </c:if>
            <form method="POST" action="ArticleServlet">
                <div class="mb-3">
                  <label for="tituloInput" class="form-label">Título</label>
                  <input type="text" class="form-control" value="${titulo}" name="tituloInput" aria-describedby="tituloHelp">
                  <div id="tituloHelp" class="form-text">Escreva um que se destaque de todos os artigos!</div>
                </div>
                <div class="mb-3">
                  <label for="autorInput" class="form-label">Autor</label>
                  <input type="text" class="form-control" name="autorInput" value="${autor}">
                </div>
                <div class="mb-3">
                  <label for="descricaoInput" class="form-label">Descrição</label>
                  <input type="text" class="form-control" name="descricaoInput" value="${descricao}">
                </div>
                <div class="mb-3">
                  <label for="textoInput" class="form-label">Texto</label>
                  <textarea class="form-control" name="textoInput">${texto}</textarea>
                </div>
                <div class="mb-3">
                    <label for="categoriaInput" class="form-label">Categoria ${$idCategoria}</label>
                    <select id="idCategoria" name="idCategoria">
                        <c:forEach items="${listaCategorias}" var="categoria">
                            <c:if test="${idCategoria == categoria.idCategoria}">
                                <option selected value="${categoria.idCategoria}">${categoria.nomeCategoria}</option>
                            </c:if>
                            <c:if test="${$idCategoria == null || idCategoria != categoria.idCategoria}">
                                <option value="${categoria.idCategoria}">${categoria.nomeCategoria}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                </div>
                <input type="hidden" id="idNoticia" name="idNoticia" value="${idNoticia}">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <input type="hidden" id="acao" name="acao" value="incluir">
                <a href="ArticleServlet?acao=listar" class="btn btn-dark">Cancelar</a>
              </form>
        </div>

      </div>
    </main>



    
  

</body>
</html>
