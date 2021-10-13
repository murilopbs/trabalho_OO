package classes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Despesa extends Pai {
    private String mesDespesa ;
    private String anoDespesa;
    private String nomeDespesa;
    private String categoriaDespesa;
    private String nomeSubcategoria;
    public Despesa(String mesDespesa, String anoDespesa, String nomeDespesa, String categoriaDespesa, String nomeSubcategoria, float valorDespesa) throws Excecoes{
        this.mesDespesa  = mesDespesa;
        this.anoDespesa = anoDespesa;
        if(nomeDespesa.isEmpty()){
            throw new Excecoes("DescricaoNaoInformadaException"); 
        }else{
            this.nomeDespesa = nomeDespesa;
        }
        this.categoriaDespesa = categoriaDespesa;
        this.nomeSubcategoria = nomeSubcategoria;
        this.setRendimentosDespesas(valorDespesa);;
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
    public String getNomeSubcategoria(){
        return nomeSubcategoria;
    }
    public float getValorDespesa(){
        return getRendimentosDespesas();
    }
    @Override
    public void salvarDados(String dado1, String dado2, float dado3, String dado4, String caminho){
        try{
            FileWriter arq =  new FileWriter(caminho, true);
                                PrintWriter gravarArq = new PrintWriter(arq);
                                gravarArq.println(dado1 + ";" //nome despesa
                                + dado2 + ";" //categoria despesa
                                + dado4 + ";" //nome Subcategoria
                                + dado3); //valor despesa
                                gravarArq.close();
        }catch(IOException e){
            int voltar = JOptionPane.showConfirmDialog(
                    null, 
                    e.getMessage() + "\nDeseja tentar cadastrar novamente?",
                    "Continua",
                    JOptionPane.YES_NO_OPTION
                    );
            if(voltar == 1){
                System.exit(0);
            }
        }
    }
}
