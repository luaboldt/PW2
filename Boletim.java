package projeto.pkgfinal.pw2;

public class Boletim {
    private int codigo;
    private int semestre;
    private float notas[];
    private float media;
    private boolean resultado;


        
    Boletim(int codigo, int semestre, float notas[], float media, boolean resultado){
        this.codigo = codigo;
        this.semestre = semestre;
        for(int i = 0; i < notas.length; i++){
            this.notas[i] = notas[i];
        }
        this.media = media;
        this.resultado = resultado;
    }
    
    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public float getNotas() {
        return notas;
    }

    public void setNotas(float notas) {
        this.notas = notas;
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

    public char getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(char disciplina) {
        this.disciplina = disciplina;
    }
    
     
}
