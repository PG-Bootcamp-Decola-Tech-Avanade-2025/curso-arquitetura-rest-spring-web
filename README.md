# Projeto de estudo, seguindo o conteúdo apresentado no curso 'Arquitetura de Aplicações Rest com Spring Web'
O repositório contém, além das anotações nesse documento, um projeto Spring Boot desenvolvido seguindo o conteúdo apresentado na aula.
Caso haja divergência deixo claro, em comentários no código, o porquê de ter divergido da implementação demonstrada no curso.

## Setup
### Estrutura do projeto + dependências iniciais
O projeto foi estruturado da com ajuda da ferramenta [Spring Initializr](start.spring.io), incluindo algumas dependências que—acredito—serão necessárias no curso.

![Imagem do setup configurado no Spring Initializr](./img/setup_initializr.png)

### Faltaram algumas dependências...
- Spring Data JPA, sem ela não tenho como interagir com banco de dados. O starter da dependência, incluído no pom.xml, é:
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>3.4.3</version>
    </dependency>
    ```

### Configuração adicional
- Configuração do banco de dados in-memory h2 para testes; Adicionada ao arquivo resources/application.properties.
  ```
  spring.application.name=arquitetura_rest_spring_web
  
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  ```
- Configuração inicial do Spring Security pra facilitar testes; Adicionada ao arquivo resources/application.properties.
  ```
  # Defino usuário e senha padrão pra evitar de ter que usar a senha gerada no startup da aplicação.
  spring.security.user.name=user
  spring.security.user.password=password
  ```

## Padões, Conceitos Aplicados e Definições

### REST (REpresentational State Transfer)
- Padrão arquitetural que visa facilitar a comunicação entre aplicações;
- Suporta diferentes formatos para transferência de dados; Entre eles JSON, XML, Plain Text;
- Define uma interface interface comum para interação com API via HTTP, fazendo uso dos verbos HTTP (POST, GET, etc.) em conjunto com separação dos recursos (como em: GET url.com/recurso);
- RESTful: Sistema que implementa padrão arquitetural REST;

### Exception Handling no Spring
- Annotation `@ControllerAdvice`: Permite definir uma classe responsável por tratativa de excessões globalmente dentro da aplicação.
  - Classes de `@ControllerAdvice` devem herdar de `ResponseEntityExceptionHandler`.
- Annotation `@ExceptionHandler`: Permite, dentro de uma classe decorada com `@ControllerAdvice`, indicar métodos responsáveis por lidar com excessões específicas e retornar respostas de acordo.
  - Métodos de `@ExceptionHandler` devem especificar uma ou mais Exceptions às quais devem responder; Feito da seguinte maneira: `@ExceptionHandler(Exception.class)` ou `@ExceptionHandler({ExceptionClassA.class, ExceptionClassB.class})`.

### Spring Security
- Uma biblioteca do ecossistema Spring que fornece features de segurança pra projetos.
- Fornece funções de autenticação (utilizando diversos formatos como Basic Auth, OAuth2, JWT, etc.), autorização e proteção contra ataques comuns.
  - Sobre o OAuth2: Esse tipo de autenticação é quase como uma terceirização da autenticação, onde um serviço terceiro autentica o cliente. É o que está sendo usado em opções como "Login com Google".
  - Sobre JWT: É uma forma de autenticação que utiliza tokens em formato base64 validados; Esses tokens são fornecidos pela aplicação em si e são compostos pelas credenciais do usuário juntamente com metadados relevantes. Geralmente autenticação com tokens JWT utiliza mecanismo de "validade" para cada token; Fazendo com que, após a expiração de seu token, o usuário tenha que se autenticar novamente.

### Actuator, Métricas e Health Check
- Actuator: Fornece métricas, e informações relacionadas à saúde da aplicação pós-deploy.
  - Após a inclusão da dependência no POM, já entra em efeito.
  - Curiosamente, parece implementar HATEOAS... e eu achei que isso não existia de verdade 0.0

## Referências
- [Martin Fowler - Richardson Maturity Model: Steps toward the glory of REST](https://martinfowler.com/articles/richardsonMaturityModel.html)
- [Beldung - Spring Boot With H2 Database](https://www.baeldung.com/spring-boot-h2-database)