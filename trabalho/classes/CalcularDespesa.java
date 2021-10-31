package classes;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.IOException;

public class CalcularDespesa extends Pessoa {

    private float somadorRendimentos = 0;
    private float somadorDespesas = 0;
    private int moradores = 0;
    private String nome = "";
    private float rendimentos = 0;
    static CalcularDespesa[] calculardespesas = new CalcularDespesa[0];

    public CalcularDespesa(String pessoaNome, float pessoaRendimentos) throws Excecoes {
        super(pessoaNome, "a", pessoaRendimentos);
        //TODO Auto-generated constructor stub
    }

    public float[] calcularIgualitaria(){
        String strMes = JOptionPane.showInputDialog("Digite o mês da despesa em número:");
        if(strMes.length() == 1){
            strMes = "0" + strMes;
        }
        String strAno = JOptionPane.showInputDialog("Digite o ano da despesa:");
        try(
            FileReader fileReader =
            new FileReader("bancodedados/alunos.txt")){
            int data = fileReader.read();
            int contador = 0;
            String juntador = "";
            while(data != -1) {
                if(contador == 0){
                    while(data != ';'){
                        juntador = juntador + (char)data;
                        data = fileReader.read();
                    }
                    nome = juntador;
                    juntador = "";
                }
                if(data == ';'){
                    contador += 1;
                }
                if(contador == 2){
                    while(data != '\n'){
                        data = fileReader.read();
                        juntador = juntador + (char)data;
                    }
                    if(data == '\n'){
                        moradores += 1;
                    }
                    rendimentos = Float.parseFloat(juntador);
                    somadorRendimentos = somadorRendimentos + Float.parseFloat(juntador);
                    juntador = "";
                    contador = 0;
                    try{
                        CalcularDespesa c = new CalcularDespesa(nome, rendimentos);
                        CalcularDespesa[] tempCalcularDespesas = new CalcularDespesa[calculardespesas.length + 1];
                        for(int i = 0; i < calculardespesas.length; i++){
                            tempCalcularDespesas[i] = calculardespesas[i];
                        }
                        tempCalcularDespesas[calculardespesas.length] = c;
                        calculardespesas = tempCalcularDespesas;
                    }catch(Excecoes e){
                        System.out.println(e.getMensagem());
                    }
                }
                //System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try(
            FileReader fileReader =
            new FileReader("bancodedados/despesas_"+ strMes + "_" + strAno +".txt")){
            int data = fileReader.read();
            int contador = 0;
            String juntador = "";
            while(data != -1) {
                if(data == ';'){
                    contador += 1;
                }
                if(contador == 2){
                    while(data != '\n'){
                        data = fileReader.read();
                        juntador = juntador + (char)data;
                    }
                    somadorDespesas = somadorDespesas + Float.parseFloat(juntador);
                    juntador = "";
                    contador = 0;
                }
                //System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        float[] resposta = new float[2];
        resposta[0] = moradores;
        resposta[1] = somadorDespesas;
        return new float[] {resposta[0], resposta[1]};
    }

    public String calcularProporcional(){
        String strMes = JOptionPane.showInputDialog("Digite o mês da despesa em número:");
        if(strMes.length() == 1){
            strMes = "0" + strMes;
        }
        String strAno = JOptionPane.showInputDialog("Digite o ano da despesa:");
        try(
            FileReader fileReader =
            new FileReader("bancodedados/alunos.txt")){
            int data = fileReader.read();
            int contador = 0;
            String juntador = "";
            while(data != -1) {
                if(contador == 0){
                    while(data != ';'){
                        juntador = juntador + (char)data;
                        data = fileReader.read();
                    }
                    nome = juntador;
                    juntador = "";
                }
                if(data == ';'){
                    contador += 1;
                }
                if(contador == 2){
                    while(data != '\n'){
                        data = fileReader.read();
                        juntador = juntador + (char)data;
                    }
                    if(data == '\n'){
                        moradores += 1;
                    }
                    rendimentos = Float.parseFloat(juntador);
                    somadorRendimentos = somadorRendimentos + Float.parseFloat(juntador);
                    juntador = "";
                    contador = 0;
                    try{
                        CalcularDespesa c = new CalcularDespesa(nome, rendimentos);
                        CalcularDespesa[] tempCalcularDespesas = new CalcularDespesa[calculardespesas.length + 1];
                        for(int i = 0; i < calculardespesas.length; i++){
                            tempCalcularDespesas[i] = calculardespesas[i];
                        }
                        tempCalcularDespesas[calculardespesas.length] = c;
                        calculardespesas = tempCalcularDespesas;
                    }catch(Excecoes e){
                        System.out.println(e.getMensagem());
                    }
                }
                //System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try(
            FileReader fileReader =
            new FileReader("bancodedados/despesas_"+ strMes + "_" + strAno +".txt")){

            int data = fileReader.read();
            int contador = 0;
            String juntador = "";
            while(data != -1) {
                if(data == ';'){
                    contador += 1;
                }
                if(contador == 2){
                    while(data != '\n'){
                        data = fileReader.read();
                        juntador = juntador + (char)data;
                    }
                    somadorDespesas = somadorDespesas + Float.parseFloat(juntador);
                    juntador = "";
                    contador = 0;
                }
                //System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String resultado = "";
        for(int i = 0; i < calculardespesas.length; i++){
            float calculo = somadorDespesas/somadorRendimentos;
            calculo =  calculo * calculardespesas[i].getRendimentos();
            resultado = resultado + calculardespesas[i].getNome()+ "->" + "R$" + calculo + "->" + ((calculo / somadorDespesas) * 100) +"%\n"; 
        }
        resultado = resultado + "Total das despesas: R$" + somadorDespesas;
        return resultado;
    }
}
