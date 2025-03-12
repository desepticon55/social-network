package dev.bd.work.socialnetwork.mapper;

import java.util.Collection;
import java.util.List;

/**
 * Abstract dto mapper.
 *
 * @param <M> model type
 * @param <D> dto type
 * @author Alexey_Bodyak
 */

public interface AbstractDtoMapper<M, D> {

    /**
     * Map domain model to dto.
     *
     * @param domainModel domain model
     * @return dto
     */
    D toDto(M domainModel);

    /**
     * Map list of domain models to list of dto.
     *
     * @param domainModelList list of domain model
     * @return list of dto
     */
    List<D> toDto(Collection<M> domainModelList);
}
