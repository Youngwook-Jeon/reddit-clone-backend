package com.young.dev.redditclone.service;

import com.young.dev.redditclone.dto.PostRequest;
import com.young.dev.redditclone.dto.PostResponse;
import com.young.dev.redditclone.exceptions.PostNotFoundException;
import com.young.dev.redditclone.exceptions.SubredditNotFoundException;
import com.young.dev.redditclone.mapper.PostMapper;
import com.young.dev.redditclone.model.Post;
import com.young.dev.redditclone.model.Subreddit;
import com.young.dev.redditclone.model.User;
import com.young.dev.redditclone.repository.PostRepository;
import com.young.dev.redditclone.repository.SubredditRepository;
import com.young.dev.redditclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service @Transactional
@RequiredArgsConstructor @Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        return postRepository.save(
                postMapper.map(postRequest, subreddit, authService.getCurrentUser())
        );
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
