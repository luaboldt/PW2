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
        <div class="col-md-6">
            <h1>Lista</h1>
            <ul class="list-group">
                <c:forEach items="${listaUsuarios}" var="usuario">
                    <div class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            
                            <h5 class="mb-1">${usuario.nome}</h5>
                            <c:if test="${usuario.nivelPermissao == 2}">
                                <img src="https://cdn-icons-png.flaticon.com/512/6941/6941697.png" width="20" height="20">
                            </c:if>
                            <c:if test="${usuario.nivelPermissao == 1}">
                                <img src="https://cdn-icons-png.flaticon.com/512/60/60471.png" width="20" height="20">
                            </c:if>
                        </div>
                        <a class="btn btn-warning btn-sm" href="UsersServlet?acao=editar&idUsuario=${usuario.idUsuario}"><i class="bi bi-pencil-fill"></i></a>
                        <a class="btn btn-danger btn-sm" href="UsersServlet?acao=excluir&idUsuario=${usuario.idUsuario}"><i class="bi bi-trash-fill"></i></a>
                        <a class="btn btn-dark btn-sm" href="UsersServlet?acao=senha&idUsuario=${usuario.idUsuario}"><i class="bi bi-lock"></i></a>
                    </div>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-6">
            <h1>Criar</h1>
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
            <form method="POST" action="UsersServlet?acao=incluir">
                <div class="mb-3">
                  <label for="nomeInput" class="form-label">Nome Completo</label>
                  <input type="text" class="form-control" value="${nome}" name="nomeInput">
                </div>
                <div class="mb-3">
                  <label for="usuarioInput" class="form-label">Usuário</label>
                  <input type="text" class="form-control" name="usuarioInput" value="${usuarioInput}">
                </div>
                <div class="mb-3">
                  <label for="emailInput" class="form-label">E-mail</label>
                  <input type="text" class="form-control" name="emailInput" value="${email}">
                </div>
                <div class="mb-3">
                    <label for="nivelPermissaoInput" class="form-label">Nivel de Permissão</label>
                    <select id="nivelPermissaoInput" name="nivelPermissaoInput">
                            <c:if test="${nivelPermissao == 1}">
                                <option selected value="1">Escritor</option>
                                <option value="2">Administrador</option>
                            </c:if>
                            <c:if test="${nivelPermissao == 2}">
                                 <option value="1">Escritor</option>
                                <option selected value="2">Administrador</option>
                            </c:if>
                            <c:if test="${nivelPermissao == null}">
                                 <option value="1">Escritor</option>
                                <option value="2">Administrador</option>
                            </c:if>
                    </select>
                </div>
                <input type="hidden" class="form-control" name="idUsuario" value="${idUsuario}">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <input type="hidden" id="acao" name="acao" value="incluir">
                <a href="../ProjetoNoticiasJava/Admin.jsp" class="btn btn-dark">Cancelar</a>
              </form>
        </div>

      </div>
    </main>



    
  

</body>
</html>
