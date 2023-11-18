package ru.hpclab.bd.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.bd.module1.entity.UserEntity;

import java.util.List;

/**
 * Repository interface for accessing and managing user entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Retrieve all users from the database.
     *
     * @return A list of all user entities.
     */
    List<UserEntity> findAll();
}
