package com.young.dev.redditclone.repository;

import com.young.dev.redditclone.model.Subreddit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Optional<Subreddit> findByName(String subredditName);

    @EntityGraph(attributePaths = "posts")
    @Query("SELECT s from Subreddit s")
    List<Subreddit> findAllWithPosts();
}
