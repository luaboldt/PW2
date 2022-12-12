/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Noticias;
import model.Categorias;
import DAO.ArticleDAO;
import DAO.CategoryDAO;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import model.Usuarios;

/**
 *
 * @author nickm
 */
@WebServlet(name = "ArticleServlet", urlPatterns = {"/ArticleServlet"})
public class ArticleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8"); // faz a saída do request dispatcher ser em formato utf-8
        
        String acao = request.getParameter("acao");
        
        List<Noticias> listaNoticias = new ArrayList<>();
        List<Categorias> listaCategorias = new ArrayList<>();
        
        Noticias noticia = new Noticias();
        ArticleDAO noticiaDAO = new ArticleDAO();
        
        /**
         * Pega a session do usuário logado
         */
        HttpSession sessao = request.getSession();
        Usuarios usuarioLogado = new Usuarios();
        usuarioLogado = (Usuarios) sessao.getAttribute("usuarioLogado");
        request.setAttribute("usuarioLogado", usuarioLogado);
       
        
        
        /**
         * Busca as categorias para mostrar no topo da página
         */
        CategoryDAO categoriaDAO = new CategoryDAO();
        listaCategorias = categoriaDAO.ListarCategorias();
        request.setAttribute("listaCategorias", listaCategorias);
        
        
        if(acao.equalsIgnoreCase("incluir")){
            
            String titulo = "";
            String autor = "";
            String texto = "";
            String descricao = "";
            String idCategoria = "";
            String idNoticia = "";
                    
            boolean validacao = true;
            
            RequestDispatcher rd = request.getRequestDispatcher("ArticleCreate.jsp");

            autor = request.getParameter("autorInput");
            texto = request.getParameter("textoInput");
            descricao = request.getParameter("descricaoInput");
            titulo = request.getParameter("tituloInput");
            idCategoria = request.getParameter("idCategoria");
            idNoticia = request.getParameter("idNoticia");
            
            /**
             * Setamos as variáveis aqui
             * para, se der erro, elas já estarem armazenadas
             * e não ser necessário a digitação delas
             */
            request.setAttribute("autor", autor);
            request.setAttribute("titulo", titulo);
            request.setAttribute("descricao", descricao);
            request.setAttribute("texto", texto);
            request.setAttribute("idCategoria", idCategoria);
            request.setAttribute("idNoticia", idNoticia);
            
            try {
                // Se algum campo não estiver preenchido, retorna erro
                if( 
                    autor == null || autor.isEmpty() ||
                    texto == null || texto.isEmpty() ||
                    descricao == null || descricao.isEmpty() ||
                    titulo == null || titulo.isEmpty() ||
                    idCategoria == null || idCategoria.isEmpty()
                ){
                    request.setAttribute("retorno", "Não foram preenchidos todos os campos!");
                    validacao = false;
                    rd.forward(request, response);
                }
                
            }catch(NumberFormatException e){
                validacao = false;
                request.setAttribute("retorno", "Apenas números não são aceitos");
                rd.forward(request, response);
            }
            
            
            if(validacao == true){
                
                
                
                noticia.setAutor(autor);
                noticia.setTexto(texto);
                noticia.setTitulo(titulo);
                noticia.setIdCategoria(Integer.parseInt(idCategoria));
                noticia.setDescricao(descricao);
                if(idNoticia == ""){
                    idNoticia = "0";
                }
                noticia.setIdNoticia(Integer.parseInt(idNoticia));

               
                //noticiaDAO.CadastrarNoticia(noticia);
                if(noticiaDAO.CadastrarNoticia(noticia)){
                    rd = request.getRequestDispatcher("ArticleServlet?acao=listar");
                    
                    request.removeAttribute("autor");
                    request.removeAttribute("titulo");
                    request.removeAttribute("descricao");
                    request.removeAttribute("texto");
                    request.removeAttribute("idCategoria");
                    request.removeAttribute("idNoticia");
                    
                    request.setAttribute("retorno", "Cadastro efetuado com sucesso!");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("retorno", "Não foi possível efetuar o cadastro");
                    rd.forward(request, response);
                }
                
                
                
            } else {
                request.setAttribute("retorno", "Não foi possível efetuar o cadastro");
                rd.forward(request, response);
            }
            
        }
        
        if(acao.equalsIgnoreCase("exibir")){
            String idNoticia = request.getParameter("idNoticia");
            try {
                noticia = noticiaDAO.selecionarNoticia(idNoticia);
            } catch (ParseException ex) {
                Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String autor = noticia.getAutor();
            String texto = noticia.getTexto();
            String descricao = noticia.getDescricao();
            String titulo = noticia.getTitulo();
            String dataCriacao = noticia.getDataCriacao();
            int idCategoria = noticia.getIdCategoria();
           
            
            RequestDispatcher rd = request.getRequestDispatcher("Article.jsp");
            
            request.setAttribute("autor", autor);
            request.setAttribute("titulo", titulo);
            request.setAttribute("descricao", descricao);
            request.setAttribute("texto", texto);
            request.setAttribute("idCategoria", idCategoria);
            request.setAttribute("dataCriacao", dataCriacao);
            request.setAttribute("idNoticia", idNoticia);
            rd.forward(request, response);
        }
        
        if(acao.equalsIgnoreCase("editar")){
            String idNoticia = request.getParameter("idNoticia");
            try {
                noticia = noticiaDAO.selecionarNoticia(idNoticia);
            } catch (ParseException ex) {
                Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String autor = noticia.getAutor();
            String texto = noticia.getTexto();
            String descricao = noticia.getDescricao();
            String titulo = noticia.getTitulo();
            int idCategoria = noticia.getIdCategoria();
            
            RequestDispatcher rd = request.getRequestDispatcher("ArticleCreate.jsp");
            
            request.setAttribute("autor", autor);
            request.setAttribute("titulo", titulo);
            request.setAttribute("descricao", descricao);
            request.setAttribute("texto", texto);
            request.setAttribute("idCategoria", idCategoria);
            request.setAttribute("idNoticia", idNoticia);
            rd.forward(request, response);
        }

        
        if(acao.equalsIgnoreCase("criacao")){

            RequestDispatcher rd = request.getRequestDispatcher("ArticleCreate.jsp");
            rd.forward(request, response);
        }
        
        
        if(acao.equalsIgnoreCase("listar")){
            
            String filtroCategoria = request.getParameter("category");
            
            
            try {
                if(filtroCategoria == null || filtroCategoria.isEmpty()){
                    listaNoticias = noticiaDAO.ListarNoticias();
                } else {
                    listaNoticias = noticiaDAO.ListarNoticias(filtroCategoria);
                }
                
            } catch (ParseException ex) {
                Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("ArticleList.jsp");
            request.setAttribute("listaNoticias", listaNoticias);
            rd.forward(request, response);
        }
        
        if(acao.equalsIgnoreCase("excluir")){
            String idNoticia = request.getParameter("idNoticia");
            noticiaDAO.excluirNoticia(idNoticia);
            RequestDispatcher rd = request.getRequestDispatcher("ArticleServlet?acao=listar");
            rd = request.getRequestDispatcher("ArticleServlet?acao=listar");
            request.setAttribute("retorno", "Notícia excluída!");
            rd.forward(request, response);
            
        }
        
        
        /*
        try ( PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ArticleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2>"+request.getParameter("tituloInput")+"</h2>");
            out.println("<h2>"+request.getParameter("autorInput")+"</h2>");
            out.println("<h2>"+request.getParameter("textoInput")+"</h2>");
            out.println("<h2>"+request.getParameter("descricaoInput")+"</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
