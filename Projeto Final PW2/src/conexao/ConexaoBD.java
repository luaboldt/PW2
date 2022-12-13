
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Aluno;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Anderson J. Lauer
 */
public class ConexaoBD {
    private Connection conn;

    public ConexaoBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DATABASE_URL = "jdbc:mysql://localhost/pw2";
            String usuario = "root";
            String senha = "";
            this.conn = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão");
        }
    }

    public boolean CadastrarAluno(Aluno aluno) {

        String sql = "INSERT INTO aluno(nome, idade, cpf) VALUES (?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCpf());
            
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Cadastro");
            return false;
        }
        

    }

    public List<Aluno> ListarAlunos() {
        String sql = "SELECT * FROM aluno";
        List<Aluno> listaalunos = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            
            
            while (resultado.next()) {
                Aluno aluno = new Aluno();
               // aluno.setIdAluno(resultado.getInt("idaluno"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setIdade(resultado.getInt("idade"));
                aluno.setCpf(resultado.getString("cpf"));
                listaalunos.add(aluno);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Alunos");
        }
        
        return listaalunos;
    }

    public boolean excluirAluno(Integer idaluno){
        String sql = "DELETE  FROM aluno WHERE idaluno=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idaluno);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir alunos");
            return false;
        }
        
    }
    
    public boolean alterarAluno(Aluno aluno) {
        
        String sql = "UPDATE aluno SET nome=?, idade=?, cpf=? WHERE idaluno=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCpf());
          
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados dos alunos");
            return false;
        }
    }
    
   public List<Aluno> buscarAluno(Integer idaluno)  {
        String sql = "SELECT * FROM aluno WHERE idaluno=?";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idaluno);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluno aluno = new Aluno();
                
              
                aluno.setNome(resultado.getString("nome"));
                aluno.setIdade(resultado.getInt("idade"));
                aluno.setCpf(resultado.getString("cpf"));
                retorno.add(aluno);
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno.");
        }
        return retorno;
    }
}
