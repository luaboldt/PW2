/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nickm
 */
public class Usuarios {
    private int idUsuario;
    private String usuario;
    private String senha;
    private String nome;
    private String email;
    private int nivelPermissao;
    private int ativo;

    public Usuarios() {
    }

    public Usuarios(int idUsuario, String usuario, String senha, String nome, String email, int nivelPermissao, int ativo) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.nivelPermissao = nivelPermissao;
        this.ativo = ativo;
    }

    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(int nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", senha=" + senha + ", nome=" + nome + ", email=" + email + ", nivelPermissao=" + nivelPermissao + ", ativo=" + ativo + '}';
    }
    
    

    
    

    
    
    
    
}
