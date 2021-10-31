package classes;

public class Despesa extends SalvarDados {
    private String mesDespesa ;
    private String anoDespesa;
    private String nomeDespesa;
    private String categoriaDespesa;
    private SubCategoria subcategoria;
    private Categoria categoria;

    public Despesa(String mesDespesa, String anoDespesa, String nomeDespesa, String categoriaDespesa, String nomeSubcategoria, float valorDespesa) throws Excecoes{
        this.mesDespesa  = mesDespesa;
        this.anoDespesa = anoDespesa;
        if(nomeDespesa.isEmpty()){
            throw new Excecoes("DescricaoNaoInformadaException"); 
        }else{
            this.nomeDespesa = nomeDespesa;
        }
        if(categoriaDespesa.isEmpty()){
            throw new Excecoes("CategoriaNaoInformadaException");
        }else{
            this.categoriaDespesa = categoriaDespesa;
        }
        if(valorDespesa > 0 ){
            this.setRendimentosDespesas(valorDespesa);
        }else{
            throw new Excecoes("ValorNaoInformadoException");
        }
        
    }

    SubCategoria getSubCategoria(){
        return subcategoria;
    }

    public void setCategoria(String categoriaDespesa, String nomeDespesa){
        categoria.setCategoriaDespesa(categoriaDespesa);
        categoria.setNomeDespesa(nomeDespesa);
    }

    Categoria getCategoria(){
        return categoria;
    }

    public String getMesDespesa(){
        return mesDespesa;
    }
    public String getAnoDespesa(){
        return anoDespesa;
    }
    public String getNomeDespesa(){
        return nomeDespesa;
    }
    public String getCategoriaDespesa(){
        return categoriaDespesa;
    }
    public float getValorDespesa(){
        return getRendimentosDespesas();
    }
}
