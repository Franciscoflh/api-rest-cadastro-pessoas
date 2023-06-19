# API REST para Cadastro de Pessoas

Esta é uma API REST desenvolvida em Java com o framework Spring Boot. Ela permite realizar operações de cadastro, atualização, remoção e consulta de pessoas.

## Pré-requisitos

-  Java 17
-   Maven
-   PostgreSQL


## Configuração do Banco de Dados

1.  A API utiliza o banco de dados PostgreSQL. Para configurar a conexão com o banco de dados, siga as etapas abaixo:

1.  Crie um banco de dados no PostgreSQL para a aplicação.
2.  Abra o arquivo `application.properties` localizado no diretório `src/main/resources`.
3.  Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` para refletir as configurações do seu banco de dados.

## Executando a API


Siga as etapas abaixo para executar a API localmente:

1.  Clone este repositório em sua máquina.
    
2.  Abra um terminal na pasta raiz do projeto.
    
3.  Execute o seguinte comando para compilar o projeto e gerar o pacote JAR:
    
    `mvn clean package` 
    
4.  Após a conclusão do processo de compilação, execute o comando a seguir para iniciar a API:
    
    `java -jar target/api-rest-cadastro-pessoas.jar` 
    
5.  A API estará disponível no seguinte URL: `http://localhost:8080/api/pessoas`

## Endpoints disponíveis

A API fornece os seguintes endpoints:

-   `GET /api/pessoas`: Retorna a lista de todas as pessoas cadastradas.
-   `GET /api/pessoas/{id}`: Retorna os detalhes de uma pessoa específica com base no ID.
-   `GET /api/pessoas/paginado`: Retorna uma lista paginada de pessoas.
-   `POST /api/pessoas`: Cria uma nova pessoa.
-   `PUT /api/pessoas/{id}`: Atualiza os dados de uma pessoa existente.
-   `DELETE /api/pessoas/{id}`: Remove uma pessoa com base no ID.

Você pode utilizar ferramentas como o Postman ou o cURL para realizar requisições HTTP para os endpoints e testar a API.
