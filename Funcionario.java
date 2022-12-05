package projeto.pkgfinal.pw2;

import java.util.Scanner;

public class Funcionario extends Pessoa{

    private float salario;
    private String funcao;
    private String dataContratacao;

    Funcionario(String nome, String cpf, String rg, String telefone, String endereco, int idade, float salario, String funcao, String dataContratacao) {
    super(nome, cpf, rg, telefone, endereco, idade);
    this.salario = salario;
    this.funcao = funcao;
    this.dataContratacao = dataContratacao;
    }
    
    Funcionario() {
        
        super();
        
        Scanner input = new Scanner(System.in);
        String  vFuncao, vDataContratacao;
        float vSalario;

        System.out.printf("Informe seu salario: ");
        vSalario = input.nextFloat();
        System.out.printf("Informe sua funcao: ");
        vFuncao = input.next();
        System.out.printf("Informe a data da contratacao: ");
        vDataContratacao = input.next();
       
        this.salario = vSalario;
        this.funcao = vFuncao;
        this.dataContratacao = vDataContratacao;
    }
    
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
    
     public void cadastrarAluno(){
    
     }
     public void retirarAluno(){
    
     }
      public void alterarDados(){
    
     }
}

