package ru.hpclab.bd.module1.mapper;

import org.mapstruct.Mapper;
import ru.hpclab.bd.module1.entity.UserEntity;
import ru.hpclab.bd.module1.model.User;

/**
 * Mapper interface for mapping between User and UserEntity objects.
 */
@Mapper
public interface UserMapper {
    /**
     * Map a User object to a UserEntity object.
     *
     * @param user The User object to map.
     * @return A UserEntity object.
     */
    UserEntity user2Entity(User user);

    /**
     * Map a UserEntity object to a User object.
     *
     * @param userEntity The UserEntity object to map.
     * @return A User object.
     */
    User entity2User(UserEntity userEntity);
}
