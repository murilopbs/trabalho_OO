package app;
import javax.swing.JOptionPane;
import classes.Pessoa;
import classes.SubCategoria;
import classes.Despesa;
import classes.Excecoes;
import classes.CalcularDespesa;
import classes.Categoria;

public class UI {
    static Pessoa[] pessoas = new Pessoa[0];
    static Despesa[] despesas = new Despesa[0];
    static CalcularDespesa[] calculardespesas = new CalcularDespesa[0];
    static Categoria[] categoria = new Categoria[0];
    static SubCategoria[] subcategoria = new SubCategoria[0];
    public static void main(String[] args){
        int opcao;
        int pessoaNovamente = 0;
        int despesaNovamente;
        int dadosIncompletos = 0;
        do{
            String strOpcao = JOptionPane.showInputDialog("Escolha uma opção: \n"
            + "1 - Cadastrar Pessoa\n"
            + "2 - Cadastrar Despesa\n"
            + "3 - Calcular Despesas\n"
            + "0 - Sair");
            opcao = Integer.parseInt(strOpcao);
            switch(opcao){
                case 1:
                    do{
                        String nomeDaPessoa = JOptionPane.showInputDialog("Digite o nome da pessoa:");
                        String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa:");
                        String strRendimentosDaPessoa = JOptionPane.showInputDialog("Digite os rendimentos da pessoa:");
                        float rendimentosDaPessoa;
                        if(strRendimentosDaPessoa.isEmpty() | strRendimentosDaPessoa.isBlank()){
                            rendimentosDaPessoa = -1;
                        }else{
                            rendimentosDaPessoa = Float.parseFloat(strRendimentosDaPessoa);
                        }
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
                        }
                        pessoaNovamente = 1;
                        if(dadosIncompletos == 0){
                            pessoaNovamente = JOptionPane.showConfirmDialog(
                            null, 
                            "Deseja cadastrar uma nova pessoa?",
                            "Continua",
                            JOptionPane.YES_NO_OPTION
                            );
                        }
                    }while(pessoaNovamente == 0);
                    for(int i = 0; i <= pessoas.length - 1; i++){
                        pessoas[i].salvarDados(pessoas[i].getNome(), pessoas[i].getEmail(), pessoas[i].getRendimentos(), "bancodedados/alunos.txt");
                    }
                    break;
                    

                case 2:
                    do{
                        String strMes = JOptionPane.showInputDialog("Digite o mês da despesa em número:");
                        if(strMes.length() == 1){
                            strMes = "0" + strMes;
                        }
                        String strAno = JOptionPane.showInputDialog("Digite o ano da despesa:");
                        String nomeDespesa = JOptionPane.showInputDialog("Digite a descrição da despesa:");
                        String categoriaDespesa = JOptionPane.showInputDialog("Digite o nome da categoria da despesa:");
                        Categoria k = new Categoria();
                        k.setCategoriaDespesa(categoriaDespesa);
                        k.setNomeDespesa(nomeDespesa);
                        int intSubcategoria = JOptionPane.showConfirmDialog(
                            null, 
                            "A despesa possui uma subcategoria?",
                            "Continua",
                            JOptionPane.YES_NO_OPTION
                        );
                        String nomeSubcategoria = "";
                        String Subcategoria = "";
                        SubCategoria sb = new SubCategoria();
                        if(intSubcategoria == 0){
                            nomeSubcategoria = JOptionPane.showInputDialog("Digite o nome da subcategoria da despesa:");
                            Subcategoria = JOptionPane.showInputDialog("Digite a descrição da subcategoria da despesa:");
                            sb.setCatergoriaSub(Subcategoria);
                            sb.setNomeSub(nomeSubcategoria);
                        }
                        String strValorDespesas = JOptionPane.showInputDialog("Digite o valor da despesa:");
                        float valorDespesas;
                        if(strValorDespesas.isEmpty() || strValorDespesas.isBlank()){
                            valorDespesas = 0;
                        }else{
                            valorDespesas = Float.parseFloat(strValorDespesas);
                        }
                        try{
                            Despesa d = new Despesa(strMes, strAno, k.getNomeDespesa(), k.getCategoriaDespesa(), nomeSubcategoria, valorDespesas);
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
                        despesas[i].salvarDados(despesas[i].getNomeDespesa(), despesas[i].getCategoriaDespesa(), despesas[i].getValorDespesa(), caminho);
                    }
                    break;
                
                case 3:
                    float somadorDespesas = 0;
                    float moradores = 0;
                    quebrar: do{
                        String strOpcao3 = JOptionPane.showInputDialog("Deseja calcular pela: \n"
                            + "1 - Regra igualitária\n"
                            + "2 - Regra proporcional\n"
                            + "0 - Sair");
                        int opcao3 = Integer.parseInt(strOpcao3);
                        switch (opcao3){
                            case 1:
                                try{
                                    CalcularDespesa calcular = new CalcularDespesa("m", 3);
                                    float[] resposta = new float[2]; 
                                    resposta = calcular.calcularIgualitaria();
                                    moradores = resposta[0];
                                    somadorDespesas = resposta[1];
                                }catch(Excecoes e){
                                    System.out.println(e.getMensagem());
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
                                String resultado = "";
                                try{
                                    CalcularDespesa calcular = new CalcularDespesa("m", 3);
                                    resultado = calcular.calcularProporcional();
                                }catch(Excecoes e){
                                    System.out.println(e.getMensagem());
                                }
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
                    break;
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