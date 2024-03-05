# Teste Técnico Backend - Simples Dental

## Descrição
O objetivo deste teste é avaliar a capacidade do candidato em desenvolver uma API RESTful utilizando a linguagem Java e o framework Spring Boot.

## Requisitos
- [Requisitos]((https://docs.google.com/document/d/1PQOAqM1Wmk_TdLmYzli2eS_CwRc4Z4QlrtMzDQkYd7k/edit?usp=sharing) )

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.3**
- **Maven** 
  - para gerenciamento de dependências
- **Spring Data JPA** 
  - para persistência de dados
- **PostgreSQL** 
  - como banco de dados
- **Flyway** 
  - para controle de versão do banco de dados
- **Lombok** 
  - para redução de boilerplate
- **OpenAPI/Swagger** 
  - para documentação da API
- **JUnit5 e Mockito** 
  - para testes
- **Docker e Docker Compose** 
  - para execução da aplicação

## Controle de versão
Os commits foram feitos utilizando o padrão [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

### Executando a aplicação
1. Para executar a aplicação sem a necessidade de configurar o ambiente, é necessária a intalação do **Docker** e **Docker Compose**. A instalação pode ser feita seguindo os seguintes tutoriais:

- [Instalação no Linux](https://dukescode.com/how-to-install-docker-and-docker-compose-on-ubuntu-22-04)
- [Instalação no Windows](https://www.bing.com/search?q=instalação+docker+e+docker+compose+windows&cvid=e03fa29565c1458b9ba9cbfda8c9d473&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIGCAEQABhAMgYIAhAAGEAyBggDEAAYQDIGCAQQABhAMgYIBRAAGEAyBggGEAAYQDIGCAcQABhAMgYICBAAGEDSAQg2Njc5ajBqNKgCALACAA&FORM=ANAB01&PC=U531)

2. Com o Docker e Docker Compose instalados, é necessário criar um arquivo chamado **.env** na raiz do projeto com as seguintes variáveis de ambiente:

```env
POSTGRES_DB=simplesdental
POSTGRES_USER=simplesdental
POSTGRES_PASSWORD=simplesdental
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/$POSTGRES_DB
```

3. Após a criação do arquivo **.env**, faça o build da aplicação com o comando:

```bash
  mvn clean install
```
4. Após o build, execute o comando:

```bash
  docker-compose up
```
5. A aplicação estará disponível em [http://localhost:8080](http://localhost:8080)

## Documentação
A documentação da API foi feita utilizando o OpenAPI/Swagger e está disponível em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) após a execução da aplicação.

## Testes Automatizados
Foram implementados testes de unidade para os serviços e testes de integração para os controladores. Para executar os testes, utilize o comando:
```bash
  mvn test
```