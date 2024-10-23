package com.bookstore.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "TB_BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublishModel publisher;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //remover junto com FetchType.Lazy
    @ManyToMany//(fetch = FetchType.LAZY)
    @JoinTable( //tabela auxiliar
            name = "tb_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReviewModel review;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;
}
