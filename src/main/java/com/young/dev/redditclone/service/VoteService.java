package com.young.dev.redditclone.service;

import com.young.dev.redditclone.dto.VoteDto;
import com.young.dev.redditclone.exceptions.PostNotFoundException;
import com.young.dev.redditclone.exceptions.SpringRedditException;
import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.Vote;
import com.young.dev.redditclone.repository.PostRepository;
import com.young.dev.redditclone.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.young.dev.redditclone.model.VoteType.UPVOTE;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(
                        () -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId())
                );
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
