package com.bookstore.jpa.repository;

import com.bookstore.jpa.model.PublishModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublishModel, UUID> {
}
