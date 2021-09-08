package com.young.dev.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SubredditDto {

    private Long id;
    private String subredditName;
    private String description;
    private Integer numberOfPosts;
}
