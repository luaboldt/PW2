package projeto.pkgfinal.pw2;

import java.util.Scanner;

public class Professor extends Pessoa {
    private int codigo;
    private String formacao;

    Professor(String nome, String cpf, String rg, String telefone, String endereco, int idade, int codigo, String formacao) {
        super(nome, cpf, rg, telefone, endereco, idade);
        this.codigo = codigo;
        this.formacao = formacao;
    }
    
    Professor() {
        super();
        
        Scanner input = new Scanner(System.in);
        String  vFormacao;
        int vCodigo;

        System.out.printf("Informe seu codigo: ");
        vCodigo = input.nextInt();
        System.out.printf("Informe sua formacao: ");
        vFormacao = input.next();
        
        this.codigo = vCodigo;
        this.formacao = vFormacao;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
         
    }
    
}
