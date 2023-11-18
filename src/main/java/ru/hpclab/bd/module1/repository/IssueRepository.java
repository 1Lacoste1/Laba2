package ru.hpclab.bd.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.bd.module1.entity.IssueEntity;

import java.util.List;

/**
 * Repository interface for accessing and managing issue entities in the database.
 */
@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    /**
     * Retrieve all issues from the database.
     *
     * @return A list of all issue entities.
     */
    List<IssueEntity> findAll();

    /**
     * Retrieve all issues associated with a user by their user ID.
     *
     * @param userId The ID of the user.
     * @return A list of issue entities associated with the specified user.
     */
    List<IssueEntity> findByUserId(Long userId);

    /**
     * Retrieve all issues associated with a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return A list of issue entities associated with the specified book.
     */
    List<IssueEntity> findByBookIsbn(String isbn);
}
