# Projeto de estudo, seguindo o conteúdo apresentado no curso 'Arquitetura de Aplicações Rest com Spring Web'
O repositório contém, além das anotações nesse documento, um projeto Spring Boot desenvolvido seguindo o conteúdo apresentado na aula.
Caso haja divergência, deixo claro onde e o porquê de ter feito.

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

## Padões, Conceitos Aplicados e Definições
### REST (REpresentational State Transfer)
- Padrão arquitetural que visa facilitar a comunicação entre aplicações;
- Suporta diferentes formatos para transferência de dados; Entre eles JSON, XML, Plain Text;
- Define uma interface interface comum para interação com API via HTTP, fazendo uso dos verbos HTTP (POST, GET, etc.) em conjunto com separação dos recursos (como em: GET url.com/recurso);
- RESTful: Sistema que implementa padrão arquitetural REST;
### 

## Referências
- [Richardson Maturity Model: Steps toward the glory of REST](https://martinfowler.com/articles/richardsonMaturityModel.html)