/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categorias;
import DAO.CategoryDAO;
import model.Usuarios;
import DAO.UsersDAO;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickm
 */
@WebServlet(name = "UsersServlet", urlPatterns = {"/UsersServlet"})
public class UsersServlet extends HttpServlet {

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
        
        List<Categorias> listaCategorias = new ArrayList<>();
        List<Usuarios> listaUsuarios = new ArrayList<>();
        
        Categorias categoria = new Categorias();
        CategoryDAO categoriaDAO = new CategoryDAO();
        
        Usuarios usuario = new Usuarios();
        UsersDAO usuarioDAO = new UsersDAO();
        
        listaCategorias = categoriaDAO.ListarCategorias();
        request.setAttribute("listaCategorias", listaCategorias);
        
        if(acao.equalsIgnoreCase("autenticar")){
            String usuarioLogin = "";
            String senha = "";
            
            usuarioLogin = request.getParameter("usuarioInput");
            senha = request.getParameter("senhaInput");
            
            usuario.setUsuario(usuarioLogin);
            usuario.setSenha(senha);
            
            Usuarios usuarioLogado = usuarioDAO.AutenticarUsuario(usuario);
            
            /*
             try ( PrintWriter out = response.getWriter()) {
                // TODO output your page here. You may use following sample code. 
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CategoryServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
                out.println("<h2>"+usuarioLogado+"</h2>");
                out.println("</body>");
                out.println("</html>");
            }
            */
            
            if(usuarioLogado.getAtivo() == 1){
                String pagina = "ArticleServlet?acao=listar";
                if(usuarioLogado.getNivelPermissao() == 2){
                    pagina = "Admin.jsp";
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(pagina);
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuarioLogado", usuarioLogado);
                request.setAttribute("usuarioLogado", usuarioLogado);
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                request.setAttribute("listaCategorias", listaCategorias);
                request.setAttribute("retorno", "Erro nas credenciais!");
                rd.forward(request, response);
            }
            
            
        }
        
        if(acao.equalsIgnoreCase("deslogar")){
            
            /**
             * Pega a session do usuário logado
             */
            HttpSession sessao = request.getSession();
            Usuarios usuarioLogado = new Usuarios();
            usuarioLogado = (Usuarios) sessao.getAttribute("usuarioLogado");
            request.setAttribute("usuarioLogado", usuarioLogado);
            
            
            if(usuarioLogado.getAtivo() == 1){
                sessao.removeAttribute("usuarioLogado");
                RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
                rd.forward(request, response);
            }
            
            
        }
        
        
        if(acao.equalsIgnoreCase("incluir")){
            
           
            String nome = "";
            String usuarioInput = "";
            String senha = "";
            String email = "";
            String idUsuario = "";
            String nivelPermissao = "";
                    
            boolean validacao = true;
            
            RequestDispatcher rd = request.getRequestDispatcher("UsersCreate.jsp");

            nome = request.getParameter("nomeInput");
            usuarioInput = request.getParameter("usuarioInput");
            email = request.getParameter("emailInput");
            nivelPermissao = request.getParameter("nivelPermissaoInput");
            idUsuario = request.getParameter("idUsuario");
            
            request.setAttribute("listaCategorias", listaCategorias);
            
            request.setAttribute("erro", 1);
            
            request.setAttribute("nome", nome);
            request.setAttribute("usuarioInput", usuarioInput);
            request.setAttribute("email", email);
            request.setAttribute("nivelPermissao", nivelPermissao);
            request.setAttribute("idUsuario", idUsuario);
            
            
            try {
                // Se algum campo não estiver preenchido, retorna erro
                if( 
                    nome == null || nome.isEmpty() ||
                    usuarioInput == null || usuarioInput.isEmpty() ||
                    email == null || email.isEmpty() ||
                    nivelPermissao == null || nivelPermissao.isEmpty()
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
                
               
                Usuarios usuarioCreate = new Usuarios();
                
                usuarioCreate.setNome(nome);
                usuarioCreate.setNivelPermissao(Integer.parseInt(nivelPermissao));
                usuarioCreate.setEmail(email);
                usuarioCreate.setUsuario(usuarioInput);
                
                if(idUsuario == ""){
                    idUsuario = "0";
                }
                usuarioCreate.setIdUsuario(Integer.parseInt(idUsuario));
                
                if(usuarioDAO.CadastrarUsuario(usuarioCreate)){
                    
                    listaUsuarios = usuarioDAO.ListarUsuarios();
                    request.setAttribute("listaCategorias", listaUsuarios);
                    
                    request.setAttribute("erro", 0);
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
        
        if(acao.equalsIgnoreCase("editar")){
            String idUsuario = request.getParameter("idUsuario");
            Usuarios usuarioShow = new Usuarios();
            try {
                usuarioShow = usuarioDAO.selecionarUsuario(idUsuario);
            } catch (ParseException ex) {
                Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
            String nome = usuarioShow.getNome();
            String usuarioInput = usuarioShow.getUsuario();
            String email = usuarioShow.getEmail();
            int nivelPermissao = usuarioShow.getNivelPermissao();
            
            RequestDispatcher rd = request.getRequestDispatcher("UsersCreate.jsp");
            listaUsuarios = usuarioDAO.ListarUsuarios();
            
            request.setAttribute("listaUsuarios", listaUsuarios);
            
            request.setAttribute("nome", nome);
            request.setAttribute("usuarioInput", usuarioInput);
            request.setAttribute("email", email);
            request.setAttribute("nivelPermissao", nivelPermissao);
            request.setAttribute("idUsuario", idUsuario);
            rd.forward(request, response);
        }
        
        
        if(acao.equalsIgnoreCase("listar")){
            RequestDispatcher rd = request.getRequestDispatcher("UsersCreate.jsp");
            listaUsuarios = usuarioDAO.ListarUsuarios();
            request.setAttribute("listaCategorias", listaCategorias);
            request.setAttribute("listaUsuarios", listaUsuarios);
            rd.forward(request, response);
        }
        
        if(acao.equalsIgnoreCase("excluir")){
            String idUsuario = request.getParameter("idUsuario");
            usuarioDAO.excluirUsuario(idUsuario);
            RequestDispatcher rd = request.getRequestDispatcher("UsersServlet?acao=listar");
           // rd = request.getRequestDispatcher("ArticleServlet?acao=listar");
            request.setAttribute("retorno", "Usuario excluída!");
            rd.forward(request, response);
            
        }
        
        /*
        try ( PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2>"+request.getParameter("nomeInput")+"</h2>");
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
