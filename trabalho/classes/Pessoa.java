package classes;

public class Pessoa extends SalvarDados{
    private String nome ;
    private String email;
    public Pessoa(String pessoaNome, String pessoaEmail, float pessoaRendimentos) throws Excecoes{
        if(pessoaNome.isEmpty() | pessoaEmail.isEmpty()){
            throw new Excecoes("DadosPessoaisIncompletos");
        }else if(pessoaRendimentos < 0){
            throw new Excecoes("RendimentoInvalidoException");
        }else{
            this.nome  = pessoaNome ;
            this.email = pessoaEmail;
            this.setRendimentosDespesas(pessoaRendimentos);
        }
    }
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public float getRendimentos(){
        return this.getRendimentosDespesas();
    }
}
