## CalcularDespesa.java:
- Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".

- Herança: A classe CalcularDespesa herda da classe Pessoa. Isso ocorre porque a classe CalcularDespesa necessita dos parâmetros nome e rendimento para que o calculo proporcional e igualitário possa ser realizado.
----------------------------------------------------------------------------------------------------------------------------------------------------
## Categoria.java e SubCategoria.java:
 - Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".
----------------------------------------------------------------------------------------------------------------------------------------------------
## Despesa.java:
- Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".

- Associação: A classe Despesa possui associções com as classes Categoria e SubCategoria para o momento do cadastro.

- Herança: A classe Despesa herda da classe SalvarDados. Isso ocorre porque a classe SalvarDados nos provê a funcionalidade de salvar os dados que será usada tanto pela classe Despesa quanto pela classe Pessoa. Para evitar a utilização do código de salvar em diversos lugares, foi criada a superclasse SalvarDados. 
----------------------------------------------------------------------------------------------------------------------------------------------------
## Excessoes.java:
- Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".

- Herança: A classe Excessoes herda da classe Exception. Isso ocorre porque, para criar novas Excessoes, é necessário herdar atributos da superclasse Exception, que é uma classe muito famosa e completa.
----------------------------------------------------------------------------------------------------------------------------------------------------
## Pessoa.java:
- Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".

- Herança: A classe Pessoa herda da classe SalvarDados. Isso ocorre porque a classe SalvarDados nos provê a funcionalidade de salvar os dados que será usada tanto pela classe Pessoa quanto pela classe Despesa. Para evitar a utilização do código de salvar em diversos lugares, foi criada a superclasse SalvarDados. 
----------------------------------------------------------------------------------------------------------------------------------------------------
## SalvarDados.java:
- Encapsulamento: As funcionalidades foram devidamente encapsuladas para que os dados não sofram acessos indevidos e também se houver alguma modificação, essa modificação seja feita de uma maneira mais controlada e "cirurgica".
