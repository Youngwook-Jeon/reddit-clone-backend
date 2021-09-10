package com.young.dev.redditclone.repository;

import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.User;
import com.young.dev.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
