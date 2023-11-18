package ru.hpclab.bd.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.bd.module1.entity.BookEntity;

import java.util.List;

/**
 * Repository interface for accessing and managing book entities in the database.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    /**
     * Retrieve all books from the database.
     *
     * @return A list of all book entities.
     */
    List<BookEntity> findAll();
}
