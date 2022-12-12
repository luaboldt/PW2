package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import model.Categorias;
import model.Noticias;

/**
 *
 * @author nickm
 */
public class CategoryDAO {
    private Connection conn;
    
    public CategoryDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DATABASE_URL = "jdbc:mysql://localhost/portal_noticias";
            String usuario = "root";
            String senha = "";
            
            this.conn = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean CadastrarCategoria(Categorias categoria){
        String sql = "INSERT INTO categorias(nome, descricao) VALUES (?, ?)";
    
        
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricaoCategoria());
            
            stmt.executeUpdate();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
        
        
       
    }
    
    /**
     * Busca todos os produtos e lista eles em uma array
     * Para utilizar List é necessário importar bibliotecas
     * 
     * @return 
     */
    public List<Categorias> ListarCategorias(){
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM categorias";
        List<Categorias> listaCategorias = new ArrayList<>();
        
        try {
            /**
             * Executa o comando SQL 
             * e armazena os dados na variável
             * ResultSet
             */
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            /**
             * Pra cada um dos resultados
             */
            while(resultado.next()){
                
                /**
                 * Cria um objeto para cada linha
                 * da tabela produtos
                 * e adiciona na array listaProdutos
                 */
                Categorias categoria = new Categorias();
                categoria.setIdCategoria((resultado.getInt("idCategoria")));
                categoria.setNomeCategoria(resultado.getString("nome"));
                categoria.setDescricaoCategoria(resultado.getString("descricao"));
                listaCategorias.add(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        /**
         * Retorna o array listaProdutos
         */
        return listaCategorias;
    }
    
    
    public List<Noticias> ListarCategorias2(){
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM noticias";
        List<Noticias> listaCategorias = new ArrayList<>();
        
        try {
            /**
             * Executa o comando SQL 
             * e armazena os dados na variável
             * ResultSet
             */
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            /**
             * Pra cada um dos resultados
             */
            while(resultado.next()){
                
                /**
                 * Cria um objeto para cada linha
                 * da tabela produtos
                 * e adiciona na array listaProdutos
                 */
                Noticias noticia = new Noticias();
                noticia.setIdCategoria((resultado.getInt("idCategoria")));
                noticia.setTitulo(resultado.getString("titulo"));
                
                listaCategorias.add(noticia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        /**
         * Retorna o array listaProdutos
         */
        return listaCategorias;
    }
    
    
    /**
     * Exclui uma categoria
     * @param idCategoria
     * @return 
     */
    public boolean excluirCategoria(String idCategoria){
        String sql = "DELETE FROM categorias WHERE idCategoria = ?";
        
        try {
            /**
             * Prepara um sql
             * para setar as variáveis
             * no sql parametrizado
             */
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            /**
             * setString(ordem_do_interrogacao, valor)
             * parametriza as variáveis
             */
            stmt.setString(1, idCategoria);
            // serve tanto para inserir novo dado como atualizar
            stmt.executeUpdate(); 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
