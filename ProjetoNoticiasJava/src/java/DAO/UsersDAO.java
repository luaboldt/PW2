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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import model.Categorias;
import model.Usuarios;

/**
 *
 * @author nickm
 */
public class UsersDAO {
    private Connection conn;
    
    public UsersDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DATABASE_URL = "jdbc:mysql://localhost/portal_noticias";
            String usuario = "root";
            String senha = "";
            
            this.conn = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuarios AutenticarUsuario(Usuarios usuario){
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        
        Usuarios usuarioLogado = new Usuarios();
        
        try {
            /**
             * Executa o comando SQL 
             * e armazena os dados na variável
             * ResultSet
             */
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
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
                usuarioLogado.setIdUsuario((resultado.getInt("idUsuario")));
                usuarioLogado.setUsuario(resultado.getString("usuario"));
                usuarioLogado.setSenha(resultado.getString("senha"));
                usuarioLogado.setNome(resultado.getString("nome"));
                usuarioLogado.setEmail(resultado.getString("email"));
                usuarioLogado.setNivelPermissao(resultado.getInt("nivelPermissao"));
                usuarioLogado.setAtivo(resultado.getInt("ativo"));
                return usuarioLogado;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return usuarioLogado;
    }
    
    public boolean CadastrarUsuario(Usuarios usuario){
        String sql = "INSERT INTO usuarios(usuario, nome, email, nivelPermissao, ativo) VALUES (?, ?, ?, ?, ?)";
        
        if(usuario.getIdUsuario() > 0){
            sql = "UPDATE `usuarios` SET `usuario`=?, `nome`= ?,`email`=?,`nivelPermissao`=? WHERE idUsuario = ?";
        }
        //UPDATE `usuarios` SET `usuario`=?,`senha`=?,`nome`=,`email`=?,`nivelPermissao`=?,`ativo`= WHERE idUsuario = ?
    
        try {
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getNivelPermissao());
            
            if(usuario.getIdUsuario() > 0){
                stmt.setInt(5, usuario.getIdUsuario());
            } else {
                stmt.setInt(5, usuario.getAtivo());
            }
            
            stmt.executeUpdate();
            return true;
            
        } catch(SQLException ex){
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Usuarios> ListarUsuarios(){
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM usuarios";
        List<Usuarios> listaUsuarios = new ArrayList<>();
        
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
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario((resultado.getInt("idUsuario")));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setNivelPermissao(resultado.getInt("nivelPermissao"));
                usuario.setAtivo(resultado.getInt("ativo"));
                
                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        /**
         * Retorna o array listaProdutos
         */
        return listaUsuarios;
    }
    
    public Usuarios selecionarUsuario(String idUsuario) throws ParseException{
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM usuarios WHERE idUsuario = " + idUsuario;
        
        Usuarios usuario = new Usuarios();
        
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
                usuario.setIdUsuario((resultado.getInt("idUsuario")));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setNivelPermissao(resultado.getInt("nivelPermissao"));
                usuario.setAtivo(resultado.getInt("ativo"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return usuario;

    }
    
    /**
     * Exclui uma usuario
     * @param idUsuario
     * @return 
     */
    public boolean excluirUsuario(String idUsuario){
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        
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
            stmt.setString(1, idUsuario);
            // serve tanto para inserir novo dado como atualizar
            stmt.executeUpdate(); 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
