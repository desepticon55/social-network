package dev.bd.work.socialnetwork.mapper;

import java.util.Collection;
import java.util.List;


/**
 * Abstract domain mapper.
 *
 * @param <M> domain model type
 * @param <D> dto type
 * @author Alexey_Bodyak
 */

public interface AbstractDomainMapper<M, D> {

    /**
     * Map dto -> domain model.
     *
     * @param dto dto
     * @return domain model
     */
    M toDomainModel(D dto);

    /**
     * Map list of dto -> list of domain models.
     *
     * @param dtoList list of dto
     * @return list of domain models
     */
    List<M> toDomainModel(Collection<D> dtoList);
}
