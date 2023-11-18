package ru.hpclab.bd.module1.mapper;
import org.mapstruct.Mapper;
import ru.hpclab.bd.module1.entity.BookEntity;
import ru.hpclab.bd.module1.model.Book;

/**
 * Mapper interface for mapping between Book and BookEntity objects.
 */
@Mapper
public interface BookMapper {
    /**
     * Map a Book object to a BookEntity object.
     *
     * @param book The Book object to map.
     * @return A BookEntity object.
     */
    BookEntity book2Entity(Book book);

    /**
     * Map a BookEntity object to a Book object.
     *
     * @param bookEntity The BookEntity object to map.
     * @return A Book object.
     */
    Book entity2Book(BookEntity bookEntity);
}
