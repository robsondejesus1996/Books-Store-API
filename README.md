# Bookstore Project - Spring Boot Application

This project is an application to manage a bookstore using **Spring Boot** and **JPA (Java Persistence API)**. It allows CRUD operations (Create, Read, Update, and Delete) on books, including authors and publishers, and integrates with a PostgreSQL database.

## Project Structure

The project is organized following the **MVC (Model-View-Controller)** architecture, promoting a clear separation between **model**, **service**, and **controller** layers. This structure simplifies maintenance and facilitates future scalability.

### Layers and Patterns

- **Controller**: Controllers expose RESTful endpoints, allowing interaction with the application through HTTP requests.
- **Service**: The service layer contains business logic and performs operations, integrating with the data repository.
- **Repository**: Uses Spring Data JPA to perform database operations, abstracting the complexity of SQL queries.

## Application Components

- **BookController**: Controller that manages book operations, such as listing, creating, and deleting books. Example endpoints:
  - `GET /bookstore/books`: Returns all registered books.
  - `POST /bookstore/books`: Creates a new book based on the provided data.
  - `DELETE /bookstore/books/{id}`: Deletes a book by ID.

- **BookService**: The service layer that contains the logic for creating a book, associating it with an author, a publisher, and a review when provided.

- **BookRepository**: Interface that extends `JpaRepository`, enabling the use of CRUD methods and creating custom database queries.

## Database Modeling

The application uses **JPA** and **Hibernate** for object-relational mapping (ORM). The main entities include:

- **BookModel**: Represents a book with a title, a publisher, and a set of authors.
- **AuthorModel**: Represents the author of one or more books.
- **PublishModel**: Represents the publisher of a book.
- **ReviewModel**: Represents a review associated with a book.

### Entity Relationships

- **BookModel and PublishModel**: Many-to-One relationship, where each book is associated with a publisher.
- **BookModel and AuthorModel**: Many-to-Many relationship, allowing a book to have multiple authors and vice versa.
- **BookModel and ReviewModel**: One-to-One relationship, where each book can have an associated review.

## Database Configuration

The application is configured to use a **PostgreSQL** database, with connection parameters defined in the `application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore-jpa
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update



#### Code Example

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
