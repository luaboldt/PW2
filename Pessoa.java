package projeto.pkgfinal.pw2;

import java.util.Scanner;

public class Pessoa {
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String endereco;
    private int idade;


    public Pessoa(String nome, String cpf, String rg, String telefone, String endereco, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idade = idade;
    }
    
    Pessoa() {
        Scanner input = new Scanner(System.in);
        String vNome, vCpf, vRg, vTelefone, vEndereco;
        int vIdade;
        
        System.out.printf("Informe seu nome: ");
        vNome = input.next();
        System.out.printf("Informe seu CPF: ");
        vCpf = input.next();
        System.out.printf("Informe seu RG: ");
        vRg = input.next();
        System.out.printf("Informe seu telefone: ");
        vTelefone = input.next();
        System.out.printf("Informe seu Endereço: ");
        vEndereco = input.next();
        System.out.printf("Informe sua idade: ");
        vIdade = input.nextInt();
        
        this.nome = vNome;
        this.cpf = vCpf;
        this.rg = vRg;
        this.telefone = vTelefone;
        this.endereco = vEndereco;
        this.idade = vIdade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
