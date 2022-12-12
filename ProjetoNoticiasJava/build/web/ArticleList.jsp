<%-- 
    Document   : Home
    Created on : 19 de out. de 2022, 19:53:10
    Author     : nickm
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
    Referência: https://getbootstrap.com/docs/5.0/examples/blog/#

https://www.youtube.com/watch?v=W4D1TVGd7UA

import java.util.ArrayList;
import java.util.List;

import model.Noticias;
import DAO.ArticleDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
-->
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Noticias"%>
<%@page import="DAO.ArticleDAO"%>
<%@page import="DAO.CategoryDAO"%>
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
        <div class="col-md-8">
            <c:if test="${usuarioLogado.nivelPermissao >= 1}">
                <a class="btn btn-primary btn-lg" href="ArticleServlet?acao=criacao">Criar post</a>
            </c:if>
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
                    <p class="blog-post-meta">${noticia.dataCriacao} <a href="#">${noticia.autor}</a></p>
                    
                    <p>${noticia.descricao}</p>
                    
                    <a class="btn btn-info btn-sm" href="ArticleServlet?acao=exibir&idNoticia=${noticia.idNoticia}"><i class="bi bi-eye-fill"></i></a>
                    <c:if test="${usuarioLogado.nivelPermissao >= 1}">
                        <a class="btn btn-warning btn-sm" href="ArticleServlet?acao=editar&idNoticia=${noticia.idNoticia}"><i class="bi bi-pencil-fill"></i></a>
                    </c:if>
                    <c:if test="${usuarioLogado.nivelPermissao == 2}">
                        <a class="btn btn-danger btn-sm" href="ArticleServlet?acao=excluir&idNoticia=${noticia.idNoticia}"><i class="bi bi-trash-fill"></i></a>
                    </c:if>
                </article>
                <hr>
            </c:forEach>

        </div>

        <div class="col-md-4">
          <div class="position-sticky" style="top: 2rem;">
            <div class="p-4 mb-3 bg-light rounded">
              <h4 class="fst-italic">Sobre</h4>
              <p class="mb-0">As notícias mais confiáveis do Brasil em um só lugar de variados assuntos aqui, no Portal de Notícias!</p>
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
