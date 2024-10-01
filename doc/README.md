# Estrutura de Projeto (SpringBoot)

## Controller
- Contém as classes que gerenciam as requisições `HTTP`. Normalmente essas classes são anotadas com `@RestController` ou `@Controller`.

## Model
- Contém as classes de entidades que representam os dados da aplicação, geralmente anotadas com `@Entity` para indicar que são mapeadas para tabelas no banco de dados.

## Service
- Contém a lógica de negócios da aplicação. As classes aqui são anotadas com `@Service`.

## Repository
- Define as interfaces e classes de persistência de dados. Geralmente `interfaces` estendem `JpaRepository` ou CrudRepository e são anotadas com `@Repository`.

# Gerenciadores de Databases H2 e JPA

## Introdução
- Primeiro é necessário entender a diferença entre os dois e as suas aplicações.

1. O que é o H2?
   * É um banco de dados relacional em memória ou persistente, escrito em Java. Ele é amplamente utilizado em ambientes de desenvolvimento e testes por sua simplicidade de configuração e por ser leve. A principal vantagem do H2 é que você pode rodar uma instância do banco dentro da própria aplicação, sem necessidade de configurar um servidor de banco de dados externo.

   * Modos de uso: 
        * In-memory (em memória): Os dados existem apenas durante a execução da aplicação e são perdidos quando o programa termina.
        * Arquivo (persistente): Os dados são salvos em disco e permanecem mesmo após a aplicação ser encerrada.
2. O que é o JPA?
   * É uma abstração da especificação JPA dentro do ecossistema Spring. Ele facilita ainda mais a implementação de repositórios de dados, oferecendo métodos prontos para CRUD (Create, Read, Update, Delete) e outras operações de banco de dados, sem precisar escrever SQL manualmente.
   * Por exemplo, com a interface JpaRepository, o Spring Data JPA gera automaticamente as operações básicas de banco de dados para você, com base na assinatura dos métodos que você define na interface.

## Configuração
* O Spring Boot simplifica bastante a configuração tanto do H2 quanto do JPA, automatizando grande parte do processo.
### 1. Configuração do H2
      *  Para usar o H2 em uma aplicação Spring Boot, você precisa adicionar a dependência no arquivo pom.xml:
      ```xml
        <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
          <scope>runtime</scope>
        </dependency>
    ```   
      * Em seguida, pode ser configurado no arquivo de configuração application.properties ou application.yml:
      ```properties
      # Configura o banco H2
      spring.datasource.url=jdbc:h2:mem:testdb  # usa um banco em memória
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=
      spring.h2.console.enabled=true  # habilita o console H2 acessível no navegador
        
      # Configura o comportamento do banco de dados (criação e atualização de tabelas)
      spring.jpa.hibernate.ddl-auto=update  # cria/atualiza tabelas automaticamente
      spring.jpa.show-sql=true  # mostra as queries SQL geradas no console
      spring.h2.console.path=/h2-console  # define a URL do console (http://localhost:8080/h2-console)
      ```
     * Com spring.h2.console.enabled=true, você pode acessar o console do H2 pelo navegador para ver os dados diretamente. O console fica disponível na URL configurada (geralmente http://localhost:8080/h2-console). Lá você pode executar consultas SQL diretamente no banco.
### 2. Configurando JPA
   * A configuração do JPA com o Spring Boot é feita automaticamente quando você inclui as dependências corretas. Aqui está um exemplo de dependências JPA/Hibernate em um pom.xml:
   ```xml
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
     </dependency>
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
     </dependency>
   ```
      * O Spring Boot configura automaticamente o JPA e o Hibernate como provedor de persistência padrão, e a configuração básica é feita através de propriedades como `spring.jpa.hibernate.ddl-auto` para definir como as entidades são mapeadas para tabelas no banco de dados: 
      * `spring.jpa.hibernate.ddl-auto`:
        * `create`: Cria o banco de dados do zero toda vez que o aplicativo é iniciado (perde os dados existentes). 
        * `update`: Atualiza o esquema do banco de dados conforme as mudanças nas entidades. 
        * `create-drop`: Cria o banco de dados ao iniciar a aplicação e o destrói ao encerrar. 
        * `validate`: Apenas valida o esquema sem criar ou modificar tabelas.
---

## Conclusão
* O H2 é um banco de dados leve, ideal para desenvolvimento e testes, permitindo persistir dados em memória. 
* O JPA é a especificação Java para o mapeamento objeto-relacional, permitindo trabalhar com bancos de dados relacionais de maneira orientada a objetos.