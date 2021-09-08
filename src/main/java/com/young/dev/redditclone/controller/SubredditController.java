package com.young.dev.redditclone.controller;

import com.young.dev.redditclone.dto.SubredditDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor @Slf4j
public class SubredditController {

    @PostMapping
    public void createSubreddit(@RequestBody SubredditDto subredditDto) {

    }
}
