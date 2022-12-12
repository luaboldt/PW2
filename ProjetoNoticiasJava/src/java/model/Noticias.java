/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author nickm
 */
public class Noticias {
    private int idNoticia;
    private int idCategoria;
    private String titulo;
    private String nomeCategoria;
    private String autor;
    private String descricao;
    private String texto;
    private boolean cancelada;
    private String dataCriacao;
    private String dataAtualizacao;
    private String dataExclusao;

    public Noticias() {
    }

    public Noticias(int idNoticia, int idCategoria, String titulo, String nomeCategoria, String autor, String descricao, String texto, boolean cancelada, String dataCriacao, String dataAtualizacao, String dataExclusao) {
        this.idNoticia = idNoticia;
        this.idCategoria = idCategoria;
        this.titulo = titulo;
        this.nomeCategoria = nomeCategoria;
        this.autor = autor;
        this.descricao = descricao;
        this.texto = texto;
        this.cancelada = cancelada;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataExclusao = dataExclusao;
    }

    

    
    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    @Override
    public String toString() {
        return "Noticias{" + "idNoticia=" + idNoticia + ", idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", autor=" + autor + ", cancelada=" + cancelada + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", dataExclusao=" + dataExclusao + '}';
    }

    
    
    
    
}
