package com.young.dev.redditclone.mapper;

import com.young.dev.redditclone.dto.PostRequest;
import com.young.dev.redditclone.dto.PostResponse;
import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.Subreddit;
import com.young.dev.redditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    public abstract PostResponse mapToDto(Post post);
}
