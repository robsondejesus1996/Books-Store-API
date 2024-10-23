package com.bookstore.jpa.repository;

import com.bookstore.jpa.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

}
