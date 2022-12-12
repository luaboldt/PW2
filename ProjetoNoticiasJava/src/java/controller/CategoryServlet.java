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

import model.Noticias;
import model.Categorias;
import DAO.CategoryDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import model.Categorias;
import model.Usuarios;

/**
 *
 * @author nickm
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

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
        
        Categorias categoria = new Categorias();
        CategoryDAO categoriaDAO = new CategoryDAO();
        
        listaCategorias = categoriaDAO.ListarCategorias();
        request.setAttribute("listaCategorias", listaCategorias);
        
        /**
         * Pega a session do usuário logado
         */
        HttpSession sessao = request.getSession();
        Usuarios usuarioLogado = new Usuarios();
        usuarioLogado = (Usuarios) sessao.getAttribute("usuarioLogado");
        request.setAttribute("usuarioLogado", usuarioLogado);
        
        if(usuarioLogado.getNivelPermissao() == 2){
            if(acao.equalsIgnoreCase("incluir")){


                String nome = "";
                String descricao = "";

                boolean validacao = true;

                RequestDispatcher rd = request.getRequestDispatcher("CategoryCreate.jsp");

                nome = request.getParameter("nomeInput");
                descricao = request.getParameter("descricaoInput");

                request.setAttribute("erro", 1);

                try {
                    // Se algum campo não estiver preenchido, retorna erro
                    if( 
                        nome == null || nome.isEmpty() ||
                        descricao == null || descricao.isEmpty() 
                    ){
                        request.setAttribute("retorno", "Não foram preenchidos todos os campos!");
                        request.setAttribute("nome", nome);
                        request.setAttribute("descricao", descricao);
                        validacao = false;
                        rd.forward(request, response);
                    }

                }catch(NumberFormatException e){
                    validacao = false;
                    request.setAttribute("retorno", "Apenas números não são aceitos");
                    request.setAttribute("nome", nome);
                    request.setAttribute("descricao", descricao);
                    rd.forward(request, response);
                }


                if(validacao == true){


                    categoria.setNomeCategoria(nome);
                    categoria.setDescricaoCategoria(descricao);

                    if(categoriaDAO.CadastrarCategoria(categoria)){

                        listaCategorias = categoriaDAO.ListarCategorias();
                        request.setAttribute("listaCategorias", listaCategorias);

                        request.setAttribute("erro", 0);
                        request.setAttribute("retorno", "Cadastro efetuado com sucesso!");
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("nome", nome);
                        request.setAttribute("descricao", descricao);
                        request.setAttribute("retorno", "Não foi possível efetuar o cadastro");
                        rd.forward(request, response);
                    }

                } else {
                    request.setAttribute("nome", nome);
                    request.setAttribute("descricao", descricao);
                    request.setAttribute("retorno", "Não foi possível efetuar o cadastro");
                    rd.forward(request, response);
                }

            }


            if(acao.equalsIgnoreCase("listar")){
                RequestDispatcher rd = request.getRequestDispatcher("CategoryCreate.jsp");

                listaCategorias = categoriaDAO.ListarCategorias();
                request.setAttribute("listaCategorias", listaCategorias);
                rd.forward(request, response);

            }

            if(acao.equalsIgnoreCase("excluir")){
                String idCategoria = request.getParameter("idCategoria");
                categoriaDAO.excluirCategoria(idCategoria);
                RequestDispatcher rd = request.getRequestDispatcher("CategoryServlet?acao=listar");
               // rd = request.getRequestDispatcher("ArticleServlet?acao=listar");
                request.setAttribute("retorno", "Categoria excluída!");
                rd.forward(request, response);

            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
            request.setAttribute("retorno", "Sem permissão para acessar esta área!");
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
