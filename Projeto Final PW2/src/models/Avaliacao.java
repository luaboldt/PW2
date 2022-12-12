package models;

import projeto.pkgfinal.pw2.*;

public class Avaliacao {

    private int codigo;
    private String dataEntrega;
    private int peso;
    private float nota;

    Avaliacao(
            int codigo,
            String dataEntrega,
            int peso,
            float nota
    ) {
        this.codigo = codigo;
        this.dataEntrega = dataEntrega;
        this.peso = peso;
        this.nota = nota;
    }

    public String getDataentrega() {
        return dataEntrega;
    }

    public void setDataentrega(int dataentrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

}
