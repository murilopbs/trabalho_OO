package classes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Pai {

    private float rendimentosDespesas;

    public void setRendimentosDespesas(float rendimentosDespesas){
        this.rendimentosDespesas = rendimentosDespesas;
    }

    public float getRendimentosDespesas(){
        return rendimentosDespesas;
    }

    public void salvarDados(String dado1, String dado2, float dado3, String dado4, String caminho){
        try{
            FileWriter arq =  new FileWriter(caminho, true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(dado1 + ";"
                    + dado2 + ";"
                    + dado3);
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
