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


import model.Noticias;

/**
 *
 * @author nickm
 */
public class ArticleDAO {
    private Connection conn;
    
    public ArticleDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DATABASE_URL = "jdbc:mysql://localhost/portal_noticias";
            String usuario = "root";
            String senha = "";
            
            this.conn = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean CadastrarNoticia(Noticias noticia){
        
        int idNoticia = noticia.getIdNoticia();
        
        String sql = "INSERT INTO noticias(titulo, descricao, autor, texto, idCategoria) VALUES (?, ?, ?, ?, ?)";
        
        if(idNoticia > 0){
            sql = "UPDATE `noticias` SET `titulo`=?,`descricao`=?,`autor`=?,`texto`=?,`idCategoria`=? WHERE id = ?";
        }
    
        
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getDescricao());
            stmt.setString(3, noticia.getAutor());
            stmt.setString(4, noticia.getTexto());
            stmt.setInt(5, noticia.getIdCategoria());
            
            if(idNoticia > 0){
                stmt.setInt(6, idNoticia);
            }

            stmt.executeUpdate();
            return true;

        } catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Noticias> ListarNoticias(String... variaveis) throws ParseException{
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM noticias WHERE deletedAt IS NULL ORDER BY createdAt DESC";
        
        
        String idCategoria = "";
        for(String item : variaveis){
            idCategoria = item;
        }
        
        /**
         * Se tiver filtro de categoria,
         * edita o SQL
         */
        if(idCategoria.isEmpty() == false){
            sql = "SELECT * FROM noticias WHERE idCategoria = " + idCategoria + " AND deletedAt IS NULL  ORDER BY createdAt DESC";
        }
            
        
        List<Noticias> listaNoticias = new ArrayList<>();
        
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
                 * Formata a data de criação
                 */
                String dataEntrada = resultado.getString("createdAt");

                DateFormat formatadorEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dataBD = formatadorEntrada.parse(dataEntrada);
                DateFormat formatadorSaida = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                //DateFormat formatadorSaida = new SimpleDateFormat("MMMM")
                
                String dataFormatada = formatadorSaida.format(dataBD);
                
                
                /**
                 * Cria um objeto para cada linha
                 * da tabela produtos
                 * e adiciona na array listaProdutos
                 */
                Noticias noticia = new Noticias();
                noticia.setIdNoticia((resultado.getInt("id")));
                noticia.setTitulo(resultado.getString("titulo"));
                noticia.setDescricao(resultado.getString("descricao"));
                noticia.setAutor(resultado.getString("autor"));
                noticia.setTexto(resultado.getString("texto"));
                noticia.setIdCategoria(resultado.getInt("idCategoria"));
                

                noticia.setDataCriacao(dataFormatada);
                listaNoticias.add(noticia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        /**
         * Retorna o array listaProdutos
         */
        return listaNoticias;
    }
    
    public Noticias selecionarNoticia(String idNoticia) throws ParseException{
        /**
         * Cria uma string para comando SQL para 
         * pesquisar os produtos
         * cria uma array
         */
        String sql = "SELECT * FROM noticias WHERE id = " + idNoticia;
        
        Noticias noticia = new Noticias();
        
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
                 * Formata a data de criação
                 */
                String dataEntrada = resultado.getString("createdAt");

                DateFormat formatadorEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dataBD = formatadorEntrada.parse(dataEntrada);
                DateFormat formatadorSaida = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                //DateFormat formatadorSaida = new SimpleDateFormat("MMMM")
                
                String dataFormatada = formatadorSaida.format(dataBD);
                
                
                /**
                 * Cria um objeto para cada linha
                 * da tabela produtos
                 * e adiciona na array listaProdutos
                 */
                noticia.setIdNoticia((resultado.getInt("id")));
                noticia.setTitulo(resultado.getString("titulo"));
                noticia.setDescricao(resultado.getString("descricao"));
                noticia.setAutor(resultado.getString("autor"));
                noticia.setTexto(resultado.getString("texto"));
                noticia.setIdCategoria(resultado.getInt("idCategoria"));
                noticia.setDataCriacao(dataFormatada);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return noticia;

    }
    
    
    /**
     * Exclui um produto
     * @param idProduto
     * @return 
     */
    public boolean excluirNoticia(String idNoticia){
        String sql = "UPDATE `noticias` SET `deletedAt`=CURRENT_TIMESTAMP WHERE id = ?;";
        
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
            stmt.setString(1, idNoticia);
            // serve tanto para inserir novo dado como atualizar
            stmt.executeUpdate(); 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
