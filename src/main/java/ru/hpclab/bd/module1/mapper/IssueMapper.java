package ru.hpclab.bd.module1.mapper;

import org.mapstruct.Mapper;
import ru.hpclab.bd.module1.entity.IssueEntity;
import ru.hpclab.bd.module1.model.Issue;

/**
 * Mapper interface for mapping between Issue and IssueEntity objects.
 */
@Mapper
public interface IssueMapper {
    /**
     * Map an Issue object to an IssueEntity object.
     *
     * @param issue The Issue object to map.
     * @return An IssueEntity object.
     */
    IssueEntity issue2Entity(Issue issue);

    /**
     * Map an IssueEntity object to an Issue object.
     *
     * @param issueEntity The IssueEntity object to map.
     * @return An Issue object.
     */
    Issue entity2Issue(IssueEntity issueEntity);
}

