/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nickm
 */
public class Categorias {
    private int idCategoria;
    private String nomeCategoria;
    private String descricaoCategoria;
    private boolean categoriaAtiva;
    private int idUsuarioCriador;

    public Categorias() {
    }

    public Categorias(int idCategoria, String nomeCategoria, String descricaoCategoria, boolean categoriaAtiva, int idUsuarioCriador) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
        this.categoriaAtiva = categoriaAtiva;
        this.idUsuarioCriador = idUsuarioCriador;
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

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public boolean isCategoriaAtiva() {
        return categoriaAtiva;
    }

    public void setCategoriaAtiva(boolean categoriaAtiva) {
        this.categoriaAtiva = categoriaAtiva;
    }

    public int getIdUsuarioCriador() {
        return idUsuarioCriador;
    }

    public void setIdUsuarioCriador(int idUsuarioCriador) {
        this.idUsuarioCriador = idUsuarioCriador;
    }

    @Override
    public String toString() {
        return "Categorias{" + "idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", descricaoCategoria=" + descricaoCategoria + ", categoriaAtiva=" + categoriaAtiva + ", idUsuarioCriador=" + idUsuarioCriador + '}';
    }
    
    
    
    
}
