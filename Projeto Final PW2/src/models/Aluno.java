package models;

import projeto.pkgfinal.pw2.*;
import java.util.Scanner;

public class Aluno extends Pessoa {

    private String matricula;
    private String curso;
    private Avaliacao avaliacao[];
    private Boletim boletim[];

    Aluno(
            String nome,
            String cpf,
            String rg,
            String telefone,
            String endereco,
            int idade, 
            String matricula,
            String curso
    ) {
        super(nome, cpf, rg, telefone, endereco, idade);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    public Aluno(){
    }

    public Aluno(String matricula, String curso, projeto.pkgfinal.pw2.Avaliacao[] avaliacao, projeto.pkgfinal.pw2.Boletim[] boletim, String nome, String cpf, String rg, String telefone, String endereco, int idade) {
        super(nome, cpf, rg, telefone, endereco, idade);
        this.matricula = matricula;
        this.curso = curso;
    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Avaliacao getAvaliacao(int i) {
        return avaliacao[i];
    }

    public void setAvaliacao(Avaliacao avaliacao, int i) {
        this.avaliacao[i] = avaliacao;
    }

    public Boletim getBoletim(int i) {
        return boletim[i];
    }

    public void setBoletim(Boletim boletim, int i) {
        this.boletim[i] = boletim;
    }

}
