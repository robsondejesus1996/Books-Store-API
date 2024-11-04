# Bookstore Project - Spring Boot Application

Este projeto é uma aplicação para gerenciar uma livraria utilizando **Spring Boot** e **JPA (Java Persistence API)**. A aplicação permite operações CRUD (Create, Read, Update e Delete) em livros, incluindo autores e editoras, e integrações com banco de dados PostgreSQL.

## Estrutura do Projeto

O projeto está organizado seguindo a arquitetura MVC (Model-View-Controller), que promove uma separação clara entre as camadas de **modelo**, **serviço** e **controlador**. Esta organização permite uma manutenção simplificada e uma estrutura que facilita a expansão futura.

### Camadas e Padrões

- **Controller**: Controladores expõem endpoints RESTful, permitindo a interação com a aplicação por meio de requisições HTTP.
- **Service**: A camada de serviços contém a lógica de negócios e realiza as operações, integrando com o repositório de dados.
- **Repository**: Usa o Spring Data JPA para realizar operações no banco de dados, abstraindo a complexidade das consultas SQL.

## Componentes da Aplicação

- **BookController**: Controlador que gerencia as operações de livros, como listar, criar e deletar livros. Exemplo de endpoints:
  - `GET /bookstore/books`: Retorna todos os livros cadastrados.
  - `POST /bookstore/books`: Cria um novo livro com base nos dados enviados.
  - `DELETE /bookstore/books/{id}`: Deleta um livro pelo ID.

- **BookService**: A camada de serviço que contém a lógica de criação de um livro, associando-o a um autor, uma editora e uma revisão, quando fornecidos.

- **BookRepository**: Interface que estende `JpaRepository`, permitindo o uso de métodos CRUD e a criação de consultas customizadas para o banco de dados.

## Modelagem do Banco de Dados

A aplicação utiliza **JPA** e **Hibernate** para mapeamento objeto-relacional (ORM). As entidades principais incluem:

- **BookModel**: Representa um livro com um título, uma editora (publisher) e um conjunto de autores.
- **AuthorModel**: Representa o autor de um ou mais livros.
- **PublishModel**: Representa a editora de um livro.
- **ReviewModel**: Representa uma revisão (review) associada a um livro.

### Relacionamentos entre Entidades

- **BookModel e PublishModel**: Relacionamento Many-to-One, onde cada livro possui uma editora associada.
- **BookModel e AuthorModel**: Relacionamento Many-to-Many, permitindo que um livro tenha múltiplos autores e vice-versa.
- **BookModel e ReviewModel**: Relacionamento One-to-One, onde cada livro pode ter uma revisão associada.

## Configuração do Banco de Dados

A aplicação está configurada para utilizar um banco de dados **PostgreSQL**, com os parâmetros de conexão definidos no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore-jpa
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update


#### Exemplo de Código

```java
@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
    }
}
