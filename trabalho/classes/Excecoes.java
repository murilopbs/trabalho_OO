package classes;

public class Excecoes extends Exception{
    private String mensagem;
    public Excecoes(String mensagem){
        this.setMensagem(mensagem);
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
