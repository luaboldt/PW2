package projeto.pkgfinal.pw2;

import java.util.Scanner;

public class Aluno extends Pessoa{
    private String matricula;
    private String curso;
    
    Aluno(String nome, String cpf, String rg, String telefone, String endereco, int idade, String matricula, String curso) {
        super(nome, cpf, rg, telefone, endereco, idade);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    Aluno() {
        
        super();
        
        Scanner input = new Scanner(System.in);
        String  vMatricula, vCurso;

        System.out.printf("Informe sua matricula: ");
        vMatricula = input.next();
        System.out.printf("Informe seu curso: ");
        vCurso = input.next();
       
        this.matricula = vMatricula;
        this.curso = vCurso;
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
    
}
