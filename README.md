
# Descrição:

Monitoramente de Ações foi desenvolvido com o intuito de auxiliar na compra e venda de ações, onde deveria ser utilizado o Spring Boot, um framework Java.

## Entidades

   - Account (conta) representa o usuario que esta processando a aplicação.

   - StockExchangeMonitoring (Monitoramento da Bolsa de valores) representa a regra de compra e venda das Ações assim como o saldo para compra.

   - MonitoringHistory (Monitoramento de Historico) representa o historico de Compra e venda das ações envolvidas no processamento da aplicação.

## Relacionamento

   - Uma conta possui um Monitoramento da Bolsa de valores.
   
   - Um Monitoramento da Bolsa possui vinculo com uma conta.
    
   - Um Monitoramento da bolsa possui sua regra de compra e venda de ações.
   
   - Um Histórico possui uma ou mais transações relacionadas a uma empresa.
   
   - Um Histórico pertence a uma transação do Monitoramento da Bolsa

## Arquitetura Utilizada

   - Controller - Recebe a request, monta o Objeto e direciona para a Service.
   
   - Service - recebe o Objeto faz as devidas tratativas com relação aos seus atributos e regra de negocio.
   
   - Service - Ainda na service o Objeto é enviado para uma interface Repository.
   
   - Repository - Intercafe responsavel por efetivar as interação com o Banco de Dados.
   
   - Enums - Modelagem dos enums
   
## Frameworks
  
   - Para o Backend foi utilizado somente o Spring Boot, que foi solicitado pelo desafio.
   
   - Para Frontend foi  utilizado apenas o Bootstrap, para dar uma apresentação mais agradavel, apesar de não ter dado muita atenção pois não era o objetivo do Desafio.
   
## Pré-requisitos

   - PostgreSQL 10 Link para download: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
   
   - Maven. Link para download: http://ftp.unicamp.br/pub/apache/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz
   
   - Git. Link para downlaod: https://git-scm.com/download/win
   
## Iniciando a configurar a aplicação

   - Escolha um local para baixar os arquivos.
   
   - Utilize o Git Bash para baixar o repositorio Git.
   
   - Use o comando:  git clone URL_do_projeto.git
   
###### Configurando o Banco de Dados da Aplicação
 
   - Na configuração do Banco, foi utilizado a porta padrão "5432", com o usuario "postgres" e senha "root123".
   
   - No caso de ter o Banco configurado ja préviamente com outros dados, utilizar a porta,login e senha referente a sua configuração pessoal.
   
   - Para esta configuração devera alterar as seguintes linhas do arquivo "application.properties"
    - spring.datasource.username=postgres
    - spring.datasource.password=root123
    - spring.datasource.url=jdbc:postgresql://localhost:5432/stock_exchanges
   
   - Crie o Banco da dados com o nome "stock_exchanges" case deseja outro nome apenas modificar a linha informada a baixo.   
	- spring.datasource.url=jdbc:postgresql://localhost:5432/stock_exchanges
   
   - Ao executar a aplicação ela sera executada na porta 8080 por padrão.Caso necessite alterar a porta incluir a linha no arquivo "application.properties".
    - server.port:1234   
   
 ###### Gerando o JAR
 
   - Ainda com o Git Bash aberto, caso não esteja abrir ele no repositorio clonado, executar o seguinte codigo
   
    - $ cd stockExchanges
	
   - Após isso execute o comando para gerar o JAR deste repositorio
   
    - $ mvn package
   
   - Agora execute o comando a baixo para entrar no local em que o diretorio do JAr foi criado
   
    - $ cd target
   
   - Execute o JAR com o seguinte comando
   
    - $ java -jar stockExchanges-0.0.1-SNAPSHOT.jar  
   
   - Pronto! Agora é só abrir a URL abaixo:
    
    - http://localhost:8080
