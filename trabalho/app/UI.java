package app;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.IOException;
import classes.Pessoa;
import classes.Despesa;
import classes.Excecoes;
import classes.CalcularDespesa;

public class UI {
    static Pessoa[] pessoas = new Pessoa[0];
    static Despesa[] despesas = new Despesa[0];
    static CalcularDespesa[] calculardespesas = new CalcularDespesa[0];
    public static void main(String[] args){
        int opcao;
        int pessoaNovamente = 0;
        int despesaNovamente;
        int dadosIncompletos = 1;
        do{
            String strOpcao = JOptionPane.showInputDialog("Escolha uma opção: \n"
            + "1 - Cadastrar Pessoa\n"
            + "2 - Cadastrar Despesa\n"
            + "3 - Calcular Despesas\n"
            + "0 - Sair");
            opcao = Integer.parseInt(strOpcao);
            switch(opcao){
                case 1:
                    quebrar: do{
                        String nomeDaPessoa = JOptionPane.showInputDialog("Digite o nome da pessoa:");
                        String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa:");
                        String strRendimentosDaPessoa = JOptionPane.showInputDialog("Digite os rendimentos da pessoa:");
                        float rendimentosDaPessoa = Float.parseFloat(strRendimentosDaPessoa);
                        try{
                            Pessoa p = new Pessoa(nomeDaPessoa, emailDaPessoa, rendimentosDaPessoa);
                            Pessoa[] temp = new Pessoa[pessoas.length + 1];
                            for(int i = 0; i < pessoas.length; i++){
                                temp[i] = pessoas[i];
                            }
                            temp[pessoas.length] = p;
                            pessoas = temp;
                        }catch(Excecoes e){
                            dadosIncompletos = JOptionPane.showConfirmDialog(
                                null, 
                                e.getMensagem() + "\n" 
                                + "Deseja tentar cadastrar novamente?",
                                "Continua",
                                JOptionPane.YES_NO_OPTION
                                );
                            if(dadosIncompletos == 1){
                                break quebrar;
                            }
                        }
                        pessoaNovamente = 1;
                        if(dadosIncompletos == 1){
                            pessoaNovamente = JOptionPane.showConfirmDialog(
                            null, 
                            "Deseja cadastrar uma nova pessoa?",
                            "Continua",
                            JOptionPane.YES_NO_OPTION
                            );
                        }
                    }while(pessoaNovamente == 0);
                    for(int i = 0; i <= pessoas.length; i++){
                        pessoas[i].salvarDados(pessoas[i].getNome(), pessoas[i].getEmail(), pessoas[i].getRendimentos(), "", "bancodedados/alunos.txt");
                    }
                    

                case 2:
                    do{
                        String strMes = JOptionPane.showInputDialog("Digite o mês da despesa em número:");
                        if(strMes.length() == 1){
                            strMes = "0" + strMes;
                        }
                        String strAno = JOptionPane.showInputDialog("Digite o ano da despesa:");
                        String nomeDespesa = JOptionPane.showInputDialog("Digite a descrição da despesa:");
                        String categoriaDespesa = JOptionPane.showInputDialog("Digite o nome da categoria da despesa:");
                        int intSubcategoria = JOptionPane.showConfirmDialog(
                            null, 
                            "A despesa possui uma subcategoria?",
                            "Continua",
                            JOptionPane.YES_NO_OPTION
                        );
                        String nomeSubcategoria = "";
                        if(intSubcategoria == 0){
                            nomeSubcategoria = JOptionPane.showInputDialog("Digite o nome da subcategoria da despesa:");
                        }
                        String strValorDespesas = JOptionPane.showInputDialog("Digite o valor da despesa:");
                        float valorDespesas = Float.parseFloat(strValorDespesas);
                        try{
                            Despesa d = new Despesa(strMes, strAno, nomeDespesa, categoriaDespesa, nomeSubcategoria, valorDespesas);
                            Despesa[] tempDespesas = new Despesa[despesas.length + 1];
                            for(int i = 0; i < despesas.length; i++){
                                tempDespesas[i] = despesas[i];
                            }
                            tempDespesas[despesas.length] = d;
                            despesas = tempDespesas;
                        }catch(Excecoes e){
                            dadosIncompletos = JOptionPane.showConfirmDialog(
                                null, 
                                e.getMensagem() + "\n" 
                                + "Deseja tentar cadastrar novamente?",
                                "Continua",
                                JOptionPane.YES_NO_OPTION
                            );
                        }
                        despesaNovamente = 1;
                        if(dadosIncompletos == 0){
                            despesaNovamente = JOptionPane.showConfirmDialog(
                            null, 
                            "Deseja cadastrar uma nova despesa?",
                            "Continua",
                            JOptionPane.YES_NO_OPTION
                            );
                        }
                    }while(despesaNovamente == 0);
                    String caminho;
                    for(int i = 0; i < despesas.length; i++){
                        caminho = "bancodedados/despesas_"+ despesas[i].getMesDespesa() + "_" + despesas[i].getAnoDespesa() +".txt";
                        despesas[i].salvarDados(despesas[i].getNomeDespesa(), despesas[i].getCategoriaDespesa(), despesas[i].getValorDespesa(), despesas[i].getNomeSubcategoria(), caminho);
                    }
                    break;
                
                case 3:
                    float somadorRendimentos = 0;
                    float somadorDespesas = 0;
                    int moradores = 0;
                    String nome = "";
                    float rendimentos = 0;
                    quebrar: do{
                        String strOpcao3 = JOptionPane.showInputDialog("Deseja calcular pela: \n"
                            + "1 - Regra igualitária\n"
                            + "2 - Regra proporcional\n"
                            + "0 - Sair");
                        int opcao3 = Integer.parseInt(strOpcao3);
                        switch (opcao3){
                            case 1:
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
                                    if(contador == 3){
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
                                int igualitariaNovamente = JOptionPane.showConfirmDialog(
                                    null, 
                                    "Cada morador pagará: \nR$" + (somadorDespesas/moradores) + "->" + ((somadorDespesas/moradores)/somadorDespesas) * 100 +"%\n" + "Deseja calcular novamente?",
                                    "Continua",
                                    JOptionPane.YES_NO_OPTION
                                    );
                                if(igualitariaNovamente == 0){
                                    opcao3 = 1;
                                }else{
                                    break quebrar;
                                }
                                break;

                            case 2:
                                strMes = JOptionPane.showInputDialog("Digite o mês da despesa em número:");
                                if(strMes.length() == 1){
                                    strMes = "0" + strMes;
                                }
                                strAno = JOptionPane.showInputDialog("Digite o ano da despesa:");
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
                                        if(contador == 3){
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
                                int proporcionalNovamente = JOptionPane.showConfirmDialog(
                                    null, 
                                    "Os moradores pagarão: \n" + resultado + "\n" + "Deseja calcular novamente?",
                                    "Continua",
                                    JOptionPane.YES_NO_OPTION
                                    );
                                if(proporcionalNovamente == 0){
                                    opcao3 = 1;
                                }else{
                                    break quebrar;
                                }
                                break;

                            case 0:
                                break quebrar;
                            
                            default:
                                break;

                            
                        }
                        
                        //break;
                    }while(true);
                    
                

                case 0:
                    break;
                
                default:
                    int encerrar = JOptionPane.showConfirmDialog(
                        null, 
                        "Você não digitou uma opção válida, deseja tentar novamente?",
                        "Continua",
                        JOptionPane.YES_NO_OPTION
                        );
                        if(encerrar == 1){
                            System.exit(0);
                        }
                    

            }
        }while(opcao != 0);
    }
}
