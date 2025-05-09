package dev.bd.work.socialnetwork.mapper;

import dev.bd.work.socialnetwork.dto.PostDto;
import dev.bd.work.socialnetwork.model.Post;
import org.mapstruct.Mapper;

/**
 * Mapper from {@link Post} to {@link PostDto}.
 *
 * @author Alexey Bodyak
 */
@Mapper
public interface PostDtoMapper extends AbstractDtoMapper<Post, PostDto> {
}
