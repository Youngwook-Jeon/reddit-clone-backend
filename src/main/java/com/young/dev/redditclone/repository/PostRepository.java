package com.young.dev.redditclone.repository;

import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.Subreddit;
import com.young.dev.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
