package com.young.dev.redditclone.repository;

import com.young.dev.redditclone.model.Comment;
import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
